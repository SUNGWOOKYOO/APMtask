#include "HW2.h"


int main()
{
	const std::string GRAPH_FILE = "C:\\Users\\±ÇÇõ¼ö\\Desktop\\graph.txt";
	Graph graph(GRAPH_FILE);

	graph.showAdjList();
	std::cout << std::endl;

	// Problem 4.B
	int source = 0;
	std::vector<double> dist;
	std::vector<int> prev;
	graph.Dijkstra(source, dist, prev);
	std::cout << "Dijkstra ended" << std::endl;
	std::ofstream out_file("Dijkstra_result.txt", std::ios::out);
	for (int destination = 0; destination < graph.getNumOfVertices(); destination++)
	{
		std::cout << "Destination: " << destination << std::endl;
		if (destination == source)
			continue;

		int back_track = destination;
		std::vector<int> trace;
		bool path_exist = true;

		while (back_track != source)
		{
			if (back_track == -1) 
			{
				path_exist = false;
				break; 
			}
			trace.push_back(back_track);
			back_track = prev[back_track];
		}

		if (path_exist)
		{
			std::cout << source << "->" << destination << ": [" << dist[destination] << "]" << std::endl;
			std::cout << source;
			out_file << source << "->" << destination << ": [" << dist[destination] << "]" << std::endl;
			out_file << source;
			for (int i = trace.size() - 1; i >= 0; i--) {
				std::cout << "->" << trace[i];
				out_file << "->" << trace[i];
			}
			std::cout << std::endl << std::endl;
			out_file << std::endl << std::endl;
		}
		else
		{
			std::cout << source << "->" << destination << ": Path do not exist" << std::endl << std::endl;
			out_file << source << "->" << destination << ": Path do not exist" << std::endl << std::endl;
		}
	}
	out_file.close();

	
	//Problem 4.C
	int seed = 199;
	std::vector<double> weight;
	std::vector<int> prev2;
	graph.Prim(seed, weight, prev2);
	std::cout << "Prim ended" << std::endl;

	out_file.open("Prim_result.txt", std::ios::out);
	double total_weight = 0;
	for (int i = 0; i < weight.size(); i++)
		total_weight += weight[i];
	std::cout << "Total weight of MST: " << total_weight << std::endl;
	out_file << "Total weight of MST: " << total_weight << std::endl;

	for (int i = 0; i < prev2.size(); i++)
	{
		if (prev2[i] != -1)
		{
			std::cout << "(" << i << ", " << prev2[i] << ")" << std::endl;
			out_file << "(" << i << ", " << prev2[i] << ")" << std::endl;
		}
	}

	return 0;
}