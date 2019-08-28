package control;

public class opcionNoValidaException extends Exception {
	public opcionNoValidaException() {
		super("No se puede ejecutar esta opción");
	}
}