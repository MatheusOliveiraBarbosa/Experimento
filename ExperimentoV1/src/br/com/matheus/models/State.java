package br.com.matheus.models;

public class State implements Elements {
	
	private String idXML;
	private String value;
	private String idParent;
	
	public State(){	}
	
	public State(String idXML){
		this.idXML=idXML;
	}
	
	public State(String idXML, String value, String idParent){
		this.idXML=idXML;
		this.value = value;
		this.idParent = idParent;
	}

	@Override
	public String getIdXML() {
		return idXML;
	}

	@Override
	public void setIdXML(String XML) {
		this.idXML=XML;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIdParent() {
		return idParent;
	}

	public void setIdParent(String idParent) {
		this.idParent = idParent;
	}
	
	@Override
	public String toString() {
		return "State id: " + idXML +" |-| State name: " + value + " |-| State ID Parent: " + idParent ;
	}

}
