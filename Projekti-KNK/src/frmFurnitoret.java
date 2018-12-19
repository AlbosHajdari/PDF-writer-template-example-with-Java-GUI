import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class frmFurnitoret extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTable tblBleresit;
	private JScrollPane scrollPane;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmFurnitoret frame = new frmFurnitoret();
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
	public frmFurnitoret() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmFurnitoret.class.getResource("/imgs/logo3.jpg")));
		setTitle("Albi Mall - Stafi");
		conn = connectionClass.connectDb();
		//updateTable();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1717, 953);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmShtoKlientTe = new JMenuItem("Shto anetare te ri");
		mntmShtoKlientTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				frmSignUp obj = new frmSignUp();
//				obj.setVisible(true);
			
			}
		});
		mntmShtoKlientTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnFile.add(mntmShtoKlientTe);
		
		JMenu mnHelp = new JMenu("Ndihma");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Ndihma");
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlbiMall = new JLabel("Albi Mall");
		lblAlbiMall.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbiMall.setFont(new Font("Castellar", Font.BOLD, 36));
		lblAlbiMall.setBounds(448, 13, 445, 43);
		contentPane.add(lblAlbiMall);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(135, 206, 250)));
		scrollPane.setBounds(67, 70, 1568, 742);
		contentPane.add(scrollPane);
		
		tblBleresit = new JTable();
		scrollPane.setViewportView(tblBleresit);
		insertTable();
		
		JLabel lblKrko = new JLabel("K\u00EBrko:");
		lblKrko.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblKrko.setBounds(1306, 825, 55, 21);
		contentPane.add(lblKrko);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				updateTable();
			}
		});
		
		txtSearch.setBounds(1376, 825, 259, 22);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
	}
	
	public void insertTable()
	{
		try 
		{
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Furnitori", "Produkti","Brendi","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select  f.emriFurnitorit, m.emriProduktit,b.emriBrendit,q.qyteti,s.shteti from tblfurnitoret f,tblBrendet b,tblfurnizonbrendin fb,tblregjistrimimallit m,tblqytetet q, tblshtetet s where f.id=fb.fid and b.id= fb.bid and m.furnitoreid=f.id and m.brendid=b.id and f.adreseid = q.id and q.sid = s.id";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String np = res.getString("f.emriFurnitorit");
				String em = res.getString("m.emriProduktit");
				String mb = res.getString("b.emriBrendit");
				String dt = res.getString("q.qyteti");
				String gj = res.getString("s.shteti");
				
				model.addRow(new Object[] {c,np,em,mb,dt,gj});
				counter++;
				
			}
			pst.close();
			tblBleresit.setModel(model);
			
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	public void updateTable()
	{
		try 
		{
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Furnitori", "Produkti","Brendi","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select  f.emriFurnitorit, m.emriProduktit,b.emriBrendit,q.qyteti,s.shteti from tblfurnitoret f,tblBrendet b,tblfurnizonbrendin fb,tblregjistrimimallit m,tblqytetet q, tblshtetet s where f.id=fb.fid and b.id= fb.bid and m.furnitoreid=f.id and m.brendid=b.id and f.adreseid = q.id and q.sid = s.id and f.emriFurnitorit like '%"+txtSearch.getText()+"%'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String np = res.getString("f.emriFurnitorit");
				String em = res.getString("m.emriProduktit");
				String mb = res.getString("b.emriBrendit");
				String dt = res.getString("q.qyteti");
				String gj = res.getString("s.shteti");
				
				model.addRow(new Object[] {c,np,em,mb,dt,gj});
				counter++;
				
			}
			pst.close();
			tblBleresit.setModel(model);
			
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
}

