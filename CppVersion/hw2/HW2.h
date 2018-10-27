#ifndef __HW4__
#define __HW4__

#include <iostream>
#include <fstream>
#include <vector>

typedef struct AdjVertex
{
	int id;
	double weight;
	struct AdjVertex* next;
}AdjVertex;

class Graph
{
public:
	Graph(const std::string FILE_PATH);
	~Graph();

	// Problem B
	void Dijkstra(int source_index,
		          std::vector<double>& dist,
		          std::vector<int>& prev);

	// Problem C
	void Prim(int seed_index,
		      std::vector<double>& dist,
		      std::vector<int>& prev) ;

	int getNumOfVertices();
	void showAdjMat();
	void showAdjList();

private:
	int num_vertices;
	std::vector<AdjVertex*> adj_list;
	double** adj_mat;

	// Problem A
	void makeAdjMat(const std::string FILE_PATH);
	void makeAdjList();
	void insertVertex(int source_vertex, AdjVertex* new_vertex);
};

#endif