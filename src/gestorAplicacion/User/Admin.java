package gestorAplicacion.User;


import BaseDatos.Datos;
import uiMain.MenuDeConsola;


public class Admin extends Persona{
	
	

	Admin(){
		super();
	}
	
	public Admin(String nombre, byte edad, long id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);	
	
	}
	
	public Admin(String nombre, String edad, String id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);	
	}

	public static String newAdminUser(String nombre, byte edad, long id, String genero, String clave){
		Admin user = new Admin();
		//Validaciones de cada parametro
		user.setNombre(nombre);
		user.setEdad(edad);
		user.setId(id);
		user.setGenero(genero);
		user.setClave(clave);
		
		//Menu por defecto al crear un nuevo usuario administrador
		String [] operations = {"1","2","3","4","5"};
		MenuDeConsola.newMenu(user, operations);
		if(true){
			Datos.hashPersona.put(id,user);
			return "Ha sido creado";
		}else{
			return "No ha sido creado...";
		}
	}

	@Override
	public void finalize() {
		// TODO Auto-generated method stub
		
	}
	public String toString() {
		return "Nombre: "+this.getNombre() + ". \nId: "+this.getId();
	}

	
	
}
