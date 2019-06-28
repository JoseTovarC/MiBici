package gestorAplicación.Bike;
import gestorAplicación.User.*;
public class Bicicleta {
	
	private int id;
	private boolean danio = false;
	private Usuario usuario;
	private Distribuidor distribuidor;
	private Estacion estacion;
	public Bicicleta(int id, Estacion estacion) {
		System.out.println("Crea nueva bicicleta");
		this.id=id;
		this.setEstacion(estacion);
		estacion.addBicicleta(this);
	}
	public Bicicleta(int id/*, Usuario usuario*/,Distribuidor distribuidor) {
		System.out.println("Si");
		this.id = id;
		//this.setUsuario(usuario);
		this.distribuidor = distribuidor;
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
	/*public Distribuidor getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}*/
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	
	
	
	@Override
	public String toString() {
		return "Bicicleta \n [id = " + id + "\n daño = " + danio + "\n usuario=" + usuario +  "] \n";
	}
	public void daniar() {
		this.danio = true;
		// Cuando se cree la Clase distribuidor, se le debe pedir al distribuidosr que lo repare
		
	}
	
}
