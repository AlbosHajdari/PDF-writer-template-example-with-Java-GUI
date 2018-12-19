
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.*;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Choice;

import javax.accessibility.AccessibleContext;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
public class frmRaportet extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					 
					frmRaportet frame = new frmRaportet();
					frame.setVisible(true);
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	public static void ConnectionToMySql( String dataSot2, double Fitimi){
		try {
			java.util.Date data = new java.util.Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String DataSot = String.valueOf(dateFormat.format(data));
			Connection conn = connectionClass.connectDb();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO tblRaportet(dataRaportit,vleraTotale) values(?,?)");
			pst.setString(1, DataSot);
			pst.setDouble(2, Fitimi);
			pst.executeUpdate();
			pst.close();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
		
	}
	

	/**
	 * Create the frame.
	 */
	public frmRaportet() {
		setTitle("Albi Mall - Raportet\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Lenovo\\eclipse-workspace\\KNK\\src\\ProjektiKNK\\bababosss.jpg"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				txtSearch.requestFocus();
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1102, 759);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblRaportet = new JLabel("Raportet");
		lblRaportet.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaportet.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblRaportet.setBounds(443, 32, 198, 40);
		contentPane.add(lblRaportet);
		
		JButton btnShikoRaportet = new JButton("Shiko Raportet");
		btnShikoRaportet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					Connection conn = connectionClass.connectDb();
					String query = "";
					
					
					if(txtSearch.getText().equalsIgnoreCase("Janar") || txtSearch.getText().equalsIgnoreCase("Muaji=1")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '1' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Shkurt") || txtSearch.getText().equalsIgnoreCase("Muaji=2")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '2' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Mars")  || txtSearch.getText().equalsIgnoreCase("Muaji=3")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '3' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Prill") || txtSearch.getText().equalsIgnoreCase("Muaji=4")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '4' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Maj") || txtSearch.getText().equalsIgnoreCase("Muaji=5")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '5' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Qershor") || txtSearch.getText().equalsIgnoreCase("Muaji=6")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '6' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Korrik") || txtSearch.getText().equalsIgnoreCase("Muaji=7")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '7' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Gusht") || txtSearch.getText().equalsIgnoreCase("Muaji=8")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '8' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Shtator") || txtSearch.getText().equalsIgnoreCase("Muaji=9")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '9' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Tetor") || txtSearch.getText().equalsIgnoreCase("Muaji=10")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '10' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Nentor") || txtSearch.getText().equalsIgnoreCase("Muaji=11")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '11' AND R.id = ms.id;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Dhjetor") || txtSearch.getText().equalsIgnoreCase("Muaji=12")) {
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"FROM tblRaportet R, MalliShitur ms\r\n" + 
								"WHERE MONTH(R.dataRaportit) = '12' AND R.id = ms.id;";
					}
					
					
					else if(txtSearch.getText().equalsIgnoreCase("Fitimi Total")) {
						query = "SELECT SUM(vleraTotale) as FitimiTotal\r\n" + 
								"FROM tblRaportet";
						}
					else if(txtSearch.getText().equalsIgnoreCase("Fitimi Mujor")) {
						query = "SELECT SUM(vleraTotale) as Fitimi_Mujor, MONTH(dataRaportit) as Muaji, YEAR(dataRaportit) as Viti\r\n" + 
								"FROM tblRaportet\r\n" + 
								"GROUP BY MUAJI, VITI;";
						}
					else if(txtSearch.getText().equalsIgnoreCase("Krahasimi Mujor")) {
						query = "SELECT vleraTotale, YEAR(dataRaportit) as Viti, MONTH(dataRaportit) as Muaji\r\n" + 
								"FROM tblRaportet\r\n" + 
								"GROUP BY Muaji, Viti";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Krahasimi Vjetor")) {
						query = "SELECT SUM(vleraTotale) as Fitimi, YEAR(dataRaportit) as Viti\r\n" + 
								"FROM tblRaportet\r\n" + 
								"GROUP BY Viti\r\n" + 
								"ORDER BY FITIMI DESC;";
					}
					else if(txtSearch.getText().equalsIgnoreCase("Krahasimi Ditor")) {
						query = "SELECT * FROM tblRaportet";
					}
					else if(txtSearch.getText().equals("")){
						query = "select R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(r.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi \r\n" + 
								"from tblRaportet R, malliShitur ms\r\n" + 
								"WHERE R.dataRaportit= ms.dtShitjes;";
					}
					else if(txtSearch.getText().matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
		
						query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(r.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
								"from tblRaportet R, malliShitur ms\r\n" + 
								"WHERE dataRaportit = '" + txtSearch.getText() +"' AND ms.id = R.id;";
					}
					else if(txtSearch.getText().matches("^\\d{4}$")) {
						query = "SELECT r.id as Nr, YEAR(R.dataRaportit) as Viti,MONTH(R.dataRaportit) as Muaji, R.vleraTotale as Fitimi, ms.cmimiShitjes as Shitja\r\n" + 
								"FROM tblRaportet R, malliShitur ms \r\n" + 
								"Where YEAR(R.dataRaportit) = '" + txtSearch.getText() + "' AND R.id = ms.id";
					}
				
					else {
						JOptionPane.showMessageDialog(null,"Zgjedhje invalide, ju lutem te shkruani kerkesen korrekt");
						query = "SELECT * FROM tblRaportet";
					}
					
	
					
					Statement stmt = conn.createStatement();
					ResultSet rs  = stmt.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					
					
					
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Raporti eshte insertuar njehere " + ex.getMessage() );
				}
				
			}
		});
		btnShikoRaportet.setBounds(943, 87, 129, 23);
		contentPane.add(btnShikoRaportet);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(285, 123, 787, 540);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Connection conn = connectionClass.connectDb();
						String query = "";
						
						
						if(txtSearch.getText().equalsIgnoreCase("Janar") || txtSearch.getText().equalsIgnoreCase("Muaji=1")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '1' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Shkurt") || txtSearch.getText().equalsIgnoreCase("Muaji=2")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '2' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Mars")  || txtSearch.getText().equalsIgnoreCase("Muaji=3")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '3' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Prill") || txtSearch.getText().equalsIgnoreCase("Muaji=4")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '4' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Maj") || txtSearch.getText().equalsIgnoreCase("Muaji=5")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '5' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Qershor") || txtSearch.getText().equalsIgnoreCase("Muaji=6")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '6' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Korrik") || txtSearch.getText().equalsIgnoreCase("Muaji=7")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '7' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Gusht") || txtSearch.getText().equalsIgnoreCase("Muaji=8")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '8' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Shtator") || txtSearch.getText().equalsIgnoreCase("Muaji=9")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '9' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Tetor") || txtSearch.getText().equalsIgnoreCase("Muaji=10")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '10' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Nentor") || txtSearch.getText().equalsIgnoreCase("Muaji=11")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '11' AND R.id = ms.id;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Dhjetor") || txtSearch.getText().equalsIgnoreCase("Muaji=12")) {
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(R.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"FROM tblRaportet R, MalliShitur ms\r\n" + 
									"WHERE MONTH(R.dataRaportit) = '12' AND R.id = ms.id;";
						}
						
						
						else if(txtSearch.getText().equalsIgnoreCase("Fitimi Total")) {
							query = "SELECT SUM(vleraTotale) as FitimiTotal\r\n" + 
									"FROM tblRaportet";
							}
						else if(txtSearch.getText().equalsIgnoreCase("Fitimi Mujor")) {
							query = "SELECT SUM(vleraTotale) as Fitimi_Mujor, MONTH(dataRaportit) as Muaji, YEAR(dataRaportit) as Viti\r\n" + 
									"FROM tblRaportet\r\n" + 
									"GROUP BY MUAJI, VITI;";
							}
						else if(txtSearch.getText().equalsIgnoreCase("Krahasimi Mujor")) {
							query = "SELECT vleraTotale, YEAR(dataRaportit) as Viti, MONTH(dataRaportit) as Muaji\r\n" + 
									"FROM tblRaportet\r\n" + 
									"GROUP BY Muaji, Viti";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Krahasimi Vjetor")) {
							query = "SELECT SUM(vleraTotale) as Fitimi, YEAR(dataRaportit) as Viti\r\n" + 
									"FROM tblRaportet\r\n" + 
									"GROUP BY Viti\r\n" + 
									"ORDER BY FITIMI DESC;";
						}
						else if(txtSearch.getText().equalsIgnoreCase("Krahasimi Ditor")) {
							query = "SELECT * FROM tblRaportet";
						}
						else if(txtSearch.getText().equals("")){
							query = "select R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(r.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi \r\n" + 
									"from tblRaportet R, malliShitur ms\r\n" + 
									"WHERE R.id = ms.ID;";
						}
						else if(txtSearch.getText().matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
							
							query = "SELECT R.id as Nr, MONTH(R.dataRaportit) as Muaji, YEAR(r.dataRaportit) as Viti, ms.cmimiShitjes as Shitja, R.vleraTotale as Fitimi\r\n" + 
									"from tblRaportet R, malliShitur ms\r\n" + 
									"WHERE dataRaportit = '" + txtSearch.getText() +"' AND ms.id = R.id;";
						}
						else if(txtSearch.getText().matches("^\\d{4}$")) {
							query = "SELECT r.id as Nr, YEAR(R.dataRaportit) as Viti,MONTH(R.dataRaportit) as Muaji, R.vleraTotale as Fitimi, ms.cmimiShitjes as Shitja\r\n" + 
									"FROM tblRaportet R, malliShitur ms \r\n" + 
									"Where YEAR(R.dataRaportit) = '" + txtSearch.getText() + "' AND R.id = ms.id";
						}
					
						else {
							JOptionPane.showMessageDialog(null,"Zgjedhje invalide, ju lutem te shkruani kerkesen korrekt");
							query = "SELECT * FROM tblRaportet";
						}
						
		
						
						Statement stmt = conn.createStatement();
						ResultSet rs  = stmt.executeQuery(query);
						table.setModel(DbUtils.resultSetToTableModel(rs));
					
						
						
						
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Error " + ex.getMessage());
					}
				}
				
			}
		});
		txtSearch.setBounds(716, 88, 210, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JTextArea txtrGjithA = new JTextArea();
		txtrGjithA.setLineWrap(true);
		txtrGjithA.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtrGjithA.setEditable(false);
		txtrGjithA.setText("Data duhet te shkruhet ne formatin yyyy/dd/mm\r\nShkruaj 'Fitimi total' per te marre Fitimin Total\r\nShkruaj 'Fitimi mujor per te marre Fitimin mujor\r\nShkruaj 'Krahasimi Mujor' per te marre krahasuar fitimet pergjate muajve\r\nShkruaj 'Krahasimi Vjetor' per te Krahasuar fitimet pergjate viteve\r\nShkruaj 'Krahasimi Ditor' per te krahasuar fitimet pergjate diteve\r\nShkruaj emrin apo numrin e muajit per te marre te dhenat per ate muaj");
		txtrGjithA.setBounds(10, 203, 265, 258);
		contentPane.add(txtrGjithA);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1042, 21);
		contentPane.add(menuBar);
		
		JMenu mnRaportet = new JMenu("Raportet");
		menuBar.add(mnRaportet);
		
		JMenuItem mntmShikoRaportet = new JMenuItem("Shiko Raportet");
		mnRaportet.add(mntmShikoRaportet);
		
		JMenuItem mntmHelp = new JMenuItem("Help\r\n");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new File("C:\\Users\\Lenovo\\eclipse-workspace\\KNK\\bin\\Help.docx"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		mnRaportet.add(mntmHelp);
		
		JButton btnInserto = new JButton("Raporti nuk eshte insertuar");
		long totalMilliSeconds = System.currentTimeMillis();
		long totalSeconds = totalMilliSeconds / 1000;
		long totalMinutes = totalSeconds / 60;
		long totalHours = totalMinutes / 60;
		long currentHour = totalHours % 24;
		currentHour = currentHour + 2;
		//JOptionPane.showMessageDialog(null, currentHour);
		if(currentHour >= 20 && currentHour <= 23 ) {
		
		btnInserto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.util.Date data = new java.util.Date();
			

				
			
			
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String DataSot = String.valueOf(dateFormat.format(data));

				/* qito duhet me u marr prej formave tjera */
				double cmimiShitjes = 0;
				double cmimiBlerjes = 0;
				double Sasia=0;
			try {
					Connection conn = connectionClass.connectDb();
					String query = "SELECT * FROM malliShitur WHERE dtShitjes = '" + DataSot + "';";
					Statement stmt = conn.createStatement();
					ResultSet rs  = stmt.executeQuery(query);
						while(rs.next()) {
					cmimiShitjes = rs.getDouble("cmimiShitjes");
					cmimiBlerjes = rs.getDouble("cmimiBlerjes");
					Sasia  =  rs.getDouble("sasia");
										}
						double Fitimi = (cmimiShitjes - cmimiBlerjes) * Sasia;
						
						ConnectionToMySql(DataSot,Fitimi);
						btnInserto.setBackground(Color.GREEN);
						btnInserto.setText("Raporti eshte insertuar");
						btnInserto.setEnabled(false);
						btnInserto.setForeground(Color.BLACK);
						
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			}
		});
		}
		else {
			btnInserto.setEnabled(false);
		}
		
		btnInserto.setBackground(Color.RED);
		btnInserto.setForeground(Color.BLACK);
		btnInserto.setBounds(226, 676, 631, 23);
		contentPane.add(btnInserto);
	}
	}
	
	

