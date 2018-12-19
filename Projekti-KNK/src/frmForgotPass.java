import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmForgotPass extends JFrame {

	private JPanel contentPane;
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTextField txtNrPersonal;
	private JTextField txtEmail;
	private JDateChooser dcDataLindjes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmForgotPass frame = new frmForgotPass();
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
	public frmForgotPass() {
		conn = connectionClass.connectDb();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumriPersonal = new JLabel("Numri Personal:");
		lblNumriPersonal.setBounds(60, 110, 130, 29);
		contentPane.add(lblNumriPersonal);
		
		txtNrPersonal = new JTextField();
		txtNrPersonal.setBounds(219, 113, 254, 26);
		contentPane.add(txtNrPersonal);
		txtNrPersonal.setColumns(10);
		
		JLabel txtDataLindjes = new JLabel("Data e lindjes:");
		txtDataLindjes.setBounds(60, 173, 130, 29);
		contentPane.add(txtDataLindjes);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(219, 258, 254, 26);
		contentPane.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(60, 255, 130, 29);
		contentPane.add(lblEmail);
		
		dcDataLindjes = new JDateChooser();
		dcDataLindjes.setBounds(219, 176, 254, 26);
		contentPane.add(dcDataLindjes);
		
		JButton btnKrijoFjalekaliminE = new JButton("Krijo fjalekalimin e ri");
		btnKrijoFjalekaliminE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fjalekalimi();
			}
		});
		btnKrijoFjalekaliminE.setBounds(219, 334, 225, 29);
		contentPane.add(btnKrijoFjalekaliminE);
	}
	public void fjalekalimi()
	{
		try
		{
			DateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date data = dcDataLindjes.getDate();
			String dtLindjes = dtFormat.format(data);
			String saltEm ="",dtDbLindjes = "",emailDb="",salti ="";
			String sql = "select email,dtLindjes,nrSerik,salt from tblStafi where nrPersonal = '"+txtNrPersonal.getText()+"'";
			pst = conn.prepareStatement(sql);
			res= pst.executeQuery();
			while(res.next())
			{
				salti = res.getString("salt");
				emailDb = res.getString("email");
				saltEm = res.getString("nrSerik");
				dtDbLindjes = res.getString("dtLindjes");
			}
			pst.close();
			String email = salti +txtEmail.getText()+saltEm;
			email = Encryption.SHA1(email);
			if(email.equalsIgnoreCase(emailDb) && dtDbLindjes.equals(dtLindjes))
			{
				frmRecoverPass obj = new frmRecoverPass();
				obj.setNrPersonal(txtNrPersonal.getText());
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Te dhenat nuk perputhen!");
				txtNrPersonal.setText("");
				dcDataLindjes.setDateFormatString("");
				txtEmail.setText("");
				txtNrPersonal.requestFocus();
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Something bad happened frmForgotPass! "+ex.getMessage());
		}
	}
}
