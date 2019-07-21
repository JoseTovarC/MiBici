package gestorAplicacion.User;

import uiMain.MenuDeConsola;

public abstract class Persona {
	protected String nombre;
	private byte edad;
	private long id;
	private String genero;
	private String clave;
	private MenuDeConsola menu;
	public Persona() {
		
	}
	
	public Persona(String nombre, byte edad, long id, String genero, String clave, MenuDeConsola menu) {
		this.nombre = nombre;
		this.setEdad(edad);
		this.setId(id);
		this.setGenero(genero);
		this.setClave(clave);
		this.menu = menu;

	}
	
	public Persona(String nombre, byte edad, long id, String genero, String clave) {
		this.nombre = nombre;
		this.setEdad(edad);
		this.setId(id);
		this.setGenero(genero);
		this.setClave(clave);

	}
	
	public Persona(String nombre, String ed, String iden, String genero, String clave) {
		
		long id = (long)Integer.parseInt(iden);
		byte edad = (byte)Integer.parseInt(iden);
		this.nombre = nombre;
		this.setEdad(edad);
		this.setId(id);
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

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getGenero() { 
		return genero;
	}
	public void setGenero(String genero) {//por si le da por cambiar de g�nero... es 2019 hay que darles la posibilidad o nos demandan
		this.genero = genero;
	}

	public String getClave() { //olvidaste tu contrase�a?
		return clave;
	}
	public void setClave(String clave) { //cambiar contrase�a
		this.clave = clave;
	}
	public abstract void finalize();
	// Abstract, obliga a Usuario y moderador definir destructor
	public void setMenu(MenuDeConsola menu){
		this.menu = menu;
	}
	public MenuDeConsola getMenu(){
		return menu;
	}
}
