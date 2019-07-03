package uiMain;

public class Deuda extends OpcionDeMenu {

	@Override
	public void ejecutar() {
		if(Main.user.isDeuda()) {
			System.out.println(Main.user.getNombre()+ ", tiene una deuda en MiBici con valor de $" + Main.user.getDeuda() );
		}else {
			System.out.println( "Usted esta libre de deudas con MiBici");
		}
		

	}

	@Override
	public String toString() {
		return "Deuda de Multas";
	}

}
