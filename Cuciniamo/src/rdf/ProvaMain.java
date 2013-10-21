package rdf;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import query.GestoreQuery;

import sostituibilità.GestoreSostituibilità;

import com.hp.hpl.jena.rdf.model.Model;

public class ProvaMain {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		ManagerRdf rdf = new ManagerRdf();
		
		rdf.caricaXml("C:\\Users\\caste\\workspace\\Cuciniamo\\Cuciniamo\\src\\documentiXML\\Linguine al pesto.xml");
		Model model =rdf.ontologia("C:\\Users\\caste\\TBCFreeWorkspace\\Cucina Wellness\\CucinaWellness.rdf");
		//rdf.getRicetta(model);
		GestoreSostituibilità gs = new GestoreSostituibilità(rdf.getDocRicetta(),rdf.getRootRicetta());
		gs.selezione(false,true,false);
		//gs.prendiIngrPrinc();
		GestoreQuery gq = new GestoreQuery();
		gq.caricaOntologia();
		gq.creaQueryPerNome(gs.getNomeRicetta());
		gq.creaQueryPerIngrPrincipali(gs.getIngrPrinc());
		gq.eseguiQuery();
		
		
		
	}

}
