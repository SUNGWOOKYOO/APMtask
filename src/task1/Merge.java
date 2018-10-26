package task1;

public class Merge extends Sort implements Runable {
	public Merge(int []a, int sizeofa) {
		super(a, sizeofa);
	}
	
	public void merge(int p, int q, int r)
	{
		int n1 = q - p + 1;
		int n2 = r - q;
		// Let L[0 .. n1] and R[0 ..n2] be new arrays
		int []L = new int[n1 + 1];
		int []R = new int[n2 + 1];
		for (int i = 0; i < n1; i++){
			L[i] = A[p + i];
		}
		for (int j = 0; j < n2; j++){
			R[j] = A[q + j + 1];
		}
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;

		int i = 0;
		int j = 0;
		for (int k = p; k <= r; k++){
			if (L[i] <= R[j]){
				A[k] = L[i];
				i += 1;
			}
			else{
				A[k] = R[j];
				j += 1;
			}
		}
	}

	public void merge_sort(int p, int r)
	{
		if (p < r)
		{
			int q = (p + r) / 2;
			merge_sort(p, q);
			merge_sort(q + 1, r);
			merge(p, q, r);
		}
	}
	@Override
	public void runSort() {
		long startTime = System.nanoTime();
		merge_sort(0,N-1);
		long endTime   = System.nanoTime();
		Rtime = endTime - startTime;
		//System.out.println(Rtime);		
	}

}
