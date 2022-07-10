package mad.Sorts.BucketSort;

public class BucketSortingVisualizer {

	private static Thread sortingThread;

	public static VisualizerFrame frame;
	public static int[] toBeSorted;
	public static boolean isSorting = false;
	public static int sortDataCount = 20;
	public static int sleep = 20;

	// Stepped depicts whether the values are incremental or random. True is incremental.
	public static boolean stepped = false;

	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
		frame.setLocationRelativeTo(null);
	}

	public static void resetArray(){

		if (isSorting) return;
		toBeSorted = new int[sortDataCount];

		for(int i = 0; i<sortDataCount; i++){
				toBeSorted[i] = (int) (100*Math.random());
		}

		frame.preDrawArray(toBeSorted);
	}

	public static void startSort(){

		if (sortingThread == null || !isSorting){

			resetArray();

			isSorting = true;

				sortingThread = new Thread(new BucketSort(toBeSorted, frame));
			
			sortingThread.start();
			
		}
			
	}
		
}
