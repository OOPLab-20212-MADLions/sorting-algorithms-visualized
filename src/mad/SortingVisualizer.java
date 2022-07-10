package mad;

import mad.Sorts.*;

public class SortingVisualizer {
	private static Thread 			algorithmThread;
	public 	static VisualizerFrame 	frame;
	public 	static Integer[] 		arr;
	public 	static boolean 			isSorting = false;
	public 	static int 				numberOfElements = 40;
	public 	static int 				sleep = 50;
	public 	static int 				pillarWidth;

	public static void main(String[] args) {
		frame = new VisualizerFrame();
		resetArray();
	}

	public static void resetArray(){
		if (isSorting) { 
			return; 
		} else {
			arr = new Integer[numberOfElements];
			pillarWidth = (int) Math.max(Math.ceil(500/numberOfElements), 1);
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) (numberOfElements * Math.random());
			}
			frame.initArray(arr);
		}
		
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
