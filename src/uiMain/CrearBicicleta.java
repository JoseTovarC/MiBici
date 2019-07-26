package uiMain;

import java.util.ArrayList;
import java.util.Map;
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
		if (BaseDatos.Datos.hashDistribuidor.isEmpty()) {
			System.out.println("No se pueden crear bicicletas. \nNo hay distribuidores.");
			return;
		} else if (BaseDatos.Datos.hashEstacion.isEmpty()) {
			System.out.println("No se pueden crear bicicletas. \\nNo hay estaciones.");
			return;
		}

		Scanner esc = new Scanner(System.in);
		ArrayList<Estacion> estaciones = new ArrayList<>();
		ArrayList<Distribuidor> distribuidores = new ArrayList<>();
		Distribuidor dis;
		Estacion e;
		while (true) {
			System.out.println("Distribuidores: ");
			int i = 1;
			for (Entry<String, Distribuidor> distri : BaseDatos.Datos.hashDistribuidor.entrySet()) {
				dis = distri.getValue();
				System.out.println((i++) + ". " + dis.getId());
				distribuidores.add(dis);
			}
			System.out.println("¿Con que distribuidor desea mandar a fabricar una Bicicleta? (Entero) ");
			i = esc.nextInt();
			if (i >= 1 && i <= distribuidores.size()) {
				Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
				dis=distribuidores.get(i - 1);
				break;
			} else {
				System.out.println("Elige una Opcion Valida.");
			}
		}
		
		
		int i;
		while (true) {
			
			StringBuffer r;
			r=new StringBuffer("Estaciones [CantBicis/capacidad]:");
			if(BaseDatos.Datos.hashEstacion.isEmpty()){
				r= new StringBuffer("No hay estaciones creadas.");
				
			}
			i=1;
			for (Map.Entry<String, Estacion> entry : BaseDatos.Datos.hashEstacion.entrySet()) {
				r.append("\n"+ i++ +". Id: "+entry.getValue().getIde() + " ["+entry.getValue().getCantBicis()+" / "+entry.getValue().getCap_max()+"]");
				estaciones.add(entry.getValue());
			}
			System.out.println(r);
			System.out.println("Elija en que estacion se dejara la bicicleta: (Entero / 0 para cancelar)");
			i = esc.nextInt();
			if (i==0) {
				System.out.println("No se creara la bicicleta.");
				return;
			}
			else if (i >= 1 && i <= BaseDatos.Datos.hashBicicleta.size()) {
				e= estaciones.get(i - 1);
				if (e.getCantBicis()<e.getCap_max()) {
					System.out.println(dis.crearB(e));
					break;
				}
			}
			else {
				System.out.println("Elija una Opcion Valida.");
			}
		}
	}
	


	@Override
	public String toString() {
		return "Solicitar creacion de bicicleta";
	}

}
