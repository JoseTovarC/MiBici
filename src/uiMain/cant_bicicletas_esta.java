package uiMain;
import gestorAplicacion.Bike.*;

import java.util.Map;
import java.util.Map.Entry;
import BaseDatos.Datos.*;

public class cant_bicicletas_esta extends OpcionDeMenu {

	public cant_bicicletas_esta(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		System.out.println(this.getEstacioness());
	}
	
	private StringBuffer getEstacioness() {
		StringBuffer r;
		r=new StringBuffer("Estaciones [CantBicis]:");
		if(BaseDatos.Datos.hashEstacion.isEmpty()){
			r= new StringBuffer("No hay estaciones creadas.");
			return r;
		}
		int i=1;
		for (Map.Entry<String, Estacion> entry : BaseDatos.Datos.hashEstacion.entrySet()) {
			r.append("\n"+ i +". Id: "+entry.getValue().getIde() + " ["+entry.getValue().getCantBicis()+"]");
			i++;
		}
		return r;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cantidad de bicicletas por estación";
	}

}
