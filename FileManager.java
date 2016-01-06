import java.io.*;
import java.util.Scanner;

/*To use this example program, run it in the same folder as a .csv file 
named Sheet1.csv */

public class FileManager {
	public static String readFile ()
	{
		String data = "";
		String pathname = "data.twentyquestions";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		while(input.hasNextLine())
		{
			data += input.nextLine();
		}
		input.close();
		
		return data;
	}
	
	public static void writeFile (String data) {
		String pathname = "data.twentyquestions";
		File file = new File(pathname);
		
		try {
			FileWriter writer = new FileWriter(file);
			
			writer.write(data);
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}



}
