import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.KeyAdapter;

public class frmStafi extends JFrame {

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
					frmStafi frame = new frmStafi();
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
	public frmStafi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmStafi.class.getResource("/imgs/logo3.jpg")));
		setTitle("Albi Mall - Stafi");
		conn = connectionClass.connectDb();
		//updateTable();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1360, 764);
		
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
		scrollPane.setBounds(12, 69, 1318, 562);
		contentPane.add(scrollPane);
		
		tblBleresit = new JTable();
		scrollPane.setViewportView(tblBleresit);
		insertTable();
		
		JLabel lblKrko = new JLabel("K\u00EBrko:");
		lblKrko.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblKrko.setBounds(1004, 657, 55, 21);
		contentPane.add(lblKrko);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				updateTable();
			}
		});
		
		txtSearch.setBounds(1071, 656, 259, 22);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
	}
	
	public void insertTable()
	{
		try 
		{
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Email","Qyteti vendbanimit","Shteti vendbanimit","Pozita","Paga"}, 0);
			int counter = 1;
			String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,q.qyteti,s.shteti,b.email,b.tel,b.paga,p.pershkrimi from tblstafi b,tblgjinia g, tblqytetet q, tblshtetet s, tblpozita p where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id and b.poziteid = p.id";
			
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
				String qt = res.getString("q.qyteti");
				String st = res.getString("s.shteti");
				String tel = res.getString("b.tel");
				String email = res.getString("b.email");
				String pozita = res.getString("p.pershkrimi");
				String[] data = dt.split("-");
				String viti = data[0];
				String muaji = data[1];
				String dita = data[2];
				String dataFinale = dita+"/"+muaji+"/"+viti;
				String euro = "\u20ac";
				String paga = res.getString("b.paga");
				paga = paga +" "+euro;
				model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,email,qt,st,pozita,paga});
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
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr.", "Numri Personal", "Emri","Mbiemri","Data e lindjes","Gjinia","Telefoni","Qyteti vendbanimit","Shteti vendbanimit","Pozita"}, 0);
			int counter = 1;
			String sql = "select b.nrPersonal,b.emri,b.mbiemri,b.dtLindjes,g.pershkrimi,q.qyteti,s.shteti,b.tel,p.pershkrimi from tblstafi b,tblgjinia g, tblqytetet q, tblshtetet s, tblpozita p where b.gjiniaId = g.id and b.adreseId = q.id and q.sid = s.id and b.poziteid = p.id and b.nrPersonal like '%"+txtSearch.getText()+"%'";
			
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
				String qt = res.getString("q.qyteti");
				String st = res.getString("s.shteti");
				String tel = res.getString("b.tel");
				String pozita = res.getString("p.pershkrimi");
				String[] data = dt.split("-");
				String viti = data[0];
				String muaji = data[1];
				String dita = data[2];
				String dataFinale = dita+"/"+muaji+"/"+viti;
				
				model.addRow(new Object[] {c,np,em,mb,dataFinale,gj,tel,qt,st,pozita});
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
