package pacoteMain;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClasseFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	JMenuBar barra = new JMenuBar();
	
	JMenu mercantil = new JMenu("Mercantil");
	JMenu produto = new JMenu("Produtos");
	JMenu cliente = new JMenu("Clientes");
	
	JMenuItem novoPedido = new JMenuItem("Novo Pedido");
	JMenuItem cadastrarArtigo = new JMenuItem("Cadastrar Artigo");
	JMenuItem consultaEstoque = new JMenuItem("Estoque");
	JMenuItem cadastrarCliente = new JMenuItem("Cadastrar Cliente");
	

	
	public ClasseFrame ()  {
		
		setJMenuBar(barra);
		barra.add(mercantil);
		barra.add(produto);
		barra.add(cliente);
		
		mercantil.add(novoPedido);
		produto.add(consultaEstoque);
		produto.add(cadastrarArtigo);
		cliente.add(cadastrarCliente);
		
		cadastrarCliente.addActionListener(e -> {
			cadastrarCliente(e );
		});
		
		novoPedido.addActionListener( b ->  {
			novoPedido(b );
		});
		
		cadastrarArtigo.addActionListener(c ->{
			CadastrarProduto(c );
		});
		
		
		setTitle("ERP 1.0");
		setSize(800, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		

		
	}
	public void cadastrarCliente(ActionEvent ave) {
		new CadastrarCliente();	
	}
	public void novoPedido(ActionEvent ave) {
		new novoPedido();
	}
	public void CadastrarProduto(ActionEvent ave) {
		new CadastroArtigo();
	}
}




