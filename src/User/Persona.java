package User;

public abstract class Persona {
	private String nombre;
	private byte edad;
	private long doc_id;
	private String genero;
	private long clave;
	public abstract void finalize(); //Abstract, obliga a Usuario y moderador definir destructor
}
