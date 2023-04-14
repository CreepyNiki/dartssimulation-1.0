package de.halft.niklas;

import java.util.Arrays;
import de.halft.niklas.Dartsmatch;
import java.util.Random;
import de.halft.niklas.Dartsscheibe;

public class MichaelvanGerwen extends Dartsspieler {
	public static double[] scoringprobs = { 4.090, 0.0, 0.0, 0.0, 5.698, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 55.548, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
			0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 41.485 };
	public static double bullprobs = 30.53;
	public static double checkoutpercentage = 39.56;
	public static double bignumberpercentage = 97.52;
	public static String name = "Michael van Gerwen";
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		MichaelvanGerwen.name = name;
	}


	@Override
	public void walkon() {

	}

	

	
	
}
