package mad.Sorts.BucketSort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class VisualizerFrame extends JFrame {
	// Speed
	private final int MAX_SPEED = 1000;
	private final int MIN_SPEED = 1;
	private final int DEFAULT_SPEED = 20;
	// Size
	private final int MAX_SIZE = 100;
	private final int MIN_SIZE = 1;
	private final int DEFAULT_SIZE = 20;

	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel[] bucketWrapper;
	private JPanel buttonWrapper;
	private JPanel allBucketWrapper;
	private JPanel[] squarePanels;
	private JPanel[] bucketPanels;
	private JPanel[][] bucketArray;
	private JButton start;
	private JSlider speed;
	private JSlider size;
	private JLabel speedVal;
	private JLabel sizeVal;
	private GridBagConstraints c;

	public VisualizerFrame(){
		super("Sorting Visualizer");

		start = new JButton("Start");
		buttonWrapper = new JPanel();
		arrayWrapper = new JPanel();
		wrapper = new JPanel();
		
		allBucketWrapper = new JPanel();
		
		speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
		size = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
		speedVal = new JLabel("Speed: 20 ms");
		sizeVal = new JLabel("Size: 20 values");
		c = new GridBagConstraints();

		arrayWrapper.setLayout(new GridLayout(2, 60, 5, 5));
		wrapper.setLayout(new BorderLayout());
		
		c.insets = new Insets(10,5,10,5);
		c.anchor = GridBagConstraints.SOUTH;

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrayWrapper.removeAll();
				allBucketWrapper.removeAll();
				BucketSortingVisualizer.startSort();
			}
		});

		speed.setMinorTickSpacing(10);
		speed.setMajorTickSpacing(100);
		speed.setPaintTicks(true);

		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				speedVal.setText(("Speed: " + Integer.toString(speed.getValue()) + "ms"));
				validate();
				BucketSortingVisualizer.sleep = speed.getValue();
			}
		});

		size.setMinorTickSpacing(5);
		size.setMajorTickSpacing(10);
		size.setPaintTicks(true);

		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeVal.setText(("Size: " + Integer.toString(size.getValue()) + " values"));
				validate();
				BucketSortingVisualizer.sortDataCount = size.getValue();
			}
		});

		buttonWrapper.add(speedVal);
		buttonWrapper.add(speed);
		buttonWrapper.add(sizeVal);
		buttonWrapper.add(size);
		buttonWrapper.add(start);

		
		wrapper.add(buttonWrapper, BorderLayout.SOUTH);
		wrapper.add(allBucketWrapper);
		wrapper.add(arrayWrapper, BorderLayout.NORTH);
		
		add(wrapper);

		setExtendedState(JFrame.MAXIMIZED_BOTH );

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	// preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
	public void preDrawArray(int[] array){
		bucketWrapper = new JPanel[10];
		squarePanels = new JPanel[BucketSortingVisualizer.sortDataCount];
		bucketPanels = new JPanel[10];
		bucketWrapper = new JPanel[10];
		bucketArray = new JPanel[10][BucketSortingVisualizer.sortDataCount];
		allBucketWrapper.setLayout(new GridLayout(10, 1, 20, 5));
		
		JLabel x;
		arrayWrapper.removeAll();
		allBucketWrapper.removeAll();

		for(int i = 0; i < BucketSortingVisualizer.sortDataCount; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(30, 30));
			squarePanels[i].setBackground(Color.black);
			squarePanels[i].setLayout(new GridBagLayout()); 
			
			x = new JLabel(Integer.toString(array[i]));
			x.setForeground(Color.white);
			squarePanels[i].add(x);
			
			arrayWrapper.add(squarePanels[i], c);
		}
		
		for(int i = 0; i < 10; i++){
			// BucketWrapper
			bucketWrapper[i] = new JPanel();
			bucketWrapper[i].setLayout(new GridBagLayout()); 
			
			// ID panel of bucket
			bucketPanels[i] = new JPanel();
			bucketPanels[i].setPreferredSize(new Dimension(50, 50));
			bucketPanels[i].setBackground(Color.blue);
			bucketPanels[i].setLayout(new GridBagLayout()); 			
			x = new JLabel(Integer.toString(i));
			x.setForeground(Color.white);
			bucketPanels[i].add(x);
			
			bucketWrapper[i].add(bucketPanels[i], c);
			
			allBucketWrapper.add(bucketWrapper[i]);
		}
		
		repaint();
		validate();
	}
	
	public void reDrawArray(int[] array, int workingItemArray, int[][] bucket, int[] bucketSize, int workingBucket, int workingItemBucket){
		allBucketWrapper.removeAll();
		arrayWrapper.removeAll();
		
		JLabel x;
		
		for(int i = 0; i < BucketSortingVisualizer.sortDataCount; i++){
			squarePanels[i] = new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(30, 30));
			squarePanels[i].setLayout(new GridBagLayout()); 
			
			x = new JLabel(Integer.toString(array[i]));
			x.setForeground(Color.white);
			squarePanels[i].add(x);
			if (i == workingItemArray){
				squarePanels[i].setBackground(Color.green);
			}else{
				squarePanels[i].setBackground(Color.black);
			}
			arrayWrapper.add(squarePanels[i], c);
		}
		
		for(int i = 0; i < 10; i++){
			// BucketWrapper
			bucketWrapper[i] = new JPanel();
			bucketWrapper[i].setLayout(new GridBagLayout()); 
			
			// ID panel of bucket
			bucketPanels[i] = new JPanel();
			bucketPanels[i].setPreferredSize(new Dimension(50, 50));
			if(i == workingBucket) {
				bucketPanels[i].setBackground(Color.red);
			} else bucketPanels[i].setBackground(Color.blue);
			bucketPanels[i].setLayout(new GridBagLayout()); 	
			
			x = new JLabel(Integer.toString(i));
			x.setForeground(Color.white);
			bucketPanels[i].add(x);
			
			bucketWrapper[i].add(bucketPanels[i], c);
			
			// Add item for each bucket
			for(int j = 0; j < bucketSize[i]; j++) {
				bucketArray[i][j] = new JPanel();
				bucketArray[i][j].setPreferredSize(new Dimension(30, 30));
				if(i == workingBucket && j == workingItemBucket) bucketArray[i][j].setBackground(Color.green);
				else if(i == workingBucket && j == bucketSize[i] - 1 && workingItemArray != -1 && workingItemBucket == -1) {
					bucketArray[i][j].setBackground(Color.green);
				} else bucketArray[i][j].setBackground(Color.black);
				
				bucketArray[i][j].setLayout(new GridBagLayout());
				
				x = new JLabel(Integer.toString(bucket[i][j]));
				x.setForeground(Color.white);
				bucketArray[i][j].add(x);
				
				bucketWrapper[i].add(bucketArray[i][j], c);
			}
			
			allBucketWrapper.add(bucketWrapper[i]);
		}
		repaint();
		validate();
	}

}
