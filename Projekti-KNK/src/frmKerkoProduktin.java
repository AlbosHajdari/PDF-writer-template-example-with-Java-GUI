

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.datatransfer.*;
import java.awt.Component;
import javax.swing.SwingConstants;

public class frmKerkoProduktin extends JFrame {

	private JPanel contentPane;
	private JTextField txtKerkoProduktin;
	int i =0;
	int numri = 0;
	private String SelektoniNjeRresht = "Ju duhet te selektoni nje rresht.";
	private PreparedStatement pst;
	private ResultSet res;
	private Connection conn;
	
	private JTable table_1;
	private JScrollPane scrollPane;
	private JLabel lblKerkoProduktin;
	private JLabel lblEmriIProduktit;
	private JButton btnKopjoBarkodin = new JButton();
	private BufferedImage imgKerkoProduktin = null;
	private BufferedImage imgKopjoBarkodin = null;
	private JButton btnShqip;
	private JButton btnEnglish;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmKerkoProduktin frame = new frmKerkoProduktin();
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
	public frmKerkoProduktin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmKerkoProduktin.class.getResource("/imgs/ikonaKerkoProduktin.png")));
		setBounds(100, 100, 534, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtKerkoProduktin = new JTextField();
		txtKerkoProduktin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateTable();
			}
		});
		txtKerkoProduktin.setBounds(10, 33, 152, 20);
		contentPane.add(txtKerkoProduktin);
		txtKerkoProduktin.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 498, 134);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table_1);
		
		lblKerkoProduktin = new JLabel("");
		lblKerkoProduktin.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/kerkoProduktin.png")));
		lblKerkoProduktin.setBounds(175, 22, 45, 40);
		contentPane.add(lblKerkoProduktin);
		
		insertTable();
		
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(205);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(205);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(70);

		
		try
		{
			imgKerkoProduktin = ImageIO.read(frmKerkoProduktin.class.getResource("/imgs/kerkoProduktin.png"));
			imgKopjoBarkodin = ImageIO.read(frmKerkoProduktin.class.getResource("/imgs/KopjoBarkodin.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Image dimgkerkoProduktin = imgKerkoProduktin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imageKerkoProduktin = new ImageIcon(dimgkerkoProduktin);
		lblKerkoProduktin.setIcon(imageKerkoProduktin);
		
		Image dimgKopjoBarkodin = imgKopjoBarkodin.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imageKopjoBarkodin = new ImageIcon(dimgKopjoBarkodin);
		btnKopjoBarkodin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnKopjoBarkodin.setText("Kopjo barkodin");
		btnKopjoBarkodin.setIcon(imageKopjoBarkodin);
		
		lblEmriIProduktit = new JLabel("Emri i produktit");
		lblEmriIProduktit.setBounds(10, 11, 136, 14);
		contentPane.add(lblEmriIProduktit);
		
		scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		
		btnKopjoBarkodin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int rreshti = table_1.getSelectedRow();
					StringSelection stringSelection = new StringSelection(String.valueOf(table_1.getValueAt(rreshti, 1)));
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,SelektoniNjeRresht);
				}
			}
		});
		btnKopjoBarkodin.setBounds(10, 223, 152, 23);
		contentPane.add(btnKopjoBarkodin);
		
		NdryshoGjuhen();
		
		btnShqip = new JButton("");
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuhesia.gjuha = "alb";
				NdryshoGjuhen();
			}
		});
		btnShqip.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/alb.png")));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setAlignmentX(0.5f);
		btnShqip.setBounds(448, 11, 25, 25);
		contentPane.add(btnShqip);
		
		btnEnglish = new JButton("");
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuhesia.gjuha = "eng";
				NdryshoGjuhen();
			}
		});
		btnEnglish.setIcon(new ImageIcon(frmKerkoProduktin.class.getResource("/imgs/eng.png")));
		btnEnglish.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEnglish.setAlignmentX(0.5f);
		btnEnglish.setBounds(483, 11, 25, 25);
		contentPane.add(btnEnglish);
		
	}
	
	public void insertTable()
	{
		try 
		{
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Produkti dhe brendi" , "Sasia"}, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			int counter = 1;
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbknk?useSSL=false", "root", "root");
			String sql = "select r.barkodi , r.emriProduktit,  r.sasia , b.emriBrendit from tblRegjistrimiMallit r, tblBrendet b where b.id = r.brendid";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String barkodi = res.getString("r.barkodi");
				String emriProduktit = res.getString("r.emriProduktit");
				String emriBrendit = res.getString("b.emriBrendit");
				String emri = emriProduktit + " " + emriBrendit;
				String sasia = res.getString("r.sasia");
				
				model.addRow(new Object[] {c,barkodi,emri,sasia});
				counter++;
				
			}
			pst.close();
			table_1.setModel(model);
			
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
			
			
			DefaultTableModel model = new DefaultTableModel(new String[]{"Nr." , "Barkodi" , "Produkti dhe brendi" , "Sasia"}, 0)
			{
				@Override
		        public boolean isCellEditable(int row, int column)
		        {
					return false;
		        }

			};
			int counter = 1;
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbknk?useSSL=false", "root", "root");
			String sql = "select r.barkodi , r.emriProduktit,  r.sasia , b.emriBrendit from tblRegjistrimiMallit r, tblBrendet b where b.id = r.brendid and r.emriProduktit like '%"+txtKerkoProduktin.getText()+"%'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
			
				String c = String.valueOf(counter);
				String barkodi = res.getString("r.barkodi");
				String emriProduktit = res.getString("r.emriProduktit");
				String emriBrendit = res.getString("b.emriBrendit");
				String emri = emriProduktit + " " + emriBrendit;
				String sasia = res.getString("r.sasia");
				
				model.addRow(new Object[] {c,barkodi,emri,sasia});
				counter++;
				
			}
			pst.close();
			table_1.setModel(model);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(205);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(205);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(70);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}
	private void NdryshoGjuhen()
	{
		if (Gjuhesia.gjuha=="eng")
		{
			lblEmriIProduktit.setText("Product's name");
			btnKopjoBarkodin.setText("Copy the barcode");
			
			table_1.getColumnModel().getColumn(0).setHeaderValue("No.");
			table_1.getColumnModel().getColumn(1).setHeaderValue("Barcode");
			table_1.getColumnModel().getColumn(2).setHeaderValue("Product and brand");
			table_1.getColumnModel().getColumn(3).setHeaderValue("Amount");
			
			scrollPane.setViewportView(table_1);
		}
		else
		{
			lblEmriIProduktit.setText("Emri i produktit");
			btnKopjoBarkodin.setText("Kopjo barkodin");
			
			table_1.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			table_1.getColumnModel().getColumn(1).setHeaderValue("Barkodi");
			table_1.getColumnModel().getColumn(2).setHeaderValue("Produkti dhe brendi");
			table_1.getColumnModel().getColumn(3).setHeaderValue("Sasia");
			
			scrollPane.setViewportView(table_1);
		}
	}
}
