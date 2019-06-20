package Bike;
import User.*;
import java.util.ArrayList;


public class Estacion {
	private int id;
	private String tipo;
	private boolean estado = false;
	private static boolean estadogeneral= true;
	private int cap_max;
	private ArrayList<Bicicleta> bicicletas = new ArrayList<>();
	private Moderador moderadores;
	
	
	
	public Estacion(int id, String tipo, boolean estado, int cap_max, ArrayList<Bicicleta> bicicletas, Moderador moderadores) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas = bicicletas;
		this.moderadores = moderadores;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public boolean isEstado() {
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


	public ArrayList<Bicicleta> getBicicletas() {
		return bicicletas;
	}


	public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}


	public Moderador getModeradores() {
		return moderadores;
	}


	public void setModeradores(Moderador moderadores) {
		this.moderadores = moderadores;
	}


	public static void cerrar_estaciones() {
		estadogeneral = false;
	}
	public static void abrir_estaciones() {
		estadogeneral = true;
	}	
	public boolean prestar(int id){
            return true;
        }
        public void devolver(Bicicleta bicicleta){
            
        }
}
