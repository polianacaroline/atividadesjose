package br.com.projeto.papelaria.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.projeto.papelaria.dao.DAOUsuario;
import br.com.projeto.papelaria.dominio.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JTextField txtUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtNome = new JLabel("Nome");
		txtNome.setBounds(10, 24, 62, 14);
		contentPane.add(txtNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 92, 62, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(99, 89, 242, 20);
		contentPane.add(txtSenha);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(99, 21, 242, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnlogar = new JButton("logar");
		btnlogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario us =new Usuario();
				us.setNomeUsuario(txtUsuario.getText());
				us.setSenha(txtSenha.getText());
				
				DAOUsuario du = new DAOUsuario();
				if(du.logar(us)) {
					
					Principal p = new Principal();
					p.setState(MAXIMIZED_BOTH);
					p.setVisible(true);
					setVisible(false);
					
					
				} 
				else {
					JOptionPane.showInternalMessageDialog(null, "Usuário ou senha incorretos");
				}
				
			}
		});
		btnlogar.setBounds(38, 178, 89, 23);
		contentPane.add(btnlogar);
	}

}
