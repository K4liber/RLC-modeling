package prodev.GraphicsInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private JPanel mainPanel;
	
	public BottomElement(String name) {
		
		setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		
		try {
			this.name = Main.translator.getTranslatedPhrase(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setTitleLabel(new JLabel(this.name, SwingConstants.CENTER));
		titleLabel.setPreferredSize(new Dimension(380,30));
		setBorder(BorderFactory.createLineBorder(new Color(20,20,20)));
		animation = new ChartAnimation(name);
		
		mainPanel.add(titleLabel);
		mainPanel.add(animation);	
		
		add(mainPanel, BorderLayout.CENTER);
		
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
