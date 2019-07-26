package uiMain;

import java.util.*;

import gestorAplicacion.Bike.Estacion;

public class crearEstacion extends OpcionDeMenu {
	public crearEstacion(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner ent= new Scanner(System.in);
		System.out.println("¿Desea crear una nueva estacion? (S/N)");
		if (ent.next().contentEquals("N")) {
			return;
		}
		System.out.println("Por favor ingrese el id de la estacion: (Entero)");
		String id= ent.next();
		Estacion e=BaseDatos.Datos.hashEstacion.get(id);
		if (e!=null) {
			System.out.println("Ya existe una estacion con ese id: "+ e.toString());
			System.out.println("Desea sobreescribirla? (S/N)");
			if (ent.next().equals("N")) {
				System.out.println("No se sobreescribira la estacion.");
				return;
			}
		}
		System.out.println("Ingrese el tipo (Automatica/Manual): ");
		String tipo= ent.next();
		System.out.println("Desea que este cerrada? (C/else)");
		boolean estado=true;
		if (ent.next().contentEquals("C")) {
			estado=false;
		}
		System.out.println("Ingrese la capacidad maxima: (Entero)");
		int cap_max= ent.nextInt();
		if (e!=null) {
			System.out.println("Se ha sobreescrito la estacion: ");
		}
		else{
			System.out.println("Se ha creado la estacion: ");
		}
		
		new Estacion(id, tipo, estado, cap_max);
		e=BaseDatos.Datos.hashEstacion.get(id);
		
		System.out.println(e.toString());
	}

	@Override
	public String toString() {
		return "Crear Estacion.";
	}

}
