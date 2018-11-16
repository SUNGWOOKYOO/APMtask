package task3;

import javafx.util.Pair;
import task2.Dijkstra;

public class hw3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hw#3 Start! ");
		String GRAPH_FILE;
		Pair<Integer, Integer> sid;
		Pair<Integer, Integer> tid;
		if(true) {
			GRAPH_FILE = "/home/swyoo/FileRead/APM/hw3_data/height.txt";
			//GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\height.txt";
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(19,19);
		}
		else {
			GRAPH_FILE = "/home/swyoo/FileRead/APM/hw3_data/testheight.txt";
			//GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\testheight.txt"; 
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(2,2);
		} 
		ResidualGraph graph = new ResidualGraph(GRAPH_FILE);
		System.out.println("==================================================================================================================================================================");
		System.out.println("								height_mat[][]										");
		System.out.println("==================================================================================================================================================================");		
		//graph.showHeightMat();
		System.out.println();
		System.out.println("==================================================================================================================================================================");
		System.out.println("								adj_list										");
		System.out.println("==================================================================================================================================================================");
		//graph.showAdjList();
		System.out.println();
		
		// Problem 4.A 
		Tool tool = new Tool();
		System.out.println("EdmondsKarp Result: ");
		System.out.println(tool.EdmondsKarp(graph, graph.v.get(sid), graph.v.get(tid)));
		//graph.showWeight();
		
		
	}

}
