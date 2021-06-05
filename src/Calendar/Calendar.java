package Calendar;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calendar extends JFrame implements ActionListener {
	Container cn;
	JButton bt[][] = new JButton[7][7];
	JComboBox ch;
	JTextField tf;
	Timer timer;
	
	String w[] = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
	String t[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public Calendar() {
		super("Calendar - HaiZuka");
		cn = init();
	}
	
	int preMonth = 5;
	String preYear = "   2021   ";
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(7, 7));
		
		for (int i = 0; i < 7; i ++)
			for (int j = 0; j < 7; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				bt[i][j].setFont(new Font("Britannic Bold", 1, 25));
				bt[i][j].setBackground(Color.black);
				bt[i][j].setForeground(Color.white);
				bt[i][j].setBorder(null);
				pn.add(bt[i][j]);
			}
		for (int i = 0; i < 7; i++)
			bt[0][i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.cyan));
		cn.add(pn);
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());
		pn2.setBackground(Color.black);
		
		ch = new JComboBox<>();
//		ch.setBackground(Color.black);
		ch.setBackground(null);
		ch.setFont(new Font("Britannic Bold", 1, 20));
		for (int i = 0; i < 12; i++)
			ch.addItem(t[i]);
		ch.setSelectedIndex(preMonth);
		
		tf = new JTextField("    2021    ");
		tf.setBackground(Color.black);
		tf.setForeground(Color.white);
		tf.setBorder(null);
		tf.setFont(new Font("Britannic Bold", 1, 20));
		tf.setHorizontalAlignment(JTextField.CENTER);
		
		pn2.add(ch);
		pn2.add(tf);
		
		cn.add(pn2, "North");
		
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		
		timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = ch.getSelectedIndex();
				String str = tf.getText();
				while (str.charAt(0) == ' ')
					str = str.substring(1, str.length() - 1);
				while (str.charAt(str.length() - 1) == ' ')
					str = str.substring(0, str.length() - 2);
//				System.out.println(m + " " + str);
				if (str.matches("[0-9]*") && (m != preMonth || !str.equals(preYear))) {
//					System.out.println("Ok");
					ch.setSelectedIndex(m);
					update(m + 1, Integer.parseInt(str));
					preMonth = m;
					preYear = str;
				}
			}
		});
		
		return cn;
	}
	
	public void update(int month, int year) {
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
		
		for (int i = 0; i < 7; i++)
			bt[0][i].setText(w[i]);
		int I = 1, J = start;
		for (int i = 1; i <= Nday(month, year); i++) {
			bt[I][J].setText(i + "");
			bt[I][J].setForeground(Color.white);
			J++;
			if (J == 7) {
				J = 0;
				I++;
			}
		}
		for (int i = start - 1; i >= 0; i--) {
			bt[1][i].setText(pday-- + "");
			bt[1][i].setForeground(Color.gray);
		}
		int st = 1;
		while (!(I == 7 && J == 0)) {
			bt[I][J].setText(st++ + "");
			bt[I][J].setForeground(Color.gray);
			J++;
			if (J == 7) {
				J = 0;
				I++;
			}
		}
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
		new Calendar().timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
