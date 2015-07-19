package prodev.GraphicsInterface;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import prodev.Main.Main;

public class BottomElement extends JPanel {

	private static final long serialVersionUID = 4010806667691565848L;
	public JLabel titleLabel;
	private String name;
	public ChartAnimation animation;
	
	public BottomElement(String name) {
		setLayout(null);
		try {
			this.name = Main.translator.getTranslatedPhrase(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.titleLabel.setFont(new Font("Arial", 1, 16));
		this.titleLabel.setForeground(new Color(200,200,200));
	}
	
}
