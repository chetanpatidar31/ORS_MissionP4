package in.co.rays.proj4.test;

import in.co.rays.proj4.model.TimetableModel;

public class TestTimetableModel {

	public static void main(String[] args) {
		testNextPk();
	}

	private static void testNextPk() {
		TimetableModel model = new TimetableModel();

		System.out.println("Next Pk: " + model.nextPk());

	}
}
