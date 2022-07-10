package mad.Sorts;

import mad.*;

public class BubbleSort implements Runnable {
	private Integer[] arr;
	private VisualizerFrame frame;
	
	public BubbleSort(Integer[] arr, VisualizerFrame frame) {
		this.arr = arr;
		this.frame = frame;
	}
	
	public void run() {
		sort();
		SortingVisualizer.isSorting = false;
		SortingVisualizer.frame.size.setEnabled(true);
		SortingVisualizer.frame.start.setText("Start");
	}

	public void sort() {
		int temp = 0;
		boolean swapped = false;
		for (int i = 0; i < arr.length - 1; i++) {
			swapped = false;
			for(int j = 1; j < arr.length - i; j++){
				if (arr[j - 1] > arr[j]){
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j]= temp;
					swapped = true;
				}
				frame.arraySwitch(arr, j, j + 1);
				try {
					Thread.sleep(SortingVisualizer.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (!swapped) break;
		}
	}

}
