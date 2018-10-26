package task1;
import java.util.*;
import java.lang.*;

public class Sort {
	public int []A;
	public int N;
	public long Rtime;
	public Sort(int []a, int sizeofa){
		N = sizeofa;
		A = new int[N];
		for(int i=0; i<N; i++){
			A[i] = a[i]; // deep copy
		}	
	}
	public int[] getA(){
		
		return A;
	}
	public void swap(int i,int j){
		int temp = A[j];
		A[j] = A[i];
		A[i] = temp;
	}
	
	public void showarray(){
		for(int i=0; i<N; i++){
			System.out.print(A[i]+ "  ");
		}
		System.out.println();
	}
	public long printRtime() {
		return Rtime;
	}
	
/*
	public void insertion_sort(){
		int i;
		for (int j = 1; j < N; j++){
			int key = A[j];
			i = j - 1;
			while (i >= 0 && A[i] > key){
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = key;
		}
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
			int q = randomized_partition(p,r);
			deterministic_quicksort(p, q-1);
			deterministic_quicksort(q+1, r);
		}
	}
	
	
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
	
	public int[] counting_sort_with_digit(int []a, int []B, int k, int d){
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
		return B; // sorted output is B, so we need to return this result
		
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
			//System.out.println("mx : "+ mx + ",  i : " + i );
			counting_sort_with_digit(A, B, mx, i);
		}
	}
	*/
	
	
}