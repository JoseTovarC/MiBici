package uiMain;

public class PerfilTarjeta extends OpcionDeMenu {

	@Override
	public void ejecutar() {
		System.out.println(Main.user.getTarjeta());

	}

	@Override
	public String toString() {
		return "Perfil de la Tarjeta";
	}

}
