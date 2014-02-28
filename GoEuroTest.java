import java.util.Scanner;

public class GoEuroTest {

	public static void main(String[] args)
	{
		Scanner user_input = new Scanner( System.in );
		String API;
		System.out.println("Please, insert API: ");
		API = user_input.next( );
        	MyJSONObj newOBj = new MyJSONObj(API+args[0]);
        	newOBj.printGeoOBj();
        	newOBj.generateCsvFile("GoEuroTest.csv");

	}

}
