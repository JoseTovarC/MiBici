package uiMain;

import gestorAplicacion.Bike.Bicicleta;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import BaseDatos.Datos;

//import gestorAplicación.Work.*;
public class Main {
		
	public static Usuario usuario;
	
	public static void main(String[] args) throws IOException {

		Main.config_inicio();
		// TODO: condicion de break
		while (true) {
			Main.usuario.getMenu().lanzarMenu();
		}

	}

	public static void config_inicio() {

		Datos.cargarDatos();
		// Cargar las opciones del programa primero
		Datos.operations.put("1", new Login());
		Datos.operations.put("2", new SignUp());
		Datos.operations.put("3", new SignOut());
		
		//Usuario invitado(por defecto)
		Main.usuario = UsuarioInvitado.nuevoUsuarioInvitado();
				
				

		@SuppressWarnings("serial")
		ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>(){
			{
						add(Datos.operations.get("3"));
			}
		};
				
		MenuDeConsola userMenu = new MenuDeConsola(userOptions);
		new Usuario("Jose", (byte) 18, (long) 1193126480, "Masculino", "7201", 50000, userMenu);

	}

}
