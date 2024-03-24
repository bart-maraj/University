/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 5 **************************

	We have a directed graph G = <N, A>, where N = {1, ..., n} is the set of nodes and A ⊆
	NxN is the set of edges. Each edge (i, j) ∈ A has an associated cost cij (cij > 0 ∀i, j ∈ N; if
	(i, j) ∉ A can be considered cij = + ∞). Let M be the cost matrix of the graph G, that is, M
	[i, j] = cij.

	Taking as data the number of nodes n and the cost matrix M, it is requested to find both
	the minimum path between nodes 1 and n and the length of said path using the Dijkstra
	algorithm, using the following ideas:
		• Create a data structure that stores the known temporal distances (initialized at the
		cost of the edge of the vertex 1 to each vertex j, or + ∞ if that edge does not exist)
		for the vertices not traveled (initially, all but 1).
		• Select as candidate the one with the least known temporal distance, eliminate it
		from the set of vertices not traveled, and update the rest of the temporal distances
		if they can be improved using the current vertex.
		• It will be necessary to store the way of traversing the graph from vertex 1 to vertex
		n (not necessarily equal to the set of decisions taken). 
	
	********************** Explanation **************************
	
	Given a graph, the number of nodes and the source node we use the Dijkstra algorithm to find 
	the mínimum path of between the nodes and the source node. The distances are stored in an array. 
	Another array is created (isPath) which keeps track of the nodes whose mínimum distance to soruce 
	is calculated. We update this array with the next node not included in isPath with the minimum 
	distance and we update all the other distances taking into account the last node´s distance.
	
 */


package week2; 

class problem5 {  
	
//	Main function, code to test the algorithm  
    public static void main(String[] args)  
    {   
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },  

                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },  

                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },  

                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },  

                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },  

                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },  

                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },  

                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },  

                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };  

        problem5 t = new problem5();  
        int n = 9; // number of nodes
        int sourceNode = 1; // from 1 to n
        t.dijkstra(graph,sourceNode - 1,n); 
    }  
    
	// Finds the minimum distance value from the set of nodes not yet included in shortest path tree  
    int minDistance(int dist[], Boolean isPath[],int n)  
    {  
        // Initialize min value  
        int min = Integer.MAX_VALUE;
        int min_index = -1;  

        for (int v = 0; v < n; v++)
        {
        	if (isPath[v] == false && dist[v] <= min) 
        	{  
                min = dist[v];  
                min_index = v;  
            }  	
        }
        return min_index;  
    }   

    void dijkstra(int graph[][], int src, int n)  
    {  
    	// "distance" contains the minimum distances from the source node to the other ones.
        int distance[] = new int[n]; 
        // isPath[i] returns true if the node is included in shortest path tree or shortest distance from source to node is finalized
        Boolean isPath[] = new Boolean[n];
        // Initialize all distances as INFINITE and isPath[] as false
        for (int i = 0; i < n; i++) 
        {  
        	distance[i] = Integer.MAX_VALUE;  
        	isPath[i] = false;  
        }   
        // Distance from source node from itself is always 0  
        distance[src] = 0;  
        // Find shortest path for all nodes  
        for (int count = 0; count < n - 1; count++) 
        {  
            // Pick the minimum distance node from the set of nodes not yet processed.  
            int j = minDistance(distance, isPath,n);  
            // Mark the picked node as processed  
            isPath[j] = true;  
            // Update distance value of the adjacent vertices of the picked node.  
            for (int v = 0; v < n; v++)
            {
            	if (!isPath[v] && graph[j][v] != 0 && distance[j] != Integer.MAX_VALUE && distance[j] + graph[j][v] < distance[v])  
            	{
            		distance[v] = distance[j] + graph[j][v];	
            	}     	
            }
            // Update distance[v] if is not in isPath, there is an edge from j to v(not 0), and the total path from src to v through j is smaller than current value of distance[v]  
        }  
        // Function to print the distances
        printSolution(distance,src+1,n);  
    }  

 
    
    void printSolution(int distance[],int src,int n)  
    {  
        System.out.println("Source node: " + src );  
        for (int i = 0; i < n; i++)
        {
        	int node = i + 1;
        	System.out.println("Distance from node " + src + " to node " + node + " = " + distance[i]);	
        } 
    }  
} 