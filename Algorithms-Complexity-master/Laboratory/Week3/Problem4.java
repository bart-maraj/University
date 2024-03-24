/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 4 **************************

	You want to program a robot to put cork stoppers to the bottles of a recycling factory.
	There are available N bottles and the N corks that cover them (N is constant in the
	problem), but there are a number of problems:
 	- The bottles are all different from each other, just like corks: each bottle can only
	be closed with a concrete cork, and each cork only serves for a specific bottle.
	- The robot is prepared to close bottles, so all he knows how to do is compare corks
	with bottles. The robot can detect if a cork is too small, too large, or just the right
	size to close a bottle.
	- The robot can not compare corks with each other to "sort" them by thickness, nor
	can it do so with the bottles.
	- The robot has space available and mechanical arms to place bottles and corks at
	will, for example in different positions of a table, if necessary.
	Design the algorithm that the robot needs to plug N bottles optimally. 
	
	********************** Explanation **************************
	
	Given an array of bottles and an array of corks we tell the user if the corks where plugged correctly. 
	In order to do it, we do a binary search with each cork over the array of bottles (we can easily chenge
	it to a true or false function if needed).
	As we were asked the algorithm allows the robot ot plug the bottles optimally.
	
 */


package week3;

class Problem4 { 
	
//	Main function, code to test the algorithm  
    public static void main(String args[]) 
    { 
    	Problem4 ob = new Problem4(); 
        int bottles[] = { 1, 2, 3, 4, 5 };
        int corks[] = { 4, 2, 1, 5, 3};
        int n = bottles.length; 
        System.out.println("Bottles: "); 
        for(int i = 0; i < bottles.length; i++) 
        {
        	System.out.print(bottles[i]+ " ");
        }
        System.out.println("\nCorks: "); 
        for(int i = 0; i < corks.length; i++) 
        {
        	System.out.print(corks[i] + " ");
        }
        System.out.println(" ");
        for(int i = 0; i < corks.length; i++) 
        {
        	int result = ob.binarySearch(bottles, 0, n - 1, corks[i]); 
        	if (result == -1) 
        		System.out.println("No bottle for current cork: " + corks[i]); 
        	else
        		System.out.println("Bottle closed at index " + result); 
        }
    }

    int binarySearch(int arr[], int l, int r, int x) 
    { 
        if (r >= l) 
        { 
            int m = l + (r - l) / 2; 
  
            // If the element is the middle itself 
            if (arr[m] == x) 
                return m; 
  
            // If element is smaller than mid  it can only be present in left subarray 
            if (arr[m] > x) 
                return binarySearch(arr, l, m - 1, x); 
  
            // Else the element can only be present  in right subarray 
            return binarySearch(arr, m + 1, r, x); 
        } 
  
        // The element is not present in the array 
        return -1; 
    }  
} 
