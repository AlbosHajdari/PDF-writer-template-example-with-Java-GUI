import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainJavaClass
  extends JFrame
{
  JLabel imageLabel = new JLabel("");
  JLabel pathLabel = new JLabel("");
  private JPanel contentPane;
  private final JLabel lblFirstName = new JLabel("First name:");
  private final JLabel lblLastName = new JLabel("Last name:");
  private JTextField txtFirstName;
  private JTextField txtLastName;
  JButton btnSavePDF = new JButton("Save PDF");
  JLabel lblImageError = new JLabel("");
  JButton btnChoose = new JButton("Choose image");
  private final JLabel lblPath2 = new JLabel("Path:");
  private final JLabel lblFirstNameError = new JLabel("");
  private final JLabel lblLastNameError = new JLabel("");
  
  public static void main(String[] args)
    throws DocumentException, MalformedURLException, IOException
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          
          MainJavaClass frame = new MainJavaClass();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  private void connectToDragDrop()
  {
    DragListener d = new DragListener(this.imageLabel, this.pathLabel, this.lblImageError);
    
    new DropTarget(this, d);
  }
  
  public MainJavaClass()
  {
    this.contentPane = new JPanel();
    
    connectToDragDrop();
    
    setDefaultCloseOperation(3);
    setBounds(100, 100, 450, 450);
    
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.imageLabel.setOpaque(true);
    this.imageLabel.setBackground(Color.WHITE);
    
    this.imageLabel.setBounds(10, 61, 222, 136);
    this.contentPane.add(this.imageLabel);
    
    this.pathLabel.setBounds(58, 270, 366, 14);
    this.contentPane.add(this.pathLabel);
    this.lblFirstName.setBounds(10, 11, 64, 14);
    
    this.contentPane.add(this.lblFirstName);
    this.lblLastName.setBounds(10, 36, 82, 14);
    
    this.contentPane.add(this.lblLastName);
    
    this.txtFirstName = new JTextField();
    this.txtFirstName.setBounds(101, 5, 131, 20);
    this.contentPane.add(this.txtFirstName);
    this.txtFirstName.setColumns(10);
    
    this.txtLastName = new JTextField();
    this.txtLastName.setBounds(102, 33, 130, 20);
    this.contentPane.add(this.txtLastName);
    this.txtLastName.setColumns(10);
    
    this.btnSavePDF.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Boolean Permission = Boolean.valueOf(true);
        if (MainJavaClass.this.txtFirstName.getText().equals(""))
        {
          MainJavaClass.this.lblFirstNameError.setText("Please fill this field.");
          Permission = Boolean.valueOf(false);
        }
        else
        {
          MainJavaClass.this.lblFirstNameError.setText("");
        }
        if (MainJavaClass.this.txtLastName.getText().equals(""))
        {
          MainJavaClass.this.lblLastNameError.setText("Please fill this field.");
          Permission = Boolean.valueOf(false);
        }
        else
        {
          MainJavaClass.this.lblLastNameError.setText("");
        }
        if (MainJavaClass.this.pathLabel.getText().equals(""))
        {
          MainJavaClass.this.lblImageError.setText("Please attach an image.");
          Permission = Boolean.valueOf(false);
        }
        else
        {
          MainJavaClass.this.lblImageError.setText("");
        }
        if (Permission.booleanValue())
        {
          JFileChooser saveChooser = new JFileChooser();
          saveChooser.setCurrentDirectory(new File("."));
          
          FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("Pdf file(.pdf)", new String[] { "pdf" });
          saveChooser.setFileFilter(pdfFilter);
          saveChooser.setAcceptAllFileFilterUsed(false);
          
          int chooserResult = saveChooser.showSaveDialog(null);
          if (chooserResult == 0)
          {
            String Folder = saveChooser.getCurrentDirectory().toString();
            String FileAndFolder = saveChooser.getSelectedFile().toString();
            System.out.println("getSelectedFolder() : " + Folder);
            System.out.println("getSelectedFile() : " + FileAndFolder);
            String[] pdfFormat = FileAndFolder.split(Pattern.quote("."));
            if (!pdfFormat[(pdfFormat.length - 1)].equals("pdf")) {
              FileAndFolder = FileAndFolder + ".pdf";
            }
            File fileName = new File(FileAndFolder);
            System.out.println(fileName);
            if (fileName.exists())
            {
              System.out.println("Ekziston");
              String[] JustTheFileName = FileAndFolder.split(Pattern.quote("\\"));
              
              int dialogButton = 0;
              int dialogResult = JOptionPane.showConfirmDialog(null, JustTheFileName[(JustTheFileName.length - 1)] + 
                " already exists\r\nDo you want to replace it?", "Confirm Save As", dialogButton, 2);
              if (dialogResult == 0)
              {
                System.out.println("POO");
                MainJavaClass.this.WriteOnPDF(FileAndFolder);
              }
              else
              {
                System.out.println("JOO");
              }
            }
            else
            {
              System.out.println("NUK ekziston");
              MainJavaClass.this.WriteOnPDF(FileAndFolder);
            }
          }
        }
      }
    });
    this.btnSavePDF.setBounds(83, 377, 89, 23);
    this.contentPane.add(this.btnSavePDF);
    
    this.btnChoose.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        JFileChooser imageChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        imageChooser.setFileFilter(imageFilter);
        imageChooser.showOpenDialog(null);
        File f = imageChooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        
        String[] format = filename.split(Pattern.quote("."));
        if ((format[(format.length - 1)].equals("png")) || (format[(format.length - 1)].equals("gif")) || (format[(format.length - 1)].equals("jpg")) || 
          (format[(format.length - 1)].equals("jpeg")) || (format[(format.length - 1)].equals("jpe")) || (format[(format.length - 1)].equals("jfif")) || 
          (format[(format.length - 1)].equals("bmp")) || (format[(format.length - 1)].equals("dib")))
        {
          MainJavaClass.this.lblImageError.setText("");
          BufferedImage img2 = null;
          try
          {
            img2 = ImageIO.read(f);
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
          ImageIcon icon = new ImageIcon(img2);
          MainJavaClass.this.imageLabel.setIcon(icon);
          MainJavaClass.this.pathLabel.setText(filename);
        }
        else
        {
          MainJavaClass.this.lblImageError.setText("Please choose an image file.");
          MainJavaClass.this.imageLabel.setIcon(null);
          MainJavaClass.this.pathLabel.setText("");
        }
      }
    });
    this.btnChoose.setBounds(113, 208, 119, 23);
    this.contentPane.add(this.btnChoose);
    this.lblImageError.setForeground(Color.RED);
    
    this.lblImageError.setBounds(10, 245, 222, 14);
    this.contentPane.add(this.lblImageError);
    this.lblPath2.setBounds(10, 270, 46, 14);
    
    this.contentPane.add(this.lblPath2);
    this.lblFirstNameError.setForeground(Color.RED);
    this.lblFirstNameError.setBounds(254, 8, 124, 14);
    
    this.contentPane.add(this.lblFirstNameError);
    this.lblLastNameError.setForeground(Color.RED);
    this.lblLastNameError.setBounds(254, 36, 124, 14);
    
    this.contentPane.add(this.lblLastNameError);
  }
  
  private void WriteOnPDF(String FileAndFolder)
  {
    String FirstName = "First name: " + this.txtFirstName.getText();
    String LastName = "Last name: " + this.txtLastName.getText();
    try
    {
      Document doc = new Document();
      
      PdfWriter.getInstance(doc, new FileOutputStream(FileAndFolder));
      
      doc.open();
      Font font = FontFactory.getFont("Courier", 12.0F, BaseColor.BLACK);
      Paragraph pr1 = new Paragraph(FirstName, font);
      Paragraph pr2 = new Paragraph(LastName, font);
      doc.add(pr1);
      doc.add(pr2);
      try
      {
        Image img = Image.getInstance(this.pathLabel.getText());
        
        img.setAbsolutePosition(10.0F, 500.0F);
        doc.add(img);
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      doc.close();
      System.out.println("Perfundoi");
    }
    catch (FileNotFoundException|DocumentException e)
    {
      e.printStackTrace();
    }
  }
}
