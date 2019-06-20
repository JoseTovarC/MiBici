package User;
import Bike.*;

public class Usuario /*extends Persona*/{
  //private Multa multa
  private boolean deuda = false;
  private Bicicleta bicicleta;
  private Tarjeta tarjeta;

  
  public Usuario(/*Aqui Van los atributoas del padre Persona*/boolean deuda, Bicicleta bicicleta, Tarjeta tarjeta) {
	super();
	this.deuda = deuda;
	this.bicicleta = bicicleta;
	this.tarjeta = tarjeta;
}
public void usuario(){
    //super persona;
    //crear tarjeta
  }
  public void finalize(){
    System.out.println("Se ha eliminado el usuario");
    //borrar tarjeta
  }
  public void prestar(int idB){
    /*
    if != bicicleta{
      if chequear saldo y multas{
         bicicleta conectar a persona idB
         cambiar estacion
      }
      else{
        saldo insuficiente o multas
      }
      print prestamo aceptado
    }
    else{
      ya tiene un prestamo
    }
    */
  }
  public void devolver(int idE){
    /*
    if != bicicleta{
      no se puede devolver nada...
      }
    else{
       desvincula bicicleta de usuario
       vincula bicicleta a estacion
       chequea multa por tiempo u otras
    }    
    */
  }
  public void recargarT(int q$){
    tarjeta.recargar(q$);
  }
  public void pagarM(){
    /*
    se revisa el saldo de la tarjeta y se paga la multa existente (corriendo Tarjeta.pagarM())
    si no tiene multas se retorna "Actualmente no tiene multas"
    si tiene multas y se paga se retorna el print "Pago realizado"
    si tiene multas pero no puede pagarlo se retorna "Saldo insuficiente"
    */
  }
}


