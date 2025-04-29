package in.co.rays.proj4.test;

import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {
	
	public static void main(String[] args) {
		testNextPk();
	}

	private static void testNextPk() {
		CourseModel model = new CourseModel();

		int i = model.nextPk();

		System.out.println("Next Pk : " + i);

	}
}
