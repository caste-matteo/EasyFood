package sostituibilità;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestoreSostituibilità {
	
	//var per scegliere i paramentri per la futura query
	private boolean sostRicetta = false;
	private boolean perNome = false;
	private boolean perIngrPrinc = false;
	private boolean perIngrSec = false;
	
	// var per salvare i parametri da passare alla query
	private String nomeRicetta;
	private ArrayList<String> ingrPrinc = new ArrayList<String>();
	private ArrayList<String> ingrSec;
	
	private Node rootRicetta;
	private Document docRicetta;
	private NodeList primaRicetta;
	private NodeList listaIngrPrinc;
	private NamedNodeMap attributiIngr;
	
	
	// costruttore
	public GestoreSostituibilità(Document docRicetta, Node rootRicetta){
		this.docRicetta = docRicetta;
		this.rootRicetta = rootRicetta;
		
		
	}
	
	
	// prendi il nome della ricetta per passarlo al gestore query
	public void prendiNome(){
		setNomeRicetta(getRootRicetta().getAttributes().item(0).getTextContent()); 	
	}
	
	// prendi gli ingredienti principali per passarli al gestore query
	public void prendiIngrPrinc(){
		primaRicetta = rootRicetta.getChildNodes();
		for(int i=0; i<primaRicetta.getLength(); i++){
			if(primaRicetta.item(i).getNodeName()=="ListaIngredienti")
				listaIngrPrinc = primaRicetta.item(i).getChildNodes();
			else
				if(primaRicetta.item(i).getNodeName()=="Ricetta"){
					ingrPrinc.add(primaRicetta.item(i).getAttributes().getNamedItem("nome").getNodeValue());
				}
		}
		for(int i=0;i<listaIngrPrinc.getLength();i++){
			if(listaIngrPrinc.item(i).hasAttributes()){
				attributiIngr =listaIngrPrinc.item(i).getAttributes();
				ingrPrinc.add(attributiIngr.getNamedItem("tipo").getNodeValue());
				
				
			}
				
		}
		System.out.println(ingrPrinc);
		
		
	}
	
	// prendi gli ingrededienti secondari per passarli al gestore query
	public void prendiIngrSec(){
		
	}
	
	public void selezione(boolean perNome, boolean perIngrPrinc, boolean perIngrSec){
		setPerNome(perNome);
		setPerIngrPrinc(perIngrPrinc);
		setPerIngrSec(perIngrSec);
		if(isPerNome()==true)
			prendiNome();
		else
			if(isPerIngrPrinc()==true){
				prendiIngrPrinc();
		}
	}
	
	
	
	public boolean isSostRicetta() {
		return sostRicetta;
	}
	public void setSostRicetta(boolean sostRicetta) {
		this.sostRicetta = sostRicetta;
	}
	public boolean isPerNome() {
		return perNome;
	}
	public void setPerNome(boolean perNome) {
		this.perNome = perNome;
	}
	public boolean isPerIngrPrinc() {
		return perIngrPrinc;
	}
	public void setPerIngrPrinc(boolean perIngrPrinc) {
		this.perIngrPrinc = perIngrPrinc;
	}
	public boolean isPerIngrSec() {
		return perIngrSec;
	}
	public void setPerIngrSec(boolean perIngrSec) {
		this.perIngrSec = perIngrSec;
	}

	public String getNomeRicetta() {
		return nomeRicetta;
	}

	public void setNomeRicetta(String nomeRicetta) {
		this.nomeRicetta = nomeRicetta;
	}

	public ArrayList<String> getIngrPrinc() {
		return ingrPrinc;
	}

	public void setIngrPrinc(ArrayList<String> ingrPrinc) {
		this.ingrPrinc = ingrPrinc;
	}

	public ArrayList<String> getIngrSec() {
		return ingrSec;
	}

	public void setIngrSec(ArrayList<String> ingrSec) {
		this.ingrSec = ingrSec;
	}

	public Node getRootRicetta() {
		return rootRicetta;
	}

	public void setRootRicetta(Node rootRicetta) {
		this.rootRicetta = rootRicetta;
	}
	

	
}
