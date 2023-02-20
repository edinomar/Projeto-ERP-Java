package pacoteMain;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InserirPedido extends JFrame {

	private static final long serialVersionUID = 1L;

	JTextField artigo;
	JTextField descricaoArtigo;
	JTextField qtdItem;
	JTextField valorUni;
	JTextField total;
	JTextField valorTotal;

	public InserirPedido() {
	
		JPanel teste = new JPanel();
		
//		teste.setTitle("Novo Pedido");
		//teste.setSize(100, 100);
		teste.setSize(500,250); 
		teste.setBounds(0, 90, 60, 20);
		teste.setVisible(true);
		teste.setLocation(10,10);
		teste.setLayout(new BorderLayout());
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	JLabel descricaoArtigo = new JLabel();
	descricaoArtigo.setText("Descrição do produto");
	descricaoArtigo.setBounds(105, 70, 150, 20);
	JTextField descricaoArtigoText = new JTextField();
	descricaoArtigoText.setBounds(105, 90, 250, 20);

	JLabel numeroArtigo = new JLabel();
	numeroArtigo.setText("Artigo");
	numeroArtigo.setBounds(25, 70, 150, 20);

	JLabel valor = new JLabel();
	valor.setText("Valor UN.");
	valor.setBounds(385, 70, 150, 20);
	JTextField valorUni = new JTextField();
	valorUni.setBounds(380, 90, 60, 20);

	JTextField artigo = new JTextField();
	artigo.setBounds(25, 90, 80, 20);
	artigo.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			int codigo1 = e.getKeyCode();
			int tecla = KeyEvent.VK_ENTER;

			if (codigo1 == tecla) {

				try {
					Connection conexao = FabricaConexao.getConexao();

					String cdArtigo = artigo.getText();

					String sql = "SELECT * FROM artigo WHERE cd_artigo = ? ";// WHERE cpf_cliente = ?

					PreparedStatement stmt = conexao.prepareStatement(sql);
					stmt.setString(1, cdArtigo);

					ResultSet resultado = stmt.executeQuery();

					while (resultado.next()) {
						String codigo = resultado.getString("cd_artigo");
						System.out.println(codigo);
						String nmArtigo = resultado.getString("nm_artigo");
						System.out.println(nmArtigo);
						String preco = resultado.getString("preco_artigo");
						System.out.println(preco);

						descricaoArtigoText.setText(nmArtigo);
						valorUni.setText(preco);
					}
					stmt.close();
					conexao.close();

				} catch (SQLException e1) {

					System.out.println("erro");
					e1.printStackTrace();
				}

			}
		}

	});

	JLabel total = new JLabel();
	total.setText("Total");
	total.setBounds(445, 70, 150, 20);
	JTextField valorTotal = new JTextField();
	valorTotal.setBounds(440, 90, 60, 20);

	JLabel qtd = new JLabel();
	qtd.setText("Qtd.");
	qtd.setBounds(355, 70, 150, 20);
	JTextField qtdItem = new JTextField();
	qtdItem.setBounds(355, 90, 25, 20);
	qtdItem.addKeyListener(new KeyAdapter() {

		public void keyPressed(KeyEvent f) {
			int codigo1 = f.getKeyCode();
			int tecla = KeyEvent.VK_ENTER;

			if (codigo1 == tecla) {
				
				
				
				String it = qtdItem.getText();
				String art = valorUni.getText();

				float inputQtdArtigInt = Integer.parseInt(it);
				float inputPrecFloat = Float.parseFloat(art);

				float totals = inputQtdArtigInt * inputPrecFloat;

				String str = Double.toString(totals);

				System.out.println(str);
				valorTotal.setText(str);

			}
		}
	});
	
	


	teste.add(valor);
	teste.add(valorUni);
	teste.add(total);
	teste.add(valorTotal);
	teste.add(numeroArtigo);
	teste.add(artigo);
	teste.add(descricaoArtigo);
	teste.add(descricaoArtigoText);
	teste.add(qtd);
	teste.add(qtdItem);

	}
	}
