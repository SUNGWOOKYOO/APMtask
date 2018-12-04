package task4;

public class hw4 {
	public static void main(String[] args) {
		System.out.println("Hw#4 Start! ");
		String GRAPH_FILE;
		PageRankGraph graph = null;
		if(true) {
			GRAPH_FILE = "C:\\Users\\cmin.kim\\eclipse-workspace\\task4\\soc-sign-bitcoinotc.txt";
			graph = new PageRankGraph(GRAPH_FILE, 0.85);
			graph.showGraphInfo();
		}else {
			GRAPH_FILE = "C:\\Users\\cmin.kim\\eclipse-workspace\\task4\\test5.txt";
			graph = new PageRankGraph(GRAPH_FILE, 0.5);
			graph.ShowStateMat();
			graph.ShowNodeOutDegreeVector();
			graph.ShowNodeInDegreeVector();
			graph.ShowNonExistNodeID();
			graph.ShowTranMat();
			System.out.println("NODENUM: " + graph.NODENUM);
			System.out.println("EDGENUM: " + graph.EDGENUM);
		}
		
		PageRankAlgorithm tool = new PageRankAlgorithm();
		tool.FindStaionaryPageRank(graph);
		graph.showPageRankTop10();
	}
}
