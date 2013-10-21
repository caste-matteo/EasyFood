package visual;

import com.mxgraph.model.mxCell;
import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.ArrayList;
import java.util.Hashtable;
import mycore.MyGraphModel;
import mycore.MyIngrediente;

public class GraficoJFrame extends JFrame
{
	
	static int width = 100, widthoff=150, height = 25, heightoff=5, xseed = 20, yseed = 20;
	int w_tot, h_tot;

	public GraficoJFrame(MyGraphModel mygraph, ArrayList<MyIngrediente> ingredienti)
	{
		super("Grafico");
		
		if(mygraph != null) {
			w_tot = (width+widthoff)*(mygraph.maxDepth()-1);
			h_tot = (height+heightoff)*mygraph.maxBreadth();
		}
		// Se è null continua e disegna "nessuna ricetta inserita"
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();

		inserisciStili(graph.getStylesheet());

		try
		{
			// Disegna in cascata tutti i nodi:
			if (mygraph != null)
				disegnaVertice(graph, parent, mygraph);
			else
				graph.insertVertex(parent, null, "Nessuna ricetta inserita", xseed, yseed, width*2, height*2, "shape");
			
			// Stampa in basso tutti gli ingredienti usati:
			if(ingredienti != null) {

				int offset_minimo = 10;
				int larghezza_disponibile = (mygraph.maxDepth())*(width+widthoff)-widthoff;
				int colonne = larghezza_disponibile/(width+offset_minimo);
				int offset_totale = larghezza_disponibile-colonne*width;
				int offset = offset_totale / (colonne-1);

				int x = xseed, y = (mygraph.maxBreadth()+1)*(height+heightoff);
				graph.insertVertex(parent, null, "Ingredienti complessivi necessari", x-10, y-10+(height+heightoff), 
						larghezza_disponibile+20, 
						(height+heightoff)*((int)(ingredienti.size()/(colonne))+(ingredienti.size()%colonne == 0 ? 0 : 1))+30,
						"surrounder");
				for(int i = 0; i<ingredienti.size(); i++) {
					// nuova riga
					if(i%colonne == 0) {
						x = xseed;
						y += height+heightoff;
					}
					graph.insertVertex(parent, null, ingredienti.get(i), x, y, width, height, "subshape2");
					x += width + offset;
				}
			} // if(ingredienti != null)

		}
		catch(Exception e) {}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graph.setCellsLocked(true);
		//graph.setAllowDanglingEdges(false);
		graphComponent.setConnectable(false);
		getContentPane().add(graphComponent);
	}
	
	private Object disegnaVertice(mxGraph graph, Object parent, MyGraphModel mgm) {
		
		if(mgm.isRoot()) {
			graph.insertVertex(parent, null, "",
					/* x: */ node_x(mgm)-2,
					/* y: */ node_y(mgm)-2, 
					width+4, height*mgm.nodeWeight()+4, "surrounder");					
		}
		
		Object v1 = graph.insertVertex(parent, null, mgm.currentLabel(), 
					/* x: */ node_x(mgm),
					/* y: */ node_y(mgm), 
					width, height*mgm.nodeWeight(), "shape");		
		
		for(int i = 0; i<mgm.getSubnodeCount(); i++) {
			Object v2 = disegnaVertice(graph, parent, mgm.getSubnode(i));
			graph.insertEdge(parent, null, "", v2, v1, "edge");
		}
		
		for(int i = 0; i<mgm.getLeafCount(); i++) {
			Object v2 = graph.insertVertex(parent, null, mgm.getLeaf(i), 
					/* x: */ leaf_x(mgm.getLeaf(i)), 
					/* y: */ leaf_y(mgm.getLeaf(i)), 
					width, height*mgm.leafWeight(), "subshape");
			graph.insertEdge(parent, null, "", v2, v1, "edge"); //instanceof mxCell			
		}
		
		if(mgm.getMeta() != null)
			graph.insertVertex(parent, null, mgm.getMeta(), node_x(mgm)-80, node_y(mgm)-20, 80, 50, "cloud");
		
		return v1;
	}
	
	private int leaf_x(MyGraphModel gm) {
		return w_tot-((width+widthoff)*(gm.currentDepth()))+xseed;
	}
	private int leaf_y(MyGraphModel gm) {
		return ((height+heightoff))*(gm.offsetBreadth())+yseed;
	}

	private int node_x(MyGraphModel gm) {
		return w_tot-((width+widthoff)*(gm.currentDepth()))+xseed;
	}
	private int node_y(MyGraphModel gm) {
		return (height+heightoff)*(int)(gm.currentBreadth()/2+gm.offsetBreadth()-0.5)+yseed;
	}
	
	private static void inserisciStili(mxStylesheet stylesheet) {
	
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
		style.put(mxConstants.STYLE_OPACITY, 50);
		style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		style.put(mxConstants.STYLE_GRADIENTCOLOR, "#0000CC");
		style.put(mxConstants.STYLE_ROUNDED, true);
		style.put(mxConstants.STYLE_WHITE_SPACE, "wrap");
		stylesheet.putCellStyle("shape", style);

		Hashtable<String, Object> stylebis = new Hashtable<String, Object>();
		stylebis.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
		stylebis.put(mxConstants.STYLE_OPACITY, 50);
		stylebis.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		stylebis.put(mxConstants.STYLE_GRADIENTCOLOR, "#00CC00");
		stylebis.put(mxConstants.STYLE_ROUNDED, true);
		stylebis.put(mxConstants.STYLE_WHITE_SPACE, "wrap");
		stylebis.put(mxConstants.STYLE_FONTSIZE, 9);
		stylesheet.putCellStyle("subshape", stylebis);
		
		Hashtable<String, Object> stylebis2 = new Hashtable<String, Object>();
		stylebis2.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
		stylebis2.put(mxConstants.STYLE_OPACITY, 50);
		stylebis2.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		stylebis2.put(mxConstants.STYLE_GRADIENTCOLOR, "#880077");
		stylebis2.put(mxConstants.STYLE_ROUNDED, true);
		stylebis2.put(mxConstants.STYLE_WHITE_SPACE, "wrap");
		stylebis2.put(mxConstants.STYLE_FONTSIZE, 9);		
		stylesheet.putCellStyle("subshape2", stylebis2);

		Hashtable<String, Object> edgestyle = new Hashtable<String, Object>();
		edgestyle.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ELBOW);
		edgestyle.put(mxConstants.STYLE_ROUNDED, true);
		edgestyle.put(mxConstants.STYLE_ELBOW, mxConstants.ELBOW_HORIZONTAL);
		edgestyle.put(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_LEFT);
		stylesheet.putCellStyle("edge", edgestyle);

		Hashtable<String, Object> cloud = new Hashtable<String, Object>();
		cloud.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CLOUD);
		cloud.put(mxConstants.STYLE_OPACITY, 50);
		cloud.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		cloud.put(mxConstants.STYLE_GRADIENTCOLOR, "#CC0000");
		cloud.put(mxConstants.STYLE_ROUNDED, true);
		cloud.put(mxConstants.STYLE_WHITE_SPACE, "wrap");
		cloud.put(mxConstants.STYLE_FONTSIZE, 9);
		stylesheet.putCellStyle("cloud", cloud);
		
		Hashtable<String, Object> surrounder = new Hashtable<String, Object>();
		surrounder.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
		surrounder.put(mxConstants.STYLE_OPACITY, 20);
		surrounder.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		surrounder.put(mxConstants.STYLE_GRADIENTCOLOR, "#00AA00");
		surrounder.put(mxConstants.STYLE_ROUNDED, true);
		surrounder.put(mxConstants.STYLE_FONTSIZE, 9);
		surrounder.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
		stylesheet.putCellStyle("surrounder", surrounder);
		
	}

}
