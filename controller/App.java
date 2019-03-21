package controller;
import view.*;

public class App {

	public static Controller contr;
	
	public static void main(String[] args) {
		
    	 contr = new Controller(new View());
		
	}

}
