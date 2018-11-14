package task3;

import javafx.util.Pair;

public class hw3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hw#3 Start! ");
		//String GRAPH_FILE = "/home/usaywook/FileRead/height.txt";
		String GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\height.txt"; 
		//String GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\testheight.txt"; 
		Graph graph = new Graph(GRAPH_FILE);
		System.out.println("==================================================================================================================================================================");
		System.out.println("								height_mat[][]										");
		System.out.println("==================================================================================================================================================================");		
		//graph.showHeightMat();
		System.out.println();
		System.out.println("==================================================================================================================================================================");
		System.out.println("								adj_list										");
		System.out.println("==================================================================================================================================================================");
		graph.showAdjList();
		System.out.println();
		
		Pair<Integer, Integer> sid = new Pair<Integer, Integer>(0,0);
		Pair<Integer, Integer> tid = new Pair<Integer, Integer>(19,19);
		//Pair<Integer, Integer> sid = new Pair<Integer, Integer>(0,0);
		//Pair<Integer, Integer> tid = new Pair<Integer, Integer>(1,2);
		// Problem 4.A 
		Tool tool = new Tool();
		System.out.print("Result: ");
		//System.out.println(tool.BFS(graph, graph.v.get(sid), graph.v.get(tid)));
		System.out.println(tool.ShortestPathList(graph, graph.v.get(sid), graph.v.get(tid)));
		
	}

}
