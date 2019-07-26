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
	public static void setUser(Persona user){
		Main.user = user;
	}
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


		// Cargar las opciones del programa primero
		BaseDatos.Datos.operations.put("0", new Modificar("0"));
		BaseDatos.Datos.operations.put("1", new Login("1"));
		BaseDatos.Datos.operations.put("2", new SignUp("2"));
		BaseDatos.Datos.operations.put("3", new SignOut("3"));
		BaseDatos.Datos.operations.put("4", new salir("4"));
		BaseDatos.Datos.operations.put("5", new ConsultasU("5"));
		BaseDatos.Datos.operations.put("6", new PerfilTarjeta("6"));
		BaseDatos.Datos.operations.put("7", new Deuda("7"));
		BaseDatos.Datos.operations.put("8", new cant_bicicletas_esta("8"));	
		BaseDatos.Datos.operations.put("9", new Pedir_bicicleta("9"));	
		BaseDatos.Datos.operations.put("10", new Devolver_bicicleta("10"));
		BaseDatos.Datos.operations.put("11", new Recargar_tarjeta("11"));
		BaseDatos.Datos.operations.put("12", new Pagar_multas("12"));
		BaseDatos.Datos.operations.put("13", new ConsultasM("13"));
	    BaseDatos.Datos.operations.put("14", new CreacionM("14"));
		BaseDatos.Datos.operations.put("15", new CrearBicicleta("15"));
		BaseDatos.Datos.operations.put("16", new crearDistribuidor("16"));
		BaseDatos.Datos.operations.put("17", new crearEstacion("17"));
		BaseDatos.Datos.operations.put("18", new Funcionalidades("18"));
		BaseDatos.Datos.operations.put("19", new MayorUsoP("19"));
		BaseDatos.Datos.operations.put("20", new MayorEdad("20"));
		BaseDatos.Datos.operations.put("21", new MenorEdad("21"));
		BaseDatos.Datos.operations.put("22", new PromEdad("22"));
		BaseDatos.Datos.operations.put("23", new PorcGen("23"));
		BaseDatos.Datos.operations.put("24", new PorcUsoGen("24"));
		BaseDatos.Datos.operations.put("25", new MayorCantM("25"));
		BaseDatos.Datos.operations.put("26", new MayorUsoB("26"));
		BaseDatos.Datos.operations.put("27", new MayorUsoE("27"));
		BaseDatos.Datos.operations.put("28", new PromCantB("28"));
		BaseDatos.Datos.operations.put("29", new CreacionA("29"));
		BaseDatos.Datos.operations.put("30", new SignUpM("30"));
		BaseDatos.Datos.operations.put("31", new SignUpA("31"));
		BaseDatos.Datos.operations.put("32", new Multar("32"));
		BaseDatos.Datos.operations.put("33", new SeeOpt("33"));
		BaseDatos.Datos.operations.put("34", new AddOpt("34"));
		BaseDatos.Datos.operations.put("35", new RemoveOpt("35"));
		
		
		
		
		BaseDatos.Datos.hashMulta.put((byte)1, new Multa((byte)1, "Tiempo maximo excedido.", 30000));
		BaseDatos.Datos.hashMulta.put((byte)2, new Multa((byte)2, "Danio intencional.", 75000));
		BaseDatos.Datos.hashMulta.put((byte)3, new Multa((byte)3, "Numero de pasajeros excedido.", 30000));
		BaseDatos.Datos.hashMulta.put((byte)4, new Multa((byte)4, "Fines comerciales", 80000));
		BaseDatos.Datos.hashMulta.put((byte)5, new Multa((byte)5, "Carga maxima excedida", 25000));
		BaseDatos.Datos.hashMulta.put((byte)6, new Multa((byte)6, "Estado Embriaguez", 80000));
		
		BaseDatos.Datos.cargarDatos();
		
		Main.user = Usuario.nuevoUsuarioInvitado();
		//Admin;18;null;1;666
		//IT;19;F;2;777
		
		Admin.newAdminUser("Admin", (byte)18, (long)1, "null", "666");
		Admin.newAdminUser("IT", (byte)19, (long)2, "F", "777");
		//Usuario invitado(por defecto)
		
	
		
		ArrayList<OpcionDeMenu> ModeradorOptions = new ArrayList<OpcionDeMenu>(){
			{
						add(BaseDatos.Datos.operations.get("13"));
						add(BaseDatos.Datos.operations.get("9"));
						add(BaseDatos.Datos.operations.get("10"));
						add(BaseDatos.Datos.operations.get("11"));
						add(BaseDatos.Datos.operations.get("14"));
						add(BaseDatos.Datos.operations.get("18"));
						add(BaseDatos.Datos.operations.get("32"));
						add(BaseDatos.Datos.operations.get("3"));						
			}
		};
		//Freddy;20;M;10021;5132;2500;0
		//Luis;19;M;10030;0561;10000;0
		//Catalina;18;F;10031;0505;1500;0
		MenuDeConsola ModeradorMenu = new MenuDeConsola(ModeradorOptions);
		
		new Moderador("Freddy", (byte) 20, (long)10021, "M", "5132",2500,ModeradorMenu);
		new Moderador("Luis", (byte) 19, (long)10030, "M", "0561",10000,ModeradorMenu);
		new Moderador("Catalina", (byte) 18, (long)10031, "F", "0505",1500,ModeradorMenu);
		
		//Hernán Darío;23;M;10018;1890;0
		//Julian;19;M;10020;0202;0
		//Yurleidi Maria;22;F;10007;1234;0
		//Juan José;21;M;10009;4321;0
		//Karen;18;F;10010;1278;0
		//Fernanda;19;F;10011;1111;0
		//Stefania;18;F;10027;2780;0
		
		ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>(){
		{
			add(BaseDatos.Datos.operations.get("5"));		
			add(BaseDatos.Datos.operations.get("9"));	
			add(BaseDatos.Datos.operations.get("10"));
			add(BaseDatos.Datos.operations.get("11"));
			add(BaseDatos.Datos.operations.get("12"));
			add(BaseDatos.Datos.operations.get("3"));

		}
	};
			
	MenuDeConsola userMenu = new MenuDeConsola(userOptions);
	new Usuario("Hernán Darío", (byte) 23, (long) 10018, "M","1890" , 0, userMenu);
	new Usuario("Julian", (byte) 19, (long) 10020, "M","0202" , 0, userMenu);
	new Usuario("Yurleidi Maria", (byte) 22, (long) 10007, "F","1234" , 0, userMenu);
	new Usuario("Juan Jose", (byte) 21, (long) 10009, "M","4321" , 0, userMenu);
	new Usuario("Karen", (byte) 18, (long) 10010, "F","1278" , 0, userMenu);
	new Usuario("Fernanda", (byte) 19, (long) 10011, "F","1111" , 0, userMenu);
	new Usuario("Estefania", (byte) 18, (long) 10027, "F","2780" , 0, userMenu);
	
	}

}