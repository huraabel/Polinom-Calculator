package view;


import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.*;


public class View extends JFrame{
	
	private JTextField firstPoly= new JTextField(40);
	private JComboBox operation = new JComboBox();
	private JTextField secondPoly= new JTextField(46);
	private JButton calculateButton= new JButton("Calculate");
	private JTextField calcSolution= new JTextField(53);
	private JTextField rest= new JTextField(53);
	
	private JLabel pol1 = new JLabel("Polinom A:");
	private JLabel pol2 = new JLabel("Polinom B:");
	private JLabel pol4 = new JLabel("Rest:");
	
	
	
	public View ()
	{	
		super("Poly-Calculator");
		
		JPanel calcPanel= new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 200);
		operation.setModel(new DefaultComboBoxModel(new String[] { "+", "-", "*", "/","deriv","integ"}));
		operation.setBounds(98, 51, 46, 20);
		
		calcPanel.add(pol1);
		calcPanel.add(firstPoly);
		calcPanel.add(operation);
		calcPanel.add(pol2);
		calcPanel.add(secondPoly);
		calcPanel.add(calculateButton);
		calcPanel.add(calcSolution);
		calcPanel.add(pol4);
		calcPanel.add(rest);
		this.add(calcPanel);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public String getFirstPoly(){
		return firstPoly.getText();
		}
	
	public String getSecondPoly(){
		return secondPoly.getText();
		}
	
	public String getCalcSolution(){
		return calcSolution.getText();
		}
	
	public void setCalcSolution(Polinom solution){
		calcSolution.setText(solution.toString());
		}
	public void setRest(Polinom solution){
		rest.setText(solution.toString());
		}
	
	public int getOption()
	{
		return this.operation.getSelectedIndex();
	}
	
	public void addCalculateListener(ActionListener listenForCalcButton){
		calculateButton.addActionListener(listenForCalcButton);
		}
	
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
		}

}
