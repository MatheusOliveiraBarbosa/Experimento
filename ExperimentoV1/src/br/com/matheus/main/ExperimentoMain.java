package br.com.matheus.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.w3c.dom.NodeList;

import br.com.matheus.models.State;
import br.com.matheus.utils.ExtractInfoStates;
import br.com.matheus.utils.ExtractInfoTransictions;
import br.com.matheus.utils.FileUtils;
import br.com.matheus.utils.ReaderXML;

public class ExperimentoMain {

	static ReaderXML reader = new ReaderXML();
	static ExtractInfoStates extractState = new ExtractInfoStates();
	static ExtractInfoTransictions extractTransictions = new ExtractInfoTransictions();

	private static final String TAG_NAME = "mxCell";
	private static final String PATH_XML = "./data/status_transitions.xml";
	private static final String PATH_PROJECT = "C:/Users/mathe/Dropbox/UFCG/Mestrado/Material Tema/Código-Fontes/smarthome-36c60045325eb2b64d28ae2d0dec080a5dc5de69/bundles/core/org.eclipse.smarthome.core.thing/src";
	private static final String FILE_EXTENSION = ".java";

	private static HashMap<String, State> classAndState = new HashMap<String, State>();

	public static void main(String[] args) {

		// Ler o arquivo xml da Máquina de Estado
		reader.readXML(PATH_XML);
		// Cria uma lista de nodes com os elementos marcados por a tag
		NodeList nList = reader.getNodeList(TAG_NAME);

		for (int i = 0; i < nList.getLength(); i++) {

			extractState.extractStates(nList.item(i));
			extractTransictions.extractTransaction(nList.item(i));
		}

		System.out.println("States: " + extractState.getStates().size());
		System.out.println("Transictions: " + extractTransictions.getTransictions().size());

		// Ler o arquivo Java e verifica se contem valor associado ao estado
		ArrayList<String> files = (ArrayList<String>) FileUtils.listNames(PATH_PROJECT, "", FILE_EXTENSION);
		try {
			for (int i = 0; i < files.size(); i++) {

				//
				String path = files.get(i);
				path = path.substring(0, path.lastIndexOf(FILE_EXTENSION));
				path = path.replace(".", "/");
				path = path + FILE_EXTENSION;
				path = PATH_PROJECT + "/" + path;

				File java = new File(path);
				Scanner scanner = new Scanner(java);
				// Contador de vezes que um estado se repete em um arquivo.
				int count = 0;
				while (scanner.hasNext()) {
					String line = scanner.nextLine();
					for (State state : extractState.getStates()) {
						if (line.contains(state.getValue())) {
							count++;
							classAndState.put(java.getName(), state);
						}
					}
				}
			}
			System.out.println(classAndState);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
