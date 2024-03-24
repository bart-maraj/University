/*	Student: Bartlomiej Maraj 
 * 	Subject: Algorithms and Complexity 2020
	
	********************** Problem 3 **************************

	There is a vector V formed by n data, from which we want to find the minimum element
	of the vector and the maximum element of the vector. The type of data that is in the vector
	is not relevant to the problem, but the comparison between two data to see which of them
	is lower is very expensive, so the algorithm for the search of the minimum and the
	maximum should make the least amount of comparisons between possible elements.
	
	A trivial method consists of a linear path of the vector to search for the maximum and
	then another path to search for the minimum, which requires a total of approximately 2n
	comparisons between data. This method is not fast enough, so it is requested to implement
	a method with voracious methodology that makes a maximum of 𝟑/𝟐 n comparisons. 
	
	********************** Explanation **************************
	
	Given a vector we need to find the minimum and the máximum. In order to do that we use a 
	voracious algorithm where we keep dividing the array in 2 and find the maximimum and mínimum 
	of each subvector, to then compare them and take the highest maximum and the lowest minimum.
	
	As we were asked, the given algorithm makes a maximum of 3/2 n comparisons in the worst case.
 */



package week2;

public class problem3 {  

// Class Pair is used to return two values from getMinMax()

    static class Pair {  
        int min;  
        int max;  
    }  

//	Main function, code to test the algorithm  
    public static void main(String args[]) 
    {  
       int vector[] = {1000, 11, -30, 11, 4333, 88888,50};  

       int vect_size = 6;  

       Pair solution = getMinMax(vector, 0, vect_size - 1);  

       System.out.println("\nVector: "); 
       for(int i = 0; i < vector.length; i++) 
       {
       	System.out.print(vector[i] + " ");
       }
       
       System.out.printf("\nMinimum element is %d", solution.min);  

       System.out.printf("\nMaximum element is %d", solution.max);  

       System.out.print(" ");
    }  
    
    static Pair getMinMax(int arr[], int low, int high) 
    {  
    	Pair subvector = new Pair();  
        Pair leftsubvector = new Pair();  
        Pair rightsubvector = new Pair();  
        int mid;  
   
//		If there is 1 element in the vector 
        
        if (low == high) 
        {  
        	subvector.max = arr[low];  
        	subvector.min = arr[low];  
            return subvector; 
        }  

//		If there are 2 elements in the vector 

        if (high == low + 1) 
        {  
            if (arr[low] > arr[high]) 
            {  
            	subvector.max = arr[low];  
            	subvector.min = arr[high];  
            } 
            else 
            {  
            	subvector.max = arr[high];  
            	subvector.min = arr[low];  
            }  
            return subvector;  
        }  

//		If there are more than 2 elements in the vector 
        mid = (low + high) / 2;  
        leftsubvector = getMinMax(arr, low, mid);  
        rightsubvector = getMinMax(arr, mid + 1, high);  
   
//		Compare minimums of two subvectors 
        if (leftsubvector.min < rightsubvector.min) 
        {  
        	subvector.min = leftsubvector.min;  
        } 
        else 
        {  
        	subvector.min = rightsubvector.min;  
        }  

//      Compare maximums of two subvectors 
        if (leftsubvector.max > rightsubvector.max) 
        {  
        	subvector.max = leftsubvector.max;  
        } 
        else 
        {  
        	subvector.max = rightsubvector.max;  
        }  
        return subvector;  
    }  
} 