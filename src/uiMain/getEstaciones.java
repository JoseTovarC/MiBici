package uiMain;

import java.util.Map;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Admin;

public class getEstaciones extends OpcionDeMenu {
	public getEstaciones(String key) {
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
		if(Main.user instanceof Admin) {
			i=0;
		}
		for (Map.Entry<String, Estacion> entry : BaseDatos.Datos.hashEstacion.entrySet()) {
			r.append("\n"+ i +". Id: "+entry.getValue().getIde() + " ["+entry.getValue().getCantBicis()+"]");
			i++;
		}
		return r;
	}
	@Override
	public String toString() {
		if(Main.user instanceof Admin) {
			return "Fabrica y Estaciones";
		}
		return "Estaciones.";
	}

}
