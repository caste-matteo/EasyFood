package primario;
/* Generated By:JavaCC: Do not edit this line. RicettaParser.java */
public class RicettaParser implements RicettaParserConstants {

//--------------------------------------------------------------------////------------------- SCOPO E OPZIONI --------------------------------////--------------------------------------------------------------------//
  final public void Ricetta() throws ParseException {
    NomeEsteso();
    jj_consume_token(SEPARATORE);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case _SALVA:
      case _AUTORE:
      case _CATEGORIA:
      case _DIFFICOLTA:
      case _TEMPO:
      case _PRIVATA:
      case _STRUMENTI:
      case _FESTIVITA:
      case _PROMEMORIA:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      Opzioni();
      jj_consume_token(SEPARATORE);
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case _PREPARA:
      case _APRI:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      Sottoricetta();
      jj_consume_token(SEPARATORE);
    }
    jj_consume_token(_INGREDIENTI);
    ListaIngredienti();
    jj_consume_token(SEPARATORE);
    jj_consume_token(_PREPARAZIONE);
    Preparazione();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FINENOTA:
      jj_consume_token(FINENOTA);
      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FINE:
      jj_consume_token(FINE);
      break;
    case 0:
      jj_consume_token(0);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void NomeEsteso() throws ParseException {
    label_3:
    while (true) {
      jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
    }
  }

  final public void Opzioni() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case _SALVA:
      jj_consume_token(_SALVA);
      NomeSalvataggio();
      break;
    case _AUTORE:
      jj_consume_token(_AUTORE);
      Autore();
      break;
    case _CATEGORIA:
      jj_consume_token(_CATEGORIA);
      TipoCategoria();
      break;
    case _DIFFICOLTA:
      jj_consume_token(_DIFFICOLTA);
      ValoreDifficolta();
      break;
    case _TEMPO:
      jj_consume_token(_TEMPO);
      Tempo();
      break;
    case _PRIVATA:
      jj_consume_token(_PRIVATA);
      Privata();
      break;
    case _STRUMENTI:
      jj_consume_token(_STRUMENTI);
      ListaStrumenti();
      break;
    case _FESTIVITA:
      jj_consume_token(_FESTIVITA);
      Festivita();
      break;
    case _PROMEMORIA:
      jj_consume_token(_PROMEMORIA);
      Promemoria();
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//--------------------------------------------------------------------////------------------ VALORI OPZIONALI --------------------------------////--------------------------------------------------------------------//  final public void NomeSalvataggio() throws ParseException {
    jj_consume_token(STRING);
  }

  final public void Autore() throws ParseException {
    jj_consume_token(STRING);
  }

  final public void TipoCategoria() throws ParseException {
    CategoriaInf();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 30:
      jj_consume_token(30);
      CategoriaSup();
      jj_consume_token(31);
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  final public void CategoriaInf() throws ParseException {
    label_4:
    while (true) {
      jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
    }
  }

  final public void CategoriaSup() throws ParseException {
    label_5:
    while (true) {
      jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_5;
      }
    }
  }

  final public void ValoreDifficolta() throws ParseException {
    jj_consume_token(NUM);
  }

  final public void Tempo() throws ParseException {
    TempoSup();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
    case STRING:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        jj_consume_token(STRING);
        break;
      default:
        jj_la1[9] = jj_gen;
        ;
      }
      TempoInf();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
  }

  final public void TempoSup() throws ParseException {
    Durata();
    UnitaTempo();
  }

  final public void TempoInf() throws ParseException {
    Durata();
    UnitaTempo();
  }

  final public void Durata() throws ParseException {
    jj_consume_token(NUM);
  }

  final public void UnitaTempo() throws ParseException {
    jj_consume_token(STRING);
  }

  final public void Privata() throws ParseException {
    jj_consume_token(STRING);
  }

  final public void ListaStrumenti() throws ParseException {
    Strumento();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 32:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_6;
      }
      jj_consume_token(32);
      Strumento();
    }
  }

  final public void Strumento() throws ParseException {
    label_7:
    while (true) {
      jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_7;
      }
    }
  }

  final public void Festivita() throws ParseException {
    label_8:
    while (true) {
      jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_8;
      }
    }
  }

  final public void Promemoria() throws ParseException {
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        jj_consume_token(STRING);
        break;
      case NUM:
        jj_consume_token(NUM);
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUM:
      case STRING:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_9;
      }
    }
  }

//--------------------------------------------------------------------////---------------------- SOTTORICETTE --------------------------------////--------------------------------------------------------------------//  final public void Sottoricetta() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case _PREPARA:
      jj_consume_token(_PREPARA);
      Ricetta();
      break;
    case _APRI:
      jj_consume_token(_APRI);
      Apri();
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Apri() throws ParseException {
    NomeSottoricetta();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 30:
      jj_consume_token(30);
      AutoreSottoricetta();
      jj_consume_token(31);
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
  }

  final public void NomeSottoricetta() throws ParseException {
    jj_consume_token(STRING);
  }

  final public void AutoreSottoricetta() throws ParseException {
    jj_consume_token(STRING);
  }

//--------------------------------------------------------------------////---------------- INGREDIENTI E PREPARAZIONE-------------------------////--------------------------------------------------------------------//  final public void ListaIngredienti() throws ParseException {
    Ingrediente();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 32:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_10;
      }
      jj_consume_token(32);
      Ingrediente();
    }
  }

                                                                                        // Nota: l'unit� di misura deve essere presa dal visitor  final public void Ingrediente() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUM:
    case QUANTITA:
      QuantitaIngrediente();
      TipoIngrediente();
      break;
    case STRING:
      TipoIngrediente();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUM:
      case QUANTITA:
        QuantitaIngrediente();
        break;
      default:
        jj_la1[19] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void TipoIngrediente() throws ParseException {
    label_11:
    while (true) {
      jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_11;
      }
    }
  }

  final public void QuantitaIngrediente() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case QUANTITA:
      jj_consume_token(QUANTITA);
      break;
    case NUM:
      jj_consume_token(NUM);
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Preparazione() throws ParseException {
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOTA:
        Nota();
        break;
      case _OPENLINK:
        jj_consume_token(_OPENLINK);
        Link();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case _STARTDESC:
          jj_consume_token(_STARTDESC);
          DescrizioneLink();
          break;
        default:
          jj_la1[23] = jj_gen;
          ;
        }
        jj_consume_token(_CLOSELINK);
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOTA:
      case _OPENLINK:
        ;
        break;
      default:
        jj_la1[25] = jj_gen;
        break label_12;
      }
    }
  }

  final public void Nota() throws ParseException {
    jj_consume_token(NOTA);
  }

  final public void Link() throws ParseException {
    jj_consume_token(ADVSTRING);
  }

  final public void DescrizioneLink() throws ParseException {
    jj_consume_token(ADVSTRING);
  }

  /** Generated Token Manager. */
  public RicettaParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[26];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x7fc0,0x18000,0x400001,0x5,0x10,0x7fc0,0x40000000,0x10,0x10,0x10,0x18,0x0,0x10,0x10,0x18,0x18,0x18000,0x40000000,0x0,0x28,0x38,0x10,0x28,0x2000000,0xa00000,0xa00000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x1,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public RicettaParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public RicettaParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new RicettaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public RicettaParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new RicettaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public RicettaParser(RicettaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(RicettaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 26; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[33];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 26; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 33; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

                           }
