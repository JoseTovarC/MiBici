package uiMain;

import gestorAplicacion.Bike.Bicicleta;
import gestorAplicacion.Bike.Distribuidor;
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
        	Datos.guardarDatos();	
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
		BaseDatos.Datos.operations.put("11", new Recargar_tarjeta("11"));
		BaseDatos.Datos.operations.put("12", new Pagar_multas("12"));
		BaseDatos.Datos.operations.put("13", new ConsultasM("13"));
		
		BaseDatos.Datos.hashMulta.put("1", new Multa("1", "Se sobrepaso el limite de Velocidad", 50000));
		BaseDatos.Datos.hashMulta.put("2", new Multa("2", "Ocasiono un danio voluntario a la bicicleta", 100000));
		BaseDatos.Datos.hashMulta.put("3", new Multa("3", "Se sobrepaso el tiempo limite", 30000));
		
		/*;0;;0;;0
		Jos;18;M;1193;1193;50000;3;1*/
		//estacion(por defecto)
		new Estacion("0", "Libre", true, 0);
		new Distribuidor("Default", "0");
		Main.user = Usuario.nuevoUsuarioInvitado();
		
		BaseDatos.Datos.cargarDatos();
		//Usuario invitado(por defecto)
		
		new Estacion("1", "Automatica", true,4, (Moderador) Usuario.getUsuarioPorUsername((long) 1193126480));
		new Bicicleta(1, BaseDatos.Datos.hashEstacion.get("1"), BaseDatos.Datos.hashDistribuidor.get("0"));
		new Bicicleta(2, BaseDatos.Datos.hashEstacion.get("1"), BaseDatos.Datos.hashDistribuidor.get("0"));
		/*ArrayList<OpcionDeMenu> ModeradorOptions = new ArrayList<OpcionDeMenu>(){
			{
						add(BaseDatos.Datos.operations.get("13"));
						add(BaseDatos.Datos.operations.get("9"));
						add(BaseDatos.Datos.operations.get("10"));
						add(BaseDatos.Datos.operations.get("3"));						
			}
		};
				
		MenuDeConsola ModeradorMenu = new MenuDeConsola(ModeradorOptions);
		new Moderador("Jose Orlando Tovar Cano", (byte) 18, (long)1193126480, "M", "1193",0,ModeradorMenu,Estacion.getEstacionporId("0"));*/
		
		

	}

}