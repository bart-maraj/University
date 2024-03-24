package examProblems;

import java.io.IOException;
import java.util.Scanner;

public class ExecutableProblem2 {
	public static void main(String[] args) throws IOException{ 
		System.out.println("Insert the path of the “example_d&c.txt” file: ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String path = in.nextLine();
		//CodeProblem2_powerof2.problem2(path);
		//CodeProblem2_aux.problem2(path);
		CodeProblem2.problem2(path);	// Run problem 2 with the data taken from the file in the given path
	} 
}
