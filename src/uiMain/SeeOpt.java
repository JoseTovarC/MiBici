package uiMain;

import java.util.Scanner;

import gestorAplicacion.User.Persona;

import uiMain.OpcionDeMenu;

public class SeeOpt extends OpcionDeMenu {
	
	public SeeOpt(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Scanner leer = new Scanner(System.in);
		System.out.print("Ingrese el nombre de usuario: ");
		Long username = leer.nextLong();
		Persona user = Persona.getUsuarioPorUsername(username);
		user.getMenu().seeOpt();
	}

	@Override
	public String toString() {
		return "ver funciones de un usuario";
	}

}
