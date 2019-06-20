package user;

class usuario extends persona{
  //multa multa
  boolean deuda=false;
  //bicicleta Bicicleta
  tarjeta Tarjeta;

  void usuario(){
    //super persona;
    //crear tarjeta
  }
  void finalize(){
    System.out.println("Se ha eliminado el usuario");
    //borrar tarjeta
  }
  void prestar(int idB){
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
  void devolver(int idE){
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
  void recargarT(int q$){
    Tarjeta.recargar(q$);
  }
  void pagarM(){
    /*
    se revisa el saldo de la tarjeta y se paga la multa existente (corriendo Tarjeta.pagarM())
    si no tiene multas se retorna "Actualmente no tiene multas"
    si tiene multas y se paga se retorna el print "Pago realizado"
    si tiene multas pero no puede pagarlo se retorna "Saldo insuficiente"
    */
  }
}

class tarjeta{
  int saldo;
  usuario Usuario;
  //multa multas

  void tarjeta(){
    //este viene desde el constructor de usuario
  }
  protected void finalize(){
    //viene desde el borrado de la persona, este método puede ser borrado
  }
  void recargar(int q$){
    saldo+=q$;
  }
  boolean pagarM(){
    /*
    if(multa>saldo){
      return false
    }
    else{
      */return true;
    //}
    
  }
  boolean pagarP(){
    //if (valor_prestamo<=saldo){
    return true;
    /*}
    else{
      return false
    }
    */
  }
  void recibirM(int idM){
    //se hace la conexión de la tarjeta a la multa y se cambia el estado del usuario de deuda false a true
  }
}
