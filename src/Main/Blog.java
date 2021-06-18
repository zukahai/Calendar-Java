package Main;

import java.math.BigInteger;

public class Blog {
	
	public boolean isLeapYear(int  N) {
		if (N % 4 == 0 && N % 100 != 0)
			return true;
		if (N % 400 == 0)
			return true;
		return false;
	}
	
	public int Nday(int month, int year) {
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
	
	
	public static void main(String[] args) {
		
	}
}
