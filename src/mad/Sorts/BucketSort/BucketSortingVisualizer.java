package mad.Sorts.BucketSort;

public class BucketSortingVisualizer {
	private static Thread 			sortingThread;	
	public 	static VisualizerFrame 	frame;
	public 	static int[] 			arr;
	public 	static boolean			isSorting = false;
	public 	static int 				numberOfElements = 20;
	public 	static int 				sleep = 20;

	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
	}

	public static void resetArray(){
		if (isSorting) return;
			arr = new int[numberOfElements];
		for (int i = 0; i < numberOfElements; i++){
			arr[i] = (int) (100 * Math.random());
		}
		frame.initArray(arr);
	}

	public static void startSort(){
		if (sortingThread == null || !isSorting){
			resetArray();
			isSorting = true;
			frame.start.setText("Running...");
			sortingThread = new Thread(new BucketSort(arr, frame));		
			sortingThread.start();	
		}		
	}		
}
