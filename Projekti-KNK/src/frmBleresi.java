import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
public class frmBleresi extends JFrame {
	private JLabel lblEmri;
	private JLabel lblMbiemri;
	private JLabel lblVendbanimi;
	private JLabel lblDataELindjes;
	private JLabel lblTel;
	private JLabel lblEmail;
	private JLabel lblGjinia;
	private JLabel lblemail;
	private JLabel lblvendi;
	private JLabel lbldata;
	private JLabel lblmbiemri;
	private JLabel lblemri;
	private JLabel lbltel;
	private JPanel contentPane;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private JTextField txtEmail;
	private JTextField txtTel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbMashkull;
	private JRadioButton rdbFemer; 
	PreparedStatement pst = null;
	ResultSet res= null;
	Connection conn = null;
	private JRadioButton rdbtnA;
	private JComboBox cmbShteti;
	private JDateChooser txtData;
	private JComboBox cmbQyteti;
	private JLabel lblQytetiVendbanimit;
	private JLabel lblqyteti;
	private JLabel lblNrPersonal;
	private JTextField txtNrPersonal;
	private JLabel lblnrPersonal;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBleresi frame = new frmBleresi();
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
	public frmBleresi() {
conn = connectionClass.connectDb();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\Projekti KNK\\logo3.jpg"));
		setResizable(false);
		setTitle("Albi Mall - Sign Up");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1498, 545);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpContent = new JMenuItem("Help contents");
		mntmHelpContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mntmHelpContent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_MASK));
		mnHelp.add(mntmHelpContent);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(204, 255, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(204, 0, 0), 1, true));
		panel.setBounds(85, 73, 1328, 349);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		lblEmri = new JLabel("Emri:");
		lblEmri.setBounds(6, 18, 61, 28);
		panel.add(lblEmri);
		lblEmri.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblMbiemri = new JLabel("Mbiemri:");
		lblMbiemri.setBounds(407, 18, 69, 28);
		panel.add(lblMbiemri);
		lblMbiemri.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblGjinia = new JLabel("Gjinia:");
		lblGjinia.setBounds(6, 107, 117, 28);
		panel.add(lblGjinia);
		lblGjinia.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblDataELindjes = new JLabel("Data e lindjes:");
		lblDataELindjes.setBounds(835, 18, 149, 28);
		panel.add(lblDataELindjes);
		lblDataELindjes.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataELindjes.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(407, 107, 71, 28);
		panel.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTel = new JLabel("Tel:");
		lblTel.setBounds(835, 107, 149, 28);
		panel.add(lblTel);
		lblTel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtEmri = new JTextField();
		txtEmri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try
				{
					if(!txtEmri.getText().equals(""))
					{
						StringBuilder str = new StringBuilder(txtEmri.getText());
						str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
						txtEmri.setText(str.toString());
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		txtEmri.setBounds(118, 24, 207, 22);
		panel.add(txtEmri);
		txtEmri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmri.setColumns(10);
		
		txtMbiemri = new JTextField();
		txtMbiemri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try
				{
					if(!txtMbiemri.getText().equals(""))
					{
						StringBuilder str = new StringBuilder(txtMbiemri.getText());
						str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
						txtMbiemri.setText(str.toString());
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		txtMbiemri.setBounds(573, 24, 207, 22);
		panel.add(txtMbiemri);
		txtMbiemri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMbiemri.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(573, 113, 207, 22);
		panel.add(txtEmail);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(1025, 113, 207, 22);
		panel.add(txtTel);
		txtTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTel.setColumns(10);
		
		rdbMashkull = new JRadioButton("M");
		rdbMashkull.setBounds(106, 110, 61, 25);
		panel.add(rdbMashkull);
		rdbMashkull.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbMashkull.setSelected(true);
		buttonGroup.add(rdbMashkull);
		
		rdbFemer = new JRadioButton("F");
		rdbFemer.setBounds(178, 110, 61, 25);
		panel.add(rdbFemer);
		rdbFemer.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonGroup.add(rdbFemer);
		
		JButton btnRegjistrohu = new JButton("Regjistrohu");
		btnRegjistrohu.setBorder(new LineBorder(new Color(0, 102, 0), 1, true));
		btnRegjistrohu.setBounds(573, 280, 207, 40);
		panel.add(btnRegjistrohu);
		btnRegjistrohu.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblVendbanimi_1 = new JLabel("Shteti vendbanimit: ");
		lblVendbanimi_1.setBounds(407, 189, 142, 28);
		panel.add(lblVendbanimi_1);
		lblVendbanimi_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		rdbtnA = new JRadioButton("A");
		rdbtnA.setBounds(243, 110, 61, 25);
		panel.add(rdbtnA);
		buttonGroup.add(rdbtnA);
		rdbtnA.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblemri = new JLabel("");
		lblemri.setBounds(118, 42, 298, 16);
		panel.add(lblemri);
		lblemri.setToolTipText("emer");
		lblemri.setForeground(Color.RED);
		lblemri.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lblmbiemri = new JLabel("");
		lblmbiemri.setBounds(570, 51, 277, 16);
		panel.add(lblmbiemri);
		lblmbiemri.setToolTipText("mbiemer");
		lblmbiemri.setForeground(Color.RED);
		lblmbiemri.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lblvendi = new JLabel("");
		lblvendi.setBounds(573, 221, 318, 16);
		panel.add(lblvendi);
		lblvendi.setToolTipText("vendbanim");
		lblvendi.setForeground(Color.RED);
		lblvendi.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lbldata = new JLabel("");
		lbldata.setBounds(1025, 42, 318, 16);
		panel.add(lbldata);
		lbldata.setForeground(Color.RED);
		lbldata.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lblemail = new JLabel("");
		lblemail.setBounds(573, 137, 318, 16);
		panel.add(lblemail);
		lblemail.setForeground(Color.RED);
		lblemail.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lbltel = new JLabel("");
		lbltel.setBounds(1025, 137, 318, 16);
		panel.add(lbltel);
		lbltel.setForeground(Color.RED);
		lbltel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		cmbShteti = new JComboBox();
		cmbShteti.setBorder(new LineBorder(new Color(204, 255, 255)));
		cmbShteti.setModel(new DefaultComboBoxModel(new String[] {""}));
		cmbShteti.setBounds(573, 193, 207, 24);
		panel.add(cmbShteti);
		
		
		
		cmbQyteti = new JComboBox();
		cmbQyteti.setBorder(new LineBorder(new Color(204, 255, 255)));
		cmbQyteti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mbushQytetet();	
			}
		});
		
		cmbQyteti.setModel(new DefaultComboBoxModel(new String[] {""}));
		cmbQyteti.setBounds(1025, 193, 207, 24);
		panel.add(cmbQyteti);
		
		lblQytetiVendbanimit = new JLabel("Qyteti vendbanimit:");
		lblQytetiVendbanimit.setBounds(835, 189, 149, 28);
		panel.add(lblQytetiVendbanimit);
		lblQytetiVendbanimit.setHorizontalAlignment(SwingConstants.LEFT);
		lblQytetiVendbanimit.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblqyteti = new JLabel("");
		lblqyteti.setBounds(1025, 221, 318, 16);
		panel.add(lblqyteti);
		lblqyteti.setToolTipText("vendbanim");
		lblqyteti.setForeground(Color.RED);
		lblqyteti.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		lblNrPersonal = new JLabel("Nr. Personal:");
		lblNrPersonal.setBounds(6, 189, 93, 28);
		panel.add(lblNrPersonal);
		lblNrPersonal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtNrPersonal = new JTextField();
		txtNrPersonal.setBounds(118, 195, 207, 22);
		panel.add(txtNrPersonal);
		txtNrPersonal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNrPersonal.setColumns(10);
		
		lblnrPersonal = new JLabel("");
		lblnrPersonal.setBounds(118, 213, 298, 16);
		panel.add(lblnrPersonal);
		lblnrPersonal.setToolTipText("emer");
		lblnrPersonal.setForeground(Color.RED);
		lblnrPersonal.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		txtData = new JDateChooser();
		txtData.setBounds(1025, 24, 207, 22);
		panel.add(txtData);
		btnRegjistrohu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
					signUp();
			}
		});
		btnRegjistrohu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					signUp();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
					
				}
			}
		});
		
		JLabel lblAlbiMall = new JLabel("Albi Mall");
		lblAlbiMall.setForeground(new Color(0, 0, 0));
		lblAlbiMall.setBounds(572, 13, 347, 47);
		lblAlbiMall.setFont(new Font("Castellar", Font.BOLD, 35));
		lblAlbiMall.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAlbiMall);
		fillCombo();
		
	}
	private void fillCombo()
	{
		try
		{
			ArrayList<String> shtetet = new ArrayList<String>();
			
			
			
			String query = "select shteti from tblshtetet ";
			shtetet.add("");
			pst = conn.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next())
			{
				String shtet = res.getString("shteti");
				shtetet.add(shtet);
			}
			pst.close();
			cmbShteti.removeAllItems();
			cmbShteti.setModel(new DefaultComboBoxModel(shtetet.toArray()));
			
			
			ArrayList<String> pozita = new ArrayList<String>();
			pozita.add("");
			String sql = "select pershkrimi from tblpozita";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				String pozite = res.getString("pershkrimi");
				pozita.add(pozite);
			}
			pst.close();
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private void mbushQytetet()
	{
		try
		{
			cmbQyteti.removeAllItems();
			ArrayList<String> qytetet = new ArrayList<String>();
			
			
			
			String query = "select q.qyteti from tblqytetet q, tblshtetet s where q.sid =s.id and s.shteti ='"+cmbShteti.getSelectedItem()+"'";
			qytetet.add("");
			pst = conn.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next())
			{
				String qytet = res.getString("qyteti");
				qytetet.add(qytet);
			}
			pst.close();
			
			cmbQyteti.setModel(new DefaultComboBoxModel(qytetet.toArray()));
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public void signUp()
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = txtData.getDate();
			String datE = dateFormat.format(date);
			
			String gjinia = "";
			String emrat = "^[a-zA-Z]+", email = "^[a-zA-Z0-9]+[\\.]?[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]{2,4}$",tel="^[0-9]+$",user="^[a-zA-Z0-9._]+$";
			int counter = 0;
			int dataPersonit = date.getYear();
			int dataAktuale = new Date().getYear();
			if(txtTel.getText().length()<9)
			{
				counter++;
				lbltel.setText("Shenoni nje numer valid!");
			}
			if((dataAktuale - dataPersonit)<16)
			{
						
				counter++;
				lbldata.setText("Mosha eshte me e vogel se 16!'");
						
			}
			if(cmbShteti.getSelectedIndex() == 0)
			{
				counter++;
				lblvendi.setText("Ju lutem zgjedheni shtetin e vendbanimit");
			}
			if(cmbQyteti.getSelectedIndex() == 0)
			{
				counter++;
				lblqyteti.setText("Ju lutem zgjedheni qytetin e vendbanimit");
			}
			
			if(!Pattern.matches(email, txtEmail.getText()))
			{
				counter++;
				lblemail.setText("Ju lutem shenoni nje email ne formatin e sakte: user@example.com"); 
			}
			if(!Pattern.matches(tel, txtTel.getText()))
			{
				counter++;
					
				lbltel.setText("Ju lutem shenoni numrin e telit ne formatin e sakte: +383000111");
			}
			
				
			
			if(!Pattern.matches(emrat, txtEmri.getText()))
			{
				lblemri.setText("Ju lutem shenoni nje emer valid!"); 
				counter++;
							
			}
			
			if(!Pattern.matches(emrat, txtMbiemri.getText()))
			{
				lblmbiemri.setText("Ju lutem shenoni nje mbiemer valid!"); 
				counter++;
							
			}
			
			if(txtEmri.getText().equals(""))
			{
				lblemri.setText("Emri nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			if(txtMbiemri.getText().equals(""))
			{
				lblmbiemri.setText("Mbiemri nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtEmail.getText().equals(""))
			{
				lblemail.setText("Email nuk duhet te jete i zbrazet!");
				counter++;
			}
			
			if(txtTel.getText().equals(""))
			{
				lbltel.setText("Telefoni nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(txtNrPersonal.getText().equals(""))
			{
				lblnrPersonal.setText("Nr. Personal nuk duhet te jete i zbrazet!");
				counter++;
			}
			if(counter != 0)
			{
				JOptionPane.showMessageDialog(null, "Keni gabime ne formen tuaj");
			}
			
			else
			{
				if(rdbMashkull.isSelected())
					gjinia = "Mashkull";
				else if(rdbFemer.isSelected())
					gjinia = "Femer";
				else
					gjinia = "Papercaktuar";

				String sql = "insert into tblBleresit(nrPersonal,emri,mbiemri,gjiniaId,dtLindjes,tel,email,adreseId)"
							+ "values('"+txtNrPersonal.getText()+"','"+txtEmri.getText()+"','"+txtMbiemri.getText()+"',(select id from tblgjinia where pershkrimi = '"+gjinia+"'),'"+datE+"','"
							+ txtTel.getText()+"','"+txtEmail.getText()+"',(select id from tblqytetet where qyteti = '"+cmbQyteti.getSelectedItem()+"'))";
				pst = conn.prepareStatement(sql);
				pst.execute();
				pst.close();
				
				JOptionPane.showMessageDialog(null, "Regjistrimi u krye me sukses");
				txtEmri.setText("");
				txtMbiemri.setText("");
				cmbQyteti.setSelectedIndex(0);
				cmbShteti.setSelectedIndex(0);
				txtData.setDate(null);
				txtNrPersonal.setText("");
				lblnrPersonal.setText("");
				lblqyteti.setText("");
				lblvendi.setText("");
				txtTel.setText("");
				txtEmail.setText("");
				lbldata.setText("");
				lblemri.setText("");
				lblmbiemri.setText("");
				lblvendi.setText("");
				lbltel.setText("");
				lblemail.setText("");
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Something Bad happened"+ex.getMessage());
			
		}
	}
}
