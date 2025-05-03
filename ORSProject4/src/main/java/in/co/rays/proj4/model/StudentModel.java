package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel {

	public Integer nextPk() {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(StudentBean bean) throws Exception {

		Connection conn = null;
		
		CollegeModel cModel = new CollegeModel();
		CollegeBean collBean=cModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collBean.getName());
		
		StudentBean duplicateEmail = findByEmail(bean.getEmail());
		if(duplicateEmail != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_student values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			JDBCDataSource.closeConnection(conn);

			System.out.println("data inserted ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
			e.printStackTrace();
		}

	}

	public void update(StudentBean bean) throws Exception {

		Connection conn = null;
		
		CollegeModel cModel = new CollegeModel();
		CollegeBean collBean=cModel.findByPk(bean.getCollegeId());
		bean.setCollegeName(collBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_student set first_name=?,last_name=?,dob=?,gender=?,mobile_no=?,email=?,college_id=?,college_name=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			pstmt.setString(8, bean.getCollegeName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

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
			PreparedStatement pstmt = conn.prepareStatement("delete from st_student where id=?");

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

	public StudentBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_student where id=?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();

		StudentBean bean = null;

		while (rs.next()) {
			bean = new StudentBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public StudentBean findByEmail(String email) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_student where email=?");
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();

		StudentBean bean = null;

		while (rs.next()) {
			bean = new StudentBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public List search(StudentBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuilder sql = new StringBuilder("select * from st_student where 1 = 1");

		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getDob() != null) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL_ID like '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME = '" + bean.getCollegeName() + "%'");
			}

			if (bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGE_ID = '" + bean.getCollegeId() + "%'");
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
			bean = new StudentBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

			list.add(bean);
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return list;
	}
}
