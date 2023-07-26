package br.com.projeto.papelaria.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import br.com.projeto.papelaria.dominio.Tipo;
import javax.swing.JButton;
import java.awt.Font;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtCpf;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtLogradouro;
	private JTextField txtTelefone;
	private JTextField txtCep;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cpf");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 56, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(300, 11, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 274, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Endereço");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 111, 66, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Logradouro");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(300, 56, 85, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Bairro");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(10, 159, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		txtNome = new JTextField();
		txtNome.setBounds(80, 8, 210, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(350, 8, 189, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(86, 108, 206, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(80, 53, 212, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(103, 344, 189, 20);
		contentPane.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(86, 156, 204, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(380, 53, 159, 20);
		contentPane.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tipo");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(300, 159, 34, 14);
		contentPane.add(lblNewLabel_7);
		
		JComboBox cboTipo = new JComboBox();
		cboTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		cboTipo.setModel(new DefaultComboBoxModel(Tipo.values()));
		cboTipo.setBounds(380, 166, 66, 22);
		contentPane.add(cboTipo);
		
		JLabel lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(10, 347, 85, 14);
		contentPane.add(lblNewLabel_8);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(86, 271, 206, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("CEP");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 219, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		txtCep = new JTextField();
		txtCep.setBounds(86, 216, 206, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setBounds(409, 343, 102, 23);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel_10 = new JLabel("Nº");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(306, 111, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(380, 124, 86, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
	}
}
