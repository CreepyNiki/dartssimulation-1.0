package de.halft.niklas;
import org.apache.xml.security.transforms.implementations.TransformC14N;
import java.io.*;
import de.halft.niklas.*;
import java.util.ArrayList;

public class Application {

	public static void main(String[] args) {
	Dartsspieler Gary = new GaryAnderson();
	Dartsspieler MVG = new MichaelvanGerwen();
	Dartsmatch game = new Dartsmatch();
	
	double[] d1 = game.getvalues(MVG);
	double[] d2 = game.getvalues(Gary);
	game.playmatch(Gary, MVG);
	}
}