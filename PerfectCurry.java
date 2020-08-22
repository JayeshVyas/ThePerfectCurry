import java.util.Arrays;
import java.util.Scanner;

public class PerfectCurry
{
	// function for checking that selected elements add up to target
	static boolean add_target(int[] a, int selection, int target)
	{
		//System.out.println("selection--"+selection);
		int sum = 0;
		for(int i=0;i<a.length;i++)
		{
			//System.out.println("selection = "+i+(selection>>i));
			if(((selection>>i) & 1) == 1)
			{
				sum += a[i];
				//System.out.println("sum in if"+sum);
			}
			//System.out.println("sum=="+sum);
		}
		//System.out.println("selection after"+selection);
		return sum==target;
	}

	// Remove the selected elements
	static int[] removeElement(int[] a, int selection)
	{
		int[] res = new int[a.length];
		int j = 0;
		for(int i=0;i<a.length;i++)
		{
			if(((selection>>i) & 1) == 0)
				res[j++] = a[i];
		}
		return Arrays.copyOf(res, j);
	}

	static String makeCurry(int[] a)
	{
		int sum = 0;
		for(int x : a)
			sum += x;
		//System.out.println("Sum=="+sum);
		if(sum%3 > 0)
		{
			//System.out.println("sum%3"+sum%3);
			return "noLuck";
		}
		int target = sum/3;
		int max1 = 1<<a.length; // 2^length
		//System.out.println("Max1=="+max1);
		for(int i=0;i<max1;i++)
		{
			if(add_target(a, i, target))//condition to satisfy the sum with target 
			{
				int[] b = removeElement(a, i);
				int max2 = 1<<b.length; // making 2^length
				//System.out.println("max2 with power"+max2);
				for(int j=0;j<max2;j++)
				{
					if(add_target(b, j, target))
						return create_Solution(i, j, a.length);
				}
			}
		}
		return "noLuck";
	}
	
	//function to make solution with Strings value 'P','Q','R'
	static String create_Solution(int p, int q, int len)
	{
		char[] res = new char[len];
		Arrays.fill(res, 'R');
		int j = 0;
		for(int i=0;i<len;i++)
		{
			if(((p>>i) & 1) == 1)
				res[i] = 'P';
			else
			{
				if(((q>>j) & 1) == 1)
					res[i] = 'Q';
				j++;
			}
		}
		return new String(res);
	}

	public static void main(String[] args)
	{
		int N; // for N inputs for array A
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        int A[] = new int[N];
        for(int i = 0; i < N; i++)
        {
            A[i] = s.nextInt();
        }
        
		System.out.println(makeCurry(A));
	}
}
