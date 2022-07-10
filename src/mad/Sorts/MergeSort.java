package mad.Sorts;

import mad.SortingVisualizer;

public class MergeSort implements Runnable {
	
	public void run() {
		Integer[] arr = SortingVisualizer.arr;
		mergeSort(arr);
		SortingVisualizer.isSorting = false;
		SortingVisualizer.frame.size.setEnabled(true);
		SortingVisualizer.frame.selection.setEnabled(true);
		SortingVisualizer.frame.start.setText("Start");
	}
	
	public void mergeSort(Integer[] arr) { 
		mergeSort (arr, 0, arr.length - 1); 
	}

	private void mergeSort(Integer[] arr, int first, int last) {
      int m, l, r;
      int temp;

      if (first >= last) {
    	  return;  
      }
      
      m = (first + last) / 2;

      mergeSort(arr, first, m);
      mergeSort(arr, m + 1, last);

      l = first;  
      r = m + 1;
      
      if (arr[m] <= arr[r]) {
    	  return; 
      }
         
      while (l <= m && r <= last) {
         if (arr[l] <= arr[r]) {
        	 l++; 
         }            
         else {
            temp = arr[r];     
            for (int i = r - l; i > 0; i--){
            	arr[l + i] = arr[l + i - 1];
            }
            arr[l] = temp;
            l++; m++; r++;
         }
         SortingVisualizer.frame.arraySwitch(arr, m, r, l);
			try {
				Thread.sleep(SortingVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
      }
   }
}
