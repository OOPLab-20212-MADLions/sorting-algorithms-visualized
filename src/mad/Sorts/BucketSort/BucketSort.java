package mad.Sorts.BucketSort;

import java.util.Arrays;

public class BucketSort implements Runnable{
	
	private int[] arr;
	private int[] sorted;
	private int[][] bucket;
	private int[] bucketSize;
	private VisualizerFrame frame;
	
	public BucketSort(int[] arr, VisualizerFrame frame) {
		this.arr = arr;
		this.frame = frame;
		this.bucket = new int[10][arr.length];
		this.bucketSize = new int[10];
		this.sorted = new int[arr.length];
	}
	
	public void run() {
		sort();
		BucketSortingVisualizer.isSorting=false;
		BucketSortingVisualizer.frame.size.setEnabled(true);
		BucketSortingVisualizer.frame.start.setText("Start");
	}

	public int hash(int num, int bucketSize) {  
		return (int) Math.floor(num/bucketSize);
	} 
	
	public void sort() {
		// Add to bucket
		for(int i = 0; i < arr.length; i++){
			
			bucket[hash(arr[i], 10)][bucketSize[hash(arr[i], 10)]] = arr[i];
			bucketSize[hash(arr[i], 10)]++;
			
			frame.arraySwitch(arr, i, bucket, bucketSize, hash(arr[i], 10), -1);

			try {
				Thread.sleep(BucketSortingVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Sort each bucket
		for(int i = 0; i < 10; i++) {
			Arrays.sort(bucket[i], 0, bucketSize[i]);
			frame.arraySwitch(arr, -1, bucket, bucketSize, i, -1);

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
				frame.arraySwitch(sorted, k, bucket, bucketSize, i, j);
				
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
