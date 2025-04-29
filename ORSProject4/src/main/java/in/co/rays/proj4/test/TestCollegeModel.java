package in.co.rays.proj4.test;

import java.util.Date;
import java.sql.Timestamp;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {

	public static void main(String[] args) {
		testNextPk();
//		testAdd();
//		testUpdate();
		testDelete();
	}

	public static void testNextPk() {
		CollegeModel model = new CollegeModel();

		int i = model.nextPk();

		System.out.println("Next Pk : " + i);
	}

	public static void testAdd() {
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
}
