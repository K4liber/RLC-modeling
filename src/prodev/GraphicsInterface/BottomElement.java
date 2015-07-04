package prodev.GraphicsInterface;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BottomElement extends JPanel {

	private static final long serialVersionUID = 4010806667691565848L;
	private JLabel titleLabel;
	private String name;
	public ChartAnimation animation;
	
	public BottomElement(String name, String path) {
		setLayout(null);
		this.name = name;
		setTitleLabel(new JLabel(this.name, SwingConstants.CENTER));
		getTitleLabel().setBounds(0,0,200,50);
		add(getTitleLabel());
		setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));
		animation = new ChartAnimation(name);
		animation.setBounds(23,45,155,220);
		add(animation);	
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	
}
