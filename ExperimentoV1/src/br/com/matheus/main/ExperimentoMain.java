package br.com.matheus.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.matheus.models.State;
import br.com.matheus.models.Transiction;

public class ExperimentoMain {

	public static void main(String[] args) {

		ArrayList<State> states = new ArrayList<State>();
		ArrayList<Transiction> transictions = new ArrayList<Transiction>();

		try {

			File xmlStateMachine = new File("./data/status_transitions.xml");
			System.out.println(xmlStateMachine.exists());

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xml = dBuilder.parse(xmlStateMachine);
			xml.getDocumentElement().normalize();

			NodeList nList = xml.getElementsByTagName("mxCell");
			for (int i = 0; i < nList.getLength(); i++) {

				Node node = nList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					if (element.hasAttribute("value")) {
						if (!(element.getAttribute("value").equals(""))) {
							State state = new State(element.getAttribute("id"), element.getAttribute("value"),
									element.getAttribute("parent"));
							System.out.println(state);
							states.add(state);
						}
					} else if (element.hasAttribute("target") && element.hasAttribute("source")) {
						Transiction transiction = new Transiction(element.getAttribute("id"),
								element.getAttribute("targer"), element.getAttribute("source"));
						transictions.add(transiction);
						System.out.println(transiction);
					}

				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
