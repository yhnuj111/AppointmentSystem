import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class calendar extends JFrame{
	private JLabel time;
	private JPanel cpanel;
	private JPanel mpanel;
	private JButton cbutton;
	private Calendar c = Calendar.getInstance();
	private int year = Calendar.getInstance().get(Calendar.YEAR);
	private int month = Calendar.getInstance().get(Calendar.MONTH);
	private int date = Calendar.getInstance().get(Calendar.DATE);
	private String Year = ""+year;
	private String Month = ""+(month+1);
	private static final int FRAME_WIDTH = 700;
	private static final int FRAME_HEIGHT = 700;
	
	public calendar() {
		this.panel();
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	public void panel() {
		c.set(year, month, 1);
		cpanel = cpanel(month);
		add(cpanel, BorderLayout.CENTER);
		mpanel = new JPanel();
		JButton pre = createJbutton();
		pre.setText("previous");
		JButton cur = createJbutton();
		cur.setText("current");
		JButton nex = createJbutton();
		nex.setText("next");
		time = new JLabel();
		time.setText(date());
		mpanel.add(time);
		mpanel.add(pre);
		mpanel.add(cur);
		mpanel.add(nex);
		add(mpanel, BorderLayout.NORTH);
		
	}
	public JButton createJbutton() {
		class listener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton a = (JButton)e.getSource();
				if(a.getText().equals("previous")){
					cpanel.setVisible(false);
					month--;
					monthd();
					cpanel = cpanel(month);
					add(cpanel, BorderLayout.CENTER);
					String n = date();
					time.setText(n);
				}
				else if(a.getText().equals("next")){
					cpanel.setVisible(false);
					month++;
					monthi();
					cpanel = cpanel(month);
					add(cpanel, BorderLayout.CENTER);
					String n = date();
					time.setText(n);
				}
				else if(a.getText().equals("current")){
					
					month = Calendar.getInstance().get(Calendar.MONTH);
					year = Calendar.getInstance().get(Calendar.YEAR);
					date = Calendar.getInstance().get(Calendar.DATE);
					Year = ""+year;
					Month = "" + (month+1);
					c.set(year, month, 1);
					cpanel.setVisible(false);
					cpanel = cpanel(month);
					add(cpanel, BorderLayout.CENTER);
					String n = date();
					time.setText(n);
				}
			}
		}
		JButton button = new JButton();
		ActionListener m = new listener();
		button.addActionListener(m);
		return button;
	}
	public JPanel cpanel(int a){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7,7));;
		for(int i = 0; i < 7; i++){
			if(i==0){
				JButton button = new JButton("SUN");
				panel.add(button);
			}
			else if(i==1){
				JButton button = new JButton("MON");
				panel.add(button);
			}
			else if(i==2){
				JButton button = new JButton("TUE");
				panel.add(button);
			}
			else if(i==3){
				JButton button = new JButton("WED");
				panel.add(button);
			}
			else if(i==4){
				JButton button = new JButton("THU");
				panel.add(button);
			}
			else if(i==5){
				JButton button = new JButton("FRI");
				panel.add(button);
			}
			else if(i==6){
				JButton button = new JButton("SAT");
				panel.add(button);
			}
		}
		int j=1;
		for (int i = 0; i <42; i++) {
			if(i<c.get(Calendar.DAY_OF_WEEK)-1||i>=(c.get(Calendar.DAY_OF_WEEK)+c.getActualMaximum(Calendar.DAY_OF_MONTH)-1)){
				JButton button = new JButton();
				panel.add(button);
				
			}
			else{
					JButton button = createJbutton();
					button.setText(""+j);
					if(j==date&&month==Calendar.getInstance().get(Calendar.MONTH)&&year==Calendar.getInstance().get(Calendar.YEAR)){
						button.setForeground(Color.RED);
					}
					panel.add(button);
					j++;
			}
			
		}
		return panel;
	}
	public String date(){
		return Month + " " + Year;
	}
	public void monthd(){
		if(month==-1){
			year--;
			c.set(year, 11, 1);
			Year = ""+year;
			Month = ""+12;
			month=11;
		}
		else {
			c.set(year, month, 1);
			Year = ""+year;
			Month = ""+(month+1);
		}
	}
	public void monthi(){
		if(month==12){
			year++;
			c.set(year, 0, 1);
			Year = ""+year;
			Month = ""+1;
			month=0;
		}
		else {
			c.set(year, month, 1);
			Year = ""+year;
			Month = ""+(month+1);
		}
	}
}
