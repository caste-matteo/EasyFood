package query;

import org.apache.log4j.BasicConfigurator;

public class provaQuery {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		GestoreQuery gq = new GestoreQuery();
		gq.caricaOntologia();
		gq.creaQueryPerNome("linguine al pesto");
		gq.eseguiQuery();

	}

}
