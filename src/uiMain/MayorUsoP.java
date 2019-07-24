package uiMain;

import BaseDatos.Datos;

public class MayorUsoP extends OpcionDeMenu {

	public MayorUsoP(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getMayorUsoP());
	}

	@Override
	public String toString() {
		return "El usuario que mas usos ha hecho.";
	}

}
