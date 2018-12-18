import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DragListener
  implements DropTargetListener
{
  public JLabel imageLabel = new JLabel();
  public JLabel pathLabel = new JLabel();
  public JLabel lblError = new JLabel();
  
  public DragListener(JLabel image, JLabel path, JLabel error)
  {
    this.imageLabel = image;
    this.pathLabel = path;
    this.lblError = error;
  }
  
  public void dragEnter(DropTargetDragEvent arg0) {}
  
  public void dragExit(DropTargetEvent arg0) {}
  
  public void dragOver(DropTargetDragEvent arg0) {}
  
  public void drop(DropTargetDropEvent ev)
  {
    int koordX = ev.getLocation().x;
    int koordY = ev.getLocation().y;
    if ((koordX > 17) && (koordX < 238) && (koordY > 89) && (koordY < 225))
    {
      ev.acceptDrop(1);
      
      Transferable t = ev.getTransferable();
      
      DataFlavor[] df = t.getTransferDataFlavors();
      DataFlavor[] arrayOfDataFlavor1;
      int j = (arrayOfDataFlavor1 = df).length;
      for (int i = 0; i < j; i++)
      {
        DataFlavor f = arrayOfDataFlavor1[i];
        try
        {
          if (f.isFlavorJavaFileListType())
          {
            List<File> files = (List)t.getTransferData(f);
            for (File file : files)
            {
              String[] format = file.getPath().split(Pattern.quote("."));
              if ((format[(format.length - 1)].equals("png")) || (format[(format.length - 1)].equals("gif")) || (format[(format.length - 1)].equals("jpg")) || 
                (format[(format.length - 1)].equals("jpeg")) || (format[(format.length - 1)].equals("jpe")) || (format[(format.length - 1)].equals("jfif")) || 
                (format[(format.length - 1)].equals("bmp")) || (format[(format.length - 1)].equals("dib")))
              {
                this.lblError.setText("");
                DisplayImage(file.getPath());
              }
              else
              {
                this.lblError.setText("Please choose an image file.");
                this.imageLabel.setIcon(null);
                this.pathLabel.setText("");
              }
            }
          }
        }
        catch (Exception ex)
        {
          JOptionPane.showMessageDialog(null, "1. Gabimi eshte ky:" + ex);
        }
      }
    }
  }
  
  public void dropActionChanged(DropTargetDragEvent arg0) {}
  
  private void DisplayImage(String path)
  {
    BufferedImage img = null;
    try
    {
      File f = new File(path);
      img = ImageIO.read(f);
      ImageIcon icon = new ImageIcon(img);
      this.imageLabel.setIcon(icon);
      this.pathLabel.setText(path);
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(null, "2. Gabimi eshte ky:" + ex);
    }
  }
}
