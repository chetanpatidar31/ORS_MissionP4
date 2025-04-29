package in.co.rays.proj4.test;

import java.util.Date;
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

		RoleBean bean = model.findByPk(5l);

		if (bean != null) {
			System.out.print(bean.getName());
			System.out.println("\t" + bean.getDescription());
		} else {
	
			throw new RecordNotFoundException("Id not found");
		}

	}
	
	private static void testfindByName() throws Exception {
		
		RoleModel model = new RoleModel();
		
		RoleBean bean = model.findByName("Student");
		
		if (bean != null) {
			System.out.print(bean.getName());
			System.out.println("\t"+bean.getDescription());
		}else {
			throw new RecordNotFoundException("name not found");
		}
	}

}
