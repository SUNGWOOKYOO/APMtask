package task2;

public class hw2 {
	public static void main(String args[]) {
		System.out.println("Hw#2 Start! ");
		String GRAPH_FILE = "/home/usaywook/FileRead/height.txt"; 
		Graph graph = new Graph(GRAPH_FILE);
		
		// Problem 4.A
		graph.showAdjMat();
		System.out.println();
		
		
	}
}