package task3;
import javafx.util.Pair;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestResidualGraph graph = new TestResidualGraph();
		//System.out.println("Show flow/weight Info for test Graph: ");
		//graph.showWeight();
		System.out.println("initial adj_list: "); graph.showAdjList();
		//graph.showResidualCapacity();
		graph.showWeight();
		System.out.println("====================== Test ====================================== ");
		Pair<Integer, Integer> sid;
		Pair<Integer, Integer> tid;
		if(false) {
			// for test graph1
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(2,3);	
		}else {
			// for test graph2
			/*
			sid = new Pair<Integer, Integer>(0,1);
			tid = new Pair<Integer, Integer>(4,1);
			*/
			// for test graph3 
			
			sid = new Pair<Integer, Integer>(1,0);
			tid = new Pair<Integer, Integer>(1,3);
			
			// for test graph4
			/*
			sid = new Pair<Integer, Integer>(0,1);
			tid = new Pair<Integer, Integer>(2,1);
			*/
		}
		TestTool tool = new TestTool();
		System.out.println();
		//System.out.println("EdmondsKarp Result: ");
		//System.out.println(tool.EdmondsKarp(graph, graph.v.get(sid), graph.v.get(tid)));
		// problem A
		//graph.showWeight();
		// problem B
		//graph.showSaturatedEdge(); 
		
		// problem C
		System.out.println(tool.FordFurkerson_order1(graph, graph.v.get(sid), graph.v.get(tid)));
		//System.out.println(tool.FordFurkerson_order2(graph, graph.v.get(sid), graph.v.get(tid)));
		
	}

}
