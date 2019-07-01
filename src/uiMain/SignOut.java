package uiMain;

import gestorAplicacion.User.*;

public class SignOut  extends OpcionDeMenu{

	@Override
	public void ejecutar() {
		System.out.println("Adios");
		Main.usuario = Usuario.getUsuarioPorUsername((long)-1);
		
	}

	@Override
	public String toString() {
		return "Cerrar Sesión";
	}

}