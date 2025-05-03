package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

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
		SubjectModel model = new SubjectModel();

		System.out.println("Next Pk: " + model.nextPk());

	}

	private static void testAdd() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		bean.setName("DS");
		bean.setCourseId(3l);
		bean.setDescription("Maths");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

	private static void testUpdate() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		bean.setId(3);
		bean.setName("Python");
		bean.setCourseId(4l);
		bean.setDescription("database");
		bean.setCreatedBy("root");
		bean.setModifiedBy("chetan");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	private static void testDelete() throws Exception {
		SubjectModel model = new SubjectModel();
		model.delete(2);
	}

	private static void testFindByName() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByName("cs");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Course not found");
		}

	}

	private static void testFindByPk() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByPk(3);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("id not found");
		}

	}

	private static void testSearch() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();

		List list = model.search(bean, 1, 10);
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			bean =(SubjectBean) it.next();
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

}
