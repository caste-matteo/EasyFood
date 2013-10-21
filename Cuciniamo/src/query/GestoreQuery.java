package query;

import java.io.InputStream;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDFS;

public class GestoreQuery {
	
	private String nomeOntologia="C:\\Users\\caste\\TBCFreeWorkspace\\Cucina Wellness\\CucinaWellness.rdf";
	private String queryPerNome;
	private String queryPerIngrPrinc;
	
	

	
	private Model model; 
	
	
	
	public Model caricaOntologia(){
		model = ModelFactory.createDefaultModel();

		 
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
	public void creaQueryPerNome(String nome){
		QPerNome qnome = new QPerNome(nome);
		qnome.crea();
		setQueryPerNome(qnome.getQuery());
			
	}
	
	public void creaQueryPerIngrPrincipali(ArrayList<String> ingrPrincipali){
		QIngredientiPrinc qPrincipali = new QIngredientiPrinc(ingrPrincipali);
		qPrincipali.crea();
		setQueryPerIngrPrinc(qPrincipali.getQuery());
		
		
	}
	
	
	public void eseguiQuery(){
		
		/*
		 * Query query = QueryFactory.create(getQueryPerNome());
		 */
		Query query = QueryFactory.create(getQueryPerIngrPrinc());
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query);
		qe.close();
	}
	public String getQueryPerNome() {
		return queryPerNome;
	}
	public void setQueryPerNome(String queryPerNome) {
		this.queryPerNome = queryPerNome;
	}
	public String getQueryPerIngrPrinc() {
		return queryPerIngrPrinc;
	}
	public void setQueryPerIngrPrinc(String queryPerIngrPrinc) {
		this.queryPerIngrPrinc = queryPerIngrPrinc;
	}
}

