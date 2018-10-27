#include "HW2.h"


void minHeapify(std::vector< std::pair<int, double> >& unvisited, int parent)
{
	int left = 2 * parent + 1;
	int right = left + 1;
	int smallest = parent;

	if (left < unvisited.size() && unvisited[left].second < unvisited[parent].second)
		smallest = left;

	if (right < unvisited.size() && unvisited[right].second < unvisited[smallest].second) 
		smallest = right;
	
	if (smallest != parent) {
		std::pair<int, double> temp = unvisited[parent];
		unvisited[parent] = unvisited[smallest];
		unvisited[smallest] = temp;
		minHeapify(unvisited, smallest);
	}
}


void buildMinHeap(std::vector< std::pair<int, double> >& unvisited)
{
	for (int i = std::floor(unvisited.size() / 2); i >= 0; i--) {
		minHeapify(unvisited, i);
	}
}


std::pair<int, double> extractMinDist(std::vector< std::pair<int, double> >& unvisited)
{
	buildMinHeap(unvisited);
	std::pair<int, double> min_vertex = unvisited[0];
	unvisited[0] = unvisited.back();
	unvisited.pop_back();
	minHeapify(unvisited, 0);

	return min_vertex;
}


Graph::Graph(const std::string FILE_PATH)
{
	makeAdjMat(FILE_PATH);
	makeAdjList();
}


Graph::~Graph()
{
	for (int i = 0; i < num_vertices; i++)
		delete[] adj_mat[i];
	delete[] adj_mat;

	for (int i = 0; i < num_vertices; i++)
	{
		AdjVertex* curr = adj_list[i];
		AdjVertex* prev = curr;
		while (curr != NULL)
		{
			curr = curr->next;
			delete prev;
			prev = curr;
		}

		delete prev;
		adj_list[i] = NULL;
	}
}


void Graph::Dijkstra(int source_index,
	                 std::vector<double>& dist,
	                 std::vector<int>& prev)
{
	// Initialize
	// dist: Array for distance calculation, vector of (index-distance) pair
	// prev: Array for shortest path back tracking
	// unvisited: Set of vertices to be visited
	std::cout << "Initializing..." << std::endl;
	std::vector< std::pair<int, double> > unvisited;

	dist.resize(num_vertices);
	prev.resize(num_vertices);
	unvisited.resize(num_vertices);

	for (int i = 0; i < unvisited.size(); i++)
	{
		if (i != source_index)
		{
			unvisited[i] = std::pair<int, double>(i, std::numeric_limits < double >::max());
			dist[i] = std::numeric_limits < double >::max();
			prev[i] = -1; // -1 represents 'Undefined'
		}
		else
		{
			unvisited[i] = std::pair<int, double>(i, 0);
			dist[i] = 0;
			prev[i] = -1;
		}
	}

	// Begin Shortest Path Search
	std::cout << "Begin path searching..." << std::endl;
	while (unvisited.size() > 0 )
	{
		std::pair<int, double> vertex_to_visit = extractMinDist(unvisited);
		AdjVertex* adjacent_vertices = adj_list[vertex_to_visit.first];
		AdjVertex* curr = adjacent_vertices;
		while (curr != NULL)
		{
			double new_dist = dist[vertex_to_visit.first] + curr->weight;
			if (new_dist < dist[curr->id])
			{
				dist[curr->id] = new_dist;
				prev[curr->id] = vertex_to_visit.first;
				for (int i = 0; i < unvisited.size(); i++) 
				{
					if (unvisited[i].first == curr->id)
					{
						unvisited[i].second = new_dist;
						break;
					}
				}
			}

			curr = curr->next;
		}
	}
}


void Graph::Prim(int seed_index,
	             std::vector<double>& dist,
	             std::vector<int>& prev)
{
	// Initialize
	// dist: Array for distance calculation, vector of (index-distance) pair
	// prev: Array for shortest path back tracking
	// unvisited: Set of vertices to be visited
	std::cout << "Initializing..." << std::endl;
	std::vector< std::pair<int, double> > unvisited;

	dist.resize(num_vertices);
	prev.resize(num_vertices);
	unvisited.resize(num_vertices);

	for (int i = 0; i < unvisited.size(); i++)
	{
		if (i != seed_index)
		{
			unvisited[i] = std::pair<int, double>(i, std::numeric_limits < double >::max());
			dist[i] = std::numeric_limits < double >::max();
			prev[i] = -1; // -1 represents 'Undefined'
		}
		else
		{
			unvisited[i] = std::pair<int, double>(i, 0);
			dist[i] = 0;
			prev[i] = -1;
		}
	}

	// Begin Shortest Path Search
	std::cout << "Begin path searching..." << std::endl;
	while (unvisited.size() > 0)
	{
		std::pair<int, double> vertex_to_visit = extractMinDist(unvisited);
		AdjVertex* adjacent_vertices = adj_list[vertex_to_visit.first];
		AdjVertex* curr = adjacent_vertices;
		while (curr != NULL)
		{
			double new_dist = curr->weight;
			if (new_dist < dist[curr->id])
			{
				dist[curr->id] = new_dist;
				prev[curr->id] = vertex_to_visit.first;
				for (int i = 0; i < unvisited.size(); i++)
				{
					if (unvisited[i].first == curr->id)
					{
						unvisited[i].second = new_dist;
						break;
					}
				}
			}

			curr = curr->next;
		}
	}
}


void Graph::makeAdjMat(const std::string FILE_PATH)
{
	std::ifstream in_file;
	in_file.open(FILE_PATH);

	std::vector<double> weights;
	double weight;
	while (in_file >> weight)
		weights.push_back(weight);

	in_file.close();

	num_vertices = std::sqrt(weights.size());

	adj_mat = new double*[num_vertices];
	for (int i = 0; i < num_vertices; i++) 
		adj_mat[i] = new double[num_vertices];

	in_file.open(FILE_PATH);
	if (!in_file)
	{
		std::cout << "Failed to open file " << std::endl;
		return;
	}
	else
	{
		std::cout << "File opened successfully" << std::endl;
		for (int i = 0; i < num_vertices; i++)
		{
			for (int j = 0; j < num_vertices; j++)
			{
				in_file >> adj_mat[i][j];
			}
		}
	}

	in_file.close();
}


void Graph::makeAdjList()
{
	adj_list.resize(num_vertices);

	for (int i = 0; i < num_vertices; i++)
	{
		adj_list[i] = NULL;
		for (int j = 0; j < num_vertices; j++)
		{
			if (adj_mat[i][j] != -1)
			{
				AdjVertex* new_vertex = new AdjVertex;
				new_vertex->id = j;
				new_vertex->weight = adj_mat[i][j];
				new_vertex->next = NULL;
				insertVertex(i, new_vertex);
			}
		}
	}
}


void Graph::insertVertex(int source_vertex, AdjVertex* new_vertex)
{
	if (adj_list[source_vertex] == NULL) { adj_list[source_vertex] = new_vertex; }
	else
	{
		AdjVertex* curr = adj_list[source_vertex];
		while (curr->next != NULL) curr = curr->next;
		curr->next = new_vertex;
	}
}


int Graph::getNumOfVertices()
{
	return num_vertices;
}


void Graph::showAdjMat()
{
	for (int i = 0; i < num_vertices; i++) {
		for (int j = 0; j < num_vertices; j++) {
			std::cout << adj_mat[i][j] << " ";
		}
		std::cout << std::endl;
	}
}


void Graph::showAdjList()
{
	for (int i = 0; i < num_vertices; i++)
	{
		AdjVertex* curr = adj_list[i];
		std::cout << "Vertex[" << i << "]: { ";

		while (curr != NULL)
		{
			std::cout << curr->id << "(" << curr->weight << ") ";
			curr = curr->next;
		}

		std::cout << "}" << std::endl;
	}
}