package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import model.DAO;

public class Produtos extends JDialog {
	private JTextField txtBarras;
	private JTextField txtID;
	private PreparedStatement pst;
	private ResultSet rs;
	private Connection con;
	DAO dao = new DAO();

	private FileInputStream fis;

	private int tamanho;
	private JLabel lblFoto;
	private JTextField txtDesc;
	private JTextField txtEstoqueMin;
	private JTextField txtEstoque;
	private JTextField txtArmazem;
	private JTextField txtValor;
	private JComboBox cboMedida;
	private JTextField txtIDFor;
	private JList listProd;
	private JScrollPane scrollPaneProd;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnBorracha;
	private JTextField txtNome;
	private JScrollPane scrollPaneNome;
	private JList listNome;
	private JTextField txtLote;
	private JLabel lblLote;
	private JTextField txtFabricante;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtLucro;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JDateChooser dateEnt;
	private JDateChooser dateVali;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Produtos() {
		setTitle("Produtos");
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPaneNome.setVisible(false);
				scrollPaneProd.setVisible(false);
			}
		});
		getContentPane().setBackground(SystemColor.text);
		getContentPane().setLayout(null);

		JButton btnFoto = new JButton("Carregar foto");
		btnFoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
				btnAdicionar.setEnabled(true);

			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

			}
		});

		scrollPaneProd = new JScrollPane();
		scrollPaneProd.setVisible(false);

		scrollPaneNome = new JScrollPane();
		scrollPaneNome.setVisible(false);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescricao.setBounds(32, 192, 156, 19);
		getContentPane().add(lblDescricao);
		scrollPaneNome.setBounds(22, 165, 521, 29);
		getContentPane().add(scrollPaneNome);

		listNome = new JList();
		scrollPaneNome.setViewportView(listNome);
		listNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarNome();
			}
		});
		listNome.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneProd.setBounds(316, 105, 227, 23);
		getContentPane().add(scrollPaneProd);

		listProd = new JList();
		listProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarForLista();
			}
		});
		scrollPaneProd.setViewportView(listProd);
		btnFoto.setForeground(new Color(0, 128, 255));
		btnFoto.setFont(new Font("Arial", Font.BOLD, 16));
		btnFoto.setBounds(867, 407, 141, 23);
		getContentPane().add(btnFoto);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(0, 0, 1098, 40);
		getContentPane().add(panel_1_1);
		panel_1_1.setLayout(null);

		JLabel lblTitulo = new JLabel("ESTOQUE");
		lblTitulo.setForeground(UIManager.getColor("Button.focus"));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBounds(349, 0, 87, 40);
		panel_1_1.add(lblTitulo);

		txtBarras = new JTextField();
		txtBarras.addKeyListener(new KeyAdapter() {
			// leitor de codigo de barra

			@Override

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarBarcode();
				}
			}
		});
		txtBarras.setFont(new Font("Arial", Font.PLAIN, 16));
		txtBarras.setColumns(10);
		txtBarras.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtBarras.setBackground(SystemColor.menu);
		txtBarras.setBounds(22, 76, 284, 29);
		getContentPane().add(txtBarras);

		JLabel codigobarra = new JLabel("Código de Barras*");
		codigobarra.setFont(new Font("Arial", Font.PLAIN, 16));
		codigobarra.setBounds(22, 51, 156, 19);
		getContentPane().add(codigobarra);

		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(789, 51, 285, 345);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblFoto = new JLabel("");
		lblFoto.setBounds(10, 89, 265, 256);
		panel.add(lblFoto);
		lblFoto.setIcon(new ImageIcon(Produtos.class.getResource("/img/bluecamera.png")));

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(10, 34, 218, 29);
		panel.add(txtID);
		txtID.setFont(new Font("Arial", Font.PLAIN, 16));
		txtID.setColumns(10);
		txtID.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtID.setBackground(SystemColor.activeCaptionBorder);

		JLabel lblID = new JLabel("Código do produto:");
		lblID.setBounds(10, 11, 236, 19);
		panel.add(lblID);
		lblID.setFont(new Font("Arial", Font.PLAIN, 16));

		JButton btnPesq = new JButton("");
		btnPesq.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesq.setContentAreaFilled(false);
		btnPesq.setBorderPainted(false);
		btnPesq.setIcon(
				new ImageIcon(Produtos.class.getResource("/img/510919_find_magnifying glass_search_zoom_icon.png")));
		btnPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProduto();
			}
		});
		btnPesq.setBounds(238, 31, 32, 32);
		panel.add(btnPesq);

		txtDesc = new JTextField();
		txtDesc.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDesc.setColumns(10);
		txtDesc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDesc.setBackground(SystemColor.menu);
		txtDesc.setBounds(22, 212, 360, 29);
		getContentPane().add(txtDesc);

		txtEstoqueMin = new JTextField();
		txtEstoqueMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}
			}
		});
		txtEstoqueMin.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEstoqueMin.setColumns(10);
		txtEstoqueMin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtEstoqueMin.setBackground(SystemColor.menu);
		txtEstoqueMin.setBounds(168, 325, 136, 29);
		getContentPane().add(txtEstoqueMin);

		JLabel lblEstoque = new JLabel("Estoque*");
		lblEstoque.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEstoque.setBounds(22, 300, 156, 19);
		getContentPane().add(lblEstoque);

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}

			}
		});
		txtEstoque.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEstoque.setColumns(10);
		txtEstoque.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtEstoque.setBackground(SystemColor.menu);
		txtEstoque.setBounds(22, 325, 136, 29);
		getContentPane().add(txtEstoque);

		JLabel lblDescricao_1_1 = new JLabel("Estoque Mínimo*");
		lblDescricao_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescricao_1_1.setBounds(168, 300, 156, 19);
		getContentPane().add(lblDescricao_1_1);

		JLabel lblDescricao_1_1_1 = new JLabel("Unidade de medida:");
		lblDescricao_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescricao_1_1_1.setBounds(316, 300, 156, 19);
		getContentPane().add(lblDescricao_1_1_1);

		cboMedida = new JComboBox();
		cboMedida.setModel(new DefaultComboBoxModel(new String[] { "UN", "CX", "PC", "Kg", "m" }));
		cboMedida.setBounds(314, 325, 78, 29);
		getContentPane().add(cboMedida);

		JLabel lblLocalDeArmazenagem = new JLabel("Local de Armazenagem:");
		lblLocalDeArmazenagem.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLocalDeArmazenagem.setBounds(22, 365, 189, 19);
		getContentPane().add(lblLocalDeArmazenagem);

		txtArmazem = new JTextField();
		txtArmazem.setFont(new Font("Arial", Font.PLAIN, 16));
		txtArmazem.setColumns(10);
		txtArmazem.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtArmazem.setBackground(SystemColor.menu);
		txtArmazem.setBounds(22, 390, 261, 29);
		getContentPane().add(txtArmazem);

		JLabel lblEstoque_1_1 = new JLabel("Valor*");
		lblEstoque_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEstoque_1_1.setBounds(22, 428, 156, 19);
		getContentPane().add(lblEstoque_1_1);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}
			}
		});

		txtValor.setFont(new Font("Arial", Font.PLAIN, 16));
		txtValor.setColumns(10);
		txtValor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtValor.setBackground(SystemColor.menu);
		txtValor.setBounds(22, 453, 261, 29);
		getContentPane().add(txtValor);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/9056782_add_icon.png")));
		btnAdicionar.setBounds(777, 441, 68, 68);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(
				new ImageIcon(Produtos.class.getResource("/img/2931178_change_edit_pencil_creative_design_icon.png")));
		btnEditar.setBounds(855, 441, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Produtos.class.getResource("/img/trast.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(929, 443, 64, 64);
		getContentPane().add(btnExcluir);

		JLabel lblIDFor = new JLabel("ID do Fornecedor:");
		lblIDFor.setFont(new Font("Arial", Font.PLAIN, 16));
		lblIDFor.setBounds(316, 51, 227, 19);
		getContentPane().add(lblIDFor);

		txtIDFor = new JTextField();
		txtIDFor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}
		});
		txtIDFor.setFont(new Font("Arial", Font.PLAIN, 16));
		txtIDFor.setColumns(10);
		txtIDFor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtIDFor.setBackground(SystemColor.control);
		txtIDFor.setBounds(316, 76, 227, 29);
		getContentPane().add(txtIDFor);

		JLabel lblNome = new JLabel("Produto:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(22, 116, 156, 19);
		getContentPane().add(lblNome);

		btnBorracha = new JButton("");
		btnBorracha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnBorracha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBorracha.setBorderPainted(false);
		btnBorracha.setContentAreaFilled(false);
		btnBorracha.setIcon(new ImageIcon(Produtos.class.getResource("/img/9165598_eraser_erase_icon.png")));
		btnBorracha.setBounds(1003, 445, 64, 64);
		getContentPane().add(btnBorracha);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				listarNome();
			}
		});
		txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNome.setColumns(10);
		txtNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.setBackground(SystemColor.menu);
		txtNome.setBounds(22, 139, 521, 29);
		getContentPane().add(txtNome);

		txtLote = new JTextField();
		txtLote.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLote.setColumns(10);
		txtLote.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLote.setBackground(SystemColor.menu);
		txtLote.setBounds(553, 76, 200, 29);
		getContentPane().add(txtLote);

		lblLote = new JLabel("Lote*");
		lblLote.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLote.setBounds(549, 55, 78, 14);
		getContentPane().add(lblLote);

		txtFabricante = new JTextField();
		txtFabricante.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFabricante.setColumns(10);
		txtFabricante.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFabricante.setBackground(SystemColor.menu);
		txtFabricante.setBounds(553, 165, 200, 29);
		getContentPane().add(txtFabricante);

		lblNewLabel = new JLabel("Fabricante*");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(553, 139, 117, 14);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Data Entrada*");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(563, 205, 130, 14);
		getContentPane().add(lblNewLabel_1);

		txtLucro = new JTextField();
		txtLucro.setText("0");
		txtLucro.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLucro.setColumns(10);
		txtLucro.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLucro.setBackground(SystemColor.menu);
		txtLucro.setBounds(293, 453, 250, 29);
		getContentPane().add(txtLucro);

		lblNewLabel_3 = new JLabel("Lucro:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(292, 432, 46, 14);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Data Validade*");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(553, 265, 140, 14);
		getContentPane().add(lblNewLabel_4);

		dateEnt = new JDateChooser();
		dateEnt.setBounds(546, 233, 179, 20);
		getContentPane().add(dateEnt);

		dateVali = new JDateChooser();
		dateVali.setBounds(542, 290, 183, 20);
		getContentPane().add(dateVali);

		JTextArea txtArea = new JTextArea();
		txtArea.setBackground(new Color(192, 192, 192));
		txtArea.setBounds(22, 241, 360, 52);
		getContentPane().add(txtArea);
		setBounds(100, 100, 1114, 572);

	}

	private void carregarFoto() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
		jfc.showOpenDialog(this);
		int resultado = jfc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {

				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_SMOOTH);
				lblFoto.setIcon(new ImageIcon(foto));
				lblFoto.updateUI();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void adicionar() {

		// System.out.println("Teste");
		// validação de campos obrigatórios

		if (txtBarras.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Código de Barras'. ");
			txtBarras.requestFocus();
		} else if (txtDesc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Descrição'.");
			txtDesc.requestFocus();

		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Estoque'.");
			txtEstoque.requestFocus();
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Estoque Mínimo'.");
			txtEstoqueMin.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Valor'.");
			txtValor.requestFocus();
		} else if (txtArmazem.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Local de Armazenagem'.");
			txtArmazem.requestFocus();
		} else if (txtIDFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'ID do Fornecedor'.");
			txtIDFor.requestFocus();

		} else if (txtLote.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Lote'.");
			txtLote.requestFocus();

		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Fabricante'.");
			txtFabricante.requestFocus();

		} else if (dateEnt.equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'DataEntrada'.");
			dateEnt.requestFocus();

		} else if (dateVali.equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'DataValidade'.");
			dateVali.requestFocus();

			btnAdicionar.setEnabled(true);
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);

		} else {
			// lógica principal
			// CRUD Create
			String create = "INSERT INTO produtos (barcode, descricao, foto, estoque, estoquemin, valor, medida, armazenagem, idfornecedor, nome, "
					+ "lote, fabricante, dataent, dataval, lucro) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
			// tratamento de exceções

			try {
				// abrir conexao
				con = dao.conectar();
				// preparar a execução da query (instrução sql, CRUD CREATE)
				pst = con.prepareStatement(create);

				pst.setString(1, txtBarras.getText());
				pst.setString(2, txtDesc.getText());
				pst.setBlob(3, fis, tamanho);
				pst.setString(4, txtEstoque.getText());
				pst.setString(5, txtEstoqueMin.getText());
				pst.setString(6, txtValor.getText());
				pst.setString(7, cboMedida.getSelectedItem().toString());
				pst.setString(8, txtArmazem.getText());
				pst.setString(9, txtIDFor.getText());
				pst.setString(10, txtNome.getText());
				pst.setString(11, txtLote.getText());
				pst.setString(12, txtFabricante.getText());
				pst.setString(15, txtLucro.getText());

				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataFormatada = formatador.format(dateEnt.getDate());
				pst.setString(13, dataFormatada);

				SimpleDateFormat formatadorval = new SimpleDateFormat("yyyyMMdd");
				String dataFormatadaval = formatadorval.format(dateVali.getDate());
				pst.setString(14, dataFormatadaval);

				// executa a query(instrução sql, CRUD)
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "Produto adicionado");
				// limpar campos
				limparCampos();

				btnAdicionar.setEnabled(false);

				// fechar conection
				con.close();

				// } catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				// JOptionPane.showMessageDialog(null, "Cliente não adicionado.\nEste CNPJ ou
				// EMAIL já está sendo utilizado.");
				// txtCNPJ.setText(null);
				// txtCNPJ.requestFocus();
				// txtEmail.setText(null);
				// txtEmail.requestFocus();

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();

		listProd.setModel(modelo);

		String readLista = "select * from produtos where idfornecedor like '" + txtIDFor.getText() + "%'"
				+ "order by idfornecedor";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery();

			while (rs.next()) {
				modelo.addElement(rs.getString(10));
				scrollPaneProd.setVisible(true);

				if (txtIDFor.getText().isEmpty()) {
					scrollPaneProd.setVisible(false);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarForLista() {

		// System.out.println("teste");

		// variavel que captuar o indice da linha da lista

		int linha = listProd.getSelectedIndex();

		if (linha >= 0) {

			// String readBuscaLista=

			// Query (instrução sql)

			// limite " , 1" -> selecionar o indice 0 e 1 usuario da lista

			String readBuscaLista = "select *from produtos where idfornecedor like '" + txtIDFor.getText() + "%'"

					+ "order by idfornecedor limit " + (linha) + " ,1";

			try {

				con = dao.conectar();

				pst = con.prepareStatement(readBuscaLista);

				rs = pst.executeQuery();

				if (rs.next()) {

					scrollPaneProd.setVisible(false);
					txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
					txtBarras.setText(rs.getString(2)); // 2º Campo da Tabela ID
					txtDesc.setText(rs.getString(3)); // 3º Campo da Tabela ID
					txtEstoque.setText(rs.getString(5)); // 4º Campo da Tabela ID
					txtEstoqueMin.setText(rs.getString(6)); // 4º Campo da Tabela ID
					txtValor.setText(rs.getString(7)); // 4º Campo da Tabela ID
					cboMedida.setSelectedItem(rs.getString(8)); // 4º Campo da Tabela ID
					txtArmazem.setText(rs.getString(9)); // 4º Campo da Tabela ID
					txtIDFor.setText(rs.getString(10)); // 4º Campo da Tabela ID
					txtNome.setText(rs.getString(11)); // 4º Campo da Tabela ID
					txtLote.setText(rs.getString(12));
					txtFabricante.setText(rs.getString(13));

					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);

					Blob blob = (Blob) rs.getBlob(4);
					byte[] img = blob.getBytes(1, (int) blob.length());
					BufferedImage imagem = null;
					// renderizar imagem
					try {
						// que sera exibida na tela
						imagem = ImageIO.read(new ByteArrayInputStream(img));

					} catch (Exception e) {
						System.out.println(e);
					}
					// setar a jlabel
					ImageIcon icone = new ImageIcon(imagem);
					Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(),
							lblFoto.getHeight(), Image.SCALE_SMOOTH));
					lblFoto.setIcon(foto);

				} else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado");

				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}

		} else {

			scrollPaneProd.setVisible(false);

		}
	}

	private void listarNome() {
		DefaultListModel<String> modelo = new DefaultListModel<>();

		listNome.setModel(modelo);

		String readLista = "select * from produtos where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery();

			while (rs.next()) {
				modelo.addElement(rs.getString(11));
				scrollPaneNome.setVisible(true);

				if (txtNome.getText().isEmpty()) {
					scrollPaneNome.setVisible(false);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarNome() {

		// System.out.println("teste");

		// variavel que captuar o indice da linha da lista

		int linha = listNome.getSelectedIndex();

		if (linha >= 0) {

			// String readBuscaLista=

			// Query (instrução sql)

			// limite " , 1" -> selecionar o indice 0 e 1 usuario da lista

			String readBuscaLista = "select * from produtos where nome like '" + txtNome.getText() + "%'"

					+ "order by nome limit " + (linha) + " ,1";

			try {

				con = dao.conectar();

				pst = con.prepareStatement(readBuscaLista);

				rs = pst.executeQuery();

				if (rs.next()) {

					scrollPaneNome.setVisible(false);

					txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
					txtBarras.setText(rs.getString(2)); // 2º Campo da Tabela ID
					txtDesc.setText(rs.getNString(3)); // 3º Campo da Tabela ID
					txtEstoque.setText(rs.getString(5)); // 4º Campo da Tabela ID
					txtEstoqueMin.setText(rs.getString(6)); // 4º Campo da Tabela ID
					txtValor.setText(rs.getString(7)); // 4º Campo da Tabela ID
					cboMedida.setSelectedItem(rs.getString(8)); // 4º Campo da Tabela ID
					txtArmazem.setText(rs.getString(9)); // 4º Campo da Tabela ID
					txtIDFor.setText(rs.getString(10)); // 4º Campo da Tabela ID
					txtNome.setText(rs.getString(11)); // 4º Campo da Tabela ID
					txtLote.setText(rs.getString(12));
					txtFabricante.setText(rs.getString(13));

					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);

					Blob blob = (Blob) rs.getBlob(4);
					byte[] img = blob.getBytes(1, (int) blob.length());
					BufferedImage imagem = null;
					try {
						imagem = ImageIO.read(new ByteArrayInputStream(img));

					} catch (Exception e) {
						System.out.println(e);
					}
					ImageIcon icone = new ImageIcon(imagem);
					Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(),
							lblFoto.getHeight(), Image.SCALE_SMOOTH));
					lblFoto.setIcon(foto);

				} else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado");

				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}

		} else {

			scrollPaneNome.setVisible(false);

		}
	}

	private void editar() {

		if (txtBarras.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Código de Barras'. ");
			txtBarras.requestFocus();
		} else if (txtDesc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Descrição'.");
			txtDesc.requestFocus();

		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Estoque'.");
			txtEstoque.requestFocus();
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Estoque Mínimo'.");
			txtEstoqueMin.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Valor'.");
			txtValor.requestFocus();
		} else if (txtArmazem.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Local de Armazenagem'.");
			txtArmazem.requestFocus();

			// btnAdicionar.setEnabled(true);
			// btnEditar.setEnabled(true);
			// btnExcluir.setEnabled(true);

		} else {
			// logica principal
			// CRUD - Update
			String update = "update produtos set barcode=?, descricao=?, foto=?, estoque=?, estoquemin=?, valor=?, medida=?, armazenagem=?, nome=?, lote=?, "
					+ "fabricante=?,lucro=? where codigoproduto=?";
			// trat de exceção
			try {
				// abrir conexão
				con = dao.conectar();

				pst = con.prepareStatement(update);

				pst.setString(1, txtBarras.getText());
				pst.setString(2, txtDesc.getText());
				pst.setBlob(3, fis, tamanho);
				pst.setString(4, txtEstoque.getText());
				pst.setString(5, txtEstoqueMin.getText());
				pst.setString(6, txtValor.getText());
				pst.setString(7, cboMedida.getSelectedItem().toString());
				pst.setString(8, txtArmazem.getText());
				pst.setString(9, txtNome.getText());
				pst.setString(10, txtLote.getText());
				pst.setString(11, txtFabricante.getText());
				pst.setString(12, txtLucro.getText());

				pst.setString(13, txtID.getText());

				pst.executeUpdate();
				// confirmar para o user
				JOptionPane.showMessageDialog(null, "Dados do produto editados com sucesso!");
				// limpar campos
				limparCampos();
				// fechar conexao
				con.close();

			} catch (Exception e) {

				System.out.println(e);

			}

		}

	}

	private void excluir() {
		// System.out.println("teste");
		// validação de exclusao - a variável confirma captura a opção escolhida.
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste produtos?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// CRUD - Delete vai excluir o contato

			String delete = "delete from produtos where codigoproduto= ?";
			// tratamento de exceção
			try {
				// abrir conexão
				con = dao.conectar();
				// preparar a query
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				// executar query
				pst.executeUpdate();
				// confirmar para o user
				JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
				// limpar campos
				limparCampos();
				// fechar conexao
				con.close();

				// } catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				// JOptionPane.showMessageDialog(null, "Produto não excluido.\nEste produto e
				// pendente.");
				// txtID.setText(null);
				// txtID.requestFocus();

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	
	
	private void buscarProduto() {
		// captura do numero a OS (sem usar caixa de txt)
		String produto = JOptionPane.showInputDialog("Digite o código do produto: ");

		// System.out.println("Teste do botão buscar");

		// Criar uma variável com a query (instrução do banco)

		// Tratamento de exceções
		String read = "select * from produtos where codigoproduto= ?";
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a execucão da query( instrução sql - CRUD Read)
			// o parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);

			// SUBSTITUI A ? PELO NÚMERO DA OS
			pst.setString(1, produto);
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrutura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				// preencher as caixas de texto com as informações

				txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
				txtBarras.setText(rs.getString(2)); // 2º Campo da Tabela ID
				txtDesc.setText(rs.getString(3)); // 3º Campo da Tabela ID

				txtEstoque.setText(rs.getString(5)); // 4º Campo da Tabela ID
				txtEstoqueMin.setText(rs.getString(6)); // 4º Campo da Tabela ID
				txtValor.setText(rs.getString(7)); // 4º Campo da Tabela ID
				cboMedida.setSelectedItem(rs.getString(8)); // 4º Campo da Tabela ID
				txtArmazem.setText(rs.getString(9)); // 4º Campo da Tabela ID
				txtIDFor.setText(rs.getString(10));
				txtNome.setText(rs.getString(11)); // 4º Campo da Tabela ID
				txtLote.setText(rs.getString(12));
				txtFabricante.setText(rs.getString(13));
				txtLucro.setText(rs.getString(14));
				// setardata
				// 1- receber data do mysql
				// 2 formatar a data para JCalendar
				String setarDataEnt = rs.getString(15);
				System.out.println(setarDataEnt);// setardata
				Date dataEntrada = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEnt);
				dateEnt.setDate(dataEntrada);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				String setarDataVali = rs.getString(16);
				System.out.println(setarDataVali);// setardata
				Date dataVali = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataVali);
				dateVali.setDate(dataVali);

				
				
				///////////////////////////////////////////////////////////////////////////
				
				// ----------------------------------------------------------------------------

				// ------------------------------------------------------------------------------
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);

				Blob blob = (Blob) rs.getBlob(4);
				byte[] img = blob.getBytes(1, (int) blob.length());
				BufferedImage imagem = null;
				try {
					imagem = ImageIO.read(new ByteArrayInputStream(img));

				} catch (Exception e) {
					System.out.println(e);
				}
				ImageIcon icone = new ImageIcon(imagem);
				Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
						Image.SCALE_SMOOTH));
				lblFoto.setIcon(foto);

				// btnEditar.setEnabled(true);
				// btnExcluir.setEnabled(true);
			} else {
				// System.out.println("Contaos não cadastrados");
				JOptionPane.showMessageDialog(null, "Produto inexistente");
				// btnAdicionar.setEnabled(true);
				// btnPesquisar.setEnabled(true);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}

	private void limparCampos() {

		txtBarras.setText(null);
		txtNome.setText(null);
		txtEstoque.setText(null);
		txtEstoqueMin.setText(null);
		txtDesc.setText(null);
		txtArmazem.setText(null);
		txtIDFor.setText(null);
		txtID.setText(null);
		txtValor.setText(null);
		lblFoto.setIcon(null);
		txtLote.setText(null);
		txtFabricante.setText(null);

		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);

	}

	private void buscarBarcode() {

		String readCodigodebarras = "select * from produtos where barcode =?";

		try {

			con = dao.conectar();
			pst = con.prepareStatement(readCodigodebarras);
			pst.setString(1, txtBarras.getText());
			rs = pst.executeQuery();

			if (rs.next()) {

				txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
				txtBarras.setText(rs.getString(2)); // 2º Campo da Tabela I
				txtDesc.setText(rs.getNString(3)); // 3º Campo da Tabela ID
				txtEstoque.setText(rs.getString(5)); // 4º Campo da Tabela ID
				txtEstoqueMin.setText(rs.getString(6)); // 4º Campo da Tabela ID
				txtValor.setText(rs.getString(7)); // 4º Campo da Tabela ID
				cboMedida.setSelectedItem(rs.getString(8)); // 4º Campo da Tabela ID
				txtArmazem.setText(rs.getString(9)); // 4º Campo da Tabela ID
				txtIDFor.setText(rs.getString(10)); // 4º Campo da Tabela ID
				txtNome.setText(rs.getString(11)); // 4º Campo da Tabela ID
				txtLote.setText(rs.getString(12));
				txtFabricante.setText(rs.getString(13));
				txtLucro.setText(rs.getString(14));

				String setarDataEnt = rs.getString(15);
				Date dataFormatadaEnt = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEnt);
				dateEnt.setDate(dataFormatadaEnt);

				String setarDataVal = rs.getString(16);
				Date dataFormatadaVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataVal);
				dateVali.setDate(dataFormatadaVal);
				
				txtBarras.setText(rs.getString(17));

				Blob blob = (Blob) rs.getBlob(4);

				byte[] img = blob.getBytes(1, (int) blob.length());

				BufferedImage imagem = null;

				try {

					imagem = ImageIO.read(new ByteArrayInputStream(img));

				} catch (Exception e) {

					System.out.println(e);

				}
				ImageIcon icone = new ImageIcon(imagem);

				Icon foto = new ImageIcon(icone.getImage().getScaledInstance(lblFoto.getWidth(),

						lblFoto.getHeight(), Image.SCALE_SMOOTH));

				lblFoto.setIcon(foto);

			} else {

				JOptionPane.showMessageDialog(null, "Produto não encontrado");

			}

			con.close();

		} catch (Exception e) {

			System.out.println(e);

		}

	}

}
