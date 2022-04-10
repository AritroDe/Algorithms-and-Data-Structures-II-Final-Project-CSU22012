import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner s = new Scanner(System.in);
		boolean quit = false;
		
		while (quit == false) {
			
			System.out.print("Which functionality would you like to use: \n \n"
					+ "1. Search for the shortest path between two stop ID's \n"
					+ "2. Search for stops using their names \n"
					+ "3. Search for trips by Arrival Time \n \n"
					+ "Please type out '1', '2' or '3' to choose or enter 'quit' to terminate the programme \n");
			
			String input = s.next();
			
			if(input == null) {
				
				System.out.print("=> ");
				
			}

			if(Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2 || Integer.parseInt(input) == 3) {
				
				int selection = Integer.parseInt(input);
				
				if(selection == 1) {
					
					Dijkstra.main(args);
					
				}
				
				else if(selection == 2) {
					
					TST.main(args);
					
				}
				
				else if(selection == 3) {
					
					searchByTime.main(args);
					
				}
				
			}
			
			else {
				
				if(input.equalsIgnoreCase("quit")) {
				
					quit = true;
					break;
					
				}
				
			}
			
		}
		
		s.close();
		
	}

}
