package gestorAplicaci�n.Work;

import gestorAplicaci�n.Bike.*;
import gestorAplicaci�n.User.*;

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
}
