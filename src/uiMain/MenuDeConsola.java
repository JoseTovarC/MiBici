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
		Scanner esc = new Scanner(System.in);
		
		int i = 1;
		for (OpcionDeMenu option : options) {
			
			System.out.println(i+" "+option);
			i++;
		}
		System.out.print("Ingrese la opcion: ");
		int opt = esc.nextInt();
		options.get((opt-1)).ejecutar();
	}
	
	public static void newMenu(Persona user, String [] operations) {
		ArrayList<OpcionDeMenu> operationsMenu = new ArrayList<OpcionDeMenu>();
		for (String opt : operations) {
			operationsMenu.add(Datos.operations.get(opt));
		}
		MenuDeConsola menu = new MenuDeConsola(user, operationsMenu);
		user.setMenu(menu);
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

	
    
}