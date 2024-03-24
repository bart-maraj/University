/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 6 **************************

	Shrek, Donkey and Dragona arrive at the foot of Lord Farquaad's towering castle to free
	Fiona from her confinement. As they suspected that the drawbridge would be guarded by
	numerous soldiers, many stairs have been brought, of different heights, with the hope that
	some of them will allow them to overcome the wall; but no stairs serve them because the
	wall is very high. Shrek realizes that if he could combine all the stairs into one, he would
	be able to get to the top exactly and be able to enter the castle.
	
	Fortunately the stairs are made of iron, so with the help of Dragona they go to "weld"
	them. Dragona can weld any two stairs with her fire breath, but it takes to heat the
	extremes as many minutes as meters add up the stairs to be welded. For example, welding 
	6 and 8 meters of stairs would take 6+8=14 minutes. If this ladder was then welded to a
	7-meter ladder, the new time would be 14 +7=21 minutes, so it would have taken a total
	of 14+21=35 minutes to complete the ladder.

	Design an efficient algorithm that finds the best cost and way to weld the stairs so that
	Shrek takes as little time as possible to scale the wall, indicating the chosen data structures
	and their way of use. It can be assumed that exactly the stairs necessary to climb the wall
	are available (neither left nor missing), that is, the data of the problem is the collection of
	measurements of the "mini-ladders" (in the data structure chosen), and that only the
	optimal way of melting the stairs is looked for. 

	
	********************** Explanation **************************
	
	In order to perform the exercise we used the priority queue, through which we obtained a heap. With
	this data structure we can optimally perform the exercise with the complexity of O(nlogn).
	
	In order to obtain the best cost we just needed to start welding the smallest stairs. In order to do
	that we took advantage of the priority queue data structure. We just needed to add the 2 smallest elements,
	remove them from the queue and then add the sum of both to the queue. Finally we would obtain the cost.
	
	The smallest element was obtained with the peek() function and to remove an element we used the remove() 
	function. With both we achieved a  O(logn) complexity. However, we also went through the priority queue,
	O(n) complexity. Like that we have the complexity of O(nlogn).
	
 */

package week2;
 
import java.util.*; 

class problem6 { 
	
//	Main function, code to test the algorithm  
	public static void main(String args[]) 
	{ 
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(); // Create empty priority queue 
		
		pQueue.add(6); // We will add the ladders to the  priority queue. 
		pQueue.add(7);
		pQueue.add(8); 
		pQueue.add(9);
		
		System.out.println(sumladders(pQueue)); //Print the total time needed 
	} 
	
	public static int sumladders(PriorityQueue<Integer> pQueue) // Returns the total time invested in the ladders
    {
		int sum = pQueue.peek(); 			// Obtain the peek element of PriorityQueue (smallest one)
	    pQueue.remove(pQueue.peek());
	    sum = sum +  pQueue.peek(); 		// Obtain the second smallest element of PriorityQueue as the first one was deleted
	    pQueue.remove(pQueue.peek());
	       
	    int aux = sum;
	       
	    Iterator<Integer> it = pQueue.iterator(); 
	    while (it.hasNext()) 
	    {  
	    	 pQueue.add(sum);
	    	 sum = sum + pQueue.peek(); 	// Add to the sum the new ladder obtaining the new time needed
	    	 sum = sum +  aux; 				// Total time = new time needed which was just obtained + old time (aux).
	         pQueue.remove(pQueue.peek());
	         pQueue.remove(aux);
	         aux = sum; 
	    }
		
		
	    return sum;							// Total time
    }
} 
