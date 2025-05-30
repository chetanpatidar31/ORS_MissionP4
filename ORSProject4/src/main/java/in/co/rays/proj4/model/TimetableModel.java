package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class TimetableModel {

	public Integer nextPk() {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_timetable");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(TimetableBean bean) throws Exception {
		Connection conn = null;

		CourseModel cModel = new CourseModel();

		SubjectModel sModel = new SubjectModel();

		if (bean.getCourseId() > 0) {
			CourseBean courseBean = cModel.findByPk(bean.getCourseId());
			bean.setCourseName(courseBean.getName());
		}

		if (bean.getSubjectId() > 0) {
			SubjectBean subjectBean = sModel.findByPk(bean.getSubjectId());
			bean.setSubjectName(subjectBean.getName());
		}
		
		TimetableBean DuplicateSemester = findBySemester(bean.getSemester());

		if (DuplicateSemester != null) {
			throw new DuplicateRecordException("Semester already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getSemester());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(5, bean.getExamTime());
			pstmt.setLong(6, bean.getCourseId());
			pstmt.setString(7, bean.getCourseName());
			pstmt.setLong(8, bean.getSubjectId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

			System.out.println("inserted successfully..." + i);

		} catch (Exception e) {
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new ApplicationException("Exception : exception in add timetable");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(TimetableBean bean) throws Exception {
		Connection conn = null;

		CourseModel cModel = new CourseModel();

		SubjectModel sModel = new SubjectModel();

		if (bean.getCourseId() > 0) {
			CourseBean courseBean = cModel.findByPk(bean.getCourseId());
			bean.setCourseName(courseBean.getName());
		}

		if (bean.getSubjectId() > 0) {
			SubjectBean subjectBean = sModel.findByPk(bean.getSubjectId());
			bean.setSubjectName(subjectBean.getName());
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set semester=?,description=?,exam_date=?,exam_time=?,course_id=?,course_name=?,subject_id=?,subject_name=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getSemester());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(4, bean.getExamTime());
			pstmt.setLong(5, bean.getCourseId());
			pstmt.setString(6, bean.getCourseName());
			pstmt.setLong(7, bean.getSubjectId());
			pstmt.setString(8, bean.getSubjectName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

			System.out.println("Updated successfully..." + i);

		} catch (Exception e) {
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new ApplicationException("Exception : exception in upadate timetable");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(long id) throws ApplicationException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_timetable where = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();
			pstmt.close();

			System.out.println("Updated successfully..." + i);

		} catch (Exception e) {
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new ApplicationException("Exception : Exception in delete timetable");
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public TimetableBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_timeTable where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		TimetableBean bean = null;
		while (rs.next()) {
			bean = new TimetableBean();

			bean.setId(rs.getLong(1));
			bean.setSemester(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setExamDate(rs.getDate(4));
			bean.setExamTime(rs.getString(5));
			bean.setCourseId(rs.getLong(6));
			bean.setCourseName(rs.getString(7));
			bean.setSubjectId(rs.getLong(8));
			bean.setSubjectName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
		}
		return bean;
	}

	public TimetableBean findBySemester(String semester) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_timeTable where semester = ?");
		pstmt.setString(1, semester);

		ResultSet rs = pstmt.executeQuery();

		TimetableBean bean = null;
		while (rs.next()) {
			bean = new TimetableBean();

			bean.setId(rs.getLong(1));
			bean.setSemester(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setExamDate(rs.getDate(4));
			bean.setExamTime(rs.getString(5));
			bean.setCourseId(rs.getLong(6));
			bean.setCourseName(rs.getString(7));
			bean.setSubjectId(rs.getLong(8));
			bean.setSubjectName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
		}
		return bean;
	}

	public List search(TimetableBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		StringBuilder sql = new StringBuilder("select * from st_timeTable where 1 = 1");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();
		while (rs.next()) {
			bean = new TimetableBean();

			bean.setId(rs.getLong(1));
			bean.setSemester(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setExamDate(rs.getDate(4));
			bean.setExamTime(rs.getString(5));
			bean.setCourseId(rs.getLong(6));
			bean.setCourseName(rs.getString(7));
			bean.setSubjectId(rs.getLong(8));
			bean.setSubjectName(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

			list.add(bean);
		}
		return list;
	}
	
	public List list() throws Exception {
		return search(null, 0, 0);
	}
}
