package middle;

// from the hint: 
// To generate the array, set nums[index] to target, nums[index-i] to target-i, and nums[index+i] to target-i. Then, this will give 
// the minimum possible sum, so check if the sum is less than or equal to maxSum.
// Binary search for the target. If it is possible, then move the lower bound up. Otherwise, move the upper bound down.

public class Leetcode1802 {
	
	public static  int maxValue(int n, int index, int maxSum) {
        if ( n == 1 )
            return maxSum;
        else if ( n == 2 )
        {
            if ( maxSum % 2 == 0 )
                return maxSum / 2;
            else 
                return 1 + (int) Math.floor( maxSum / 2 );
        }
        else
        {
            long low = 1;
            long high = (long) maxSum;
            long result = -1;
            while ( low <= high )
            {
                long mid = low + ( high - low )/2;
                long sum = left_sum(0, index, mid ) + right_sum( index+1 , n-1, mid-1);
                if( sum <= maxSum ) 
                {
                    result = mid;
                    low = mid +1;
                }
                else
                    high = mid - 1;
            }
            return (int)result;
        }
    }
	
	// calculate the sum in [start_index, end_index], where the element at end_index is val
    private static long left_sum( int start_index, int end_index, long val)
    {
        if(start_index == end_index)
            return val;
        else
        {
            long l = (long) ( end_index - start_index + 1 );
            // 2 cases:
            // if val >= length_of_interval, then the element at start_index is val-(l-1)
            // if val < length_of_interval, then the element at end_index-(val-1) is already 1.
            
            long result;
            if ( val >= l )
            {
                result = val + val - (l-1);
                result *= l;
                result /= 2;
            }
            else
            {
                result = ( val + 1 ) * val;
                result /= 2;
                result += (l-val);
            }
            return result;
            
        }
    }
    
 // similar to left_sum, the only diffenrence is we should consider the case 
    // start_index > right_index
    private static long right_sum( int start_index, int end_index, long val )
    {
        if(start_index > end_index)
            return 0;
        else if(start_index == end_index)
            return val;
        else
        {
            long l = (long) ( end_index - start_index + 1 );
            long result;
            if ( val >= l )
            {
                result = (val-(l-1)) + val;
                result *= l;
                result /= 2;
            }
            else
            {
                result = ( val + 1 ) * val;
                result /= 2;
                result += (l-val);
            }
            return result;
            
        }
    }
    
    public static void main( String[] args ) {
		long i = (465692470 + 465692472)*3;
		System.out.println( i );
		
		int j = 465692470 + 465692472;
		j *= 3;
		System.out.println( j );
		
		long k = 465692470 + 465692472;
		k *= 3;
		System.out.println( k );
		
		
		long val = maxValue( 6, 2, 931384943 );
		System.out.println( val );
	}
	
}
