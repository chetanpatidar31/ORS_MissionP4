package in.co.rays.proj4.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testfindByName();
		testSearch();
	}

	private static void testNextPk() {
		RoleModel model = new RoleModel();

		int i = model.nextPk();

		System.out.println("nextPk:  " + i);
	}

	private static void testAdd() throws Exception {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setName("Kios");
		bean.setDescription("Kiosk");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	private static void testUpdate() throws Exception {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();

		bean.setId(1l);
		bean.setName("Admin");
		bean.setDescription("admin has all access");
		bean.setModifiedBy("root");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testDelete() throws Exception {
		RoleModel model = new RoleModel();
		model.delete(5l);
	}

	private static void testFindByPk() throws Exception {

		RoleModel model = new RoleModel();

		RoleBean bean = model.findByPk(1l);

		if (bean != null) {
			System.out.print(bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {

			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testfindByName() throws Exception {

		RoleModel model = new RoleModel();

		RoleBean bean = model.findByName("Student");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			throw new RecordNotFoundException("name not found");
		}
	}

	private static void testSearch() throws Exception {

		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();

//		bean.setName("Student");

		List list = model.search(bean, 1, 10);
		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (RoleBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

}
