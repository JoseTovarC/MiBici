package uiMain;

import java.util.ArrayList;
import BaseDatos.Datos;

public class Consultas extends OpcionDeMenu {


	public ArrayList<OpcionDeMenu> Options;
	public Consultas(String key){
		super(key);
		Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("6"));
				add(BaseDatos.Datos.operations.get("7"));
				add(BaseDatos.Datos.operations.get("8"));
			}
		};
	}
	
	
	

	@Override
	public void ejecutar() {
		Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("6"));
				add(BaseDatos.Datos.operations.get("7"));
				add(BaseDatos.Datos.operations.get("8"));
			}
		};

		MenuDeConsola ConsultMenu = new MenuDeConsola(Options);

		ConsultMenu.lanzarMenu();
	}
	



	@Override
	public String toString() {
		return "Consultas";
	}

}
