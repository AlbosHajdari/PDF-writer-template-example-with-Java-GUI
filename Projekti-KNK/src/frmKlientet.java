import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class frmKlientet extends JFrame {

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
					frmKlientet frame = new frmKlientet();
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
	public frmKlientet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmKlientet.class.getResource("/imgs/logo3.jpg")));
		setTitle("Albi Mall - Klientet");
		conn = connectionClass.connectDb();
		//updateTable();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1253, 959);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmShtoKlientTe = new JMenuItem("Shto klient te ri");
		mntmShtoKlientTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBleresi obj = new frmBleresi();
				obj.setVisible(true);
			
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
		lblAlbiMall.setBounds(395, 13, 445, 43);
		contentPane.add(lblAlbiMall);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(135, 206, 250)));
		scrollPane.setBounds(12, 82, 1211, 733);
		contentPane.add(scrollPane);
		
		tblBleresit = new JTable();
		scrollPane.setViewportView(tblBleresit);
		insertTable();
		
		
		JLabel lblKrko = new JLabel("K\u00EBrko:");
		lblKrko.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblKrko.setBounds(854, 828, 66, 22);
		contentPane.add(lblKrko);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				updateTable();
			}
		});
		txtSearch.setBounds(933, 829, 290, 21);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
	}
	public void insertTable()
	{
		try 
		{
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Email","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,b.tel,b.email,q.qyteti,s.shteti from tblbleresit b,tblgjinia g, tblqytetet q, tblshtetet s where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String np = res.getString("b.nrPersonal");
				String em = res.getString("b.emri");
				String mb = res.getString("b.mbiemri");
				String dt = res.getString("b.dtLindjes");
				String gj = res.getString("g.pershkrimi");
				String tel = res.getString("b.tel");
				String email = res.getString("b.email");
				String qt = res.getString("q.qyteti");
				String st = res.getString("s.shteti");
				String[] data = dt.split("-");
				String viti = data[0];
				String muaji = data[1];
				String dita = data[2];
				String dataFinale = dita+"/"+muaji+"/"+viti;
				
				model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,email,qt,st});
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
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Email","Qyteti vendbanimit","Shteti vendbanimit"}, 0);
			int counter = 1;
			String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,b.tel,b.email,q.qyteti,s.shteti from tblbleresit b,tblgjinia g, tblqytetet q, tblshtetet s where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id and (b.nrPersonal like '%"+txtSearch.getText()+"%')";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String np = res.getString("b.nrPersonal");
				String em = res.getString("b.emri");
				String mb = res.getString("b.mbiemri");
				String dt = res.getString("b.dtLindjes");
				String gj = res.getString("g.pershkrimi");
				String tel = res.getString("b.tel");
				String email = res.getString("b.email");
				String qt = res.getString("q.qyteti");
				String st = res.getString("s.shteti");
				String[] data = dt.split("-");
				String viti = data[0];
				String muaji = data[1];
				String dita = data[2];
				String dataFinale = dita+"/"+muaji+"/"+viti;
				
				model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,email,qt,st});
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
