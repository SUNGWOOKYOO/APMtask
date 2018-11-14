package task3;
import javafx.util.Pair;

public class Vertex {
	private Pair<Integer, Integer> id;
	private double height;
	public Vertex pi;
	public double d;
	public int name;
	public boolean visited;
	
	public Vertex(int _x, int _y) {
		id = new Pair<Integer, Integer>(_x,_y);
		name = 20*id.getKey() + id.getValue();
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
	public Pair<Integer, Integer> getId(){
		return id;
	}
	public int getname() {
		return name;
	}
	public int IdtoName(Pair<Integer, Integer> id) {
		return 20*id.getKey() + id.getValue();
	}
	public Pair<Integer, Integer> NametoId(int name){
		int key = name / 20;
		int value = name % 20;
		return new Pair<Integer, Integer>(key, value);
	}
	public String toString() {
		StringBuilder s = new StringBuilder("("+id.getKey()+","+id.getValue()+") ");
		return s.toString();
	}
	
}
