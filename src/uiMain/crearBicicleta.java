package uiMain;

import java.util.Scanner;

import BaseDatos.Datos;
import gestorAplicacion.Bike.Distribuidor;
import gestorAplicacion.User.Moderador;

public class crearBicicleta extends OpcionDeMenu {
	public crearBicicleta(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner ent= new Scanner(System.in);
		String id;
		Distribuidor d;
		System.out.println("Desea pedir la creacion de bicicleta(s)? (S/N");
		if (ent.next().contentEquals("N")) {
			return;
		}
		System.out.println("Por favor ingrese el id del distribuidor que creara las bicicletas: (Entero/0 para cancelar)");
		while (true) {
			id= ent.next();
			d= BaseDatos.Datos.hashDistribuidor.get(id);
			if (id.equals("0")) {
				return;
			}
			else if(d!=null) {
				System.out.println("Desea utilizar el distribuidor "+d.toString()+"? (S/N)");
				if (ent.next().contentEquals("S")) {
					break;
				}
			}
			else {
				System.out.println("Distribuidor inexistente, por favor ingrese un id valido.");
			}
		}
		System.out.println("Ingrese la cantidad de bicicletas a crear (Entero): ");
		int q= ent.nextInt();
		System.out.println(d.crearB(q));
		
		System.out.println("En la fabrica actualmente hay "+BaseDatos.Datos.hashEstacion.get("0").getCantBicis()+" y se pueden crear hasta "+ (Datos.hashEstacion.get("0").getCap_max()-Datos.hashEstacion.get("0").getCantBicis())+" bicicletas mas.");
	}

	@Override
	public String toString() {
		if(Main.user instanceof Moderador) {
			return "Pedir crear bicicleta(s).";
		}
		return "Crear bicicleta(s).";
	}

}
