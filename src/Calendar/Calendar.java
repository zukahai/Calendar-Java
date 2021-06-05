package Calendar;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calendar extends JFrame implements ActionListener {
	Container cn;
	JButton bt[][] = new JButton[7][7];
	public Calendar() {
		super("Calendar");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(7, 7));
		
		for (int i = 0; i < 7; i ++)
			for (int j = 0; j < 7; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				pn.add(bt[i][j]);
			}
		cn.add(pn);
		
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		
		return cn;
	}
	
	public boolean isLeapYear(int N) {
		if (N % 4 == 0 && N % 100 != 0)
			return true;
		if (N % 400 == 0)
			return true;
		return false;
	}
	
	public int getThu(int month, int year) {
		int d = 0;
		for (int i = 1; i <= year; i++)
			if (isLeapYear(i))
				d += 366;
			else
				d += 365;
		for (int i = 1; i < month; i++)
			d += Nday(i, year);
		return (d - 1) % 7 + 2;
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
		new Calendar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
