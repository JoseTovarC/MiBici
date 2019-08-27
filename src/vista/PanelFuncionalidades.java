package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelFuncionalidades extends JPanel {
	public JPanel panel1; // titulo norte
	public JPanel panel2; // Centro

	JLabel label1; // titulo Principal
	JLabel label2; // Descripcion
	JLabel label3; // Resultado
	
	public PanelFuncionalidades(String a, StringBuffer b) {
		this.setLayout(new BorderLayout());
		
		label1 = new JLabel("FUNCIONALIDAD", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 20));
		label2 = new JLabel(a, SwingConstants.CENTER);
		label2.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);

		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1, 50, 15));
		a = b.toString();
		label3 = new JLabel(a, SwingConstants.CENTER);
		label3.setFont(auxFont);
		panel2.add(label3);

		this.add(panel2);

		this.setVisible(true);
	}
}
