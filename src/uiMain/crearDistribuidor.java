package uiMain;

import java.util.Scanner;

import gestorAplicacion.Bike.Distribuidor;

public class crearDistribuidor extends OpcionDeMenu {
	public crearDistribuidor(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner ent= new Scanner(System.in);
		System.out.println("Desea crear un nuevo distribuidor? (S/N");
		if (ent.next().contentEquals("N")) {
			return;
		}
		System.out.println("Por favor ingrese el id del distribuidor: (Entero)");
		String id= ent.next();
		Distribuidor e=BaseDatos.Datos.hashDistribuidor.get(id);
		if (e!=null) {
			System.out.println("Ya existe un distribuidor con ese id: "+ e.toString());
			System.out.println("Desea sobreescribirlo? (S/N");
			if (ent.next().equals("N")) {
				System.out.println("No se sobreescribira el distribuidor.");
				return;
			}
		}
		System.out.println("Ingrese el nombre del distribuidor (Cadena String): ");
		String name= ent.next();
		if (e!=null) {
			System.out.println("Se ha sobreescrito el distribuidor: ");
		}
		else{
			System.out.println("Se ha creado el distribuidor: ");
		}
		
		new Distribuidor(name, id);
		e=BaseDatos.Datos.hashDistribuidor.get(id);
		
		System.out.println(e.toString());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Crear Distribuidor.";
	}

}
