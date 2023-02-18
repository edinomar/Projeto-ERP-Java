package pacoteMain;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CadastroArtigo extends JFrame {

	private static final long serialVersionUID = 1L;

	JTextField inputCodArtigo;
	JTextField inputNome;
	JTextField inputQtdArtigo;
	JTextField inputPreco;

	public CadastroArtigo() {

		JLabel CodArtigo = new JLabel();
		CodArtigo.setText("Código:");
		CodArtigo.setBounds(25, 0, 70, 40);

		inputCodArtigo = new JTextField();
		inputCodArtigo.setBounds(70, 10, 80, 18);

		JLabel nome = new JLabel();
		nome.setText("Descrição:");
		nome.setBounds(5, 20, 70, 40);

		inputNome = new JTextField();
		inputNome.setBounds(70, 30, 80, 18);

		JLabel qtdArtigo = new JLabel();
		qtdArtigo.setText("Quantida:");
		qtdArtigo.setBounds(10, 40, 70, 40);

		inputQtdArtigo = new JTextField();
		inputQtdArtigo.setBounds(70, 50, 80, 18);

		JLabel preco = new JLabel();
		preco.setText("Valor:");
		preco.setBounds(32, 70, 60, 18);

		inputPreco = new JTextField();
		inputPreco.setBounds(70, 70, 80, 18);

		JButton cadastrar = new JButton();
		cadastrar.setBounds(40, 100, 100, 20);
		cadastrar.setText("Cadastrar");
		cadastrar.setFont(new Font("", Font.BOLD, 12));
		cadastrar.addActionListener(e -> {
			try {
				cadastrar(e);
			} catch (SQLException err) {
				err.printStackTrace();
			}
		});

		JLabel ajuste = new JLabel();

		add(CodArtigo);
		add(inputCodArtigo);
		add(nome);
		add(inputNome);
		add(qtdArtigo);
		add(inputQtdArtigo);
		add(preco);
		add(inputPreco);
		add(cadastrar);
		add(ajuste);

		setTitle("Cadastro de Artigo");
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);

	}

	private void cadastrar(ActionEvent e) throws SQLException {

		String inputCodArtig = inputCodArtigo.getText();
		String inputNom = inputNome.getText();
		String inputQtdArtig = inputQtdArtigo.getText();
		String inputPrec = inputPreco.getText();

		if (!inputCodArtig.equals("")) {

			int inputCodArtigInt = Integer.parseInt(inputCodArtig);
			int inputQtdArtigInt = Integer.parseInt(inputQtdArtig);
			float inputPrecFloat = Float.parseFloat(inputPrec);

			try {
				Connection conexao = FabricaConexao.getConexao();

				String sql = "INSERT INTO artigo (cd_artigo, nm_artigo, qtd_estoque, preco_artigo) VALUES (?,?,?,?)";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, inputCodArtigInt);
				stmt.setString(2, inputNom);
				stmt.setInt(3, inputQtdArtigInt);
				stmt.setFloat(4, inputPrecFloat);

				stmt.execute();
				conexao.close();

				JOptionPane.showMessageDialog(null, "Artigo cadastrado com sucesso!!");
				
			} catch (SQLException SQLIntegrityConstraintViolationException) {

				JOptionPane.showMessageDialog(null, "Item já castrado");

			}
		} else {
			
			JOptionPane.showMessageDialog(null, "Informe todos os dados");
			System.out.println("Err informe todos os dados!");
		}
	}
}
