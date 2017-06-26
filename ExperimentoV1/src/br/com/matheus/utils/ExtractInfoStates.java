package br.com.matheus.utils;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.matheus.models.State;

public class ExtractInfoStates {

	private ArrayList<State> states;

	public ExtractInfoStates() {
		states = new ArrayList<State>();
	}

	public ArrayList<State> extractStates(Node node) {

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			if (element.hasAttribute("value")) {
				if (!(element.getAttribute("value").equals(""))) {
					if (element.getAttribute("value").contains("<br>")) {
						State state = new State(element.getAttribute("id"),
								element.getAttribute("value").replace("<br>", ""), element.getAttribute("parent"));
						states.add(state);
//						System.out.println(state);

					} else {
						State state = new State(element.getAttribute("id"), element.getAttribute("value"),
								element.getAttribute("parent"));
//						System.out.println(state);
						states.add(state);
					}
				}
			}
		}

		return states;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

}
