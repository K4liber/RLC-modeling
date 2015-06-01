package prodev.MathModel;

import javax.swing.JOptionPane;
import prodev.GraphicsInterface.MainFrame;
import prodev.Main.Main;

public class ValuesOfElements {

	double resistorValue;
	double capacitorValue;
	double coilValue;
	double amplitudeValue;
	double frequencyValue;
	
	//Resistor,Coil,Capacitor,Generator states:
	boolean listOfElements[] = {false,false,false,false,false};
	
	public ValuesOfElements(boolean list[]) {
		for (int ii=0;ii<4;ii++) {
			listOfElements[ii] = list[ii];
		}
	}
	
	public ValuesOfElements() {}
	
	public void setElementValue(double value,int element){
		switch(element){
			case 0:
				resistorValue = value;
				break;
			case 1:
				coilValue = value;
				break;
			case 2:
				capacitorValue = value;
				break;
			case 3:
				amplitudeValue = value;
				break;
			case 4:
				frequencyValue = value;
				break;
		}
	}
	void setResistorValue(double value){
		resistorValue = value;
	}
	
	void setCapacitorValue(double value){
		capacitorValue = value;
	}
	
	void setCoilValue(double value){
		coilValue = value;
	}
	
	void setAmplitudeValue(double value){
		amplitudeValue = value;
	}
	
	void setRequencyValue(double value){
		frequencyValue = value;
	}
	
	public double getResistorValue(){
		return resistorValue;
	}
	
	public double getCapacitorValue(){
		return capacitorValue;
	}
	
	public double getCoilValue(){
		return coilValue;
	}
	
	public double getAmplitudeValue(){
		return amplitudeValue;
	}
	
	public double getFrequencyValue(){
		return frequencyValue;
	}
	
	public void setState(boolean state,int ii){
		listOfElements[ii] = state;
	}
	
	boolean[] getStates(){
		return listOfElements;
	}
	
	public void validationStates(){
		boolean validation = false;
		if(listOfElements[3] && listOfElements[4]){
			for(int ii=0;ii<3;ii++){
				if(listOfElements[ii]){
					validation = true;
					break;
				}
			}
		}
		else{
			if(listOfElements[2])
			{
				if(listOfElements[0] || listOfElements[1]){
					validation = true;
				}		
			}
		}
		MainFrame.elementsValidation = validation;
		
		if(validation == false){
			MainFrame.setRunState(false);
			JOptionPane.showMessageDialog(null, "Uk³ad musi czerpac energie z generatora lub kondesatora!");
		}
		else{
			
			for(int ii=0;ii<4;ii++){
				if(!Main.frame.bottomPanel.bottomElements.get(ii).animation.trit.isAlive()){
					Main.frame.bottomPanel.bottomElements.get(ii).animation.trit.start();
					Main.frame.bottomPanel.bottomElements.get(ii).animation.setStartFrequency(frequencyValue);
					Main.frame.bottomPanel.bottomElements.get(ii).animation.setStartAmplitude(amplitudeValue);
				}
			}
		}
	}
}
