package mad.Sorts.BucketSort;

import java.util.Arrays;

public class BucketSort implements Runnable{
	
	private int[] toBeSorted;
	private int[] sorted;
	private int[][] bucket;
	private int[] bucketSize;
	private VisualizerFrame frame;
	
	public BucketSort(int[] toBeSorted, VisualizerFrame frame) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
		this.bucket = new int[10][toBeSorted.length];
		this.bucketSize = new int[10];
		this.sorted = new int[toBeSorted.length];
	}
	
	public void run() {
		sort();
		BucketSortingVisualizer.isSorting=false;
	}

	public int hash(int num, int bucketSize) {  
		return (int) Math.floor(num/bucketSize);
	} 
	
	public void sort() {
		// Add to bucket
		for(int i = 0; i < toBeSorted.length; i++){
			
			bucket[hash(toBeSorted[i], 10)][bucketSize[hash(toBeSorted[i], 10)]] = toBeSorted[i];
			bucketSize[hash(toBeSorted[i], 10)]++;
			
			frame.reDrawArray(toBeSorted, i, bucket, bucketSize, hash(toBeSorted[i], 10), -1);

			try {
				Thread.sleep(BucketSortingVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Sort each bucket
		for(int i = 0; i < 10; i++) {
			Arrays.sort(bucket[i], 0, bucketSize[i]);
			frame.reDrawArray(toBeSorted, -1, bucket, bucketSize, i, -1);

			try {
				Thread.sleep(BucketSortingVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Write to new Array
		int k = 0;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < bucketSize[i]; j++) {

				sorted[k] = bucket[i][j];
				frame.reDrawArray(sorted, k, bucket, bucketSize, i, j);
				
				k++;
						
				try {
					Thread.sleep(BucketSortingVisualizer.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
