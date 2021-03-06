package br.com.matheus.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReaderXML {

	private Document xml = null;

	public ReaderXML() {
	}

	public Document readXML(String path) {
		// "./data/status_transitions.xml"
		File xmlStateMachine = new File(path);
		try {
			DocumentBuilder dBuilder;
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			xml = dBuilder.parse(xmlStateMachine);
			xml.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return xml;
	}
	
	public NodeList getNodeList(String tagName){
		return xml.getElementsByTagName(tagName);
	}

	public Document getXml() {
		return xml;
	}

	public void setXml(Document xml) {
		this.xml = xml;
	}

}
