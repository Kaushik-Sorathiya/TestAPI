package API_TestCases;

import java.util.Scanner;
import org.testng.TestNG;

public class Executor {

	public static void main(String[] args){

		@SuppressWarnings("resource")
		Scanner in1 = new Scanner(System.in);
		System.out.println("\n"
				+ "Please enter the BaseUrl below & press Enter : ");
		String st = in1.nextLine();

		if ((st.isEmpty()) || (st == null) || (st.equals("")) || (st.contains(" "))
				|| (!st.contains("https://"))) {
			System.out.println("\n" + "Invalid BaseUrl Entered");
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			System.out.println("\n" + "Please enter BaseUrl below again & press Enter : ");
			String s = in.nextLine();
			if ((s.isEmpty()) || (s == null) || (s.equals("")) || (s.contains(" "))
					|| (!s.toLowerCase().contains("https://"))) {
				System.out.println("\n" + "Invalid BaseUrl entered again");
				System.exit(0);
			}

		}
			TestNG testSuite = new TestNG();
			testSuite.setTestClasses(new Class[] { API_Tests.class });
			testSuite.run();

			System.out.println("\n" + "Test Execution completed");
			System.out.println("\n" + "Have a nice day!!" + "\n");
	}
}