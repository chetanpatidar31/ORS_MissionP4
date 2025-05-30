package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.proj4.bean.TimetableBean;
import in.co.rays.proj4.model.TimetableModel;

public class TestTimetableModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindBySemester();
//		testSearch();
	}

	private static void testNextPk() {
		TimetableModel model = new TimetableModel();

		System.out.println("Next Pk: " + model.nextPk());

	}

	private static void testAdd() throws Exception {
		TimetableModel model = new TimetableModel();
		TimetableBean bean = new TimetableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		bean.setSemester("16th");
		bean.setDescription("exam timetable");
		bean.setExamDate(sdf.parse("2023/04/02"));
		bean.setExamTime("9 to 12");
		bean.setCourseId(11);
		bean.setSubjectId(1003);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	private static void testUpdate() throws Exception {
		TimetableModel model = new TimetableModel();
		TimetableBean bean = new TimetableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		bean.setId(1);
		bean.setSemester("5th");
		bean.setDescription("exam timetable");
		bean.setExamDate(sdf.parse("2023/04/02"));
		bean.setExamTime("9 to 12");
		bean.setCourseId(2);
		bean.setSubjectId(3);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	private static void testDelete() throws Exception {
		TimetableModel model = new TimetableModel();
		model.delete(2);

	}

	private static void testFindByPk() throws Exception {
		TimetableModel model = new TimetableModel();

		TimetableBean bean = model.findByPk(1);

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

	private static void testFindBySemester() throws Exception {
		TimetableModel model = new TimetableModel();

		TimetableBean bean = model.findBySemester("4th");

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}

	private static void testSearch() throws Exception {
		TimetableBean bean = new TimetableBean();
		TimetableModel model = new TimetableModel();

		List list = model.search(bean, 2, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (TimetableBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		}

	}
}
