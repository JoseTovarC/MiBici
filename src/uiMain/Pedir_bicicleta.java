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
		for (Entry<String, Estacion> estacion : BaseDatos.Datos.hashEstacion.entrySet()) {
			Estacion est = estacion.getValue();
			estaciones.add(est);
		}
		int i;
		while (true) {
			System.out.print("Elija una estacion: \n");
			BaseDatos.Datos.operations.get("32").ejecutar(); // getEstaciones
			i = esc.nextInt();

			if (i >= 1 && i <= estaciones.size()) {
				Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
				estaciones.get(i - 1).getCantBicis();
				aux.prestar(estaciones.get(i - 1));
				break;
			} else {
				System.out.println("Ingrese un valor valido entre 1 y " + estaciones.size());
			}
		}
	}

	@Override
	public String toString() {

		return "Pedir Bicicleta.";
	}

}
