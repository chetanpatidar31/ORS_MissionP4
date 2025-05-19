package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
//		testAdd();
		testUpdate();
//		testDelete();
//		testfindByPk();
//		testFindByLogin();
//		testAuthenticate();
//		testSearch();
	}

	private static void testNextPk() {
		UserModel model = new UserModel();
		System.out.println("Next Pk: " + model.nextPk());
	}

	private static void testAdd() throws Exception {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Harshit");
		bean.setLastName("Patidar");
		bean.setLogin("harshit@gmail.com");
		bean.setPassword("12345");
		bean.setDob(sdf.parse("2003-10-05"));
		bean.setMobileNo("9755058881");
		bean.setRoleId(3l);
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
		bean.setFirstName("Nitin");
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
		model.delete(2);
	}

	private static void testfindByPk() throws Exception {
		UserModel model = new UserModel();

		UserBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testFindByLogin() throws Exception {
		UserModel model = new UserModel();

		UserBean bean = model.findByLogin("nitin@gmail.com");

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.print(bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.println("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.println("\t" + bean.getGender());
			System.out.println("\t" + bean.getCreatedBy());
			System.out.println("\t" + bean.getModifiedBy());
			System.out.println("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Login id not found");
		}

	}

	private static void testAuthenticate() throws Exception {
		UserModel model = new UserModel();

		UserBean bean = model.authenticate("nitin@gmail.com", "12345");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.print(bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.println("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.println("\t" + bean.getGender());
			System.out.println("\t" + bean.getCreatedBy());
			System.out.println("\t" + bean.getModifiedBy());
			System.out.println("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Login id and password is invalid");
		}

	}

	private static void testSearch() throws Exception {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

//		bean.setFirstName("Nitin");

		List list = model.search(bean, 4, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (UserBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}
}
