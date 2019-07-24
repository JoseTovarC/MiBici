package uiMain;

import BaseDatos.Datos;

public class MenorEdad extends OpcionDeMenu {

	public MenorEdad(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getMenorEdad());
	}

	@Override
	public String toString() {
		return "El usuario mas joven.";
	}
}
