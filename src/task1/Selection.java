package task1;

public class Selection extends Sort implements Runable{
	public Selection(int []a, int sizeofa) {
		super(a, sizeofa);
	}
	public void selection_sort(){
		for(int i=0; i< N-1; i++){
			int min = i; 
			for(int j = i+1; j<N; j++){
				if(A[j]<A[min])
					min =j;
			}
			swap(i,min);
		}
	}
	
	@Override
	public void runSort() {
		long startTime = System.nanoTime();
		selection_sort();
		long endTime   = System.nanoTime();
		Rtime = endTime - startTime;
		//System.out.println(Rtime);		
	}
}
