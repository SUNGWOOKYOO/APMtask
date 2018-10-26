package task1;

import task1.Sort;
import task1.MaxHeap;
import java.io.*;


public class hw {
	
	public static void showexternalarray(int []a, int N){
		for(int i=0; i<N; i++){
			System.out.print(a[i]+ "  ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
/* ========================================================================================================================
 * 				Parsing Data
 ========================================================================================================================*/
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("file name: ");
		String src = sc.nextLine();
		String path = "/home/swyoo/FileRead/HwData/".concat(src);
		*/
		String []filename = {"10k_almostsorted.txt","10k_random.txt","50k_almostsorted.txt","50k_random.txt"};
		for(int iteration = 0; iteration< filename.length ;iteration++){
		System.out.println("====================== " + filename[iteration] + " calcuating average running time ==========================");	
		String path = "D:\\FileRead\\HwData\\APM\\hw1_data\\".concat(filename[iteration]);
		StringBuilder str = new StringBuilder();
				
		InputStream f = null;
		try  {
			f = new FileInputStream(path);
			int c;
			while((c= f.read()) != -1 ) {
				str.append((char)c);
			}
			f.close();
		}catch (IOException e) {
			System.out.println("I/O error ! ");
		}
	
		String sarray = str.toString();
		String []Sa = sarray.split(" ");		
		int []a = new int[Sa.length];
		int n = a.length;
		
		try{
			for(int i=0; i < a.length-1;i++){
				a[i] = Integer.parseInt(Sa[i]);
			}
			StringBuilder k = new StringBuilder();
			k.append(Sa[a.length-1]);
			a[a.length-1] = Integer.parseInt(k.substring(0,Sa[a.length-1].length()-1));
			
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException error ! ");
		}
		
/* =========================================================================================================================
 * 			Sorting Data
 ==========================================================================================================================*/
		long AverageRtime = 0;
		
		System.out.println("Selection sort: ");
		Selection Ss = new Selection(a, n);
		for(int i=0;i<2 ;i++){
			Ss.runSort();
			AverageRtime += Ss.Rtime;			
		}
		AverageRtime = AverageRtime/2; 
		System.out.println(">>>> ARtime: "+ AverageRtime);
		AverageRtime = 0;
		//Ss.showarray();
		
		System.out.println("Merge sort: ");
		Merge Ms = new Merge(a, n);
		for(int i=0;i<100 ;i++){
			Ms.runSort();
			AverageRtime += Ms.Rtime;			
		}
		AverageRtime = AverageRtime/100; 
		System.out.println(">>>> ARtime: "+ AverageRtime);
		AverageRtime = 0;
		//Ms.showarray();
		
		System.out.println("Randomized Qick sort: ");
		RQick RQs = new RQick(a, n);
		for(int i=0;i<100 ;i++){
			RQs.runSort();
			AverageRtime += RQs.Rtime;			
		}
		AverageRtime = AverageRtime/100; 
		System.out.println(">>>> ARtime: "+ AverageRtime);
		AverageRtime = 0;
		//RQs.showarray();
		
		System.out.println("Deterministic Qick sort: ");
		DQick DQs = new DQick(a, n);
		for(int i=0;i<1 ;i++){
			DQs.runSort();
			AverageRtime += DQs.Rtime;			
		}
		AverageRtime = AverageRtime/2; 
		System.out.println(">>>> ARtime: "+ AverageRtime);
		AverageRtime = 0;
		//DQs.showarray();
		
		System.out.println("Radix sort: ");
		Radix Rs = new Radix(a, n);
		Rs.runSort();
		for(int i=0;i<100 ;i++){
			Rs.runSort();
			AverageRtime += Rs.Rtime;			
		}
		AverageRtime = AverageRtime/100; 
		System.out.println(">>>> ARtime: "+ AverageRtime);
		AverageRtime = 0;
		//Rs.showarray();
		
		System.out.println("Heap sort: ");
		MaxHeap Mh = new MaxHeap(a,n);
		for(int i=0;i<100 ;i++){
			Mh.runSort();
			AverageRtime += Mh.Rtime;			
		}
		AverageRtime = AverageRtime/100; 
		System.out.println(">>>> ARtime: "+ AverageRtime);
		AverageRtime = 0;
		//Mh.showarray();
		}
		
	}
	
}
