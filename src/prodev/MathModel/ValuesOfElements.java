package prodev.MathModel;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JOptionPane;

import prodev.GraphicsInterface.MainFrame;
import prodev.Main.Main;

public class ValuesOfElements {

	private double resistorValue;
	private double capacitorValue;
	private double coilValue;
	private double amplitudeValue;
	private double frequencyValue;
	
	private double capacitorImpedance;
	private double coilImpedance;
	private double impedance;
	private double phase;
	private double resistancesCC;
	private double beta;
	private double omega;
	private double betaBf;
	private double betaBs;
	private double omegaZero;
	private double initialCharge;
	String schemeName = "";
	
	
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
	
	void setFrequencyValue(double value){
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
		String message = "The system must contain a power source (generator or capacitor)";
		try {
			message = Main.translator.getTranslatedPhrase("The system must contain a power source (generator or capacitor)");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(validation == false){
			MainFrame.setRunState(false);
			JOptionPane.showMessageDialog(null, message);
		}
		else{
			setSchemeBasicData();
			for(int ii=0;ii<4;ii++){
				if(listOfElements[ii]){
					Main.frame.bottomPanel.bottomElements.get(ii).titleLabel.setForeground(new Color(1,1,1));
					Main.frame.bottomPanel.bottomElements.get(ii).animation.runForest(true);
					Main.frame.bottomPanel.bottomElements.get(ii).animation.setStartFrequency(frequencyValue);
					Main.frame.bottomPanel.bottomElements.get(ii).animation.setStartAmplitude(amplitudeValue);
					if(!Main.frame.bottomPanel.bottomElements.get(ii).animation.trit.isAlive()){
						Main.frame.bottomPanel.bottomElements.get(ii).animation.runForest(true);
						Main.frame.bottomPanel.bottomElements.get(ii).animation.trit.start();
					}
				}
			}
		}
	}
	
	public void setSchemeBasicData(){
		String name = schemeName;
		if(listOfElements[0])
			name = name + "R";
		if(listOfElements[1]){
			name = name + "L";
			setCoilImpedance((Math.PI*2*frequencyValue*coilValue));
		}
		if(listOfElements[2]){
			name = name + "C";
			setCapacitorImpedance(1/(Math.PI*2*frequencyValue*capacitorValue));
		}
		if(listOfElements[3] && listOfElements[4])
			name = name + "G";
		
		schemeName = name;
		setAdditionalValues();
	}
	
	public void setAdditionalValues() {
		switch(schemeName){
			case "RLCG":{
				setImpedance(Math.sqrt(Math.pow(resistorValue, 2) + Math.pow(coilImpedance-capacitorImpedance, 2)));
				setPhase(Math.acos(resistorValue/impedance));
				break;
			}
			case "LCG":{
				setImpedance(Math.abs(coilImpedance-capacitorImpedance));
				break;
			}
			case "RCG":{
				setImpedance(Math.sqrt(Math.pow(resistorValue, 2) + Math.pow(capacitorImpedance, 2)));
				setPhase(Math.acos(resistorValue/impedance));
				break;
			}
			case "RLG":{
				setImpedance(Math.sqrt(Math.pow(resistorValue, 2) + Math.pow(coilImpedance, 2)));
				setPhase(Math.acos(resistorValue/impedance));
				break;
			}
			case "LC":{
				setInitialCharge(0.000000001);
				setOmega(1/Math.sqrt(coilValue*capacitorValue));
				setFrequencyValue(omega/(2*Math.PI));
				setAmplitudeValue(initialCharge/capacitorValue);
				break;
			}
			case "RC":{
				setInitialCharge(0.000000001);
				setOmega(1/(resistorValue*capacitorValue));
				setFrequencyValue(omega/(2*Math.PI));
				setAmplitudeValue(initialCharge/capacitorValue);
				break;
			}
			case "RLC":{
				setInitialCharge(0.000000001);
				setBeta(resistorValue/(2*coilValue));
				setAmplitudeValue(initialCharge/capacitorValue);
				if(resistorValue < 2*Math.sqrt(coilValue/capacitorValue)){
					schemeName = schemeName + "D";
					setOmega(Math.sqrt((1/(coilValue*capacitorValue)-Math.pow(beta, 2))));
				}else{
					schemeName = schemeName + "A";
					setOmegaZero(Math.sqrt((1/(coilValue*capacitorValue))));
					setBetaBf(beta+Math.sqrt(Math.pow(beta, 2)-omegaZero));
					setBetaBs(beta-Math.sqrt(Math.pow(beta, 2)-omegaZero));
					setOmega(betaBs);
				}
				setFrequencyValue(omega/(2*Math.PI));
			}
		}
	}

	public String getSchemeName(){
		return schemeName;
	}

	public double getImpedance() {
		return impedance;
	}

	public void setImpedance(double impedance) {
		this.impedance = impedance;
	}

	public double getPhase() {
		return phase;
	}

	public void setPhase(double phase) {
		this.phase = phase;
	}

	public double getResistancesCC() {
		return resistancesCC;
	}

	public void setResistancesCC(double resistancesCC) {
		this.resistancesCC = resistancesCC;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getOmega() {
		return omega;
	}

	public void setOmega(double omega) {
		this.omega = omega;
	}

	public double getBetaBf() {
		return betaBf;
	}

	public void setBetaBf(double betaBf) {
		this.betaBf = betaBf;
	}

	public double getBetaBs() {
		return betaBs;
	}

	public void setBetaBs(double betaBs) {
		this.betaBs = betaBs;
	}

	public double getOmegaZero() {
		return omegaZero;
	}

	public void setOmegaZero(double omegaZero) {
		this.omegaZero = omegaZero;
	}

	public double getInitialCharge() {
		return initialCharge;
	}

	public void setInitialCharge(double initialCharge) {
		this.initialCharge = initialCharge;
	}

	public double getCapacitorImpedance() {
		return capacitorImpedance;
	}

	public void setCapacitorImpedance(double capacitorImpedance) {
		this.capacitorImpedance = capacitorImpedance;
	}

	public double getCoilImpedance() {
		return coilImpedance;
	}

	public void setCoilImpedance(double coilImpedance) {
		this.coilImpedance = coilImpedance;
	}
}
