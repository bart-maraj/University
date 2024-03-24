/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 4 **************************

	Ali Baba has managed to enter the cave of the one hundred and one thousand thieves, and
	has brought his camel along with two large panniers. The problem is that he finds so much
	treasure that he does not know what to take. The treasures are carved jewels, works of art,
	ceramics ... that is, they are unique objects that cannot be split since then their value would
	be reduced to zero. 
	
	Fortunately the thieves have everything very well organized and find a list of all the
	treasures in the cave, which reflects the weight of each piece and its value in the Damascus
	market. For his part, Ali knows the weight capacity of each of the saddlebags.
	
	Design an algorithm that, taking as data the weights and value of the pieces, and the
	capacity of the two saddlebags, allows to obtain the maximum benefit that Ali Baba can
	get from the cave of wonders. 
	
	********************** Explanation **************************
	
	In order to perform this problem, we will use dynamic programming. 
	To solve the problem by dynamic programming, we prepare a table bag[1..n,0..W] that has
	a row for each available treasure, and a column for each weight from 0 to "capacity".  In the table,
	bag[i ,j] will be the maximum value of the objects that we can transport if the weight limit
	is j, with 0≤ j ≤ capacity, and if we only include the objects numbered from 1 to i, with 1 ≤ i ≤n.
	The solution to this case can be found in V[n,W].
	We will fill the entries in the table using the general rule: bag[i, j] max(bag[i 1, j],bag[i 1, j w ] v ).
	
	Once we obtain the values for 1 bag, we delete the treasures used and then obtain the values for the next one.
	We are only working with the treasure that is left (the treasure that is not in the first bag).
 */


package week4;


class Problem4 { 

//	Main function, code to test the algorithm  
	public static void main(String args[]) 
	{ 
		int value[] = new int[] { 60, 100, 120, 140, 210 }; 
		int weight[] = new int[] { 10, 20, 30, 40, 50 }; 
		int capacity = 50; 
		int n = value.length; 
		System.out.print("First bag\n"); 
		
		int[] firstBag = fillBag(capacity, weight, value, n); //First bag
		
		//Code to delete the used treasures.		
		int lw = weight.length;
		for(int j = 0; j < firstBag.length;j++)
		{
			for(int i = 0; i < lw; i++){
	            if(weight[i] == firstBag[j]){
	                for(int t = i; t < lw; t++)
	                {
	                	value[t] = value[t+1];
	                	weight[t] = weight[t+1];
	                	lw--;
	                }
	                break;
	            }
	        }
		}
		
		System.out.print("\nSecond bag\n"); 
		fillBag(capacity, weight, value, n); //Second bag
	} 
	
	// Function that returns maximum of two integers 
	static int max(int a, int b) 
	{ 
		if (a > b) 
		{
		  return a;
		}
		else 
		{
		  return b;
		}
	} 

	// Returns the maximum value that can be put in the bag at bag[n][capacity]
	static int[] fillBag(int capacity, int weight[], int value[], int n) 
	{ 
		int i, w; 
		int bag[][] = new int[n + 1][capacity + 1]; 

		
		for (i = 0; i <= n; i++) 
		{ 
			for (w = 0; w <= capacity; w++) 
			{ 
				if (i == 0 || w == 0) // First row and first column
				{ 
					bag[i][w] = 0; 
				}
				else if (weight[i - 1] <= w) // Fill the entries in the table using the general rule
				{
					bag[i][w] = max( value[i - 1] + bag[i - 1][w - weight[i - 1]], bag[i - 1][w]); 
				}
				else
					bag[i][w] = bag[i - 1][w]; 
			} 
		} 
		
		int bagValue = bag[n][capacity];
		System.out.print("Value: " + bagValue);
		System.out.print("\nContent: " );
		
		int[] bagTreasures = new int [n];
		int index = 0;
		
		w = capacity; 
		for (i = n; i > 0 && bagValue > 0; i--) { 
			// If the item comes from (value[i-1] + bag[i-1][w-weight[i-1]]),the item is included.
			// The only other possibility is the item comes from bagValue == bag[i - 1][w].
			
			if (bagValue != bag[i - 1][w]) 
			{ 

				// Item is included. 
				System.out.print(weight[i - 1] + " "); 
				
				bagTreasures[index] = weight[i - 1];
				index++;

				// Weight is included and value is deducted 
				bagValue = bagValue - value[i - 1]; 
				w = w - weight[i - 1]; 
			} 
		} 

		return bagTreasures; // We obtain the weights with the highest values within the given capacity 
	} 
} 
