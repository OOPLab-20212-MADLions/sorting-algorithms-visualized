package mad;

import mad.Sorts.*;

public class SortingVisualizer {
	private static Thread algorithmThread;
	public static VisualizerFrame frame;
	public static Integer[] arr;
	public static boolean isSorting = false;
	public static int sortDataCount = 40;
	public static int sleep = 50;
	public static int blockWidth;

	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
	}

	public static void resetArray(){
		if (isSorting) { 
			return; 
		}
		arr = new Integer[sortDataCount];
		blockWidth = (int) Math.max(Math.ceil(500/sortDataCount), 1);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (sortDataCount * Math.random());
		}
		frame.preDrawArray(arr);
	}

	public static void startSort(String algorithm) {
		if (!isSorting || algorithmThread == null){
			resetArray();
			isSorting = true;
			frame.start.setText("Running...");
			switch (algorithm){
				case "Bubble Sort":
					algorithmThread = new Thread(new BubbleSort(arr, frame));
					break;
				case "Selection Sort":
					algorithmThread = new Thread(new SelectionSort(arr, frame));
					break;
				case "Merge Sort":
					algorithmThread = new Thread(new MergeSort());
					break;
				default:
					isSorting = false;
					return;
			}
			algorithmThread.start();
		}
	}
}
