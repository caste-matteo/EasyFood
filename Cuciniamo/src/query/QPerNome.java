package query;

import com.hp.hpl.jena.vocabulary.RDFS;

public class QPerNome {
	
	private String nomeRicetta;
	private String query;
	
	
	public QPerNome(String nomeRicetta){
		this.nomeRicetta=nomeRicetta;
	}
	
	public void crea(){
		this.query = "prefix rdfs: <" + RDFS.getURI() + ">"+
				 
				"SELECT ?nome " +
				"WHERE {" +
				"?x rdfs:label ?nome ." +
				"FILTER regex(?nome, \""+ nomeRicetta + "\",\"i\") }";
				
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
}	
