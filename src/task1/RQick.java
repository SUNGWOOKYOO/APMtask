package task1;
import java.util.*;

public class RQick extends Sort implements Runable{
	public RQick(int []a, int sizeofa) {
		super(a, sizeofa);
	}
	
	public int partition(int p, int r){
		int x = A[r];
		int i = p-1;
		
		for(int j=p; j< r; j++){
			if(A[j] <= x){
				i += 1;
				swap(i,j);
			}
		}
		i += 1;
		swap(i,r);
		return i;
	}
	
	public void quick_sort(int p,int r){
		if(p < r){
			int q = partition(p,r);
			quick_sort(p, q-1);
			quick_sort(q+1, r);
		}
	}
	
	public int randomized_partition(int p, int r){
		Random R = new Random(System.currentTimeMillis());
		int i =  p + R.nextInt(r - p + 1);
		swap(i,r);
		return partition(p,r);
	}
	
	public void randomized_quicksort(int p, int r){
		if (p<r){
			int q = randomized_partition(p,r);
			randomized_quicksort(p, q-1);
			randomized_quicksort(q+1, r);
		}
	}
	
	public int deterministic_partition(int p, int r){
		int i =  p;
		swap(i,r);
		return partition(p,r);
	}
	
	public void deterministic_quicksort(int p, int r){
		if (p<r){
			int q = deterministic_partition(p,r);
			deterministic_quicksort(p, q-1);
			deterministic_quicksort(q+1, r);
		}
	}
	
	@Override
	public void runSort() {
		long startTime = System.nanoTime();
		randomized_quicksort(0,N-1);
		long endTime   = System.nanoTime();
		Rtime = endTime - startTime;
		//System.out.println(Rtime);		
	}
}
