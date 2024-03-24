/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 6 **************************

	After passing through the Tile Room and getting the Cradle of Life, now Indiana Croft
	faces a new challenge before being able to leave the Temple Cursed. It is located on a
	bridge under which there is deep darkness.
	
	Fortunately, this place also appears in the diary. The bridge crosses the so-called Valley
	of Shadows, which begins with a descent slope (the slope is not necessarily constant) so
	after reaching the lowest point start to climb to the other end of the bridge (again, not
	necessarily with constant slope). Just at the bottom of the valley there is a river, but the
	diary does not specify its location with respect to the bridge (for example, it is not known
	if the river is 53 meters from the beginning of the bridge) or the distance in height (ie , it
	is not known if the river is 228 meters below, for example). On the slopes there are sharp
	rocks.
	
	If Indiana Croft had time, he could easily find the point where he can get off the bridge
	to get exactly to the river, as it has a laser pointer to measure heights that tells him how
	many meters there are from the bridge to the ground at a certain point. The problem is
	that the priests of the temple have found out that they have stolen the Cradle of Life, they
	are chasing Indiana Croft and they will reach him right away. The adventurer must
	quickly find the position of the river to get off and flee safely. 

	Design the algorithm that Indiana Croft should use to find the minimum point of the valley
	under the indicated conditions. The algorithm must be efficient: at least in the best case it
	must have a logarithmic order. You can consider the time it takes for Indiana Croft to
	travel along the bridge is null and that the estimate of the point of the river where to drop
	off can have an approximation error of ε meters (ε is a given constant). 
	
	********************** Explanation **************************
	
	Given the bridge(values given by the function), we need to find the mínimum (deacreasing array, minimum, increasing array). 
	The mínimum is where the river is found. In order to find the minimum we do a divide and conquer algorithm when we 
	compare the middle element “m” with m + 1 and m – 1. If the bridge at m is lower than at m+1 and higher than at m-1,
	then the river lies at the left side of m. If not, it lies at the right side.
	 
 */

package week3;

class Problem6 
{    
//	Main function, code to test the algorithm  
    public static void main (String[] args)  
    { 
        int arr[] = {16,9,4,1,0,1,4,9,16}; // Values of x^2
        int n = arr.length; 
        System.out.println("Bridge: "); 
        for(int i = 0; i < arr.length; i++) 
        {
        	System.out.print(arr[i] + " ");
        }

        int river = findRiver(arr, 0, n-1) + 1;
        System.out.println("\nThe river is "+  river + " meters from the beginning of the bridge"); 
    } 
    
    static int findRiver(int bridge[], int start, int end) 
    {  
       // Lenght of the bridge is one
       if (start == end) 
         return start; 
       
       // Lenght of the bridge is two
       if ((end == start + 1))
       {
    	   // First element is the lowest
    	   if (bridge[start] <= bridge[end])
    		   return start;
    	   // Second element is the lowest
    	   if (bridge[start] > bridge[end]) 
    	       return end; 
       }
        
       //else
       int m = (start + end)/2;    
       
       // The bridge at m is lower than at m-1 and at m+1 then m is the lowest point (river)
       if ( bridge[m] < bridge[m + 1] && bridge[m] < bridge[m - 1]) 
          return m; 
       if ( bridge[m] < bridge[m + 1] && bridge[m] == bridge[m - 1]) 
           return m;
       if ( bridge[m] == bridge[m + 1] && bridge[m] < bridge[m - 1]) 
           return m;
       
       // The bridge at m is lower than at m+1 and higer at m-1 then m then the river lies at the left side of m 
       if (bridge[m] < bridge[m + 1] && bridge[m] > bridge[m - 1]) 
         return findRiver(bridge, start, m-1); 
       // If not the river lies at the right side of m 
       else 
         return findRiver(bridge, m + 1, end); 
    } 
} 
