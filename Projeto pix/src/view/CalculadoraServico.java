package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CalculadoraServico extends JFrame {
	Locale localeBR = new Locale("pt","BR");
	
	
	double remuneracao, custo, hora, valor, total;
	int cargaHoraria, estimativa;
	
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRemuneracao;
	private JTextField txtCusto;
	private JTextField txtCargaHoraria;
	private JTextField txtEstimativa;
	private JLabel lblValor;
	private JLabel lblTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraServico frame = new CalculadoraServico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculadoraServico() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CalculadoraServico.class.getResource("/img/5027823_calculator_coin_dollar_money_icon.png")));
		setTitle("Calculadora de serviço  de TI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Renumeração mensal pretendida:");
		lblNewLabel.setBounds(10, 25, 212, 14);
		contentPane.add(lblNewLabel);

		txtRemuneracao = new JTextField();
		txtRemuneracao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// event digitacao...
				// definir caracteres permitidos (que podem ser digitados)
				String caracteres = "0123456789.";
				// definir caractere nao permitido
				if (!caracteres.contains(e.getKeyChar() + "")) {
					// ignorar
					e.consume();
				}
			}
		});
		txtRemuneracao.setBounds(206, 22, 144, 20);
		contentPane.add(txtRemuneracao);
		txtRemuneracao.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Custo Operacional mensal:");
		lblNewLabel_1.setBounds(10, 85, 212, 14);
		contentPane.add(lblNewLabel_1);

		txtCusto = new JTextField();
		txtCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});
		txtCusto.setBounds(172, 82, 144, 20);
		contentPane.add(txtCusto);
		txtCusto.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Carga horária mensal de trabalho:");
		lblNewLabel_2.setBounds(10, 146, 212, 14);
		contentPane.add(lblNewLabel_2);

		txtCargaHoraria = new JTextField();
		txtCargaHoraria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				
				String caracteres = "0123456789.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					
					e.consume();
				}
			}

		});
		txtCargaHoraria.setBounds(206, 143, 144, 20);
		contentPane.add(txtCargaHoraria);
		txtCargaHoraria.setColumns(10);

		JButton btnCalcular = new JButton("");
		btnCalcular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCalcular
				.setIcon(new ImageIcon(CalculadoraServico.class.getResource("/img/9165696_calculator_math_icon.png")));
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calcular();
			}
		});
		btnCalcular.setBounds(10, 197, 89, 57);
		contentPane.add(btnCalcular);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();

			}
		});
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setIcon(new ImageIcon(
				CalculadoraServico.class.getResource("/img/9104211_backspace_delete_back_remove_previous_icon.png")));
		btnLimpar.setBounds(133, 197, 89, 57);
		contentPane.add(btnLimpar);

		JLabel lblNewLabel_3 = new JLabel("Valor da Hora:");
		lblNewLabel_3.setBounds(23, 265, 108, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Estimativa de horas de serviço:");
		lblNewLabel_4.setBounds(23, 315, 199, 14);
		contentPane.add(lblNewLabel_4);

		txtEstimativa = new JTextField();
		txtEstimativa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}

		});
		txtEstimativa.setBounds(209, 312, 89, 20);
		contentPane.add(txtEstimativa);
		txtEstimativa.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Total a ser cobrado do cliente:");
		lblNewLabel_5.setBounds(23, 362, 199, 14);
		contentPane.add(lblNewLabel_5);

		lblValor = new JLabel("R$ 0");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValor.setBounds(103, 265, 76, 14);
		contentPane.add(lblValor);

		lblTotal = new JLabel("R$ 0");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(196, 362, 154, 14);
		contentPane.add(lblTotal);
		
		JButton btnTotal = new JButton("");
		btnTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTotal.setIcon(new ImageIcon(CalculadoraServico.class.getResource("/img/5760367_equal_equality_math_icon (1).png")));
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotal();
			}
		});
		btnTotal.setBounds(318, 293, 89, 41);
		contentPane.add(btnTotal);
	}

	private void calcular() {
				if (txtRemuneracao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a renumeração mensal pretendida");
			txtRemuneracao.requestFocus();
		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o custo operacional mensal");
			txtCusto.requestFocus();
		} else if (txtCargaHoraria.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o carga horaria mensal");
			txtCargaHoraria.requestFocus();
	
		} else {
			
			remuneracao = Double.parseDouble(txtRemuneracao.getText());
			custo = Double.parseDouble(txtCusto.getText());
			estimativa = Integer.parseInt(txtEstimativa.getText());
			cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());
			
			valor = (remuneracao + (0.3 * remuneracao) + (0.2 * remuneracao) + custo) / cargaHoraria;
			total = valor * estimativa;
			lblValor.setText(dinheiro.format(valor));
			lblTotal.setText(dinheiro.format(total));
			
		}
		

	}

	private void LimparCampos() {
		txtRemuneracao.setText(null);
		txtCusto.setText(null);
		txtCargaHoraria.setText(null);
		txtEstimativa.setText(null);
		lblValor.setText(null);
		lblTotal.setText(null);
		
	}
	
/**
 * metodo responsavel pelo calculo do valor total do servico
 */
private void calcularTotal() {
	if (txtEstimativa.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha a estima de horas");
		txtEstimativa.requestFocus();
	}else {
	
	estimativa = Integer.parseInt(txtEstimativa.getText());
	total = estimativa * valor;
	DecimalFormat formatador = new DecimalFormat("#0.00");
	lblTotal.setText("R$"+ formatador.format(valor));
}
}
}


