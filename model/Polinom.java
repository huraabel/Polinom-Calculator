package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Polinom implements OperatiiNecesareCuPolinoame, OperatiiSuplimentareCuPolinoame {

	private ArrayList<Monom> pol;
	private int grad;

	public void setGrad(int grad) {
		this.grad = grad;
	}

	public int getGrad() {
		return this.grad;
	}

	public void preparePolinom(int grad) {
		this.pol = new ArrayList<Monom>(grad);
	}

	public ArrayList<Monom> getPolinomList() {
		return this.pol;
	}

	public Polinom(Monom m) {
		this.setGrad(m.getExp());
		this.preparePolinom(m.getExp());
		for (int i = 0; i < this.getGrad(); i++) {
			this.getPolinomList().add(new Monom(0, i));
		}
		this.getPolinomList().add(m);
	}

	public Polinom(int grad) {
		this.setGrad(grad);
		this.preparePolinom(grad);
		for (int i = 0; i <= grad; i++) {
			this.getPolinomList().add(new Monom(0, i));
		}
	}

	public void setPolinom(double coef, int exponent) {
		try {
			this.getPolinomList().set(exponent, new Monom(coef, exponent));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCoeficient(double coef, int exponent) {
		try {
			this.pol.set(exponent, new Monom(this.getPolinomList().get(exponent).getCoef() + coef, exponent));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public String toString() {
		String s = "";

		for (int i = this.grad; i >= 0; i--) {
			if (this.pol.get(i).getCoef() != 0)
				s = s + "+(" + this.pol.get(i).getCoef() + "*X^" + this.pol.get(i).getExp() + ") ";
		}

		if (s.equals("") == false)
			return s;
		else
			return "0";
	}
	
	/** Verifica daca polinomul este nul*/
	public boolean checkZero() {
		boolean z = true;
		for (int i = 0; i <= this.grad; i++) {
			if (this.pol.get(i).getCoef() != 0)
				z = false;
		}
		return z;
	}
	
	/** Gaseste substringul de putere*/
	private static Integer getPower(String pol, int i) {
		int j = i + 1;
		i = i + 1;
		char y;
		if (i != pol.length()) {
			y = pol.charAt(i);
			while (y == '1' || y == '2' || y == '3' || y == '4' || y == '5' || y == '6' || y == '7' || y == '8'
					|| y == '9' || y == '0') {
				if (i < pol.length() - 1) {
					i++;
					y = pol.charAt(i);
				} else
					y = 'x';
			}

			if (y != 'x') {
				return Integer.parseInt(pol.substring(j, i));
			} else {
				return Integer.parseInt(pol.substring(j, i + 1));
			}

		}
		return 0;
	}
	
	/** Gaseste gradul maxim din String */
	private static int findMaxGrad(String pol) {
		int N = 0;
		for (int i = 0; i < pol.length() - 1; i++)
			if (pol.charAt(i) == '^') {
				int n;
				n = getPower(pol, i); //gaseste puterea

				if (n > N)
					N = n;
			}

		return N;
	}

	/** Gaseste fiecare coeficient*/
	private static Polinom findCoef(String pol, int N) {
		Polinom p = new Polinom(N);

		for (int i = 0; i < pol.length() - 1; i++) {
			if (pol.charAt(i) == '+' || pol.charAt(i) == '-') {
				int semn = (pol.charAt(i) == '+') ? 1 : -1; //semnul coeficientului
				int j = i + 1;

				char y = pol.charAt(i + 1);
				String s = "";
				while (y != '*') {
					i++;
					y = pol.charAt(i);
					s = pol.substring(j, i);
				} //gaseste substringul de numere din coeficient
				
				//adauga in lista de coeficienti ,coeficientul gasit
				p.addCoeficient((double) semn * Integer.parseInt(s), getPower(pol, i + 2));

			}

		}

		return p;
	}
	
	
	/** Preia un String si il transforma in polinom*/
	public static Polinom polinomize(String s) {
		return findCoef(s, findMaxGrad(s));
	}

	public Polinom add(Polinom B) {
		Polinom C;

		if (B.grad > this.grad) {
			C = new Polinom(B.grad);
			int i;
			for (i = 0; i <= this.grad; i++)
				C.setPolinom(this.pol.get(i).getCoef() + B.pol.get(i).getCoef(), i);

			for (i = this.grad + 1; i <= B.grad; i++)
				C.setPolinom(B.pol.get(i).getCoef(), i);

		} else {
			C = new Polinom(this.grad);
			int i;
			for (i = 0; i <= B.grad; i++)
				C.setPolinom(this.pol.get(i).getCoef() + B.pol.get(i).getCoef(), i);

			for (i = B.grad + 1; i <= this.grad; i++)
				C.setPolinom(this.pol.get(i).getCoef(), i);
		}

		return C;
	}

	public Polinom sub(Polinom B) {
		Polinom C;

		if (B.grad > this.grad) {
			C = new Polinom(B.grad);
			int i;
			for (i = 0; i <= this.grad; i++)
				C.setPolinom(this.pol.get(i).getCoef() - B.pol.get(i).getCoef(), i);

			for (i = this.grad + 1; i <= B.grad; i++)
				C.setPolinom(-B.pol.get(i).getCoef(), i);

		} else {
			C = new Polinom(this.grad);
			int i;
			for (i = 0; i <= B.grad; i++)
				C.setPolinom(this.pol.get(i).getCoef() - B.pol.get(i).getCoef(), i);

			for (i = B.grad + 1; i <= this.grad; i++)
				C.setPolinom(this.pol.get(i).getCoef(), i);
		}

		return C;

	}

	public Polinom mul(Polinom B) {
		Polinom C = new Polinom(this.grad + B.grad);
		for (int i = 0; i <= this.grad; i++)
			for (int j = 0; j <= B.grad; j++) {
				Polinom T = new Polinom(new Monom(this.pol.get(i).getCoef() * B.pol.get(j).getCoef(), i + j));
				C = C.add(T);
			}
		return C;

	}

	public Polinom[] div(Polinom B) {

		if (B.checkZero() == true) {
			JOptionPane.showMessageDialog(null, "division by zero", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		Polinom Q = new Polinom(this.grad);
		Polinom R = this;
		int gradr = R.grad;
		while (R.checkZero() == false && gradr >= B.grad) {
			int t_e = R.pol.get(gradr).getExp() - B.pol.get(B.grad).getExp();
			double t_c = R.pol.get(gradr).getCoef() / B.pol.get(B.grad).getCoef();
			Polinom T = new Polinom(new Monom(t_c, t_e));
			Q = Q.add(T);
			R = R.sub(B.mul(T));
			gradr = gradr - 1;
		}
		Polinom[] ret = new Polinom[] { Q, R };
		return ret;

	}

	public Polinom deriv() {

		if (this.grad == 0) {
			return new Polinom(new Monom(0, 0));
		}

		Polinom C = new Polinom(this.grad - 1);

		for (int i = this.grad; i > 0; i--) {
			C.setPolinom(this.pol.get(i).getCoef() * i, i - 1);
		}

		return C;

	}

	public Polinom integ() {
		Polinom C = new Polinom(this.grad + 1);

		for (int i = C.grad; i > 0; i--) {
			C.setPolinom(this.pol.get(i - 1).getCoef() / (i), i);

		}

		return C;

	}

}
