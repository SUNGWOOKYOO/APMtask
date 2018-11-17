package task3;

import javafx.util.Pair;

public class hw3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hw#3 Start! ");
		String GRAPH_FILE;
		Pair<Integer, Integer> sid;
		Pair<Integer, Integer> tid;
		if(true) {
			//GRAPH_FILE = "/home/swyoo/FileRead/APM/hw3_data/height.txt";
			GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\height.txt";
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(19,19);
		}
		else {
			//GRAPH_FILE = "/home/swyoo/FileRead/APM/hw3_data/testheight.txt";
			GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\testheight.txt"; 
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(2,2);
		} 
		ResidualGraph graph = new ResidualGraph(GRAPH_FILE);
		System.out.println("==================================================================================================================================================================");
		System.out.println("								show weight/ flow										");
		System.out.println("==================================================================================================================================================================");		
		//graph.showWeight();
		System.out.println();
		System.out.println("==================================================================================================================================================================");
		System.out.println("								show residualCapacity										");
		System.out.println("==================================================================================================================================================================");
		//graph.showResidualCapacity();
		System.out.println();
		
		// Problem 4.A  and Problem 4.B
		Tool tool = new Tool();
		//System.out.println("EdmondsKarp Result: ");
		//System.out.println(tool.EdmondsKarp(graph, graph.v.get(sid), graph.v.get(tid)));
		//graph.showWeight();
		//graph.showSaturatedEdge();
		
		// Problem 4.C
		System.out.println(tool.FordFurkerson_order1(graph, graph.v.get(sid), graph.v.get(tid)));
		//System.out.println(tool.FordFurkerson_order2(graph, graph.v.get(sid), graph.v.get(tid)));
	}

}
