public class transfers {
	
	int from_stop_id;
	int to_stop_id;
	int transfer_type;
	int min_transfer_time;
	double cost;
	
	public transfers(int from_stop_id, int to_stop_id, int transfer_type, int min_transfer_type, double cost2) {
		
		this.from_stop_id = from_stop_id;
		this.to_stop_id = to_stop_id;
		this.transfer_type = transfer_type;
		this.min_transfer_time = min_transfer_type;
		this.cost = cost2;
		
	}
	
}