package task3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javafx.util.Pair;

public class hw3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hw#3 Start! ");
		String GRAPH_FILE;
		String GRAPH_FILE2;
		Pair<Integer, Integer> sid;
		Pair<Integer, Integer> tid;
		int snum, tnum;
		if(true) {
			GRAPH_FILE = "/home/swyoo/FileRead/APM/hw3_data/height.txt";
			GRAPH_FILE2 = "/home/swyoo/FileRead/APM/hw3_data/integer.txt";
			//GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\height.txt";
			//GRAPH_FILE2 = "D:\\FileRead\\HwData\\APM\\hw3_data\\integer.txt";			
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(19,19);
			snum = 0;
			tnum = 9;
		}
		else {
			//GRAPH_FILE = "/home/swyoo/FileRead/APM/hw3_data/testheight.txt";
			//GRAPH_FILE2 = "/home/swyoo/FileRead/APM/hw3_data/testinteger.txt";
			//GRAPH_FILE = "D:\\FileRead\\HwData\\APM\\hw3_data\\testheight.txt"; 
			//GRAPH_FILE2 = "D:\\FileRead\\HwData\\APM\\hw3_data\\testinteger.txt";
			sid = new Pair<Integer, Integer>(0,0);
			tid = new Pair<Integer, Integer>(2,2);
			snum = 0;
			tnum = 5;
		} 
		ResidualGraph graph = new ResidualGraph(GRAPH_FILE);
		System.out.println("==================================================================================================================================================================");
		System.out.println("								show weight/ flow										");
		System.out.println("==================================================================================================================================================================");		
		//graph.showWeight();
		System.out.println();
		System.out.println("==================================================================================================================================================================");
		System.out.println("								show residualCapacity										");
		System.out.println("==================================================================================================================================================================");
		//graph.showResidualCapacity();
		System.out.println();
		
		// Problem 4.A  and Problem 4.B
		Tool tool = new Tool();
		//System.out.println("EdmondsKarp Result: ");
		System.out.println(tool.EdmondsKarp(graph, graph.v.get(sid), graph.v.get(tid)));
		//graph.showWeight();
		//graph.showSaturatedEdge();
		//graph.showResidualCapacity();
		
		// Problem 4.C
		ProblemC PROBLEMC = new ProblemC();
		ProblemCGraph graph2 = new ProblemCGraph(GRAPH_FILE2); 
		long startTime1 = System.nanoTime();
		double result1 = PROBLEMC.FordFurkerson_order1(graph2, graph2.vertice[snum], graph2.vertice[tnum]);
		long stopTime1 = System.nanoTime();
		long elapsedTime1 = (stopTime1 - startTime1);
		System.out.println(result1);
		
		ProblemCGraph graph3 = new ProblemCGraph(GRAPH_FILE2); 
		long startTime2 = System.nanoTime();
		double result2 = PROBLEMC.FordFurkerson_order2(graph3, graph3.vertice[snum], graph3.vertice[tnum]);
		long stopTime2 = System.nanoTime();
		long elapsedTime2 = (stopTime2 - startTime2);
		System.out.println(result2);
		
		System.out.println("Write FordFulkerson.txt ... ");
		File file = new File("./FordFulkerson.txt"); // output at current directory 
		try {
			FileWriter fw = new FileWriter(file);
			DecimalFormat formatter = new DecimalFormat("#0.00");
			DecimalFormat formatter2 = new DecimalFormat("#0");
			
			fw.write("1) Totoal: "); 
			fw.write(formatter.format(result1).toString()); fw.write("/");
			fw.write(" Execution time: "); fw.write(formatter2.format(elapsedTime1/1000).toString()); fw.write(" microsecond");
			fw.write("\r\n");
			
			fw.write("2) Totoal: "); 
			fw.write(formatter.format(result2).toString()); fw.write("/");
			fw.write(" Execution time: "); fw.write(formatter2.format(elapsedTime2/1000).toString()); fw.write(" microsecond");
			fw.write("\r\n");
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
