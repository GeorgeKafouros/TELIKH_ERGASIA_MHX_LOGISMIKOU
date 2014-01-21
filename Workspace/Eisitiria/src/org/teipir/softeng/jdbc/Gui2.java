package org.teipir.softeng.jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.teipir.softeng.jdbc.Gui2;

public class Gui2 {
	Connection con;
	Statement st;
	ResultSet rs;
	
	JFrame f=new JFrame("forma ektypwshs");
	JLabel l=new JLabel("onoma parastasis:");
	JLabel l1=new JLabel("hmerominia:");
	JLabel l2=new JLabel("wra:");
	JTextField t=new JTextField(10);
	JTextField t1=new JTextField(10);
	JTextField t2=new JTextField(10);
	JButton prt=new JButton("Print");
	JButton b1=new JButton("Next");
	JButton b2=new JButton("Previous");
	JLabel l3=new JLabel("discount");
	JLabel l4=new JLabel("theseis");
	JLabel l5=new JLabel("");
	
	String[] items1={"1","2","3","4","5","6","7","8","9","10"};
	String[] items={"Kanoniko","Foititis","Anilikos"};
	JComboBox c1=new JComboBox(items1);
	JComboBox c=new JComboBox(items);
	

	public void connect()
	{
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl="jdbc:mysql://localhost:3306/db1";
			String connectionUser = "root";
			String connectionPassword = "123123";
		    con = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
		    
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		String sql="select * from shows";
		rs=st.executeQuery(sql);
		
		
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class!");
			 System.exit(1);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public Gui2(){
		connect();
		frame();
		btnAction();
		
	}
	public void frame()
	{
		f.setSize(600,200);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		
		
		JPanel p=new JPanel();
		
		p.add(l);
		p.add(t);
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(t2);
		p.add(b1);
		p.add(b2);
		p.add(l4);
		p.add(c1);
		p.add(l3);
		p.add(c);
		p.add(prt);
	    p.add(l5);
		f.add(p);
	    
	    prt.addActionListener(new ActionListener(){
	    	
	    	public void actionPerformed(ActionEvent e){
	    		
	    		String s=c.getSelectedItem().toString();
	    		String s1=c1.getSelectedItem().toString();
	    		double discount;
	    		if (s.equals("Kanoniko")) {
	    			discount=1; }
	    		else if (s.equals("Foititis")) 
	    		{
	    			discount=0.75;
	    					}
	    		else {
	    			discount=0.5;}
	    		
	    		
	    		double timh=20;
	    		
	    		double telikhTimh = timh*Integer.parseInt(s1)*discount;
	    		try {
					

JOptionPane.showMessageDialog(null," Θέση: "+s1+"  Είδος έκπτωσης: "+s+ "  Όνομα παράστασης: "+rs.getString("act")+"  Ημερομηνία: "+rs.getString("day")+"  Ώρα: "+rs.getString("time")+"  Τελική τιμή:"+telikhTimh);

				
					
					
				} catch (SQLException e1) {
					
					
				}
	    		
	    		
	    	}
	    }
	    );
	    
	    try{
	    	rs.next();
	    	t.setText(rs.getString("act"));
	    	t1.setText(rs.getString("day"));
	    	t2.setText(rs.getString("time"));
	    	
	    	
	    	String s=c.getSelectedItem().toString();
    		String s1=c1.getSelectedItem().toString();
			
	    }catch(Exception ex){
	    	
	    }
	    
	    
	    
	    
	}
		
	    public void btnAction()
		{
			b1.addActionListener(new ActionListener(){
				public void 

				actionPerformed(ActionEvent e)
				{
					try{
						
						if (rs.next()){
								
							t.setText(rs.getString("act"));				
							t1.setText(rs.getString("day"));
							t2.setText(rs.getString("time"));
							
							String s=c.getSelectedItem().toString();
				    		String 
				    		s1=c1.getSelectedItem().toString();
							
						}
						
						else
						{
							
							rs.previous();
							JOptionPane.showMessageDialog(null,"No more records");
							
						}
							
							
					}catch(Exception ex)
					{
						
					}
					
						
				}
			});
			
			
			b2.addActionListener(new ActionListener(){
				public void 

				actionPerformed(ActionEvent e)
				{
					try{
						
						if (rs.previous()){
							
							t.setText(rs.getString("act"));
							t1.setText(rs.getString("day"));
							t2.setText(rs.getString("time"));
							
						}
						
						else
						{
							rs.next();
							JOptionPane.showMessageDialog(null,"No more records");
							
							
						}
							
							
					}catch(Exception ex)
					{
						
					}
									
				}
			});
			
			
			
			
			}
			public static void main(String[] args){
				
				
				new Gui2();
				
				
				}

}
