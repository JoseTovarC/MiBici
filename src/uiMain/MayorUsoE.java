package uiMain;

import BaseDatos.Datos;

public class MayorUsoE extends OpcionDeMenu {

	public MayorUsoE(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getMayorUsoE());
	}

	@Override
	public String toString() {
		return "Las estaciones mas usadas.";
	}
}
