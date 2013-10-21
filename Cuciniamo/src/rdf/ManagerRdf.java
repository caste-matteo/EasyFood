package rdf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

public class ManagerRdf {
	
	
	
	/* ArrayList<String> */
	
	private ArrayList<ArrayList<String>> ingredienti = new ArrayList<ArrayList<String>>();
	private Node rootRicetta; /* Ricetta principale*/
	private Document docRicetta;
	private String uri = "http://example.org/CucinaWellness#";
	
	
	public void caricaXml(String RicettaXml) throws ParserConfigurationException, SAXException, IOException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(RicettaXml);
		setDocRicetta(doc);
		rootRicetta = doc.getFirstChild();
		//System.out.println(rootRicetta.getAttributes().item(0).getTextContent());
			
	}
	
	
	public Node getRootRicetta() {
		return rootRicetta;
	}


	public void setRootRicetta(Node rootRicetta) {
		this.rootRicetta = rootRicetta;
	}


	/** Crea un modello Model dell'ontologia 
	 * 
	 */
	
	public Model ontologia(String nomeOntologia){
		Model model = ModelFactory.createDefaultModel();

		 // use the FileManager to find the input file
		 InputStream in = FileManager.get().open( nomeOntologia );
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + nomeOntologia + " not found");
		}

		// read the RDF/XML file
		model.read(in, null);

		//model.write(System.out);
		
		return model;
		
	}
	/* 
	 * Costruisce un modello Model della ricetta 
	 */
	
	public void costruzioneRdf(Node rootRicetta){
		Model model = ModelFactory.createDefaultModel();
		model.createResource(uri + rootRicetta.getAttributes().item(1).getTextContent());
		model.write(System.out);
	
	}
	
	/*
	 * Riceve la root della ricetta in xml e il modello dell'ontologia
	 * 
	 * */
	
	public Document getDocRicetta() {
		return docRicetta;
	}


	public void setDocRicetta(Document docRicetta) {
		this.docRicetta = docRicetta;
	}

	Property pro;
	String nomeRicetta;
	Statement stmt;
	/*public void getRicetta(Model model){
		
	nomeRicetta = 	getRootRicetta().getAttributes().item(0).getTextContent();
	Resource res = 	model.getResource(uri + nomeRicetta);
	Model ricetta = res.getModel();
	System.out.println(res.getURI());
	pro = model.getProperty("haIngrediente");
	;
	if(model.contains(res, pro)==false)
		System.out.println("null");
	//System.out.println(stmt.toString());
	
		//System.out.println(rootRicetta.getAttributes().item(0).toString());
	}*/
	
	}
	
	
	
	
		
	
	

