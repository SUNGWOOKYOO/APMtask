package task2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Graph {
	// Member variable
	private int num_vertices = 0;
	private ArrayList<Vertex> adj_list = null;
	double [][]adj_mat = null;
	
	// Constructor
	public Graph(String FILE_PATH) {
		MakeWeightMat(FILE_PATH);
		
	}
	
	// Method
	
	
	public void MakeWeightMat(String FILE_PATH) {
		File f = null;
		FileReader fr = null;
		BufferedReader in_file = null;
				
		try
		{
			f = new File(FILE_PATH);
			fr = new FileReader(f);
			in_file = new BufferedReader(fr); 
			if(new File(FILE_PATH).exists()) {
				String temp = (new BufferedReader(new FileReader(new File(FILE_PATH)))).readLine();
				num_vertices = temp.split("\t").length;	
			}
			adj_mat = new double[num_vertices][num_vertices];
			if (f.exists()) {
				int i = 0;
				for (String str = in_file.readLine(); str != null; str = in_file.readLine()) {
					//System.out.println(str);
					String []vector = str.split("\t");
					//System.out.println("Debug : "+ vector.length); // in file, there must be no addition space
					for (int j=0; j<vector.length; j++) {
						adj_mat[i][j] = Double.parseDouble(vector[j]);
						//System.out.println(i +","+j +" : " + adj_mat[i][j]);
						}
					i++;
					}
				}
			}
			catch (Exception e){
				System.out.println("I/O error" + e);
			}
			finally{
				try{
					in_file.close();
				}
				catch (Exception e){
					System.out.println("I/O error" + e);
				}
			}
	}
	
	public void showAdjMat() {
		for (int i=0; i<num_vertices; i++ ) {
			for (int j=0; j<num_vertices; j++ ) {
				System.out.print(adj_mat[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
}
