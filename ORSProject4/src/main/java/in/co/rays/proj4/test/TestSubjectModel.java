package in.co.rays.proj4.test;

import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

	public static void main(String[] args) {
		testNextPk();
	}

	private static void testNextPk() {
		SubjectModel model = new SubjectModel();

		System.out.println("Next Pk: " + model.nextPk());

	}
}
