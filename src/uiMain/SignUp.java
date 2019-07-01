package uiMain;

import BaseDatos.Datos;

public class SignUp extends OpcionDeMenu {

	@Override
	public void ejecutar() {
		Datos.escribirUsuarios(null);
	}

	@Override
	public String toString() {
		return "Registrarse";
	}

}
