package vista;

import control.*;

public interface InterfazVista {
	void setControlador(Control c);

	void arranca(); // comienza la visualización

	double getCantidad(); // cantidad a convertir

	void escribeCambio(String s); // texto con la conversión
// Constantes que definen las posibles operaciones:

	//static final String AEUROS = "Pesos a Euros";
	//static final String APESOS = "Euros a Pesos";
}