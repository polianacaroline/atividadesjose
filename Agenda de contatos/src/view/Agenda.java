package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Agenda extends JFrame {
//instanciar objetos JDBC
	DAO dao = new DAO();

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtFone;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JLabel lblStatus;

	private String read;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnApagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
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
	public Agenda() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Agenda de contatos");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/299047_address_book_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 309);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel b = new JLabel("ID:");
		b.setBounds(21, 11, 52, 14);
		contentPane.add(b);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(83, 8, 131, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(21, 36, 52, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fone:");
		lblNewLabel_1_1.setBounds(21, 64, 52, 14);
		contentPane.add(lblNewLabel_1_1);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(83, 61, 131, 20);
		contentPane.add(txtFone);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(83, 33, 223, 20);
		contentPane.add(txtNome);
		//uso do validador
		txtNome.setDocument(new Validador(50));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1_1.setBounds(21, 92, 52, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(83, 89, 325, 20);
		contentPane.add(txtEmail);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorder(null);
		btnAdicionar.setToolTipText("Adicionar contato");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Agenda.class.getResource("/img/addcontact.png")));
		btnAdicionar.setBounds(21, 137, 48, 48);
		contentPane.add(btnAdicionar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
				
			}
		});
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Deletar contato");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Agenda.class.getResource("/img/delet.png")));
		btnExcluir.setBounds(112, 137, 48, 48);
		contentPane.add(btnExcluir);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarContato();// enter...
			}
		});
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setToolTipText("Editar contato");
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Agenda.class.getResource("/img/edit.png")));
		btnEditar.setBounds(206, 137, 48, 48);
		contentPane.add(btnEditar);

		btnApagar = new JButton("");
		btnApagar.setContentAreaFilled(false);
		btnApagar.setBorder(null);
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnApagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnApagar.setIcon(
				new ImageIcon(Agenda.class.getResource("/img/2064480_education_eraser_learn_student_study_icon.png")));
		btnApagar.setToolTipText("Apagar");
		btnApagar.setBorderPainted(false);
		btnApagar.setBounds(294, 137, 63, 48);
		contentPane.add(btnApagar);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento clicar no botão
				buscar();
			}
		});
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setToolTipText("Procurar");
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(Agenda.class.getResource("/img/zoom.png")));
		btnPesquisar.setBounds(316, 26, 41, 41);
		contentPane.add(btnPesquisar);

		lblStatus = new JLabel("");
		lblStatus.setToolTipText("");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dataoff.png")));
		lblStatus.setBounds(413, 211, 48, 48);
		contentPane.add(lblStatus);
		// substituir o click pela tecla <ENTER> em um botão
		getRootPane().setDefaultButton(btnPesquisar);

		JButton btnSobre = new JButton("");
		btnSobre.setBorderPainted(false);
		btnSobre.setIcon(new ImageIcon(Agenda.class.getResource("/img/about.png")));
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clicar no botão
				// mostra a janela sobre
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setBounds(372, 36, 89, 45);
		contentPane.add(btnSobre);

	}// FIM DO CONSTRUTOR

	/**
	 * Método responsável por limpar os campos e setar os botoes
	 */
	;private void limparCampos() {
		txtID.setText(null);
		txtFone.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}// fim do metodo limpar campos()

	/**
	 * método responsavel por exibir o status da conexão
	 */
	private void status() {
		try {
			// abrir a conexão
			con = dao.conectar();
			if (con == null) {
				// system.out.println("erro de conexão");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dataoff.png")));

			} else {
				// System.out.println("banco conectado");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dataon.png")));

			}
			// nunca esquecer de fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do metodo status ()

	/**
	 * metodo para buscar um contato plo nome
	 */

	private void buscar() {
		// dica - testar o evento primeiro
		// System.out.println("teste do botão buscar");

		// Criar uma variável com a query (instrução do banco)
		String read = "select * from contatos where nome = ?";
		// tratamento de exceções
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a execução da query(instrução sql - CRUD Read)
			// O parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText());
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				// preencher as caixas de texto com as informações
				txtID.setText(rs.getString(1)); // 1º campo da tabela ID
				txtFone.setText(rs.getString(3)); // 3º campo (Fone)
				txtEmail.setText(rs.getString(4)); // 4º campo (Email)
				//validacao (liberacao dos botoes alterar e excluir)
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				
				// se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Contato inexistente");
				btnAdicionar.setEnabled(true);
			}
			// fechar a conexão (IMPORTANTE!)
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do método buscar

	/**
	 * PRA VER SE FUNCIONA método para adicionar um novo cobtato E SO UM TESTE
	 */
	private void adicionar() {
		System.out.println("teste");
		// systen.out.println("teste")
		// validacao de campos obrigatorios

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do contato");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o fone de contato");
			txtFone.requestFocus();
		} else {
			// logica principal
			// CRUD Create
			String create = "insert into contatos(nome,fone,email) values (?,?,?)";
			// tratamento de excecoes
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a execucao da query(instrucao sql - CRUD create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				// executar a query(instrucao sql (CRUD - Create)
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "Contato adicionado");
				// lpar campos
				limparCampos();
				// fechar a conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

// fim do metodo adicionar
	/**
	 * Método para editar um contato (ATENÇÃO)
	 */
	// ADICIONAR BOTAO, TESTAR BOTAO ADICIONAR
	private void editarContato() {
		// System.out.println("Teste do botão");
		// validacao dos campos obrigatorio
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato");
			txtNome.requestFocus();

		} else if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o fone do contato");
			txtNome.requestFocus();
		} else {
			// logica principal
			// CRUD -UPDATE
			String update = "update contatos set nome=?,fone=?,email=? where id=?";
			// tratamento de excecoes
			try {
				// abrir conexão
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtID.getText());
				// execuatra a query
				pst.executeUpdate();
				// confirmar para o usuario
				JOptionPane.showMessageDialog(null, "Dados do contato editados com sucesso!!");
				// limpar campos
				limparCampos();
				// fechar Campos
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}// fim do método editar Contato

	/**
	 * método usado para excluir contato
	 */
	private void excluirContato() {
		// System.out.println("teste d botão excluir");
//fim do método excluircontato
		// validacao de exclusao
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusao deste contato?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// crud - delete
			String delete = "delete from contatos where id=?";
			// tratamento de excecoes
			try {
				// abrir conexao
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(delete);
				// susbtituir a ? pelo id do contato
				pst.setString(1, txtID.getText());
				// executar a query
				pst.executeUpdate();
				// limparCampos
				limparCampos();
				// exibir uma mensagem ao usuario
				JOptionPane.showMessageDialog(null, "Contato excluido");
				// fechar a conxão
				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}

		}
	}// fim do método excluirContato

// FIM DE CÓDIGO
}
