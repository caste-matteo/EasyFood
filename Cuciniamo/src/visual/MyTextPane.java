/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visual;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
/**
 *
 * @author pierpytom
 */   

@SuppressWarnings("serial")
public class MyTextPane extends JTextPane implements KeyListener {

    Boolean highliterActive = true;
	Boolean customBackground = false;
    
    Highlighter evidenziatore;
    Highlighter.HighlightPainter effetto1, effetto2, effetto3;
    String tokens1[] = {"salva:","autore:","categoria:","difficolta:","<_difficolta>",
                        "difficoltà:","tempo:","privata:","strumenti:","<_strumenti>",
                        "usa:", "festività:", "festivita:", "<_festivita>", "promemoria:"};
    String tokens2[] = {"prepara:","apri:"};
    String tokens3[] = {"ingredienti:", "procedimento:","preparazione:","<_preparazione>"};
        
    public MyTextPane(){
        super();
		
        evidenziatore = getHighlighter();
        effetto1 = new DefaultHighlighter.DefaultHighlightPainter(new Color(0,  255,255));
        effetto2 = new DefaultHighlighter.DefaultHighlightPainter(new Color(200,200,255));
        effetto3 = new DefaultHighlighter.DefaultHighlightPainter(new Color(255,190,190));
        addKeyListener(this);
		
        if(customBackground) {
			setOpaque(false);
			// this is needed if using Nimbus L&F - see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6687960
			setBackground(new Color(0,0,0,0));
		}
		
    }

	
	@Override
	protected void paintComponent(Graphics g) {

		if(customBackground){
			// set background green - but can draw image here too
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, getWidth(), getHeight());
			// uncomment the following to draw an image
			ImageIcon img = new ImageIcon(getClass().getResource("/visual/img/background.jpg"));
			g.drawImage(img.getImage(), 0, 0, this);
		}
		super.paintComponent(g);
	}
    
    public Boolean isHiglighterActive() {
        return highliterActive;
    }
    
    public void setHiglighterActive(Boolean ha) {
        highliterActive = ha;
        if(highliterActive)
            aggiorna();
        else
            evidenziatore.removeAllHighlights();
    }
	
	public Boolean isCustomBackground() {
		return customBackground;
	}
	
	public void setCustomBackground(Boolean bool) {
		customBackground = true;
		if(customBackground) {
			setOpaque(false);
			// this is needed if using Nimbus L&F - see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6687960
			setBackground(new Color(0,0,0,0));
		}
	}

    // Ascolto solo keytiped, se aggiornassi sempre non potrei selezionare
    public void keyTyped(KeyEvent ke)   { if(highliterActive) aggiorna(); }
    public void keyReleased(KeyEvent ke){}
    public void keyPressed(KeyEvent ke) {}
    
    public void aggiorna()
    {
        evidenziatore.removeAllHighlights();
        try
        {
            for(String word : tokens1)
                for(int v,i=0; (v=getText().toLowerCase().indexOf(word,i)) >= i; i+=word.length())
                    evidenziatore.addHighlight(v, v+word.length(), effetto1);
            for(String word : tokens2)
                for(int v,i=0; (v=getText().toLowerCase().indexOf(word,i)) >= i; i+=word.length())
                    evidenziatore.addHighlight(v, v+word.length(), effetto2);
            for(String word : tokens3)
                for(int v,i=0; (v=getText().toLowerCase().indexOf(word,i)) >= i; i+=word.length())
                    evidenziatore.addHighlight(v, v+word.length(), effetto3);
        }
        catch(Exception e) {}
    }
    
    public void appendColored(String s, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
            StyleConstants.Foreground, c);

        int len = getDocument().getLength(); // same value as getText().length();
        setCaretPosition(len); // place caret at the end (with no selection)
        setCharacterAttributes(aset, false);
        replaceSelection(s); // there is no selection, so inserts at caret
    }
    
    public void clear(){
        setText("");
    }
    
    public void setText(String s, Color c) {
        clear();
        appendColored(s, c);
        aggiorna();
    }
    
    @Override
    public void setText(String s){
        super.setText(s);
        aggiorna();
    }
    

    public void insertAfterCaret(String text) {
        try {
            getDocument().insertString(getCaretPosition(), text, null);
        } catch (BadLocationException ex) { }
        aggiorna();
    }

    
}



