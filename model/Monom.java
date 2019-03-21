package model;

public class Monom  {
	
	private int exp;
	private double coef;
	
	public Monom()
	{	
		this.setCoef(0.0); 
		this.setExp(0);
	}
	
	public Monom(double coef, int exp)
	{	
		this.setCoef(coef); 
		this.setExp(exp);
	}
	
	public int getExp()
	{
		return this.exp;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public double getCoef()
	{
		return this.coef;
	}
	
	public void setCoef(double coef) {
		this.coef = coef;
	}
	
}
