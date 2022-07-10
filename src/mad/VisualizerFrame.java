package mad;

import java.awt.BorderLayout;
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
	private final int MAX_DELAY 	= 1000;
	private final int MIN_DELAY		= 1;
	private final int DEFAULT_DELAY = 50;
	
	private final int MAX_SIZE 		= 300;
	private final int MIN_SIZE 		= 1;
	private final int DEFAULT_SIZE 	= 40;

	private final String[] Sorts 	= { "Bubble Sort", "Selection Sort", "Merge Sort" };

	private int pillarHeight;

	private JPanel 				wrapper;
	private JPanel 				top;
	private JPanel 				bottom;
	private JPanel[] 			pillar;
	public	JButton 			start;
	public	JComboBox<String> 	selection;
	private JSlider 			delay;
	public  JSlider 			size;
	private JLabel 				delayVal;
	private JLabel 				sizeVal;
	private GridBagConstraints 	c;

	public VisualizerFrame() {
		// LEFT CONTAINER -----------------------------------------------------------------------
		TitledBorder buttonWrapperTitle = BorderFactory.createTitledBorder("FUNCTIONAL AREA");
		buttonWrapperTitle.setTitleJustification(TitledBorder.CENTER);
		buttonWrapperTitle.setTitleColor(Color.WHITE);
		
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setBackground(Color.BLACK);
		top.setBorder(new CompoundBorder(buttonWrapperTitle, new EmptyBorder(10, 10, 10, 10)));

		selection 	= new JComboBox<String>();
		start 		= new JButton("Start");
		delay 		= new JSlider(MIN_DELAY, MAX_DELAY, DEFAULT_DELAY);
		size 		= new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
		delayVal 	= new JLabel("Speed: 50 ms");
		sizeVal 	= new JLabel("Size: 40 values");

		start.setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 30, 10, 30)));
		start.setBackground(Color.BLACK);
		start.setForeground(Color.WHITE);

		for (String s: Sorts) {	selection.addItem(s); }
		selection.setBorder(new EmptyBorder(10, 30, 10, 30));
		selection.setBackground(Color.BLACK);
		selection.setForeground(Color.WHITE);

		delay.setBorder(new EmptyBorder(10, 30, 10, 30));
		
		delayVal.setForeground(Color.WHITE);
		
		size.setBorder(new CompoundBorder(new LineBorder(null), new EmptyBorder(10, 50, 10, 10)));

		sizeVal.setForeground(Color.WHITE);
		
		
		top.add(delayVal);
		top.add(delay);
		top.add(selection);
		top.add(sizeVal);
		top.add(size);

		// BOTTOM CONTAINER --------------------------------------------------------------
		bottom = new JPanel();
		bottom.setLayout(new GridBagLayout());
		bottom.setBorder(new EmptyBorder(10, 40, 10, 10));
		bottom.setBackground(Color.BLACK);
				
		TitledBorder arrayWrapperTitle = BorderFactory.createTitledBorder("SORTING AREA");
		arrayWrapperTitle.setTitleJustification(TitledBorder.CENTER);
		arrayWrapperTitle.setTitleColor(Color.WHITE);
		bottom.setBorder(new CompoundBorder(arrayWrapperTitle , new EmptyBorder(10, 10, 10, 10)));
		
		c 		 = new GridBagConstraints();
		c.insets = new Insets(0, 1, 0, 1);
		c.anchor = GridBagConstraints.SOUTH;		
		
		// WRAPPER ----------------------------------------------------------------------
		wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setBorder(new EmptyBorder(20, 20, 20, 20));
		wrapper.setBackground(Color.BLACK);
		
		wrapper.add(top, BorderLayout.NORTH);
		wrapper.add(bottom, BorderLayout.CENTER);	
		wrapper.add(start, BorderLayout.SOUTH);
		
		add(wrapper);
		this.setTitle("MAD Lions' Sorting Algorithms Visualization");
		
		// ACTION LISTENERS --------------------------------------------------------------
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!SortingVisualizer.isSorting) {
					SortingVisualizer.startSort((String) selection.getSelectedItem());
					size.setEnabled(false);
					selection.setEnabled(false);
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

		delay.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				delayVal.setText(("Delay: " + Integer.toString(delay.getValue()) + "ms"));
				validate();
				SortingVisualizer.sleep = delay.getValue();
			}
		});
		
		setExtendedState(JFrame.MAXIMIZED_BOTH );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initArray(Integer[] elements){
		pillar = new JPanel[SortingVisualizer.numberOfElements];
		bottom.removeAll();
		pillarHeight =  (int) ((getHeight() * 0.5) / (pillar.length));
		for(int i = 0; i < SortingVisualizer.numberOfElements; i++){
			pillar[i] = new JPanel();
			pillar[i].setPreferredSize(new Dimension(SortingVisualizer.pillarWidth, elements[i] * pillarHeight));
			pillar[i].setBackground(Color.green);
			bottom.add(pillar[i], c);
		}
		repaint();
		validate();
	}

	public void arraySwitch(Integer[] elements, int currentElement, int comparingElement){
		arraySwitch(elements, currentElement, comparingElement, NULL_ELEMENT);
	}

	public void arraySwitch(Integer[] elements, int currentElement, int comparingElement, int reading){
		bottom.removeAll();
		for(int i = 0; i < pillar.length; i++){
			pillar[i] = new JPanel();
			pillar[i].setPreferredSize(new Dimension(SortingVisualizer.pillarWidth, elements[i] * pillarHeight));
			if (i == currentElement){
				pillar[i].setBackground(Color.blue);
			}else if(i == comparingElement){
				pillar[i].setBackground(Color.red);
			}else if(i == reading){
				pillar[i].setBackground(Color.white);
			}else{
				pillar[i].setBackground(Color.green);
			}
			bottom.add(pillar[i], c);
		}
		repaint();
		validate();
	}

}
