package images;
import java.awt.*;
import javax.swing.*;

public class Imagen extends JPanel{
    private Image fondo=null;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo,0,0,getWidth(),getHeight(),null);
    }
    public void setImage(String image){
        if (image!=null) {
            fondo=new ImageIcon(image).getImage();
        }
    }
    
}