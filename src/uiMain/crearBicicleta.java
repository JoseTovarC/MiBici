package uiMain;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map.Entry;

import BaseDatos.Datos;
import gestorAplicacion.Bike.Distribuidor;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Usuario;

public class CrearBicicleta extends OpcionDeMenu {

	public CrearBicicleta(String key) {
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		ArrayList<Estacion> estaciones = new ArrayList<>();
		ArrayList<Distribuidor> distibuidores = new ArrayList<>();
		while (true) {
			
			

			System.out.println("Con que distribuidor desea mandar a fabricar una Bicicleta: ");
			
				for (Entry<String, Distribuidor> distri : BaseDatos.Datos.hashDistribuidor.entrySet()) {
					Distribuidor dis = distri.getValue();

					i += dis.getNumero_bicis();
				

				}
				if(i >= 1 && i<= estaciones.size() ) {
					Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
					estaciones.get(i-1).getCantBicis();
					aux.prestar(estaciones.get(i-1));
					break;
				}else {
					System.out.println("Elige una Opcion Valida!");
				}
		}
		
		
				
		while(distribuidores.size!=0) {
								
			int i = 1;
			for (Entry<String, Estacion> estacion : BaseDatos.Datos.hashEstacion.entrySet()) {
				Estacion est = estacion.getValue();
				estaciones.add(est);
				System.out.println((i++) + ". " + est.getIde());

			}
			
			if(estaciones.isEmpty()){
				System.out.println("No hay todavia estaciones creadas, lo cual no se puede Fabricar bicicletas");
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
		// TODO Auto-generated method stub
		return null;
	}

}
