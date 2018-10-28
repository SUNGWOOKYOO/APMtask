package task2;

import javafx.util.Pair;

public class hw2 {
	
	public static void main(String args[]) {
		System.out.println("Hw#2 Start! ");
		//String GRAPH_FILE = "/home/usaywook/FileRead/height.txt";
		String GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw2_data\\height.txt"; 
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
		
		// Problem 4.B
		System.out.println("==================================================================================================================================================================");
		System.out.println("								Dijkstra Algorithm										");
		System.out.println("==================================================================================================================================================================");
		Dijkstra DijkAlgo = new Dijkstra();
		DijkAlgo.Dijkstra_run(graph, graph.v.get(new Pair<Integer, Integer>(0,0)) );
		DijkAlgo.showPathandWrite(graph, graph.v.get(new Pair<Integer, Integer>(0,0)) );
		
		// Problem 4.C
		System.out.println("==================================================================================================================================================================");
		System.out.println("								Prim Algorithm										");
		System.out.println("==================================================================================================================================================================");
		Graph graph2 = new Graph(GRAPH_FILE);
		Prim PrimAlgo = new Prim();
		PrimAlgo.Prim_run(graph2, graph.v.get(new Pair<Integer, Integer>(19,19)) );
		PrimAlgo.showWeightandWrite(graph2, graph.v.get(new Pair<Integer, Integer>(19,19)));
	}
	
}