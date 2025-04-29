package in.co.rays.proj4.test;

import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {
	
	public static void main(String[] args) {
		testNextPk();
	}

	private static void testNextPk() {
		MarksheetModel model = new MarksheetModel();
		
		System.out.println("Next Pk: "+model.nextPk());
		
	}
}
