package org.teipir.softeng.jdbc;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.swing.*;

import org.teipir.softeng.jdbc.Admin;

import java.awt.event.*;
import java.sql.*;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
public class Admin {

	Connection con;
	Statement st;
	ResultSet rs;
	
	JFrame f=new JFrame("Admin login");
	JLabel l=new JLabel("username:");
	JLabel l1=new JLabel("password:");
	JTextField t=new JTextField(10);
	JPasswordField t2 = new JPasswordField(10);
	JButton b=new JButton("Login");
	
		

	public void connect()
	{
	
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			String connectionUrl="jdbc:mysql://localhost:3306/db1";
			String connectionUser = "root";
			String connectionPassword = "123123";
		    con = DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
		    
		}
		
		catch(ClassNotFoundException ex)
		{
			System.out.println("Error: unable to load driver class!");
			 System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	private void assertThat(Exception e) {
		// TODO Auto-generated method stub
		
	}
	public Admin(){
		connect();
		frame();
		
	}
	
	public void frame()
	{
		f.setSize(240,250);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		
		JPanel p=new JPanel();
		p.add(l);
		p.add(t);
		p.add(l1);
		p.add(t2);
		p.add(b);
		f.add(p);
		
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				String user = t.getText().trim();
				String password = t2.getText().trim();
				
				st=con.createStatement();
				String sql="select name,password from admins where name= '"+user+"'and password= '"+password+"'";
				rs=st.executeQuery(sql);
				
				int count=0;
				while(rs.next())
				{
				count=count + 1;
				}
					
				if(count==1){
					
					JOptionPane.showMessageDialog(null,"User Found, Access Granted!");
					
					e.getSource();
					f.setVisible(false); 
					f.dispose(); 
					Gui1 gui = new Gui1();
									
				}
			
				
				else 
				{
					JOptionPane.showMessageDialog(null,"User not Found");
				}
				
				}
				catch (Exception ex)
				{
			}
			}
		});
		
		
	}

	
	public static void main(String[] args){
	
	
	new Admin();
		

	}

}
