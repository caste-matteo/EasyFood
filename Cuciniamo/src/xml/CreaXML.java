package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import mycore.MyOpzioni;
import mycore.MyRicetta;

public class CreaXML {

	private MyRicetta myRicetta;

	private Document doc;
	private String filePath = "C:\\Users\\caste\\workspace\\Cuciniamo\\Cuciniamo\\src\\documentiXML\\";
	private String fileXSD = filePath + "cuciniamo.xsd";
	private Element ingredienti;
	private Element opzioni;
	private Element rootElement;
	private Boolean primaRicetta = true;
	private MyOpzioni myOpzioni;

	public void run(MyRicetta myRicetta) {

		setMyRicetta(myRicetta);
		setMyOpzioni(myRicetta.getOpzioni());

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setValidating(true);
			docFactory.setNamespaceAware(true);
			docFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage","http://www.w3.org/2001/XMLSchema");
			/*
			docFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource",fileXSD);
			*/
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			setDoc(docBuilder.newDocument());

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		creaRootRicetta();
		creaRicetta(getRootElement(), getMyRicetta());
		scriviContenuto(getDoc(), getMyRicetta().getNomeEsteso());
		ValidaXSD valida = new ValidaXSD();
		try {
			valida.run(doc, fileXSD);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void creaRootRicetta() {
		String nomeRicetta = myRicetta.getNomeEsteso();
		setRootElement(doc.createElement("Ricetta"));
		doc.appendChild(rootElement);
		Attr attr = doc.createAttribute("nome");
		attr.setValue(nomeRicetta);
		getRootElement().setAttributeNode(attr);
		getRootElement().setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		/*
		getRootElement().setAttribute("xsi:schemaLocation", fileXSD);
		*/
		setPrimaRicetta(true);
		
	}

	public void creaRicetta(Element parent, MyRicetta myRicetta) {
		if (getPrimaRicetta() == false) {
			for (int i = 0; i < myRicetta.getSottoricette().size(); i++) {
				Element ricetta = doc.createElement("Ricetta");
				Attr attr = doc.createAttribute("nome");
				attr.setValue(myRicetta.getSottoricetta(i).getNomeEsteso());
				ricetta.setAttributeNode(attr);
				parent.appendChild(ricetta);
				creaOpzioni(ricetta, myRicetta.getSottoricetta(i));
				for (int j = 0; j < myRicetta.getSottoricette().get(i)
						.getSottoricette().size(); j++) {
					creaRicetta(ricetta, myRicetta.getSottoricette().get(i)
							.getSottoricetta(j));
				}
				creaIngredienti(ricetta, getDoc(), myRicetta.getSottoricetta(i));
				creaProcedimento(ricetta, getDoc(),
						myRicetta.getSottoricetta(i));
			}

		} else {
			creaOpzioni(parent, myRicetta);
			setPrimaRicetta(false);
			creaRicetta(parent, myRicetta);
			creaIngredienti(parent, getDoc(), myRicetta);
			creaProcedimento(parent, getDoc(), myRicetta);
		}
	}

	public void creaOpzioni(Element rootElement, MyRicetta myRicetta) {
		opzioni = doc.createElement("Opzioni");
		rootElement.appendChild(opzioni);
		if (myRicetta.getOpzioni().getNomeSalvataggio() != null) {
			Element salva = doc.createElement("Salva");
			salva.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getNomeSalvataggio()));
			opzioni.appendChild(salva);
		}
		if (myRicetta.getOpzioni().getAutore() != null) {
			Element autore = doc.createElement("Autore");
			autore.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getAutore()));
			opzioni.appendChild(autore);
		}
		if (myRicetta.getOpzioni().getCategoriaSup() != null) {
			Element categoria = doc.createElement("Categoria");
			opzioni.appendChild(categoria);
			Element sup = doc.createElement("Superiore");
			sup.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getCategoriaSup()));
			categoria.appendChild(sup);

			if (myRicetta.getOpzioni().getCategoriaInf() != null) {
				Element inf = doc.createElement("Inferiore");
				inf.appendChild(doc.createTextNode(myRicetta.getOpzioni()
						.getCategoriaInf()));
				categoria.appendChild(inf);
			}
		}
		if (myRicetta.getOpzioni().getDifficolta() != null) {
			Element diff = doc.createElement("Difficolta");
			diff.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getDifficolta().toString()));
			opzioni.appendChild(diff);
		}
		if (myRicetta.getOpzioni().summaDeiTempi(true).toString() != null) {
			Element tempo = doc.createElement("Tempo");
			tempo.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.summaDeiTempi(true).toString()));
			opzioni.appendChild(tempo);
		}
		if (myRicetta.getOpzioni().getPrivata() != null) {
			Element privata = doc.createElement("Privata");
			privata.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getPrivata().toString()));
			opzioni.appendChild(privata);
		}
		if (myRicetta.getOpzioni().numStrumenti() > 0) {
			Element strumenti = doc.createElement("Strumenti");
			opzioni.appendChild(strumenti);
			for (int i = 0; i < myRicetta.getOpzioni().numStrumenti(); i++) {
				Element strumento = doc.createElement("Strumento");
				strumento.appendChild(doc.createTextNode(myRicetta.getOpzioni()
						.getStrumento(i)));
				strumenti.appendChild(strumento);
			}
		}
		if (myRicetta.getOpzioni().getFestivita() != null) {
			Element festivita = doc.createElement("Festivita");
			festivita.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getFestivita()));
			opzioni.appendChild(festivita);
		}
		if (myRicetta.getOpzioni().getPromemoria() != null) {

			Element promemoria = doc.createElement("Promemoria");
			promemoria.appendChild(doc.createTextNode(myRicetta.getOpzioni()
					.getPromemoria()));
			opzioni.appendChild(promemoria);
		}
	}

	public void creaIngredienti(Element rootElement, Document doc,
			MyRicetta ricetta) {
		ingredienti = doc.createElement("ListaIngredienti");
		rootElement.appendChild(ingredienti);

		for (int j = 0; j < ricetta.numIngredienti(); j++) {
			Element ingrediente = doc.createElement("Ingrediente");
			Attr tipo = doc.createAttribute("tipo");
			tipo.setValue(ricetta.getIngrediente(j).getTipo());
			ingrediente.setAttributeNode(tipo);
			if (ricetta.getIngrediente(j).getMisura() != null) {
				Attr misura = doc.createAttribute("misura");
				misura.setValue(ricetta.getIngrediente(j).getMisura());
				ingrediente.setAttributeNode(misura);
			}
			if (ricetta.getIngrediente(j).getQuantita() != null) {
				Attr quantita = doc.createAttribute("quantita");
				quantita.setValue(ricetta.getIngrediente(j).getQuantita()
						.toString());
				ingrediente.setAttributeNode(quantita);
			}

			ingredienti.appendChild(ingrediente);
		}
	}

	public void creaProcedimento(Element parent, Document doc, MyRicetta ricetta) {
		Element procedimento = doc.createElement("Procedimento");
		procedimento.appendChild(doc.createTextNode(ricetta.getPreparazione()));
		parent.appendChild(procedimento);

	}

	public void scriviContenuto(Document doc, String nome) {
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			/* 
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"cuciniamo.dtd");
			*/
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath + nome	+ ".xml"));
			transformer.transform(source, result);

		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	// GETTER AND SETTER //

	public void setPrimaRicetta(Boolean primaRicetta) {
		this.primaRicetta = primaRicetta;

	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Element getRootElement() {
		return rootElement;
	}

	public void setRootElement(Element rootElement) {
		this.rootElement = rootElement;
	}

	public Boolean getPrimaRicetta() {
		return primaRicetta;
	}

	public MyRicetta getMyRicetta() {
		return myRicetta;
	}

	public void setMyRicetta(MyRicetta myRicetta) {
		this.myRicetta = myRicetta;
	}

	public MyOpzioni getMyOpzioni() {
		return myOpzioni;
	}

	public void setMyOpzioni(MyOpzioni myOpzioni) {
		this.myOpzioni = myOpzioni;
	}

}
