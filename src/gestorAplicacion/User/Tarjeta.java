package gestorAplicacion.User;

import java.util.ArrayList;
import gestorAplicacion.Work.*;

public class Tarjeta {
	private int saldo;
	private final Usuario usuario;
	private ArrayList<Multa> multas = new ArrayList<>();

	//Constructores
	public Tarjeta(int saldo, Usuario usuario) {
		this.saldo = saldo;
		this.usuario = usuario;
	}
	public Tarjeta(Usuario usuario) {
		this(0, usuario);
	}

	//Getters & Setters
	public int getSaldo() {return saldo;}
	public void setSaldo(int saldo) {this.saldo = saldo;}

	public Usuario getUsuario() {return usuario;}

	public ArrayList<Multa> getMultas() {return multas;}
	public void setMultas(ArrayList<Multa> multas) {this.multas = multas;}

	protected void finalize() {}

	public void recargar(int q$) {
		saldo += q$;
	}

	public boolean pagarM() { //se pagan todas las multas
		if(usuario.isDeuda()) {
			if(usuario.getDeuda() <= saldo) {
				saldo -= usuario.getDeuda();
				usuario.delMultas();
				usuario.setDeuda(false);
				return true;
			}
		}
		return false;
	}
	public boolean pagarM(int id) { //se paga una sola multa
		if(usuario.isDeuda()) {
			Multa a=usuario.getMulta(id);
			if( a!=null && saldo>a.getPrecio()) {
				saldo -= a.getPrecio();
				usuario.delMulta(id);
				if (usuario.getDeuda()==0) {
					usuario.setDeuda(false);
				}
				return true;
			}
		}
		return false;
	}

	public boolean pagarP() {
		if (500<=saldo){
			saldo-=500;
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Tarjeta de "+ usuario.getNombre() + " con un saldo de :" + saldo ;
	}
	
	
}
