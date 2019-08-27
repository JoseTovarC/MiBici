package uiMain;

import java.util.ArrayList;

public class ConsultasA extends OpcionDeMenu {

	public ConsultasA(String key){
		super(key);
		Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("8"));
				add(BaseDatos.Datos.operations.get("18"));
				add(BaseDatos.Datos.operations.get("38"));
				add(BaseDatos.Datos.operations.get("4"));
			}
		};
	}
	
	
	
	

	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("8"));
				add(BaseDatos.Datos.operations.get("18"));
				add(BaseDatos.Datos.operations.get("38"));
				add(BaseDatos.Datos.operations.get("4"));
			}
		};

		MenuDeConsola ConsultMenu = new MenuDeConsola(Options);

		ConsultMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Consultas";
	}

}
