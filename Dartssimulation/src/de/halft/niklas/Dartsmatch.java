package de.halft.niklas;

import java.util.Random;
import java.util.Scanner;
import de.halft.niklas.Dartsspieler;
import de.halft.niklas.GaryAnderson;
import de.halft.niklas.MichaelvanGerwen;

public class Dartsmatch {
	int restscore2;
	int restscore;
	public static int startscore;
	int startscore2;
	int modus;
	int legs;
	int legs1;
	int legs2;
	int sets;
	public static double darts;
	public static double darts2;
	public double legavg1;
	public double legavg2;
	public double matchavg1;
	public double matchavg2;
	Dartsspieler[] da1 = new Dartsspieler[2];

	public Dartsspieler[] ausbullen(Dartsspieler spieler, Dartsspieler gegner, double[] bullpercentage1,
		double[] bullpercentage2) {
		int targetint = 50;
		int dart1 = 0;
		int dart2 = 0;
		double d1 = new Random().nextDouble();
//		da1 = null;

//			System.out.println(d1);

		if (d1 < bullpercentage1[0] / 100) {
			dart1 = targetint;
			System.out.println("Dart1 " + dart1);

		} else if (d1 > bullpercentage1[0] / 100 && d1 < bullpercentage1[2] / 100) {
			dart1 = targetint / 2;
			System.out.println("Dart1 " + dart1);
		} else {
			int i1 = new Random().nextInt(19);
			dart1 = i1;
			System.out.println("Dart1 " + dart1);
		}
		double d2 = new Random().nextDouble();
//		System.out.println(d2);

		if (d2 < bullpercentage2[0] / 100) {
			dart2 = targetint;
			System.out.println("Dart2 " + dart2);

		} else if (d2 > bullpercentage2[0] / 100 && d2 < bullpercentage2[2] / 100) {
			dart2 = targetint / 2;
			System.out.println("Dart2 " + dart2);
		} else {
			int i1 = new Random().nextInt(19);
			dart2 = i1;
			System.out.println("Dart2 " + dart2);
		}

		if (dart1 > dart2) {
			System.out.println(spieler.getName() + " has won the Bull.");
			da1[0] = spieler;
			da1[1] = gegner;
			return da1;
		} else if (dart1 < dart2) {
			System.out.println(gegner.getName() + " has won the Bull.");
			da1[0] = gegner;
			da1[1] = spieler;
			return da1;
		} else {
			ausbullen(spieler, gegner, bullpercentage1, bullpercentage2);
		}
		return da1;
	}

	public int playoneleg(Dartsspieler spieler, Dartsspieler gegner) {
		int[] values = new int[2];
		int[] values2 = new int[2];
		double[] probs = getvalues(spieler);
		double[] probs2 = getvalues(gegner);
		Caller c1 = getCaller();
		MC mc1 = getMC();
		System.out.println(c1.getName());
		System.out.println(mc1.getName());
		startscore = setstartscore();
		restscore = startscore;
		restscore2 = startscore;
		int gesamtscore1 = 0;
		int gesamtscore2 = 0;
		Dartsspieler[] winner = ausbullen(spieler, gegner, probs, probs2);
		double[] scoringprobs = getscoring(winner[0]);
		double[] scoringprobs2 = getscoring(winner[1]);

//		System.out.println("Auf wie viele Legs möchtest du spielen?");
//		Scanner scannerVariable = new Scanner(System.in);
//		setLegs(scannerVariable.nextInt());

		while (restscore >= 170 && restscore2 >= 170) {

			int score1 = winner[0].score(scoringprobs);
			int score2 = winner[1].score(scoringprobs2);
			restscore = restscore - score1;
			restscore2 = restscore2 - score2;
			darts = darts + 3;
			darts2 = darts2 + 3;
			System.out.println(winner[0].getName()+  ": " + score1);
			System.out.println(winner[1].getName()+  ": " + score2);
			gesamtscore1 = gesamtscore1 + score1;
			gesamtscore2 = gesamtscore2 + score2;
//			System.out.println(restscore);

			if (restscore <= 170) {
				break;
			}
			if (restscore2 <= 170) {
				break;
			}

		}
		while (restscore != 0 || restscore2 != 0) {

			if (restscore <= 170) {
				System.out.println(winner[0].getName() +  " you require: " + restscore);
				values = spieler.finish(restscore, scoringprobs, probs);
				restscore = values[1];
				darts = darts + values[0];
				gesamtscore1 = gesamtscore1 + values[2];

				if (restscore == 0) {
					System.out.println("Game Shot in " + (int) darts + " Darts " + winner[0].getName());
					System.out.println(winner[0].getName() + " Wins");
					legavg1 = gesamtscore1 / darts * 3;
					legavg2 = gesamtscore2 / darts2 * 3;
					System.out.println("Avg " + winner[0].getName() + ": " + legavg1);
					System.out.println("Avg " + winner[1].getName() + ": " + legavg2);
					return 0;
				}

			}

			if (restscore2 <= 170) {
				System.out.println(winner[1].getName()+  " you require: " + restscore2);
				values2 = gegner.finish(restscore2, scoringprobs2, probs2);
				restscore2 = values2[1];
				darts2 = darts2 + values2[0];
				gesamtscore2 = gesamtscore2 + values2[2];

				if (restscore2 == 0) {
					System.out.println("Game Shot in " + (int) darts2 + " Darts " + winner[1].getName());
					System.out.println(winner[1].getName() + " Wins");
					legavg1 = gesamtscore1 / darts * 3;
					legavg2 = gesamtscore2 / darts2 * 3;
					System.out.println("Avg " + winner[0].getName() + ": " + legavg1);
					System.out.println("Avg " + winner[1].getName() + ": " + legavg2);

					return 0;

				}
			}
		}
		return 0;
	}

	public int setstartscore() {
		System.out.println("Von welchem Startscore aus möchtest du spielen?");
		Scanner scannerVariable = new Scanner(System.in);
		String temp = scannerVariable.nextLine();
		restscore = Integer.parseInt(temp);
		return restscore;
	}

	public int setmodus() {
		System.out.println("Welchen Modus möchtest du spielen? 1 = Double-Out, 2 = Single-Out");
		Scanner scannerVariable = new Scanner(System.in);
		String temp = scannerVariable.nextLine();
		if (temp.equals("1")) {
			modus = Integer.parseInt(temp);
		} else if (temp.equals("2")) {
			modus = Integer.parseInt(temp);
			System.out.println("lalala");
		} else {
			System.out.println("Keine gültige Eingabe");
		}
		return modus;

	}

	public int setLegs() {
		System.out.println("Wie viele Legs muss der Sieger erreichen?");
		Scanner scannerVariable = new Scanner(System.in);
		String temp = scannerVariable.nextLine();
		legs = Integer.parseInt(temp);
		return legs;
	}

	public int getLegs() {
		return legs;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int playmatch(Dartsspieler spieler, Dartsspieler gegner) {
		int[] values = new int[2];
		int[] values2 = new int[2];
		double[] probs = getvalues(spieler);
		double[] probs2 = getvalues(gegner);
		Caller c1 = getCaller();
		MC mc1 = getMC();
		System.out.println(c1.getName());
		System.out.println(mc1.getName());
		double temp;
		double temp2;
		startscore = setstartscore();
		legs = setLegs();
		legs1 = 0;
		legs2 = 0;
		legavg1 = 0;
		legavg2 = 0;
		Dartsspieler[] winner = ausbullen(spieler, gegner, probs, probs2);
		double[] scoringprobs = getscoring(winner[0]);
		double[] scoringprobs2 = getscoring(winner[1]);
		
		while (legs1 < legs || legs2 < legs) {
			int gesamtscore1 = 0;
			int gesamtscore2 = 0;
			restscore = startscore;
			restscore2 = startscore;
			darts = 0;
			darts2 = 0;

			while (restscore >= 170 || restscore2 >= 170) {
			int gesamtlegs = legs1 + legs2;
				if (gesamtlegs % 2 == 0) {
					int score1 = winner[0].score(scoringprobs);
					int score2 = winner[1].score(scoringprobs2);
					restscore = restscore - score1;
					restscore2 = restscore2 - score2;
					darts = darts + 3;
					darts2 = darts2 + 3;
					System.out.println(winner[0].getName() + ": " + score1);
					System.out.println(winner[1].getName() + ": " + score2);
					gesamtscore1 = gesamtscore1 + score1;
					gesamtscore2 = gesamtscore2 + score2;

					if (restscore <= 170) {
						break;
					}
					if (restscore2 <= 170) {
						break;
					}
				} else if (gesamtlegs % 2 != 0) {
					int score2 = winner[1].score(scoringprobs2);
					int score1 = winner[0].score(scoringprobs);
					restscore = restscore - score1;
					restscore2 = restscore2 - score2;
					darts = darts + 3;
					darts2 = darts2 + 3;
					System.out.println(winner[1].getName() + ": " + score2);
					System.out.println(winner[0].getName() + ": " + score1);
					gesamtscore1 = gesamtscore1 + score1;
					gesamtscore2 = gesamtscore2 + score2;

					if (restscore <= 170) {
						break;
					}
					if (restscore2 <= 170) {
						break;
					}
				}
			}

			while (restscore != 0 || restscore2 != 0) {
				if (restscore <= 170) {
					System.out.println(winner[0].getName() + " you require: " + restscore);
					values = spieler.finish(restscore, scoringprobs2, probs2);
					restscore = values[1];
					darts = darts + values[0];
					gesamtscore1 = gesamtscore1 + values[2];

					if (restscore == 0) {
						System.out.println("Game Shot in " + (int) darts + " Darts " + winner[0].getName());
						this.legs1++;
						String spielstand = "Spielstand: " + winner[0].getName() + " " + legs1 + " : " + legs2 + " " + winner[1].getName();
						System.out.println(spielstand);

						legavg1 = gesamtscore1 / darts * 3;
						legavg2 = gesamtscore2 / darts2 * 3;
						System.out.println("LegAvg " + winner[0].getName() + ": " + legavg1);
						System.out.println("LegAvg "  + winner[1].getName() + ": " + legavg2);
						matchavg1 += legavg1;
						matchavg2 += legavg2;

						if (legs1 >= legs) {
							int gesamtlegs = legs1 + legs2;
							matchavg1 = matchavg1 / gesamtlegs;
							matchavg2 = matchavg2 / gesamtlegs;
							System.out.println("Game Shot and the match: " + winner[0].getName());
							System.out.println("MatchAVG " + winner[0].getName() + ": " + matchavg1);
							System.out.println("MatchAVG " + winner[1].getName() + ": " + matchavg2);

							return 0;
						} else {
							break;
						}
					}

				}

				if (restscore2 <= 170) {
					System.out.println(winner[1].getName() + " you require: " + restscore2);
					values2 = gegner.finish(restscore2, scoringprobs2, probs2);
					restscore2 = values2[1];
					darts2 = darts2 + values2[0];
					gesamtscore2 = gesamtscore2 + values2[2];

					if (restscore2 == 0) {
						System.out.println("Game Shot in " + (int) darts2 + " Darts " + winner[1].getName());
						this.legs2++;
						String spielstand = "Spielstand: " + winner[0].getName() + " " + legs1 + " : " + legs2 + " " + winner[1].getName();
						System.out.println(spielstand);


						legavg1 = gesamtscore1 / darts * 3;
						legavg2 = gesamtscore2 / darts2 * 3;
						System.out.println("LegAvg " + winner[0].getName() + ": " + legavg1);
						System.out.println("LegAvg "  + winner[1].getName() + ": " + legavg2);
						matchavg1 += legavg1;
						matchavg2 += legavg2;

						if (legs2 >= legs) {
							int gesamtlegs = legs1 + legs2;
							matchavg1 = matchavg1 / gesamtlegs;
							matchavg2 = matchavg2 / gesamtlegs;
							System.out.println("Game Shot and the match: " + winner[1].getName());
							System.out.println("MatchAVG " + winner[0].getName() + ": " + matchavg1);
							System.out.println("MatchAVG " + winner[1].getName() + ": " + matchavg2);
							return 0;
						} else {
							break;
						}

					}

				}
			}
		}
		return 0;
	}

	public Caller getCaller() {
		Random rnd = new Random();
		int random = rnd.nextInt(5);

		switch (random) {
		case 0:
			Caller c1 = new KirkBevins();
			return c1;
		case 1:
			Caller c2 = new RussBray();
			return c2;
		case 2:
			Caller c3 = new HuwWare();
			return c3;
		case 3:
			Caller c4 = new GeorgeNoble();
			return c4;
		case 4:
			Caller c5 = new CharlieCorstorphine();
			return c5;
		default:
			System.out.println("Ein Fehler ist aufgetreten");
			return null;
		}

	}

	public MC getMC() {
		Random rnd = new Random();
		int random = rnd.nextInt(2);
		switch (random) {
		case 0:
			MC MC1 = new JohnMcDonald();
			return MC1;
		case 1:
			MC MC2 = new PhilipBrzezinski();
			return MC2;
		default:
			System.out.println("Ein Fehler ist aufgetreten");
			return null;
		}
	}

	public double[] getscoring(Dartsspieler spieler) {

		if (spieler instanceof MichaelvanGerwen) {
			return MichaelvanGerwen.scoringprobs;
		} else if (spieler instanceof MichaelSmith) {
			return MichaelSmith.scoringprobs;
		} else if (spieler instanceof GerwynPrice) {
			return GerwynPrice.scoringprobs;
		} else if (spieler instanceof PeterWright) {
			return PeterWright.scoringprobs;
		} else if (spieler instanceof LukeHumphries) {
			return LukeHumphries.scoringprobs;
		} else if (spieler instanceof JonnyClayton) {
			return JonnyClayton.scoringprobs;
		} else if (spieler instanceof RobCross) {
			return RobCross.scoringprobs;
		} else if (spieler instanceof DannyNoppert) {
			return DannyNoppert.scoringprobs;
		} else if (spieler instanceof DimitriVanDenBergh) {
			return DimitriVanDenBergh.scoringprobs;
		} else if (spieler instanceof NathanAspinall) {
			return NathanAspinall.scoringprobs;
		} else if (spieler instanceof DirkVanDuijvenbode) {
			return DirkVanDuijvenbode.scoringprobs;
		} else if (spieler instanceof JoeCullen) {
			return JoeCullen.scoringprobs;
		} else if (spieler instanceof RyanSearle) {
			return RyanSearle.scoringprobs;
		} else if (spieler instanceof JamesWade) {
			return JamesWade.scoringprobs;
		} else if (spieler instanceof JoseDeSousa) {
			return JoseDeSousa.scoringprobs;
		} else if (spieler instanceof RossSmith) {
			return RossSmith.scoringprobs;
		} else if (spieler instanceof GaryAnderson) {
			return GaryAnderson.scoringprobs;
		} else {
			System.out.println("Ein Fehler ist aufgetreten");
			return null;
		}
	}

	public double[] getvalues(Dartsspieler spieler) {
		double[] values = new double[4];
		String name;

		if (spieler instanceof MichaelvanGerwen) {
			values[0] = MichaelvanGerwen.bullprobs;
			values[1] = MichaelvanGerwen.checkoutpercentage;
			values[2] = MichaelvanGerwen.bignumberpercentage;
			return values;
		} else if (spieler instanceof MichaelSmith) {
			values[0] = MichaelSmith.bullprobs;
			values[1] = MichaelSmith.checkoutpercentage;
			values[2] = MichaelSmith.bignumberpercentage;
			return values;
		} else if (spieler instanceof GerwynPrice) {
			values[0] = GerwynPrice.bullprobs;
			values[1] = GerwynPrice.checkoutpercentage;
			values[2] = GerwynPrice.bignumberpercentage;
			return values;
		} else if (spieler instanceof PeterWright) {
			values[0] = PeterWright.bullprobs;
			values[1] = PeterWright.checkoutpercentage;
			values[2] = PeterWright.bignumberpercentage;
			return values;
		} else if (spieler instanceof LukeHumphries) {
			values[0] = LukeHumphries.bullprobs;
			values[1] = LukeHumphries.checkoutpercentage;
			values[2] = LukeHumphries.bignumberpercentage;
			return values;
		} else if (spieler instanceof JonnyClayton) {
			values[0] = JonnyClayton.bullprobs;
			values[1] = JonnyClayton.checkoutpercentage;
			values[2] = JonnyClayton.bignumberpercentage;
			return values;
		} else if (spieler instanceof RobCross) {
			values[0] = RobCross.bullprobs;
			values[1] = RobCross.checkoutpercentage;
			values[2] = RobCross.bignumberpercentage;
			return values;
		} else if (spieler instanceof DannyNoppert) {
			values[0] = DannyNoppert.bullprobs;
			values[1] = DannyNoppert.checkoutpercentage;
			values[2] = DannyNoppert.bignumberpercentage;
			return values;
		} else if (spieler instanceof DimitriVanDenBergh) {
			values[0] = DimitriVanDenBergh.bullprobs;
			values[1] = DimitriVanDenBergh.checkoutpercentage;
			values[2] = DimitriVanDenBergh.bignumberpercentage;
			return values;
		} else if (spieler instanceof NathanAspinall) {
			values[0] = NathanAspinall.bullprobs;
			values[1] = NathanAspinall.checkoutpercentage;
			values[2] = NathanAspinall.bignumberpercentage;
			return values;
		} else if (spieler instanceof DirkVanDuijvenbode) {
			values[0] = DirkVanDuijvenbode.bullprobs;
			values[1] = DirkVanDuijvenbode.checkoutpercentage;
			values[2] = DirkVanDuijvenbode.bignumberpercentage;
			return values;
		} else if (spieler instanceof JoeCullen) {
			values[0] = JoeCullen.bullprobs;
			values[1] = JoeCullen.checkoutpercentage;
			values[2] = JoeCullen.bignumberpercentage;
			return values;
		} else if (spieler instanceof RyanSearle) {
			values[0] = RyanSearle.bullprobs;
			values[1] = RyanSearle.checkoutpercentage;
			values[2] = RyanSearle.bignumberpercentage;
			return values;
		} else if (spieler instanceof JamesWade) {
			values[0] = JamesWade.bullprobs;
			values[1] = JamesWade.checkoutpercentage;
			values[2] = JamesWade.bignumberpercentage;
			return values;
		} else if (spieler instanceof JoseDeSousa) {
			values[0] = JoseDeSousa.bullprobs;
			values[1] = JoseDeSousa.checkoutpercentage;
			values[2] = JoseDeSousa.bignumberpercentage;
			return values;
		} else if (spieler instanceof RossSmith) {
			values[0] = RossSmith.bullprobs;
			values[1] = RossSmith.checkoutpercentage;
			values[2] = RossSmith.bignumberpercentage;
			return values;
		} else if (spieler instanceof GaryAnderson) {
			values[0] = GaryAnderson.bullprobs;
			values[1] = GaryAnderson.checkoutpercentage;
			values[2] = GaryAnderson.bignumberpercentage;
			return values;
		} else {
			System.out.println("Ein Fehler ist aufgetreten");
			return null;
		}
	}
}