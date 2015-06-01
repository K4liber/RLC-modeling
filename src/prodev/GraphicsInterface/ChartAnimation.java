package prodev.GraphicsInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChartAnimation extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	public int sinusData[];
	public int timeBase[];
	public Thread trit;
	int last;
	public JKnob knobV;
	public JKnob knobT;
	public JLabel vDiv;
	public JLabel tDiv;
	public double counter;
	public double startAmplitude;
	public double frequency;
	
	public ChartAnimation() {
		
		setLayout(null);
		JPanel knobs = new JPanel();
		knobs.setLayout(null);
		knobV = new JKnob();
		knobV.setBounds(20,17,50,40);
		knobT = new JKnob();
		knobT.setBounds(100,17,50,40);
		vDiv = new JLabel();
		vDiv.setText(powerToString(knobV.power,knobV.value)+"V/div");
		vDiv.setBounds(15,28,55,60);
		tDiv = new JLabel();
		tDiv.setText(powerToString(knobT.power,knobT.value)+"s/div");
		tDiv.setBounds(95,28,55,60);
		trit = new Thread(this);
		sinusData = new int[150];
		timeBase = new int[150];
		knobs.add(vDiv);
		knobs.add(tDiv);
		knobs.add(knobV);
		knobs.add(knobT);
		for(int ii=0;ii<150;ii++){
			timeBase[ii] = ii;
		}
		knobs.setBounds(0,151,200,140);
		add(knobs);
	}
	
	public void run(){
		while(true){
			try {
				last = sinusData[0];
				for(int ii=0;ii<150;ii++){
					sinusData[ii] = sinResult(counter+ii);	
				}
				Thread.sleep(50);
				vDiv.setText(powerToString(knobV.power,knobV.value)+"V/div");
				tDiv.setText(powerToString(knobT.power,knobT.value)+"s/div");
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0,0, 150,150);
        for(int ii=0;ii<4;ii++){
        	g.drawLine(ii*38, 0, ii*38, 150);
        	g.drawLine(0, ii*38, 150, ii*38);
        }
        g.setColor(new Color(200,10,0));
        if(MainFrame.runAnimation)
        	counter+=10;
        if(trit.isAlive()){
	        for(int ii=0;ii<150;ii++)
	        	g.drawOval(timeBase[ii], 75+sinusData[ii], 1, 1);
        }
    }

	public int sinResult(double time){		
		return (int)(startAmplitude*37.5/knobV.amplitude*Math.sin(Math.toRadians((time/1000)*frequency*knobT.amplitude*2*Math.PI*375)));
	}
	
	public String powerToString(int power,int value){
		String s = "Wrong Data!";
		switch(power){
		case -7:
			s = "0,"+value+"u";
			break;
		case -6:
			s = value+"u";
			break;
		case -5:
			s = 10*value+"u";
			break;
		case -4:
			s = "0,"+value+"m";
			break;
		case -3:
			s = value+"m";
			break;
		case -2:
			s = 10*value+"m";
			break;
		case -1:
			s = "0,"+value;
			break;
		case 0:
			s = ""+value;
			break;
		case 1:
			s = ""+10*value;
			break;
		case 2:
			s = "0,"+value+"k";
			break;
		case 3:
			s = value+"k";
			break;
		}
		return s;
	}
	
	public void setStartAmplitude(double amp){
		startAmplitude = amp;
		knobV.amplitude = amp;
	}
	
	public void setStartFrequency(double f){
		frequency = f;
		knobT.amplitude = 1/frequency;
	}
}
