package mad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private final int MAX_SPEED 	= 1000;
	private final int MIN_SPEED 	= 1;
	private final int DEFAULT_SPEED = 50;
	
	private final int MAX_SIZE 		= 500;
	private final int MIN_SIZE 		= 1;
	private final int DEFAULT_SIZE 	= 40;

	private final String[] Sorts 	= { "Bubble Sort", "Selection Sort", "Merge Sort" };

	private int pillarHeight;

	private JPanel 				wrapper;
	private JPanel 				left;
	private JPanel 				right;
	private JPanel[] 			pillar;
	public	JButton 			start;
	private JComboBox<String> 	selection;
	private JSlider 			speed;
	public  JSlider 			size;
	private JLabel 				speedVal;
	private JLabel 				sizeVal;
	private GridBagConstraints 	c;

	public VisualizerFrame() {
		// LEFT CONTAINER -----------------------------------------------------------------------
		TitledBorder buttonWrapperTitle = BorderFactory.createTitledBorder("FUNCTIONAL AREA");
		buttonWrapperTitle.setTitleJustification(TitledBorder.CENTER);
		buttonWrapperTitle.setTitleColor(Color.WHITE);
		
		left = new JPanel();
		left.setLayout(new FlowLayout());
		left.setBackground(Color.BLACK);
		left.setBorder(new CompoundBorder(buttonWrapperTitle, new EmptyBorder(10, 10, 10, 10)));

		selection 	= new JComboBox<String>();
		start 		= new JButton("Start");
		speed 		= new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
		size 		= new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
		speedVal 	= new JLabel("Speed: 50 ms");
		sizeVal 	= new JLabel("Size: 40 values");

		start.setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 30, 10, 30)));
		start.setBackground(Color.BLACK);
		start.setForeground(Color.WHITE);

		for (String s: Sorts) {	selection.addItem(s); }
		selection.setBorder(new EmptyBorder(10, 30, 10, 30));
		selection.setBackground(Color.BLACK);
		selection.setForeground(Color.WHITE);

		speed.setBorder(new EmptyBorder(10, 30, 10, 30));
		speed.setPaintTicks(false);
		
		speedVal.setForeground(Color.WHITE);
		
		size.setPaintTicks(false);
		size.setBorder(new EmptyBorder(10, 30, 10, 30));

		sizeVal.setForeground(Color.WHITE);
		size.setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 50, 10, 10)));

		left.add(speedVal);
		left.add(speed);
		left.add(sizeVal);
		left.add(size);
		left.add(start);
		left.add(selection);

		// RIGHT CONTAINER --------------------------------------------------------------
		right = new JPanel();
		right.setLayout(new GridBagLayout());
		right.setBorder(new EmptyBorder(10, 40, 10, 10));
		right.setBackground(Color.BLACK);
				
		TitledBorder arrayWrapperTitle = BorderFactory.createTitledBorder("SORTING AREA");
		arrayWrapperTitle.setTitleJustification(TitledBorder.CENTER);
		arrayWrapperTitle.setTitleColor(Color.WHITE);
		right.setBorder(new CompoundBorder(arrayWrapperTitle , new EmptyBorder(10, 10, 10, 10)));
		
		c 		 = new GridBagConstraints();
		c.insets = new Insets(0, 1, 0, 1);
		c.anchor = GridBagConstraints.SOUTH;		
		
		// WRAPPER ----------------------------------------------------------------------
		wrapper = new JPanel();
		wrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		wrapper.setBorder(new EmptyBorder(20, 20, 20, 20));
		wrapper.setBackground(Color.BLACK);
		
		wrapper.add(left);
		wrapper.add(right);	
		add(wrapper);
		this.setTitle("MAD Lions' Sorting Algorithms Visualization");
		
		// ACTION LISTENERS --------------------------------------------------------------
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!SortingVisualizer.isSorting) {
					SortingVisualizer.startSort((String) selection.getSelectedItem());
					size.setEnabled(false);
				} 
			}
		});
		
		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeVal.setText(("Size: " + Integer.toString(size.getValue()) + " values"));
				validate();
				SortingVisualizer.numberOfElements = size.getValue();
			}
		});

		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				speedVal.setText(("Speed: " + Integer.toString(speed.getValue()) + "ms"));
				validate();
				SortingVisualizer.sleep = speed.getValue();
			}
		});

		setExtendedState(JFrame.MAXIMIZED_BOTH );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initArray(Integer[] squares){
		pillar = new JPanel[SortingVisualizer.numberOfElements];
		right.removeAll();
		pillarHeight =  (int) ((getHeight()*0.7)/(pillar.length));
		for(int i = 0; i < SortingVisualizer.numberOfElements; i++){
			pillar[i] = new JPanel();
			pillar[i].setPreferredSize(new Dimension(SortingVisualizer.pillarWidth, squares[i] * pillarHeight));
			pillar[i].setBackground(Color.green);
			right.add(pillar[i], c);
		}
		repaint();
		validate();
	}

	public void arraySwitch(Integer[] x){
		arraySwitch(x, NULL_ELEMENT);
	}

	public void arraySwitch(Integer[] x, int y){
		arraySwitch(x, y, NULL_ELEMENT);
	}

	public void arraySwitch(Integer[] x, int y, int z){
		arraySwitch(x, y, z, NULL_ELEMENT);
	}

	public void arraySwitch(Integer[] squares, int currentElement, int comparingElement, int reading){
		right.removeAll();
		for(int i = 0; i < pillar.length; i++){
			pillar[i] = new JPanel();
			pillar[i].setPreferredSize(new Dimension(SortingVisualizer.pillarWidth, squares[i] * pillarHeight));
			if (i == currentElement){
				pillar[i].setBackground(Color.blue);
			}else if(i == comparingElement){
				pillar[i].setBackground(Color.red);
			}else if(i == reading){
				pillar[i].setBackground(Color.white);
			}else{
				pillar[i].setBackground(Color.green);
			}
			right.add(pillar[i], c);
		}
		repaint();
		validate();
	}

}
