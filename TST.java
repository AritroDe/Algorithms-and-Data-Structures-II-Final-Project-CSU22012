import java.util.ArrayList;

public class TST {
    private TSTNode node;
    private ArrayList<String> stringArrayList;

    public TST() {
    	
        node = null;
        
    }

    public boolean isNull() {
    	
        return node == null;
        
    }

    public void makeNull() {
    	
        node = null;
        
    }
 
    public void insert(String word) {
    	
    	char[] w = word.toCharArray();
        node = insertRecursive(node, w, 0);
        
    }

    public TSTNode insertRecursive(TSTNode node, char[] w, int i) {
    	
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
    
    public void delete(String word) {
    	
    	char[] w = word.toCharArray();
        deleteRecursive(node, w, 0);
        
    }

    private void deleteRecursive(TSTNode node, char[] w, int i) {
    	
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
    
}