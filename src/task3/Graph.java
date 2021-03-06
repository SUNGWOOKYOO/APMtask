package task3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

public class Graph {
	// Member variable
	public int num_grid = 0;
	public int num_vertices = 0;
	public HashMap<Pair<Integer,Integer>, ArrayList<Vertex>> adj_list; // HashMap< vertex id, G.adjacent[vertex] >
	public HashMap<Pair<Integer,Integer>, Vertex> v = new HashMap<Pair<Integer,Integer>, Vertex>() ;
	public HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double> w 
				= new HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double>() ;
	
	private double [][]height_mat = null;
	
	// Constructor
	public Graph() {} // default Constructor
	public Graph(String FILE_PATH) {
		MakeHeightMat(FILE_PATH);
		MakeAdjList();
		setDistInfo();
	}
	// Method	
	public void MakeAdjList() {
		adj_list = new HashMap<Pair<Integer,Integer>, ArrayList<Vertex>>(); 
		
		for(int x1=0; x1< num_grid; x1++) {
			for(int y1=0; y1< num_grid; y1++) {
				if(height_mat[x1][y1] != -1.0) {
					Pair<Integer, Integer> px1y1 = new Pair<Integer, Integer>(x1, y1);
					//System.out.println("Debug");
					//System.out.println(v);
					if(!v.containsKey(px1y1)) {
						Vertex a = new Vertex(x1,y1);
						a.setheight(height_mat[x1][y1]);
						v.put(px1y1, a);
					}
					ArrayList<Vertex> temp = new ArrayList<Vertex>();
					adj_list.put(px1y1, temp);
					for(int x2=0; x2< num_grid; x2++) {
						for(int y2=0; y2<num_grid; y2++) {
							if(height_mat[x2][y2] != -1.0) {
								if(( !((x1==x2)&&(y1==y2)) ) && ( Math.abs(x1-x2) + Math.abs(y1-y2) <= 2 ) ) {
									Pair<Integer, Integer> px2y2 = new Pair<Integer, Integer>(x2, y2);
									if(!v.containsKey(px2y2)) {
										Vertex new_vertex = new Vertex(x2,y2);
										new_vertex.setheight(height_mat[x2][y2]);
										v.put(px2y2, new_vertex);
									}
									adj_list.get(px1y1).add(v.get(px2y2));
								}
							}
						}
					}
				}
			}
		}
		num_vertices = adj_list.keySet().size();
	}
	public void showAdjList() {
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()){
			Pair<Integer, Integer> id = iti.next();
			Vertex i = v.get(id);
			System.out.print("Vertex: "); i.printId();
			Iterator<Vertex> itj = adj_list.get(id).iterator();
			System.out.print("adj_list: ");
			while(itj.hasNext()) {
				Vertex j = itj.next();
				j.printId(); 
			}
			System.out.println();
		}
	}
	public void MakeHeightMat(String FILE_PATH) {
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
	public void showHeightMat() {
		for (int i=0; i<num_grid; i++ ) {
			for (int j=0; j<num_grid; j++ ) {
				System.out.print(height_mat[i][j] + "\t");
			}
			System.out.println();
		}
	}
	public void setDistInfo() {
		//System.out.println(adj_list.keySet());
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> uid = iti.next(); 
			int x1 = uid.getKey();
			int y1 = uid.getValue();
			double h1 = height_mat[uid.getKey()][uid.getValue()];
			//System.out.println( "from (" + x1+","+y1+ ", height1: "+h1 +" ) " );
			Iterator<Vertex> itj = adj_list.get(uid).iterator();
			while(itj.hasNext()) {
				// G.adj[u] : adjacent list for a vertex   
				Vertex vv = itj.next();
				int x2 = vv.getId().getKey();
				int y2 = vv.getId().getValue();
				double h2 = vv.getheight();
				//System.out.print( "to (" + x2+","+y2+ " height2: "+h2 + ")" );
				
				double distance = Math.sqrt( (Math.pow((h1 - h2), 2) + Math.pow((Math.abs(x1 -x2) + Math.abs(y1 -y2)), 2) ) );
				//double distance = Math.abs(h1 - h2);
				//System.out.println("  \u2192  Distance: "+ distance );
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> uvid_pair 
						= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer > >(uid,vv.getId());
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> vuid_pair 
					= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer > >(vv.getId(),uid);
				w.put(uvid_pair, distance);
				if(!adj_list.get(vv.getId()).contains(v.get(uid))) {
					w.put(vuid_pair, Double.MAX_VALUE);
				}
			}
			//System.out.println("-----------------------------");
		}
	}
	public double weight(Vertex a, Vertex b) {
		return w.get(VertexPair(a,b)); 
	}
	public void setweight(Vertex a, Vertex b, double _weight) {
		w.put(VertexPair(a,b), _weight);
	}
	public Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> VertexPair(Vertex a, Vertex b){
		Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> id_pair 
		= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer > >(a.getId(),b.getId());
		return id_pair;
	}
	public void showWeight() {
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> uid = iti.next(); 
			Iterator<Vertex> itj = adj_list.get(uid).iterator();
			while(itj.hasNext()) {
				Vertex vv = itj.next();
				Vertex uu = v.get(uid);
				if(w.containsKey(VertexPair(uu, vv))){
					System.out.print(" edge  (");
					uu.printId(); System.out.print(","); vv.printId(); 
					System.out.print(")"); System.out.print(" weight: ");
					//System.out.println(" "+ w.get(VertexPair(uu, vv)));
					System.out.println(weight(uu,vv));
				}
			}
		}
	}
	public void showDandPI() {
		Iterator<Pair<Integer, Integer>> it = v.keySet().iterator();
		while(it.hasNext()) {
			Pair<Integer, Integer> nid = it.next();
			v.get(nid).printId();
			System.out.println("'s d : "+ v.get(nid).d);
			System.out.println("'s pi : "+ v.get(nid).pi);
		}
	}
	
}
