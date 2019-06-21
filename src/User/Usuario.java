package User;

import Bike.*;
import Work.*;
import java.util.*;

public class Usuario extends Persona {
    Scanner ent=new Scanner(System.in);

    private Multa multa;
    private boolean deuda = false;
    private Bicicleta bicicleta;
    private Tarjeta tarjeta;

    public Usuario(String nombre, byte edad, long id, String genero, long clave, int saldo) {
        super(nombre, edad, id, genero, clave);
        Tarjeta tarjeta = new Tarjeta(saldo, this);
        this.tarjeta = tarjeta;
        //main.usuarioh.add(id, this);
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public boolean isDeuda() {
        return deuda;
    }

    public void setDeuda(boolean deuda) {
        this.deuda = deuda;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void finalize() {
        System.out.println("Se ha eliminado el usuario " + nombre);
        // borrar tarjeta
    }
    
    // Esto aqui, fue lo que hice yo
    
    public void prestar(Estacion estacion) {

		Bicicleta[] bicis;
		bicis = estacion.getBicicletas();
		int cantidadb = 0;
		for (int i = 0; i < estacion.getCap_max(); i++) {
			if (bicis[i] != null) {
				cantidadb++;
			}
		}
		if ((this.bicicleta == null) && !deuda) {

			if (cantidadb > 0 && tarjeta.getSaldo() >= 500) {
				for (int i = 0; i < bicis.length; i++) {
					System.out.print((i+1) + ". " + bicis[i]);
					System.out.println(" ");
				}
				int idB = ent.nextInt();
				if(estacion.prestar(idB, this)) {
					System.out.println("Prestamo aceptado");
					System.out.println(super.nombre + " posee la bicicleta " + idB+ ". " + bicicleta.getId() + " de la estaciÛn " + estacion.getId());
					tarjeta.setSaldo(tarjeta.getSaldo()-500);
				}else {
					System.out.println("Prestamo no aceptado");
					System.out.println(" No Hay Bicicletas en la posiciÛn elegida o posiciÛn fuera de rango, etc.");
					
				}
				

			
			}else if(tarjeta.getSaldo() < 500){
				System.out.println("Saldo Insuficiente");
			}
		
		}else if(this.bicicleta != null) {
			System.out.println("Tiene un prestamo vigente");
		}else {
			System.out.println("Tiene una deuda pendiente");
		}

	}
    
    // Esto aqui comentado, fue lo echo por usted.
    
    /*public void prestar(Estacion estacion) {
        if (bicicleta== null && !deuda) {
            if (estacion.getBicicletas().size()>0) { 
                if (tarjeta.getSaldo()>500) {
                    int idB= ent.nextInt();
                    if(estacion.prestar(idB)){
                        System.out.println("Prestamo aceptado");
                    }
                    else{
                        System.out.println("Bicicleta no encontrada");
                    }
                }
                else {
                    System.out.println("saldo insuficiente");
                }
            }
            else{
                System.out.println("Estaci√≥n vac√≠a");
            }
        }
        else if (deuda) {
            System.out.println("Tiene una deuda");
        }
        
        else {
            System.out.println("ya tiene un prestamo");
        }
    }

    public void devolver(Estacion estacion, Date initialtime) {
        if (bicicleta!=null && estacion.getBicicletas().size()<estacion.getCap_max() && estacion.isEstado()) {
            estacion.devolver(bicicleta);
            
            //check multa por medio de bicicleta
        } else {
            System.out.println("Usted no tiene un prestamo actualmente");
        }
    }*/

    public void recargarT(int q$) {
        tarjeta.recargar(q$);
        System.out.println("Recarga de: $" + q$ + " realizada correctamente.");
    }

    public void pagarM() {
        if (deuda /*&& tarjeta.saldo>multa.costo*/) {
            tarjeta.pagarM();
            System.out.println("Pago realizado.");
        } else if (deuda) {
            System.out.println("Saldo insuficiente.");
        } else {
            System.out.println("Usted no tiene multas o deudas actualmente.");
        }
    }
}
