package mad.Sorts;

import mad.SortingVisualizer;

public class MergeSort implements Runnable {
	
	public void run() {
		Integer[] arr = SortingVisualizer.arr;
		mergeSort(arr);
		SortingVisualizer.isSorting = false;
		SortingVisualizer.frame.size.setEnabled(true);
		SortingVisualizer.frame.start.setText("Start");
	}
	
	public void mergeSort(Integer[] x) { 
		mergeSort (x, 0, x.length - 1); 
	}

	private void mergeSort(Integer[] x, int first, int last) {
      int mid, lt, rt;
      int tmp;

      if (first >= last) {
    	  return;  
      }
      
      mid = (first + last) / 2;

      mergeSort(x, first, mid);
      mergeSort(x, mid + 1, last);

      lt = first;  rt = mid + 1;
      
      if ( x[mid] <= x[rt]) {
    	  return; 
      }
         
      while (lt <= mid && rt <= last) {
         if ( x[lt] <= x[rt]) {
        	 lt++; 
         }            
         else {
            tmp = x[rt];     
            for (int i = rt - lt; i > 0; i--){
            	x[lt + i] = x[lt + i-1];
            }
            x[lt] = tmp;
            lt++;  
            mid++;  
            rt++;
         }
         SortingVisualizer.frame.arraySwitch(x, mid, rt, lt);
			try {
				Thread.sleep(SortingVisualizer.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
      }
   }
}
