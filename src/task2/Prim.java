package task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.util.Pair;

public class Prim extends Dijkstra{
	public void Prim_run(Graph G, Vertex s) {
		initialize_single_source(G,s);

		ArrayList< Pair< Double, Pair<Integer, Integer>> > Qarray 
				= new ArrayList< Pair< Double, Pair<Integer, Integer>> >();
		
		Iterator<Pair<Integer, Integer>> k = G.v.keySet().iterator();
		
		// Q = G.V
		while(k.hasNext()) {
			Pair<Integer, Integer> u = k.next();
			Pair< Double, Pair<Integer, Integer>> keyIDpairs 
					= new Pair< Double, Pair<Integer, Integer>>(G.v.get(u).d,u);
			Qarray.add(keyIDpairs);
		}
		PriorityQ Q = new PriorityQ(Qarray); 
		//System.out.println(Q.arr);
		
		while(!Q.arr.isEmpty()) {	
			Pair<Integer, Integer> MinId = Q.extractMinId();
			Vertex uu = G.v.get(MinId);
			
			Iterator<Vertex> it = G.adj_list.get(MinId).iterator();
			while(it.hasNext()) {
				Vertex itv = it.next();
				Vertex vv = G.v.get(itv.getId());
				Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pairs 
				= new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(uu.getId(), vv.getId());
				
				Pair<Double, Pair<Integer, Integer>> val 
				= new Pair<Double, Pair<Integer, Integer>>(vv.d, vv.getId());
				
				if ( Q.arr.contains(val) && (vv.d > uu.d + G.w.get(pairs))) {
					vv.d = uu.d + G.w.get(pairs);
					vv.pi = uu;
				}
				Q.key_update(itv.getId(),G.v.get(itv.getId()).d);
			}
			//System.out.println("=============================");
		}
		
	}
	
	public void showWeightandWrite(Graph G, Vertex s) {
		System.out.println("BackTracking .... ");
		File file = new File("./PrimOutput.txt"); // output at current directory 
		try {
			FileWriter fw = new FileWriter(file);
			Iterator<Pair<Integer, Integer>> it = G.v.keySet().iterator();
			Double sum_weight = new Double(0);
			while(it.hasNext()) {
				StringBuilder InfoLine = new StringBuilder();
				Pair<Integer, Integer> nid = it.next();
				
				InfoLine.append(G.v.get(nid).toString());
				
				if(G.v.get(nid).pi != null ) {
					InfoLine.append(G.v.get(nid).pi.toString());
					
					Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pairs 
					= new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(G.v.get(nid).pi.getId(), G.v.get(nid).getId());      
		
					Double edgeWeight = Double.parseDouble(String.format("%.3f",G.w.get(pairs)));
					sum_weight += G.w.get(pairs);
					InfoLine.append(" edge weight : ");
					InfoLine.append(edgeWeight.toString());
		
					System.out.println(InfoLine.toString());
					fw.write(InfoLine.toString());
					fw.write("\r\n");
					}
				//System.out.println();
				}
				fw.write("Total Sum of the weights: ");
				DecimalFormat formatter = new DecimalFormat("#0.000");
				//System.out.println(formatter.format(sum_weight));
				fw.write(formatter.format(sum_weight).toString());
				System.out.printf("%.3f", sum_weight);
				fw.write("\r\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

}
