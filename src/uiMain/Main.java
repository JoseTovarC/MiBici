package uiMain;

import gestorAplicacion.Bike.Bicicleta;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import BaseDatos.*;

//import gestorAplicación.Work.*;
public class Main {
	static boolean aux= true;	
	public static Usuario user;
	public static MenuDeConsola userMenu;
	public static void main(String[] args) throws IOException {

		Main.config_inicio();
		// TODO: condicion de break
               
		while (aux) {
			MenuDeConsola a = Main.user.getMenu();			
                        a.lanzarMenu();                  
		}

	}

	public static void config_inicio() {

		BaseDatos.Datos.cargarDatos();
		// Cargar las opciones del programa primero
		BaseDatos.Datos.operations.put("1", new Login());
		BaseDatos.Datos.operations.put("2", new SignUp());
		BaseDatos.Datos.operations.put("3", new SignOut());
		BaseDatos.Datos.operations.put("4", new salir());
		
		//Usuario invitado(por defecto)
		Main.user = Usuario.nuevoUsuarioInvitado();

		ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>(){
			{
						add(BaseDatos.Datos.operations.get("3"));
			}
		};
				
		userMenu = new MenuDeConsola(userOptions);
		
		new Usuario("Jose", (byte) 18, (long) 1193126480, "Masculino", "7201", 50000, userMenu);

	}

}