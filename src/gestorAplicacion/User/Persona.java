package gestorAplicacion.User;

import BaseDatos.Datos;
import uiMain.Main;
import uiMain.MenuDeConsola;

public abstract class Persona {
	protected String nombre;
	private byte edad;
	private long id;
	private String genero;
	private String clave;
	private MenuDeConsola menu;

	
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
		byte edad = (byte)Integer.parseInt(ed);
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
	public void setGenero(String genero) {//por si le da por cambiar de género... es 2019 hay que darles la posibilidad o nos demandan
		this.genero = genero;
	}

	public String getClave() { //olvidaste tu contraseña?
		return clave;
	}
	public void setClave(String clave) { //cambiar contraseña
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
	public static String login(long id, String password) {
	Persona u = Usuario.getUsuarioPorUsername(id);
		if (u != null) {
			if (u.getId() == id && u.getClave().equals(password)) {
				// Seteo el usuario
				Main.user = u;
				if(u instanceof Moderador) {
					return "Bienvenido,\nModerador: " + u.getNombre();
				}else if(u instanceof Admin) {
					return "Bienvenido,\nAdministrador: " + u.getNombre();
				}else {
					return "Bienvenido,\nUsuario: " + u.getNombre();
				}
				
				
			}
		}
		return "Usuario no encontrado";
	}

	public static Persona getUsuarioPorUsername(long id) {
		return Datos.hashPersona.get(id);
	}
}
