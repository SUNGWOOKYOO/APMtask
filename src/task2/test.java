package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class test
{
	/*
	public static void main(String[] args)
	{
		// ����Ʈ ������ �����б�
		String filePath = "/home/usaywook/FileRead/height.txt"; // ��� ����
		
		File file = null; // ���� ��Ʈ��
		FileReader in_file = null;
		BufferedReader fileStream = null;
		
		try
		{
			file = new File(filePath);// ���� ��Ʈ�� ����
			in_file = new FileReader(file);
			fileStream = new BufferedReader(in_file); 
			double [][]values = null;
			int values_length = 0;
			if(new File(filePath).exists()) {
				String temp = (new BufferedReader(new FileReader(new File(filePath)))).readLine();
				values_length = temp.split("\t").length;	
			}
			values = new double[values_length][values_length];
			if (file.exists()) {
				int i = 0;
				for (String str = fileStream.readLine(); str != null; str = fileStream.readLine()) {
					System.out.println(str);
					String []vector = str.split("\t");
					//System.out.println("Debug : "+ vector.length); // in file, there must be no addition space
					for (int j=0; j<vector.length; j++) {
						values[i][j] = Double.parseDouble(vector[j]);
						System.out.println(i +","+j +" : " + values[i][j]);
					}
					i++;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("���� ����� ����!!" + e);
		}
		finally
		{
			try
			{
				fileStream.close();// ���� �ݱ�. ���⿡�� try/catch�� �ʿ��ϴ�.
			}
			catch (Exception e)
			{
				System.out.println("�ݱ� ����" + e);
			}
		}
	}
	*/
}