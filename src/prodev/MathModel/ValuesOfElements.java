package prodev.MathModel;

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
			setSchemeName();
			for(int ii=0;ii<4;ii++){
				if(listOfElements[ii]){
					Main.frame.bottomPanel.bottomElements.get(ii).animation.runForest(true);
					if(!Main.frame.bottomPanel.bottomElements.get(ii).animation.trit.isAlive()){
						Main.frame.bottomPanel.bottomElements.get(ii).animation.runForest(true);
						Main.frame.bottomPanel.bottomElements.get(ii).animation.trit.start();
						Main.frame.bottomPanel.bottomElements.get(ii).animation.setStartFrequency(frequencyValue);
						Main.frame.bottomPanel.bottomElements.get(ii).animation.setStartAmplitude(amplitudeValue);
					}
				}
			}
		}
	}
	
	public void setSchemeName(){
		String name = schemeName;
		if(listOfElements[0])
			name = name + "R";
		if(listOfElements[1])
			name = name + "L";
		if(listOfElements[2])
			name = name + "C";
		if(listOfElements[3] && listOfElements[4])
			name = name + "G";
		
		schemeName = name;
		setAdditionalValues();
	}
	
	public void setAdditionalValues() {
		switch(schemeName){
			case "RLCG":{
				setCapacitorImpedance(1/(Math.PI*2*frequencyValue*capacitorValue));
				setCoilImpedance((Math.PI*2*frequencyValue*coilValue));
				System.out.println(coilImpedance);
				impedance = Math.sqrt(Math.pow(resistorValue, 2) + Math.pow(coilImpedance-capacitorImpedance, 2));
				phase = Math.acos(resistorValue/impedance);
				setImpedance(impedance);
				setPhase(phase);
				System.out.println(impedance);
				System.out.println(phase);
				break;
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
