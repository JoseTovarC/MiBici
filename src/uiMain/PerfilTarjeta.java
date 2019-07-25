package uiMain;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class PerfilTarjeta extends OpcionDeMenu {

	public PerfilTarjeta(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
		System.out.println(aux.getTarjeta());

	}

	@Override
	public String toString() {
		return "Perfil de la Tarjeta";
	}

}
