package task1;

public class MaxHeap extends Sort implements Runable {
//	public int []A;
	public int HeapLastIndex;
	
		
	public MaxHeap(int[] a, int sizeofa){
		super(a, sizeofa);
		//A = a.clone();
		HeapLastIndex = 0;
		
	}
	public int[] getA(){
		
		return A;
	}
	public int parent(int i){
		if(i<= HeapLastIndex)	return (i-1)>>1;
		else return i; 
	}
	public int left(int i){
		if(((i<< 1) + 1) <= HeapLastIndex) return (i<<1)+1;
		else return i;
	}
	public int right(int i){
		if(((i<< 1) + 2) <= HeapLastIndex) return (i<<1)+2;
		else return i;
	}
	
	public void max_heapify(int i){
		int L = left(i);
		int R = right(i);
		int largest;
		if((L <= A.length)&&(A[L] > A[i])) largest = L;
		else largest = i;
		if((R <= A.length)&&(A[R] > A[largest])) largest = R;
		
		if (largest != i){
			swap(i,largest);
			max_heapify(largest);
		}
	}
	public void build_max_heap(){
		HeapLastIndex = A.length -1;
		for(int i= (int)((A.length-1)/2); i > -1; i--){
			max_heapify(i);
		}
	}
	
	public int [] heap_sort(){
		build_max_heap();
		for (int i = A.length-1; i> -1; i--){
			swap(0,i);
			HeapLastIndex--;
			max_heapify(0);
		}
		return getA();
	}

	@Override
	public void runSort() {
		long startTime = System.nanoTime();
		heap_sort();
		long endTime   = System.nanoTime();
		Rtime = endTime - startTime;
		//System.out.println(Rtime);		
	}
}
