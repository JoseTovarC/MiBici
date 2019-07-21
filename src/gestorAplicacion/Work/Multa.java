package gestorAplicacion.Work;

import gestorAplicacion.Bike.*;
import gestorAplicacion.User.*;

public class Multa {

	private final String id;
	private final String descripcion;
	private int precio;

	public Multa(String id, String descripcion, int precio) {
		this.id = id;
		this.precio = precio;
		this.descripcion = descripcion;

	}

	public String getId() {
		return id;
	}

	public int getPrecio() {
		return precio;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
