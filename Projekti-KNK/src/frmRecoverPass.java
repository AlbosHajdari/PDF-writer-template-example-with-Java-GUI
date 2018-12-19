import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;
public class frmRecoverPass extends JFrame {

	private JPanel contentPane;
	private JTextField txtFjalekalimi;
	private JTextField txtKonfirmo;
	private String nrPersonal = "";
	private JLabel lblfjalekalimi;
	private JLabel lblkonfirmo;
	PreparedStatement pst = null;
	Connection conn = null;
	ResultSet res = null;

	/**
	 * Launch the application.
	 */
	public void setNrPersonal(String nrPersonal)
	{
		this.nrPersonal = nrPersonal;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRecoverPass frame = new frmRecoverPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public frmRecoverPass() {
		conn = connectionClass.connectDb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFjalekalimiIRi = new JLabel("Fjalekalimi i ri:");
		lblFjalekalimiIRi.setBounds(132, 94, 154, 35);
		contentPane.add(lblFjalekalimiIRi);
		
		txtFjalekalimi = new JTextField();
		txtFjalekalimi.setBounds(305, 94, 316, 35);
		contentPane.add(txtFjalekalimi);
		txtFjalekalimi.setColumns(10);
		
		txtKonfirmo = new JTextField();
		txtKonfirmo.setColumns(10);
		txtKonfirmo.setBounds(305, 176, 316, 35);
		contentPane.add(txtKonfirmo);
		
		JLabel lblKonfirmo = new JLabel("Konfirmo fjalekalimin:");
		lblKonfirmo.setBounds(132, 176, 154, 35);
		contentPane.add(lblKonfirmo);
		
		JButton btnRuajFjalekalimin = new JButton("Ruaj fjalekalimin ");
		btnRuajFjalekalimin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recover();
			}
		});
		btnRuajFjalekalimin.setBounds(272, 271, 212, 25);
		contentPane.add(btnRuajFjalekalimin);
		
		lblfjalekalimi = new JLabel("");
		lblfjalekalimi.setBounds(305, 129, 415, 16);
		contentPane.add(lblfjalekalimi);
		
		lblkonfirmo = new JLabel("");
		lblkonfirmo.setBounds(305, 213, 415, 16);
		contentPane.add(lblkonfirmo);
	}
	
	public void recover()
	{
		try
		{
			if(txtFjalekalimi.getText().length() <8)
			{
				lblfjalekalimi.setText("Fjalekalimi duhet te jete se paku 8 karaktere!");
				txtFjalekalimi.requestFocus();
			}
			else if(!txtFjalekalimi.getText().equals(txtKonfirmo.getText()))
			{
				lblkonfirmo.setText("Fjalekalimi nuk perputhet!");
				txtFjalekalimi.setText("");
				txtKonfirmo.setText("");
				txtFjalekalimi.requestFocus();
			}
			else
			{
				
				String salt = Encryption.generateSalt();
				String pass = txtFjalekalimi.getText() + salt;
				String passHash = Encryption.SHA1(pass);
				JOptionPane.showMessageDialog(null, nrPersonal + " "+passHash+"  "+salt);
				String query1 = "UPDATE tblStafi SET passwordi = '"+passHash+"', salt = '"+salt+"' WHERE nrPersonal = '"+nrPersonal+"'";
				Statement stmt1 = conn.createStatement();
				stmt1.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "Fjalekalimi u rivendos me sukses!");
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Something bad  frmRecoverPass! "+ex.getMessage());
		}
	}
}
