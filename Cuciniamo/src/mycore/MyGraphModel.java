package mycore;

/**
 * Interfaccia utilizzata per disegnare un albero come fosse un grafo, a 
 * differenza del TreeModel fa distinzione tra nodi figli e foglie figlie,
 * così da fare i calcoli in modo separato (il numero di nodi influenza in
 * maniera diversa rispetto al numero di foglie, quindi le informazioni 
 * vengono mantenute separate ed è necessario farne la somma per avere 
 * un'informazione simile al numero di figli del TreeModel).
 * @author pierpytom
 */
public interface MyGraphModel {
	
	/**
	 * Massima profondità dell'albero da disegnare, è calcolata dalla root.
	 * Così si può capire la larghezza del grafico.
	 * @return 
	 */
	public int maxDepth();
	
	/**
	 * profondità del nodo atttuale
	 * @return 
	 */
	public int currentDepth();
	
	/**
	 * da calcolare ricorsivamente:
	 * numero di figli del nodo, ogni nodo sottostante restituirà
	 * il numero massimo di figli al di sotto di lui, se non ne ha
	 * restituirà 1. Ogni nodo darà come risultato la somma dei 
	 * numChildren sottostanti.
	 * Così si può capire l'altezza del grafico.
	 * @return 
	 */
	public int maxBreadth();
	
	/**
	 * simile al caso precedente, però non parte dalla root.
	 * @return 
	 */
	public int currentBreadth();
	
	/**
	 * Quanta ampiezza è stata occupata dai fratelli sullo stesso livello.
	 * @return 
	 */
	public int offsetBreadth();
	
	/**
	 * nome da visualizzare sul grafico
	 * @return stringa da inserire nella shape
	 */
	public String currentLabel();
	
	/**
	 * Ritorna il nodo figlio che si vuole esplorare
	 * @param index i-esimo nodo figlio che si vuole esplorare
	 * @return nodo figlio di tipo MyGraphModel
	 */
	public MyGraphModel getSubnode(int index);

	/**
	 * numero di nodi sottostanti
	 * @return numero di figli
	 */
	public int getSubnodeCount();

	/**
	 * Indica se si è al nodo radice o meno.
	 * @return se è la radice o meno
	 */
	public boolean isRoot();
	
	/**
	 * Ritorna la foglia figlia che si vuole analizzare
	 * @param index foglia i-esima che si vuole recuperare
	 * @return la foglia
	 */
	public MyGraphModel getLeaf(int index);
	
	/**
	 * Numero di foglie sotto il nodo
	 * @return numero foglie
	 */
	public int getLeafCount();
	
	/**
	 * Indica se è una foglia o meno (viene disegnata diversamente)
	 * @return se è foglia o meno
	 */
	public boolean isLeaf();
	
	/**
	 * Peso di una foglia, ovvero la proporzione nel disegno (eg: 2)
	 * @return 
	 */
	public int leafWeight();
	
	/**
	 * Peso di un nodo, ovvero la proporzione nel disegno (eg: 4)
	 * @return 
	 */
	public int nodeWeight();
	
	/**
	 * Eventuali informazioni aggiuntive
	 * @return 
	 */
	public String getMeta();
}
