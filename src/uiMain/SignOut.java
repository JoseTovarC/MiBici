package uiMain;

import gestorAplicacion.User.*;

public class SignOut  extends OpcionDeMenu{

	public SignOut(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {
		System.out.println("Adios");
		Main.user = Usuario.getUsuarioPorUsername((long)0);
		
	}

	@Override
	public String toString() {
		return "Cerrar Sesi�n";
	}

}