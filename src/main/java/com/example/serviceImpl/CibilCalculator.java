package com.example.serviceImpl;

import java.util.Random;

import com.example.entity.Cibil;
import com.example.entity.Enquiry;

public class CibilCalculator {

	public static int generateRandomCibilScore() {
		int min = 300;
		int max = 900;
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	public static Cibil evaluateCibilScore(Enquiry enquiry) {

		int cibilScore = generateRandomCibilScore();

		Cibil cibil = new Cibil();

		if (cibilScore >= 750) {
			cibil.setStatus("Excellent");
			cibil.setCibilRemark("Eligible for best credit offers.");
			cibil.setCibilScore(cibilScore);
			System.out.println(cibil);
			return cibil;
		} else if (cibilScore >= 650) {
			cibil.setStatus("Good");
			cibil.setCibilRemark("Moderate risk. Eligible for most offers.");
			cibil.setCibilScore(cibilScore);
			System.out.println(cibil);
			return cibil;
		} else if (cibilScore >= 550) {
			cibil.setStatus("Fair");
			cibil.setCibilRemark("Higher risk. Might need guarantees or higher interest.");
			cibil.setCibilScore(cibilScore);
			System.out.println(cibil);
			return cibil;
		} else {
			cibil.setStatus("Poor");
			cibil.setCibilRemark("Low score. Credit offers may be limited.");
			cibil.setCibilScore(cibilScore);
			System.out.println(cibil);
			return cibil;
		}
	}
}
