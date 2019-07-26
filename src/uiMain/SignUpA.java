package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Moderador;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;

public class SignUpA extends OpcionDeMenu {

	public SignUpA(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		System.out.println("1. Registrar un nuevo administrador.");
		System.out.println("2. Retroceder.");
		byte a = 0;
		Estacion e;
		while (a < 1 || a > 2) {
			a = esc.nextByte();
			if (a == 1) {
				System.out.println("Ingrese su nombre y luego ' *' (ESPACIO + *): ");
				String aux = esc.next();
				String nombre = "";
				while (!(aux.equals("*"))) {
					nombre += aux + " ";
					aux = esc.next();
				}
				nombre = nombre.substring(0, nombre.length() - 1);
				System.out.println("Ingrese su edad: ");
				byte edad = esc.nextByte();
				if (edad < 18) {
					System.out.println("Es Menor de edad, no puede hacer uso del sistema.");
					Main.user.getMenu().lanzarMenu();
					break;
				}
				System.out.print("Ingrese su genero (M/F): ");
				String genero = esc.next();
				System.out.print("Ingrese el No. de su cedula (Este sera su usuario): ");
				long id = esc.nextLong();
				System.out.print("Inserte una contraseña: ");
				String contra = esc.next();
				if (Persona.getUsuarioPorUsername(id) == null) {
					Admin.newAdminUser(nombre, edad, id, genero, contra);
					System.out.println("Registro realizado exitosamente.");
				} else {
					System.out.println("No se pudo realizar el registro, el usuario ya existe.");
				}

				Main.user.getMenu().lanzarMenu();
			} else if (a == 2) {
				Main.user.getMenu().lanzarMenu();
			} else {
				System.out.println("Inserte un valor valido.");
			}
		}
		esc.close();

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Crear Administrador";
	}

}
