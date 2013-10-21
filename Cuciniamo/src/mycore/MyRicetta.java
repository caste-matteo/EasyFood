package mycore;

import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

public class MyRicetta implements TreeModel, MyGraphModel, Serializable {

	private MyRicetta root;
	@SuppressWarnings("unused")
	// Usato internamente dalla aggiungiSottoricetta, previsione per il futuro
	private MyRicetta parent;

	// Una volta calcolata la totalità degli ingredienti la conservo, così
	// da risparmiare tempo le volte successive.
	ArrayList<MyIngrediente> totalitaIngredientiCached = null;

	/**
	 * Nuova ricetta (sarà la radice nel caso si aggiungano sottoricette, se viene
	 * aggiunta la radice viene cambiata di conseguenza).
	 */
	public MyRicetta() {
		root = this;
		parent = null;
	}


	private String nomeEsteso = null;

	private MyOpzioni opzioni = new MyOpzioni(this);

	private ArrayList<MyRicetta> sottoricette = new ArrayList<MyRicetta>();

	public ArrayList<MyRicetta> getSottoricette() {
		return sottoricette;
	}


	private ArrayList<MyIngrediente> ingredienti = new ArrayList<MyIngrediente>();
	private String preparazione = "";
	private ArrayList<String> linkimmagini = new ArrayList<String>();
	private ArrayList<String> descimmagini = new ArrayList<String>();

	/**
	 * Controlla tutte le informazioni aggiuntive (tempo ricetta coerente, categoria nel db,
	 * esistenza dell'autore, festività, ecc...)
	 */
	public boolean check(){
		return true;
	}

	/**
	 * Stampa tutti i valori della ricetta come da grammatica compattata.
	 */
	public void printAll(String tab) {
		System.out.println(tab+"Nome: "+nomeEsteso);
		opzioni.printAll(tab);
		for(int i=0; i<sottoricette.size(); i++)
			sottoricette.get(i).printAll(tab+"\t");
	}

	public String getNomeEsteso() {
		return nomeEsteso;
	}
	public void setNomeEsteso(String nomeEsteso) throws IllegalAssignmentException {
		if(this.nomeEsteso == null)
			this.nomeEsteso = nomeEsteso;
		else
			throw new IllegalAssignmentException("Ridefinizione del nome della ricetta non concesso.");
	}
	public String toString(){
		return nomeEsteso;
	}

	public MyOpzioni getOpzioni() {
		return opzioni;
	}

	public String getAdvancedInfo() {
		String result = "";
		result += nomeEsteso + ": " + BlankRemover.trim(preparazione);
		result += "\n\nInformazioni utili:\n";
		result += opzioni.getAdvancedInfo();
		return result;
	}

	//------------------------------------------------------------------------------//
	//----------------------------- SOTTORICETTE -----------------------------------//
	//------------------------------------------------------------------------------//

	public void aggiungiSottoricetta(MyRicetta sottoricetta){
		sottoricetta.root = root;
		sottoricetta.parent = this;
		sottoricette.add(sottoricetta);
	}

	public int numSottoricette(){
		return sottoricette.size();
	}

	public MyRicetta getSottoricetta(int index) {
		return sottoricette.get(index);
	}


	//------------------------------------------------------------------------------//
	//------------------------ INGREDIENTI E PREPARAZIONE --------------------------//
	//------------------------------------------------------------------------------//

	public void aggiungiIngrediente(MyIngrediente i){
		// Copio l'ingrediente in uno nuovo con root e parent corretta
		MyIngrediente ultimo = new MyIngrediente(this, root);
		ultimo.setMisura(i.getMisura());
		ultimo.setQuantita(i.getQuantita());
		ultimo.setTipo(i.getTipo());
		ingredienti.add(ultimo);
	}

	public MyIngrediente ultimoIngrediente() {
		return ingredienti.get(ingredienti.size()-1);
	}

	public MyIngrediente getIngrediente(int index) {
		return ingredienti.get(index);
	}

	public int numIngredienti() {
		return ingredienti.size();
	}

	// Ora controlla tutti i parametri passati, metodo chiamato quando il parser ha finito di 
	// passare i token quindi momento giusto per ipotizzare le assegnazioni
	// Esempio guida: 30g di farina, 40 di zucchero, 3 uova, zucchero
	public void sistemaIngredienti(){

		MyIngrediente current;
		for(int i=0; i<ingredienti.size(); i++) {

			//System.out.println("\nAnalizzo: "+ingredienti.get(i).getTipo());
			current = ingredienti.get(i);

			// L'elemento corrente inizia con "di " qualcosa?
			if(current.getTipo().startsWith("di ")) {
				//Tronca via il "di "!
				current.setTipo(current.getTipo().substring(3) );
				//System.out.println("Risultato troncato: "+ingredienti.get(i).getTipo());
			}

			//Si tratta di un qb o q.b.?
			if(current.getTipo().endsWith("qb")) {
				current.setTipo(current.getTipo().substring(0, current.getTipo().length()-3));
				//System.out.println("qb troncato, ora ho: "+current.getTipo());
				current.setQuantita(0);					// QB = 0
			} else if(current.getTipo().endsWith("q.b.")) {
				current.setTipo(current.getTipo().substring(0, current.getTipo().length()-5));
				//System.out.println("q.b. troncato, ora ho: "+current.getTipo());
				current.setQuantita(0);					// QB = 0
			}

			// ho un'unità di misura?
			if(current.getMisura() != null)
				// quello davanti, se esiste, ce l'ha?
				if(i+1<ingredienti.size() && ingredienti.get(i+1).getMisura() == null)
					// ok, c'è un elemento senza unità di misura, è del tipo "di zucchero"?
					if(ingredienti.get(i+1).getTipo().startsWith("di "))
						// imposta l'unità di misura all'elemento davanti!
						ingredienti.get(i+1).setMisura(current.getMisura());

		} // for(int i=0; i<ingredienti.size(); i++)
		
		ingredienti.trimToSize();

	}

	private ArrayList<MyIngrediente> getCopiaIngredienti() {
		ArrayList<MyIngrediente> copia = new ArrayList<MyIngrediente>(); 
		for(MyIngrediente temp : ingredienti)
			copia.add(temp.getCopia());
		return copia;
	}
	
	/**
	 * Restituisce la somma di tutti gli ingredienti necessari per il completamento
	 * della ricetta e di tutte le sue sottoricette.
	 * @return lista di ingredienti
	 */
	public ArrayList<MyIngrediente> getTotalitaIngredienti() {
		return getTotalitaIngredienti(true);
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<MyIngrediente> getTotalitaIngredienti(boolean seed) {

		if(totalitaIngredientiCached != null)
			return totalitaIngredientiCached;
		
		ArrayList<MyIngrediente> temptot = getCopiaIngredienti();
		
		for (MyRicetta temp : sottoricette)
			temptot.addAll(temp.getTotalitaIngredienti(false));
		
		// Faccio fare l'ordinamento solo al chiamante
		if(!seed)
			return temptot;

		ArrayList<MyIngrediente> totalitaIngredienti = new ArrayList<MyIngrediente>();
		Collections.sort(temptot);
		// Ciclo tutti gli elementi trovati
		for(int i=0; i<temptot.size(); i++) {
			// Fissato i faccio il confronto fino a quando tutti gli elementi
			// successivi (se esistono) hanno lo stesso nome
			int j;
			for(j=i+1; j<temptot.size() &&
			temptot.get(i).getTipo().equalsIgnoreCase(temptot.get(j).getTipo());
			j++)
			{
				// Tecnica: In caso di due tipi uguali il valore più a sinistra (i) avrà la somma, finito il giro
				// di lookup in avanti metto il valore i negli array dei risultati.

				// Nota: quantità non obbligatoria, misura non obbligatoria ma se c'è è dopo quantità!
				// Caso di due ingredienti senza quantità
				if(temptot.get(i).getQuantita() == null && temptot.get(j).getQuantita() == null) 
					continue;
				// Un solo ingrediente ha la quantità:
				if(temptot.get(j).getQuantita() == null)	// Elemento a destra: no problema, prossima iterazione cambia
					continue;
				if(temptot.get(i).getQuantita() == null) {	// Elemento a sinistra: inverti!
					temptot.get(i).setQuantita(temptot.get(j).getQuantita());
					temptot.get(i).setMisura(temptot.get(j).getMisura());
					continue;
				}
				// Nota: Se sono arrivato qui entrambe le quantità non sono null!
				// Caso di due ingredienti senza unità di misura oppure con misura uguale
				if(	temptot.get(i).getMisura() == null && temptot.get(j).getMisura() == null ||
						temptot.get(i).getMisura().equalsIgnoreCase(temptot.get(j).getMisura())) {
					temptot.get(i).setQuantita(temptot.get(i).getQuantita()+temptot.get(j).getQuantita());
					continue;
				}
				// Caso di misura e kilomisura
				if(temptot.get(i).getMisura() != null && temptot.get(j).getMisura() != null) {
					// Caso di Kg per i e g per j
					if(temptot.get(i).getMisura().equalsIgnoreCase("k"+temptot.get(j).getMisura())) {
						temptot.get(i).setQuantita(1000*temptot.get(i).getQuantita()+temptot.get(j).getQuantita());
						temptot.get(i).setMisura(temptot.get(j).getMisura());
						continue;
					}
					// Caso di g per i e Kg per j
					if(temptot.get(j).getMisura().equalsIgnoreCase("k"+temptot.get(i).getMisura())) {
						temptot.get(i).setQuantita(temptot.get(i).getQuantita()+1000*temptot.get(j).getQuantita());
						temptot.get(j).setMisura(temptot.get(i).getMisura());
						continue;
					}
					// Caso di misure diverse... Boh... Sgrido l'utente...
					continue;
				}
				

			}
			// Prendo l'ingrediente più a sinistra
			totalitaIngredienti.add(temptot.get(i));
			// Salto tutta la parte di ingredienti uguali
			i=j-1;
		}

		totalitaIngredienti.trimToSize();
		totalitaIngredientiCached = totalitaIngredienti;
		return totalitaIngredienti;
	}

	public String getPreparazione() {
		return BlankRemover.trim(preparazione);
	}
	public void aggiungiPreparazione(String aggiunta) {
		preparazione += aggiunta; 
	}
	public void aggiungiLinkImmagine(String link) {
		linkimmagini.add(link);
		preparazione += "(rif: immagine "+linkimmagini.size()+")";
	}
	public void aggiungiDescImmagine(String desc) {
		// Non tutte le immagini possono avere descrizione, mettiti in pari:
		while(descimmagini.size()<descimmagini.size()-1)
			descimmagini.add("");
		descimmagini.add(desc);
	}

	
	//------------------------------------------------------------------------------//
	//---------------------------- MY GRAPH MODEL ----------------------------------//
	//------------------------------------------------------------------------------//
	
	
	
	@Override
	public int currentDepth() {
		if (parent == null) return 0;
		int currentDepth = 1;
		MyRicetta temp_parent = parent;
		while(temp_parent.parent != null) {
			currentDepth++;
			temp_parent = temp_parent.parent;
		}
		return currentDepth;
	}
	
	@Override
	public int maxDepth() {
		return root.maxDepth(0)+1; //+1 -> dò per scontato che ci siano foglie nel nodo
	}
	
	// Ricerca depthfirst, risultato 1 + profondità sottostante
	private int maxDepth(int currentlevel) {
		currentlevel++;
		int sublevel = currentlevel;
		for(int i=0; i<numSottoricette(); i++)
			sublevel = Math.max(
					sublevel, 
					getSottoricetta(i).maxDepth(currentlevel)
			);
		return sublevel;		
	}
	
	@Override
	public int maxBreadth() {
		return root.currentBreadth();
	}
	
	@Override
	public int currentBreadth() {
		int breadth = 0;
		// Ampiezza occupata dalle ricette (ampiezza normale)
		for(int i=0; i<numSottoricette(); i++)
			breadth += getSottoricetta(i).currentBreadth();
		// Ampiezza occupata dai nodi foglia:
		breadth += ingredienti.size()*leafWeight(); //Approssimo all'intero superiore
		return (breadth == 0 || breadth < nodeWeight()) ? nodeWeight() : breadth;
	}
	
	@Override
	public int offsetBreadth() {
		if(parent == null) // se è la root non ha fratelli!
			return 0;
		int breadth = parent.offsetBreadth();
		for(int i=0; i<parent.numSottoricette(); i++) {
			if(parent.getSottoricetta(i) == this)
				break;
			breadth += parent.getSottoricetta(i).currentBreadth();
		}
		return breadth;
	}

	
	@Override
	public String currentLabel() {
		return nomeEsteso;
	}
	
	@Override
	public MyGraphModel getSubnode(int index){
		return getSottoricetta(index);
	}

	@Override
	public int getSubnodeCount(){
		return numSottoricette();
	}
	
	@Override
	public MyGraphModel getLeaf(int index) {
		return ingredienti.get(index);
	}
	
	@Override
	public int getLeafCount() {
		return ingredienti.size();
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	@Override
	public int leafWeight() {
		return 1;
	}
	
	@Override
	public int nodeWeight() {
		return 2;
	}
	
	@Override
	public String getMeta() {
		return opzioni.getPromemoria();
	}

	@Override
	public boolean isRoot() {
		return parent == null;
	}
	
	//------------------------------------------------------------------------------//
	//------------------------------ TREE MODEL ------------------------------------//
	//------------------------------------------------------------------------------//

	// Nota: le ricette sono sempre dei nodi, gli ingredienti sono sempre le foglie di questi nodi,
	//		 procedimento e altro sono tutte proprietà della ricetta.

	@Override
	public Object getChild(Object parent, int index) {
		if(parent instanceof MyRicetta) {
			MyRicetta my = (MyRicetta)parent;
			if(index < my.numSottoricette())
				return my.getSottoricetta(index);
			else
				return my.getIngrediente(index-my.numSottoricette());
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(parent instanceof MyRicetta)
			return ((MyRicetta)parent).numSottoricette()+((MyRicetta)parent).numIngredienti();
		else
			return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if(parent instanceof MyRicetta) {
			if(child instanceof MyRicetta) {
				for(int i=0; i<((MyRicetta)parent).numSottoricette(); i++)
					if ( ((MyRicetta)parent).getSottoricetta(i) == child)
						return i;
			}
			else if(child instanceof MyIngrediente) {
				for(int i=0; i<((MyRicetta)parent).numIngredienti(); i++)
					if ( ((MyRicetta)parent).getIngrediente(i) == child)
						return ((MyRicetta)parent).numSottoricette() + i;
			}
		}
		return 0;
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public boolean isLeaf(Object node) {
		if(node instanceof MyRicetta)
			return false;
		return true;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// Non gestisco eventi
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// Non gestisco eventi
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// Non gestisco eventi
	}

}
