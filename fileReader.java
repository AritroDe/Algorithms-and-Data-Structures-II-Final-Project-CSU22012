import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class fileReader {
	
	public ArrayList<stops> stopsList;
	public HashMap<Integer, stops> stopsHashMap;
	public ArrayList<stopTimes> stopTimesList;
	public ArrayList<transfers> transfersList;
	
	public fileReader() throws IOException {
		
		stopsList = new ArrayList<stops>();
		stopsHashMap = new HashMap<Integer, stops>();
		stopsReader(stopsList);
		
		stopTimesList = new ArrayList<stopTimes>();
		stopTimesReader(stopTimesList);
		
		transfersList = new ArrayList<transfers>();
		transfersReader(transfersList);
		
	}
	
	private void stopsReader(ArrayList<stops> stopsList) throws IOException {
		
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
	
	public void stopTimesReader(ArrayList<stopTimes> stopTimesList) {
		
		
		
	}

	public void transfersReader(ArrayList<transfers> transfersList) {
		
		
		
	}
	
}