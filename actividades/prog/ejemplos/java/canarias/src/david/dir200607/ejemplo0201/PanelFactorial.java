package david.dir200607.ejemplo0201;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;

public class PanelFactorial extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblEntrada = null;
	private JTextField txtEntrada = null;
	private JButton btnCalcular = null;
	private JLabel lblResultado = null;
	private JLabel lblTitulo = null;
	/**
	 * This is the default constructor
	 */
	public PanelFactorial() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblTitulo = new JLabel();
		lblTitulo.setText("Calcular el factorial de un número");
		lblTitulo.setForeground(Color.black);
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.gridy = 3;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 1;
		gridBagConstraints3.gridy = 3;
		lblResultado = new JLabel();
		lblResultado.setText("Resultado");
		lblResultado.setVisible(false);
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 1;
		gridBagConstraints2.gridy = 2;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		lblEntrada = new JLabel();
		lblEntrada.setText("Introducir un número");
		lblEntrada.setForeground(Color.blue);
		lblEntrada.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.setSize(227, 103);
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(227, 103));
		this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
		this.setToolTipText("panel factorial");
		this.add(lblTitulo, null);
		this.add(lblEntrada, null);
		this.add(getTxtEntrada(), null);
		this.add(getBtnCalcular(), null);
		this.add(lblResultado, null);
	}

	/**
	 * This method initializes txtEntrada	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEntrada() {
		if (txtEntrada == null) {
			txtEntrada = new JTextField();
			txtEntrada.setText("");
			txtEntrada.setBackground(new Color(255, 255, 204));
			txtEntrada.setPreferredSize(new Dimension(159, 15));
			txtEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					activarBoton();
				}
			});
		}
		return txtEntrada;
	}

	/**
	 * This method initializes btnCalcular	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCalcular() {
		if (btnCalcular == null) {
			btnCalcular = new JButton();
			btnCalcular.setEnabled(false);
			btnCalcular.setText("Calcular el factorial");
			btnCalcular.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ejecutarAccion();
				}
			});
		}
		return btnCalcular;
	}

	private void activarBoton() {
		this.btnCalcular.setEnabled(true);
		this.lblResultado.setVisible(false);
	}
	
	private void ejecutarAccion() {
		long numero = Long.parseLong(this.txtEntrada.getText());
		long factorial = FuncionesMatematicas.calcularFactorial(numero);
		this.lblResultado.setText("El factorial de "+numero+" es "+factorial);
		this.lblResultado.setVisible(true);
		this.txtEntrada.setText("");
		this.btnCalcular.setEnabled(false);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
