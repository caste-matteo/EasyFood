//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * All void visitors must implement this interface.
 */

public interface Visitor {

   //
   // void Auto class visitors
   //

   public void visit(NodeList n);
   public void visit(NodeListOptional n);
   public void visit(NodeOptional n);
   public void visit(NodeSequence n);
   public void visit(NodeToken n);

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> NomeEsteso()
    * f1 -> <SEPARATORE>
    * f2 -> ( Opzioni() <SEPARATORE> )*
    * f3 -> ( Sottoricetta() <SEPARATORE> )*
    * f4 -> <_INGREDIENTI>
    * f5 -> ListaIngredienti()
    * f6 -> <SEPARATORE>
    * f7 -> <_PREPARAZIONE>
    * f8 -> Preparazione()
    * f9 -> ( <FINENOTA> | <EOF> )
    * f10 -> ( <FINE> | <EOF> )
    */
   public void visit(Ricetta n);

   /**
    * f0 -> ( <STRING> )+
    */
   public void visit(NomeEsteso n);

   /**
    * f0 -> ( <_SALVA> NomeSalvataggio() | <_AUTORE> Autore() | <_CATEGORIA> TipoCategoria() | <_DIFFICOLTA> ValoreDifficolta() | <_TEMPO> Tempo() | <_PRIVATA> Privata() | <_STRUMENTI> ListaStrumenti() | <_FESTIVITA> Festivita() | <_PROMEMORIA> Promemoria() )
    */
   public void visit(Opzioni n);

   /**
    * f0 -> <STRING>
    */
   public void visit(NomeSalvataggio n);

   /**
    * f0 -> <STRING>
    */
   public void visit(Autore n);

   /**
    * f0 -> CategoriaInf()
    * f1 -> [ "(" CategoriaSup() ")" ]
    */
   public void visit(TipoCategoria n);

   /**
    * f0 -> ( <STRING> )+
    */
   public void visit(CategoriaInf n);

   /**
    * f0 -> ( <STRING> )+
    */
   public void visit(CategoriaSup n);

   /**
    * f0 -> <NUM>
    */
   public void visit(ValoreDifficolta n);

   /**
    * f0 -> TempoSup()
    * f1 -> [ [ <STRING> ] TempoInf() ]
    */
   public void visit(Tempo n);

   /**
    * f0 -> Durata()
    * f1 -> UnitaTempo()
    */
   public void visit(TempoSup n);

   /**
    * f0 -> Durata()
    * f1 -> UnitaTempo()
    */
   public void visit(TempoInf n);

   /**
    * f0 -> <NUM>
    */
   public void visit(Durata n);

   /**
    * f0 -> <STRING>
    */
   public void visit(UnitaTempo n);

   /**
    * f0 -> <STRING>
    */
   public void visit(Privata n);

   /**
    * f0 -> Strumento()
    * f1 -> ( "," Strumento() )*
    */
   public void visit(ListaStrumenti n);

   /**
    * f0 -> ( <STRING> )+
    */
   public void visit(Strumento n);

   /**
    * f0 -> ( <STRING> )+
    */
   public void visit(Festivita n);

   /**
    * f0 -> ( <STRING> | <NUM> )+
    */
   public void visit(Promemoria n);

   /**
    * f0 -> ( <_PREPARA> Ricetta() | <_APRI> Apri() )
    */
   public void visit(Sottoricetta n);

   /**
    * f0 -> NomeSottoricetta()
    * f1 -> [ "(" AutoreSottoricetta() ")" ]
    */
   public void visit(Apri n);

   /**
    * f0 -> <STRING>
    */
   public void visit(NomeSottoricetta n);

   /**
    * f0 -> <STRING>
    */
   public void visit(AutoreSottoricetta n);

   /**
    * f0 -> Ingrediente()
    * f1 -> ( "," Ingrediente() )*
    */
   public void visit(ListaIngredienti n);

   /**
    * f0 -> QuantitaIngrediente() TipoIngrediente()
    *       | TipoIngrediente() [ QuantitaIngrediente() ]
    */
   public void visit(Ingrediente n);

   /**
    * f0 -> ( <STRING> )+
    */
   public void visit(TipoIngrediente n);

   /**
    * f0 -> <QUANTITA>
    *       | <NUM>
    */
   public void visit(QuantitaIngrediente n);

   /**
    * f0 -> ( Nota() | <_OPENLINK> Link() [ <_STARTDESC> DescrizioneLink() ] <_CLOSELINK> )+
    */
   public void visit(Preparazione n);

   /**
    * f0 -> <NOTA>
    */
   public void visit(Nota n);

   /**
    * f0 -> <ADVSTRING>
    */
   public void visit(Link n);

   /**
    * f0 -> <ADVSTRING>
    */
   public void visit(DescrizioneLink n);

}

