package uiMain;

import BaseDatos.Datos;

public class PorcGen extends OpcionDeMenu {

	public PorcGen(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getPorcGen());
	}

	@Override
	public String toString() {
		return "Porcentaje de genero de los usuarios.";
	}

}
