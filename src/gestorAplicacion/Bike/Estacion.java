package gestorAplicacion.Bike;
import java.util.Map.Entry;

import BaseDatos.Datos;
import gestorAplicacion.User.*;
//import java.util.ArrayList;

public class Estacion implements red{
	private String id;
	private String tipo;
	private boolean estado = true;
	private static boolean estadogeneral= true;
	private int cap_max;
	private Bicicleta[] bicicletas;
	private Moderador moderador;
	private byte cantBicis=0;
	
	public Estacion(String id, String tipo, boolean estado, int cap_max, Moderador moderador) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];
		this.moderador = moderador;
		BaseDatos.Datos.hashUsoE.put(id, 0);
		BaseDatos.Datos.hashEstacion.put(id, this);
	}
	
	public Estacion(String id, String tipo, boolean estado, int cap_max) {
		
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];
		BaseDatos.Datos.hashUsoE.put(id, 0);
		BaseDatos.Datos.hashEstacion.put(id, this);
	}
	
	public Estacion(String id, String tipo, String esta, String cap_max) {
		this.id = id;
		this.tipo = tipo;
		boolean estado = Boolean.parseBoolean(esta);
		this.estado = estado;
		int aux = Integer.parseInt(cap_max);
		this.cap_max = aux;		
		this.bicicletas =  new Bicicleta[aux];
		BaseDatos.Datos.hashUsoE.put(id, 0);
		BaseDatos.Datos.hashEstacion.put(id, this);
	}
	
	public static Estacion getEstacionporId(String id) {
		
		return BaseDatos.Datos.hashEstacion.get(id);
	}
	
	public String getIde() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public boolean isEstado() {
		if(estadogeneral) {
			estado=true;
		}
		else {
			estado=false;
		}
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public int getCap_max() {
		return cap_max;
	}


	public void setCap_max(int cap_max) {
		this.cap_max = cap_max;
	}


	public Bicicleta[] getBicicletas() {
		return bicicletas;
	}


	public void setBicicletas(Bicicleta[] bicicletas) {
		this.bicicletas = bicicletas;
	}
	public void addBicicleta(Bicicleta bicicleta) {
		int i=0;
		
			while(i<cap_max) {
				if (bicicletas[i]==null) {
					bicicletas[i]=bicicleta;
					cantBicis++;
					
					break;
				}
				i++;
			}
		
		
	}


	public Moderador getModerador() {
		return moderador;
	}


	public void setModerador(Moderador moderador) {
		this.moderador = moderador;
	}
	
	public byte getCantBicis() {
		byte i=0;
		
		for(int y=0;y<bicicletas.length;y++) {
			if (bicicletas[y]!=null) {
				i++;
			}
	
		}
		this.cantBicis= i;
	
		return i;
	}

	public void cerrar_estaciones() {
		estadogeneral = false;
	}
	public void abrir_estaciones() {
		estadogeneral = true;
	}
	
	public boolean prestar(int idb, Usuario usuario){
		if((idb-1) <0 || (idb-1)> cap_max || cantBicis==0 || !this.isEstado()) {
			return false;
		}
		else if(bicicletas[idb-1] == null) {
			return false;
		}		
		else {
			usuario.setBicicleta(bicicletas[idb-1]);
			bicicletas[idb-1].setUsuario(usuario);
			bicicletas[idb-1].setEstacion(null);
			bicicletas[idb-1] = null;
			int valor = usuario.getTarjeta().getSaldo() - 500;
			usuario.getTarjeta().setSaldo(valor);
			cantBicis--;
			
			
            return true;
		}
	}
	
	public boolean prestar(int idb, Moderador moderador){
		if((idb-1) <0 || (idb-1)> cap_max || cantBicis==0 || !this.isEstado()) {
			return false;
		}
		else if(bicicletas[idb-1] == null) {
			return false;
		}
		else {
			moderador.setBicicleta(bicicletas[idb-1]);
			bicicletas[idb-1].setUsuario(moderador);
			bicicletas[idb-1].setEstacion(null);
			bicicletas[idb-1] = null;

			cantBicis--;
            return true;
		}
	}
	
	public boolean recibir(Bicicleta bicicleta){
        if((this.getCantBicis()==cap_max) || !this.isEstado()) {
        	return false;
        }
        else {
        	this.addBicicleta(bicicleta);
        	return true;
        }
		
	}
	
	public Bicicleta sacarBicicletas() {
		for(int y=0;y<bicicletas.length;y++) {
			if (bicicletas[y]!=null) {
				Bicicleta aux = bicicletas[y]; 
				bicicletas[y].setEstacion(null);
				bicicletas[y] = null;
				return aux;
			}
			
		}
		return null;
	}
	public static void nivelar() {
		for(Entry<String, Estacion> est: Datos.hashEstacion.entrySet()) {
			Estacion esta = est.getValue();
			if(esta.getCantBicis()==0) {
				Distribuidor.transportar(esta);
			}
		}
		
	}
	
	public boolean isFull() {
		if (this.getCantBicis()==cap_max) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Estacion \n [id = " + id + "\n Tipo = " + tipo+ "\n moderador=" + moderador + "\n Bicis= "+ cantBicis+"] \n";
	}
}
