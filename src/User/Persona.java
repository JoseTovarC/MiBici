package User;

public abstract class Persona {
	protected String nombre;
	private byte edad;
	private long doc_id;
	private String genero;
	private String clave;

	public Persona(String nombre, byte edad, long id, String genero, String clave) {
		this.nombre = nombre;
		this.setEdad(edad);
		setDoc_id(id);
		this.setGenero(genero);
		this.setClave(clave);
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public byte getEdad() {
		return edad;
	}
	public void setEdad(byte edad) {
		this.edad = edad;
	}

	public long getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}

	public String getGenero() { 
		return genero;
	}
	public void setGenero(String genero) {//por si le da por cambiar de género... es 2019 hay que darles la posibilidad o nos demandan
		this.genero = genero;
	}

	public String getClave() { //olvidaste tu contraseña?
		return clave;
	}
	public void setClave(String clave) { //cambiar contraseña
		this.clave = clave;
	}

	public abstract void finalize(); // Abstract, obliga a Usuario y moderador definir destructor
}
