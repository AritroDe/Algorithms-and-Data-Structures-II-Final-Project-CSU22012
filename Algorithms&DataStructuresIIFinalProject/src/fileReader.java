import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class fileReader {
	
	public ArrayList<stops> stopsList;
	public HashMap<Integer, stops> stopsHashMap;
	
	public ArrayList<stopTimes> stopTimesList;
	
	public ArrayList<transfers> transfersList;
	
	
	public fileReader() throws Exception {
		
		stopsList = new ArrayList<stops>();
		stopsHashMap = new HashMap<Integer, stops>();
		stopsReader();
		
		stopTimesList = new ArrayList<stopTimes>();
		stopTimesReader();
		
		transfersList = new ArrayList<transfers>();
		transfersReader();
		
	}
	
	private void stopsReader() throws IOException {
		
		BufferedReader BR = new BufferedReader(new FileReader("stops.txt"));
		String s;
		
		for(int i = 0; (s = BR.readLine()) != null; i++) {
			
			String[] line = s.split(",");
			
			if(i != 0) {
				
				
				
				int stop_id;
				
				if(line[0].equals("") || line[0].equals(" ")) {
					
					stop_id = -1;
					
				}
				
				else {
					
					stop_id = Integer.parseInt(line[0]);
					
				}
				
				
				
				int stop_code;
				
				if(line[1].equals("") || line[1].equals(" ")) {
					
					stop_code = -1;
					
				}
				
				else {
					
					stop_code = Integer.parseInt(line[1]);
					
				}
				
				
				
				String stop_name;
				
				if(line[2].equals("") || line[2].equals(" ")) {
					
					stop_name = "";
					
				}
				
				else {
					
					stop_name = line[2];
					
				}
				
				
				
				String stop_desc;
				
				if(line[3].equals("") || line[3].equals(" ")) {
					
					stop_desc = "";
					
				}
				
				else {
					
					stop_desc = line[3];
					
				}
				
				
				
				double stop_lat;
				
				if(line[4].equals("") || line[4].equals(" ")) {
					
					stop_lat = -1;
					
				}
				
				else {
					
					stop_lat = Double.parseDouble(line[4]);
					
				}
				
				
				
				double stop_lon;
				
				if(line[5].equals("") || line[5].equals(" ")) {
					
					stop_lon = -1;
					
				}
				
				else {
					
					stop_lon = Double.parseDouble(line[5]);
					
				}
				
				
				
				String zone_id;
				
				if(line[6].equals("") || line[6].equals(" ")) {
					
					zone_id = "";
					
				}
				
				else {
					
					zone_id = line[6];
					
				}
				
				
				
				String stop_url;
				
				if(line[7].equals("") || line[7].equals(" ")) {
					
					stop_url = "";
					
				}
				
				else {
					
					stop_url = line[7];
					
				}
				
				
				
				int location_type;
				
				if(line[8].equals("") || line[8].equals(" ")) {
					
					location_type = -1;
					
				}
				
				else {
					
					location_type = Integer.parseInt(line[8]);
					
				}
				
				
				
				String parent_station;
				
				if(line[9].equals("") || line[9].equals(" ")) {
					
					parent_station = "";
					
				}
				
				else {
					
					parent_station = line[9];
					
				}
				
				
				
				stops stop = new stops(stop_id, stop_code, stop_name, stop_desc, stop_lat, stop_lon, zone_id, stop_url, location_type, parent_station);
				
				stopsList.add(stop);
				stopsHashMap.put(stop_id, stop);
				
			}
			
		}
		
		BR.close();
		
	}
	
	public void stopTimesReader() throws IOException {
		
		BufferedReader BR = new BufferedReader(new FileReader("stop_times.txt"));
		String s;
		
		for(int i = 0; (s = BR.readLine()) != null; i++) {
			
			String[] line = s.split(",");
			
			if(i != 0) {
				
				
				
				int trip_id;
				
				if(line[0].equals("") || line[0].equals(" ")) {
					
					trip_id = -1;
					
				}
				
				else {
					
					trip_id = Integer.parseInt(line[0]);
					
				}
				
				
				
				String arrival_time;
				
				if(line[1].equals("") || line[1].equals(" ") || validTimeCheck(line[1]) == false) {
					
					arrival_time = "";
					
				}
				
				else {
					
					arrival_time = line[1];
					
				}
				
				
				
				String departure_time;
				
				if(line[2].equals("") || line[2].equals(" ") || validTimeCheck(line[2]) == false) {
					
					departure_time = "";
					
				}
				
				else {
					
					departure_time = line[2];
					
				}
				
				
				
				int stop_id;
				
				if(line[3].equals("") || line[3].equals(" ")) {
					
					stop_id = -1;
					
				}
				
				else {
					
					stop_id = Integer.parseInt(line[3]);
					
				}
				
				
				
				int stop_sequence;
				
				if(line[4].equals("") || line[4].equals(" ")) {
					
					stop_sequence = -1;
					
				}
				
				else {
					
					stop_sequence = Integer.parseInt(line[4]);
					
				}
				
				
				
				int stop_headsign;
				
				if(line[5].equals("") || line[5].equals(" ")) {
					
					stop_headsign = -1;
					
				}
				
				else {
					
					stop_headsign = Integer.parseInt(line[5]);
					
				}
				
				
				
				int pickup_type;
				
				if(line[6].equals("") || line[6].equals(" ")) {
					
					pickup_type = -1;
					
				}
				
				else {
					
					pickup_type = Integer.parseInt(line[6]);
					
				}
				
				
				
				int drop_off_type;
				
				if(line[7].equals("") || line[7].equals(" ")) {
					
					drop_off_type = -1;
					
				}
				
				else {
					
					drop_off_type = Integer.parseInt(line[7]);
					
				}
				
				
				
				float shape_dist_travelled;
				
				if(line[8].equals("") || line[8].equals(" ")) {
					
					shape_dist_travelled = -1;
					
				}
				
				else {
					
					shape_dist_travelled = Float.parseFloat(line[8]);
					
				}
				
				
				
				stopTimes stopTimes = new stopTimes(trip_id, arrival_time, departure_time, stop_id, stop_sequence, stop_headsign, pickup_type, drop_off_type, shape_dist_travelled);
				
				stopTimesList.add(stopTimes);
				
			}
			
		}
		
		BR.close();
		
	}
	
	public boolean validTimeCheck(String timeInput) {
		
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

	public void transfersReader() throws Exception {
		
		BufferedReader BR = new BufferedReader(new FileReader("transfers.txt"));
		String s;
		
		for(int i = 0; (s = BR.readLine()) != null; i++) {
			
			String[] line = s.split(",");
			
			if(i != 0) {
				
				
				
				int from_stop_id;
				
				if(line[0].equals("") || line[0].equals(" ")) {
					
					from_stop_id = -1;
					
				}
				
				else {
					
					from_stop_id = Integer.parseInt(line[0]);
					
				}
				
				
				
				int to_stop_id;
				
				if(line[1].equals("") || line[1].equals(" ")) {
					
					to_stop_id = -1;
					
				}
				
				else {
					
					to_stop_id = Integer.parseInt(line[1]);
					
				}
				
				
				
				int transfer_type;
				
				if(line[2].equals("") || line[2].equals(" ")) {
					
					transfer_type = -1;
					
				}
				
				else {
					
					transfer_type = Integer.parseInt(line[2]);
					
				}
				
				
				
				int min_transfer_type = 0;
				double cost = 0;
				
				if(transfer_type == 0) {
					
					cost = 2;
					
				}
				
				else if(transfer_type == 2) {
					
					min_transfer_type = (int) Double.parseDouble(line[3]);
					cost = min_transfer_type / 100;
					
				}
				
				else if(transfer_type != 2 && transfer_type != 0) {
					
					throw new Exception("transfer type not valid at line " + i + "\n" + line);
					
				}
				
				
				
				transfers transfer = new transfers(from_stop_id, to_stop_id, transfer_type, min_transfer_type, cost);
				
				transfersList.add(transfer);
				
			}
			
		}
		
		BR.close();
		
	}
	
}