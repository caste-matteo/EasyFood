/**
 * 
 */
package mycore;

/**
 * @author pierpytom
 *
 */
@SuppressWarnings("serial")
public class IllegalAssignmentException extends Exception {
	
	/**
	 * Eccezione per l'assegnamento (o ri-assegnamento) di una variabile non concesso.
	 * @param desc Descrizione dell'errore
	 */
	public IllegalAssignmentException(String desc) {
		super(desc);
	}
	
	public IllegalAssignmentException() {
		super();
	}
	
	
}