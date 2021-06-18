package Main;

import java.awt.Color;
import java.math.BigInteger;

public class Blog {
	
	public static boolean isLeapYear(int  N) {
		if (N % 4 == 0 && N % 100 != 0)
			return true;
		if (N % 400 == 0)
			return true;
		return false;
	}
	
	public static int Nday(int month, int year) {
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if (isLeapYear(year))
					return 29;
				return 28;
			}
		return 0;
	}
	
	public static int getDay(int month, int year) {
		int N = year - 1;
		int d = N * 365 + N / 4 - N / 100 + N / 400;
		for (int i = 1; i < month; i++)
			d += Nday(i, N + 1);
		return d;
	}
	
	public static int getThu(int month, int year) {
		return getDay(month, year) % 7 + 2;
	}
	
	public static int[][]  update(int month, int year) {
		int a[][] = new int[6][7];
		int thu = getThu(month, year);
		int day = Nday(month, year);
		int pday = 0;
		if (month > 1)
			pday = Nday(month - 1, year);
		else
			pday = Nday(12, year - 1);
		int start = thu - 1;
		if (start == 7)
			start = 0;
		
		int I = 0, J = start;
		for (int i = 1; i <= day; i++) {
			a[I][J] = i;
			J++;
			if (J == 7) {
				J = 0;
				I++;
			}
		}
		for (int i = start - 1; i >= 0; i--) {
			a[0][i] = pday--;
		}
		int st = 1;
		while (!(I == 6 && J == 0)) {
			a[I][J] = st++;
			J++;
			if (J == 7) {
				J = 0;
				I++;
			}
		}
		return a;
	}
	
	
	public static void main(String[] args) {
		int a[][] = update(6, 2021) ;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
	}
}
