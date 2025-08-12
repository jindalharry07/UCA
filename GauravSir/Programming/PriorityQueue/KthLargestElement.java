import java.util.PriorityQueue;

public class KthLargestElement{
    
    public static int findkthLargest(int[]arr,int k){
	PriorityQueue<Integer>pq=new PriorityQueue<>();

	for(int ele:arr){
	    pq.add(ele);
	    if(pq.size()>k){
		pq.poll();
	    }
	}
	return pq.peek();
    }
    
    public static void main(String []arg){
        int []arr={3,2,1,5,6,4};
	int k=2;

	int kthLargest=findkthLargest(arr,k);
	System.out.println(k+" th largest element is "+kthLargest);
    }
}
