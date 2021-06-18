package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Event extends JFrame implements ActionListener{
	Container cn;
	JLabel lb;
	TextArea ta;
	JButton bt;
	String TIME;
	
	String evt[][] = new String[10000][2];
	int N = 0;
	
	public Event(String time) {
		this.TIME = time;
		readEvent();
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout());
		lb = new JLabel(TIME);
		lb.setFont(new Font("Algerian", 1, 40));
		pn.add(lb);
		
		ta = new TextArea();
		ta.setFont(new Font("Algerian", 1, 20));
		
		bt = new JButton("New Event");
		bt.addActionListener(this);
		bt.setFont(new Font("Algerian", 1, 15));
		bt.setBackground(Color.LIGHT_GRAY);
		
		updateEvent(TIME);
		
		this.setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 398);
		this.setLocationRelativeTo(null);
		Point p = this.getLocation();
		this.setLocation((int)p.getX() + 245, (int)p.getY());
		
		cn.add(pn, "North");
		cn.add(ta);
		cn.add(bt, "South");
		
		return cn;
	}
	
	public void readEvent() {
		N = 0;
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
					while (s[1].indexOf("  ") >= 0)
						s[1] = s[1].replace("  ", " ");
					if (s.length == 2) {
						evt[N][0] = s[0];
						evt[N ++][1] = s[1];
					}
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
	
	public void writeEvent() throws IOException {
		try {
			File file = new File("Event.txt");
	        OutputStream outputStream;
			outputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			for (int i = 0; i < N; i++)
				outputStreamWriter.write(evt[i][0] + ": " + evt[i][1] + "\n");
	        outputStreamWriter.flush();
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
	
	public void addEvent(String time, String ev) {
		boolean kt = true;
		for (int i = 0; i < N; i++)
			if (time.equals(evt[i][0])) {
				kt = false;
				evt[i][1] = evt[i][1] + ", " + ev;
			}
		if (kt) {
			evt[N][0] = time;
			evt[N ++][1] = ev;
		}
	}
	
	public void updateEvent(String time) {
		TIME = time;
		lb.setText(time);
		ta.setText(getEvent(time));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = JOptionPane.showInputDialog("More events for " + TIME);
		if (str.length() > 0) {
			addEvent(TIME, str);
			try {
				writeEvent();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			updateEvent(TIME);
//			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new Event("27-12-2000");
	}
}
