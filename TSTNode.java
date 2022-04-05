public class TSTNode {
	
    char value;
    boolean finished;
    TSTNode l;
    TSTNode r;
    TSTNode mid;
    
    public TSTNode(char valueInput) {
    	
        this.value = valueInput;
        finished = false;
        l = null;
        r = null;
        mid = null;
        
    }
    
}