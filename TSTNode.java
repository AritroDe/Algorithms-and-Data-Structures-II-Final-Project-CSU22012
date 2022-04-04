public class TSTNode {
	
    char data;
    boolean isEnd;
    TSTNode left;
    TSTNode middle;
    TSTNode right;
    
    public TSTNode(char data) {
    	
        this.data = data;
        isEnd = false;
        left = null;
        right = null;
        middle = null;
        
    }
    
}