package uiMain;

import gestorAplicacion.Bike.Bicicleta;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.*;
import gestorAplicacion.Work.Multa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import BaseDatos.*;

//import gestorAplicacion.Work.*;
public class Main {
	static boolean aux= true;	
	public static Persona user;
	public static void main(String[] args) throws IOException {

		Main.config_inicio();
		// TODO: condicion de break
        try {       
        	while (aux) {	
				Main.user.getMenu().lanzarMenu();											
			}
        }
        catch(Exception e){
        		
    		System.out.println("Adios");
        }
        finally{
        	Datos.guardarDatos();
        }
		

	}

	public static void config_inicio() {

		BaseDatos.Datos.cargarDatos();
		// Cargar las opciones del programa primero
		BaseDatos.Datos.operations.put("1", new Login("1"));
		BaseDatos.Datos.operations.put("2", new SignUp("2"));
		BaseDatos.Datos.operations.put("3", new SignOut("3"));
		BaseDatos.Datos.operations.put("4", new salir("4"));
		BaseDatos.Datos.operations.put("5", new Consultas("5"));
		BaseDatos.Datos.operations.put("6", new PerfilTarjeta("6"));
		BaseDatos.Datos.operations.put("7", new Deuda("7"));
		BaseDatos.Datos.operations.put("8", new cant_biciclitas_esta("8"));	
		BaseDatos.Datos.operations.put("9", new Pedir_bicicleta("9"));	
		BaseDatos.Datos.operations.put("10", new Devolver_bicicleta("10"));
		
		BaseDatos.Datos.hashMulta.put("1", new Multa("1", "Se sobrepaso el limite de Velocidad", 50000));
		BaseDatos.Datos.hashMulta.put("2", new Multa("2", "Ocasiono un danio voluntario a la bicicleta", 100000));
		BaseDatos.Datos.hashMulta.put("3", new Multa("3", "Se sobrepaso el tiempo limite", 30000));
		
		/*;0;;0;;0
		Jos;18;M;1193;1193;50000;3;1*/

		
		BaseDatos.Datos.cargarDatos();
		//Usuario invitado(por defecto)
		Main.user = Usuario.nuevoUsuarioInvitado();

		ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>(){
			{
						add(BaseDatos.Datos.operations.get("5"));
						add(BaseDatos.Datos.operations.get("3"));
			}
		};
				
		MenuDeConsola userMenu = new MenuDeConsola(userOptions);
		
		
		
		

	}

}