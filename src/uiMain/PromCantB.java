package uiMain;

import BaseDatos.Datos;

public class PromCantB extends OpcionDeMenu {

	public PromCantB(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getPromCantB());
	}

	@Override
	public String toString() {
		return "El promedio de la cantidad de bicicletas en todas las estaciones.";
	}

}
