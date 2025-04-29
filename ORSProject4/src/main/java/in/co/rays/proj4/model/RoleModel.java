package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.relation.Role;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class RoleModel {

	public Integer nextPk() {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pk + 1;
	}

	public void add(RoleBean bean) throws Exception {
		Connection conn = null;
		
		RoleBean existBean = findByName(bean.getName());
		
		if (existBean !=null) {
			throw new DuplicateRecordException("Login id already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("DATA Inserted... " + i);

			JDBCDataSource.closeConnection(conn);

		} catch (Exception e) {

			JDBCDataSource.trnRollback(conn);
		}
	}

	public void update(RoleBean bean) throws Exception {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"Update st_role set name=?,description=?,modified_by=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getModifiedBy());
			pstmt.setTimestamp(4, bean.getModifiedDatetime());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("Updated Successfully ..... " + i);

			JDBCDataSource.closeConnection(conn);

		} catch (Exception e) {

			JDBCDataSource.trnRollback(conn);
		}
	}

	public void delete(long id) throws Exception {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("deleted sucessfully ....." + i);

			JDBCDataSource.closeConnection(conn);

		} catch (Exception e) {
			JDBCDataSource.trnRollback(conn);
		}
	}

	public RoleBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id=?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;

		while (rs.next()) {
			bean = new RoleBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));

		}

		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public RoleBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where name =?");

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;

		while (rs.next()) {
			bean = new RoleBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));
		}
		return bean;
	}
}
