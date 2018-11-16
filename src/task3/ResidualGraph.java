package task3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import javafx.util.Pair;

public class ResidualGraph extends Graph{
	// define a flow of an edge
	public HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double> flow 
		= new HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double>() ;
	// define a residual capacity
	public HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double> residualCapacity
		= new HashMap<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>, Double>() ;
		
	// Constructor
	public ResidualGraph() {
		initialize_flowAndresidualCapacity();
	}
	public ResidualGraph(String FILE_PATH) {
		super(FILE_PATH);	
		// flow initialize
		initialize_flowAndresidualCapacity();
	}
		
	public void initialize_flowAndresidualCapacity() { 
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
				residualCapacity.put(uvid_pair, weight(v.get(uid),vv));
			}
		}
	}
	
	public double getflow(Vertex a, Vertex b) {
		return flow.get(VertexPair(a,b)); 
	}
	public void setflow(Vertex a, Vertex b, double _flow) {
		flow.put(VertexPair(a,b), _flow);
	}
	public double getResidualCapacity(Vertex a, Vertex b) {
		return residualCapacity.get(VertexPair(a,b));
	}
	public void setResidualCapacity(Vertex a, Vertex b, double _residualCapacity) {
		residualCapacity.put(VertexPair(a,b), _residualCapacity);
	}
	
	// show residualCapacity
	public void showResidualCapacity() {
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
					System.out.print(")"); System.out.print(" residualCapacity: ");
					//System.out.println(" "+ w.get(VertexPair(uu, vv)));
					System.out.println(getResidualCapacity(uu,vv)); 
				}
			}
		}
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
					System.out.print(weight(uu,vv)- getResidualCapacity(uu,vv) + "/"); System.out.println(weight(uu,vv)); 
				}
			}
		}	
	}
		
	public void WriteWeightTofile(double _Max_flow) {
		File file = new File("./EdmondsKarpOutput.txt"); // output at current directory 
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("Flow value: "); 
			DecimalFormat formatter = new DecimalFormat("#0.00");
			Double THE_MAX_FLOW = _Max_flow; fw.write(formatter.format(THE_MAX_FLOW).toString()); fw.write("\r\n");
			Iterator<Pair<Integer, Integer>> iti = adj_list.keySet().iterator();
			while(iti.hasNext()) {
				Pair <Integer, Integer> uid = iti.next(); 
				Iterator<Vertex> itj = adj_list.get(uid).iterator();
				while(itj.hasNext()) {
					StringBuilder InfoLine = new StringBuilder();
					Vertex vv = itj.next();
					Vertex uu = v.get(uid);
					if(w.containsKey(VertexPair(uu, vv))){
						Integer uuname = uu.name;
						Integer vvname = vv.name;
						InfoLine.append("("); 
						InfoLine.append(uuname.toString()); InfoLine.append(","); InfoLine.append(vvname.toString()); 
						InfoLine.append(") Flow: ");
						Double Cfvalue = (weight(uu,vv)- getResidualCapacity(uu,vv));
						InfoLine.append(formatter.format(Cfvalue));
						fw.write(InfoLine.toString());
						fw.write("\r\n");
					}
				}
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
