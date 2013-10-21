package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import parser.*;
import mycore.*;
import syntaxtree.*;
import xml.CreaXML;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CuciniamoJFrame.java
 *
 * Created on 7-nov-2011, 16.12.18
 */
/**
 *
 * @author pierpytom
 */
@SuppressWarnings("serial")
public class CuciniamoJFrame extends javax.swing.JFrame {

    MyRicetta ricetta;
	MyConnectionHelper connectionHelper;
    GuidaJFrame guida = new GuidaJFrame();
    InfoJFrame  info  = new InfoJFrame();
  
    /** Creates new form CuciniamoJFrame */
    public CuciniamoJFrame() {
    	//this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/visual/img/leaf.png"));
    	initComponents();
		connectionHelper = new MyConnectionHelper();
		textOutput.setText("Scrivi la tua ricetta!\n\n", Color.green);
		ArrayList<ColoredString> result = connectionHelper.getOutput();
		for(ColoredString temp : result)
			textOutput.appendColored(temp.getString()+"\n", temp.getColor());
		textOutput.setCaretPosition(0);

	}
    
    
    private DefaultTreeCellRenderer getTreeCellRenderer() {
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(new ImageIcon(getClass().getResource("/visual/img/leaf.png")));
        renderer.setOpenIcon(new ImageIcon(getClass().getResource("/visual/img/open.png")));
        renderer.setClosedIcon(new ImageIcon(getClass().getResource("/visual/img/close.png")));
        return renderer;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        splitPane1 = new javax.swing.JSplitPane();
        splitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        treeRicetta = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textSource = new visual.MyEditorPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textOutput = new visual.MyTextPane();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        buttonApri = new javax.swing.JButton();
        buttonSalva = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonCompila = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        buttonGuida = new javax.swing.JButton();
        buttonInfo = new javax.swing.JButton();
        buttonEsci = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuApri = new javax.swing.JMenuItem();
        menuSalva = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuRicetta = new javax.swing.JMenu();
        menuCompila = new javax.swing.JMenuItem();
        Invia = new javax.swing.JMenuItem();
        menuVisualizza = new javax.swing.JMenu();
        menuGrafico = new javax.swing.JMenuItem();
        menuInserisci = new javax.swing.JMenu();
        menuCommento = new javax.swing.JMenuItem();
        menuToken = new javax.swing.JMenu();
        menuTokenSalva = new javax.swing.JMenuItem();
        menuTokenAutore = new javax.swing.JMenuItem();
        menuTokenCategoria = new javax.swing.JMenuItem();
        menuTokenDifficolta = new javax.swing.JMenuItem();
        menuTokenTempo = new javax.swing.JMenuItem();
        menuTokenPrivata = new javax.swing.JMenuItem();
        menuTokenStrumenti = new javax.swing.JMenuItem();
        menuTokenFestivita = new javax.swing.JMenuItem();
        menuTokenPrepara = new javax.swing.JMenuItem();
        menuTokenApri = new javax.swing.JMenuItem();
        menuTokenIngredienti = new javax.swing.JMenuItem();
        menuTokenPreparazione = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuSottoricettaFile = new javax.swing.JMenuItem();
        menuSottoricettaDB = new javax.swing.JMenuItem();
        menuImmagine = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuInfo = new javax.swing.JMenuItem();
        menuGuida = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("cuciniamo: parser di ricette");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/visual/img/guida.png")));
        setLocation(new java.awt.Point(150, 150));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        splitPane1.setDividerLocation(300);
        splitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane1.setResizeWeight(0.8);
        splitPane1.setOneTouchExpandable(true);

        splitPane2.setDividerLocation(440);
        splitPane2.setResizeWeight(0.9);
        splitPane2.setOneTouchExpandable(true);

        treeRicetta.setFont(new java.awt.Font("Lucida Grande", 0, 14));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Nessuna ricetta inserita");
        treeRicetta.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeRicetta.setCellRenderer(getTreeCellRenderer());
        treeRicetta.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeRicettaValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(treeRicetta);

        splitPane2.setRightComponent(jScrollPane3);

        jScrollPane2.setViewportView(textSource);
        TextLineNumber tln = new TextLineNumber(textSource);
        jScrollPane2.setRowHeaderView( tln );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
        );

        splitPane2.setLeftComponent(jPanel1);

        splitPane1.setLeftComponent(splitPane2);

        textOutput.setToolTipText("Output dell'elaborazione");
        textOutput.setCustomBackground(java.lang.Boolean.TRUE);
        textOutput.setFocusable(false);
        textOutput.setHiglighterActive(java.lang.Boolean.FALSE);
        jScrollPane1.setViewportView(textOutput);

        splitPane1.setRightComponent(jScrollPane1);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator3);

        buttonApri.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        buttonApri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/img/apri.png"))); // NOI18N
        buttonApri.setText("Apri");
        buttonApri.setToolTipText("apri una ricetta salvata su disco");
        buttonApri.setFocusable(false);
        buttonApri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonApri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApriActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonApri);

        buttonSalva.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        buttonSalva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/img/salva.png"))); // NOI18N
        buttonSalva.setText("Salva");
        buttonSalva.setToolTipText("salva il sorgente della ricetta su disco");
        buttonSalva.setFocusable(false);
        buttonSalva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonSalva.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvaActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonSalva);
        jToolBar1.add(jSeparator1);

        buttonCompila.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        buttonCompila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/img/compila.png"))); // NOI18N
        buttonCompila.setText("Compila");
        buttonCompila.setToolTipText("compila la ricetta inserita");
        buttonCompila.setFocusable(false);
        buttonCompila.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCompila.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonCompila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCompilaActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonCompila);
        jToolBar1.add(jSeparator2);

        buttonGuida.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        buttonGuida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/img/guida.png"))); // NOI18N
        buttonGuida.setText("Guida");
        buttonGuida.setFocusable(false);
        buttonGuida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonGuida.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonGuida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuidaActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonGuida);

        buttonInfo.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        buttonInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/img/info.png"))); // NOI18N
        buttonInfo.setText("Info");
        buttonInfo.setFocusable(false);
        buttonInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonInfo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInfoActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonInfo);

        buttonEsci.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        buttonEsci.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/img/esci.png"))); // NOI18N
        buttonEsci.setText("Esci");
        buttonEsci.setFocusable(false);
        buttonEsci.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonEsci.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buttonEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEsciActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonEsci);
        jToolBar1.add(jSeparator5);

        menuFile.setText("File");

        menuApri.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.META_MASK));
        menuApri.setText("Apri");
        menuApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuApriActionPerformed(evt);
            }
        });
        menuFile.add(menuApri);

        menuSalva.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
        menuSalva.setText("Salva");
        menuSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvaActionPerformed(evt);
            }
        });
        menuFile.add(menuSalva);

        jMenuItem1.setText("Stampa report");
        menuFile.add(jMenuItem1);

        jMenuBar1.add(menuFile);

        menuRicetta.setText("Ricetta");

        menuCompila.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
        menuCompila.setText("Compila");
        menuCompila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompilaActionPerformed(evt);
            }
        });
        menuRicetta.add(menuCompila);

        Invia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.META_MASK));
        Invia.setText("Invia");
        menuRicetta.add(Invia);

        jMenuBar1.add(menuRicetta);

        menuVisualizza.setText("Visualizza");

        menuGrafico.setText("Grafico");
        menuGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGraficoActionPerformed(evt);
            }
        });
        menuVisualizza.add(menuGrafico);

        jMenuBar1.add(menuVisualizza);

        menuInserisci.setText("Inserisci");

        menuCommento.setText("Commento");
        menuCommento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCommentoActionPerformed(evt);
            }
        });
        menuInserisci.add(menuCommento);

        menuToken.setText("Token");

        menuTokenSalva.setText("Salva:");
        menuTokenSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenSalvaActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenSalva);

        menuTokenAutore.setText("Autore:");
        menuTokenAutore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenAutoreActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenAutore);

        menuTokenCategoria.setText("Categoria:");
        menuTokenCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenCategoriaActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenCategoria);

        menuTokenDifficolta.setText("Difficoltà:");
        menuTokenDifficolta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenDifficoltaActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenDifficolta);

        menuTokenTempo.setText("Tempo:");
        menuTokenTempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenTempoActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenTempo);

        menuTokenPrivata.setText("Privata:");
        menuTokenPrivata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenPrivataActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenPrivata);

        menuTokenStrumenti.setText("Strumenti:");
        menuTokenStrumenti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenStrumentiActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenStrumenti);

        menuTokenFestivita.setText("Festività:");
        menuTokenFestivita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenFestivitaActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenFestivita);

        menuTokenPrepara.setText("Prepara:");
        menuTokenPrepara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenPreparaActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenPrepara);

        menuTokenApri.setText("Apri:");
        menuTokenApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenApriActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenApri);

        menuTokenIngredienti.setText("Ingredienti:");
        menuTokenIngredienti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenIngredientiActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenIngredienti);

        menuTokenPreparazione.setText("Preparazione:");
        menuTokenPreparazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTokenPreparazioneActionPerformed(evt);
            }
        });
        menuToken.add(menuTokenPreparazione);

        menuInserisci.add(menuToken);
        menuInserisci.add(jSeparator4);

        menuSottoricettaFile.setText("Sottoricetta da file");
        menuSottoricettaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSottoricettaFileActionPerformed(evt);
            }
        });
        menuInserisci.add(menuSottoricettaFile);

        menuSottoricettaDB.setText("Sottoricetta da db");
        menuInserisci.add(menuSottoricettaDB);

        menuImmagine.setText("Immagine");
        menuImmagine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuImmagineActionPerformed(evt);
            }
        });
        menuInserisci.add(menuImmagine);

        jMenuBar1.add(menuInserisci);

        jMenu1.setText("?");

        menuInfo.setText("Info");
        menuInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInfoActionPerformed(evt);
            }
        });
        jMenu1.add(menuInfo);

        menuGuida.setText("Guida");
        menuGuida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuidaActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuida);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(splitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(splitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuApriActionPerformed
        Apri(menuApri);
    }//GEN-LAST:event_menuApriActionPerformed

    private void buttonApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApriActionPerformed
        Apri(buttonApri);
    }//GEN-LAST:event_buttonApriActionPerformed

    private void buttonCompilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompilaActionPerformed
        Compila();
    }//GEN-LAST:event_buttonCompilaActionPerformed

    private void menuCompilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompilaActionPerformed
        Compila();
    }//GEN-LAST:event_menuCompilaActionPerformed

    private void menuTokenSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenSalvaActionPerformed
        textSource.insertAfterCaret("Salva: ");
    }//GEN-LAST:event_menuTokenSalvaActionPerformed

    private void menuCommentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCommentoActionPerformed
        textSource.insertAfterCaret("(* commento qui *)");
        textSource.setCaretPosition(textSource.getCaretPosition()-15);
    }//GEN-LAST:event_menuCommentoActionPerformed

    private void treeRicettaValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeRicettaValueChanged

        Object node = treeRicetta.getLastSelectedPathComponent();

        if (node == null)    return;

        if (node instanceof MyIngrediente)
            textOutput.setText(((MyIngrediente)node).getAdvancedInfo(), Color.cyan);
        if (node instanceof MyRicetta)
            textOutput.setText(((MyRicetta)node).getAdvancedInfo(), Color.cyan);
        textOutput.setCaretPosition(0);

    }//GEN-LAST:event_treeRicettaValueChanged

    private void buttonEsciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEsciActionPerformed
        setVisible (false);
        dispose();
        System.exit(0);
    }//GEN-LAST:event_buttonEsciActionPerformed

    private void buttonInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInfoActionPerformed
		info.setVisible(true);
    }//GEN-LAST:event_buttonInfoActionPerformed

    private void buttonSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvaActionPerformed
        Salva(buttonSalva);
    }//GEN-LAST:event_buttonSalvaActionPerformed

    private void menuSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvaActionPerformed
        Salva(menuSalva);
    }//GEN-LAST:event_menuSalvaActionPerformed

    private void menuTokenAutoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenAutoreActionPerformed
        textSource.insertAfterCaret("Autore: ");
    }//GEN-LAST:event_menuTokenAutoreActionPerformed

    private void menuTokenCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenCategoriaActionPerformed
        textSource.insertAfterCaret("Categoria: ");
    }//GEN-LAST:event_menuTokenCategoriaActionPerformed

    private void menuTokenDifficoltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenDifficoltaActionPerformed
        textSource.insertAfterCaret("Difficolta: ");
    }//GEN-LAST:event_menuTokenDifficoltaActionPerformed

    private void menuTokenTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenTempoActionPerformed
        textSource.insertAfterCaret("Tempo: ");
    }//GEN-LAST:event_menuTokenTempoActionPerformed

    private void menuTokenPrivataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenPrivataActionPerformed
        textSource.insertAfterCaret("Privata: ");
    }//GEN-LAST:event_menuTokenPrivataActionPerformed

    private void menuTokenStrumentiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenStrumentiActionPerformed
        textSource.insertAfterCaret("Strumenti: ");
    }//GEN-LAST:event_menuTokenStrumentiActionPerformed

    private void menuTokenFestivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenFestivitaActionPerformed
        textSource.insertAfterCaret("Festivita: ");
    }//GEN-LAST:event_menuTokenFestivitaActionPerformed

    private void menuTokenPreparaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenPreparaActionPerformed
        textSource.insertAfterCaret("Prepara: ");
    }//GEN-LAST:event_menuTokenPreparaActionPerformed

    private void menuTokenApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenApriActionPerformed
        textSource.insertAfterCaret("Apri: ");
    }//GEN-LAST:event_menuTokenApriActionPerformed

    private void menuTokenIngredientiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenIngredientiActionPerformed
        textSource.insertAfterCaret("Ingredienti: ");
    }//GEN-LAST:event_menuTokenIngredientiActionPerformed

    private void menuTokenPreparazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTokenPreparazioneActionPerformed
        textSource.insertAfterCaret("Preparazione: ");
    }//GEN-LAST:event_menuTokenPreparazioneActionPerformed

    private void menuImmagineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuImmagineActionPerformed
             
        File file = null;
        Boolean fileok = false;
		String os = System.getProperty("os.name").toLowerCase();
        
        // Il file chooser swing per mac è pessimo, usa awt
        if (os.indexOf( "mac" ) >= 0) {
            final FileDialog fc = new FileDialog(this, "Scegli un'immagine", FileDialog.LOAD);
            fc.setVisible(true);
            if(fc.getFile() != null) {
                file = new File(fc.getDirectory()+ fc.getFile());
                fileok = true;
            }
        } else {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(menuImmagine);
            fileok = (returnVal == JFileChooser.APPROVE_OPTION);
            file = fc.getSelectedFile();
        }
        
        if(fileok)
        	textSource.insertAfterCaret("["+file.toString()+";descrizione qui]");
        
    }//GEN-LAST:event_menuImmagineActionPerformed

    private void menuSottoricettaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSottoricettaFileActionPerformed
                
        File file = null;
        Boolean fileok = false;
		String os = System.getProperty("os.name").toLowerCase();
        
        // Il file chooser swing per mac è pessimo, usa awt
        if (os.indexOf( "mac" ) >= 0) {
            final FileDialog fc = new FileDialog(this, "Seleziona il file da importare", FileDialog.LOAD);
			fc.setDirectory(System.getProperty("user.dir"));
            fc.setVisible(true);
            if(fc.getFile() != null) {
                file = new File(fc.getDirectory()+ fc.getFile());
                fileok = true;
            }
        } else {
            final JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnVal = fc.showOpenDialog(menuSottoricettaFile);
            fileok = (returnVal == JFileChooser.APPROVE_OPTION);
            file = fc.getSelectedFile();
        }
        
        if (fileok)    
			textSource.insertAfterCaret("Apri: "+file.getName());
        
    }//GEN-LAST:event_menuSottoricettaFileActionPerformed

    private void buttonGuidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuidaActionPerformed
        guida.setVisible(true);
    }//GEN-LAST:event_buttonGuidaActionPerformed

    private void menuInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInfoActionPerformed
        info.setVisible(true);
    }//GEN-LAST:event_menuInfoActionPerformed

    private void menuGuidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuidaActionPerformed
        guida.setVisible(true);
    }//GEN-LAST:event_menuGuidaActionPerformed

	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		connectionHelper.dispose();
	}//GEN-LAST:event_formWindowClosing

	private void menuGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGraficoActionPerformed
		GraficoJFrame frame = new GraficoJFrame(ricetta, ricetta != null ? ricetta.getTotalitaIngredienti() : null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}//GEN-LAST:event_menuGraficoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Invia;
    private javax.swing.JButton buttonApri;
    private javax.swing.JButton buttonCompila;
    private javax.swing.JButton buttonEsci;
    private javax.swing.JButton buttonGuida;
    private javax.swing.JButton buttonInfo;
    private javax.swing.JButton buttonSalva;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem menuApri;
    private javax.swing.JMenuItem menuCommento;
    private javax.swing.JMenuItem menuCompila;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuGrafico;
    private javax.swing.JMenuItem menuGuida;
    private javax.swing.JMenuItem menuImmagine;
    private javax.swing.JMenuItem menuInfo;
    private javax.swing.JMenu menuInserisci;
    private javax.swing.JMenu menuRicetta;
    private javax.swing.JMenuItem menuSalva;
    private javax.swing.JMenuItem menuSottoricettaDB;
    private javax.swing.JMenuItem menuSottoricettaFile;
    private javax.swing.JMenu menuToken;
    private javax.swing.JMenuItem menuTokenApri;
    private javax.swing.JMenuItem menuTokenAutore;
    private javax.swing.JMenuItem menuTokenCategoria;
    private javax.swing.JMenuItem menuTokenDifficolta;
    private javax.swing.JMenuItem menuTokenFestivita;
    private javax.swing.JMenuItem menuTokenIngredienti;
    private javax.swing.JMenuItem menuTokenPrepara;
    private javax.swing.JMenuItem menuTokenPreparazione;
    private javax.swing.JMenuItem menuTokenPrivata;
    private javax.swing.JMenuItem menuTokenSalva;
    private javax.swing.JMenuItem menuTokenStrumenti;
    private javax.swing.JMenuItem menuTokenTempo;
    private javax.swing.JMenu menuVisualizza;
    private javax.swing.JSplitPane splitPane1;
    private javax.swing.JSplitPane splitPane2;
    private visual.MyTextPane textOutput;
    private visual.MyEditorPane textSource;
    private javax.swing.JTree treeRicetta;
    // End of variables declaration//GEN-END:variables

    
    private void Apri(Component caller) {
       
        File file = null;
        Boolean fileok = false;
		String os = System.getProperty("os.name").toLowerCase();
        
        // Il file chooser swing per mac è pessimo, usa awt
        if (os.indexOf( "mac" ) >= 0) {
            final FileDialog fc = new FileDialog(this, "Scegli un file", FileDialog.LOAD);
            fc.setVisible(true);
            if(fc.getFile() != null) {
                file = new File(fc.getDirectory()+ fc.getFile());
                fileok = true;
            }
        } else {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(caller);
            fileok = (returnVal == JFileChooser.APPROVE_OPTION);
            file = fc.getSelectedFile();
        }
        
        if (fileok) {
            
            StringBuffer contents = new StringBuffer();
            BufferedReader reader = null;
            
            try {
                reader = new BufferedReader(new FileReader(file));
                String text = null;
                
                while((text=reader.readLine()) != null)
                    contents.append(text).append(System.getProperty("line.separator"));
                textSource.setText(contents.toString());
                textSource.setCaretPosition(0);
                textOutput.setText(file.getName()+" aperto correttamente", Color.green);
                
            } catch (FileNotFoundException e) {
                textOutput.setText("File non trovato.", Color.red);
            } catch (IOException e) {
                textOutput.setText("Eccezione I/O.", Color.red);
            } finally {
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    textOutput.setText("Eccezione I/O.", Color.red);
               }
                    
            }
        
        } // if (fileok)
    }

    
    private void Salva(Component caller) {

        if(textSource.getText().isEmpty()) {
            textOutput.setText("Impossibile salvare: nessuna ricetta scritta.", Color.red);
            return;
        }
        
        File file = null;
        Boolean fileok = false;
		String os = System.getProperty("os.name").toLowerCase();
        
        // Il file chooser swing per mac è pessimo, usa awt
        if (os.indexOf( "mac" ) >= 0) {
            final FileDialog fc = new FileDialog(this, "Scegli un file", FileDialog.SAVE);
            fc.setVisible(true);
            if(fc.getFile() != null) {
                file = new File(fc.getDirectory()+ fc.getFile());
                fileok = true;
            }
        } else {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(caller);
            fileok = (returnVal == JFileChooser.APPROVE_OPTION);
            file = fc.getSelectedFile();
        }
        
        if(fileok)
        try{
          // Create file 
          FileWriter fstream = new FileWriter(file);
          BufferedWriter out = new BufferedWriter(fstream);
          out.write(textSource.getText());
          //Close the output stream
          out.close();
          textOutput.setText("File '"+file.toString()+"' salvato correttamente.", Color.green);          
        }catch (Exception e) {
          textOutput.setText("Impossibile salvare: " + e.getMessage(), Color.red);
        }
 
    }
    
    
    RicettaParser parser = null;
    private void Compila() {

        if(textSource.getText().isEmpty()) {
            textOutput.setText("Non hai ancora scritto nulla!", Color.red);
            return;
        }
        try {
			if (parser == null)
	            parser = new RicettaParser(new StringReader(textSource.getText()));
			else
				parser.ReInit(new StringReader(textSource.getText()));
            Node root = parser.Ricetta();
            textOutput.setText("Ricetta compilata.\n\n", Color.green);
            MyDepthFirstVisitor myVisitor = new MyDepthFirstVisitor();
            root.accept(myVisitor);
			ArrayList<ColoredString> result = myVisitor.getOutput();
			textOutput.appendColored("Risultato della compilazione:\n", Color.cyan);
			for(ColoredString temp : result)
				textOutput.appendColored(temp.getString()+"\n", temp.getColor());
			textOutput.setCaretPosition(0);
            ricetta = myVisitor.getRicetta();
            treeRicetta.setModel(myVisitor.getRicetta());
            CreaXML prova = new CreaXML();
            prova.run(ricetta);
            
        }
        catch (ParseException e) {
			String details = "Errore nel parsing.\n";
			if(e.currentToken.image == null) {
				details += "Non esistono ancora token validi!";
			} else {
				// details += "Ultimo token valido: "+ (e.currentToken.image.contains("\n") ? e.currentToken.image : "accapo");
				details += "\nDetails: ";
				details += e.toString();
			}
			textOutput.setText(details, Color.red);
			textOutput.setCaretPosition(0);
			DefaultMutableTreeNode treeNodeError = new DefaultMutableTreeNode("Nessuna ricetta valida inserita");
			treeRicetta.setModel(new DefaultTreeModel(treeNodeError));
        }
		catch(TokenMgrError e) {
            textOutput.setText("Qualcosa è andato nella lettura dei token, probabilmente si tratta di un errore lessicale"
					+ " (hai controllato che il set di caratteri sia quello giusto?)", Color.red);
			DefaultMutableTreeNode treeNodeError = new DefaultMutableTreeNode("Nessuna ricetta valida inserita");
			treeRicetta.setModel(new DefaultTreeModel(treeNodeError));
		}
        catch(Exception e) {
            textOutput.setText("Qualcosa è andato storto...", Color.red);
			DefaultMutableTreeNode treeNodeError = new DefaultMutableTreeNode("Nessuna ricetta valida inserita");
			treeRicetta.setModel(new DefaultTreeModel(treeNodeError));
        }
    }
    

}