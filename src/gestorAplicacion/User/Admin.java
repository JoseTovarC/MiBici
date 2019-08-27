package gestorAplicacion.User;


import BaseDatos.Datos;
import uiMain.MenuDeConsola;


public class Admin extends Persona{
	
	

	public Admin(String nombre, byte edad, long id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);	
	
	}
	
	public Admin(String nombre, String edad, String id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);	
	}

	public static String newAdminUser(String nombre, byte edad, long id, String genero, String clave){
		Admin user = new Admin(nombre,edad,id,genero,clave);
		
		//Menu por defecto al crear un nuevo usuario administrador
		String [] operations = {"0","29","3"};
		MenuDeConsola.newMenu(user, operations);
		Datos.hashPersona.put(id,user);
		return "Ha sido creado: "+Datos.hashPersona.get(id).toString();
		
	}

	@Override
	public void finalize() {
		// TODO Auto-generated method stub
		
	}
	public String toString() {
		return "Administrador: "+this.getNombre()+".";
	}

	
	
}
