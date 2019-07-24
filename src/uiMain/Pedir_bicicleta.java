package uiMain;

import java.util.ArrayList;
import java.util.Map.Entry;

import BaseDatos.Datos;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Moderador;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;
import gestorAplicacion.Work.Multa;
import java.util.Scanner;

public class Pedir_bicicleta extends OpcionDeMenu {

	public Pedir_bicicleta(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		ArrayList<Estacion> estaciones = new ArrayList<>();
		while (true) {

			System.out.println("Estaciones:");
			
			int i = 1;
			for (Entry<String, Estacion> estacion : BaseDatos.Datos.hashEstacion.entrySet()) {
				Estacion est = estacion.getValue();
				estaciones.add(est);
				System.out.println((i++) + ". " + est.getIde());

			}
			
			if(estaciones.isEmpty()){
				System.out.println("No hay todavia estaciones creadas, lo cual no se pueden hacer prestamos todavia");
				break;
			}
			System.out.print("Elija la estaciòn en la que va a pedir su bicicleta: ");
			i = esc.nextInt();
			if(i >= 1 && i<= estaciones.size() ) {
				Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
				estaciones.get(i-1).getCantBicis();
				aux.prestar(estaciones.get(i-1));
				break;
			}else {
				System.out.println("Elige una Opcion Valida!");
			}
			
		}

	}

	@Override
	public String toString() {

		return "Pedir Bicicleta";
	}

}
