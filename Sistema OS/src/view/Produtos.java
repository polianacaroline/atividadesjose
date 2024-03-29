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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import model.DAO;
import utils.Validador;

import javax.swing.JCheckBox;

public class Produtos extends JDialog {
	private JTextField txtID;
	private PreparedStatement pst;
	private ResultSet rs;
	private Connection con;
	DAO dao = new DAO();

	private FileInputStream fis;

	private int tamanho;
	private JLabel lblFoto;
	private JTextField txtEstoqueMin;
	private JTextField txtEstoque;
	private JTextField txtArmazem;
	private JTextField txtValor;
	private JComboBox cboMedida;
	private JTextField txtIDFor;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnBorracha;
	private JTextField txtNome;
	private JScrollPane scrollPaneNome;
	private JList listNome;
	private JTextField txtLote;
	private JTextField txtFabricante;
	private JTextField txtLucro;
	private JDateChooser dateVal;
	private JScrollPane scrollPane;
	private JTextArea txtDesc;
	private JDateChooser dateEnt;
	private JTextField txtCodigoBarras;
	private JCheckBox checkAlterar;
	private JTextField txtRazao;
	private JScrollPane scrollPanelist;
	private JList listrazao;

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
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPaneNome.setVisible(false);

			}
		});
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

			}
		});

		scrollPaneNome = new JScrollPane();
		scrollPaneNome.setVisible(false);

		scrollPanelist = new JScrollPane();
		scrollPanelist.setVisible(false);
		scrollPanelist.setBounds(355, 99, 275, 32);
		getContentPane().add(scrollPanelist);

		listrazao = new JList();
		listrazao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarForLista();
			}
		});
		scrollPanelist.setViewportView(listrazao);
		scrollPaneNome.setBounds(22, 101, 285, 23);
		getContentPane().add(scrollPaneNome);

		listNome = new JList();
		listNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarNome();
			}
		});
		listNome.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneNome.setViewportView(listNome);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(SystemColor.control);
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPaneNome.setVisible(false);
			}
		});
		panel_1_1.setBounds(0, 0, 1098, 40);
		getContentPane().add(panel_1_1);
		panel_1_1.setLayout(null);

		JLabel lblTitulo = new JLabel("ÁREA DE PRODUTOS:");
		lblTitulo.setForeground(new Color(0, 128, 255));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBounds(451, 0, 251, 40);
		panel_1_1.add(lblTitulo);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(SystemColor.control);
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(0, 468, 1098, 40);
		getContentPane().add(panel_1_1_1);

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(803, 51, 285, 345);
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

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescricao.setBounds(22, 116, 156, 19);
		getContentPane().add(lblDescricao);

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
		txtEstoqueMin.setBounds(532, 152, 98, 30);

		getContentPane().add(txtEstoqueMin);

		JLabel lblEstoque = new JLabel("Estoque*");
		lblEstoque.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEstoque.setBounds(413, 132, 68, 19);
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
		txtEstoque.setBounds(413, 151, 98, 31);
		getContentPane().add(txtEstoque);

		JLabel lblDescricao_1_1 = new JLabel("Estoque Mínimo*");
		lblDescricao_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDescricao_1_1.setBounds(532, 132, 136, 19);
		getContentPane().add(lblDescricao_1_1);

		JLabel lblUnidades = new JLabel("Unidade:");
		lblUnidades.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUnidades.setBounds(672, 132, 71, 19);
		getContentPane().add(lblUnidades);

		cboMedida = new JComboBox();
		cboMedida.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "CX", "PC", "Kg", "M", "CM" }));
		cboMedida.setBounds(672, 151, 71, 30);
		getContentPane().add(cboMedida);

		JLabel lblLocalDeArmazenagem = new JLabel("Local de Armazenagem:");
		lblLocalDeArmazenagem.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLocalDeArmazenagem.setBounds(22, 214, 189, 19);
		getContentPane().add(lblLocalDeArmazenagem);

		txtArmazem = new JTextField();
		txtArmazem.setFont(new Font("Arial", Font.PLAIN, 16));
		txtArmazem.setColumns(10);
		txtArmazem.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtArmazem.setBackground(SystemColor.menu);
		txtArmazem.setBounds(22, 239, 261, 29);
		txtArmazem.setDocument(new Validador(50));
		getContentPane().add(txtArmazem);

		JLabel lblEstoque_1_1 = new JLabel("Valor*");
		lblEstoque_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEstoque_1_1.setBounds(22, 279, 156, 19);
		getContentPane().add(lblEstoque_1_1);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}
			}
		});

		txtValor.setFont(new Font("Arial", Font.PLAIN, 16));
		txtValor.setColumns(10);
		txtValor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtValor.setBackground(SystemColor.menu);
		txtValor.setBounds(22, 304, 261, 29);
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
		btnAdicionar

				.setIcon(new ImageIcon(Produtos.class.getResource("/img/4781840_+_add_circle_create_expand_icon.png")));
		btnAdicionar.setBounds(498, 368, 68, 68);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkAlterar.isSelected()) {
					editar();
				} else {

					editarExcetoImagem();
				}
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(
				new ImageIcon(Produtos.class.getResource("/img/4831013_adit_create_pen_pencil_write_icon.png")));
		btnEditar.setBounds(576, 368, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Produtos.class.getResource("/img/1564505_close_delete_exit_remove_icon.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(655, 368, 64, 64);
		getContentPane().add(btnExcluir);

		JLabel lblIDFor = new JLabel("ID Fornecedor:");
		lblIDFor.setFont(new Font("Arial", Font.PLAIN, 16));
		lblIDFor.setBounds(647, 51, 142, 19);
		getContentPane().add(lblIDFor);

		txtIDFor = new JTextField();
		

		txtIDFor.setFont(new Font("Arial", Font.PLAIN, 16));
		txtIDFor.setColumns(10);
		txtIDFor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtIDFor.setBackground(SystemColor.control);
		txtIDFor.setBounds(647, 72, 124, 29);
		getContentPane().add(txtIDFor);

		JLabel lblNome = new JLabel("Modelo Cell:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNome.setBounds(22, 53, 156, 19);
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
		btnBorracha.setIcon(new ImageIcon(Produtos.class.getResource("/img/9110490_rubber_icon.png")));
		btnBorracha.setBounds(729, 370, 64, 64);
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
		txtNome.setBounds(22, 76, 285, 29);
		txtNome.setDocument(new Validador(40));
		getContentPane().add(txtNome);

		txtLote = new JTextField();
		txtLote.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLote.setColumns(10);
		txtLote.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLote.setBackground(SystemColor.menu);
		txtLote.setBounds(293, 239, 197, 29);
		getContentPane().add(txtLote);

		JLabel lblLote = new JLabel("Lote*");
		lblLote.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLote.setBounds(293, 214, 189, 19);
		getContentPane().add(lblLote);

		txtFabricante = new JTextField();
		txtFabricante.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFabricante.setColumns(10);
		txtFabricante.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFabricante.setBackground(SystemColor.menu);
		txtFabricante.setBounds(502, 239, 291, 29);
		txtFabricante.setDocument(new Validador(20));
		getContentPane().add(txtFabricante);

		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFabricante.setBounds(502, 214, 189, 19);
		getContentPane().add(lblFabricante);

		JLabel lblDataEntrada = new JLabel("Data entrada*");
		lblDataEntrada.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDataEntrada.setBounds(505, 279, 156, 19);
		getContentPane().add(lblDataEntrada);

		JLabel lblDataValidade = new JLabel("Data validade*");
		lblDataValidade.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDataValidade.setBounds(654, 279, 108, 19);
		getContentPane().add(lblDataValidade);

		JLabel lblEstoque_1_1_1 = new JLabel("Lucro:");
		lblEstoque_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEstoque_1_1_1.setBounds(293, 279, 156, 19);
		getContentPane().add(lblEstoque_1_1_1);

		txtLucro = new JTextField();
		txtLucro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}
			}

		});
		txtLucro.setText("0");
		txtLucro.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLucro.setColumns(10);
		txtLucro.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtLucro.setBackground(SystemColor.menu);
		txtLucro.setBounds(293, 304, 149, 29);
		getContentPane().add(txtLucro);

		dateEnt = new JDateChooser();
		dateEnt.setBounds(502, 304, 136, 29);
		getContentPane().add(dateEnt);

		dateVal = new JDateChooser();
		dateVal.setBounds(654, 304, 136, 29);
		getContentPane().add(dateVal);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 140, 368, 63);
		getContentPane().add(scrollPane);

		txtDesc = new JTextArea();
		txtDesc.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDesc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDesc.setBackground(SystemColor.control);
		txtDesc.setDocument(new Validador(90));
		scrollPane.setViewportView(txtDesc);

		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(447, 307, 46, 23);
		getContentPane().add(lblNewLabel);

		JLabel lblCodigoBarras = new JLabel("");
		lblCodigoBarras.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblCodigoBarras.setBounds(22, 341, 66, 55);
		getContentPane().add(lblCodigoBarras);

		txtCodigoBarras = new JTextField();
		txtCodigoBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarBarcode();
				}
			}
		});
		txtCodigoBarras.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCodigoBarras.setColumns(10);
		txtCodigoBarras.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCodigoBarras.setBackground(SystemColor.menu);
		txtCodigoBarras.setBounds(88, 354, 213, 29);
		getContentPane().add(txtCodigoBarras);

		checkAlterar = new JCheckBox("Alterar a imagem");
		checkAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkAlterar.isSelected()) {

					carregarFoto();
				} else {

				}
			}
		});
		checkAlterar.setBounds(880, 403, 124, 23);
		getContentPane().add(checkAlterar);

		JButton btnLupa = new JButton("");
		btnLupa.setContentAreaFilled(false);
		btnLupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lupa();

			}
		});
		btnLupa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLupa.setBorderPainted(false);
		btnLupa.setIcon(
				new ImageIcon(Produtos.class.getResource("/img/510919_find_magnifying glass_search_zoom_icon.png")));
		btnLupa.setBounds(313, 72, 32, 32);
		getContentPane().add(btnLupa);

		txtRazao = new JTextField();
		txtRazao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarFornecedor1();
			}
		});
		txtRazao.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRazao.setColumns(10);
		txtRazao.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtRazao.setBackground(SystemColor.menu);
		txtRazao.setBounds(355, 72, 275, 29);
		getContentPane().add(txtRazao);

		JLabel lblIDFor_1 = new JLabel("Nome do fornecedor:");
		lblIDFor_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblIDFor_1.setBounds(357, 51, 273, 19);
		getContentPane().add(lblIDFor_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(355, 124, 197, -22);
		getContentPane().add(scrollPane_1);
		setBounds(100, 100, 1114, 539);

	}

	private void carregarFoto() {
		JFileChooser jfc = new JFileChooser();

		jfc.setDialogTitle("Selecionar arquivo de IMAGEM");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
		int resultado = jfc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {

				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_SMOOTH);
				lblFoto.setIcon(new ImageIcon(foto));
				lblFoto.updateUI();
				btnAdicionar.setEnabled(true);

			} catch (Exception e) {
				System.out.println(e);

			}
		} else if (resultado == JFileChooser.CANCEL_OPTION)
			checkAlterar.setSelected(false);

		{

		}

	}

	private void adicionar() {

		// System.out.println("Teste");
		// validação de campos obrigatórios

		if (txtDesc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Descrição'.");
			txtDesc.requestFocus();
		} else if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Nome do produto*'.");
			txtNome.requestFocus();

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
		} else if (cboMedida.equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Unidade'.");
			cboMedida.requestFocus();

		} else if (dateVal.getDate() == null) {
			dateVal.setDate(null);
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Data de Validade'.");

		} else if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Fornecedor'.");
			txtRazao.requestFocus();

			dateEnt.requestFocus();
			btnAdicionar.setEnabled(true);
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);

		} else {
			// lógica principal
			// CRUD Create
			String create = "INSERT INTO produtos (barcode, descricao, foto, estoque, estoquemin, valor, medida, armazenagem, idfornecedor, nome, lote, fabricante, lucro, dataent, dataval,razaosocial) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
			// tratamento de exceções

			try {
				// abrir conexao
				con = dao.conectar();
				// preparar a execução da query (instrução sql, CRUD CREATE)
				pst = con.prepareStatement(create);

				pst.setString(1, txtCodigoBarras.getText());
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
				pst.setString(13, txtLucro.getText());

				java.util.Date dataAtual = new java.util.Date();
				SimpleDateFormat formatadorEnt = new SimpleDateFormat("yyyyMMdd");
				String dataFormatadaEnt = formatadorEnt.format(dataAtual);
				pst.setString(14, dataFormatadaEnt);

				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataFormatada = formatador.format(dateVal.getDate());
				pst.setString(15, dataFormatada);

				pst.setString(16, txtRazao.getText());

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

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null,
						"Produto não adicionado.\nEste código de barras já está sendo utilizado.");

				txtCodigoBarras.setText(null);
				txtCodigoBarras.requestFocus();

			} catch (Exception e2) {
				System.out.println(e2);
			}
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
					txtCodigoBarras.setText(rs.getString(2)); // 2º Campo da Tabela ID
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
					dateVal.setDate(dataFormatadaVal);

					txtRazao.setText(rs.getString(17));

					// txtDataEnt.setText(rs.getString(14));
					// txtDataVal.setText(rs.getString(15));
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

	private void lupa() {

		String read = "select * from produtos where nome = ?";
		try {

			con = dao.conectar();

			pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText());

			rs = pst.executeQuery();

			if (rs.next()) {

				scrollPaneNome.setVisible(false);
				txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
				txtCodigoBarras.setText(rs.getString(2)); // 2º Campo da Tabela ID
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
				dateVal.setDate(dataFormatadaVal);

				txtCodigoBarras.setText(rs.getString(17));

				// txtDataEnt.setText(rs.getString(14));
				// txtDataVal.setText(rs.getString(15));
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

				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);

			} else {
				btnAdicionar.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Produto não encontrado");

			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void editar() {

		if (txtDesc.getText().isEmpty()) {
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

		} else {
			// logica principal
			// CRUD - Update
			String update = "update produtos set barcode=?, descricao=?, foto=?, estoque=?, estoquemin=?, valor=?, medida=?, armazenagem=?, nome=?, lote=?, fabricante=?, lucro=?, dataent=? where codigo=?";
			// trat de exceção
			try {
				// abrir conexão
				con = dao.conectar();

				pst = con.prepareStatement(update);

				pst.setString(1, txtCodigoBarras.getText());
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

				SimpleDateFormat formatadorEnt = new SimpleDateFormat("yyyyMMdd");
				String dataFormatadaEnt = formatadorEnt.format(dateEnt.getDate());
				pst.setString(13, dataFormatadaEnt);

				pst.setString(14, txtID.getText());

				pst.executeUpdate();
				// confirmar para o user
				JOptionPane.showMessageDialog(null, "Dados do produto editados com sucesso!");
				// limpar campos
				limparCampos();
				// fechar conexao
				con.close();

			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "Coloque uma imagem para poder editar o
				// produto!!");

				System.out.println(e);

			}

		}

	}

	private void editarExcetoImagem() {

		if (txtDesc.getText().isEmpty()) {
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

		} else if (dateEnt.equals(" ")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo 'Data de Entrada'.");
			dateEnt.requestFocus();

		} else {
			// logica principal
			// CRUD - Update
			String update = "update produtos set barcode=?, descricao=?, estoque=?, estoquemin=?, valor=?, medida=?, armazenagem=?, nome=?, lote=?, fabricante=?, lucro=?, dataent=? where codigo=?";
			// trat de exceção
			try {
				// abrir conexão
				con = dao.conectar();

				pst = con.prepareStatement(update);

				pst.setString(1, txtCodigoBarras.getText());
				pst.setString(2, txtDesc.getText());
				pst.setString(3, txtEstoque.getText());
				pst.setString(4, txtEstoqueMin.getText());
				pst.setString(5, txtValor.getText());
				pst.setString(6, cboMedida.getSelectedItem().toString());
				pst.setString(7, txtArmazem.getText());

				pst.setString(8, txtNome.getText());
				pst.setString(9, txtLote.getText());
				pst.setString(10, txtFabricante.getText());
				pst.setString(11, txtLucro.getText());

				SimpleDateFormat formatadorEnt = new SimpleDateFormat("yyyyMMdd");
				String dataFormatadaEnt = formatadorEnt.format(dateEnt.getDate());
				pst.setString(12, dataFormatadaEnt);

				pst.setString(13, txtID.getText());

				pst.executeUpdate();
				// confirmar para o user
				JOptionPane.showMessageDialog(null, "Dados do produto editados com sucesso!");
				// limpar campos
				limparCampos();
				// fechar conexao
				con.close();

			} catch (Exception e) {
				// JOptionPane.showMessageDialog(null, "Coloque uma imagem para poder editar o
				// produto!!");

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

			String delete = "delete from produtos where codigo= ?";
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
		String read = "select * from produtos inner join fornecedores on produtos.idfornecedor = fornecedores.idfornecedor where codigo=?";
		try {
			// abrir a conexão
			con = dao.conectar();

			pst = con.prepareStatement(read);

			// SUBSTITUI A ? PELO NÚMERO DA OS
			pst.setString(1, produto);

			rs = pst.executeQuery();

			if (rs.next()) {

				txtID.setText(rs.getString(1));
				txtCodigoBarras.setText(rs.getString(2));
				txtDesc.setText(rs.getNString(3)); // ESSE GETNSTRING É PRA QUANDO É JTEXTAREA

				txtEstoque.setText(rs.getString(5));
				txtEstoqueMin.setText(rs.getString(6));
				txtValor.setText(rs.getString(7));
				cboMedida.setSelectedItem(rs.getString(8));
				txtArmazem.setText(rs.getString(9));
				txtIDFor.setText(rs.getString(10));
				txtNome.setText(rs.getString(11));
				txtLote.setText(rs.getString(12));
				txtFabricante.setText(rs.getString(13));
				txtLucro.setText(rs.getString(14));

				// dateEnt.setDate(rs.getDate(15));

				String setarDataEnt = rs.getString(15);
				Date dataFormatadaEnt = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataEnt);
				dateEnt.setDate(dataFormatadaEnt);

				String setarDataVal = rs.getString(16);
				Date dataFormatadaVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataVal);
				dateVal.setDate(dataFormatadaVal);

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
				btnAdicionar.setEnabled(true);
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

		btnAdicionar.setEnabled(false);
		txtCodigoBarras.setText(null);
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
		dateEnt.setDate(null);
		dateVal.setDate(null);
		txtLucro.setText(null);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		cboMedida.setSelectedIndex(0);
		txtRazao.setText(null);
		lblFoto.setIcon(new ImageIcon(Produtos.class.getResource("/img/bluecamera.png")));
		txtCodigoBarras.setText(null);
		checkAlterar.setSelected(false);
		scrollPaneNome.setVisible(false);

	}

	private void buscarBarcode() {
		String readCodigodebarras = "select * from produtos inner join fornecedores on fornecedores.idfornecedor = produtos.idfornecedor where barcode =?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readCodigodebarras);
			pst.setString(1, txtCodigoBarras.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1)); // 1º Campo da Tabela ID
				txtCodigoBarras.setText(rs.getString(2)); // 2º Campo da Tabela ID
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
				dateVal.setDate(dataFormatadaVal);

				txtRazao.setText(rs.getString(18));

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
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "Produto não encontrado");

			}

			con.close();

		} catch (Exception e) {

			System.out.println(e);

		}

	}

	private void listarFornecedor1() {
		DefaultListModel<String> modelo = new DefaultListModel<>();

		listrazao.setModel(modelo);

		String readLista = "select * from fornecedores  where razaosocial like '" + txtRazao.getText() + "%'"
				+ "order by razaosocial";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readLista);
			rs = pst.executeQuery();

			while (rs.next()) {
				modelo.addElement(rs.getString(2));
				scrollPanelist.setVisible(true);

				if (txtRazao.getText().isEmpty()) {
					scrollPanelist.setVisible(false);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarForLista() {

		int linha = listrazao.getSelectedIndex();
		if (linha >= 0) {
			// query
			// limit (0,1) > seleciona o indice de 0 1 usuario
			String readListaClientes = "select * from fornecedores where razaosocial like'" + txtRazao.getText() + "%'"
					+ "order by razaosocial limit " + (linha) + " , 1";
			try {
				// abrirconexao
				con = dao.conectar();
				pst = con.prepareStatement(readListaClientes);
				// executar e obter o resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					// se existir um usuario esconder a lista
					scrollPanelist.setVisible(false);

					// setar os campos
					txtIDFor.setText(rs.getString(1));
					txtRazao.setText(rs.getString(2));

				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");

				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			scrollPanelist.setVisible(false);
		}
		// captura o indice da linha
	}



}