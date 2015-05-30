package prodev.GraphicsInterface;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 2730631373587824211L;
	private BufferedImage image;
	public String imageName;
    public ImagePanel(String name) {
    	imageName = name;
    	URL resource = getClass().getResource("images/" + imageName);
    	try {
			image = ImageIO.read(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(resource);
		}
    }
    
    public void reloadState(boolean state){
    	if(state && imageName.contains("OFF")){
    		String newName = imageName.replace("OFF","ON");
    		imageName = newName;
    	}
    	if(!state && imageName.contains("ON")){
    		String newName = imageName.replace("ON","OFF");
    		imageName = newName;
    	}
    	URL resource = getClass().getResource("images/" + imageName);
    	try {
			image = ImageIO.read(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);        
    }

}