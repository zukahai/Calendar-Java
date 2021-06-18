package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Event extends JFrame{
	Container cn;
	JLabel lb;
	TextArea ta;
	JButton bt;
	String time;
	
	String evt[][] = new String[10000][2];
	int N = 0;
	
	public Event(String time) {
		this.time = time;
		readEvent();
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout());
		lb = new JLabel(time);
		lb.setFont(new Font("Algerian", 1, 40));
		pn.add(lb);
		
		ta = new TextArea();
		ta.setFont(new Font("Algerian", 1, 20));
		
		bt = new JButton("New Event");
		bt.setFont(new Font("Algerian", 1, 15));
		bt.setBackground(Color.LIGHT_GRAY);
		
		updateEvent(time);
		
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 398);
		this.setLocationRelativeTo(null);
		
		cn.add(pn, "North");
		cn.add(ta);
		cn.add(bt, "South");
		
		return cn;
	}
	
	
	
	public void readEvent() {
		String FILE_URL = "Event.txt";
    	File file = new File(FILE_URL);
        InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        BufferedReader reader = new BufferedReader(inputStreamReader);
	 
	        String line = "";
	        try {
				while((line = reader.readLine()) != null){
					String s[] = line.split(":");
					evt[N][0] = s[0];
					evt[N ++][1] = s[1];
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getEvent(String time) {
		for (int i = 0; i < N; i++)
			if (time.equals(evt[i][0])) {
				String str = evt[i][1];
				str = str.replace(",", "\n");
				return str;
			}
		return "No event";
	}
	
	public void updateEvent(String time) {
		lb.setText(time);
		ta.setText(getEvent(time));
		
	}
	
	public static void main(String[] args) {
		new Event("27-12-2000");
	}
}
