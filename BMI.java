import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.time.*;

class BMI extends JFrame implements ActionListener,ItemListener
{
	private JTextField get_name;
	private JTextField text_height;
	private JTextField text_weight;
	private JComboBox date;
	private JComboBox month;
	private JComboBox year;
	private JComboBox size;
	private JComboBox wgh;
	private JButton submit_btn;
	private JButton reset_btn;
	double h,w,r;


	String name="",age="",height="",weight="",result="";
	String day,monthh,yearr;
	int d,m,y,agee;

	private String dates[]
           = { "1", "2", "3", "4", "5",
               "6", "7", "8", "9", "10",
               "11", "12", "13", "14", "15",
               "16", "17", "18", "19", "20",
               "21", "22", "23", "24", "25",
               "26", "27", "28", "29", "30",
               "31" };
    private String months[]
           = { "Jan", "feb", "Mar", "Apr",
               "May", "Jun", "July", "Aug",
               "Sep", "Oct", "Nov", "Dec" };
    private String years[]
           = { "1987", "1988", "1989", "1990",
             "1991", "1992", "1993", "1994",
             "1995", "1996", "1997", "1998",
               "1999", "2000", "2001", "2002",
               "2003", "2004", "2005", "2006",
               "2007", "2008", "2009", "2010",
               "2011", "2012", "2013", "2014",
               "2015", "2016", "2017", "2018",
               "2019","2020","2021","2022","2023","2024"};
    private String sizes[]={"CM","M"};
	private String wghs[]={"KG","LBS"};
	
	public BMI()
	{
		super("BMI CALCULATOR");
		setSize(400,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel head=new JLabel("BMI Calculater");
		head.setFont(new Font("Serif", Font.BOLD, 20));
		head.setBounds(90, 10, 150, 30);
		add(head);
		
		JLabel name=new JLabel ("Enter your name");
		name.setBounds(20, 50, 120, 20);
		add(name);
		
		get_name=new JTextField();
		get_name.setBounds(150, 50, 120, 20);
		add(get_name);
		get_name.setColumns(10);
		
		JLabel Enter_date= new JLabel("Enter Date") ;
		Enter_date.setBounds(20, 80, 120, 20);
		add(Enter_date);
		
		date= new JComboBox(dates);
		date.setBounds(150, 80, 50, 20);
		add(date);
		
		month=new JComboBox(months);
		month.setBounds(210, 80, 60, 20);
		add(month);
		
		year=new JComboBox(years);
		year.setBounds(280, 80, 60, 20);
		add(year);
		
		JLabel hight=new JLabel("Enter hight");
		hight.setBounds(20, 110, 120, 20);
		add(hight);
		
		text_height=new JTextField();
		text_height.setBounds(150, 110, 50, 20);
		add(text_height);
		text_height.setColumns(10);
		
		size=new JComboBox(sizes);
		size.setBounds(210, 110, 60, 20);
		add(size);
		
		JLabel weight=new JLabel("Enter Weight");
		weight.setBounds(20, 140, 120, 20);
		add(weight);
		
		text_weight=new JTextField();
		text_weight.setBounds(150, 140, 50, 20);
		add(text_weight);
		
		wgh=new JComboBox(wghs);
		wgh.setBounds(210, 140, 60, 20);
		add(wgh);
		text_weight.setColumns(10);
		
		submit_btn=new JButton("Submit");
		submit_btn.setBounds(50,190,100,20);
		add(submit_btn);
		
		submit_btn.addActionListener(new ActionListener() {

			// Submit Button Event
			public void actionPerformed(ActionEvent e) {

				if (get_name.getText().isEmpty() || text_height.getText().isEmpty()
						|| text_weight.getText().isEmpty()) {
					error();

					return;
				}
				convertUnits();
				calculate_bmi();

				openResultFrame();
			}
		});
		
		size.addItemListener(this);
		wgh.addItemListener(this);

		reset_btn=new JButton("Reset");
		reset_btn.setBounds(250,190,100,20);
		add(reset_btn);
		reset_btn.addActionListener(new ActionListener() {
			//Reset Button Event
			public void actionPerformed(ActionEvent e) {
				get_name.setText("");
				text_height.setText("");
				text_weight.setText("");
				date.setSelectedIndex(0);
				month.setSelectedIndex(0);
				year.setSelectedIndex(0);
				size.setSelectedIndex(0);
				wgh.setSelectedIndex(0);
			}
		});
		

		setLayout(null);
		setVisible(true);
	}//Constructor Close

	//BMI calulation method
	public void calculate_bmi(){
		if(get_name.getText().isEmpty() || text_height.getText().isEmpty() || text_weight.getText().isEmpty())
		{
			error();
			return;
		}

		name = get_name.getText();
		
		DecimalFormat df = new DecimalFormat("###.##");

		r=w/(h*h);
		result += String.valueOf(df.format(r));

		if (r >= 25)
			result += "\t Overweight \n You have a higher than normal body weight. \nTry to exercise more.";
		else if (r >= 18.5 && r < 25)
			result += "\t Normal \n You have normal body weight.\n Good Job!.";
		else
			result += "\t Underweight \n You have a lower than normal body weight.\n You can eat a it more.";

		/* JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f,result);
 */
	}


	public void itemStateChanged(ItemEvent e){
		convertUnits(); //COnvert height and weight as per units
	}

	public void convertUnits(){
		if(size.getSelectedIndex() == 0 && wgh.getSelectedIndex() == 0){
		height = text_height.getText();
		h = Double.parseDouble(height); //cms
		h= h / 100;

		weight = text_weight.getText();
		w = Double.parseDouble(weight); //kgs
		}
		else if(size.getSelectedIndex() == 0 && wgh.getSelectedIndex() == 1){
			height = text_height.getText();
			h = Double.parseDouble(height); //cms
			h= h / 100; // cm to Meter
			
			weight = text_weight.getText();
			w = Double.parseDouble(weight); // lbs
			w = w * 0.453592;//lbs to kg

		}
		else if(size.getSelectedIndex() == 1 && wgh.getSelectedIndex() == 0){
			weight = text_weight.getText();
			w = Double.parseDouble(weight); //Kgs
			

			height = text_height.getText();
			h = Double.parseDouble(height); // meters
		}
		else if(size.getSelectedIndex() == 1 && wgh.getSelectedIndex() == 1){
			weight = text_weight.getText();
			w = Double.parseDouble(weight);//weight in lbs
			w = w * 0.453592;//lbs to kg

			height = text_height.getText();
			h = Double.parseDouble(height); // height in meters
		}
	}

	public void error(){
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "Fill all the details","Alert", JOptionPane.WARNING_MESSAGE);
	}

	//For opening Result Frame
	public void openResultFrame(){
		Result resultframe = new Result(name,w,h,r);	
		resultframe.setVisible(true);
	}
	
	public static void main(String args[])
	{
		BMI z=new BMI();
	}
}
