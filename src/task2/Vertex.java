package task2;

class Point extends Vertex{
	private int x;
	private int y;
	public Point(int _x, int _y) {
		x = _x;
		y = _y;
	}
}

public class Vertex {
	private Point id;
	private double weight;
	public Vertex next;
}
