package task3;
import javafx.util.Pair;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestResidualGraph graph = new TestResidualGraph();
		//System.out.println("Show flow/weight Info for test Graph: ");
		//graph.showWeight();
		System.out.println("initial adj_list: "); graph.showAdjList();
		graph.showResidualCapacity();
		System.out.println("====================== Test ====================================== ");
		Pair<Integer, Integer> sid;
		Pair<Integer, Integer> tid;
		if(true) {
			// for test graph1
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(2,3);	
		}else {
			// for test graph2
			sid = new Pair<Integer, Integer>(0,1);
			tid = new Pair<Integer, Integer>(4,1);
		}
		Tool tool = new Tool();
		System.out.println();
		System.out.println("EdmondsKarp Result: ");
		//System.out.println(tool.EdmondsKarp(graph, graph.v.get(sid), graph.v.get(tid)));
		graph.showWeight();
	}

}
