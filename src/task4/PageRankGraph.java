package task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PageRankGraph {

	public int[][] State_mat = null;
	public double[][] Tran_mat = null;
	public int[] NodeOutDegree = null;
	public int[] NodeInDegree = null;
	public double[] PageRank = null;
	public ArrayList<Integer> NonExistNodeID;
	public int MAXNODENUM;
	public int NODENUM;
	public int EDGENUM;
	public double DampingRate;

	public PageRankGraph(String FILE_PATH, double DR) {
		SetDampingRate(DR);
		MakeGraph(FILE_PATH);
		MakeTransitionMat();
	}

	public void SetDampingRate(double DR) {
		DampingRate = DR;
	}

	public void MakeGraph(String FILE_PATH) {
		File f = null;
		FileReader fr = null;
		BufferedReader in_file = null;

		try {
			f = new File(FILE_PATH);
			fr = new FileReader(f);
			in_file = new BufferedReader(fr);
			// Find MAX_MAXNODENUM
			if (f.exists()) {
				MAXNODENUM = 0;
				EDGENUM = 0;
				for (String str = in_file.readLine(); str != null; str = in_file.readLine()) {
					String[] vector = str.split(", ");
					int SrcID = Integer.parseInt(vector[0]);
					int DestID = Integer.parseInt(vector[1]);
					int LargerID = Math.max(SrcID, DestID);
					if (LargerID >= MAXNODENUM) {
						MAXNODENUM = LargerID;
					}
					EDGENUM++;
					// System.out.println(EDGENUM +","+ SrcID +","+ DestID);
				}
			}
			MAXNODENUM = MAXNODENUM + 1; // This is because Matrix index range is from 0 to MAX-1
			// Set State_mat
			State_mat = new int[MAXNODENUM][MAXNODENUM];
			NodeOutDegree = new int[MAXNODENUM];
			NodeInDegree = new int[MAXNODENUM];
			for (int i = 0; i < MAXNODENUM; ++i) {
				NodeOutDegree[i] = 0;
				for (int j = 0; j < MAXNODENUM; ++j) {
					State_mat[i][j] = 0;
				}
			}
			f = new File(FILE_PATH);
			fr = new FileReader(f);
			in_file = new BufferedReader(fr);
			if (f.exists()) {
				for (String str = in_file.readLine(); str != null; str = in_file.readLine()) {
					String[] vector = str.split(", ");
					int SrcID = Integer.parseInt(vector[0]);
					int DestID = Integer.parseInt(vector[1]);
					State_mat[SrcID][DestID] = State_mat[SrcID][DestID] + 1;
					NodeOutDegree[SrcID] = NodeOutDegree[SrcID] + 1;
					NodeInDegree[DestID] = NodeInDegree[DestID] + 1;
				}
			}

			// Find NODENUM and Non-exist Nodes
			NODENUM = 0;
			NonExistNodeID = new ArrayList<Integer>();
			for (int NodeID = 0; NodeID < MAXNODENUM; ++NodeID) {
				if (NodeOutDegree[NodeID] == 0 && NodeInDegree[NodeID] == 0) {
					// System.out.println("Non-exist NodeID: " + NodeID);
					NonExistNodeID.add(NodeID);
				} else {
					NODENUM++;
				}
			}

		} catch (Exception e) {
			System.out.println("I/O error" + e);
		} finally {
			try {
				in_file.close();
			} catch (Exception e) {
				System.out.println("I/O error" + e);
			}
		}
	}

	public void MakeTransitionMat() {
		Tran_mat = new double[MAXNODENUM][MAXNODENUM];
		for (int i = 0; i < MAXNODENUM; ++i) {
			for (int j = 0; j < MAXNODENUM; ++j) {
				if (State_mat[i][j] != 0) {
					Tran_mat[i][j] = DampingRate / NodeOutDegree[i] + (1.0 - DampingRate) / NODENUM;
				} else {
					Tran_mat[i][j] = (1.0 - DampingRate) / NODENUM;
				}
			}
		}
		Iterator<Integer> it = NonExistNodeID.iterator();
		while (it.hasNext()) {
			int NonExistID = it.next();
			for (int j = 0; j < MAXNODENUM; ++j) {
				Tran_mat[NonExistID][j] = 0;
			}
			for (int i = 0; i < MAXNODENUM; ++i) {
				Tran_mat[i][NonExistID] = 0;
			}
		}
	}

	// For Debug
	public void ShowStateMat() {
		System.out.println("Show State_mat[...][...]");
		for (int i = 0; i < MAXNODENUM; ++i) {
			for (int j = 0; j < MAXNODENUM; ++j) {
				System.out.print(State_mat[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void ShowStateMat(int row) {
		System.out.println("Show State_mat[" + row + "][...]");
		for (int j = 0; j < MAXNODENUM; ++j) {
			System.out.print(State_mat[row][j] + " ");
		}
		System.out.println();
	}

	public void ShowNodeOutDegreeVector() {
		System.out.print("Show NodeOutDegree Vector [...]: ");
		for (int j = 0; j < MAXNODENUM; ++j) {
			System.out.print(NodeOutDegree[j] + " ");
		}
		System.out.println();
	}

	public void ShowNodeInDegreeVector() {
		System.out.print("Show NodeInDegree Vector [...]: ");
		for (int j = 0; j < MAXNODENUM; ++j) {
			System.out.print(NodeInDegree[j] + " ");
		}
		System.out.println();
	}

	public void ShowTranMat() {
		System.out.println("Show Tran_mat [...][...]: ");
		DecimalFormat formatter = new DecimalFormat("#0.00");
		for (int i = 0; i < MAXNODENUM; ++i) {
			for (int j = 0; j < MAXNODENUM; ++j) {
				System.out.print(formatter.format(Tran_mat[i][j]).toString() + " ");
			}
			System.out.println();
		}
	}

	public void ShowNonExistNodeID() {
		System.out.println("The # of NonExistNodeID: " + NonExistNodeID.size());
		System.out.println("Show NonExistNodeID [...]: ");
		Iterator<Integer> it = NonExistNodeID.iterator();
		while (it.hasNext()) {
			int ID = it.next();
			System.out.print(ID + " ");
		}
		System.out.println();
	}

	public void showGraphInfo() {
		System.out.println("MaxNodeID: " + MAXNODENUM);
		// ShowNonExistNodeID();
		System.out.println("NODENUM: " + NODENUM);
		System.out.println("EDGENUM: " + EDGENUM);

	}

	public void showPageRank() {
		System.out.println("Show PageRank Vector [...]: ");
		DecimalFormat formatter = new DecimalFormat("#0.000");
		for (int i = 0; i < MAXNODENUM; ++i) {
			System.out.print(formatter.format(PageRank[i]).toString() + " ");
		}
		System.out.println();
	}

	public void showPageRankTop10() {
		System.out.println("Show top10 Rank: ID/PageRank [...]: ");
		DecimalFormat formatter = new DecimalFormat("#0.000");
		List<Tuple> TotalList = new ArrayList<>();
		List<Tuple> Top10 = new ArrayList<>();
		for (int i = 0; i < MAXNODENUM; ++i) {
			TotalList.add(new Tuple(i, PageRank[i]));
		}
		Collections.sort(TotalList);

		Iterator<Tuple> it = TotalList.iterator();
		int Count = 0;
		while (it.hasNext() && (Count < 10)) {
			Top10.add(it.next());
			Count++;
		}

		/*
		 * ======================================================== 
		 * show and writetofile
		 * ========================================================
		 */
		System.out.println("Write PageRank_Top10.txt ... ");
		File file = new File("./PageRank_Top10.txt"); // output at current directory

		Iterator<Tuple> topit = Top10.iterator();
		try {
			FileWriter fw = new FileWriter(file);
			int Rank = 1;
			fw.write("Rank: ID/PR \r\n");
			while (topit.hasNext()) {
				StringBuilder InfoLine = new StringBuilder();
				Tuple temp = topit.next();
				System.out.print(Rank + ": ");
				System.out.println(temp.toString());
				InfoLine.append(Rank).append(": ");
				InfoLine.append(temp.toString());
				InfoLine.append("\r\n");
				fw.write(InfoLine.toString());
				Rank++;
			}
			System.out.println();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
