package uiMain;

import BaseDatos.Datos;

public class PorcUsoGen extends OpcionDeMenu {

	public PorcUsoGen(String key) {
		super(key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		System.out.println(Datos.getPorcUsoGen());
	}

	@Override
	public String toString() {
		return "El porcentaje de uso por genero.";
	}

}
