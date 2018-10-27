package task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javafx.util.Pair;

public class PriorityQ {
	public ArrayList< Pair< Double, Pair<Integer, Integer>> > arr;
	public PriorityQ(ArrayList< Pair< Double, Pair<Integer, Integer>> > array) {
		arr = array;
		buildMinHeap();
	}
	public void buildMinHeap() {
		for(int i = (int)Math.floor(arr.size()/2); i>=0; i--) {
			minHeapify(i);
		}
	}
	public void minHeapify(int i) {
		int l = (i<<1)+1;
		int r = (i<<1)+2; 
		int smallest = i;
		if(l < arr.size() && (arr.get(l).getKey() < arr.get(i).getKey())){
			smallest = l;
		}
		if(r < arr.size() && (arr.get(r).getKey() < arr.get(i).getKey())){
			smallest = r;
		}
		if (smallest != i) {
			 Pair< Double, Pair<Integer, Integer>> temp = arr.get(i);
			 arr.set(i, arr.get(smallest));
			 arr.set(smallest, temp);
			 minHeapify(smallest);
		}
	}
	public Pair<Integer, Integer > extractMinId() {
		Pair<Double, Pair<Integer, Integer>> MinValueAndId = arr.get(0); 
		arr.set(0, arr.get(arr.size()-1));
		minHeapify(0);
		
		arr.remove(arr.size()-1);
		return MinValueAndId.getValue();
	}
	public void key_update(Pair<Integer, Integer> updateid, Double updateV) {
		Iterator<Pair<Double, Pair<Integer, Integer>>> it = arr.iterator();
		int i = 0;
		while(it.hasNext()) {
			Pair<Double, Pair<Integer, Integer>> jt = it.next();
			if (jt.getValue().equals(updateid)) {
				Pair<Double, Pair<Integer, Integer>> temp 
					= new Pair<Double, Pair<Integer, Integer>>(updateV, jt.getValue());
				arr.set(i, temp);
			}
			i++;
		}
		buildMinHeap();
			
	}
}
