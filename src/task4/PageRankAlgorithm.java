package task4;

public class PageRankAlgorithm {
	public void Initialize_PageRankVector(PageRankGraph G) {
		G.PageRank = new double[G.MAXNODENUM];
		for (int i = 0; i < G.MAXNODENUM; ++i) {
			G.PageRank[i] = 1.0 / G.NODENUM;
			// G.PageRank[i] = 1.0;
			if (G.NonExistNodeID.contains(i)) {
				G.PageRank[i] = 0.0;
			}
		}
	}

	public void Multiply(PageRankGraph G) {
		Matrix tool = new Matrix();
		G.PageRank = tool.multiply(G.PageRank, G.Tran_mat);
	}

	public void FindStaionaryPageRank(PageRankGraph G) {
		Initialize_PageRankVector(G);
		int iteration = 0;

		// debug
		System.out.println("iteration [0]: ... ");
		// G.showPageRank();

		double[] prevPageRank = new double[G.MAXNODENUM];
		prevPageRank = G.PageRank.clone();
		Multiply(G); // PageRank update
		iteration++;

		// debug
		System.out.println("iteration [" + iteration + "]: ... ");
		// G.showPageRank();

		Matrix tool = new Matrix();
		while (tool.calculateDistance(prevPageRank, G.PageRank) > 0.001) {
			Multiply(G); // PageRank update
			prevPageRank = G.PageRank.clone();
			iteration++;

			// debug
			System.out.println("iteration [" + iteration + "]: ... ");
			// G.showPageRank();
		}
		System.out.println("iteration [" + iteration + "]: ... end ");
		// G.showPageRank();
	}

}
