package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SubjectModel {

	public Integer nextPk() {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_subject");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(SubjectBean bean) throws Exception {

		Connection conn = null;
		
		CourseModel cModel = new CourseModel();
		CourseBean courseBean = cModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectBean duplicateName = findByName(bean.getName());

		if (duplicateName != null) {
			throw new DuplicateRecordException("Subject already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_subject values(?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

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

	public void update(SubjectBean bean) throws Exception {

		Connection conn = null;
		
		CourseModel cModel = new CourseModel();
		CourseBean courBean=cModel.findByPk(bean.getCourseId());
		bean.setCourseName(courBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_subject set name=?,course_id=?,course_name=?,description=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getName());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
			JDBCDataSource.closeConnection(conn);

			System.out.println("data Updated ...." + i);

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
			PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id=?");

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
	
	public SubjectBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_subject where id=?");
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();

		SubjectBean bean = null;

		while (rs.next()) {
			bean = new SubjectBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCourseId(rs.getLong(3));
			bean.setCourseName(rs.getString(4));
			bean.setDescription(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return bean;
	}
	
	public SubjectBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_subject where name=?");
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();

		SubjectBean bean = null;

		while (rs.next()) {
			bean = new SubjectBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCourseId(rs.getLong(3));
			bean.setCourseName(rs.getString(4));
			bean.setDescription(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return bean;
	}
	
	public List search(SubjectBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuilder sql = new StringBuilder("select * from st_subject where 1 = 1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND Description like '" + bean.getDescription() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND Course_id = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND course_Name like '" + bean.getCourseName() + "%'");
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
			bean = new SubjectBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCourseId(rs.getLong(3));
			bean.setCourseName(rs.getString(4));
			bean.setDescription(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));

			list.add(bean);
		}
		rs.close();
		JDBCDataSource.closeConnection(conn);
		return list;
	}
}