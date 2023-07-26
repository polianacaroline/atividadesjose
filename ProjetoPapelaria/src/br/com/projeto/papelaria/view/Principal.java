package br.com.projeto.papelaria.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private JDesktopPane contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 463);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(128, 128, 128));
		setJMenuBar(menuBar);
		
		JMenu menuCadastro = new JMenu("Cadastro");
		
		
		
			
			
	
		menuCadastro.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(menuCadastro);
		
		JMenuItem miniUsuario = new JMenuItem("Usuario");
		miniUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaUsuario janelausuario = new JanelaUsuario();
			//HABILITAR  MINIZAR 
          add(janelausuario);
          janelausuario.setClosable(true);
          janelausuario.setIconifiable(true);
          janelausuario.setResizable(true);
          
          janelausuario.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          janelausuario.setVisible(true);
			}
		
		});
		miniUsuario.setForeground(new Color(0, 0, 0));
		miniUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		menuCadastro.add(miniUsuario);
		
		JMenuItem miniCliente = new JMenuItem("Cliente");
		miniCliente.setForeground(new Color(0, 0, 0));
		miniCliente.setFont(new Font("Arial", Font.BOLD, 12));
		menuCadastro.add(miniCliente);
		
		JMenuItem miniFornecedor = new JMenuItem("Fornecedor");
		miniFornecedor.setForeground(new Color(0, 0, 0));
		miniFornecedor.setFont(new Font("Arial", Font.BOLD, 12));
		menuCadastro.add(miniFornecedor);
		
		JMenuItem miniFuncionario = new JMenuItem("Funcionario");
		miniFuncionario.setForeground(new Color(0, 0, 0));
		miniFuncionario.setFont(new Font("Arial", Font.BOLD, 12));
		menuCadastro.add(miniFuncionario);
		
		JMenuItem miniPedido = new JMenuItem("Pedido");
		miniPedido.setForeground(new Color(0, 0, 0));
		miniPedido.setFont(new Font("Arial", Font.BOLD, 12));
		menuCadastro.add(miniPedido);
		
		JMenuItem miniProduto = new JMenuItem("Produto");
		miniProduto.setForeground(new Color(0, 0, 0));
		miniProduto.setFont(new Font("Arial", Font.BOLD, 12));
		menuCadastro.add(miniProduto);
		
		JMenu menuConsulta = new JMenu("Consulta");
		menuConsulta.setFont(new Font("Arial", Font.BOLD, 13));
		menuBar.add(menuConsulta);
		
		JMenuItem miniPagamento = new JMenuItem("Pagamento");
		miniPagamento.setFont(new Font("Arial", Font.BOLD, 12));
		menuConsulta.add(miniPagamento);
		
		JMenuItem Usuario = new JMenuItem("Usuario");
		Usuario.setFont(new Font("Arial", Font.BOLD, 12));
		menuConsulta.add(Usuario);
		
		JMenuItem Cliente = new JMenuItem("Cliente");
		Cliente.setFont(new Font("Arial", Font.BOLD, 12));
		menuConsulta.add(Cliente);
		
		JMenuItem Fornecedor = new JMenuItem("Fornecedor");
		Fornecedor.setFont(new Font("Arial", Font.BOLD, 12));
		menuConsulta.add(Fornecedor);
		
		JMenuItem Pedido = new JMenuItem("Pedido");
		Pedido.setFont(new Font("Arial", Font.BOLD, 12));
		menuConsulta.add(Pedido);
		
		JMenuItem Produto = new JMenuItem("Produto");
		Produto.setFont(new Font("Arial", Font.BOLD, 12));
		menuConsulta.add(Produto);
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
		}
