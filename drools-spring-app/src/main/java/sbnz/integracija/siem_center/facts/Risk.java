package sbnz.integracija.siem_center.facts;

import java.io.Serializable;

public class Risk implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String condition;
	private RiskType riskType;

	public Risk() {
		super();
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public RiskType getRiskType() {
		return riskType;
	}

	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}

	public Risk(String condition, RiskType riskType) {
		super();
		this.condition = condition;
		this.riskType = riskType;
	}

	@Override
	public String toString() {
		return "Risk [condition=" + condition + ", riskType=" + riskType + "]";
	}
	
	
}
