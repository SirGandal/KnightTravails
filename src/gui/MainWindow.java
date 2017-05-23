package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import solver.Solver;
import solver.State;

public class MainWindow implements ActionListener {

	String startingSquare, endingSquare;
	int startingRow, endingRow, startingCol, endingCol;
	private final int ASCII_LOWER_A = 97;
	private final int ASCII_UPPER_A = 65;
	private final int ASCII_LOWER_H = 104;
	//Value to add to the ASCII value of a small letter in order to transform it in its correspondent capital letter.
	private final int UPPER_INCREMENT = ASCII_UPPER_A - ASCII_LOWER_A;

	private final JFrame FRAME = new JFrame("The Knight's Travails");
	
	private JTextField startingSquareTxt = new JTextField();
	private JTextField endingSquareTxt = new JTextField();
	private JPanel solutionPanel;
	private JTextArea textArea = new JTextArea();
	
	public MainWindow() {
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		FRAME.setContentPane(contentPane);

		JPanel panel = new JPanel((new GridLayout(3, 1)));
		JPanel squaresPanel = new JPanel((new GridLayout(1, 4)));
		solutionPanel = new JPanel(new GridLayout(1,1));
		
		JLabel startingSquareLabel = new JLabel("Starting square");
		JLabel endingSquareLabel = new JLabel("Ending square");
		squaresPanel.add(startingSquareLabel);
		squaresPanel.add(startingSquareTxt);
		squaresPanel.add(endingSquareLabel);
		squaresPanel.add(endingSquareTxt);

		panel.add(squaresPanel);

		JButton findPathButton = new JButton("<html><h2>Find Path</h2></html>");
		findPathButton.addActionListener(this);
		panel.add(findPathButton);
		
		textArea.setEditable(false);
		solutionPanel.add(textArea);

		panel.add(solutionPanel);
		
		FRAME.getContentPane().add(panel);
		FRAME.pack();
	}
	
	/**
	 * Shows the window.
	 */
	public final void show() {
		FRAME.setVisible(true);
		FRAME.setResizable(false);
		FRAME.setLocationRelativeTo(null);
	}

	private void computeShortestPath(){
		try {
			startingSquare = startingSquareTxt.getText();
			endingSquare = endingSquareTxt.getText();
			
			/*
			 * Checking the input using regular expressions in order to accept as input only well formatted string
			 * such as "A8 B7" or "a8 b7" or "A8 b7" and avoid input bad formatted as a 8 b 7 or invalid input as b10 c20.
			 */
			if(Pattern.matches("[a-hA-H][1-8]", startingSquare) && Pattern.matches("[a-hA-H][1-8]", endingSquare)){	
				/*
				 * Splitting the two input arguments in four separate values identifying the
				 * row and column corresponding to the starting and ending squares of the knight.
				 */
				startingCol = Integer.valueOf(startingSquare.charAt(0));
				startingRow = Integer.valueOf(startingSquare.substring(1, 2));
				endingCol = Integer.valueOf(endingSquare.charAt(0));
				endingRow = Integer.valueOf(endingSquare.substring(1, 2));
				
				/*
				 * Changing the index of the columns from small letters to capital letters if needed.
				 * This change is because of the check that is made inside the State class 
				 * in order to point out which are the squares inside the board reachable from a given square.
				 */
				if(startingCol >= ASCII_LOWER_A && startingCol <= ASCII_LOWER_H){
					startingCol += UPPER_INCREMENT;
				}
				
				if(endingCol >= ASCII_LOWER_A && endingCol <= ASCII_LOWER_H){
					endingCol += UPPER_INCREMENT;
				}
				
				State startingState = new State(startingRow, startingCol);
				State endingState = new State(endingRow, endingCol);
				Solver solver = new Solver();
								
				String moveSequence = solver.findPath(solver.solve(startingState, endingState));
											
				showMessageInSolutionPanel(moveSequence);
			}else{
				throw new NumberFormatException();
			}			
		} catch(NumberFormatException e) {
			showMessageInSolutionPanel("Please insert valid squares. (E.g. A8 B7)");
			return;
		}
	}
	
	private void showMessageInSolutionPanel(String message){
		textArea.setText(message);
		solutionPanel.validate();
		solutionPanel.repaint();
	}
	
	@Override
	public final void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("<html><h2>Find Path</h2></html>")) {
			computeShortestPath();
		}
	}

}