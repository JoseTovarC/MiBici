package gestorAplicacion.User;


import BaseDatos.Datos;
import uiMain.MenuDeConsola;


public class Admin extends Usuario{
	
	Admin(){
		super();
	}
	
	public Admin(String nombre, byte edad, long id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);	
		BaseDatos.Datos.hashPersona.put(id, this);
	
	}
	
	public static String newAdminUser(String nombre, byte edad, long id, String genero, String clave){
		Admin user = new Admin();
		//Validaciones de cada parametro
		user.setName(name);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		
		//Menu por defecto al crear un nuevo usuario administrador
		String [] operations = {"1","2","3","4","5"};
		MenuDeConsola.newMenu(user, operations);
		if(true){
			Data.users.put(username,user);
			return "Ha sido creado";
		}else{
			return "No ha sido creado...";
		}
	}
}
