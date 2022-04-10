import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TST {
	
    private static TSTNode node;
    private static ArrayList<String> stringArrayList;

    public TST() {
    	
        node = null;
        
    }
    
    public TST(ArrayList<stops> stopsList) {
    	
    	for (int i = 0; i < stopsList.size(); i++) {
    		
    		String input = stopsList.get(i).stop_name;
    		char[] inputArray = input.toCharArray();
    		
    		if(inputArray[0] == 'W' && inputArray[1] == 'B'
    				|| inputArray[0] == 'N' && inputArray[1] == 'B' 
    				|| inputArray[0] == 'S' && inputArray[1] == 'B'
    				|| inputArray[0] == 'E' && inputArray[1] == 'B') {
    			
    			inputArray[0] = ' ';
    			inputArray[1] = ' ';
    			
    		}
    		
    		input = String.copyValueOf(inputArray);
    		input = input.trim();
			TST.insert(stopsList.get(i).stop_name);
				
		}
    	
    }

    public boolean isNull() {
    	
        return node == null;
        
    }
    
    public void makeNull() {
    	
        node = null;
        
    }
 
    public static void insert(String word) {
    	
    	char[] w = word.toCharArray();
        node = insertRecursive(node, w, 0);
        
    }

    public static TSTNode insertRecursive(TSTNode node, char[] w, int i) {
    	
        if (node == null) {
        	
            node = new TSTNode(w[i]);
            
        }

        if (w[i] < node.value) {
        	
            node.l = insertRecursive(node.l, w, i);
            
        }
        
        else if (w[i] > node.value) {
        	
            node.r = insertRecursive(node.r, w, i);
            
        }

        else { 
	        
        	if (i + 1 < w.length) {
	            	
	            node.mid = insertRecursive(node.mid, w, i + 1);
	                
	        }
	            
	        else {
	            	
	            node.finished = true;
	                
	        }
        
        }
        	
        return node;
        
    }
    
    public static void delete(String word) {
    	
    	char[] w = word.toCharArray();
        deleteRecursive(node, w, 0);
        
    }

    private static void deleteRecursive(TSTNode node, char[] w, int i) {
    	
        if (node == null) {
        	
            return;
            
        }

        if (w[i] < node.value) {
        	
            deleteRecursive(node.l, w, i);
            
        }
        
        if (w[i] > node.value) {
        	
            deleteRecursive(node.r, w, i);
            
        }

        if (node.finished && i == w.length - 1) {

        	node.finished = false;
            
        }

        else if (i + 1 < w.length) {

        	deleteRecursive(node.mid, w, i + 1);

        }
        
    }
    
    public static String[] search(String word) {
    	
	    String[] nullArray = new String[0];
	    
	    try {
	    	
	        if (word == null) {
	        	
	            return nullArray;
	            
	        }
	        
	    } 
	    
	    catch (Exception e) {
	    	
	        System.out.println("No name detected");
	        
	    }
	    
	    try {
	    	
	        if (word.charAt(0) == ' ') {
	        	
	            return nullArray;
	            
	        }
	
	    }
	    
	    catch (Exception e) {
	    	
	        System.out.println("Bus stop name cannot start with a space i.e. ' '");
	
	    }
	    
	    char[] w = word.toCharArray();
	    
	    TSTNode prevNode = searchPrevNode(node, w, 0);
	    
	    StringBuilder SB = new StringBuilder();
	    
	    searchWithInput(prevNode, "", SB, word);
	    
	    if(SB.length() < 1) {
	    	
	    	System.out.println("Could not find matching name");
	    	return nullArray;
	    	
	    }
	    
		return null;
	    
    }

	private static TSTNode searchPrevNode(TSTNode node, char[] w, int i) {
		
		if(node != null) {
		
			if (w[i] < node.value) {
				
	            return searchPrevNode(node.l, w, i);
	            
			}
			
	        else if (w[i] > node.value) {
	        	
	            return searchPrevNode(node.r, w, i);
	            
	        }
	        
			if (i == w.length - 1) {
	                
				return node;
	            
			}
	            
			else {
	                
				return searchPrevNode(node.mid, w, i + 1);
	        
			}
			
		}
		
		return null;
		
    }
	
    private static void searchWithInput(TSTNode node, String s, StringBuilder SB, String input) {
        
    	if(node == null) {
    		
    		return;
    		
    	}
    	
    	else {
    		
            searchWithInput(node.l, s, SB, input);
            s += node.value;
            
            if (node.finished == true) {
            	
                if (input.length() == 1) {
                	
                    if (input.equals(s.substring(0, 1))) {
                    	
                        SB.append(input + s.substring(1) + "\n");
                        
                    }
                    
                } 
                
                else {
                	
                    SB.append(input + s.substring(1) + "\n");
                    
                }
            
                searchWithInput(node.mid, s, SB, input);
                s = s.substring(0, s.length() - 1);
                searchWithInput(node.r, s, SB, input);
                
            }
            
    	}

    }
    
    
    public String printTree() {
    	
		stringArrayList = new ArrayList<String>();
		traverse(node, "");
		return "\nTernary Search Tree : "+ stringArrayList;
		
	}
    
	private void traverse(TSTNode node, String s) {
		
		if (node != null) {
			
			traverse(node.l, s);
			s += node.value;
			
			if (node.finished) {
				
				stringArrayList.add(s);
				
			}

			traverse(node.mid, s);
			s = s.substring(0, s.length() - 1);
			traverse(node.r, s);
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {

		fileReader files = new fileReader();
    	ArrayList<stops> stopsList = files.stopsList;
    	HashMap<Integer, stops> stopsHashMap = files.stopsHashMap;
    	ArrayList<stopTimes> stopTimesList = files.stopTimesList;
    	ArrayList<transfers> transfersList = files.transfersList;

    	TST tst = new TST(stopsList);
    	
    	Scanner s = new Scanner(System.in);
		boolean quit = false;
		
		while (quit == false) {
			System.out.println("Enter stop name to search, or 'quit' leave: ");
			String input = s.nextLine().toUpperCase();
			
			if(input.equals("QUIT")) {
				
				quit = true;
				break;
				
			}
			
			if (tst.search(input) != null) {
				
				for (int i = 0; i < stopsList.size(); i++) {
					
					String input1 = stopsList.get(i).stop_name;
		    		char[] inputArray = input1.toCharArray();
		    		
		    		if(inputArray[0] == 'W' && inputArray[1] == 'B'
		    				|| inputArray[0] == 'N' && inputArray[1] == 'B' 
		    				|| inputArray[0] == 'S' && inputArray[1] == 'B'
		    				|| inputArray[0] == 'E' && inputArray[1] == 'B') {
		    			
		    			inputArray[0] = ' ';
		    			inputArray[1] = ' ';
		    			
		    		}
		    		
		    		input1 = String.copyValueOf(inputArray);
		    		input1 = input1.trim();
		    		input1 = input1.toUpperCase();
		    		
		    		if(input.length() <= input1.length()) {
		    			
						if (input1.substring(0,input.length()).equals(input)) {
							
							System.out.println("The stop ID is: " + stopsList.get(i).stop_id);
							System.out.println("The stop code is: " + stopsList.get(i).stop_code);
							System.out.println("The stop name is: " + stopsList.get(i).stop_name);
							System.out.println("The stop description is: " + stopsList.get(i).stop_desc);
							System.out.println("The stop latitude is: " + stopsList.get(i).stop_lat);
							System.out.println("The stop longitude is: " + stopsList.get(i).stop_lon);
							System.out.println("The stop zone ID is: " + stopsList.get(i).zone_id);
							System.out.println("The stop url is: " + stopsList.get(i).stop_url);
							System.out.println("The stop location type is: " + stopsList.get(i).location_type);
							System.out.println("=================================================");
							
						}
					
		    		}
		    		
				}
				
			}
			
			else if (tst.search(input) == null) {
				 
				System.out.println("Please enter a valid bus stop name.");
				quit = false;
				
			}
		}
	}
}