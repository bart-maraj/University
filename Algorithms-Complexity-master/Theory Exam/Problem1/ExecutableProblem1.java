package examProblems;

import java.io.IOException;
import java.util.Scanner;

public class ExecutableProblem1 {
	public static void main(String[] args) throws IOException{ 
		System.out.println("Insert the path of the “example_voracious.txt” file: ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String path = in.nextLine();
		CodeProblem1.problem1(path); // Run problem 1 with the data taken from the file in the given path
	} 
}
