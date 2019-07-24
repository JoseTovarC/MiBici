package gestorAplicacion.Bike;
import gestorAplicacion.User.*;
public class Bicicleta {
	
	private int id;
	private boolean danio = false;
	private Usuario usuario;
	private Distribuidor distribuidor;
	private Estacion estacion;
	public Bicicleta(int id, Estacion estacion) {
		this.id=id;
		this.setEstacion(estacion);
		estacion.addBicicleta(this);
		BaseDatos.Datos.hashUsoB.put(id, 0);
		BaseDatos.Datos.hashBicicleta.put(id,this);
	}
	public Bicicleta(int id,Distribuidor distribuidor) {
		
		this.id = id;
		//this.setUsuario(usuario);
		this.setDistribuidor(distribuidor);
		BaseDatos.Datos.hashUsoB.put(id, 0);
		BaseDatos.Datos.hashBicicleta.put(id,this);
		this.estacion = Estacion.getEstacionporId("0");
	}
	public Bicicleta(int id, Estacion estacion, Distribuidor distribuidor) {
		
		this.id = id;
		//this.setUsuario(usuario);
		this.setDistribuidor(distribuidor);
		this.estacion = estacion;
		BaseDatos.Datos.hashUsoB.put(id, 0);
		BaseDatos.Datos.hashBicicleta.put(id,this);
	}
	public Bicicleta(String ide, String danio, String IdUsuario, String Iddistribuidor, String Idestacion) {
		int id = Integer.parseInt(ide);
		boolean dan = Boolean.parseBoolean(danio);
		long idu = (long) Integer.parseInt(IdUsuario);
		this.id = id;
		this.danio = dan;
		if(idu!=0){
			this.usuario = (Usuario) Usuario.getUsuarioPorUsername(idu);
			this.usuario.setBicicleta(this);
		}
		if(!Iddistribuidor.equals("0")){
			this.distribuidor = BaseDatos.Datos.hashDistribuidor.get(Iddistribuidor);
			distribuidor.addBicicleta(this);
		}
		if(!Idestacion.equals("0")){
			this.estacion = BaseDatos.Datos.hashEstacion.get(Idestacion);
			estacion.addBicicleta(this);
		}
		
		BaseDatos.Datos.hashBicicleta.put(id,this);
		BaseDatos.Datos.hashUsoB.put(id, 0);
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
	public Distribuidor getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(Distribuidor distribuidor) {
		this.distribuidor = distribuidor;
	}
	
}
