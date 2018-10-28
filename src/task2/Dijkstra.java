package task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javafx.util.Pair;

public class Dijkstra {
	public void initialize_single_source(Graph G, Vertex s) {
		
		Iterator<Pair<Integer,Integer>> it = G.v.keySet().iterator();
		while(it.hasNext()) {
			Pair<Integer, Integer> p = it.next();
			G.v.get(p).d = Double.MAX_VALUE;
			G.v.get(p).pi = null;
		}
		G.v.get(s.getId()).d = 0;
		//debug
		/*
		G.showDandPI();
		Iterator<Vertex> k = G.adj_list.get(s.getId()).iterator();
		while(k.hasNext()) {
			Vertex u = k.next();
			u.printId();
			System.out.println(u.d);
		}
		*/
	}
	
	public void relax(Graph G, Vertex uu, Vertex vv) {
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pairs 
				= new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(uu.getId(), vv.getId());
		if (vv.d > uu.d + G.w.get(pairs)) {
			vv.d = uu.d + G.w.get(pairs);
			vv.pi = uu;
		}
	}
	
	public void Dijkstra_run(Graph G, Vertex s) {
		initialize_single_source(G,s);
		ArrayList<Pair<Integer, Integer>> Sset = new ArrayList<Pair<Integer, Integer>>();
		ArrayList< Pair< Double, Pair<Integer, Integer>> > Qarray 
				= new ArrayList< Pair< Double, Pair<Integer, Integer>> >();
		
		Iterator<Pair<Integer, Integer>> k = G.v.keySet().iterator();
		while(k.hasNext()) {
			Pair<Integer, Integer> u = k.next();
			Pair< Double, Pair<Integer, Integer>> keyIDpairs 
					= new Pair< Double, Pair<Integer, Integer>>(G.v.get(u).d,u);
			Qarray.add(keyIDpairs);
		}
		
		PriorityQ Q = new PriorityQ(Qarray);
		//System.out.println(Q.arr);
		while(!Q.arr.isEmpty()) {	
			//System.out.println(Q.arr);
			Pair<Integer, Integer> MinId = Q.extractMinId();
			//System.out.print("From PQ, Extract ");
			//G.v.get(MinId).printId();
			//System.out.println();
			Sset.add(MinId);	// shortest path decided
			//System.out.println(Sset);
			Iterator<Vertex> it = G.adj_list.get(MinId).iterator();
			while(it.hasNext()) {
				Vertex itv = it.next();
				relax(G,G.v.get(MinId),G.v.get(itv.getId()));
				Q.key_update(itv.getId(),G.v.get(itv.getId()).d);
			}
			//G.showDandPI();
			//System.out.println("=============================");
		}
	}
	
	public void showPathandWrite(Graph G, Vertex s) {
		System.out.println("BackTracking .... ");
		File file = new File("./DijkstraOutput.txt"); // output at current directory 
		try {
			FileWriter fw = new FileWriter(file);
			Iterator<Pair<Integer, Integer>> it = G.v.keySet().iterator();
			while(it.hasNext()) {
				StringBuilder InfoLine = new StringBuilder();
				Pair<Integer, Integer> nid = it.next();
				// destination
				//G.v.get(nid).printId();
				// distance from source to destination
				//System.out.printf(":%5.3f",G.v.get(nid).d);
				//System.out.print(" ");
				InfoLine.append(G.v.get(nid).toString());
				InfoLine.append(":");
				Double dis = Double.parseDouble(String.format("%.3f",G.v.get(nid).d));
				InfoLine.append(dis.toString()).append(" ");
				if(G.v.get(nid).pi != null ) {
					ArrayList<String> Path = new ArrayList<String>();
					Path.add(G.v.get(nid).toString());
					for(Vertex t = G.v.get(nid).pi; t != null; t = t.pi) {
						//backtracking print
						//t.printId();
						Path.add(t.toString());
					}
					//System.out.println();
					Collections.reverse(Path);
					//System.out.println(Path.toString());
					InfoLine.append(Path.toString());
					System.out.println(InfoLine.toString());
					fw.write(InfoLine.toString());
					fw.write("\r\n");
				}
				//System.out.println();
			}
			fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
}
