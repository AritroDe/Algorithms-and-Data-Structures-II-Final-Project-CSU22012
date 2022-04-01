import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class fileReader {
	
	public ArrayList<stops> stopsList;
	public HashMap<Integer, ArrayList<stops>> stopsHashMap;
	public ArrayList<stopTimes> stopTimesList;
	public ArrayList<transfers> transfersList;
	
	public fileReader() throws IOException {
		
		stopsList = new ArrayList<stops>();
		stopsHashMap = new HashMap<Integer, ArrayList<stops>>();
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
				
				
				
			}
			
		}
		
	}
	
	public void stopTimesReader(ArrayList<stopTimes> stopTimesList) {
		
		
		
	}

	public void transfersReader(ArrayList<transfers> transfersList) {
		
		
		
	}
	
}