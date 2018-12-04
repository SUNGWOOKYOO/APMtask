package task4;

import java.text.DecimalFormat;

public class Tuple implements Comparable<Tuple> {
	public int NodeID;
	public double PageRank;

	public Tuple(int _ID, double _PR) {
		NodeID = _ID;
		PageRank = _PR;
	}

	// refer:
	// https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html
	@Override
	public int compareTo(Tuple other) {
		// TODO Auto-generated method stub
		if (this.PageRank < other.PageRank) return 1;
		else if(this.PageRank > other.PageRank) return -1;
		else return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder InfoLine = new StringBuilder();
		InfoLine.append(NodeID);
		InfoLine.append("/");
		DecimalFormat formatter = new DecimalFormat("#0.000");
		InfoLine.append(formatter.format(PageRank).toString());
		return InfoLine.toString();
	}
}
