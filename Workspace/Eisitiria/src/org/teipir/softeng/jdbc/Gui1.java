package org.teipir.softeng.jdbc;

import javax.swing.*;
import org.teipir.softeng.jdbc.Gui1;
import java.awt.event.*;
import java.sql.*;


public class Gui1 {

	Connection con;
	Statement st;
	ResultSet rs;
	
	JFrame f=new JFrame("diaxirisi");
	JLabel l=new JLabel("onoma parastasis:");
	JLabel l1=new JLabel("hmerominia:");
	JLabel l2=new JLabel("wra:");
	JTextField t=new JTextField(10);
	JTextField t1=new JTextField(10);
	JTextField t2=new JTextField(10);
	
	JButton b1=new JButton("Next");
	JButton b2=new JButton("Previous");
	JButton ud=new JButton("Update");
	JButton del=new JButton("Delete");
	JButton nr=new JButton("New");
	JButton save=new JButton("Save");
	
	
	
	
	 
	public void connect()
	{ 
		try
		{
			
		
			Class.forName("com.mysql.jdbc.Driver");			
			String connectionUrl="jdbc:mysql://localhost:3306/db1";
			String connectionUser = "root";
			String connectionPassword = "123123";
		    con = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
				
		
		st=con.createStatement();
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
	public Gui1(){
		connect();
		frame();
		btnAction();
		
	}
	public void frame()
	{
		f.setSize(140,380);
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
		p.add(ud);
		p.add(del);
		p.add(nr);
		p.add(save);
		f.add(p);
		
		 try{
		rs.next();
    	t.setText(rs.getString("act"));
    	t1.setText(rs.getString("day"));
    	t2.setText(rs.getString("time"));
		 }catch(Exception ex){
		    	
		  }
		
		
		
		
	}	
		public void btnAction()
		{
			b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					try{
						
						if (rs.next()){
							
							t.setText(rs.getString("act"));
							t1.setText(rs.getString("day"));
							t2.setText(rs.getString("time"));
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
				public void actionPerformed(ActionEvent e)
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
			
			ud.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
				    try{ 
						String parastash=t.getText();
						String mera=t1.getText();
						String wra=t2.getText();
						String sql="update shows set day='"+mera+"',time='"+wra+"' where act='"+parastash+"'";
						int rows = st.executeUpdate(sql);
					    if (rows == 1) 
					    {
					    	JOptionPane.showMessageDialog(null,"Record Updated");
					    }
					    else
					    {
					    	JOptionPane.showMessageDialog(null,"Invalid Input");
					    }
					}catch(SQLException e0)
						{	
					
					
					}
					
					
				}	
					
				
			});
			
			
			del.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					
					
					
					try{ 
						
						String parastash=t.getText();
						String sql="delete from shows where act='"+parastash+"'";
						st = con.createStatement();
						st.executeUpdate(sql);
								    
						JOptionPane.showMessageDialog(null,"Record Deleted");
						
					}catch(SQLException e3)
						{	
					
					
					}
					
					
				}	
					
				
			});
			
			nr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{   
					
					t.setText("");
					t1.setText("");
					t2.setText("");					
					
					
					
				}
			});
			
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					try{
					String parastash=t.getText();
					String mera=t1.getText();
					String wra=t2.getText();
					String sql1="insert into shows values ('" +parastash+"', '" +mera+ "','" +wra+ "')";
					st.executeUpdate(sql1);
				    JOptionPane.showMessageDialog(null,"Record Updated");
				    				
											    
					 st.close();
					 rs.close();
										
					}catch(SQLException e1)
					{
						
					}
				
					}
			});
			
			
		}
		
	
	public static void main(String[] args){
	
	
	new Gui1();
	
	}
}
