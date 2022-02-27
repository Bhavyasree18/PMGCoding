import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFilesMerger{
	public static void main(String[] args) {
		if(args.length <2)  // Check atleast two files to merge
		{
		    // System.out.println("Atleast two files need to be passed as argumwnts");
			throw new IllegalArgumentException("Atleast two files need to be passed as arguments");
		}
		List<Path> paths = new ArrayList<>();
		for(String arg : args){
			if(Files.exists(Paths.get(arg)) && Paths.get(arg).getFileName().toString().contains(".csv")) { // Check whether the file exists in the path and also file format should be .csv
				paths.add(Paths.get(arg)); 
			}else {
				throw new IllegalArgumentException(arg + "Atleast two files need to be passed as arguments");
				// System.out.println(arg + " : FILE NOT SUPPORTED");
			}
		}
		
		mergeFile(paths); // Calling mergeFile Function
	}

	private static void mergeFile(List<Path> paths) {
		
		try{
		    int count = 1;
		for(Path p: paths)
		{
		    BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8); // Buffer Reader declaration
		    String line = br.readLine();
		    if(count != 0){ // Insert Header only Once
		    	System.out.println(line +", filename"+System.lineSeparator());  // writing in to file
		        count--;
		    }
		    while((line = br.readLine()) != null)  // Read Untill the End Line
		    {
		    	System.out.println(line+", "+p.getFileName()); // writing in to file
		    }
		    br.close();
		}
		}
		catch(IOException e){
		    System.out.println("Something is interrupted with the some error!!!!!");
		    e.printStackTrace();
		}
		
		return ;
		
	}
}