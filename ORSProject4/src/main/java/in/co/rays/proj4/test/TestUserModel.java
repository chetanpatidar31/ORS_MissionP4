package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
		testAdd();
//		testUpdate();
//		testDelete();
//		testfindByPk();
//		testFindByLogin();
//		testFindByAuthenticate();
	}

	private static void testNextPk() {
		UserModel model = new UserModel();
		System.out.println("Next Pk: " + model.nextPk());
	}

	private static void testAdd() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Nitin");
		bean.setLastName("Patidar");
		bean.setLogin("nitin@gmail.com");
		bean.setPassword("12345");
		bean.setDob(sdf.parse("2005-12-15"));
		bean.setMobileNo("9755058601");
		bean.setRoleId(2l);
		bean.setGender("male");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		UserModel model = new UserModel();
		model.add(bean);

	}

	private static void testUpdate() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setFirstName("Ravi");
		bean.setLastName("Patidar");
		bean.setLogin("nitin@gmail.com");
		bean.setPassword("1234asdf");
		bean.setDob(sdf.parse("2005-12-15"));
		bean.setMobileNo("9755058601");
		bean.setRoleId(2l);
		bean.setGender("male");
		bean.setModifiedBy("root");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		UserModel model = new UserModel();
		model.update(bean);

	}

	private static void testDelete() throws Exception {
		UserModel model = new UserModel();
		model.delete(1);
	}

	private static void testfindByPk() throws Exception {
		UserModel model = new UserModel();

		UserBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getDob());
			System.out.println("\t" + bean.getMobileNo());

		} else {
			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testFindByLogin() throws Exception {
		UserModel model = new UserModel();

		UserBean bean = model.findByLogin("nitin@gmail.com");

		if (bean != null) {
			System.out.print(bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getDob());
			System.out.println("\t" + bean.getMobileNo());

		} else {
			throw new RecordNotFoundException("Login id not found");
		}

	}

	private static void testFindByAuthenticate() throws Exception {
		UserModel model = new UserModel();

		UserBean bean = model.findByAuthenticate("nitin@gmail.com", "12345");

		if (bean != null) {
			System.out.print(bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getDob());
			System.out.println("\t" + bean.getMobileNo());

		} else {
			throw new RecordNotFoundException("Login id not found");
		}

	}

}
