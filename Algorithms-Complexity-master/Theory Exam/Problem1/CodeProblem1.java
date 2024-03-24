/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 1 **************************

	In the management of the social network ThinksThat (www.thinksthat.com, where you
	can hear what your contacts think) they want to determine the degree of connection
	between their users. The degree of connection is the number of user groups connected to
	each other divided by the total number of users. If this value is 1, it means that the users
	are isolated and do not have common contacts, as the lower is the value the degree of
	connection of the users is higher.

	Among the thousands of ThinksThat users, a random population of one hundred
	identifiers is chosen to obtain their degree of connection. Each of these hundred identifiers
	represents a user, whose contacts are also stored in a list. For example, you could have
	the following case of six users (in italics) and their contacts (separated by commas):
	
	
	Although Emma only has Bea as contact, through her she is related to Carlos and Antonio.
	On the other hand, Fernando and David also have a connection between them (David has

	Fernando among his contacts, although the reciprocal is not met). However, the two
	groups have no common links.

	In this example the degree of connection would be 2 groups divided by 6 users,
	approximately the value 0'33.

	Design a voracious algorithm that, having as data the chosen users (each of them with
	their identifier and the list of their contacts), obtain the degree of connection between
	them.

 */


package examProblems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Java program to print connected components in 
// an undirected graph 
import java.util.LinkedList; 
class CodeProblem1 { 
	// Use of a graph, an array of adjacency lists. 
	// Size of array will be U (number of users in graph)
	
	int U; 
	LinkedList<Integer>[] adjListArray; 
	
	// constructor 
	@SuppressWarnings("unchecked")
	CodeProblem1(int Users) { 
		this.U = Users; 
		// Define the size of array as number of users 
		adjListArray = new LinkedList[U]; 

		// Create a new list for each user where connected users will be stored 

		for(int i = 0; i < U ; i++){ 
			adjListArray[i] = new LinkedList<Integer>(); 
		} 
	} 
	
	
	// Adds an user to the  graph 
	void addUser( int src, int dest) { 
		// Add an edge from src to dest. 
		adjListArray[src].add(dest); 
	} 
	
	void DFSUtil(int u, boolean[] visited,FileWriter fw) throws IOException { 
		// Mark the current user as visited
		visited[u] = true; 
		// Write the current user in the current group in the output file
		fw.write(u + " "); 
		// Recur for all the users connected to this user 
		for (int x : adjListArray[u]) { 
			if(!visited[x]) DFSUtil(x,visited,fw); 
		} 

	} 
	int getGroups(FileWriter fw) throws IOException { 
		// Mark all the users as not visited 
		boolean[] visited = new boolean[U]; 
		int groups = 0;
		for(int u = 0; u < U; ++u) { 
			if(!visited[u]) { 
				//A new group is formed, write it in the output file.
				fw.write("Members of group " + (groups+1) + " = "); 
				// Find all the users connected to user "u".
				DFSUtil(u,visited,fw); 
				// All these connected users form the new group
				fw.write("\n"); 
				groups++;
			} 
		} 
		return groups;
		
	} 
	
	
	// Code to test the algorithm
	public static void problem1(String path) throws IOException{ 
		File file = new File(path); 
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		FileWriter fw = new FileWriter("output_voracious.txt");  		// Create the output file
	  
		String st; 
		st = br.readLine();
		int totalUsers = Character.getNumericValue(st.charAt(0));

		// Create a graph with "totalUsers" users numbered from 0 to "totalUsers - 1"
		CodeProblem1 graph = new CodeProblem1(totalUsers); 
		
		int src, dest;
		while ((st = br.readLine()) != null) {
			src = Character.getNumericValue(st.charAt(0));
			dest = Character.getNumericValue(st.charAt(1));
			graph.addUser(src, dest); 
		}
		
		int groups = graph.getGroups(fw); 
		
		// We need the degree with decimals, we work with double
		double connectionDegree = (double)groups/totalUsers;
		
		fw.write("Number of groups: " + groups + "\n"); 
		fw.write("Degree of connection: " + groups + "/" + totalUsers + " = " + connectionDegree);
		fw.close(); 	// The output file is closed
		
	} 

}	 
