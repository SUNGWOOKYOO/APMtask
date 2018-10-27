package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class test
{
	public static void main(String[] args)
	{
		// 바이트 단위로 파일읽기
		String filePath = "D:\\FileRead\\HwData\\APM\\hw2_data\\height.txt"; // 대상 파일
		
		File file = null; // 파일 스트림
		FileReader in_file = null;
		BufferedReader fileStream = null;
		
		try
		{
			file = new File(filePath);// 파일 스트림 생성
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
			System.out.println("파일 입출력 에러!!" + e);
		}
		finally
		{
			try
			{
				fileStream.close();// 파일 닫기. 여기에도 try/catch가 필요하다.
			}
			catch (Exception e)
			{
				System.out.println("닫기 실패" + e);
			}
		}
	}
}