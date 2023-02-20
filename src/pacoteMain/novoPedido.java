package pacoteMain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class novoPedido extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel Pedido;
	JLabel cliente;
	String nome;
	JTextField artigo;
	JTextField descricaoArtigo;
	JTextField qtdItem;
	JTextField valorUni;
	JTextField total;
	JTextField valorTotal;
	JTextField qtd;
	JTextField valorField;
	JTextField artigo2;
	JTextField nomeDoCliente;
	JTextField cpfDoCliente;
	String cpf_cliente;
	String cdArtigo;
	String descricaoArtigoText;
	String str;
	

	Calendar datas = Calendar.getInstance();
	int dia = datas.get(Calendar.DATE); 
	int mes = datas.get(Calendar.MONTH);
	int ano = datas.get(Calendar.YEAR);

	String formData = dia + "/" + mes + 1 + "/" + ano;
	
	


	public novoPedido() {

		JLabel pedido = new JLabel();
		pedido.setText("Pedido");
		pedido.setBounds(5, 0, 50, 25);
		pedido.setVisible(true);
		pedido.setForeground(Color.BLUE);

		JLabel numero = new JLabel();
		numero.setText("Número:");
		numero.setBounds(5, 20, 50, 20);

		JTextField numeroDoPedido = new JTextField();
		numeroDoPedido.setBounds(65, 20, 80, 20);

		JLabel cliente = new JLabel();
		cliente.setText("Cliente: ");
		cliente.setBounds(150, 20, 50, 20);
		

		JTextField nomeDoCliente = new JTextField();
		nomeDoCliente.setBounds(200, 20, 200, 20);

		JTextField cpfDoCliente = new JTextField();
		cpfDoCliente.setBounds(400, 20, 120, 20);
		cpfDoCliente.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int codigo1 = e.getKeyCode();
				int tecla = KeyEvent.VK_ENTER;

				if (codigo1 == tecla) {

					try {
						Connection conexao = FabricaConexao.getConexao();

						String cpf_cliente = cpfDoCliente.getText();

						String sql = "SELECT * FROM cliente WHERE cpf_cliente like ? ";// WHERE cpf_cliente = ?

						PreparedStatement stmt = conexao.prepareStatement(sql); 
						stmt.setString(1, cpf_cliente);

						ResultSet resultado = stmt.executeQuery();

						while (resultado.next()) {
							String nome = resultado.getString("nome_cliente");
							System.out.println(nome);
							String cpf = resultado.getString("cpf_cliente");
							System.out.println(cpf);

							nomeDoCliente.setText(nome);
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

		JLabel data = new JLabel();
		data.setText("Data:   " + formData);
		data.setBounds(25, 45, 150, 20);
		data.setVisible(true);

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
		
		JButton inserir = new JButton();
		inserir.setBounds(500, 500, 90, 20);
		inserir.setText("Inserir");
		inserir.setVisible(true);
		inserir.addActionListener(e -> {
			acctionInserir(e );
			

		});
		
		JButton finalizar = new JButton();
		finalizar.setBounds(600, 500, 90, 20);
		finalizar.setText("Finalizar");
		finalizar.setVisible(true);
		finalizar.addActionListener(e -> {
			try {
				finalizarPedido(e );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	

		setTitle("Novo Pedido");
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);
		
		

		add(pedido);
		add(valor);
		add(numero);
		add(valorUni);
		add(numeroDoPedido);
		add(total);
		add(cliente);
		add(valorTotal);
		add(nomeDoCliente);
		add(cpfDoCliente);
		add(data);
		add(numeroArtigo);
		add(artigo);
		add(descricaoArtigo);
		add(descricaoArtigoText);
		add(qtd);
		add(qtdItem);
		add(inserir);
		add(finalizar);

	   
			

	}

	
	private void finalizarPedido(ActionEvent e)throws SQLException {
		
		String inputnm_cliente = nome ;
		String inputcpfDoCliente = cpf_cliente ;

		String inputCodArtig = cdArtigo;
		String descricaoArtig = descricaoArtigoText;
		String inputQtdArtig = "10";
		String inputPrec = str;


		if (inputCodArtig !="") {
		//	try {
				Connection conexao = FabricaConexao.getConexao();

				String sql = "INSERT INTO pedido (nm_cliente, cpf_cliente, nr_item, descricao, qtd_item, valor_total) VALUES (?,?,?,?,?,?)";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setString(1, inputnm_cliente);
				stmt.setString(2, inputcpfDoCliente);
				stmt.setString(3, inputCodArtig);
				stmt.setString(4, descricaoArtig);
				stmt.setString(5, inputQtdArtig);
				stmt.setString(6, inputPrec);

				stmt.execute();
				conexao.close();

				JOptionPane.showMessageDialog(null, "Artigo cadastrado com sucesso!!");
				
			//} catch (SQLException SQLIntegrityConstraintViolationException) {

				JOptionPane.showMessageDialog(null, "Item já castrado");

			//}
		} else {
			
			JOptionPane.showMessageDialog(null, "Informe todos os dados");
			System.out.println("Err informe todos os dados!");
		}
	}
	
	public void acctionInserir(ActionEvent e) {
		addLabel();

		System.out.println("funciona");

	}

	int position = 90;

	public void addLabel() {

		position += 20;

		JTextField artigo = new JTextField();
		artigo.setVisible(true);
		artigo.setBounds(25, position, 80, 20);
		getContentPane().add(artigo);

		JTextField descricao = new JTextField();
		descricao.setVisible(true);
		descricao.setBounds(105, position, 250, 20);
		getContentPane().add(descricao);

		JTextField qtdItem = new JTextField();
		qtdItem.setBounds(355, position, 25, 20);
		getContentPane().add(qtdItem);

		JTextField valorUni = new JTextField();
		valorUni.setBounds(380, position, 60, 20);
		getContentPane().add(valorUni);

		JTextField valorTotal = new JTextField();
		valorTotal.setBounds(440, position, 60, 20);
		getContentPane().add(valorTotal);

		setResizable(false);

	}

}

