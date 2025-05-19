package in.co.rays.proj4.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
//		testSearch();
	}

	public static void testNextPk() {
		CollegeModel model = new CollegeModel();

		int i = model.nextPk();

		System.out.println("Next Pk : " + i);
	}

	public static void testAdd() throws Exception {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();

		bean.setName("MIT");
		bean.setAddress("Trichy");
		bean.setState("TamilNadu");
		bean.setCity("Tiruchirappali");
		bean.setPhoneNo("777898989");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	public static void testUpdate() {
		try {
			CollegeBean bean = new CollegeBean();

			bean.setName("MU University");
			bean.setAddress("Mandsaur");
			bean.setState("TamilNadu");
			bean.setCity("Tiruchirappali");
			bean.setPhoneNo("777898989");
			bean.setModifiedBy("Manager");
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			bean.setId(4l);

			CollegeModel model = new CollegeModel();
			model.update(bean);

			System.out.println("Record Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {

		CollegeModel model = new CollegeModel();
		model.delete(3);

		System.out.println("record delete");
	}

	private static void testFindByPk() throws Exception {
		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByPk(2);

		if (bean != null) {

			System.out.print(bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testFindByName() throws Exception {
		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByName("NIT");

		if (bean != null) {

			System.out.print(bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testSearch() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();

		List list = model.search(bean, 1, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CollegeBean)it.next();

			System.out.print(bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}
