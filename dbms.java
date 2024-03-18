import java.sql.*;

import java.util.Iterator;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EtchedBorder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtcomplaintid;
	private JTextField txtusn;
	private JTextField txtname;
	private JTextField txtbranch;
	private JTextField txtsubjectid;
	private JTextField txtcomplaint;
	private JTable table;
	private JTextField txtsearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	
	public void Connect()
	{
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacurd","root","W7301@jqir#");
//			3 parameters
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void table_load() {
		try {
//			pst = con.prepareStatement("select * from complaints");
//			rs = pst.executeQuery();
//			//////////////////////////////////////////////////
			Statement st = con.createStatement();
			String query = "select * from complaints";
			rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			int cols = rsmd.getColumnCount();
			String [] colName = new String [cols];
			for(int i=0;i<cols;i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			String usn,name,branch,subjectid,complaintid,complaint;
			while(rs.next()) {
				usn = rs.getString(1);
				name = rs.getString(2);
				branch = rs.getString(3);
				subjectid = rs.getString(4);
				complaintid = rs.getString(5);
				complaint = rs.getString(6);
				String [] row = {usn,name,branch,subjectid,complaintid,complaint};
				model.addRow(row);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//		image changes
		frame = new JFrame(); // Initialize frame here
	    frame.setBounds(100, 100, 1360, 422);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(new BorderLayout());
	    
	    // Set content pane after initializing frame
	    Image backgroundImage = new ImageIcon("C:/Users/Windows/OneDrive/Desktop/sjcit_logo3.jpg").getImage();
//	    Image backgroundImage = new ImageIcon("C:/Users/Windows/Downloads/SJCIT-logoooo.jpg").getImage();
	    JPanel backgroundPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	        }
	    };
	    backgroundPanel.setLayout(null);
	    frame.setContentPane(backgroundPanel);
		
		JLabel lblNewLabel = new JLabel("SJCIT DHI COMPLAINT MANAGEMENT SYSTEM");
		lblNewLabel.setForeground(new Color(0, 64, 64));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(329, 11, 773, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Complaint Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 70, 309, 177);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("USN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(24, 26, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NAME");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(24, 51, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("BRANCH");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(24, 76, 71, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("SUBJECT ID");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(24, 99, 89, 14);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("COMPLAINT ID");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(24, 123, 114, 14);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("COMPLAINT");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_5.setBounds(24, 148, 114, 14);
		panel.add(lblNewLabel_1_5);
		
		txtcomplaintid = new JTextField();
		txtcomplaintid.setBounds(142, 117, 138, 20);
		panel.add(txtcomplaintid);
		txtcomplaintid.setColumns(10);
		
		txtusn = new JTextField();
		txtusn.setColumns(10);
		txtusn.setBounds(142, 20, 138, 20);
		panel.add(txtusn);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(142, 45, 138, 20);
		panel.add(txtname);
		
		txtbranch = new JTextField();
		txtbranch.setColumns(10);
		txtbranch.setBounds(142, 70, 138, 20);
		panel.add(txtbranch);
		
		txtsubjectid = new JTextField();
		txtsubjectid.setColumns(10);
		txtsubjectid.setBounds(142, 93, 138, 20);
		panel.add(txtsubjectid);
		
		txtcomplaint = new JTextField();
		txtcomplaint.setColumns(10);
		txtcomplaint.setBounds(142, 142, 138, 20);
		panel.add(txtcomplaint);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				storing the values
				String usn,name,branch,subjectid,complaintid,complaint;
				usn = txtusn.getText();
				name = txtname.getText();
				branch = txtbranch.getText();
				subjectid = txtsubjectid.getText();
				complaintid = txtcomplaintid.getText();
				complaint = txtcomplaint.getText();
				
				try {
					pst = con.prepareStatement("insert into complaints(USN,NAME,BRANCH,SUBJECT_ID,COMPLAINT_ID,COMPLAINT)values(?,?,?,?,?,?)");
					pst.setString(1, usn);
					pst.setString(2, name);
					pst.setString(3, branch);
					pst.setString(4, subjectid);
					pst.setString(5, complaintid);
					pst.setString(6, complaint);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addeddd......");
//					table_load();
					txtusn.setText("");
					txtname.setText("");
					txtbranch.setText("");
					txtsubjectid.setText("");
					txtcomplaintid.setText("");
					txtcomplaint.setText("");
					txtusn.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(10, 258, 89, 46);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(new Color(64, 0, 128));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(109, 258, 89, 46);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(new Color(0, 0, 128));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtusn.setText("");
				txtname.setText("");
				txtbranch.setText("");
				txtsubjectid.setText("");
				txtcomplaintid.setText("");
				txtcomplaint.setText("");
				txtusn.requestFocus();
			}
		});
		btnClear.setBounds(208, 258, 89, 46);
		frame.getContentPane().add(btnClear);
		
		table = new JTable();
		table.setBounds(389, 245, -43, -133);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 310, 309, 46);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("USN : ");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(10, 16, 61, 14);
		panel_1.add(lblNewLabel_1_2_1);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
				public void keyReleased(KeyEvent e) {
			        try {
			            String usnToSearch = txtsearch.getText();
			            String query = "SELECT * FROM complaints WHERE usn=?";//
			            
			            pst = con.prepareStatement(query);
			            pst.setString(1, usnToSearch); //

			            rs = pst.executeQuery();
			            
			            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			            model.setRowCount(0); // Clear the existing rows
			            
			            while (rs.next()) {
			                String usn = rs.getString(1);
			                String name = rs.getString(2);
			                String branch = rs.getString(3);
			                String subjectid = rs.getString(4);
			                String complaintid = rs.getString(5);
			                String complaint = rs.getString(6);

			                String[] row = { usn, name, branch, subjectid, complaintid, complaint };
			                model.addRow(row);
			            }
			        } catch (SQLException el) {
			            System.out.println(el);
			        }
			    }
			});
		txtsearch.setColumns(10);
		txtsearch.setBounds(62, 11, 221, 20);
		panel_1.add(txtsearch);
		
		JButton btnUpdate = new JButton("Display table");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		btnUpdate.setBounds(415, 309, 117, 46);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_2 = new JButton("CLEAR");
		btnClear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(new DefaultTableModel());
			}
		});
		btnClear_2.setBounds(569, 310, 89, 46);
		frame.getContentPane().add(btnClear_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 70, 1000, 219);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		

	}
}
