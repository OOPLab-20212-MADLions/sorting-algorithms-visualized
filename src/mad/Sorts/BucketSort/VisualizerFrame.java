package mad.Sorts.BucketSort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VisualizerFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private final int NULL_ELEMENT 	= -1; 
	private final int MAX_DELAY 	= 1000;
	private final int MIN_DELAY 	= 1;
	private final int DEFAULT_DELAY = 20;
	
	private final int MAX_SIZE 		= 100;
	private final int MIN_SIZE 		= 1;
	private final int DEFAULT_SIZE 	= 20;

	private JPanel 				wrapper;
	private JPanel 				top;
	private JPanel 				bottom;
	private JPanel[] 			bucketWrapper;
	private JPanel 				allBucketWrapper;
	private JPanel[] 			pillar;
	private JPanel[] 			bucketPanels;
	private JPanel[][] 			bucketArray;
	public 	JButton 			start;
	private JSlider 			delay;
	public 	JSlider 			size;
	private JLabel 				delayVal;
	private JLabel 				sizeVal;
	private GridBagConstraints 	c;

	public VisualizerFrame(){
		// TOP CONTAINER -----------------------------------------------------------------------
		TitledBorder buttonWrapperTitle = BorderFactory.createTitledBorder("FUNCTIONAL AREA");
		buttonWrapperTitle.setTitleJustification(TitledBorder.CENTER);
		buttonWrapperTitle.setTitleColor(Color.WHITE);

		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setBorder(new CompoundBorder(buttonWrapperTitle, new EmptyBorder(10, 10, 10, 10)));
		top.setBackground(Color.BLACK);
		
		start 		= new JButton("Start");
		delay 		= new JSlider(MIN_DELAY, MAX_DELAY, DEFAULT_DELAY);
		size 		= new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
		delayVal 	= new JLabel("Delay: 20 ms");
		sizeVal 	= new JLabel("Size: 20 values");
		
		start.setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 30, 10, 30)));
		start.setBackground(Color.BLACK);
		start.setForeground(Color.WHITE);
		
		delay.setBorder(new EmptyBorder(10, 30, 10, 30));
		
		delayVal.setForeground(Color.WHITE);

		size.setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 50, 10, 10)));

		sizeVal.setForeground(Color.WHITE);
		
		top.add(start);
		top.add(delayVal);
		top.add(delay);
		top.add(sizeVal);
		top.add(size);

		// BOTTOM CONTAINER --------------------------------------------------------------
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(2, 60, 5, 5));
		bottom.setBorder(new EmptyBorder(10, 40, 10, 10));
		bottom.setBackground(Color.BLACK);
		
		TitledBorder arrayWrapperTitle = BorderFactory.createTitledBorder("SORTING ARRAY");
		arrayWrapperTitle.setTitleJustification(TitledBorder.CENTER);
		arrayWrapperTitle.setTitleColor(Color.WHITE);
		bottom.setBorder(new CompoundBorder(arrayWrapperTitle , new EmptyBorder(10, 10, 10, 10)));
		
		c 		 = new GridBagConstraints();
		c.insets = new Insets(10, 5, 10, 5);
		c.anchor = GridBagConstraints.SOUTH;	
		
		// WRAPPER ----------------------------------------------------------------------
		allBucketWrapper = new JPanel();
		allBucketWrapper.setBackground(Color.BLACK);

		wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setBorder(new EmptyBorder(20, 20, 20, 20));
		wrapper.setBackground(Color.BLACK);
		
		wrapper.add(top, BorderLayout.NORTH);
		wrapper.add(allBucketWrapper, BorderLayout.CENTER);
		wrapper.add(bottom, BorderLayout.SOUTH);
		
		add(wrapper);
		this.setTitle("MAD Lions' Bucket Sort Visualization");

		// ACTION LISTENERS --------------------------------------------------------------
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottom.removeAll();
				allBucketWrapper.removeAll();
				BucketSortingVisualizer.startSort();
				size.setEnabled(false);
			}
		});

		delay.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				delayVal.setText(("Delay: " + Integer.toString(delay.getValue()) + "ms"));
				validate();
				BucketSortingVisualizer.sleep = delay.getValue();
			}
		});

		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeVal.setText(("Size: " + Integer.toString(size.getValue()) + " values"));
				validate();
				BucketSortingVisualizer.numberOfElements = size.getValue();
			}
		});
		
		setExtendedState(JFrame.MAXIMIZED_BOTH );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
	public void initArray(int[] array) {
		bucketWrapper = new JPanel[10];
		pillar = new JPanel[BucketSortingVisualizer.numberOfElements];
		bucketPanels = new JPanel[10];
		bucketWrapper = new JPanel[10];
		bucketArray = new JPanel[10][BucketSortingVisualizer.numberOfElements];
		allBucketWrapper.setLayout(new GridLayout(10, 1, 20, 5));
		
		JLabel x;
		bottom.removeAll();
		allBucketWrapper.removeAll();

		for(int i = 0; i < BucketSortingVisualizer.numberOfElements; i++){
			pillar[i] = new JPanel();
			pillar[i].setPreferredSize(new Dimension(30, 30));
			pillar[i].setBackground(Color.black);
			pillar[i].setLayout(new GridBagLayout()); 
			
			x = new JLabel(Integer.toString(array[i]));
			x.setForeground(Color.white);
			pillar[i].add(x);
			
			bottom.add(pillar[i], c);
		}
		
		for(int i = 0; i < 10; i++){
			// BucketWrapper
			bucketWrapper[i] = new JPanel();
			bucketWrapper[i].setLayout(new GridBagLayout()); 
			bucketWrapper[i].setBackground(Color.BLACK);
			
			// ID panel of bucket
			bucketPanels[i] = new JPanel();
			bucketPanels[i].setPreferredSize(new Dimension(50, 50));
			bucketPanels[i].setBackground(Color.BLACK);
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
	
	public void arraySwitch(int[] array, int workingItemArray, int[][] bucket, int[] bucketSize, int workingBucket, int workingItemBucket){
		allBucketWrapper.removeAll();
		bottom.removeAll();
		JLabel x;
		
		for(int i = 0; i < BucketSortingVisualizer.numberOfElements; i++){
			pillar[i] = new JPanel();
			pillar[i].setPreferredSize(new Dimension(30, 30));
			pillar[i].setLayout(new GridBagLayout()); 
			
			x = new JLabel(Integer.toString(array[i]));
			x.setForeground(Color.white);
			
			pillar[i].add(x);
			
			if (i == workingItemArray) {
				pillar[i].setBackground(Color.green);
			} else {
				pillar[i].setBackground(Color.black);
			}
			
			bottom.add(pillar[i], c);
		}
		
		for(int i = 0; i < 10; i++){
			// BucketWrapper
			bucketWrapper[i] = new JPanel();
			bucketWrapper[i].setLayout(new GridBagLayout()); 
			bucketWrapper[i].setBackground(Color.BLACK);
			
			// ID panel of bucket
			bucketPanels[i] = new JPanel();
			bucketPanels[i].setPreferredSize(new Dimension(50, 50));
			bucketPanels[i].setBackground(Color.BLACK);

			if(i == workingBucket) {
				bucketPanels[i].setBackground(Color.red);
			} else {
				bucketPanels[i].setBackground(Color.blue);
			}
			
			bucketPanels[i].setLayout(new GridBagLayout()); 	
			bucketPanels[i].setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 10, 10, 10)));
			
			x = new JLabel(Integer.toString(i));
			x.setForeground(Color.white);
			
			bucketPanels[i].add(x);
			
			bucketWrapper[i].add(bucketPanels[i], c);
			
			// Add item for each bucket
			for(int j = 0; j < bucketSize[i]; j++) {
				bucketArray[i][j] = new JPanel();
				bucketArray[i][j].setPreferredSize(new Dimension(30, 30));
				if (i == workingBucket && j == workingItemBucket) {
					bucketArray[i][j].setBackground(Color.green);
				} else if (i == workingBucket && j == bucketSize[i] - 1 && workingItemArray != NULL_ELEMENT && workingItemBucket == NULL_ELEMENT) {
					bucketArray[i][j].setBackground(Color.green);
				} else {
					bucketArray[i][j].setBackground(Color.white);
				}
				
				bucketArray[i][j].setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 10, 10, 10)));
				bucketArray[i][j].setLayout(new GridBagLayout());
				
				x = new JLabel(Integer.toString(bucket[i][j]));
				x.setForeground(Color.black);
				
				bucketArray[i][j].add(x);
				
				bucketWrapper[i].add(bucketArray[i][j], c);
			}
			
			allBucketWrapper.add(bucketWrapper[i]);
		}
		repaint();
		validate();
	}

}
