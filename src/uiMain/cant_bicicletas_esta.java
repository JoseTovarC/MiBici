package uiMain;
import gestorAplicacion.Bike.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class cant_bicicletas_esta extends OpcionDeMenu {

	public static ArrayList<Estacion> estaciones = new ArrayList<>();
	
	public cant_bicicletas_esta(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		System.out.println(this.getEstacioness());
	}
	
	public static String getEstacioness() {
		String r;
		r=new String();
		if(BaseDatos.Datos.hashEstacion.isEmpty()){
			r= new String("No hay estaciones creadas.");
			return r;
		}
		int i=1;
		estaciones.clear();
		for (Map.Entry<String, Estacion> entry : BaseDatos.Datos.hashEstacion.entrySet()) {
			r += ""+ i +". Id: "+entry.getValue().getIde() + " ["+ entry.getValue().getCantBicis()+" / " + entry.getValue().getCap_max() + " ]\n";
			estaciones.add(entry.getValue());
			i++;
		}
		
		return r;
	}
	public static  Estacion getEstacion( String r) {
		int index = Integer.parseInt(r);
		return estaciones.get(index - 1);
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cantidad de bicicletas por estación";
	}

}
