package mycore;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;

import visitor.*;
import syntaxtree.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.*;

import parser.ParseException;
import parser.RicettaParser;

/**
 * Crea e popola la classe MyRicetta
 */
public class MyDepthFirstVisitor extends DepthFirstVisitor {

	MyRicetta ricetta;		//ricetta in analisi
	MyRicetta root;			//ricetta radice

	ArrayList<ColoredString> output = new ArrayList<ColoredString>();
	public ArrayList<ColoredString> getOutput() {
		return output;
	}
        
	public MyRicetta getRicetta(){
		return ricetta;
	}
        	
	@Override
	public void visit(Ricetta n) {				// Scopo

		MyRicetta current, previous;
		previous = ricetta;						// Anticipato per non avere errori

		// Scopo
		if(ricetta == null) {
			output.add(new ColoredString("Analizzo la ricetta principale...", Color.green));
			ricetta = new MyRicetta();
			root = ricetta;
		// Sottoricetta
		} else {
			output.add(new ColoredString("Analizzo la sottoricetta...", Color.green));
			//previous = ricetta;					// Salva la ricetta corrente per tornare indietro
			current = new MyRicetta();				// Creane una nuova per poterci lavorare (passandogli la root)
			ricetta.aggiungiSottoricetta(current);	// Mettila come sottoricetta di quella corrente
			ricetta = current;						// Ora facci lavorare il visitor
		}

		super.visit(n);

		if(ricetta != root) {
			ricetta = previous;					// Rimetti la vecchia ricetta a posto
			output.add(new ColoredString("Esco dalla sottoricetta...", Color.green));
		}
		else {
			output.add(new ColoredString("Ricetta analizzata completamente.", Color.green));
			output.trimToSize();
		}

	}
		
	
	@Override
	public void visit(NomeEsteso n) {			// f0 -> ( <STRING> )+
		super.visit(n);
		try {
			ricetta.setNomeEsteso(spacedString(n.f0));
			output.add(new ColoredString("Assegno il nome...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}

	@Override
	public void visit(NomeSalvataggio n) {		// f0 -> <STRING>
		super.visit(n);
		try {
			ricetta.getOpzioni().setNomeSalvataggio(n.f0.toString());
			output.add(new ColoredString("Assegno il nome salvataggio...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}

    @Override
	public void visit(Autore n) {				// f0 -> <STRING>
		super.visit(n);
		try {
			ricetta.getOpzioni().setAutore(n.f0.toString());
			output.add(new ColoredString("Assegno l'autore...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}

	}

    @Override
	public void visit(CategoriaInf n) {			// f0 -> ( <STRING> )+
		super.visit(n);
		try {
			ricetta.getOpzioni().setCategoriaInf(spacedString(n.f0));
			output.add(new ColoredString("Assegno la categoria inferiore...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}
	
	@Override
	public void visit(CategoriaSup n) {			// f0 -> ( <STRING> )+
		super.visit(n);
		try {
			ricetta.getOpzioni().setCategoriaSup(spacedString(n.f0));
			output.add(new ColoredString("Assegno la categoria superiore...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}

    @Override
	public void visit(ValoreDifficolta n) {		// f0 -> <NUM>
		super.visit(n);
		try {
			int diff = Integer.decode(n.f0.toString());
			if(diff < 1 || diff > 10)
				output.add(new ColoredString("Warning: valore difficoltà non ammesso ("+diff+")", Color.yellow));
			else
				ricetta.getOpzioni().setDifficolta(diff);
			output.add(new ColoredString("Assegno la difficoltà...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}

    @Override
	public void visit(TempoSup n) {				// f0 -> Durata(), f1 -> UnitaTempo()
		super.visit(n);
		try {
			ricetta.getOpzioni().setTempoSup(Integer.decode(n.f0.f0.toString()));
			ricetta.getOpzioni().setTipoTempoSup(n.f1.f0.toString());
		} catch (NumberFormatException e) {
			// Non dovrebbe mai verificarsi, il parser termina prima!
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}

    @Override
	public void visit(TempoInf n) {				// f0 -> Durata(), f1 -> UnitaTempo()
		super.visit(n);
		try {
			ricetta.getOpzioni().setTempoInf(Integer.decode(n.f0.f0.toString()));
			ricetta.getOpzioni().setTipoTempoInf(n.f1.f0.toString());
		} catch (NumberFormatException e) {
			// Non dovrebbe mai verificarsi, il parser termina prima!
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}

    @Override
	public void visit(Privata n) {				// f0 -> <STRING>
		super.visit(n);
		try {
			ricetta.getOpzioni().setPrivata(n.f0.toString().equalsIgnoreCase("si"));
			output.add(new ColoredString("Assegno la privacy...", Color.green));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}


    @Override
	public void visit(Strumento n) { 			// f0 -> ( <STRING> )+
		super.visit(n);
		ricetta.getOpzioni().aggiungiStrumento(spacedString(n.f0));
	}

    @Override
	public void visit(Festivita n) {			// f0 -> ( <STRING> )+
		super.visit(n);
		try {
			ricetta.getOpzioni().setFestivita(spacedString(n.f0));
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
	}
    
    public void visit(Promemoria n) {			// f0 -> ( <STRING> | < NUM > )+
    	super.visit(n);
		try {
			String temp = "";
			for (int i=0; i<n.f0.nodes.size(); i++) {
				temp += ((NodeChoice)n.f0.nodes.get(i)).choice.toString() + " ";
			}
			ricetta.getOpzioni().setPromemoria(temp);
		} catch (IllegalAssignmentException e) {
			output.add(new ColoredString("Warning: "+e.getMessage(), Color.yellow));
		}
    }

    @Override
    public void visit(Apri n) {						// f0 -> NomeSottoricetta(), f1 -> [ "(" AutoreSottoricetta() ")" ]
    	super.visit(n);
    		
    	// Se non si tratta di un accesso al database (ovvero non c'è un nome utente specificato tra parentesi)
    	if(!n.f1.present()) {
	    	try {
	    		output.add(new ColoredString("Apertura '"+n.f0.f0.toString()+"' in '"+System.getProperty("user.dir")+"'", Color.blue));
				RicettaParser parser = new RicettaParser(new FileReader(n.f0.f0.toString()));
				Node root = parser.Ricetta();
				System.out.println("Sto chiamando la accept");
				root.accept(this);
	
			} catch (FileNotFoundException e) {
				output.add(new ColoredString("File "+n.f0.f0.toString()+" non trovato!", Color.red));
			} catch(ParseException e) {
				output.add(new ColoredString("Errore nel parsing del file "+n.f0.f0.toString(), Color.red));
			}
    	} else {
    		// TODO: accesso al database per recuperare una ricetta
    	}
    }
    
    @Override
	public void visit(ListaIngredienti n) {			// f0 -> Ingrediente(), f1 -> ( "," Ingrediente() )*
		output.add(new ColoredString("Aggiungo gli ingredienti...", Color.green));
		super.visit(n);
		// Una volta aggiunti tutti gli ingredienti sistemali ricavando
		// "intelligentemente" alcune informazioni mancanti
		output.add(new ColoredString("Ingredienti aggiunti, completo eventuali informazioni mancanti...", Color.green));
		ricetta.sistemaIngredienti();
	}

	// L'ordine con cui vengono chiamati i nodi non è quello congeniale, prima so
	// che deve essere inserito un ingrediente e poi so come (le espansioni hanno
	// ordini diversi)
    @Override
	public void visit(Ingrediente n) {
		// Per ora so che deve essere creato l'ingrediente
		ricetta.aggiungiIngrediente(new MyIngrediente());
		super.visit(n);
		// Old: meglio sistemarli tutti assieme quando si inizia con la preparazione
		//ricetta.elaboraUltimoIngrediente();
	}
        
    @Override
	public void visit(TipoIngrediente n) {				// f0 -> ( <STRING> )+
		ricetta.ultimoIngrediente().setTipo(spacedString(n.f0));	
		super.visit(n);
	}

	// Espansione non sempre presente (quantità non sempre indicata)
    @Override
	public void visit(QuantitaIngrediente n) {

		Pattern pattern = Pattern.compile("^([0-9]*)([a-zA-Z]*)$");
		Matcher matcher = pattern.matcher(n.f0.choice.toString());
		//System.out.println(n.f0.choice.toString());
		matcher.find();
		
		// Non sempre è riportata l'unità di misura
		ricetta.ultimoIngrediente().setQuantita(Integer.decode(matcher.group(1)));
		if(!matcher.group(2).isEmpty())
			ricetta.ultimoIngrediente().setMisura(matcher.group(2));

		super.visit(n);

	}

    @Override
	public void visit(Nota n) {						// f0 -> <NOTA>
		super.visit(n);
		output.add(new ColoredString("Aggiungo la preparazione...", Color.green));
		ricetta.aggiungiPreparazione(n.f0.toString());
	}

    @Override
	public void visit(Link n) {						// f0 -> <ADVSTRING>
		super.visit(n);
		ricetta.aggiungiLinkImmagine(n.f0.toString());
	}

    @Override
	public void visit(DescrizioneLink n) {			// f0 -> <ADVSTRING>
		super.visit(n);
		ricetta.aggiungiDescImmagine(n.f0.toString());
	}
        
        
	private String spacedString(syntaxtree.NodeList f) {
		String temp = "";
		for (Iterator<syntaxtree.Node> it = f.nodes.iterator(); it.hasNext();) {
			syntaxtree.Node nodo = it.next();
			temp += nodo.toString()+(it.hasNext()?" ":"");
		}
		if(temp.isEmpty())
			return null;
		return temp;
	}

}
