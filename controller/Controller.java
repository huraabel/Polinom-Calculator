package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class Controller {
	private View theView;
	private int op ;
	
	public Controller(View theView) {
		this.theView = theView;
		this.theView.addCalculateListener(new CalculateListener());

	}
	
	public class CalculateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Polinom firstPoly, secondPoly = null;
			Polinom [] resultPoly = null;
				try {
				firstPoly  = Polinom.polinomize(theView.getFirstPoly()  );
				secondPoly = Polinom.polinomize(theView.getSecondPoly() );
				op = theView.getOption();
				resultPoly = functii(firstPoly,secondPoly,op);
				theView.setCalcSolution(resultPoly[0]);
				theView.setRest(resultPoly[1]);
			} catch (Exception ex) {
				theView.displayErrorMessage("Introduceti 2 polinoame cu coeficienti intregi");
			}
		}
		
		/** returneaza rezultatul operatiei alese*/
		private Polinom[] functii(Polinom firstPoly,Polinom secondPoly, int op)
		{
			Polinom[] resultPoly =  {null, new Polinom(0)};;
			if (op == 0)
				resultPoly[0]=firstPoly.add(secondPoly);
			else if (op == 1) {
				resultPoly[0]=firstPoly.sub(secondPoly);
			} else if (op == 2) {
				resultPoly[0]=firstPoly.mul(secondPoly);
			} else if (op == 3) {
				try {
					resultPoly=firstPoly.div(secondPoly);
				} catch (Exception ex) {
					theView.displayErrorMessage(ex.toString());
				}
			} else if  (op == 4) {
				resultPoly[0]=firstPoly.deriv();	
			} else if  (op == 5) {
				resultPoly[0]=firstPoly.integ();}
			
			return resultPoly;
		}
		
	}
}
