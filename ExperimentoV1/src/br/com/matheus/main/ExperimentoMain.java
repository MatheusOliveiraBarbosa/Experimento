package br.com.matheus.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.matheus.models.State;
import br.com.matheus.models.Transiction;
import br.com.matheus.readerXML.ReaderXML;

public class ExperimentoMain {

	static ArrayList<State> states = new ArrayList<State>();
	static ArrayList<Transiction> transictions = new ArrayList<Transiction>();
	static ReaderXML reader = new ReaderXML();

	private static final String TAG_NAME = "mxCell";
	private static final String PATH_XML = "./data/status_transitions.xml";

	public static void main(String[] args) {

		// Ler o arquivo xml da Máquina de Estado
		reader.readXML(PATH_XML);
		// Cria uma lista de nodes com os elementos marcados por a tag
		NodeList nList = reader.getNodeList(TAG_NAME);

		for (int i = 0; i < nList.getLength(); i++) {

			Node node = nList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (element.hasAttribute("value")) {
					if (!(element.getAttribute("value").equals(""))) {
						if (element.getAttribute("value").contains("<br>")) {
							State state = new State(element.getAttribute("id"),
									element.getAttribute("value").replace("<br>", ""), element.getAttribute("parent"));
							states.add(state);
							System.out.println(state);

						} else {
							State state = new State(element.getAttribute("id"), element.getAttribute("value"),
									element.getAttribute("parent"));
							System.out.println(state);
							states.add(state);
						}
					}
				}
				if (element.hasAttribute("target") && element.hasAttribute("source")) {
					Transiction transiction = new Transiction(element.getAttribute("id"),
							element.getAttribute("target"), element.getAttribute("source"));
					transictions.add(transiction);
					System.out.println(transiction);
				}

			}
		}
		System.out.println("States: " + states.size());
		System.out.println("Transictions: " + transictions.size());
		
		//Ler o arquivo Java e verifica se contem valor associado ao estado
		try {
			File java = new File("./data/ThingStatus.java");
			Scanner scanner = new Scanner(java);
			while(scanner.hasNext()){
				String line = scanner.nextLine();
				for(State state : states){
					if (line.contains(state.getValue())) {
						System.out.println("consegui");
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
