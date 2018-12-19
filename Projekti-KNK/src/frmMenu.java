import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.PreparedStatement;
import java.awt.Dimension;
import java.awt.image.*;
import java.awt.Color;
import java.sql.*;
public class frmMenu extends JFrame {

	private JPanel contentPane;
	private JLabel lblUseri =  new JLabel("");
	PreparedStatement pst = null;
	Connection conn = null;
	ResultSet res = null;
	private JLabel lblshtostaf;
	private JLabel lblstafi;
	private JMenuItem mntmStafi;
	private JMenuItem mntmShtoAnetareTe;
	private static String pozita= "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMenu frame = new frmMenu();
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
	public frmMenu() {
		conn = connectionClass.connectDb();
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMenu.class.getResource("/imgs/logo3.jpg")));
		setTitle("Albi Mall - Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1226, 792);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProduktet = new JMenu("Produktet");
		menuBar.add(mnProduktet);
		
		JMenuItem mntmRegjistrimiIMallit = new JMenuItem("Regjistrimi i mallit");
		mntmRegjistrimiIMallit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegjistrimiMallit obj = new frmRegjistrimiMallit();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmRegjistrimiIMallit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnProduktet.add(mntmRegjistrimiIMallit);
		
		JMenuItem mntmShtoMallTe = new JMenuItem("Shto produkte");
		mntmShtoMallTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnProduktet.add(mntmShtoMallTe);
		
		JMenu mnRaportet = new JMenu("Raportet");
		menuBar.add(mnRaportet);
		
		JMenuItem mntmRaportet = new JMenuItem("Raportet");
		mntmRaportet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRaportet obj = new frmRaportet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmRaportet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnRaportet.add(mntmRaportet);
		
		JMenuItem mntmLibriBlerjes = new JMenuItem("Libri Blerjes");
		mntmLibriBlerjes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnRaportet.add(mntmLibriBlerjes);
		
		JMenuItem mntmLibriShitjes = new JMenuItem("Libri Shitjes");
		mntmLibriShitjes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnRaportet.add(mntmLibriShitjes);
		
		JMenu mnDefinicionet = new JMenu("Administrimi");
		menuBar.add(mnDefinicionet);
		
		JMenuItem mntmFurnizuesit = new JMenuItem("Furnizuesit");
		mntmFurnizuesit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFurnitoret obj = new frmFurnitoret();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			
			}
		});
		mntmFurnizuesit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmFurnizuesit);
		
		JMenuItem mntmKlientet = new JMenuItem("Klientet");
		mntmKlientet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				frmKlientet obj = new frmKlientet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmKlientet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmKlientet);
		
		JMenuItem mntmShtoKlientTe = new JMenuItem("Shto klient te ri");
		mntmShtoKlientTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmBleresi obj = new frmBleresi();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmShtoKlientTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmShtoKlientTe);
		
		mntmStafi = new JMenuItem("Stafi");
		mntmStafi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pozita.equals("Pronar"))
				{
					frmStafi obj = new frmStafi();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
			}
		});
		mntmStafi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_MASK));
		mnDefinicionet.add(mntmStafi);
		
		mntmShtoAnetareTe = new JMenuItem("Shto anetare te ri");
		mntmShtoAnetareTe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_MASK));
		mntmShtoAnetareTe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pozita.equals("Pronar"))
				{
//					frmSignUp obj = new frmSignUp();
//					obj.setVisible(true);
//					obj.setLocationRelativeTo(null);
				}
			}
		});
		mnDefinicionet.add(mntmShtoAnetareTe);
		
		JMenu mnShitja = new JMenu("Shitja");
		menuBar.add(mnShitja);
		
		JMenuItem mntmShitja = new JMenuItem("Shitja");
		mntmShitja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frmShitja obj = new frmShitja();
			obj.setVisible(true);
			obj.setLocationRelativeTo(null);
			}
		});
		mntmShitja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnShitja.add(mntmShitja);
		
		JMenu mnNdihma = new JMenu("Ndihma");
		menuBar.add(mnNdihma);
		
		JMenuItem mntmNdihma = new JMenuItem("Ndihma");
		mntmNdihma.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnNdihma.add(mntmNdihma);
		
		JMenu mnDalja = new JMenu("Dalja");
		menuBar.add(mnDalja);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Useri.setNrPersonal("");
				Useri.setEmri("");
				Useri.setMbiemri("");
				String dalja = "Kaloni bukur!";
				JOptionPane.showMessageDialog(null,dalja);
				dispose();
				frmLogin obj = new frmLogin();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnDalja.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblshitja = new JLabel("");
		lblshitja.setVerticalAlignment(SwingConstants.BOTTOM);
		lblshitja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmShitja obj = new frmShitja();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		
		lblshitja.setBounds(164, 71, 100, 100);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(frmMenu.class.getResource("/imgs/buy.png"));
		    
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		Image dimg = img.getScaledInstance(100, 100,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		lblshitja.setIcon(imageIcon);
		contentPane.add(lblshitja);
		
		JLabel lblFurnizohu = new JLabel("");
		lblFurnizohu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmFurnitoret obj = new frmFurnitoret();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblFurnizohu.setPreferredSize(new Dimension(100, 100));
		lblFurnizohu.setMinimumSize(new Dimension(50, 50));
		lblFurnizohu.setMaximumSize(new Dimension(100, 100));
		lblFurnizohu.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/supply.png")));
		lblFurnizohu.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFurnizohu.setBounds(428, 71, 100, 100);
		contentPane.add(lblFurnizohu);
		
		JLabel lblShitja = new JLabel("Shitja");
		lblShitja.setForeground(Color.WHITE);
		lblShitja.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblShitja.setHorizontalAlignment(SwingConstants.CENTER);
		lblShitja.setBounds(164, 184, 100, 23);
		contentPane.add(lblShitja);
		
		JLabel lblfurnizohu = new JLabel("Furnitoret");
		lblfurnizohu.setForeground(Color.WHITE);
		lblfurnizohu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblfurnizohu.setHorizontalAlignment(SwingConstants.CENTER);
		lblfurnizohu.setBounds(428, 184, 100, 23);
		contentPane.add(lblfurnizohu);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmKlientet obj = new frmKlientet();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		label.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/clients.png")));
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBounds(692, 71, 100, 100);
		contentPane.add(label);
		
		JLabel lblKlientat = new JLabel("Klient\u00EBt");
		lblKlientat.setForeground(Color.WHITE);
		lblKlientat.setHorizontalAlignment(SwingConstants.CENTER);
		lblKlientat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKlientat.setBounds(692, 184, 100, 23);
		contentPane.add(lblKlientat);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBleresi obj = new frmBleresi();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		label_1.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/images.jpg")));
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setBounds(692, 303, 100, 100);
		contentPane.add(label_1);
		
		JLabel lblShtoKlient = new JLabel("Shto klient");
		lblShtoKlient.setForeground(Color.WHITE);
		lblShtoKlient.setHorizontalAlignment(SwingConstants.CENTER);
		lblShtoKlient.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblShtoKlient.setBounds(692, 416, 100, 23);
		contentPane.add(lblShtoKlient);
		
		JLabel lblStafi = new JLabel("Stafi");
		lblStafi.setForeground(Color.WHITE);
		lblStafi.setHorizontalAlignment(SwingConstants.CENTER);
		lblStafi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStafi.setBounds(956, 184, 100, 23);
		contentPane.add(lblStafi);
		
		lblstafi = new JLabel("");
		lblstafi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pozita.equals("Pronar"))
				{
					frmStafi obj = new frmStafi();
					obj.setVisible(true);
					obj.setLocationRelativeTo(null);
				}
				
			}
		});
		lblstafi.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/career_318-31359.jpg")));
		lblstafi.setVerticalAlignment(SwingConstants.BOTTOM);
		lblstafi.setBounds(956, 71, 100, 100);
		contentPane.add(lblstafi);
		
		JLabel lblShtoStaf = new JLabel("Shto staf");
		lblShtoStaf.setForeground(Color.WHITE);
		lblShtoStaf.setHorizontalAlignment(SwingConstants.CENTER);
		lblShtoStaf.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblShtoStaf.setBounds(956, 416, 100, 23);
		contentPane.add(lblShtoStaf);
		
		lblshtostaf = new JLabel("");
		lblshtostaf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(pozita.equals("Pronar"))
				{
//					frmSignUp obj = new frmSignUp();
//					obj.setVisible(true);
//					obj.setLocationRelativeTo(null);
				}
			}
		});
		lblshtostaf.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/administrator_add.png")));
		lblshtostaf.setVerticalAlignment(SwingConstants.BOTTOM);
		lblshtostaf.setBounds(956, 303, 100, 100);
		contentPane.add(lblshtostaf);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/Add-item-icon.png")));
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setBounds(164, 303, 100, 100);
		contentPane.add(label_2);
		
		JLabel lblShtoProdukte = new JLabel("Shto produkte");
		lblShtoProdukte.setForeground(Color.WHITE);
		lblShtoProdukte.setHorizontalAlignment(SwingConstants.CENTER);
		lblShtoProdukte.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblShtoProdukte.setBounds(152, 416, 128, 23);
		contentPane.add(lblShtoProdukte);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/blerje.png")));
		label_5.setVerticalAlignment(SwingConstants.BOTTOM);
		label_5.setBounds(428, 303, 100, 100);
		contentPane.add(label_5);
		
		JLabel lblLibriIBlerjes = new JLabel("Libri i blerjes");
		lblLibriIBlerjes.setForeground(Color.WHITE);
		lblLibriIBlerjes.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibriIBlerjes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLibriIBlerjes.setBounds(417, 416, 128, 23);
		contentPane.add(lblLibriIBlerjes);
		
		JLabel label_6 = new JLabel("");
		label_6.setSize(new Dimension(100, 100));
		label_6.setMinimumSize(new Dimension(50, 50));
		label_6.setMaximumSize(new Dimension(100, 100));
		label_6.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/sell.png")));
		label_6.setVerticalAlignment(SwingConstants.BOTTOM);
		label_6.setBounds(164, 517, 100, 100);
		contentPane.add(label_6);
		
		JLabel lblLibriIShitjes = new JLabel("Libri i shitjes");
		lblLibriIShitjes.setForeground(Color.WHITE);
		lblLibriIShitjes.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibriIShitjes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLibriIShitjes.setBounds(152, 630, 128, 23);
		contentPane.add(lblLibriIShitjes);
		
		JLabel lblPerdoruesi = new JLabel("Perdoruesi:");
		lblPerdoruesi.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPerdoruesi.setForeground(Color.WHITE);
		lblPerdoruesi.setBounds(914, 0, 91, 23);
		contentPane.add(lblPerdoruesi);
		
		
		lblUseri.setForeground(Color.WHITE);
		lblUseri.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUseri.setBounds(1005, 0, 215, 23);
		
		contentPane.add(lblUseri);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/helpi.png")));
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setBounds(428, 517, 100, 100);
		contentPane.add(label_3);
		
		JLabel lblNdihma = new JLabel("Ndihma");
		lblNdihma.setHorizontalAlignment(SwingConstants.CENTER);
		lblNdihma.setForeground(Color.WHITE);
		lblNdihma.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNdihma.setBounds(428, 630, 100, 23);
		contentPane.add(lblNdihma);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Useri.setNrPersonal("");
				Useri.setEmri("");
				Useri.setMbiemri("");
				String dalja = "Kaloni bukur!";
				JOptionPane.showMessageDialog(null,dalja);
				dispose();
				frmLogin obj = new frmLogin();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		label_4.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/exiti.jpg")));
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setBounds(692, 517, 100, 100);
		contentPane.add(label_4);
		
		JLabel lblDalja = new JLabel("Dalja");
		lblDalja.setHorizontalAlignment(SwingConstants.CENTER);
		lblDalja.setForeground(Color.WHITE);
		lblDalja.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDalja.setBounds(692, 630, 100, 23);
		contentPane.add(lblDalja);

		
		JLabel label_7 = new JLabel("");
		label_7.setForeground(Color.WHITE);
		label_7.setIcon(new ImageIcon(frmMenu.class.getResource("/imgs/shop.jpg")));
		label_7.setBounds(0, 0, 1220, 731);
		contentPane.add(label_7);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{menuBar, mnProduktet, mntmRegjistrimiIMallit, mntmShtoMallTe, mnRaportet, mntmRaportet, mntmLibriBlerjes, mntmLibriShitjes, mnDefinicionet, mntmFurnizuesit, mntmKlientet, mntmShtoKlientTe, mnShitja, mntmShitja, mnDalja, mntmExit, contentPane}));
		perdoruesi();
	}
	public void perdoruesi()
	{
		try
		{
			String emri = Useri.getEmri();
			String mbiemri = Useri.getMbiemri();
			String nrPersonal = Useri.getNrPersonal();
			lblUseri.setText(emri+" "+mbiemri);
			
			String sql = "select p.pershkrimi from tblpozita p,tblStafi s  where p.id = s.poziteId and s.nrPersonal = '"+nrPersonal+"'";
			
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();			
			if(res.next());
			{
				pozita = res.getString("p.pershkrimi");
				
			}
			pst.close();
			
			
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
			
		}
		
	}
}
