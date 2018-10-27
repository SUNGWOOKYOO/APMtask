package task2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

public class Graph {
	// Member variable
	private int num_grid = 0;
	private int num_vertices = 0;
	private HashMap<Pair<Integer,Integer>, ArrayList<Vertex>> adj_list; // HashMap< vertex id, G.adjacent[vertex] >
	private double [][]height_mat = null;
	private double [][]weight_mat = null;
	
	// Constructor
	public Graph(String FILE_PATH) {
		MakeheightMat(FILE_PATH);
		MakeAdjList();
		MakeWeightMat();
	}
	// Method
	public void MakeAdjList() {
		adj_list = new HashMap<Pair<Integer,Integer>, ArrayList<Vertex>>(); 
		
		for(int x1=0; x1< num_grid; x1++) {
			for(int y1=0; y1< num_grid; y1++) {
				if(height_mat[x1][y1] != -1.0) {
					Vertex src = new Vertex(x1,y1);
					src.setheight(height_mat[x1][y1]);
					ArrayList<Vertex> temp = new ArrayList<Vertex>();
					adj_list.put(src.getId(), temp);
					for(int x2=0; x2< num_grid; x2++) {
						for(int y2=0; y2<num_grid; y2++) {
							if(height_mat[x2][y2] != -1.0) {
								Vertex new_vertex = new Vertex(x2,y2);
								new_vertex.setheight(height_mat[x2][y2]);
								if(( !((x1==x2)&&(y1==y2)) ) && ( Math.abs(x1-x2) + Math.abs(y1-y2) <= 2 ) ) {
									adj_list.get(src.getId()).add(new_vertex);
									//System.out.println(adj_list.get(src.getId()));
								}
							}
						}
					}
				}
			}
		}
	}
	void showAdjList() {
		int k = 0;
		for(int x1=0; x1< num_grid; x1++) {
			for(int y1=0; y1< num_grid; y1++) {
				if (adj_list.get(new Pair<Integer, Integer>(x1,y1)) != null){
					System.out.println("Source vertex ("+x1+","+y1+") : ");
					System.out.print("Adjacent List : ");
					ArrayList<Vertex> temp = adj_list.get(new Pair<Integer, Integer>(x1,y1));
					Iterator<Vertex> it = temp.iterator();
					while(it.hasNext()) {
						System.out.print("\u2192");
						it.next().printId();
					}
					System.out.println();
					k++;
				}
			}
		}
		System.out.println("num_vertices: "+k);
		num_vertices = k;
	}
	public void MakeheightMat(String FILE_PATH) {
		File f = null;
		FileReader fr = null;
		BufferedReader in_file = null;
				
		try
		{
			f = new File(FILE_PATH);
			fr = new FileReader(f);
			in_file = new BufferedReader(fr); 
			if(new File(FILE_PATH).exists()) {
				String temp = (new BufferedReader(new FileReader(new File(FILE_PATH)))).readLine();
				num_grid = temp.split("\t").length;	
			}
			height_mat = new double[num_grid][num_grid];
			if (f.exists()) {
				int i = 0;
				for (String str = in_file.readLine(); str != null; str = in_file.readLine()) {
					//System.out.println(str);
					String []vector = str.split("\t");
					//System.out.println("Debug : "+ vector.length); // in file, there must be no addition space
					for (int j=0; j<vector.length; j++) {
						height_mat[i][j] = Double.parseDouble(vector[j]);
						//System.out.println(i +","+j +" : " + height_mat[i][j]);
						}
					i++;
					}
				}
			}
			catch (Exception e){
				System.out.println("I/O error" + e);
			}
			finally{
				try{
					in_file.close();
				}
				catch (Exception e){
					System.out.println("I/O error" + e);
				}
			}
	}
	public void showheightMat() {
		for (int i=0; i<num_grid; i++ ) {
			for (int j=0; j<num_grid; j++ ) {
				System.out.print(height_mat[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public void MakeWeightMat() {
		weight_mat = new double [num_grid*num_grid][num_grid*num_grid];
		for (int i=0;i<num_grid*num_grid; i++) {
			for(int j=0; j<num_grid*num_grid; j++) {
				weight_mat[i][j] = -1;
			}
		}
		
		System.out.println(adj_list.keySet());
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> p = iti.next();
			int x1 = p.getKey();
			int y1 = p.getValue();
			double h1 = height_mat[p.getKey()][p.getValue()];
			System.out.println(x1+","+y1+ "and "+h1 +" ~ ");
			Iterator<Vertex> itj = adj_list.get(p).iterator();
			while(itj.hasNext()) {
				Vertex v = itj.next();
				int x2 = v.getId().getKey();
				int y2 = v.getId().getValue();
				double h2 = v.getheight();
				System.out.println(x2+","+y2+ "and "+h2);
			}
			System.out.println("-----------------------------");
		}
	}

}
