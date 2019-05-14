package org.himadri.practice.java_practice.design_pattern.chain_of_responsibility;
public interface DispenseChain {

	void setNextChain(DispenseChain nextChain);
	
	void dispense(Currency cur);
}