package br.com.matheus.utils;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.matheus.models.Transiction;

public class ExtractInfoTransictions {
	
	private ArrayList<Transiction> transictions;
	
	public ExtractInfoTransictions(){
		transictions = new ArrayList<Transiction>();
	}
	
	public ArrayList<Transiction> extractTransaction(Node node) {

		Element element = (Element) node;
		if (element.hasAttribute("target") && element.hasAttribute("source")) {
			Transiction transiction = new Transiction(element.getAttribute("id"), element.getAttribute("target"),
					element.getAttribute("source"));
			transictions.add(transiction);
		}
		return transictions;
	}

	public ArrayList<Transiction> getTransictions() {
		return transictions;
	}

	public void setTransictions(ArrayList<Transiction> transictions) {
		this.transictions = transictions;
	}
}
