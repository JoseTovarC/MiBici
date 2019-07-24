package uiMain;

import BaseDatos.*;

public class MayorCantM extends OpcionDeMenu {

	public MayorCantM(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getMayorCantM());
	}

	@Override
	public String toString() {
		return "El usuario que tiene mas multas actualmente.";
	}

}
