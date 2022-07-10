package mad.Sorts;

import mad.*;

public class SelectionSort implements Runnable {
	
	private Integer[] arr;
	private VisualizerFrame frame;
	
	public SelectionSort(Integer[] toBeSorted, VisualizerFrame frame) {
		this.arr = toBeSorted;
		this.frame = frame;
	}
	
	public void run() {
		sort();
		SortingVisualizer.isSorting = false;
		SortingVisualizer.frame.size.setEnabled(true);
		SortingVisualizer.frame.selection.setEnabled(true);
		SortingVisualizer.frame.start.setText("Start");
	}
		
	public void sort() {
		int temp = 0;
		int selected = 0;
		for (int i = 0; i < arr.length; i++){
			selected = i;
			for(int j = arr.length - 1; j > i; j--){
				if (arr[j] <= arr[selected]){
					selected = j;
				}
				frame.arraySwitch(arr, selected, j - 1);
				try {
					Thread.sleep(SortingVisualizer.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			temp = arr[i];
			arr[i] = arr[selected];
			arr[selected] = temp;
		}
	}

}
