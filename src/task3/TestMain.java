package task3;
import javafx.util.Pair;
import task2.Prim;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestGraph graph = new TestGraph();
		graph.showWeight();
		System.out.println("====================== Test ====================================== ");
		//G.showAdjList();
		Pair<Integer, Integer> sid;
		Pair<Integer, Integer> tid;
		sid = new Pair<Integer, Integer>(0,0);
		tid = new Pair<Integer, Integer>(2,3);	
		
		Tool tool = new Tool();
		System.out.print("Result: ");
		System.out.println(tool.BFS(graph, graph.v.get(sid), graph.v.get(tid)));
		System.out.println(tool.ShortestPathList(graph, graph.v.get(sid), graph.v.get(tid)));
	}

}
