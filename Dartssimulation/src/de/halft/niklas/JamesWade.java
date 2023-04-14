package de.halft.niklas;

public class JamesWade extends Dartsspieler {
	public static double[] scoringprobs = { 1.23, 0.0, 0.0, 0.0, 1.24, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 15.0, 36.147, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
			0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0, 15.0, 27.403 };
	public static double bullprobs = 30.53;
	public static double checkoutpercentage = 39.56;
	public static double bignumberpercentage = 97.52;
	public static String name = "James Wade";
	@Override
	public void walkon() {
		// TODO Auto-generated method stub

	}
	public  String getName() {
		return name;
	}
	public  void setName(String name) {
		JamesWade.name = name;
	}

}
