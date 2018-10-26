package task1;

import java.util.Arrays;

public class Radix extends Sort implements Runable {
	public Radix(int []a, int sizeofa) {
		super(a, sizeofa);
	}
	/*
	public int[] counting_sort(int []a, int []B, int k){
		int []C = new int[k+1];
		for(int i=0; i<k+1; i++){
			C[i] = 0;
		}
		for(int j=0; j<a.length; j++){
			C[a[j]] = C[a[j]] + 1;
		}
		for(int i=1; i<k+1; i++){
			C[i] = C[i] + C[i-1];
		}
		for(int j=a.length-1 ; j> -1; j--){
			B[C[a[j]]-1] = a[j];
			C[a[j]] = C[a[j]] - 1;
		}
		return B; // sorted output is B, so we need to return this result
	}
	*/
	public void counting_sort_with_digit(int []a, int []B, int k, int d){
		int []C = new int[k+1];
		for(int i=0; i<k+1; i++){
			C[i] = 0;
		}
		
		for(int j=0; j<a.length; j++){
			C[( (int)(a[j]/Math.pow(10,d)) ) %10 ] = C[ ( (int)(a[j]/Math.pow(10,d)) ) %10 ] + 1;
		}
		
		for(int i=1; i<k+1; i++){
			C[i] = C[i] + C[i-1];
		}
	
		for(int j=a.length-1 ; j> -1; j--){
			B[(C[( (int)(a[j]/Math.pow(10,d)) ) %10 ]-1)] = a[j];
			C[( (int)(a[j]/Math.pow(10,d)) ) %10] = C[( (int)(a[j]/Math.pow(10,d)) ) %10 ] - 1 ;
		}
		
		A = B.clone();
		//return B; // sorted output is B, so we need to return this result
		
	}
	
	public int getMax(int a[], int n, int d){
		int mx = ( (int)(a[0]/Math.pow(10,d)) ) %10;
		for(int i = 0; i<n; i++){
			if(mx <( (int)(a[i]/Math.pow(10,d)) ) %10) mx = ( (int)(a[i]/Math.pow(10,d)) ) %10;
		}
		return mx;
	}
	
	public void radix_sort(int d){
		int B[] = new int[A.length];
		int mx;
		for (int i=0 ; i< d; i++){
			mx = getMax(A, A.length, i);
			counting_sort_with_digit(A, B, mx, i);
		}
	}
	
	@Override
	public void runSort() {
		int B2[] = new int[A.length];
		int k = Arrays.stream(A).max().getAsInt();
		int D=0;
		while(Boolean.TRUE){
			k = k/10;
			D++;
			if(k == 0) break;
		}
		
		long startTime = System.nanoTime();
		radix_sort(D);
		long endTime   = System.nanoTime();
		Rtime = endTime - startTime;
		//System.out.println(Rtime);		
	}
	
}
