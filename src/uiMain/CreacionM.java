package uiMain;

import java.util.ArrayList;

public class CreacionM extends OpcionDeMenu {

	public CreacionM(String key) {
		super(key);
	}
	
	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> consultOptions = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("15"));
				add(BaseDatos.Datos.operations.get("16"));
				add(BaseDatos.Datos.operations.get("17"));
			}
		};

		MenuDeConsola ConsultMenu = new MenuDeConsola(consultOptions);

		ConsultMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
