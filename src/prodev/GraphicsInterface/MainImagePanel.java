package prodev.GraphicsInterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image1;
	private BufferedImage image2;
	private int ktoryRysowac = 0;
	
	public void rysuj(int ktory) {
		ktoryRysowac = ktory;
	}

	public MainImagePanel(String n1, String n2) {

    	URL resource = getClass().getResource("images/" + n1);
    	URL resource2 = getClass().getResource("images/" + n2);
    	try {
			image1 = ImageIO.read(resource);
			image2 = ImageIO.read(resource2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(ktoryRysowac == 0)
        	g.drawImage(image1, 0, 0, null);  
        else
        	g.drawImage(image2, 0, 0, null);
    }

}
