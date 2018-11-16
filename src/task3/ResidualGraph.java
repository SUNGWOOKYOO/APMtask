package task3;

import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

public class ResidualGraph extends Graph{
	// define a flow of an edge
	public HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double> flow 
		= new HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double>() ;
		
	// Constructor
	public ResidualGraph() {
		initialize_flow();
	}
	public ResidualGraph(String FILE_PATH) {
		super(FILE_PATH);	
		// flow initialize
		initialize_flow();
	}
		
	public void initialize_flow() {
		System.out.println(v.keySet());
		System.out.println(adj_list);
		Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
		while(iti.hasNext()) {
			Pair <Integer, Integer> uid = iti.next(); 
			Iterator<Vertex> itj = adj_list.get(uid).iterator();
			while(itj.hasNext()) {
				Vertex vv = itj.next();
				Pair <Integer, Integer> vid = vv.getId(); 
					
				// flow(u,v) - f(u,v) = weight(u,v) // capacity(u,v)
				Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> uvid_pair
					= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer> >(uid,vid);
				//Pair<Pair<Integer, Integer>,Pair<Integer, Integer>> vuid_pair
				//	= new Pair < Pair<Integer, Integer>, Pair< Integer, Integer> >(vid,uid);
				flow.put(uvid_pair, 0.0);
			}
		}
	}
	
	public double getflow(Vertex a, Vertex b) {
		return flow.get(VertexPair(a,b)); 
	}
	public void setflow(Vertex a, Vertex b, double _weight) {
		w.put(VertexPair(a,b), _weight);
	}
	
	@Override // show flow / weight
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
					System.out.print(")"); System.out.print(" flow/weight: ");
					//System.out.println(" "+ w.get(VertexPair(uu, vv)));
					System.out.print(getflow(uu,vv) + "//"); System.out.println(weight(uu,vv)); 
				}
			}
		}
	}
	
}
