import java.util.ArrayList;

public class TST {
	
    private static TSTNode node;
    private static ArrayList<String> stringArrayList;

    public TST() {
    	
        node = null;
        
    }
    
    public TST(ArrayList<stops> stopsList) {
    	
    	for (int i = 0; i < stopsList.size(); i++) {
    		
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
        
        if (w[i] > node.value) {
        	
            node.r = insertRecursive(node.r, w, i);
            
        }

        if (i + 1 < w.length) {
            	
            node.mid = insertRecursive(node.mid, w, i + 1);
                
        }
            
        else {
            	
            node.finished = true;
                
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
	    	
	    	System.out.print("Could not find matching name");
	    	return nullArray;
	    	
	    }
	    
		return null;
	    
    }

	private static TSTNode searchPrevNode(TSTNode node, char[] w, int i) {
		
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
    
}