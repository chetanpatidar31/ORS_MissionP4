package in.co.rays.proj4.test;

import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {

	public static void main(String[] args) {
		testNextPk();
	}

	private static void testNextPk() {
		StudentModel model = new StudentModel();

		System.out.println("Next Pk: " + model.nextPk());

	}
}
