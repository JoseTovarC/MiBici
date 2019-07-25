package uiMain;

import java.util.ArrayList;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class PerfilTarjeta extends OpcionDeMenu {

	public PerfilTarjeta(String key){
		super(key);
	}
	
	private static ArrayList<OpcionDeMenu> tarjetaOptionsDefault = new ArrayList<OpcionDeMenu>() {
		{
			add(BaseDatos.Datos.operations.get("11"));
			add(BaseDatos.Datos.operations.get("29"));
		}
	};
	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> tarjetaOptions = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("11"));
				add(BaseDatos.Datos.operations.get("29"));
			}
		};

		MenuDeConsola tarjetaMenu = new MenuDeConsola(tarjetaOptions);

		tarjetaMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		return "Perfil de la Tarjeta";
	}

}
