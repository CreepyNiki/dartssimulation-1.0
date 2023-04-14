package de.halft.niklas;

import de.halft.niklas.Dartsscheibe;
import de.halft.niklas.MichaelvanGerwen;
import java.util.Random;
import de.halft.niklas.Dartsmatch;

public abstract class Dartsspieler {

	public String speed;
	public String nickname;
	public int age;
	public String nationality;
	public int ofm;
	public String darts;
	public String name;
	public String vorname;
	public double[] scoringprobs;
	public double bullprobs = 30.53;
	public double checkoutpercentage = 39.56;
	public double bignumberpercentage = 97.52;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public int score(double[] scoringprobs) {
		int temp = 0;
		int score = 0;
		int dart = 0;
		for (int i = 3; i > 0; i--) {
			double d1 = new Random().nextDouble();
//			System.out.println(scoringprobs[1]);
			if (d1 < scoringprobs[0] / 100) {
				dart = 1;
//				System.out.println(dart);
			} else if (d1 < scoringprobs[4] / 100 && d1 > scoringprobs[0] / 100) {
				dart = 5;
//				System.out.println(dart);
			} else if (d1 < scoringprobs[18] / 100 && d1 > scoringprobs[4] / 100) {
				dart = 19;
//				System.out.println(dart);
			} else if (d1 < scoringprobs[19] / 100 && d1 > scoringprobs[18] / 100) {
				dart = 20;
//				System.out.println(dart);
			} else if (d1 < scoringprobs[38] / 100 && d1 > scoringprobs[19] / 100) {
				dart = 54;
//				System.out.println(dart);
			} else if (d1 < scoringprobs[39] / 100 && d1 > scoringprobs[38] / 100) {
				dart = 57;
			} else {
				dart = 60;
//				System.out.println(dart);
			}
			temp = dart;
			score += dart;
//			System.out.println(dart);
		}
//		System.out.println(score);
		return score;
	}

	public int[] finish(int restscore, double[] scoringprobs, double[] probs) {
		String[][] cot2 = Dartsscheibe.checkouttable;
		int[] dartsboard = Dartsscheibe.dartsboard;
		String target1;
		int score = 0;
		int dart = 0;
		int targetint = 0;
		int rest = restscore;
		int temp = 0;
		int[] values = new int[3];
		int dartcounter = 0;

		for (dartcounter = 0; dartcounter < 3; dartcounter++) {

			if (dartcounter == 2 && restscore == 50) {
				targetint = 50;
				double d1 = new Random().nextDouble();
//				System.out.println(d1);
				if (d1 < probs[0] / 100) {
					dart = targetint;
					System.out.println(dart);
					restscore = restscore - dart;
					temp = score;
					score = temp + dart;
					dartcounter = 3;

				} else {
					dart = targetint / 2;
					System.out.println(dart);
					restscore = restscore - dart;
					temp = score;
					score = temp + dart;
					values[0] = dartcounter;
					values[1] = restscore;
					values[2] = score;
					return values;
				}
			}
//			System.out.println(dartcounter);
//			System.out.println(restscore);

			if (restscore == 169 || restscore == 168 || restscore == 166 || restscore == 165 || restscore == 163
					|| restscore == 162 || restscore == 159) {
				dart = scoreonedart(scoringprobs);
				restscore = restscore - dart;
				temp = score;
				score = temp + dart;
				values[0] = dartcounter;
				values[1] = restscore;
				values[2] = score;
				if(dartcounter == 0) {
				dartcounter = 1;
			}else if (dartcounter == 1) {
				dartcounter = 2;
				
			} else if (dartcounter == 2){
				dartcounter = 3;
			}else {
				System.out.println("Ein Fehler ist aufgetreten");
			}
			}	
				
			if (restscore >= 2) {
				target1 = Dartsscheibe.arrayeinzelnewerte(cot2, restscore, 1);
//		System.out.println(target1);
				if (target1.contains("T")) {
//				System.out.println("ES IST EIN TRIPLE");
					String substring = target1.substring(1);
					targetint = Integer.parseInt(substring) * 3;
//					System.out.println(targetint);

					double d1 = new Random().nextDouble();
//					System.out.println(d1);
					if (d1 < scoringprobs[0] / 100) {
						for (int i = 0; i < dartsboard.length; i++) {
							if (targetint / 3 == dartsboard[i]) {
								dart = dartsboard[i + 1];
								System.out.println(dart);
								if (targetint / 3 == dartsboard[i]) {
									try {
										dart = dartsboard[i + 1];
										System.out.println(dart);
										restscore = restscore - dart;
										temp = score;
										score = temp + dart;

									} catch (ArrayIndexOutOfBoundsException e) {
										dart = dartsboard[1];
										System.out.println(dart);
										restscore = restscore - dart;
										temp = score;
										score = temp + dart;

									}
								}
							}
						}
					} else if (d1 < scoringprobs[18] / 100 && d1 > scoringprobs[0] / 100) {
						for (int i = 0; i < dartsboard.length; i++) {
							if (targetint / 3 == dartsboard[i]) {
								try {
									dart = dartsboard[i - 1];
									System.out.println(dart);
									restscore = restscore - dart;
									temp = score;
									score = temp + dart;
								} catch (ArrayIndexOutOfBoundsException e) {
									dart = dartsboard[20];
									System.out.println(dart);
									restscore = restscore - dart;
									temp = score;
									score = temp + dart;

								}
							}
						}
					} else if (d1 < scoringprobs[19] / 100 && d1 > scoringprobs[18] / 100) {
						dart = targetint / 3;
						System.out.println(dart);
						restscore = restscore - dart;
						temp = score;
						score = temp + dart;

					} else {
						dart = targetint;
						System.out.println(dart);
						restscore = restscore - dart;
						temp = score;
						score = temp + dart;

					}

				} else if (target1.contains("D")) {
//					System.out.println("ES IST EIN DOPPEL");
					String substring = target1.substring(1);
					targetint = Integer.parseInt(substring) * 2;
//					System.out.println(targetint);

					double d1 = new Random().nextDouble();
//					System.out.println(d1);
					if (d1 < probs[1] / 100) {
						dart = targetint;
						System.out.println(dart);
						restscore = restscore - dart;
						temp = score;
						score = temp + dart;

					} else {
						double d2 = new Random().nextDouble();
//						System.out.println(d2);
						if (d2 < 0.5) {
							dart = targetint / 2;
							System.out.println(dart);
							restscore = restscore - dart;
							temp = score;
							score = temp + dart;

						} else if (d2 > 0.5) {
							dart = 0;
							System.out.println(dart);
							restscore = restscore - dart;
							temp = score;
							score = temp + dart;

						}
					}

				} else if (target1.contains("S")) {
//					System.out.println("ES IST EIN SINGLE");
					String substring = target1.substring(1);
					targetint = Integer.parseInt(substring);
//					System.out.println(targetint);

					double d1 = new Random().nextDouble();
//					System.out.println(d1);

					if (d1 < probs[2] / 100) {
						dart = targetint;
						System.out.println(dart);
						restscore = restscore - dart;
						temp = score;
						score = temp + dart;

					} else {
						double d2 = new Random().nextDouble();
//						System.out.println(d2);
						if (d2 < 0.5) {

							for (int i = 0; i < dartsboard.length; i++) {
								if (targetint == dartsboard[i]) {
									try {
										dart = dartsboard[i - 1];
										System.out.println(dart);
										restscore = restscore - dart;
										temp = score;
										score = temp + dart;

									} catch (ArrayIndexOutOfBoundsException e) {
										dart = dartsboard[20];
										System.out.println(dart);
										restscore = restscore - dart;
										temp = score;
										score = temp + dart;
									}
								}
							}

						} else if (d2 > 0.5) {

							for (int i = 0; i < dartsboard.length; i++) {
								if (targetint == dartsboard[i]) {
									try {
										dart = dartsboard[i + 1];
										System.out.println(dart);
										restscore = restscore - dart;
										temp = score;
										score = temp + dart;
									} catch (ArrayIndexOutOfBoundsException e) {
										dart = dartsboard[1];
										System.out.println(dart);
										restscore = restscore - dart;
										temp = score;
										score = temp + dart;

									}
								}
							}

						}
					}
				}

			} else if (restscore == 0) {
//				System.out.println("Gecheckt mit " + dartcounter + " Darts");
				values[0] = dartcounter;
				values[1] = restscore;
				values[2] = score;
				return values;
			} else {
				System.out.println("Bust");
				restscore = rest;
				values[0] = dartcounter;
				values[1] = restscore;
				values[2] = 0;
				return values;
			}
		}

		if (restscore == 0) {
//				System.out.println("Gecheckt mit " + dartcounter + " Darts");
			values[0] = dartcounter;
			values[1] = restscore;
			values[2] = score;
			return values;

		} else if (restscore != 0 && restscore < 2) {
			System.out.println("Bust");
			restscore = rest;
			values[0] = dartcounter;
			values[1] = restscore;
			values[2] = 0;

			return values;
		} else {
			values[0] = dartcounter;
			values[1] = restscore;
			values[2] = score;
			return values;
		}
		}

	public int scoreonedart(double[] scoringprobs) {
		int score = 0;
		int dart = 0;
		for (int i = 1; i >= 0; i--) {
			double d1 = new Random().nextDouble();
			if (d1 < scoringprobs[0] / 100) {
				dart = 1;
			} else if (d1 < scoringprobs[4] / 100 && d1 > scoringprobs[0] / 100) {
				dart = 5;
			} else if (d1 < scoringprobs[18] / 100 && d1 > scoringprobs[4] / 100) {
				dart = 19;
			} else if (d1 < scoringprobs[19] / 100 && d1 > scoringprobs[18] / 100) {
				dart = 20;
			} else if (d1 < scoringprobs[38] / 100 && d1 > scoringprobs[19] / 100) {
				dart = 54;
			} else if (d1 < scoringprobs[39] / 100 && d1 > scoringprobs[38] / 100) {
				dart = 57;
			} else {
				dart = 60;
			}
			score = dart;
		}
		System.out.println(score);
		return score;
	}

	public void celebrate() {

	}

	public static int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public abstract void walkon();
}
