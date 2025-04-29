package in.co.rays.proj4.test;

import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

	public static void main(String[] args) {
		testNextPk();
	}

	private static void testNextPk() {
		FacultyModel model = new FacultyModel();
		System.out.println("Next Pk: " + model.nextPk());
	}

}
