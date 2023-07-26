package br.com.projeto.papelaria.view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.projeto.papelaria.dao.DAOUsuario;
import br.com.projeto.papelaria.dominio.Usuario;

public class JanelaUsuario extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaUsuario frame = new JanelaUsuario();
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
	public JanelaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Usuario");
		lblNewLabel.setBounds(10, 27, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(10, 94, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel txtNivelAcesso = new JLabel(" Nível Acesso");
		txtNivelAcesso.setBounds(10, 161, 82, 14);
		contentPane.add(txtNivelAcesso);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(173, 24, 228, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JComboBox cbo = new JComboBox();
		cbo.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User", "Sales", "Operator"}));
		cbo.setBounds(102, 157, 299, 22);
		contentPane.add(cbo);
		
		
		JButton btnGravar = new JButton("Gravar Usuario");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario us = new Usuario();
				us.setNomeUsuario(txtUsuario.getText());
				us.setSenha(txtSenha.getText());
				us.setNivelAcesso(cbo.getSelectedItem().toString());
				DAOUsuario du = new DAOUsuario();
			    JOptionPane.showMessageDialog(null, du.cadastro(us));
				
			}
		});
		btnGravar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGravar.setBounds(33, 205, 125, 23);
		contentPane.add(btnGravar);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(173, 91, 228, 20);
		contentPane.add(txtSenha);
	}
}
