package com.javacalc;

import javax.inject.Named;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped

public class Calculator {

	private String months;
	private String capital;
	private String interest;
	private double result;

	@ManagedProperty("#{textCalcErr}")
	private ResourceBundle textCalcErr;

	@ManagedProperty("#{textCalc}")
	private ResourceBundle textCalc;

	@PostConstruct
	public void postConstruct() {
		// loading resource (notice the full "file" name)
		FacesContext context = FacesContext.getCurrentInstance();
		textCalc = ResourceBundle.getBundle("resources.textCalc", context.getViewRoot().getLocale());
		textCalcErr = ResourceBundle.getBundle("resources.textCalcErr", context.getViewRoot().getLocale());
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public double getResult() {
		return result;
	}

	public String count() {
		
		double capital = Double.parseDouble(this.capital);
		double interest = Double.parseDouble(this.interest);
		double months = Double.parseDouble(this.months);
		
		FacesContext ctx = FacesContext.getCurrentInstance();

		result = ((capital + ((capital * interest) / 100)) / months);
		double roundedResult = Math.round(result*100.0)/100.0;

		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, textCalcErr.getString("calcComputationOkInfo"), null));
		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, textCalc.getString("result") + ": " + roundedResult, null));
		
		return "";
	}
}