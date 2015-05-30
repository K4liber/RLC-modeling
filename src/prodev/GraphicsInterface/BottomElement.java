package prodev.GraphicsInterface;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomElement extends JPanel {

	private static final long serialVersionUID = 4010806667691565848L;

	public JLabel titleLabel;
	public String name;
	public ChartAnimation animation;

	
	public BottomElement(String name, String path) {
		setLayout(null);
		this.name = name;
		titleLabel = new JLabel(this.name);
		titleLabel.setBounds(15,0,180,50);
		add(titleLabel);
		setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));
		animation = new ChartAnimation();
		animation.setBounds(20,45,155,220);
		add(animation);
		
	}	
}
