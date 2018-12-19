import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.JOptionPane;

public class frmEditimi extends JFrame {

	private JPanel contentPane;
	private JTextField txtSasia;
	private JTextField txtProdukti;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	// Objekti per lidhje me db
	Connection conn = null;
	// Objekti per vendosjen e rezultatit
	ResultSet res = null;
	// Objekti per pyetsore
	PreparedStatement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEditimi frame = new frmEditimi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	frmRegjistrimiMallit obj=new frmRegjistrimiMallit();
	public void updateTable()
	{
		try 
		{
			String sql="select * from tblregjistrimimallit";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
			res=pst.executeQuery();
			//duhet te behet import rs2xml libraria.
			obj.getTblPerdoruesit().setModel(DbUtils.resultSetToTableModel(res));
			
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	public frmEditimi() {
		
		setTitle("Boutique");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\etnik_000\\Desktop\\img_316419.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 317, 453);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		conn = connectionClass.connectDb();
		
		JComboBox cmbBarkodi = new JComboBox();
		cmbBarkodi.setModel(new DefaultComboBoxModel(new String[] {"102259", "102299", "102229", "102269"}));
		cmbBarkodi.setBounds(10, 83, 279, 22);
		contentPane.add(cmbBarkodi);
		
		JButton button = new JButton("Ndrysho");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				
				
				try 
				{
			        
			    
					if(txtProdukti.getText().matches("[a-zA-Z]{1,}")&&(txtSasia.getText().matches("[0-9]{1,}")))
							{
					String sql="update tblregjistrimimallit set emriProduktit='"+txtProdukti.getText()+"', sasia="+txtSasia.getText()+" where barkodi='"+cmbBarkodi.getSelectedItem()+"'";
					
					pst = conn.prepareStatement(sql);
				
		              pst.execute();
						JOptionPane.showMessageDialog(null,"Te dhenat u ndryshuan me sukses!!!");

					

					
					pst.close();
					txtSasia.setText("");
					txtProdukti.setText("");
				    txtProdukti.requestFocus();
					
					
					}
					else {
						JOptionPane.showMessageDialog(null,"Eshte shtypur numer tek Produkti ose tekst tek Sasia!!!");
						txtProdukti.setText("");
						txtSasia.setText("");
						txtProdukti.requestFocus();
					}
				} 
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, "Gabim gjate editimit te dhenave "+e.getMessage());
				}
				
			}
		});
		button.setBackground(UIManager.getColor("Button.background"));
		button.setBounds(10, 336, 279, 67);
		button.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Desktop\\ndrysho.png"));

		contentPane.add(button);
		
		txtSasia = new JTextField();
		txtSasia.setColumns(10);
		txtSasia.setBounds(11, 216, 278, 20);
		contentPane.add(txtSasia);
		
		txtProdukti = new JTextField();
		txtProdukti.setColumns(10);
		txtProdukti.setBounds(10, 139, 279, 20);
		contentPane.add(txtProdukti);
		
		JLabel label_3 = new JLabel("Produkti");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(10, 116, 125, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Sasia");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(11, 193, 86, 14);
		contentPane.add(label_4);
		
		
	
		
		JLabel label_9 = new JLabel("Ndryshimi i mallit");
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_9.setBounds(78, 21, 191, 23);
		contentPane.add(label_9);
		
	
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 59, 46, 14);
		contentPane.add(lblNewLabel);
		
	
	}
}
