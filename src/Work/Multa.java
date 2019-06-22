package Work;

import Bike.*;
import User.*;

public class Multa {

	private final String id;
	private final int precio;
	private Tarjeta tarjeta;

	public Multa(String id, int precio, Tarjeta tarjeta) {
		this.id = id;
		this.precio = precio;
		this.tarjeta = tarjeta;
	}

	public String getId() {
		return id;
	}

	public int getPrecio() {
		return precio;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public static void main(String[] args) {
		Estacion Estadio= new Estacion("01", "M", true, 15);
		Persona Lopero= new Moderador("Juan José Lopera Duque", (byte) 18, 1, "M", "0418",Estadio);
		Persona Orlando= new Usuario("José Orlando Tovar", (byte)18, 2, "M", "1234", 5000);
		Bicicleta B1= new Bicicleta(1, Estadio);
		((Usuario)Orlando).prestar(Estadio);
		System.out.println(((Usuario)Orlando).devolver(Estadio));
	}
}
