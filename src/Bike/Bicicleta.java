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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDanio() {
		return danio;
	}
	public void setDanio(boolean danio) {
		this.danio = danio;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Distribuidor getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	
		
	
}
