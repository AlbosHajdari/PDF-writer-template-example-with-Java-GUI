import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.awt.image.*;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class frmLogin extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
	Connection conn=null;
		//objekti per vendosje te rezultatit
	ResultSet res=null;
		//objekti per query
	PreparedStatement pst=null;
	private JTextField txtPerdoruesi;
	private JPasswordField txtFjalekalimi;
	private JLabel lblBackground;
	private JButton btnKycu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmLogin() {
		setBackground(new Color(0, 0, 102));
		setResizable(false);
		conn = connectionClass.connectDb();
		setTitle("Albi Mall - Kycu");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/imgs/logo3.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 383, 621);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlbiMall = new JLabel("Albi Mall");
		lblAlbiMall.setForeground(Color.WHITE);
		lblAlbiMall.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbiMall.setFont(new Font("Castellar", Font.BOLD, 38));
		lblAlbiMall.setBounds(0, 13, 381, 53);
		contentPane.add(lblAlbiMall);
		
		JLabel label = new JLabel("Perdoruesi:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(12, 124, 122, 33);
		contentPane.add(label);
		
		txtPerdoruesi = new JTextField();
		txtPerdoruesi.setToolTipText("");
		txtPerdoruesi.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPerdoruesi.setColumns(10);
		txtPerdoruesi.setBackground(new Color(240, 255, 255));
		txtPerdoruesi.setBounds(12, 181, 347, 28);
		contentPane.add(txtPerdoruesi);
		
		JLabel label_1 = new JLabel("Fjal\u00EBkalimi:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_1.setBounds(12, 243, 122, 33);
		contentPane.add(label_1);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}
		});
		txtFjalekalimi.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFjalekalimi.setColumns(10);
		txtFjalekalimi.setBackground(new Color(240, 255, 255));
		txtFjalekalimi.setBounds(12, 304, 347, 28);
		contentPane.add(txtFjalekalimi);
		
		btnKycu = new JButton("Ky\u00E7u");
		btnKycu.setIcon(null);
		btnKycu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				login();
			}
		});
		btnKycu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnKycu.setForeground(Color.WHITE);
		btnKycu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnKycu.setBackground(new Color(0, 0, 102));
		btnKycu.setBounds(53, 400, 271, 53);
		contentPane.add(btnKycu);
		
		JLabel label_2 = new JLabel("Keni harruar fjalekalimin?");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmForgotPass obj = new frmForgotPass();
				obj.setVisible(true);
			}
		});
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(49, 458, 271, 21);
		contentPane.add(label_2);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/lock4.jpg")));
		lblBackground.setBounds(0, 0, 377, 588);
		contentPane.add(lblBackground);
	}
	
	public void login()
	{
		try
		{
			String sql = "select s.nrPersonal,s.emri,s.mbiemri,s.passwordi,s.salt,p.pershkrimi from tblStafi s,tblpozita p where s.poziteid = p.id and s.username = '"+txtPerdoruesi.getText()+"'";
			String passDb = "",salt ="",pozita = "",nrPersonal ="",emri="",mbiemri="";
			String welcome = "Miresevini!";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				nrPersonal = res.getString("s.nrPersonal");
				emri = res.getString("s.emri");
				mbiemri = res.getString("s.mbiemri");
				passDb = res.getString("s.passwordi");
				salt = res.getString("s.salt");
				pozita = res.getString("p.pershkrimi");
			}
			pst.close();
			String pass = txtFjalekalimi.getText() + salt;
			pass = Encryption.SHA1(pass);
			if(pass.equalsIgnoreCase(passDb))
			{
				Useri.setNrPersonal(nrPersonal);
				Useri.setEmri(emri);
				Useri.setMbiemri(mbiemri);
				lblBackground.setIcon(new ImageIcon(frmLogin.class.getResource("/imgs/unlock.jpg")));
				JOptionPane.showMessageDialog(null, welcome);
				if(pozita.equalsIgnoreCase("punetor"))
				{
					frmShitja obj = new frmShitja();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				else
				{
					frmMenu obj = new frmMenu();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				
				
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Perdoruesi ose fjalekalimi eshte gabim!");
				
				txtPerdoruesi.setText("");
				txtFjalekalimi.setText("");
				txtPerdoruesi.requestFocus();
			}
		}
		catch(Exception ex)
		{
			
		}
	}
}
