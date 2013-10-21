/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuidaJFrame.java
 *
 * Created on 12-nov-2011, 9.47.01
 */
package visual;

/**
 *
 * @author pierpytom
 */
public class GuidaJFrame extends javax.swing.JFrame {

    /** Creates new form GuidaJFrame */
    public GuidaJFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        htmlArea = new javax.swing.JTextPane();

        setTitle("Guida di cuciniamo");
        setLocation(new java.awt.Point(100, 100));
        setLocationByPlatform(true);

        htmlArea.setContentType("text/html");
        htmlArea.setEditable(false);
        htmlArea.setText("<html>\n    <head>\n        <title></title>\n    </head>\n    <body>\n        <div>\n            <h1>Guida all'utilizzo di <strong>Cuciniamo: parser di ricette</strong></h1>\n            \n            <ul>\n                <li><a href=\"#intro\">Introduzione</a></li>\n                <li><a href=\"#nome\">Nome della ricetta</a></li>\n                <li><a href=\"#opzioni\">Informazioni aggiuntive</a></li>\n                <li><a href=\"#sottoricette\">Inserimento di sottoricette</a></li>\n                <li><a href=\"#ingredienti\">Ingredienti necessari e preparazione</a></li>\n                <li><a href=\"#auto\">Automatizzazioni fornite dal software</a></li>\n                <li><a href=\"#esempio\">Un esempio completo</a></li>\n            </ul>\n            \n            <a name=\"intro\"></a><p>Il software permette di analizzare ricette scritte nel linguaggio <strong>cuciniamo</strong>\n             e di ottenere una serie di informazioni utili alla preparazione di una ricetta.<br />\n             Una ricetta scritta in tale linguaggio è strutturata in quattro sezioni:</p>\n            <ol>\n                <li>Nome della ricetta</li>\n                <li>Informazioni aggiuntive (opzionali)</li>\n                <li>Inserimento di sottoricette (opzionale)</li>\n                <li>Ingredienti necessari e preparazione</li>\n            </ol>\n            <p>Ogni sezione deve essere separata da uno o più ritorni a capo, inoltre deve essere segnalata la fine \n            della preparazione con il carattere <b>_</b> (underscore).<br/>\n            E' possibile utilizzare i commenti nella stesura della ricetta attraverso l'apertura <b>(*</b> e la \n            chiusura <b>*)</b>, questi verranno ignorati dal parser.</p>\n            \n            <a name=\"nome\"></a><h2>Nome della ricetta</h2>\n            <p>Ogni ricetta deve iniziare con il nome che gli verrà assegnato, &egrave; concesso \n            l'esclusivo utilizzo di lettere (maiuscole e minuscole) e spazi.</p>\n            \n            <a name=\"opzioni\"></a><h2>Informazioni aggiuntive</h2>\n            <p>E' quindi possibile passare alla sezione di informazioni aggiuntive, queste permettono di avere \n            un maggiore dettaglio della ricetta ed inoltre consentono al sotware di eseguire stime pi&ugrave;\n            accurate.<br />\n            Un'informazione aggiuntiva &egrave; data da una parola chiave seguita da <b>:</b> e dal valore che \n            si vuole impostare, il significato del valore cambia in base alla parola chiave.<br />\n            E' possibile passare un'informazione aggiuntiva una sola volta, in caso venga passata pi&ugrave;\n            volte la stessa informazione verr&agrave; generato un warning e solo la prima avr&agrave; effetto.<br />\n            Di seguita la lista delle informazioni che &egrave; possibile dichiarare, in grassetto le parole \n            chiave ed in corsivo i parametri da passare:</p>\n            <ul>\n                <li><u><b>Salva:</b> <i>nomesalvataggio</i></u>: nome con cui si desidera che la ricetta venga salvata sul \n                    database remoto, se assente la ricetta non verr&agrave salvata. Per il nome sono concesse \n                    esclusivamente lettere (maiuscole o minuscole) e non spazi.</li>\n                <li><u><b>Autore:</b> <i>nomeautore</i></u>: nickname dell'autore della ricetta, se assente non verr&agrave;\n                    assegnata a nessuno (analogamente al caso precedente non sono possibili spazi).</li>\n                <li><u><b>Categoria:</b> <i>categoria inferiore</i> <i><strike>(categoria superiore)</strike></i></u>: \n                    categoria di cui la ricetta fa parte, in caso di ambiguit&agrave; &egrave; possibile dichiarare \n                    esplicitamente la categoria superiore (Es: <i>Categoria: Torte (Dolci)</i>)</li>\n                <li><u><b>Difficolt&agrave;:</b> <i>valore da 1 a 10</i></u>: difficolt&agrave; della ricetta.</li>\n                <li><u><b>Tempo:</b> <i>tempo preparazione</i></u>: tempo di preparazione della ricetta, pu&ograve; \n                    essere espresso in ore e minuti ma non in quarti/mezzi ecc... (Es: <i>Tempo: 1 ora e 30 minuti</i>,\n                    <i>Tempo: 2 ore</i>, <i>Tempo: 200 minuti</i>, <u>non va bene</u> <i>Tempo: 1 ora e un quarto</i> o \n                    <i>Tempo: Un'ora e 1/2</i>).</li>\n                <li><u><b>Privata:</b> <i>si/no</i></u>: indica se una ricetta sar&agrave; visibile anche agli altri utenti.</li>\n                \n                <li><u><b>Strumenti:</b> <i>lista di strumenti separati da virgola</i></u>: strumenti necessari per una corretta\n                    preparazione della ricetta (in alternativa &egrave; possibile utilizzare la parola chiave <b>usa</b>).</li>\n                <li><u><b>Festivit&agrave;:</b> <i>nome festivit&agrave;</i></u>: festivit&agrave; a cui si vuole associare la ricetta \n                (Natale, Compleanno, San Valentino, ...)</li>\n            </ul>\n            <p><b>NB:</b> per aumentare la portabilit&agrave; tra sistemi diversi (mac, windows, linux, ...) &egrave; consigliabile \n                l'l'uso delle parole chiave senza accento (Es: <i>difficolt<b>a</b></i> anzich&eacute; <i>difficolt<b>&agrave;</b></i>), \n                questo perch&eacute; il cambio di sistema operativo comporta il cambio del set di caratteri.</p>\n            \n            <a name=\"sottoricette\"></a><h2>Inserimento di sottoricette</h2>\n            <p>Nonostante sia opzionale <u>&egrave vivamente consigliato</u> di dividere una ricetta in pi&ugrave; sottoricette (ovvero \n            in pi&ugrave; passi elementari), data la struttura del linguaggio infatti si potr&agrave; beneficiare del salvataggio separato \n            dei vari passi di una ricetta cos&igrave; da poterne facilmente beneficiare in futuro anche su altre ricette, inoltre l'aggiornamento \n            di una sottoricetta comporter&agrave; l'aggiornamento automatico di tutte le ricette che la sfruttano (se ad esempio si cambia \n            la preparazione del pan di spagna, automaticamente tutte le ricette di torte che ne fanno uso per la creazione verranno aggiornate).<br />\n            Per inserire una sottoricetta &egrave; sufficiente utilizzare la parola chiave <b>prepara:</b> seguita dal nome della sottoricetta, \n            dopo si proseguir&agrave; all'inserimento delle ultime tre sezioni come da abitudine. Per poter capire in quale punto una sottoricetta \n            finisca (anche perch&egrave; &egrave; possibile inserire altre sottoricette nelle sottoricette e cos&igrave; via) <u>&egrave; obbligatorio \n            utilizzare il simbolo di chiusura</u> <b>_</b> (underscore).\n            \n            <a name=\"ingredienti\"></a><h2>Ingredienti necessari e preparazione</h2>\n            Infine si pu&ograve; passare all'inserimento degli ingredienti necessari attravero l'uso della parola chiave <b>Ingredienti:</b> e delle \n            istruzioni per la preparazione attraverso la parola chiave <b>preparazione:</b> (o <b>procedimento:</b>).<br />\n            Per l'inserimento degli ingredienti &egrave; necessario inserirli uno alla volta separati da virgole, il sistema &egrave; in grado di \n            interpretare l'unit&agrave; di misura se inserita immediatamente dopo la quantit&agrave; senza lasciare spazi (e.g.: <code>40g di farina</code> \n            oppure <code>farina 40g</code> &egrave corretto, <code>40 g di farina</code> <b>no</b>!). E' possibile inserire come quantit&agrave; <i>qb</i> \n            oppure <i>q.b.</i> per dichiare piccole quantit&agrave; di un ingredienti, inoltre nell'inserimento di pi&ugrave; ingredienti con la stessa \n            unit&agrave; di misura &egrave; possibile non inserirte tale informazione dal secondo ingrediente in poi ricordandosi di usare la forma \n            <i>XX di qualcosa</i>(e.g.: <code>40g di farina, 30 di zucchero</code> aggiunger&agrave; <code>g</code> allo <code>zucchero</code>).<br />\n            Per quanto riguarda la <b>preparazione</b> &egrave; possibile scrivere qualsiasi cosa, inoltre tra parentesi quadre (<b>[</b> e <b>]</b>) \n            &egrave; possibile importare un file immagine dal disco, se si vuole inserire una descrizione basta inserire <b>;</b> dopo il percorso del file \n            e scrivere ci&ograve; che si preferisce prima di <b>]</b>, un esempio per sistemi unix pu&ograve; essere \n            <code><b>[</b>/percorso/alla/tua/immagine.jpg<b>;</b>descrizione che preferisci<b>]</b></code>\n            \n            \n            <a name=\"auto\"></a><h2>Automatizzazioni fornite dal software</h2>\n            <p>Il software, in base anche alle informazioni opzionali (se inserite), &egrave; in grado di eseguire le seguenti operazioni:</p>\n            <ul>\n                <li>Salvare automaticamente la ricetta sul db con il nome utente fornito</li>\n                <li>Trovare l'id della categoria inserita, in caso nessuna categoria sia indicata inserir&agrave; la ricetta in nella sottocategoria <i>Altro</i> \n                    della categoria <i>Altro</i>.</li>\n                <li>Se si forniranno le difficolt&agrave; di tutte le ricette &egrave; in grado di stabilirne quella complessiva (la massima tra tutte le sottoricette).</li>\n                <li>E' in grado di calcolare il tempo in minuti e quindi fare statistiche sul tempo complessivo prendendo come tempo minimo il massimo tra tutte le \n                ricette (caso fortunato in cui tutte le operazioni possono essere fatte in parallelo) e come tempo massimo la somma di tutti i tempi (caso peggiore in cui \n                per andare avanti bisogna sempre aver finito la sottoricetta precedente), inoltre il sistema avvertir&agrave; nel caso una sottoricetta (ad un qualsiasi livello \n                di profondit&agrave;) prender&agrave; pi&ugrave; tempo della ricetta principale.</li>\n                <li>Salvare la ricetta sul database in maniera privata, in modo tale da non renderla disponibile agli altri utenti.</li>\n                <li>Listare gli strumenti necessari alla sola ricetta, fare la summa listare gli strumenti necessari per il completamento della ricetta passando per tutte le \n                sottoricette, listare esclusivamente gli strumenti aggiuntivi necessari per il completamento delle sottoricette.</li>\n                <li>Trovare l'id della festivit&agrave; inserita.</li>\n                <li>Listare la lista di ingredienti necessari alla sola ricetta (suppendo tutte le sottoricette gi&agrave; pronte) oppure tutti gli ingredienti necessari al \n                completamento sia della ricetta che di tutte le sottoricette.</li>\n                <li>Inserire i riferimenti alle immagini all'interno del procedimento di una ricetta.</li>\n                <li></li>\n            </ul>\n            <a name=\"esempio\"></a><h2>Un esempio completo</h2>\n            <pre></pre>\n            \n        </div>\n    </body>\n</html>");
        htmlArea.setCaretPosition(0);
        jScrollPane1.setViewportView(htmlArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane htmlArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}