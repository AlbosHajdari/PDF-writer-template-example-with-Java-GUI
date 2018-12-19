

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import net.proteanit.sql.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;

public class frmRegjistrimiMallit extends JFrame {

	private JPanel contentPane;
	
	// Objekti per lidhje me db
	Connection conn = null;
	// Objekti per vendosjen e rezultatit
	ResultSet res = null;
	// Objekti per pyetsore
	PreparedStatement pst = null;
	private JTextField txtPerdoruesi;
	private JTextField txtFjalekalimi;
	private JTable tblPerdoruesit;
	private String barkodi;
	private JTextField txtKerko;
	private JComboBox cmbKerko;
	private JTable tblPerdoruesit1;
	private JTable tblPerdoruesit2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegjistrimiMallit frame = new frmRegjistrimiMallit();
					frame.setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setSize(screenSize.width, screenSize.height);
					    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * 
	 */
public void updateTable() 
{
		
	try
	{
		
		String sql = "select regj.barkodi as Barkodi,regj.emriProduktit as Produkti,gj.pershkrimi as Gjinia,regj.sasia as Sasia,njm.pershkrimi as Njesia_Matese,size.size as Madhesia,brendi.emriBrendit as Brendi,furnitori.emriFurnitorit as Furnizuesi,cmimet.cmimiShitjes as Çmimi\r\n" + 
				"from tblbrendet brendi,tblfurnitoret furnitori,tblregjistrimimallit regj,tblnjmatese njm,tblgjinia gj,tblsize size,tblcmimet cmimet\r\n" + 
				"where regj.brendid=brendi.id and regj.njMateseId=njm.id and regj.sizeId=size.id and regj.gjiniaId=gj.id and regj.id=cmimet.produktetId and regj.furnitoreid=furnitori.id;";
		pst = conn.prepareStatement(sql);
		res=pst.executeQuery();
		
		getTblPerdoruesit().setModel(DbUtils.resultSetToTableModel(res));
		pst.close();
		String query="select count(*) as Gjithsej from tblregjistrimimallit;";
		pst=conn.prepareStatement(query);
		res=pst.executeQuery();
		tblPerdoruesit1.setModel(DbUtils.resultSetToTableModel(res));
		pst.close();
		String query1="select sum(cmimiShitjes*regj.sasia) as Vlera_Totale\r\n" + 
				"from tblcmimet cmimet,tblregjistrimimallit regj\r\n" + 
				"where regj.id=cmimet.produktetId;";
		pst=conn.prepareStatement(query1);
		res=pst.executeQuery();
		tblPerdoruesit2.setModel(DbUtils.resultSetToTableModel(res));
		pst.close();
		
		
		
		
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
	}
	
		
}

public JTable getTblPerdoruesit() {
	return tblPerdoruesit;
}


public void setTblPerdoruesit(JTable tblPerdoruesit) {
	this.tblPerdoruesit = tblPerdoruesit;
	tblPerdoruesit.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			DefaultTableModel model=(DefaultTableModel)tblPerdoruesit.getModel();
			barkodi=(String)model.getValueAt(tblPerdoruesit.getSelectedRow(),0);
			try 
			{
				String sql="select regj.barkodi as Barkodi,regj.emriProduktit as Produkti,gj.pershkrimi as Gjinia,regj.sasia as Sasia,njm.pershkrimi as Njesia_Matese,size.size as Madhesia,brendi.emriBrendit as Brendi,furnitori.emriFurnitorit as Furnizuesi,cmimet.cmimiShitjes as Çmimi\r\n" + 
						"from tblbrendet brendi,tblfurnitoret furnitori,tblregjistrimimallit regj,tblnjmatese njm,tblgjinia gj,tblsize size,tblcmimet cmimet\r\n" + 
						"where regj.brendid=brendi.id and regj.njMateseId=njm.id and regj.sizeId=size.id and regj.gjiniaId=gj.id and regj.id=cmimet.produktetId and regj.furnitoreid=furnitori.id\r\n" + 
						" and regj.barkodi='"+barkodi+"'";
				pst=conn.prepareStatement(sql);
				res=pst.executeQuery();
				
				
				
				pst.close();
				String query="select count(*) as Gjithsej from tblregjistrimimallit;";
				pst=conn.prepareStatement(query);
				res=pst.executeQuery();
				tblPerdoruesit1.setModel(DbUtils.resultSetToTableModel(res));
				pst.close();
				String query1="select sum(cmimiShitjes*regj.sasia) as Vlera_Totale\r\n" + 
						"from tblcmimet cmimet,tblregjistrimimallit regj\r\n" + 
						"where regj.id=cmimet.produktetId;";
				pst=conn.prepareStatement(query1);
				res=pst.executeQuery();
				tblPerdoruesit2.setModel(DbUtils.resultSetToTableModel(res));
				pst.close();
				
				
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Gabim gjate mbushjes se textbox me te dhena "+e.getMessage());
			}
			
			
		}
	});
}
	
	
	
	public frmRegjistrimiMallit() {
		setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\Projekti KNK\\logo3.jpg"));
		setTitle("Kontrollimi i mallit");
		
		// Vendosja e lidhjes me DB permes klases sqlFiekConn dhe funksionit connectFiekDb
		conn = connectionClass.connectDb();

		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1237, 779);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				   {
						
						String sql = "DELETE FROM tblregjistrimimallit WHERE barkodi='"+barkodi+"'";
						pst=conn.prepareStatement(sql);
						pst.execute();
						updateTable();
					    pst.close();
						String query="select count(*) as Gjithsej from tblregjistrimimallit;";
						pst=conn.prepareStatement(query);
						res=pst.executeQuery();
						tblPerdoruesit1.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
						String query1="select sum(cmimiShitjes*regj.sasia) as Vlera_Totale\r\n" + 
								"from tblcmimet cmimet,tblregjistrimimallit regj\r\n" + 
								"where regj.id=cmimet.produktetId;";
						pst=conn.prepareStatement(query1);
						res=pst.executeQuery();
						tblPerdoruesit2.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
						
					}
					catch(Exception e)
				    {
						JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
					}
			
			
			}
		});
		mntmDelete.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Downloads\\X1.png"));
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnFile.add(mntmDelete);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setIcon(null);
		
		menuBar.add(mnEdit);
		
		JMenuItem mntmNdrysho = new JMenuItem("Ndrysho");
		mntmNdrysho.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Desktop\\edit.png"));
		mntmNdrysho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmEditimi obj = new frmEditimi();
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				obj.setVisible(true);
			}
		});
		mntmNdrysho.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnEdit.add(mntmNdrysho);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmSqarimet = new JMenuItem("Sqarimet");
		mntmSqarimet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mntmSqarimet.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Downloads\\help.png"));
		mntmSqarimet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnHelp.add(mntmSqarimet);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(12, 97, 1066, 441);
		contentPane.add(scrollPane);
		
		setTblPerdoruesit(new JTable());
		getTblPerdoruesit().setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		getTblPerdoruesit().setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(getTblPerdoruesit());
		
		JButton btnShfaq = new JButton("Shfaq\u00EB\r\n");
		btnShfaq.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Downloads\\show.png"));
		btnShfaq.setBackground(Color.LIGHT_GRAY);
		btnShfaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String sql = "select regj.barkodi as Barkodi,regj.emriProduktit as Produkti,gj.pershkrimi as Gjinia,regj.sasia as Sasia,njm.pershkrimi as Njesia_Matese,size.size as Madhesia,brendi.emriBrendit as Brendi,furnitori.emriFurnitorit as Furnizuesi,cmimet.cmimiShitjes as Çmimi\r\n" + 
							"from tblbrendet brendi,tblfurnitoret furnitori,tblregjistrimimallit regj,tblnjmatese njm,tblgjinia gj,tblsize size,tblcmimet cmimet\r\n" + 
							"where regj.brendid=brendi.id and regj.njMateseId=njm.id and regj.sizeId=size.id and regj.gjiniaId=gj.id and regj.id=cmimet.produktetId and regj.furnitoreid=furnitori.id;";
							
					pst = conn.prepareStatement(sql);
					res=pst.executeQuery();
					

					getTblPerdoruesit().setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					String query="select count(*) as Gjithsej\r\n" + 
							"from tblbrendet brendi,tblfurnitoret furnitori,tblregjistrimimallit regj,tblnjmatese njm,tblgjinia gj,tblsize size,tblcmimet cmimet\r\n" + 
							"where regj.brendid=brendi.id and regj.njMateseId=njm.id and regj.sizeId=size.id and regj.gjiniaId=gj.id and regj.id=cmimet.produktetId and regj.furnitoreid=furnitori.id;\r\n";
					pst=conn.prepareStatement(query);
					res=pst.executeQuery();
					tblPerdoruesit1.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					String query1="select sum(cmimiShitjes*regj.sasia) as Vlera_Totale\r\n" + 
							"from tblcmimet cmimet,tblregjistrimimallit regj\r\n" + 
							"where regj.id=cmimet.produktetId;";
					pst=conn.prepareStatement(query1);
					res=pst.executeQuery();
					tblPerdoruesit2.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
/*				   
					for(int i=0;i<tblPerdoruesit.getRowCount();i++) {
			           int a =(((int)tblPerdoruesit.getValueAt(i,3)*(int)tblPerdoruesit.getValueAt(i,8)))+(((int)tblPerdoruesit.getValueAt(i+1,3)*(int)tblPerdoruesit.getValueAt(i+1,8)));
					   
					}
					
					
	*/				
					
					
					
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
			}
		});
		btnShfaq.setBounds(1088, 97, 125, 180);
		contentPane.add(btnShfaq);
		
		JButton btnNewButton = new JButton("Edito");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmEditimi obj = new frmEditimi();
				obj.setLocationRelativeTo(null);
				obj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				obj.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(1088, 307, 125, 180);
		contentPane.add(btnNewButton);
		
		JScrollPane scrPane = new JScrollPane();
		scrPane.setBounds(544, 572, 533, 106);
		contentPane.add(scrPane);
		
		JLabel lblNewLabel_1 = new JLabel("*Shyp Enter pas \u00E7do kerkimi");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(22, 689, 433, 14);
		contentPane.add(lblNewLabel_1);
		
		txtKerko = new JTextField();
		txtKerko.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
    	    try 
    	       {
    			String selection=cmbKerko.getSelectedItem().toString();
					
					String sql = "select regj.barkodi as Barkodi,regj.emriProduktit as Produkti,gj.pershkrimi as Gjinia,regj.sasia as Sasia,njm.pershkrimi as Njesia_Matese,size.size as Madhesia,brendi.emriBrendit as Brendi,furnitori.emriFurnitorit as Furnizuesi,cmimet.cmimiShitjes as Çmimi\r\n" + 
							"from tblbrendet brendi,tblfurnitoret furnitori,tblregjistrimimallit regj,tblnjmatese njm,tblgjinia gj,tblsize size,tblcmimet cmimet\r\n" + 
							"where regj.brendid=brendi.id and regj.njMateseId=njm.id and regj.sizeId=size.id and regj.gjiniaId=gj.id and regj.id=cmimet.produktetId and regj.furnitoreid=furnitori.id\r\n" + 
							" and regj."+selection+"=?";

					pst = conn.prepareStatement(sql);
					pst.setString(1, txtKerko.getText().toString());
					res=pst.executeQuery();
					
					

					getTblPerdoruesit().setModel(DbUtils.resultSetToTableModel(res));
					
					
					pst.close();
				
    	    	}
    	    catch(Exception e)
    	        {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				String selection=cmbKerko.getSelectedItem().toString();
				
			    try 
	    	       {
			    	String query="select count(*) as Gjithsej\r\n" + 
			    			"from tblbrendet brendi,tblfurnitoret furnitori,tblregjistrimimallit regj,tblnjmatese njm,tblgjinia gj,tblsize size,tblcmimet cmimet\r\n" + 
			    			"where regj.brendid=brendi.id and regj.njMateseId=njm.id and regj.sizeId=size.id and regj.gjiniaId=gj.id and regj.id=cmimet.produktetId and regj.furnitoreid=furnitori.id and "+selection+"='"+txtKerko.getText().toString()+"'";
					pst=conn.prepareStatement(query);
					res=pst.executeQuery();
					tblPerdoruesit1.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					String query1="select sum(cmimiShitjes*regj.sasia) as Vlera_Totale\r\n" + 
							"from tblcmimet cmimet,tblregjistrimimallit regj\r\n" + 
							"where regj.id=cmimet.produktetId and regj."+selection+"='"+txtKerko.getText().toString()+"'";
					pst=conn.prepareStatement(query1);
					res=pst.executeQuery();
					tblPerdoruesit2.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
	    	       }
			    catch(Exception i)
			    {
			    	JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + i.getMessage());
			    }
	    	    
	    	      }
			}
		
		);
		txtKerko.setBounds(772, 63, 123, 23);
		contentPane.add(txtKerko);
		txtKerko.setColumns(10);
		
		JLabel lblKerko = new JLabel("Kerko");
		lblKerko.setBounds(770, 48, 62, 14);
		contentPane.add(lblKerko);
		
		cmbKerko = new JComboBox();
		cmbKerko.setModel(new DefaultComboBoxModel(new String[] {"barkodi", "emriProduktit", "sasia"}));
		cmbKerko.setBounds(896, 63, 125, 20);
		contentPane.add(cmbKerko);
		
		JLabel lblBoutiqueknk = new JLabel("Albi Mall");
		lblBoutiqueknk.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoutiqueknk.setFont(new Font("Castellar", Font.BOLD, 38));
		lblBoutiqueknk.setBounds(265, 17, 349, 33);
		contentPane.add(lblBoutiqueknk);
		
		JButton btnFshij = new JButton("Fshij");
		btnFshij.setSelectedIcon(new ImageIcon("C:\\Users\\etnik_000\\Desktop\\delete.png"));
		btnFshij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   try 
			   {
					
					String sql = "DELETE FROM tblregjistrimimallit WHERE barkodi='"+barkodi+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					updateTable();
				    pst.close();
					String query="select count(*) as Gjithsej from tblregjistrimimallit;";
					pst=conn.prepareStatement(query);
					res=pst.executeQuery();
					tblPerdoruesit1.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					String query1="select sum(cmimiShitjes*regj.sasia) as Vlera_Totale\r\n" + 
							"from tblcmimet cmimet,tblregjistrimimallit regj\r\n" + 
							"where regj.id=cmimet.produktetId;";
					pst=conn.prepareStatement(query1);
					res=pst.executeQuery();
					tblPerdoruesit2.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					
				}
				catch(Exception e)
			    {
					JOptionPane.showMessageDialog(null, "Gabim gjate update te tabeles. " + e.getMessage());
				}
			}
			
		});
		btnFshij.setBackground(Color.RED);
		btnFshij.setBounds(1088, 498, 125, 180);
		btnFshij.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Desktop\\delete.png"));

		contentPane.add(btnFshij);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(1029, 22, 46, 33);
		lblFoto.setIcon(new ImageIcon("C:\\Users\\etnik_000\\Desktop\\S.png"));
		

		contentPane.add(lblFoto);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 572, 522, 106);
		contentPane.add(scrollPane_1);
		
		tblPerdoruesit1 = new JTable();
		scrollPane_1.setViewportView(tblPerdoruesit1);
		
		JLabel lblMallGjithsej = new JLabel("Malli gjithsej");
		lblMallGjithsej.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMallGjithsej.setBounds(156, 538, 204, 23);
		contentPane.add(lblMallGjithsej);
		
		
		
		tblPerdoruesit2 = new JTable();
		scrPane.setViewportView(tblPerdoruesit2);
		
		JLabel lblVleraTotale = new JLabel("Vlera totale (euro)");
		lblVleraTotale.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVleraTotale.setBounds(722, 538, 204, 23);
		contentPane.add(lblVleraTotale);
		
		
		
	
		
				

		}
}