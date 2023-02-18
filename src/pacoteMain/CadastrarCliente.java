package pacoteMain;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CadastrarCliente extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JTextField inputCnpj;
	JTextField inputRazaoSocial;
	JTextField inputCodCliente;
	JLabel erro;
	
	public CadastrarCliente () {


		JLabel cnpj = new JLabel(); // "C.N.P.J:");
		cnpj.setText("C.N.P.J:");
		cnpj.setBounds(40, 75, 350, 20);
		
		inputCnpj = new JTextField(); // entrada CNPJ
		inputCnpj.setBounds(90, 75, 150, 20); 
		
		JLabel codCliente = new JLabel(); // "Codigo do Cliente"); 
		codCliente.setText("Código:");
		codCliente.setBounds(245, 75, 350, 20);
		
		inputCodCliente = new JTextField(); // entrada CNPJ
		inputCodCliente.setBounds( 290, 75, 150, 20);  
		
		JLabel nome = new JLabel(); //"Razão Social:"
		nome.setText("Razão Social:");
		nome.setBounds(8, 110, 350, 20);
	
		inputRazaoSocial = new JTextField(); // entrada Razão Social:
		inputRazaoSocial.setBounds(90, 110, 350, 20); 
	
		
		JButton botao = new JButton();
		botao.setBounds(450, 140, 100, 40);
		botao.setText("Cadastrar");
		botao.addActionListener(e -> {
		try {
			botao(e );
		} catch (SQLException err) {
			err.printStackTrace();
		}
	});
		
	
	setTitle("Cadastro de Clientes");
	setSize(800, 600);
	setVisible(true);
	setLocationRelativeTo(null);
	//setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
	
	add(nome);
	add(inputCnpj);
	add(cnpj);
	add(inputRazaoSocial);
	add(codCliente);
	add(inputCodCliente);
	add(botao);

	
}

	public void botao(ActionEvent actionevent1)  throws SQLException{
		
		String cpfInput  = inputCnpj.getText();
		String  nomeInput = inputRazaoSocial.getText();
		
				if(!cpfInput.equals("") && !nomeInput.equals("") ) {
					
				Connection conexao = FabricaConexao.getConexao();
				
				String sql = "INSERT INTO cliente (nome_cliente, cpf_cliente) VALUES (?,?)";
				PreparedStatement stmt = conexao.prepareStatement(sql);
				stmt.setString(1, nomeInput );
				stmt.setString(2, cpfInput);
				
				stmt.execute();
				conexao.close();
				
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!!");}
				
				else {
					
				    JOptionPane.showMessageDialog(null, "Informe todos os dados");
					System.out.println("Err informe todos os dados!");}
		}
}