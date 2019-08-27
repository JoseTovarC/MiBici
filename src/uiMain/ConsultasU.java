package uiMain;

import java.util.ArrayList;

public class ConsultasU extends OpcionDeMenu {
	

	public ConsultasU(String key){
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
		ArrayList<OpcionDeMenu> Options = new ArrayList<OpcionDeMenu>() {
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
