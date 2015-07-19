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
	public String name;
	private boolean running = false;
	
	private double ampFactor;
	
	public ChartAnimation(String n) {
		
		name = n;
		setLayout(null);
		JPanel knobs = new JPanel();
		knobs.setLayout(null);
		knobV = new JKnob();
		knobV.setBounds(20,18,50,40);
		knobT = new JKnob();
		knobT.setBounds(100,18,50,40);
		vDiv = new JLabel();
		vDiv.setText(" ? V/div");
		vDiv.setBounds(15,28,55,60);
		tDiv = new JLabel();
		tDiv.setText(" ? s/div");
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
		knobs.setBounds(0,153,200,140);
		add(knobs);
	}
	
	public void run(){
		while(true){
			try {
				last = sinusData[0];
				for(int ii=0;ii<150;ii++){
					sinusData[ii] = mathResult(counter+ii);	
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
        for(int ii=0;ii<5;ii++){
        	g.setColor(new Color(200,200,200));
        	if(ii==2) g.setColor(new Color(100,100,100));
        	g.drawLine(ii*38, 0, ii*38, 150);
        	g.drawLine(0, ii*38, 150, ii*38);
        }
        g.setColor(new Color(200,10,0));
        if(MainFrame.runAnimation)
        	counter+=10;
        if(trit.isAlive() && running){
	        for(int ii=0;ii<150;ii++)
	        	g.drawOval(timeBase[ii], 75+sinusData[ii], 1, 1);
        }
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
		vDiv.setText(amp+"V/div");
	}
	
	public void setStartFrequency(double f){
		frequency = f;
		knobT.amplitude = 1/frequency;
	}
	
	public int mathResult(double time){
		int r = 0;
		setAmpFactor(startAmplitude*37.5/knobV.amplitude);
		switch(MainFrame.values.getSchemeName()){
			case "RLCG":{
				switch(name){
					case "Resistor":{
						r = (int)(ampFactor*MainFrame.values.getResistorValue()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Capacitor":{
						r = (int)(-ampFactor*MainFrame.values.getCapacitorImpedance()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,Math.PI/2)));
						break;
					}
					case "Coil":{
						r = (int)(ampFactor*MainFrame.values.getCoilImpedance()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,Math.PI/2)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,MainFrame.values.getPhase())));
						break;
					}
				}
				break;
			}
			case "LCG":{
				switch(name){
					case "Capacitor":{
						r = (int)(ampFactor*MainFrame.values.getCapacitorImpedance()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Coil":{
						r = (int)(-ampFactor*MainFrame.values.getCoilImpedance()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
				}
				break;
			}
			case "CG":{
				switch(name){
					case "Capacitor":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
				}
				break;
			}
			case "LG":{
				switch(name){
					case "Coil":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
				}
				break;
			}
			case "RCG":{
				switch(name){
					case "Capacitor":{
						r = (int)(ampFactor*MainFrame.values.getCapacitorImpedance()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,Math.PI/2)));
						break;
					}
					case "Resistor":{
						r = (int)(ampFactor*MainFrame.values.getResistorValue()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,MainFrame.values.getPhase())));
						break;
					}
				}
				break;
			}
			case "RG":{
				switch(name){
					case "Resistor":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,0)));
						break;
					}
				}
				break;
			}
			case "RLG":{
				switch(name){
					case "Resistor":{
						r = (int)(ampFactor*MainFrame.values.getResistorValue()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,0)));
						break;
					}
					case "Coil":{
						r = (int)(ampFactor*MainFrame.values.getCoilImpedance()/MainFrame.values.getImpedance()*Math.sin(getRadiansValue(time,Math.PI/2)));
						break;
					}
					case "Generator":{
						r = (int)(ampFactor*Math.sin(getRadiansValue(time,MainFrame.values.getPhase())));
						break;
					}
				}
				break;
			}
			case "LC":{
				switch(name){
					case "Capacitor":{
						r = (int)(ampFactor*Math.cos(getRadiansValue(time,0)));
						break;
					}
					case "Coil":{
						r = (int)(-ampFactor*Math.cos(getRadiansValue(time,0)));
						break;
					}
				}
				break;
			}
			case "RC":{
				switch(name){
					case "Capacitor":{
						r = (int)(-ampFactor*Math.pow(Math.E, -(time*0.375*frequency*knobT.amplitude)/MainFrame.values.getOmega()));
						break;
					}
					case "Resistor":{
						r = (int)(ampFactor*Math.pow(Math.E, -(time*0.375*frequency*knobT.amplitude)/MainFrame.values.getOmega()));
						break;
					}
				}
				break;
			}
			case "RLCA":{
				double betaBf = MainFrame.values.getBetaBf();
				double betaBs = MainFrame.values.getBetaBs();
				double e = betaBf/(betaBf-betaBs)*Math.pow(Math.E, -(time/1000*knobT.amplitude)*betaBs)+betaBs/(betaBf-betaBs)*Math.pow(Math.E, -(time/1000*knobT.amplitude)*betaBf);
				
				switch(name){
					case "Resistor":{
						r = (int)(-ampFactor*e);
						break;
					}
					case "Capacitor":{
						r = (int)(-ampFactor*e);
						break;
					}
					case "Coil":{
						r = (int)(ampFactor*MainFrame.values.getCoilValue()/MainFrame.values.getCapacitorValue()*e);
						break;
					}
				}
				break;
			}
			case "RLCD":{
				double e = Math.pow(Math.E, -(time/1000*knobT.amplitude)*MainFrame.values.getBeta());
				
				switch(name){
					case "Resistor":{
						r = (int)(ampFactor*MainFrame.values.getResistorValue()*MainFrame.values.getOmega()*MainFrame.values.getCapacitorValue()*Math.sin(getRadiansValue(time,0))*e);
						break;
					}
					case "Capacitor":{
						r = (int)(-ampFactor*Math.cos(getRadiansValue(time,0))*e);
						break;
					}
					case "Coil":{
						r = (int)(ampFactor*MainFrame.values.getCoilValue()/MainFrame.values.getCapacitorValue()*Math.cos(getRadiansValue(time,0))*e);
						break;
					}
				}
				break;
			}
		}
		return r;
	}
	public void runForest(boolean f){
		running = f;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public double getAmpFactor() {
		return ampFactor;
	}

	public void setAmpFactor(double ampFactor) {
		this.ampFactor = ampFactor;
	}
	
	public double getRadiansValue(double time,double phase){
		return Math.toRadians(((time/1000)*frequency*knobT.amplitude*2*Math.PI+phase)*375);
	}
}
