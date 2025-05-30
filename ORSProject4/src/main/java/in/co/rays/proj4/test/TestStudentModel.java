package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.RecordNotFoundException;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.model.SubjectModel;

public class TestStudentModel {

	public static void main(String[] args) throws Exception {
		testNextPk();
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByEmail();
//		testSearch();
		testList();
	}


	private static void testList() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();

		List list = model.list();
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			bean =(StudentBean) it.next();
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}

	}


	private static void testNextPk() {
		StudentModel model = new StudentModel();

		System.out.println("Next Pk: " + model.nextPk());

	}
	
	private static void testAdd() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Aastik");
		bean.setLastName("Sahu");
		bean.setDob(sdf.parse("1999-08-09"));
		bean.setGender("Male");
		bean.setMobileNo("9872891434");
		bean.setEmail("aastik@gmail.com");
		bean.setCollegeId(3);
		bean.setCreatedBy("root");
		bean.setModifiedBy("Chetan");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
		
	}

	private static void testUpdate() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		bean.setId(1);
		bean.setFirstName("Amit");
		bean.setLastName("Kirar");
		bean.setDob(sdf.parse("2002-02-15"));
		bean.setGender("Male");
		bean.setMobileNo("8120891470");
		bean.setEmail("amit@gmail.com");
		bean.setCollegeId(4);
		bean.setCreatedBy("root");
		bean.setModifiedBy("Chetan");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
		
	}

	private static void testDelete() throws Exception {
		StudentModel model = new StudentModel();
		model.delete(2);
		
	}

	private static void testFindByPk() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = model.findByPk(1);
		
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Student not found");
		}
		
	}

	private static void testFindByEmail() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = model.findByEmail("aastik@gmail.com");
		
		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());

		} else {
			throw new RecordNotFoundException("Student email not found");
		}
		
	}

	private static void testSearch() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();

		List list = model.search(bean, 1, 10);
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			bean =(StudentBean) it.next();
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getEmail());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
		
	}
}
