package uiMain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map.Entry;

import BaseDatos.Datos;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Usuario;

public class Devolver_bicicleta extends OpcionDeMenu {

	public Devolver_bicicleta(String key) {
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		while (true) {

			System.out.println("Estaciones:");
			ArrayList<Estacion> estaciones = new ArrayList<>();
			int i = 0;
			for (Entry<String, Estacion> estacion : BaseDatos.Datos.hashEstacion.entrySet()) {
				Estacion est = estacion.getValue();
				estaciones.add(est);
				System.out.println((i++) + ". " + est.getId());

			}
			
			if(estaciones.isEmpty()){
				System.out.println("No hay todavia estaciones creadas, lo cual no se pueden devolver, ni prestar ciclas todavia");
				break;
			}
			System.out.print("Elija la estaciòn en la que va a devolver la cicla su bicicleta: ");
			i = esc.nextInt();
			if(i >= 1 && i<= estaciones.size() ) {
				Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
				aux.devolver(estaciones.get(i-1));
				break;
			}else {
				System.out.println("Elige una Opcion Valida!");
			}
			
		}

	}

	@Override
	public String toString() {
		
		return "Devolver Bicicleta";
	}

}
