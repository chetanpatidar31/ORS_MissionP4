package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel {

	public Integer nextPk() {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(UserBean bean) throws Exception {

		Connection conn = null;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			throw new DuplicateRecordException("Login id already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();

			JDBCDataSource.closeConnection(conn);

			System.out.println("data inserted ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
		}

	}

	public void update(UserBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name=?,last_name=?,login=?,password=?,dob=?,mobile_no=?,role_id=?,gender=?,modified_by=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			JDBCDataSource.closeConnection(conn);

			System.out.println("updated successfully ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
		}
	}

	public void delete(long id) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id=?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			JDBCDataSource.closeConnection(conn);

			System.out.println("Deleted successfully ...." + i);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
		}
	}

	public UserBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id=?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

		}
		return bean;
	}

	public UserBean findByLogin(String login) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login=?");

		pstmt.setString(1, login);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

		}
		return bean;
	}

	public UserBean findByAuthenticate(String login, String password) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login=? and password=?");

		pstmt.setString(1, login);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();

			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));

		}
		return bean;
	}
}
