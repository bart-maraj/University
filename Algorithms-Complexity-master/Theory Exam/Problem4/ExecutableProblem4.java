package examProblems;

import java.io.IOException;
import java.util.Scanner;

public class ExecutableProblem4 {
	public static void main(String[] args) throws IOException{ 
		System.out.println("Insert the path of the “example_backtracking.txt” or “example_backtracking2.txt” file: ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String path = in.nextLine();
		CodeProblem4.problem4(path);	// Run problem 4 with the data taken from the file in the given path
	} 
}
