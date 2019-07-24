package uiMain;

import BaseDatos.Datos;

public class MayorUsoB extends OpcionDeMenu {
	
	public MayorUsoB(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getMayorUsoB());
	}

	@Override
	public String toString() {
		return "Las bicicletas mas usadas.";
	}

}
