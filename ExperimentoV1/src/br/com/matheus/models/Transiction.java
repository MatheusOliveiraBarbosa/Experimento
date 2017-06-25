package br.com.matheus.models;

public class Transiction implements Elements {
	
	private String idXML;
	private String target;
	private String source;
	
	
	public Transiction(){}
	
	public Transiction(String idXml){
		this.idXML = idXml;
	}
	
	public Transiction(String idXml, String target, String source){
		this.idXML = idXml;
		this.target = target;
		this.source = source;
	}

	@Override
	public String getIdXML() {
		return idXML;
	}

	@Override
	public void setIdXML(String XML) {
		this.idXML=XML;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String toString() {
		return "Transiction id: " + idXML + " |-| Source: "  + source + " |-| Target: " + target ;
	}

}
