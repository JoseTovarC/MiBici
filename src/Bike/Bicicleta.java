package Bike;

public class Bicicleta {
	
	private int id;
	private boolean danio = false;
	private Usuario usuario;
	private Distribuidor distribuidor;
	private Estacion estacion;
	public Bicicleta(int id, boolean danio, Usuario usuario, Distribuidor distribuidor, Estacion estacion) {
		super();
		this.id = id;
		this.danio = danio;
		this.usuario = usuario;
		this.distribuidor = distribuidor;
		this.estacion = estacion;
	}
	
}
