package mycore;

import java.awt.Color;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class MyConnectionHelper implements Serializable {

	static Connection conn = null;
	ArrayList<ColoredString> output = new ArrayList<ColoredString>();
	public ArrayList<ColoredString> getOutput() {
		return output;
	}


	public MyConnectionHelper() {
        if(conn == null) {
			try
			{
				String userName = "root";
				String password = "root";
				String url = "jdbc:mysql://localhost:3306/cuciniamo";
				Class.forName ("com.mysql.jdbc.Driver").newInstance ();
				conn = DriverManager.getConnection (url, userName, password);
				output.add(new ColoredString("Connessione con il database disponibile.", Color.green));
			}
			catch (Exception e)
			{
				output.add(new ColoredString("Connessione con il database non disponibile, alcune informazioni non saranno disponibili.", Color.red));
			} 
			output.trimToSize();
		}
	}

	/**
	 * Trova l'id della categoria.
	 * @return id della categoria
	 */
	public int getIdCategoria(String catInf, String catSup){
		
		if(conn==null) return -1;
		
		//Passi: 1. se si hanno catInf e catSup si fa la ricerca su entrambe
		//		 2. provare a cercare caInf e basta e vedere quanti risultati ci sono
		//			-> Se un solo risultato va bene, se più warning + non determinismo
		//		 3. se catInf non dà risultati provare nel caso l'utente abbia dato catSup
		//			come fosse catInf (e quindi impostare catInf ad 'Altro')
		//		 4. warning e restituire id di altro/altro
		
		// pulisco l'ultimo output
		output.clear();
		
		try {
			Statement s;
			s = conn.createStatement ();
			ResultSet rs;
			
			if(catInf != null && catSup != null) {
				s.executeQuery ("SELECT ID_Categoria FROM categorie WHERE Inferiore = '"+catInf+"' AND Superiore = '"+catSup+"'");	   
				rs = s.getResultSet ();
				int count = 0, idVal = -1;
				while (rs.next ()) {
					idVal = rs.getInt ("ID_Categoria");
					count++;
				}
				rs.close ();
				s.close ();
				if(count == 0) {
					System.out.println("Nessuna categoria trovata nel db.");
					return -1;
				}
				return idVal;				
			}
			
			if(catInf != null && catSup == null) {
				s.executeQuery ("SELECT ID_Categoria FROM categorie WHERE Inferiore = '"+catInf+"'");	   
				rs = s.getResultSet ();
				int count = 0, idVal = -1;
				while (rs.next ()) {
					idVal = rs.getInt ("ID_Categoria");
					count++;
				}
				rs.close ();
				s.close ();
				if(count == 0) {
					//System.out.println("Nessuna categoria trovata nel db. Riprovo.");
					Statement s2 = conn.createStatement ();
					s2.executeQuery ("SELECT ID_Categoria FROM categorie WHERE Superiore = '"+catInf+"' AND Inferiore='Altro'");
					rs = s2.getResultSet ();
					if(rs.next()) {
						idVal = rs.getInt("ID_Categoria");
						//System.out.println("Trovata al secondo tentativo");
						return idVal;
					} else {
						//System.out.println("Nessuna categoria trovata nel db al secondo tentativo.");
						return -1;
					}
				}
				if(count > 0) {
					//System.out.println("Warning: trovate più di una sottocategoria con il nome fornito.");
					return idVal;
				}			
				
			}
			
			return -1; // -1 -> niente trovato

		} catch (SQLException ex) { }
		return 0; // 0 -> connessione assente
	}
	
	public int getIdFestivita(String festivita){
		
		if(conn==null) return -1;

		if (festivita == null) return 0;
		try {
			Statement s;
			s = conn.createStatement ();
			s.executeQuery ("SELECT ID_Festivita FROM Festivita WHERE Nome = '"+festivita+"'");
			ResultSet rs = s.getResultSet ();
			int idVal = -1;
			if(rs.next ()) {
				idVal = rs.getInt ("ID_Festivita");
			}
			rs.close ();
			s.close ();
			return idVal;				
		} catch (SQLException ex) {}
		return 0;
	}
	
	
	
	public void dispose() {
        if (conn != null)
        {
            try { conn.close (); }
            catch (Exception e) { /* ignore close errors */ }
        }
	}
	
}
