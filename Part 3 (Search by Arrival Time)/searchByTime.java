import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class searchByTime {

	public static void main(String[] args) throws Exception {
		
    	fileReader files = new fileReader();
    	ArrayList<stops> stopsList = files.stopsList;
    	HashMap<Integer, stops> stopsHashMap = files.stopsHashMap;
    	ArrayList<stopTimes> stopTimesList = files.stopTimesList;
    	ArrayList<transfers> transfersList = files.transfersList;
    	
    	Scanner s = new Scanner(System.in);
    	String input;
    	boolean quit = false;
    	
    	while(quit == false) {
	    	
			System.out.println("Please enter your arrival time in the form - hh:mm:ss, or enter 'quit' to leave => ");
			input = s.nextLine();
			
			if(input.equals("quit") || quit == true) {
				
				quit = true;
				break;
				
			}
			
			while(validTimeCheck(input) == false) {
				
				System.out.println("Please enter a valid time, or enter 'quit' to leave");
				input = s.nextLine();
				
				if(input.equals("quit")) {
					
					quit = true;
					break;
					
				}
				
			}
			
			for(int i = 0; i < stopTimesList.size(); i++) {
				
				String at = stopTimesList.get(i).arrival_time.replaceAll(" ",  "");
				if (input.equals(at)) {
					
					System.out.println("The trip ID is: " + stopTimesList.get(i).trip_id);
					System.out.println("The departure time is: " + stopTimesList.get(i).departure_time);
					System.out.println("The stop ID is: " + stopTimesList.get(i).stop_id);
					System.out.println("The stop sequence is: " + stopTimesList.get(i).stop_sequence);
					System.out.println("The stop headsign is: " + stopTimesList.get(i).stop_headsign);
					System.out.println("The pickup type is: " + stopTimesList.get(i).pickup_type);
					System.out.println("The drop off type is: " + stopTimesList.get(i).drop_off_type);
					System.out.println("=================================================");
					
				}
				
			}
			
    	}
			
	}
	
	public static boolean validTimeCheck(String timeInput) {
		
		timeInput = timeInput.replaceAll("//s", ""); // removing all spaces from the string
		
		String[] hoursMinutesSeconds = timeInput.split(":");
		
		int hours;
		int minutes;
		int seconds;
		
		try {
			
			hours = Integer.parseInt(hoursMinutesSeconds[0]);
			minutes = Integer.parseInt(hoursMinutesSeconds[1]);
			seconds = Integer.parseInt(hoursMinutesSeconds[2]);
			
		}
		
		catch (Exception e) {
			
			return false;
			
		}
		
		if ( (00 <= hours) && (hours <= 23) && (00 <= minutes) && (minutes <= 59) && (00 <= seconds) && (seconds <= 59)) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}

}
