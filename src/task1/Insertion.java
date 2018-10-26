package task1;

public class Insertion extends Sort{
	
	public Insertion(int []a, int sizeofa) {
		super(a, sizeofa);
	}
	
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
}
