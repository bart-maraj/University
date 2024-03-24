/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 2 **************************
	Calculate the average grade of a student in a course consisting of n subjects using the
	divide and conquer technique, with n being a power of 2. Modify the algorithm to allow
	values of n that are not a power of 2. Calculate efficiency and determine if it improves
	the that is obtained with respect to a simple iterative version.

	Delivery requirements:
	• Deliver a report in which the solution is explained, justifying why the
	implementation has been chosen.
	• Source code.
	• Input file ‘example_d&c.txt’
	• Executable that can be tested by inserting the example file, “example_d&c.txt”,
	and returns an output file with the average value obtained. The average value
	obtained in all recursive stages of the divide and conquer process must be shown.
	
	The executable must work in any Windows environment. The example file must
	be read from the same folder where the executable is located.
 */

package examProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeProblem2_aux {
	public static void problem2(String path) throws IOException 
	{
    
		File file = new File(path); 
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		FileWriter fw = new FileWriter("output_d&c.txt"); 
	  
		String st; 
		st = br.readLine();
		int subjects = Character.getNumericValue(st.charAt(0));
		
		int[] grades = new int[subjects];
		int i = 0;
		
		while ((st = br.readLine()) != null) 
		{
			grades[i] = Integer.parseInt(st);
			i++;
		}
		
		fw.write("Final average value: " + averageDC(grades, 0, subjects - 1, fw)/subjects + "\n");  // averageDC return the sum of all the grades, it is divided by the number of subjects
		fw.close(); 	// The output file is closed
	}



	public static double averageDC(int[] grades, int l, int r, FileWriter fw) throws IOException 
	{	
		//Empty Case
		if (l > r)
		{
		    return 0;
		}
		// Base Case: if there's just a single element it is the average of the array itself
		if (l==r) 
		{
			
		    return grades[l];
		}
		

		int mid = (l+r)/2;
		double avgLeft = 0;
		double avgRight = 0;

		avgLeft = averageDC(grades, l, mid, fw);		// Divide and Conquer, right side recursive call
		avgRight = averageDC(grades, mid+1, r, fw);		// Divide and Conquer, right side recursive call
	
		double average = avgLeft + avgRight; // Sum of grades, division performed later

		return average; // Returns the sum of all the grades which will be later divided by the number of subjects
	}
}
