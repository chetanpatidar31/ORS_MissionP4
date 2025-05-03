package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.model.UserModel;

public class TestFacultyModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
//		testAdd();
//		testDelete();
		testUpdate();
//		testFindByPk();
//		testFindByEmail();
//		testSearch();
	}

	private static void testNextPk() {
		FacultyModel model = new FacultyModel();
		System.out.println("Next Pk: " + model.nextPk());
	}

	private static void testAdd() throws Exception {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		bean.setFirstName("Rohan");
		bean.setLastName("karma");
		bean.setGender("male");
		bean.setEmail("rohan2@gmail.com");
		bean.setMobileNo("9087654329");
		bean.setCollegeId(3);
//		bean.setCollegeName("rpl");
		bean.setCourseId(2);
//		bean.setCourseName("m.com");
		bean.setDob(sdf.parse("22/09/1999"));
		bean.setSubjectId(1);
//		bean.setSubjectName("maths");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		FacultyModel model = new FacultyModel();
		model.add(bean);
	}

	private static void testDelete() throws Exception {
		FacultyModel model = new FacultyModel();
		model.delete(2l);

	}

	private static void testUpdate() throws Exception {

		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		bean.setId(1l);
		bean.setFirstName("Rohan");
		bean.setLastName("karma");
		bean.setGender("male");
		bean.setEmail("rohan@gmail.com");
		bean.setMobileNo("9087654329");
		bean.setCollegeId(2);
		bean.setCourseId(1);
		bean.setDob(sdf.parse("22/09/1999"));
		bean.setSubjectId(3);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		FacultyModel model = new FacultyModel();
		model.update(bean);

	}

	private static void testFindByPk() throws Exception {
		FacultyModel model = new FacultyModel();

		FacultyBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());

			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());

			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testFindByEmail() throws Exception {
		FacultyModel model = new FacultyModel();

		FacultyBean bean = model.findByEmail("rohan@gmail.com");

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Login id not found");
		}
	}

	private static void testSearch() throws Exception {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();

		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (FacultyBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}

}
