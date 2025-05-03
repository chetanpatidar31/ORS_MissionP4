package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel {

	public Integer nextPk() {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_marksheet");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public void add(MarksheetBean bean) throws Exception {

		Connection conn = null;

		StudentModel sModel = new StudentModel();
		StudentBean studentbean = sModel.findByPk(bean.getStudentId());
		String studentname = (studentbean.getFirstName() + " " + studentbean.getLastName());

		MarksheetBean duplicateRollNo = findByRollNo(bean.getRollNo());

		if (duplicateRollNo != null) {
			throw new DuplicateRecordException("RollNo already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, studentname);
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			JDBCDataSource.closeConnection(conn);

			System.out.println("data Inserted ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
			e.printStackTrace();
		}
	}

	public void update(MarksheetBean bean) throws Exception {

		Connection conn = null;

		StudentModel sModel = new StudentModel();
		StudentBean studentbean = sModel.findByPk(bean.getStudentId());
		String studentname = (studentbean.getFirstName() + " " + studentbean.getLastName());
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_marksheet set roll_no=?,student_id=?,name=?,physics=?,chemistry=?,maths=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, studentname);
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			int i = pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			JDBCDataSource.closeConnection(conn);

			System.out.println("data updated ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
			e.printStackTrace();
		}
	}

	public void delete(long id) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_marksheet where id=?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			JDBCDataSource.closeConnection(conn);

			System.out.println("Deleted successfully ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
			e.printStackTrace();
		}
	}

	public MarksheetBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where id=?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();

		MarksheetBean bean = null;

		while (rs.next()) {
			bean = new MarksheetBean();

			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public MarksheetBean findByRollNo(String rollId) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where roll_no=?");
		pstmt.setString(1, rollId);
		ResultSet rs = pstmt.executeQuery();

		MarksheetBean bean = null;

		while (rs.next()) {
			bean = new MarksheetBean();

			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public List search(MarksheetBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		StringBuilder sql = new StringBuilder("select * from st_marksheet where 1 = 1");

		if (bean != null) {
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" AND roll_no like '" + bean.getRollNo() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND name like '" + bean.getName() + "%'");
			}
			if (bean.getPhysics() != null && bean.getPhysics() > 0) {
				sql.append(" AND physics = " + bean.getPhysics());
			}
			if (bean.getChemistry() != null && bean.getChemistry() > 0) {
				sql.append(" AND chemistry = " + bean.getChemistry());
			}
			if (bean.getMaths() != null && bean.getMaths() > 0) {
				sql.append(" AND maths = " + bean.getMaths());
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql..." + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new MarksheetBean();
			bean.setId(rs.getLong(1));
			bean.setRollNo(rs.getString(2));
			bean.setStudentId(rs.getLong(3));
			bean.setName(rs.getString(4));
			bean.setPhysics(rs.getInt(5));
			bean.setChemistry(rs.getInt(6));
			bean.setMaths(rs.getInt(7));
			bean.setCreatedBy(rs.getString(8));
			bean.setModifiedBy(rs.getString(9));
			bean.setCreatedDatetime(rs.getTimestamp(10));
			bean.setModifiedDatetime(rs.getTimestamp(11));
			list.add(bean);
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return list;
	}

}
