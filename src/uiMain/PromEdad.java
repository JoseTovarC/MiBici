package uiMain;

import BaseDatos.Datos;

public class PromEdad extends OpcionDeMenu {

	public PromEdad(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getPromEdad());
	}

	@Override
	public String toString() {
		return "Promedio de edad de todos los usuarios.";
	}

}
