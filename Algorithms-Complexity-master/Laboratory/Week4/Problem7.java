/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 7 **************************
	
	A sequence of bits A is defined as a sequence A={a1, a2,..., an} where each ai can take the
	value 0 or the value 1, and n is the length of the sequence A. From a sequence it is defined
	a subsequence X of A as X = {x1, x2,..., xk}, where kn, so that X can be obtained by
	eliminating some element of A but respecting the order in which the bits appear; for
	example, if A={1,0,1,1,0,0,1} we could obtain as subsequences {1,1,1,0,1}, {1,0,1} or
	{1,1,0,0} among others, but you could never get the subsequence {1,0,0,1,1}.
	Given two sequences A and B, X is called a common subsequence of A and B when X is
	a subsequence of A and is also a subsequence of B. (although they may have been
	obtained by removing different elements in A than B, and even different quantities of
	elements). Assuming the sequences A = {0,1,1,0,1,0,1,0} and B = {1,0,1,0,0,1,0,0,1}, a
	common subsequence would be X = {1,1,0,1}, but it could not be X = {0,1,1,1,0}.

	We want to determine the common subsequence of two sequences A and B that have the
	maximum length, for which it is requested
	• explain in detail how to solve the problem, and
	• make a Dynamic Programming algorithm that obtains the maximum possible length and a common 
	sequence of that length
	
	
	********************** Explanation **************************
	
	In order to solve the problem we have used Dynamic Programming, because generating all subsequences
	of both given sequences and find the longest matching subsequence is exponential in terms of time 
	complexity.
	
	In this problem we encounter 2 properties: Optimal Substructure Property and Overlapping Substructure property.
	
	Let the input sequences be A[0..m-1] and B[0..n-1] of lengths m and n respectively.If last characters of both sequences match 
	(or A[m-1] == B[n-1]) then LS(A[0..m-1], B[0..n-1]) = 1 + LS(A[0..m-2], B[0..n-2]). If last characters of both sequences do not 
	match (or A[m-1] != B[n-1]) then LS(A[0..m-1], B[0..n-1]) = max( LS(X[0..m-2], Y[0..n-1]), LS(A[0..m-1], B[0..n-2])).We can see
	that the common subsequence problem  has Optimal Substructure property, so the main problem can be solved using optimal solutions to subproblems.

	While resolving this problem we saw that there were many subproblems which were solved repeteadly, which indicated the Overlapping Substructure property.
	(we will be using Tabulation).
	
	
	In order to obtain and print the longest common subsequence, we first constructed the L[m+1][n+1] table. As the  value LS[m][n] contains the length of subsequence
	we created a character array lcs[lcs length + 1] with one extra slot to store \0. After that we went through the array where every cell was studied to be
	part of the subsequence. If the characters corresponding to LS[i][j] where the same (Or A[i-1] == B[j-1]), then it wsa included as part of the subsequence, 
	if not, we compared the values of LS[i-1][j] and LS[i][j-1] and followed the greater value.
	
	Like that we obtained the longest common subsequence of two given sequences.
	
	
	
 */

package week4;


class Problem7 
{ 
//	Main function, code to test the algorithm  
	public static void main (String[] args) 
	{ 
		String A = "01101010"; 	//First sequence
		String B = "101001001"; //Second sequence
		int m = A.length(); 	//Lenght of first subsequence
		int n = B.length(); 	//Lenght of second subsequence
		longestCommonSubsequence(A, B, m, n); 
	} 
	
	static void longestCommonSubsequence(String A, String B, int m, int n) 
	{ 
		int[][] LS = new int[m+1][n+1]; 	//Create the LS[m+1][n+1] table (returns the lenght of the subsequence).

		// Build of LS[m+1][n+1] in bottom up fashion.
		for (int i = 0; i <= m; i++) 
		{ 
			for (int j = 0; j <= n; j++) 
			{ 
				if (i == 0 || j == 0)  	// First row and first column
				{
					LS[i][j] = 0; 	
				}
				else if (A.charAt(i-1) == B.charAt(j-1))   // Both characters are equal
				{
					LS[i][j] = LS[i-1][j-1] + 1; 
				}
				else
				{
					LS[i][j] = Math.max(LS[i-1][j], LS[i][j-1]); 
				}
			} 
		} 

		int index = LS[m][n]; 
		int temp = index; 

		char[] lcs = new char[index+1]; 	// "lcs[]" stores the longest common subsequence.
		lcs[index] = ' '; 					

		// Starting from the right-most-bottom-most corner. Each character is found by studying and comparing the LS.
		// These are stored in lcs[], the longest common sequence
		int i = m; 
		int j = n; 
		while (i > 0 && j > 0) 
		{ 
			if (A.charAt(i-1) == B.charAt(j-1)) // Current character is part of LCS.
			{ 
				
				lcs[index-1] = A.charAt(i-1); // Current character is the result 
				
				i--; 
				j--; 
				index--;	 
			} 

			
			else if (LS[i-1][j] > LS[i][j-1]) // Find and follow the greater value.
			{
				i--; 
			}
			
			else
			{
				j--; 
			}
		} 

		for(int k=0;k<=temp;k++)
		{
			System.out.print(lcs[k]); // Print the solution 
		}	
	} 
} 

