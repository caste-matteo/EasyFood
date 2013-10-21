package query;

import java.util.ArrayList;

import com.hp.hpl.jena.vocabulary.RDFS;

public class QIngredientiPrinc {
	
	private ArrayList<String> ingredienti;
	private String query;
	
	public QIngredientiPrinc(ArrayList<String> ingredienti){
		this.ingredienti=ingredienti;
	}
	
	public void crea(){
		this.query = "prefix cucina: <http://example.org/CucinaWellness#>" + 
					"prefix rdfs: <" + RDFS.getURI() + ">"+
					"SELECT ?subject " +
					"WHERE {" +
					"?subject cucina:haIngrediente ?ingrediente ." +
					"?ingrediente rdfs:label ?nome ." +
					"FILTER regex(?nome,\""+ ingredienti.get(0) + "\",\"i\"  )." +
					"	}";
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
