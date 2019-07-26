package uiMain;

import java.util.ArrayList;
import java.util.Scanner;
import BaseDatos.Datos;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;



public class MenuDeConsola {
	
	private Persona user;
	public ArrayList<OpcionDeMenu> options = new ArrayList<OpcionDeMenu> ();
	
	public MenuDeConsola(ArrayList<OpcionDeMenu> options){
		this.options = options;
	}
	
	public MenuDeConsola(Persona user, ArrayList<OpcionDeMenu> options){
		this.user = user;
		this.options = options;
	}
	
	

	public Persona getUser() {
		return user;
	}

	public void setUser(Persona user) {
		this.user = user;
	}
	
	public ArrayList<OpcionDeMenu> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<OpcionDeMenu> options) {
		this.options = options;
	}
	
	public String[] getOperations() {
		String [] opt = new String[options.size()];
		int i = 0;
		for (OpcionDeMenu opcionDeMenu : options) {
			opt[i] = opcionDeMenu.getKey();
			i++;
		}
		return opt;
	}
	
	public void lanzarMenu(){
		Scanner leer = new Scanner(System.in);
		
		System.out.println();
		int i = 1;
		for (OpcionDeMenu option : options) {
			
			System.out.println(i+" "+option);
			i++;
		}
		
		System.out.print("Ingrese la opcion: ");
		int opt = leer.nextInt();
		options.get((opt-1)).ejecutar();
		
	}
	
	public static void newMenu(Persona user, String [] operations) {
		
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
				
				
		ArrayList<OpcionDeMenu> operationsMenu = new ArrayList<OpcionDeMenu>();
		for (String opt : operations) {
			operationsMenu.add(Datos.operations.get(opt));
		}
		MenuDeConsola menu = new MenuDeConsola(user, operationsMenu);
		user.setMenu(menu);
		menu.setUser(user);
		Datos.menus.put(user.getId(), menu);

	}

	
	public void seeOpt() {
		int i = 1;
		for (OpcionDeMenu opcionDeMenu : options) {
			System.out.println(i + " "+opcionDeMenu);
			i++;
		}
	}
	
	public void removeOpt(int index) {
		options.remove(index-1);
	}
	
	public void anadirOpcion(OpcionDeMenu option) {
		options.add(option);
	}


	public void añadirOpcion(String opt) {
		options.add(Datos.operations.get(opt));
	}
	
    
}