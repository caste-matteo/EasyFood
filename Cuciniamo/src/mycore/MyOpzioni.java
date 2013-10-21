package mycore;

import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Proprietà di una ricetta
 * @author pierpytom
 *
 */
public class MyOpzioni implements Serializable {
	
	// Ricetta a cui sono associate le opzioni, permette di ritrovare
	// le opzioni delle ricette sottostanti.
	private MyRicetta parent;
	MyConnectionHelper connectionHelper = new MyConnectionHelper();

	
	private String nomeSalvataggio = null;
	private String autore = null;
	private String categoriaInf = null;
	private String categoriaSup = null;
	private Integer difficolta = null;
	private Integer tempoSup = null, tempoInf = null;
	private String tipoTempoSup = null, tipoTempoInf = null;
	private String festivita = null;
	private Boolean privata = null;
	private ArrayList<String> strumenti = new ArrayList<String>();

	// Valori che vengono calcolati alla prima richiesta e poi conservati:
	Integer durataCached=null, summaTempiCached=null, durataMassimaCached=null, difficoltaMassimaCached=null;
	String listaStrumentiCached=null, listaStrumentiSottoricetteCached=null;
	ArrayList<String> totalitaStrumentiCached=null;
	ArrayList<String> strumentiSottoricetteCached=null;
	
	private String promemoria = null;
	
	public MyOpzioni(MyRicetta parent) {
		this.parent = parent;
	}
	
	/**
	 * Stampa tutti i valori della ricetta come da grammatica compattata.
	 */
	public void printAll(String tab) {
		System.out.println(tab+"Salva: "+ nomeSalvataggio);
		System.out.println(tab+"Autore: " + autore);
		System.out.println(tab+"Categoria: "+categoriaInf+" ("+categoriaSup+")");
		System.out.println(tab+"Difficoltà: "+difficolta);
		System.out.println(tab+"Tempo: " + calcolaDurata() + " minuti");
	}
	
	public String getAdvancedInfo() {
		
		String result = "";
		
		result += (nomeSalvataggio != null) ? "Nome salvataggio: " + nomeSalvataggio : "Ricetta non salvata.";
		result += "\n";
		
		result += (autore != null)	? "Autore della ricetta: "+ autore : "Autore sconosciuto.";
		result += "\n";
		
		if(categoriaInf != null)
			result += "Ricetta inserita in '"+categoriaInf+"'"+(categoriaSup != null ? " nella sezione " + categoriaSup: "") + printIdCategoria()+".";
		else 
			result+= "Nessuna categoria inserita, si supporrà \"Altro\" in \"Altro\".";
		result += "\n";
		
		result += (difficolta != null && difficolta != 0) ? "Difficoltà d'esecuzione: " + difficolta : "Difficoltà d'esecuzione sconosciuta"; 
		result += (parent.numSottoricette() > 0) ? ", difficoltà massima delle sottoricette "+(difficoltaMassima(false)>0? ": "+difficoltaMassima(false)+"." : "non data.") : ".";
		result += "\n";
		
		result += calcolaDurata() > 0 ? "Tempo necessario per il completamento: " + calcolaDurata() +"minuti": "Tempo di preparazione sconosciuto";
		if (parent.numSottoricette() > 0) {
			int durataMassima = durataMassima(true);
			int summaTempi = summaDeiTempi(true);
			result += summaTempi > durataMassima ? " (potrebbe variare tra "+durataMassima+" min. e "+summaTempi +	" min.)." : ".";
		}
		result += durataMassima(true)>calcolaDurata() ? "\nNB: la preparazione di una sottoricetta prenderà più tempo della ricetta stessa!" : "";
		result += "\n";

		if(strumenti.size()>0) {
			result += "Strumenti necessari per la realizzazione: "+listaStrumenti();
			result += strumentiSottoricette().size()>0 ? "; inoltre per le sottoricette sono necessari: "+listaStrumentiSottoricette() : "";
			result += ".";
		} else
			result += "Non sono stati riportati strumenti necessari per la realizzazione.";
		result += "\n";
		
		result += (festivita!=null) ? "Questa ricetta è stata associata a " + festivita + printIdFestivita() : "Questa ricetta non è stata associata ad alcuna festività";
		result += "\n";
		
		return result;

	}

	
	//------------------------------------------------------------------------------//
	//-------------------------- ASSEGNAZIONE VALORI -------------------------------//
	//------------------------------------------------------------------------------//

	// I valori opzionali possono essere settati solo la prima volta, poi non possono più essere modificati,
	// se si tenta la modifica viene lanciata l'eccezione IllegalAssignmentException e nessuna modifica viene
	// fatta.
	
	public String getNomeSalvataggio() { return nomeSalvataggio; }
	public void setNomeSalvataggio(String nomeSalvataggio) throws IllegalAssignmentException {
		if(this.nomeSalvataggio == null)	this.nomeSalvataggio = nomeSalvataggio;
		else		throw new IllegalAssignmentException("Ridefinizione del nome salvataggio non concessa.");
	}
	
	public String getAutore() {	return autore; }
	public void setAutore(String autore) throws IllegalAssignmentException {
		if(this.autore == null)				this.autore = autore;
		else	throw new IllegalAssignmentException("Ridefinizione dell'autore non concessa.");
	}
	
	public String getCategoriaInf() { return categoriaInf; }
	public void setCategoriaInf(String categoriaInf) throws IllegalAssignmentException {
		if(this.categoriaInf == null) 		this.categoriaInf = categoriaInf;
		else	throw new IllegalAssignmentException("Ridefinizione della categoria inferiore non concessa.");
	}

	public String getCategoriaSup() { return categoriaSup; }
	public void setCategoriaSup(String categoriaSup) throws IllegalAssignmentException {
		if(this.categoriaSup == null)		this.categoriaSup = categoriaSup;
		else	throw new IllegalAssignmentException("Ridefinizione della categoria superiore non concessa.");
	}
	
	public Integer getDifficolta() { return difficolta;	}
	public void setDifficolta(Integer difficolta) throws IllegalAssignmentException {
		if(this.difficolta == null)			this.difficolta = difficolta;
		else	throw new IllegalAssignmentException("Ridefinizione della categoria superiore non concessa.");
	}
	
	public Integer getTempoSup() { return tempoSup;	}
	public void setTempoSup(Integer tempoSup) throws IllegalAssignmentException {
		if(this.tempoSup == null)			this.tempoSup = tempoSup;
		else	throw new IllegalAssignmentException("Ridefinizione del tempo superiore non concessa.");
	}
	
	public Integer getTempoInf() { return tempoInf; }
	public void setTempoInf(Integer tempoInf) throws IllegalAssignmentException {
		if(this.tempoInf == null)			this.tempoInf = tempoInf;
		else	throw new IllegalAssignmentException("Ridefinizione del tempo inferiore non concessa.");
	}
	
	public String getTipoTempoSup() { return tipoTempoSup; }
	public void setTipoTempoSup(String tipoTempoSup) throws IllegalAssignmentException {
		if(this.tipoTempoSup == null)			this.tipoTempoSup = tipoTempoSup;
		else	throw new IllegalAssignmentException("Ridefinizione del tipo tempo superiore non concessa.");
	}
	
	public String getTipoTempoInf() { return tipoTempoInf; }
	public void setTipoTempoInf(String tipoTempoInf) throws IllegalAssignmentException {
		if(this.tipoTempoInf == null)			this.tipoTempoInf = tipoTempoInf;
		else	throw new IllegalAssignmentException("Ridefinizione del tipo tempo inferiore non concessa.");
	}
	
	public String getFestivita() { return festivita; }
	public void setFestivita(String festivita) throws IllegalAssignmentException {
		if(this.festivita == null)			this.festivita = festivita;
		else	throw new IllegalAssignmentException("Ridefinizione della festivita non concessa.");
	}

	public Boolean getPrivata() { return privata; }
	public void setPrivata(Boolean privata) throws IllegalAssignmentException {
		if(this.privata == null)			this.privata = privata;
		else	throw new IllegalAssignmentException("Ridefinizione della tipologia privata non concessa.");
	}
	
	public void aggiungiStrumento(String strumento) 	{ strumenti.add(strumento); 	}
	public String getStrumento(int index)				{ return strumenti.get(index); 	}
	public int numStrumenti() 							{ return strumenti.size(); 		}	
	
	public String getPromemoria() {	return promemoria; }
	public void setPromemoria(String promemoria) throws IllegalAssignmentException {
		if(this.promemoria == null)				this.promemoria = promemoria;
		else	throw new IllegalAssignmentException("Ridefinizione del promemoria non concessa.");
	}

	
	//------------------------------------------------------------------------------//
	//------------------------------ EXTRAS ----------------------------------------//
	//------------------------------------------------------------------------------//

	/**
	 * In base al tempo dato (in ore e minuti o soli minuti) calcola l'equivalente in soli minuti.
	 * @return
	 */
	public Integer calcolaDurata() {
		
		if(durataCached != null)
			return durataCached;
		
		Integer durata = null;
		
		if(tipoTempoSup != null) {
			// Se ore moltiplica per 60, in ogni altro caso non si sa, usa i minuti
			durata =
				tipoTempoSup.equalsIgnoreCase("ore")||tipoTempoSup.equalsIgnoreCase("ora")?
						tempoSup*60 : tempoSup;
			if(tipoTempoInf != null)
				// Se ore moltiplica per 60, in ogni altro caso non si sa, usa i minuti		
				durata +=
					tipoTempoInf.equalsIgnoreCase("ore")||tipoTempoInf.equalsIgnoreCase("ora")?
							tempoInf*60 : tempoInf;
		}
		
		if(durata==null)
			return 0;
		durataCached = durata;
		return durata;
		
	}
	
	
	public Integer summaDeiTempi(boolean includeCalled) {
		if(summaTempiCached != null)
			return summaTempiCached;
		Integer summa = includeCalled ? calcolaDurata() : 0;
		for (int i = 0; i<parent.numSottoricette(); i++)
			summa += parent.getSottoricetta(i).getOpzioni().summaDeiTempi(true);
		summaTempiCached = summa;
		return summa;
	}
	
	public Integer durataMassima(boolean includeCalled) {
		if(durataMassimaCached!=null)
			return durataMassimaCached;
		// Se sei il primo non devi contare il tuo tempo, altrimenti si
		Integer max = (includeCalled) ? calcolaDurata() : 0;
		for (int i=0; i<parent.numSottoricette(); i++) {
			MyOpzioni temp = parent.getSottoricetta(i).getOpzioni();
			max = java.lang.Math.max(max, temp.durataMassima(true));
		}
		durataMassimaCached = max;
		return max;
	}

	public Integer difficoltaMassima(boolean includeCalled) {
		if(difficoltaMassimaCached!=null)
			return difficoltaMassimaCached;
		// Se sei il primo non devi contare la tua difficolta, altrimenti si
		Integer max = (includeCalled && difficolta != null) ? difficolta : 0;
		for (int i=0; i<parent.numSottoricette(); i++) {
			MyOpzioni temp = parent.getSottoricetta(i).getOpzioni();
			max = java.lang.Math.max(max, temp.difficoltaMassima(true));
		}
		difficoltaMassimaCached = max;
		return max;
	}
	
	public String listaStrumenti() {
		if(listaStrumentiCached != null)
			return listaStrumentiCached;
		if (strumenti.size() == 0)
			return "";
		String result = "";
		for(String temp : strumenti)
			result += temp + ", ";
		result = result.substring(0, result.length()-2);
		listaStrumentiCached = result;
		return result;
	}

	/**
	 * Totalità degli strumenti
	 * @return tutti gli strumenti (doppioni inclusi)
	 */
	public ArrayList<String> totalitaStrumenti() {
		if(totalitaStrumentiCached != null)
			return totalitaStrumentiCached;
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(strumenti);
		for (int i=0; i<parent.numSottoricette(); i++) {
			MyOpzioni temp = parent.getSottoricetta(i).getOpzioni();
			result.addAll(temp.totalitaStrumenti());
		}
		totalitaStrumentiCached = result;
		totalitaStrumentiCached.trimToSize();
		return result;
	}
	
	/**
	 * Strumenti in più richiesti dalle sottoricette (esclusi
	 * quelli attuali ed i doppioni).
	 * @return vettore di strumenti
	 */
	public ArrayList<String> strumentiSottoricette() {
		if(strumentiSottoricetteCached != null)
			return strumentiSottoricetteCached;
		// 1: ordina gli strumenti
		ArrayList<String> result = totalitaStrumenti();
		Collections.sort(result);
		// 2: elimina i doppioni
		for(int i=0; i<result.size(); i++) {
			// Fissato i faccio il confronto fino a quando tutti gli elementi
			// successivi (se esistono) hanno lo stesso nome
			while(i+1<result.size() && result.get(i).equalsIgnoreCase(result.get(i+1)))
				result.remove(i+1);
		}
		// 3: elimina quelli presenti in questa MyOpzioni
		for(int i=0; i<strumenti.size(); i++)
			for(int j=0; j<result.size(); j++)
				if(strumenti.get(i).equals(result.get(j)))
					result.remove(j--);	// Al prossimo giro devo ricalcolare j
		strumentiSottoricetteCached = result;
		strumentiSottoricetteCached.trimToSize();
		return result;
	}
	
	public String listaStrumentiSottoricette() {
		if(listaStrumentiSottoricetteCached != null)
			return listaStrumentiSottoricetteCached;
		ArrayList<String> strumentisottoricette = strumentiSottoricette();
		if (strumentisottoricette.size() == 0)
			return "";
		String result = "";
		for(String temp : strumentisottoricette)
			result += temp + ", ";
		result = result.substring(0, result.length()-2);
		listaStrumentiSottoricetteCached = result;
		return result;
	}
	
	private String printIdCategoria() {
		int id = connectionHelper.getIdCategoria(categoriaInf, categoriaSup);
		if (id<0)
			return " (nessuna categoria trovata nel db con i nomi forniti)";
		else if(id > 0)
			return " (ID associato nel database: "+id+")";
		else
			return " (connessione al db assente, impossibile trovare l'id associato)";
	}
	
	private String printIdFestivita() {
		int id = connectionHelper.getIdFestivita(festivita);
		if (id<0)
			return " (nessuna categoria trovata nel db con i nomi forniti)";
		else if(id > 0)
			return " (ID associato nel database: "+id+")";
		else
			return " (connessione al db assente, impossibile trovare l'id associato)";
	}
	
}
