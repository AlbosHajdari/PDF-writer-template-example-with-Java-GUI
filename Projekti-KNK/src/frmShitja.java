

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Frame;
import java.awt.Image;

import java.awt.Component;

import static javax.swing.ScrollPaneConstants.*;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
/*
import com.itextpdf.text.BaseColor; 
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
*/
import java.io.FileOutputStream;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.DropMode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class frmShitja extends JFrame {
	

	
	private JPanel contentPane;
	private JTextField txtBarkodi;
	private JTable table;
	int numri = 0;
	
	private double ShumaTotaliPaTVSH = 0.00;
	private double ShumaTotaliTVSHs = 0.00;
	private double ShumaTotaliMeTVSH = 0.00;
	private double ShumaZbritjes = 0.00;
	private double shumatotale = 0.00;
	private double PerqindejaZbritjes = 0.00;
	
	
	private int id_e_klientit;
	
	private ArrayList<ArrayList<String>> values = new ArrayList<>();
	
	private Map<String, Integer> map = new HashMap<>();
	
	private ArrayList<Integer> rreshtat = new ArrayList<>();
	
	
	private int sasiaDinamike = 0;
	private int k;
	private int i;
	private boolean z = true;
	private boolean y = true;
	private boolean x = true;
	private double Shuma_e_Zbritur= 0;
	
	private DecimalFormat df = new DecimalFormat("0.00");
	
	private JTextField txtID_klientit;
	
	private final ButtonGroup grupiRadioButonave = new ButtonGroup();
	
	private JRadioButton rdbtnKlientIZakonshem = new JRadioButton("Klient i zakonshem");
	private JRadioButton rdbtnKlientIRregullt = new JRadioButton("Klient i rregullt");
	private JTextField txtSasia;
	private JLabel lblMoney = new JLabel();
	private JLabel lblTotaliMeTVSH = new JLabel();
	private JLabel lblTotali2 = new JLabel();
	private JLabel lblTotaliZbritur = new JLabel();
	private JLabel lblTotaliTVSHs = new JLabel();
	private JLabel lblTotaliPaTVSH = new JLabel();
	JButton btnShto = new JButton("");
	JScrollPane scrollPane = new JScrollPane();
	JButton btnKerkoProduktin = new JButton();
	JButton btnPerfundoBlerjen = new JButton("Perfundo blerjen");
	JButton btnAnglisht = new JButton("");
	JLabel lblBackgroundShitja = new JLabel("");
	JLabel lblTotaliPaTVSH1 = new JLabel("Totali pa TVSH");
	JLabel lblTotaliITvshs = new JLabel("Totali i TVSH-s");
	JLabel lblTotaliMeTvsh = new JLabel("Totali me TVSH");
	JLabel lblTotali = new JLabel("Totali");
	JLabel lblTotaliIZbritur = new JLabel("Totali i zbritur");
	
	BufferedImage imgShto1 = null;
	BufferedImage imgShto2 = null;
	BufferedImage imgShto3 = null;
	
	BufferedImage imgKerkoProduktin1 = null;
	BufferedImage imgKerkoProduktin2 = null;
	BufferedImage imgKerkoProduktin3 = null;
	
	BufferedImage imgFshijArtikullin1 = null;
	BufferedImage imgFshijArtikullin2 = null;
	BufferedImage imgFshijArtikullin3 = null;
	
	BufferedImage imgPerfundoBlerjen = null;
	
	JLabel lblBarkodi = new JLabel("Barkodi");
	
	JLabel lblSasia = new JLabel("Sasia");
	
	
	
	JLabel lblIdEKlientit = new JLabel("ID e klientit:");
	
	private JButton btnFshijArtikullin = new JButton("");
	private String[] columnNames = {"Nr", "Produkti dhe brendi", "Gjinia", "Madhesia", "Njesia matese", "Sasia", "Cmimi pa TVSH", "TVSH", "Cmimi me TVSH","Totali pa TVSH", "Totali i TVSH-s", "Totali me TVSH","Zbrit", "Shuma e zbritur", "Totali"};
	private DefaultTableModel model = new DefaultTableModel(null, columnNames)
	{
		@Override
        public boolean isCellEditable(int row, int column)
        {
            if (column == 5 || column == 12)
            	return true;
            else
            	return false;
        }

	};
	private final JButton btnShqip = new JButton("");
	
	
	private void NdryshoSasineOseZbritjen()
	{
		try
		{
			z=true;
			shumatotale = 0;
			ShumaTotaliPaTVSH = 0;
			ShumaTotaliTVSHs = 0;
			ShumaTotaliMeTVSH = 0;
			ShumaZbritjes = 0;
			PerqindejaZbritjes = 0;
			y=true;
			for (i=0; i<values.size(); i++)
			{
				String PQZstring = String.valueOf(table.getValueAt(i,12));
				PQZstring = PQZstring.replace(" ", "");
				PQZstring = PQZstring.replace("%", "");
				
				
				String TotaliString = String.valueOf(table.getValueAt(i,14));
				TotaliString = TotaliString.replace(" ", "");
				TotaliString = TotaliString.replace("€", "");
				
				
				if ( Integer.parseInt((String) table.getValueAt(i, 5)) < 0 || Double.parseDouble(PQZstring)<0 || Double.parseDouble(PQZstring)>=100)
				{
					z = false;
				}
				else
				{
					PerqindejaZbritjes = Double.parseDouble(PQZstring);
					sasiaDinamike = map.get(values.get(i).get(15));
					sasiaDinamike = sasiaDinamike - Integer.parseInt(values.get(i).get(5));
					values.get(i).set(5, (String) table.getValueAt(i, 5));
					sasiaDinamike = sasiaDinamike + Integer.parseInt(values.get(i).get(5));
					map.put(values.get(i).get(15), sasiaDinamike);
					
				}
				double TVSH = 0.18;
				double CmimiPerCopePaTVSH = Double.parseDouble(values.get(i).get(6));
				double CmimiPerCopeMeTVSH = CmimiPerCopePaTVSH*TVSH+ CmimiPerCopePaTVSH;
				double TotaliPaTVSH = CmimiPerCopePaTVSH*Double.parseDouble(values.get(i).get(5)); // Double.parseDouble(values.get(i).get(5)) eshte sasia
				double Totali_i_TVSHs =  (CmimiPerCopeMeTVSH-CmimiPerCopePaTVSH)*Double.parseDouble(values.get(i).get(5));  // Double.parseDouble(values.get(i).get(5)) eshte sasia
				double TotaliMeTVSH = TotaliPaTVSH + Totali_i_TVSHs;
				Shuma_e_Zbritur = TotaliMeTVSH*PerqindejaZbritjes/100;
				double TotaliPerfundimtar = TotaliMeTVSH - Shuma_e_Zbritur;
				
				String tptFormatuar = df.format(TotaliPaTVSH)+ " €";
				table.setValueAt(String.valueOf(tptFormatuar), i, 9);
				
				String ttFormatuar = df.format(Totali_i_TVSHs)+ " €";
				table.setValueAt(String.valueOf(ttFormatuar), i, 10);
				
				String tmtFormatuar = df.format(TotaliMeTVSH)+ " €";
				table.setValueAt(String.valueOf(tmtFormatuar), i, 11);
				
				String shzFormatuar = df.format(Shuma_e_Zbritur)+ " €";
				table.setValueAt(String.valueOf(shzFormatuar), i, 13);
				
				String tpFormatuar = df.format(TotaliPerfundimtar)+ " €";
				table.setValueAt(String.valueOf(tpFormatuar), i, 14);
				
				values.get(i).set(9, String.valueOf(TotaliPaTVSH));
				values.get(i).set(10, String.valueOf(Totali_i_TVSHs));
				values.get(i).set(11, String.valueOf(TotaliMeTVSH));
				values.get(i).set(12, String.valueOf(PerqindejaZbritjes));
				values.get(i).set(13, String.valueOf(Shuma_e_Zbritur));
				values.get(i).set(14, String.valueOf(TotaliPerfundimtar));
				
				Shuma_e_Zbritur= 0;
				PerqindejaZbritjes = 0;
				
				shumatotale = shumatotale + TotaliPerfundimtar;
				ShumaTotaliPaTVSH = ShumaTotaliPaTVSH + TotaliPaTVSH;
				ShumaTotaliTVSHs = ShumaTotaliTVSHs+Totali_i_TVSHs;
				ShumaTotaliMeTVSH = ShumaTotaliMeTVSH + TotaliMeTVSH;
				ShumaZbritjes = ShumaTotaliMeTVSH - shumatotale ;
			}
			lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
			lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
			
			lblTotaliPaTVSH.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
			lblTotaliTVSHs.setText(String.valueOf(df.format(ShumaTotaliTVSHs)) + " €");
			lblTotaliMeTVSH.setText(String.valueOf(df.format(ShumaTotaliMeTVSH)) + " €");
			lblTotaliZbritur.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmShitja frame = new frmShitja();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setUndecorated(true);
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
	public frmShitja() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
	
		
		
		
		setBounds(100, 100, 1378, 837);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setFont(new Font("Times New Roman", Font.BOLD, 99));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBarkodi = new JTextField();
		
		txtBarkodi.setBounds(20, 234, 261, 20);
		contentPane.add(txtBarkodi);
		txtBarkodi.setColumns(10);
		
		txtSasia = new JTextField();
		txtSasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnShto.doClick();
				}
			}
		});
		
		txtSasia.setBounds(23, 290, 86, 20);
		contentPane.add(txtSasia);
		txtSasia.setColumns(10);

		btnShto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnShto.doClick();
				}
				
			}
		});
		

		
		
		btnShto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnShto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					NdryshoSasineOseZbritjen();
					if (z==false)
					{
						JOptionPane.showMessageDialog(null,"Nuk lejohen vlera negative tek sasia apo vlera jashtw intervalit (1,100) tek zbritja.");
					}
					else
					{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbknk?useSSL=false", "root", "root");
					
					String query0 = "SELECT COUNT(*) FROM tblregjistrimimallit WHERE barkodi='"+txtBarkodi.getText()+"'";
					Statement stmt0 = conn.createStatement();
					ResultSet rs0 = stmt0.executeQuery(query0);
					while(rs0.next())
					{
						if(rs0.getInt("COUNT(*)") == 0)
						{
							y= false;
						}
					}
					
					String query1 = "select b.emriBrendit, r.emriProduktit, g.pershkrimi, s.size, nj.pershkrimi, r.sasia,\r\n" + 
									"cs.cmimiShitjes, cb.cmimiBlerjes, r.barkodi\r\n" + 
									"from tblBrendet b, tblRegjistrimiMallit r, tblgjinia g, tblSize s, tblNjMatese nj, tblCmimet cs, tblCmimiBlerjes cb\r\n" + 
									"where r.barkodi = '"+txtBarkodi.getText()+"' and r.njMateseId = nj.id and r.sizeId = s.id\r\n" + 
									"and r.id = cs.produktetId and cs.produktetId = cb.cmimiId and r.brendid = b.id and r.gjiniaId = g.id;";
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery(query1);
					
					String nr = String.valueOf(numri+1);
					
					if(y==false)
					{
						JOptionPane.showMessageDialog(null,"Ky produkt nuk ekziston ne stok.");
					}
					else
					while (rs1.next()) //
					{
						int sasiaRegjistrimi = rs1.getInt("r.sasia");
						int sasia = Integer.parseInt(txtSasia.getText());
						if(sasia<=0)
						{
							JOptionPane.showMessageDialog(null,"Nuk lejohen vlera negative tek sasia apo vlera jashtw intervalit (1,100) tek zbritja.");
							txtSasia.requestFocus();
						}
						else if (sasia>sasiaRegjistrimi)
						{
							JOptionPane.showMessageDialog(null,"Nuk ka sasi te mjaftueshme ne stok.");
							txtSasia.requestFocus();
						}
						else
						{	
							if(map.containsKey(txtBarkodi.getText()))
							{
								sasiaDinamike = map.get(txtBarkodi.getText());
								sasiaDinamike = sasiaDinamike + sasia;
								if(sasiaDinamike<=sasiaRegjistrimi)
								{
									map.put(txtBarkodi.getText(), sasiaDinamike);
								}
							}
							else
							{
								sasiaDinamike = sasia;
								map.put(txtBarkodi.getText(), sasiaDinamike);
							}
							
							if(sasiaDinamike>sasiaRegjistrimi)
							{
								JOptionPane.showMessageDialog(null,"Nuk ka sasi te mjaftueshme ne stok.");
								txtSasia.requestFocus();
							}
							else
							{
								map.put(txtBarkodi.getText(), sasiaDinamike);
								double TVSH = 0.18;
								double CmimiPerCopePaTVSH = rs1.getDouble("cs.cmimiShitjes");
								double CmimiPerCopeMeTVSH = CmimiPerCopePaTVSH*TVSH+ CmimiPerCopePaTVSH;
								double TotaliPaTVSH = CmimiPerCopePaTVSH*sasia;
								double Totali_i_TVSHs =  (CmimiPerCopeMeTVSH-CmimiPerCopePaTVSH)*sasia;
								double TotaliMeTVSH = TotaliPaTVSH + Totali_i_TVSHs;
								Shuma_e_Zbritur = PerqindejaZbritjes*TotaliMeTVSH;
								double TotaliPerfundimtar = TotaliMeTVSH - Shuma_e_Zbritur;
								
								shumatotale = shumatotale + TotaliPerfundimtar;
								ShumaTotaliPaTVSH = ShumaTotaliPaTVSH + TotaliPaTVSH;
								ShumaTotaliTVSHs = ShumaTotaliTVSHs+Totali_i_TVSHs;
								ShumaTotaliMeTVSH = ShumaTotaliMeTVSH + TotaliMeTVSH;
								ShumaZbritjes = ShumaTotaliMeTVSH - shumatotale ;
								
								lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
								lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
								
								lblTotaliPaTVSH.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
								lblTotaliTVSHs.setText(String.valueOf(df.format(ShumaTotaliTVSHs)) + " €");
								lblTotaliMeTVSH.setText(String.valueOf(df.format(ShumaTotaliMeTVSH)) + " €");
								lblTotaliZbritur.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
								
								values.add(new ArrayList<>());
									
								values.get(numri).add(nr); 						//0
								values.get(numri).add(rs1.getString("r.emriProduktit") +" "+ rs1.getString("b.emriBrendit"));  	//1
								values.get(numri).add(rs1.getString("g.pershkrimi"));  			//2
								values.get(numri).add(rs1.getString("s.size"));  		 	 //3
								values.get(numri).add(rs1.getString("nj.pershkrimi"));  		//4
								values.get(numri).add(String.valueOf(sasia));			//5
								values.get(numri).add(String.valueOf(CmimiPerCopePaTVSH));  	//6
								values.get(numri).add(String.valueOf(TVSH));			//7
								values.get(numri).add(String.valueOf(CmimiPerCopeMeTVSH));		//8
								values.get(numri).add(String.valueOf(TotaliPaTVSH));		//9
								values.get(numri).add(String.valueOf(Totali_i_TVSHs));			//10
								values.get(numri).add(String.valueOf(TotaliMeTVSH));		//11
								values.get(numri).add(String.valueOf(PerqindejaZbritjes));	//12
								
								values.get(numri).add(String.valueOf(Shuma_e_Zbritur));		//13
								values.get(numri).add(String.valueOf(TotaliPerfundimtar)); 		//14
								
								
			
								values.get(numri).add(rs1.getString("r.barkodi")); 		//15
								values.get(numri).add(rs1.getString("cb.cmimiBlerjes")); 		//16
								    
								
								
								
							    DefaultTableModel model = (DefaultTableModel) table.getModel();
							    Object rowData[] = new Object[15];
							    for(i = 0; i < values.size(); i++)
							    {    	
							    	String Vlera6 = df.format(Double.parseDouble(values.get(i).get(6))) + " €";
							    	String Vlera7 = df.format(Double.parseDouble(values.get(i).get(7))*100) + "%";
							    	
							    	String Vlera8 = df.format(Double.parseDouble(values.get(i).get(8))) + " €";
							    	String Vlera9 = df.format(Double.parseDouble(values.get(i).get(9))) + " €";
							    	String Vlera10 = df.format(Double.parseDouble(values.get(i).get(10))) + " €";
							    	String Vlera11 = df.format(Double.parseDouble(values.get(i).get(11))) + " €";
							    	String Vlera12 = df.format(Double.parseDouble(values.get(i).get(12))) + " %";
							    	String Vlera13 = df.format(Double.parseDouble(values.get(i).get(13))) + " €";
							    	String Vlera14 = df.format(Double.parseDouble(values.get(i).get(14))) + " €";
							    	
							    	rowData[0] = values.get(i).get(0);
							        rowData[1] = values.get(i).get(1);
							        rowData[2] = values.get(i).get(2);
							        rowData[3] = values.get(i).get(3);
							        rowData[4] = values.get(i).get(4);
							        rowData[5] = values.get(i).get(5);
							        rowData[6] = Vlera6;
							        rowData[7] = Vlera7;
							        rowData[8] = Vlera8;
							        rowData[9] = Vlera9;
							        rowData[10] = Vlera10;
							        rowData[11] = Vlera11;
							        rowData[12] = Vlera12;
							        rowData[13] = Vlera13;
							        rowData[14] = Vlera14;
							    }
							    model.addRow(rowData);
							    numri++;
							}	
							
							scrollPane.setViewportView(table);
						}
	
					}
				}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		

		
		
		
		
		btnShto.setBounds(20, 321, 203, 43);
		contentPane.add(btnShto);
		

		
		
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(10, 375, 1342, 164);
		
		contentPane.add(scrollPane);
		
		
		
		


		
		

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				NdryshoSasineOseZbritjen();
			}
		});
		table.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_TAB)
				{
					NdryshoSasineOseZbritjen();
				}
			}
		});

		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);
		lblTotali2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotali2.setText("0.00 €");
		lblTotaliPaTVSH.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTotaliPaTVSH.setText("0.00 €");
		lblTotaliTVSHs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaliTVSHs.setText("0.00 €");
		lblTotaliMeTVSH.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaliMeTVSH.setText("0.00 €");
		lblTotaliZbritur.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotaliZbritur.setText("0.00 €");
		
		
		
		
		
		
		btnPerfundoBlerjen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnPerfundoBlerjen.doClick();
				}
			}
		});
		btnPerfundoBlerjen.setLayout( new BorderLayout() );
		btnPerfundoBlerjen.setHorizontalAlignment(SwingConstants.LEFT);
		btnPerfundoBlerjen.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPerfundoBlerjen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPerfundoBlerjen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{	
					x=true;
					if(values.size()==0)
					{
						JOptionPane.showMessageDialog(null,"Ju duhet te shisni dicka.");
					}
					else
					{
						NdryshoSasineOseZbritjen();
						if(z==false)
						{
							JOptionPane.showMessageDialog(null,"Nuk lejohen vlera negative tek sasia apo vlera jashtw intervalit (1,100) tek zbritja.");
						}
						else
						{	
							SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
							Date data1 = new Date();
							String data2 = formatter1.format(data1);
							
							
							SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
							Date koha1 = new Date();
							String koha2 = formatter2.format(koha1);
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbknk?useSSL=false", "root", "root");
							
							if(rdbtnKlientIRregullt.isSelected())
							{
								id_e_klientit = Integer.parseInt(txtID_klientit.getText());
								
								String query0 = "SELECT COUNT(*) FROM tblBleresit WHERE nrPersonal='"+id_e_klientit+"'";
								Statement stmt0 = conn.createStatement();
								ResultSet rs0 = stmt0.executeQuery(query0);
								while(rs0.next())
								{
									if(rs0.getInt("COUNT(*)") == 0)
									{
										x= false;
									}
								}
								if (x==false)
								{
									JOptionPane.showMessageDialog(null,"Ky bleres nuk ekziston.");
									//frmBleresi obj = new frmBleresi();
								}
								else
								{
									for (i=0; i<numri; i++)
									{	
										
										String query1 = "INSERT INTO malliShitur (barkodiProduktit, sasia, cmimiBlerjesMeTVSH, cmimiShitjesMeTVSH,zbritja, kohaShitjes, dtShitjes, staffId, bleresId) " 
														+ "VALUES ('" + values.get(i).get(15) + "','" + values.get(i).get(5) + "','" 
														+values.get(i).get(16) + "','" + values.get(i).get(8) +"','"+values.get(i).get(12) +"','"+koha2 +"','"  +data2 + "','"+  "stafi1"   + "','" + id_e_klientit + "')";
										Statement stmt1 = conn.createStatement();
										stmt1.executeUpdate(query1);
											
										String query2 = "UPDATE tblRegjistrimiMallit SET sasia = sasia-" +values.get(i).get(5)+ " WHERE barkodi = '"+values.get(i).get(15) +"'";
										Statement stmt2 = conn.createStatement();
										stmt2.executeUpdate(query2);
									}
									numri = 0;
									shumatotale = 0;
									ShumaTotaliPaTVSH = 0;
									ShumaTotaliTVSHs = 0;
									ShumaTotaliMeTVSH = 0;
									ShumaZbritjes = 0;
									PerqindejaZbritjes = 0;
									values.clear();
									map.clear();
									DefaultTableModel model = (DefaultTableModel)table.getModel();
									model.getDataVector().removeAllElements();
									model.fireTableDataChanged();
									lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
									lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
									
									lblTotaliPaTVSH.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
									lblTotaliTVSHs.setText(String.valueOf(df.format(ShumaTotaliTVSHs)) + " €");
									lblTotaliMeTVSH.setText(String.valueOf(df.format(ShumaTotaliMeTVSH)) + " €");
									lblTotaliZbritur.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
										
									JOptionPane.showMessageDialog(null, "BLERJA PERFUNDOI ME SUKSES!");

									rdbtnKlientIZakonshem.setSelected(true);
									txtID_klientit.setText("");
									txtID_klientit.setEnabled(false);
								}
							}
							else
							{
								for (i=0; i<numri; i++)
								{	
									
									String query1 = "INSERT INTO malliShitur (barkodiProduktit, sasia, cmimiBlerjesMeTVSH, cmimiShitjesMeTVSH,zbritja, kohaShitjes, dtShitjes, staffId) " 
													+ "VALUES ('" + values.get(i).get(15) + "','" + values.get(i).get(5) + "','" 
													+values.get(i).get(16) + "','" + values.get(i).get(8) +"','"+values.get(i).get(12) +"','"+koha2 +"','"  +data2 + "','"+  "stafi1')";
									Statement stmt1 = conn.createStatement();
									stmt1.executeUpdate(query1);
										
									String query2 = "UPDATE tblRegjistrimiMallit SET sasia = sasia-" +values.get(i).get(5)+ " WHERE barkodi = '"+values.get(i).get(15) +"'";
									Statement stmt2 = conn.createStatement();
									stmt2.executeUpdate(query2);
								}
								numri = 0;
								shumatotale = 0;
								ShumaTotaliPaTVSH = 0;
								ShumaTotaliTVSHs = 0;
								ShumaTotaliMeTVSH = 0;
								ShumaZbritjes = 0;
								values.clear();
								map.clear();
								DefaultTableModel model = (DefaultTableModel)table.getModel();
								model.getDataVector().removeAllElements();
								model.fireTableDataChanged();
								lblMoney.setText(String.valueOf(df.format(shumatotale)) + " €");
								lblTotali2.setText(String.valueOf(df.format(shumatotale)) + " €");
								
								lblTotaliPaTVSH.setText(String.valueOf(df.format(ShumaTotaliPaTVSH)) + " €");
								lblTotaliTVSHs.setText(String.valueOf(df.format(ShumaTotaliTVSHs)) + " €");
								lblTotaliMeTVSH.setText(String.valueOf(df.format(ShumaTotaliMeTVSH)) + " €");
								lblTotaliZbritur.setText(String.valueOf(df.format(ShumaZbritjes)) + " €");
								
								JOptionPane.showMessageDialog(null, "BLERJA PERFUNDOI ME SUKSES!");
							}
						}
					}
					/*
					Document doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("D://Fatura.pdf"));
					doc.open();
					com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
					Paragraph pr1 = new Paragraph("Nr  Kompania  Produkti  Gjinia  Madhesia  Njesia matese  Sasia  Cmimi per cope pa TVSH  TVSH  Cmimi per cope me TVSH  Totali pa TVSH  Totali i TVSH-s  Totali me TVSH  Shuma e zbritur  Totali",font);
					Paragraph pr2 = new Paragraph("------------------------------------------",font);
					
					doc.add(pr1);
					doc.add(pr2);
					doc.close();
					*/
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
				
				
				
			}
		});

		btnPerfundoBlerjen.setBounds(20, 676, 198, 43);
		contentPane.add(btnPerfundoBlerjen);
		rdbtnKlientIZakonshem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				rdbtnKlientIZakonshem.setSelected(true);
			}
		});
		
		rdbtnKlientIZakonshem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnKlientIZakonshem.setBackground(Color.ORANGE);
		rdbtnKlientIZakonshem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtID_klientit.setEnabled(false);
			}
		});
		
		grupiRadioButonave.add(rdbtnKlientIZakonshem);
		rdbtnKlientIZakonshem.setSelected(true);
		rdbtnKlientIZakonshem.setBounds(20, 567, 133, 23);
		contentPane.add(rdbtnKlientIZakonshem);
		rdbtnKlientIRregullt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				rdbtnKlientIRregullt.setSelected(true);
				txtID_klientit.setEnabled(true);
				txtID_klientit.requestFocus();
			}
		});
		rdbtnKlientIRregullt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnKlientIRregullt.setBackground(Color.ORANGE);
		
		
		rdbtnKlientIRregullt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID_klientit.setEnabled(true);
			}
		});
		grupiRadioButonave.add(rdbtnKlientIRregullt);
		rdbtnKlientIRregullt.setBounds(20, 597, 135, 23);
		contentPane.add(rdbtnKlientIRregullt);
		
		txtID_klientit = new JTextField();
		txtID_klientit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnPerfundoBlerjen.doClick();
				}
			}
		});
		txtID_klientit.setEnabled(false);
		txtID_klientit.setBounds(105, 625, 118, 20);
		contentPane.add(txtID_klientit);
		txtID_klientit.setColumns(10);
		btnFshijArtikullin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFshijArtikullin.setOpaque(false);
		btnFshijArtikullin.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/Fshij1.jpg")));
		
		
		btnFshijArtikullin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				shumatotale = 0;
				ShumaTotaliPaTVSH = 0;
				ShumaTotaliTVSHs = 0;
				ShumaTotaliMeTVSH = 0;
				ShumaZbritjes = 0;
				int vleraselektuarInteger;
				String vleraselektuarString;
				
				int gjatesiaEselektimit = table.getSelectedRows().length;
				if(gjatesiaEselektimit>0)
				{
					for(i=0; i<gjatesiaEselektimit; i++ )
					{
						vleraselektuarString =  String.valueOf(table.getValueAt(table.getSelectedRow(), 0));    //metoda table.getValueAt nuk e lejonte qe te shendrrohej direkt ne Integer, andaj se pari ne String e pastaj ne Integer
						vleraselektuarInteger = Integer.parseInt(vleraselektuarString);
					    rreshtat.add(vleraselektuarInteger);
					    model.removeRow(table.getSelectedRow());
					}
					
					
					for (i = rreshtat.size()-1; i>=0; i--) //duhet ne reverse sepse indexat bien poshte cdo here
					{
						k = rreshtat.get(i);
						values.remove(k-1);
						
					}
					
					for (i = 0 ; i< values.size(); i++)
					{
						model.setValueAt(i+1, i, 0);
						values.get(i).set(0, String.valueOf(i+1));
					}
					
					NdryshoSasineOseZbritjen();
					
					numri = values.size();
					sasiaDinamike = 0;
					map.clear();
					for (i=0; i<numri ; i++)
					{
						if (map.containsKey(values.get(i).get(15)))
						{
							sasiaDinamike = map.get(values.get(i).get(15));
							sasiaDinamike = sasiaDinamike + Integer.parseInt(values.get(i).get(5));
							map.put(values.get(i).get(15), sasiaDinamike);
						}
						else
						{
							sasiaDinamike = Integer.parseInt(values.get(i).get(5));
							map.put(values.get(i).get(15), sasiaDinamike);
						}
	
					}
					rreshtat.clear();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Nuk e keni selektuar asnje rresht");
				}
			}

		});
		
		btnFshijArtikullin.setBounds(1160, 710, 198, 52);
		contentPane.add(btnFshijArtikullin);
		btnAnglisht.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAnglisht.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAnglisht.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/eng.png")));
		
		
		
		

		btnAnglisht.setBounds(1327, 209, 25, 25);
		contentPane.add(btnAnglisht);
		lblMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lblMoney.setText("0.00 €");
		
		lblMoney.setBackground(new Color(0, 0, 0));
		lblMoney.setOpaque(true);
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 99));
		lblMoney.setForeground(new Color(0, 255, 0));
		lblMoney.setBounds(0, 21, 1372, 177);
		contentPane.add(lblMoney);
		
		
		lblIdEKlientit.setBounds(30, 628, 65, 14);
		contentPane.add(lblIdEKlientit);
		
		
		lblSasia.setBounds(10, 265, 46, 14);
		contentPane.add(lblSasia);
		
		
		lblBarkodi.setBounds(10, 209, 85, 14);
		contentPane.add(lblBarkodi);
		lblTotaliPaTVSH1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblTotaliPaTVSH1.setOpaque(true);
		lblTotaliPaTVSH1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTotaliPaTVSH1.setBackground(new Color(255, 255, 0));
		lblTotaliPaTVSH1.setBounds(1160, 550, 107, 14);
		contentPane.add(lblTotaliPaTVSH1);
		lblTotaliITvshs.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblTotaliITvshs.setOpaque(true);
		lblTotaliITvshs.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTotaliITvshs.setBackground(new Color(255, 255, 0));
		lblTotaliITvshs.setBounds(1160, 581, 107, 14);
		contentPane.add(lblTotaliITvshs);
		lblTotaliMeTvsh.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblTotaliMeTvsh.setBackground(new Color(255, 255, 0));
		lblTotaliMeTvsh.setOpaque(true);
		lblTotaliMeTvsh.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTotaliMeTvsh.setBounds(1160, 614, 107, 14);
		contentPane.add(lblTotaliMeTvsh);
		lblTotali.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblTotali.setBackground(new Color(255, 255, 0));
		lblTotali.setOpaque(true);
		lblTotali.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTotali.setBounds(1160, 682, 107, 14);
		contentPane.add(lblTotali);
		lblTotaliIZbritur.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		lblTotaliIZbritur.setBackground(new Color(255, 255, 0));
		lblTotaliIZbritur.setOpaque(true);
		lblTotaliIZbritur.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblTotaliIZbritur.setBounds(1160, 649, 107, 14);
		contentPane.add(lblTotaliIZbritur);
		
		lblTotaliPaTVSH.setOpaque(true);
		lblTotaliPaTVSH.setBackground(Color.GREEN);
		lblTotaliPaTVSH.setBounds(1287, 550, 65, 14);
		contentPane.add(lblTotaliPaTVSH);
		
		lblTotaliTVSHs.setOpaque(true);
		lblTotaliTVSHs.setBackground(Color.GREEN);
		lblTotaliTVSHs.setBounds(1287, 581, 65, 14);
		contentPane.add(lblTotaliTVSHs);
		
		lblTotaliMeTVSH.setOpaque(true);
		lblTotaliMeTVSH.setBackground(Color.GREEN);
		lblTotaliMeTVSH.setBounds(1287, 614, 65, 14);
		contentPane.add(lblTotaliMeTVSH);
		
		lblTotaliZbritur.setOpaque(true);
		lblTotaliZbritur.setBackground(Color.GREEN);
		lblTotaliZbritur.setBounds(1287, 649, 65, 14);
		contentPane.add(lblTotaliZbritur);
		
		lblTotali2.setOpaque(true);
		lblTotali2.setBackground(Color.GREEN);
		lblTotali2.setBounds(1287, 682, 65, 14);
		contentPane.add(lblTotali2);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(110);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(110);
		table.getColumnModel().getColumn(9).setPreferredWidth(110);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		table.getColumnModel().getColumn(11).setPreferredWidth(100);
		table.getColumnModel().getColumn(12).setPreferredWidth(50);
		table.getColumnModel().getColumn(13).setPreferredWidth(100);
		
		scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		
		
		btnKerkoProduktin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnKerkoProduktin.doClick();
				}
			}
		});
		btnKerkoProduktin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		

		NdryshoGjuhen();
		btnAnglisht.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha = "eng";
				NdryshoGjuhen();
			}
		});
		
		btnKerkoProduktin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					frmKerkoProduktin  ObjKerkoProduktin = new frmKerkoProduktin();
					ObjKerkoProduktin.setVisible(true);
				}
				catch (Exception e1)
				{
					System.out.println(e1);
				}
			}
		});
		btnKerkoProduktin.setBounds(341, 219, 142, 46);
		contentPane.add(btnKerkoProduktin);
		btnShqip.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShqip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShqip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShqip.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/alb.png")));
		btnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuhesia.gjuha = "alb";
				NdryshoGjuhen();
			}
		});
		btnShqip.setBounds(1292, 209, 25, 25);
		
		contentPane.add(btnShqip);
		
		JButton btnKtheProduktin = new JButton("Kthe produktin");
		btnKtheProduktin.setBackground(Color.WHITE);
		btnKtheProduktin.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/KtheProduktin.png")));
		btnKtheProduktin.setBounds(1201, 327, 151, 37);
		contentPane.add(btnKtheProduktin);
		lblBackgroundShitja.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		
		lblBackgroundShitja.setIcon(new ImageIcon(frmShitja.class.getResource("/imgs/backgroundFrmShitja.png")));
		lblBackgroundShitja.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBackgroundShitja.setBorder(new CompoundBorder());
		lblBackgroundShitja.setBounds(0, 21, 1372, 1023);
		contentPane.add(lblBackgroundShitja);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1362, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("File");
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(menu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		mntmExit.setActionCommand("Exit");
		menu.add(mntmExit);
		
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBarkodi, txtSasia, btnShto, rdbtnKlientIZakonshem, rdbtnKlientIRregullt, txtID_klientit, btnPerfundoBlerjen}));
	}
	private void NdryshoGjuhen()
		{
		if(Gjuhesia.gjuha=="eng")
		{
			table.getColumnModel().getColumn(0).setHeaderValue("No.");
			table.getColumnModel().getColumn(1).setHeaderValue("Product and brand");
			table.getColumnModel().getColumn(2).setHeaderValue("Gender");
			table.getColumnModel().getColumn(3).setHeaderValue("Size");
			table.getColumnModel().getColumn(4).setHeaderValue("Measuring unit");
			table.getColumnModel().getColumn(5).setHeaderValue("Amount");
			table.getColumnModel().getColumn(6).setHeaderValue("Price without VAT");
			table.getColumnModel().getColumn(7).setHeaderValue("VAT");
			table.getColumnModel().getColumn(8).setHeaderValue("Price with VAT");
			table.getColumnModel().getColumn(9).setHeaderValue("Total without VAT");
			table.getColumnModel().getColumn(10).setHeaderValue("Total of VAT");
			table.getColumnModel().getColumn(11).setHeaderValue("Total with VAT");
			table.getColumnModel().getColumn(12).setHeaderValue("Disc.");
			table.getColumnModel().getColumn(13).setHeaderValue("Disc. amount");
			table.getColumnModel().getColumn(14).setHeaderValue("Total");
			
			scrollPane.setViewportView(table);
			
			try 
			{ 
			    imgShto1 = ImageIO.read(frmShitja.class.getResource("/imgs/ADD1.jpg"));
			    imgShto2 = ImageIO.read(frmShitja.class.getResource("/imgs/ADD2.jpg"));
			    imgShto3 = ImageIO.read(frmShitja.class.getResource("/imgs/ADD3.jpg"));
			    
			    imgKerkoProduktin1 = ImageIO.read(frmShitja.class.getResource("/imgs/searchProduct1.png"));
				imgKerkoProduktin2 = ImageIO.read(frmShitja.class.getResource("/imgs/searchProduct2.png"));
				imgKerkoProduktin3 = ImageIO.read(frmShitja.class.getResource("/imgs/searchProduct3.png"));
			   
				imgFshijArtikullin1 = ImageIO.read(frmShitja.class.getResource("/imgs/Delete1.jpg"));
				imgFshijArtikullin2 = ImageIO.read(frmShitja.class.getResource("/imgs/Delete2.jpg"));
				imgFshijArtikullin3 = ImageIO.read(frmShitja.class.getResource("/imgs/Delete3.jpg"));
				
				imgPerfundoBlerjen = ImageIO.read(frmShitja.class.getResource("/imgs/paguaj.png"));
			}
			catch (Exception ex)
			{
			    JOptionPane.showMessageDialog(null,ex.getMessage());
			}
			
			lblBarkodi.setText("Barcode");
			lblSasia.setText("Amount");
			rdbtnKlientIZakonshem.setText("Ordinary costumer");
			btnPerfundoBlerjen.setText("Complete the sale");
			rdbtnKlientIRregullt.setText("Regular costumer");
			lblIdEKlientit.setText("Client's ID:");
			
			lblTotaliPaTVSH1.setText("Total without ATV");
			lblTotaliITvshs.setText("Total of ATV");
			lblTotaliMeTvsh.setText("Total with ATV");
			lblTotaliIZbritur.setText("Total discount");
			lblTotali.setText("Total");
		}
		else
		{
			table.getColumnModel().getColumn(0).setHeaderValue("Nr.");
			table.getColumnModel().getColumn(1).setHeaderValue("Produkti dhe brendi");
			table.getColumnModel().getColumn(2).setHeaderValue("Gjinia");
			table.getColumnModel().getColumn(3).setHeaderValue("Madhesia");
			table.getColumnModel().getColumn(4).setHeaderValue("Njesia matese");
			table.getColumnModel().getColumn(5).setHeaderValue("Sasia");
			table.getColumnModel().getColumn(6).setHeaderValue("Cmimi pa TVSH");
			table.getColumnModel().getColumn(7).setHeaderValue("TVSH");
			table.getColumnModel().getColumn(8).setHeaderValue("Cmimi me TVSH");
			table.getColumnModel().getColumn(9).setHeaderValue("Totali pa TVSH");
			table.getColumnModel().getColumn(10).setHeaderValue("Totali i TVSH-s");
			table.getColumnModel().getColumn(11).setHeaderValue("Totali me TVSH");
			table.getColumnModel().getColumn(12).setHeaderValue("Zbrit");
			table.getColumnModel().getColumn(13).setHeaderValue("Shuma e zbritur");
			table.getColumnModel().getColumn(14).setHeaderValue("Totali");
			
			scrollPane.setViewportView(table);
			
			try 
			{
				imgShto1 = ImageIO.read(frmShitja.class.getResource("/imgs/SHTO1.jpg"));
				imgShto2 = ImageIO.read(frmShitja.class.getResource("/imgs/SHTO2.jpg"));
				imgShto3 = ImageIO.read(frmShitja.class.getResource("/imgs/SHTO3.jpg"));
				
				imgKerkoProduktin1 = ImageIO.read(frmShitja.class.getResource("/imgs/kerkoProduktin1.png"));
				imgKerkoProduktin2 = ImageIO.read(frmShitja.class.getResource("/imgs/kerkoProduktin2.png"));
				imgKerkoProduktin3 = ImageIO.read(frmShitja.class.getResource("/imgs/kerkoProduktin3.png"));
				
				imgFshijArtikullin1 = ImageIO.read(frmShitja.class.getResource("/imgs/Fshij1.jpg"));
				imgFshijArtikullin2 = ImageIO.read(frmShitja.class.getResource("/imgs/Fshij2.jpg"));
				imgFshijArtikullin3 = ImageIO.read(frmShitja.class.getResource("/imgs/Fshij3.jpg"));
				
				imgPerfundoBlerjen = ImageIO.read(frmShitja.class.getResource("/imgs/paguaj.png"));
			}
			catch (Exception ex)
			{
			    JOptionPane.showMessageDialog(null,ex.getMessage());
			}
			lblBarkodi.setText("Barkodi");
			lblSasia.setText("Sasia");
			rdbtnKlientIZakonshem.setText("Klient i zakonshem");
			btnPerfundoBlerjen.setText("Perfundo blerjen");
			rdbtnKlientIRregullt.setText("Klient i rregullt");
			lblIdEKlientit.setText("ID e klientit:");
			
			lblTotaliPaTVSH1.setText("Totali pa TVSH");
			lblTotaliITvshs.setText("Totali i TVSH-s");
			lblTotaliMeTvsh.setText("Total me TVSH");
			lblTotaliIZbritur.setText("Totali i zbritur");
			lblTotali.setText("Totali");
		}
		Image dimgShto1 = imgShto1.getScaledInstance(210, 55, Image.SCALE_SMOOTH);
		ImageIcon imageIconShto1 = new ImageIcon(dimgShto1);
		
		Image dimgShto2 = imgShto2.getScaledInstance(210, 55, Image.SCALE_SMOOTH);
		ImageIcon imageIconShto2 = new ImageIcon(dimgShto2);
		
		Image dimgShto3 = imgShto3.getScaledInstance(210, 55, Image.SCALE_SMOOTH);
		ImageIcon imageIconShto3 = new ImageIcon(dimgShto3);
		
		btnShto.setIcon(imageIconShto1);
		btnShto.setSelectedIcon(imageIconShto1);
		
		btnShto.setRolloverIcon(imageIconShto2);
		btnShto.setPressedIcon(imageIconShto3);

		
		//Kerko
		Image dimgKerkoProduktin1 = imgKerkoProduktin1.getScaledInstance(150, 55, Image.SCALE_SMOOTH);
		ImageIcon iamgeKerkoProduktin1 = new ImageIcon(dimgKerkoProduktin1);
		
		Image dimgKerkoProduktin2 = imgKerkoProduktin2.getScaledInstance(150, 55, Image.SCALE_SMOOTH);
		ImageIcon iamgeKerkoProduktin2 = new ImageIcon(dimgKerkoProduktin2);
		
		Image dimgKerkoProduktin3 = imgKerkoProduktin3.getScaledInstance(150, 55, Image.SCALE_SMOOTH);
		ImageIcon iamgeKerkoProduktin3 = new ImageIcon(dimgKerkoProduktin3);
		
		btnKerkoProduktin.setIcon(iamgeKerkoProduktin1);
		btnKerkoProduktin.setSelectedIcon(iamgeKerkoProduktin1);
		
		btnKerkoProduktin.setRolloverIcon(iamgeKerkoProduktin2);
		btnKerkoProduktin.setPressedIcon(iamgeKerkoProduktin3);
		
		//Fshij
		Image dimgFshijArtikullin1 = imgFshijArtikullin1.getScaledInstance(199, 55, Image.SCALE_SMOOTH);
		ImageIcon iamgeFshijArtikullin1 = new ImageIcon(dimgFshijArtikullin1);
		
		Image dimgFshijArtikullin2 = imgFshijArtikullin2.getScaledInstance(199, 55, Image.SCALE_SMOOTH);
		ImageIcon iamgeFshijArtikullin2 = new ImageIcon(dimgFshijArtikullin2);
		
		Image dimgFshijArtikullin3 = imgFshijArtikullin3.getScaledInstance(199, 55, Image.SCALE_SMOOTH);
		ImageIcon iamgeFshijArtikullin3 = new ImageIcon(dimgFshijArtikullin3);
		
		btnFshijArtikullin.setIcon(iamgeFshijArtikullin1);
		btnFshijArtikullin.setSelectedIcon(iamgeFshijArtikullin1);
		
		btnFshijArtikullin.setRolloverIcon(iamgeFshijArtikullin2);
		btnFshijArtikullin.setPressedIcon(iamgeFshijArtikullin3);
		
		//Perfundo
		Image dimgPerfundoBlerjen = imgPerfundoBlerjen.getScaledInstance(43, 43, Image.SCALE_SMOOTH);
		ImageIcon iamgePerfundoBlerjen = new ImageIcon(dimgPerfundoBlerjen);
		
		btnPerfundoBlerjen.setIcon(iamgePerfundoBlerjen);
		btnKerkoProduktin.setBorder(UIManager.getBorder("Emptyborder.border"));
		btnFshijArtikullin.setBorder(UIManager.getBorder("Emptyborder.border"));
		btnShto.setBorder(UIManager.getBorder("Emptyborder.border"));
	}
}