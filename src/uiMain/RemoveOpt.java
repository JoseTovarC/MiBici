package uiMain;

import java.util.Scanner;

import gestorAplicacion.User.Persona;

import uiMain.OpcionDeMenu;

public class RemoveOpt extends OpcionDeMenu{
	
	public RemoveOpt(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Scanner leer = new Scanner(System.in);
		System.out.print("Ingrese el nombre de usuario: ");
		Long username = leer.nextLong();
		Persona user = Persona.getUsuarioPorUsername(username);
		user.getMenu().seeOpt();
		System.out.print("Ingrese el numero de la funcionalidad a remover: ");
		int index = leer.nextInt();
		user.getMenu().removeOpt(index);
	}

	@Override
	public String toString() {
		return "Quitar funcionalidades de un usuario";
	}

}
