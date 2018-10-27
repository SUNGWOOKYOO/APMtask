package task2;

import javafx.util.Pair;

public class hw2 {
	
	public static void main(String args[]) {
		System.out.println("Hw#2 Start! ");
		String GRAPH_FILE = "/home/usaywook/FileRead/testheight.txt"; 
		Graph graph = new Graph(GRAPH_FILE);
	
		// Problem 4.A
		System.out.println("==================================================================================================================================================================");
		System.out.println("								adj_mat[][]										");
		System.out.println("==================================================================================================================================================================");		
		graph.showHeightMat();
		System.out.println();
		System.out.println("==================================================================================================================================================================");
		System.out.println("								adj_list										");
		System.out.println("==================================================================================================================================================================");
		graph.showAdjList();
		System.out.println();
		
		System.out.println("==================================================================================================================================================================");
		System.out.println("								Dijkstra Algorithm										");
		System.out.println("==================================================================================================================================================================");
		Dijkstra Dijk = new Dijkstra();
		
		Dijk.Dijkstra_run(graph, graph.v.get(new Pair<Integer, Integer>(0,0)) );
		
		System.out.println(" result: ");
		Dijk.showPath(graph, graph.v.get(new Pair<Integer, Integer>(0,0)) );
		
	}
	
}