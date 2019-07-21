package uiMain;
import gestorAplicacion.Bike.*;
import java.util.Map.Entry;
import BaseDatos.Datos.*;

public class cant_biciclitas_esta extends OpcionDeMenu {

	public cant_biciclitas_esta(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		System.out.println("Estación:  Cant_bicicletas:");
		
		for (Entry<String, Estacion> entry : BaseDatos.Datos.hashEstacion.entrySet()) {
		    Estacion esta = entry.getValue();
			System.out.println(esta.getTipo()+ "   " + esta.getCantBicis());
		}

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cantidad de bicicletas por estación";
	}

}
