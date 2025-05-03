package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByName();
		testSearch();
	}

	private static void testNextPk() {
		CourseModel model = new CourseModel();

		int i = model.nextPk();

		System.out.println("Next Pk : " + i);

	}

	private static void testAdd() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		
		bean.setName("cs");
		bean.setDuration("2024-2025");
		bean.setDescription("Maths");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.add(bean);
	}

	private static void testUpdate() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		
		bean.setId(4);
		bean.setName("Computer");
		bean.setDuration("2024-2025");
		bean.setDescription("Application");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
	}

	private static void testDelete() throws Exception {
		CourseModel model = new CourseModel();
		model.delete(3);
	}

	private static void testFindByPk() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Id not found");
		}

	}

	private static void testFindByName() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = model.findByName("physics");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Course not found");
		}

	}

	private static void testSearch() throws Exception {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();

//		bean.setName("Computer Science");

		List list = model.search(bean, 1, 10);
		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CourseBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}
}
