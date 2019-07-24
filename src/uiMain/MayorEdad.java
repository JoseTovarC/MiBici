package uiMain;

import BaseDatos.Datos;

public class MayorEdad extends OpcionDeMenu {
	
	public MayorEdad(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getMayorEdad());
	}

	@Override
	public String toString() {
		return "El usuario mas viejo.";
	}
}
