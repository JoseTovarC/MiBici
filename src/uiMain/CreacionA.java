package uiMain;

import java.util.ArrayList;

public class CreacionA extends OpcionDeMenu {

	public CreacionA(String key) {
		super(key);
	}
	
	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> consultOptions = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("30"));
				add(BaseDatos.Datos.operations.get("31"));
			}
		};

		MenuDeConsola ConsultMenu = new MenuDeConsola(consultOptions);

		ConsultMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Creación";
	}

}
