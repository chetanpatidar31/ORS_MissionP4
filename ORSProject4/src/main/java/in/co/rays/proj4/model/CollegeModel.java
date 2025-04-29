package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel {

	public Integer nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_college");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public void add(CollegeBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO st_college VALUES(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("data inserted");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(CollegeBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE st_college SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONE_NO=?,MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			pstmt.executeUpdate();
			System.out.println("updated successfully...");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(long id) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM st_college WHERE id=?");

			pstmt.setLong(1, id);
			
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("deleted succesfully");
			pstmt.close();

		} catch (Exception e) {
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
	
				e1.printStackTrace();
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

}
