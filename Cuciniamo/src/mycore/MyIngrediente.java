package mycore;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("rawtypes")
public class MyIngrediente implements TreeModel, Comparable, MyGraphModel, Serializable {

	private MyRicetta parent, root;

	String tipo;
	Integer quantita;	// 0 significa qb, se non si sa è null
	String misura;

	/**
	 * Nuovo ingrediente non appartenente a nessuna ricetta.
	 */
	public MyIngrediente() { }

	/**
	 * Nuovo ingrediente appartenente ad una ricetta
	 * @param parent
	 * @param root
	 */
	public MyIngrediente (MyRicetta parent, MyRicetta root) {
		this.parent = parent;
		this.root   = root;
	}

	public String toString() {
		String result = tipo;
		if (quantita != null) {
			if (quantita == 0)
				result += " qb";
			else {
				result += " ("+quantita;
				result += misura != null ? misura+")" : ")";
			}
		}
		
		return result;
	}

	public MyIngrediente getCopia() {
		MyIngrediente copia = new MyIngrediente();
		copia.tipo = tipo;
		copia.quantita = quantita;
		copia.misura = misura;
		return copia;
	}

	public String getAdvancedInfo() {
		String result = "Ingrediente selezionato: " + tipo+".";
		if(quantita != null)
			if (quantita==0)
				result += "\nNecessario solo q.b.";
			else
				result += "\nQuantità necessaria: "+quantita+(misura != null ? misura : "")+".";			
		result += "\n\n";
		result += "Ingredienti necessari per la sola ricetta: ";
		for(int i=0; i<parent.numIngredienti(); i++) 
			result += parent.getIngrediente(i).toString() + ", ";
		result = result.substring(0, result.length()-2);
		result += "\n\n";
		result += "Ingredienti necessari per la ricetta e tutte le sue sottoricette: ";
		ArrayList<MyIngrediente> tot = parent.getTotalitaIngredienti();
		for (MyIngrediente temp : tot)
			result += temp.toString()+", ";
		result = result.substring(0, result.length()-2);
		return result;
	}


	//------------------------------------------------------------------------------//
	//------------------------------ GET & SET -------------------------------------//
	//------------------------------------------------------------------------------//

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public String getMisura() {
		return misura;
	}

	public void setMisura(String misura) {
		this.misura = misura;
	}
		
	
	//------------------------------------------------------------------------------//
	//---------------------------- MY GRAPH MODEL ----------------------------------//
	//------------------------------------------------------------------------------//	
	
	@Override
	public int currentDepth() {
		if (parent == null) 
			return 0;
		return parent.currentDepth()+1;
	}
	
	@Override
	public int maxDepth() {
		return root.maxDepth();		
	}
	
	@Override
	public int maxBreadth() {
		return root.currentBreadth();
	}
	
	@Override
	public int currentBreadth() {
		return leafWeight();
	}
	
	@Override
	public int offsetBreadth() {
		
		int leafbreadth = 0;
		for(int i=0; i<parent.numIngredienti(); i++) {
			if(parent.getIngrediente(i) == this)
				break;
			leafbreadth += leafWeight();
		}
		
		int nodebreadth = 0;
		for(int i=0; i<parent.numSottoricette(); i++) {
			nodebreadth += parent.getSottoricetta(i).currentBreadth();
		}
		
		return parent.offsetBreadth()+nodebreadth+leafbreadth;
	}
	
	@Override
	public String currentLabel() {
		return toString();
	}
	
	@Override
	public MyGraphModel getSubnode(int index){
		return null;
	}

	@Override
	public int getSubnodeCount(){
		return 0;
	}
	
	@Override
	public MyGraphModel getLeaf(int index) {
		return null;
	}
	
	@Override
	public int getLeafCount() {
		return 0;
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public int leafWeight() {
		return root.leafWeight();
	}
	
	@Override
	public int nodeWeight() {
		return root.nodeWeight();
	}
	
	@Override
	public String getMeta() {
		return null;
	}
	
	@Override
	public boolean isRoot() {
		return false;
	}
		
		
	//------------------------------------------------------------------------------//
	//------------------------------ TREE MODEL ------------------------------------//
	//------------------------------------------------------------------------------//

	@Override
	public Object getChild(Object parent, int index) {
		return root.getChild(parent, index);
	}

	@Override
	public int getChildCount(Object parent) {
		return root.getChildCount(parent);
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return root.getIndexOfChild(parent, child);
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public boolean isLeaf(Object node) {
		return root.isLeaf(node);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
	}


	//------------------------------------------------------------------------------//
	//------------------------------ COMPARABLE ------------------------------------//
	//------------------------------------------------------------------------------//

	@Override
	public int compareTo(Object o) {
		if (o instanceof MyIngrediente)
			return getTipo().compareTo(((MyIngrediente)o).getTipo());
		return 0;
	}


}
