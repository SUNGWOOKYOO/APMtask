package task2;
import javafx.util.Pair;

public class Vertex {
	private Pair<Integer, Integer> id;
	private double height;
	public Vertex prev;
	public Vertex(int _x, int _y) {
		id = new Pair<Integer, Integer>(_x,_y);
	}
	public void setheight(double _height) {
		height = _height;
	}
	public double getheight() {
		return height;
	}
	public void printId() {
		System.out.print("("+id.getKey()+","+id.getValue()+") ");
	}
	public  Pair<Integer, Integer> getId(){
		return id;
	}
	
}
