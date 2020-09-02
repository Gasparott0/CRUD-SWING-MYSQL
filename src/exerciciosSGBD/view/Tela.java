package exerciciosSGBD.view;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exerciciosSGBD.db.ClienteDB;
import exerciciosSGBD.model.Cliente;

public class Tela {

	private JFrame frame;
	private ClienteDB clienteDB;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtId;
	private JButton btnProcurar;
	private JButton btnInserir;
	private JButton btnApagar;
	private JButton btnAtualizar;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnListar;
	
	private String operation;

	public JFrame getFrame() {
		return frame;
	}
	
	public Tela() {
		initialize();
		this.clienteDB = new ClienteDB();
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Arial", Font.PLAIN, 12));
		lblId.setBounds(70, 75, 13, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNome.setBounds(70, 100, 37, 14);
		frame.getContentPane().add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTelefone.setBounds(70, 123, 51, 14);
		frame.getContentPane().add(lblTelefone);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setBounds(131, 97, 261, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setToolTipText("(DDD) XXXXX-XXXX ");
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(131, 120, 261, 20);
		frame.getContentPane().add(txtTelefone);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Arial", Font.PLAIN, 12));
		txtId.setColumns(10);
		txtId.setBounds(131, 72, 130, 20);
		frame.getContentPane().add(txtId);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener((e) -> btnProcurarActionPerformed(e));
		btnProcurar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnProcurar.setBounds(270, 71, 122, 23);
		frame.getContentPane().add(btnProcurar);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener((e) -> btnInserirActionPerformed(e));
		btnInserir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnInserir.setBounds(70, 158, 100, 23);
		frame.getContentPane().add(btnInserir);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener((e) -> btnApagarActionPerformed(e));
		btnApagar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnApagar.setBounds(292, 158, 100, 23);
		frame.getContentPane().add(btnApagar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener((e) -> btnAtualizarActionPerformed(e));
		btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAtualizar.setBounds(182, 158, 100, 23);
		frame.getContentPane().add(btnAtualizar);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener((e) -> btnConfirmarActionPerformed(e));
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmar.setBounds(239, 192, 100, 23);
		frame.getContentPane().add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener((e) -> btnCancelarActionPerformed(e));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(129, 192, 100, 23);
		frame.getContentPane().add(btnCancelar);
		
		btnListar = new JButton("...");
		btnListar.addActionListener((e) -> btnListarActionPerformed(e)); 
		btnListar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnListar.setEnabled(true);
		btnListar.setBounds(391, 71, 23, 23);
		frame.getContentPane().add(btnListar);
		this.frame.setVisible(true);
		
		action1();
	}

	private void btnListarActionPerformed(ActionEvent e) {
		new Tabela();
	}

	private void btnProcurarActionPerformed(ActionEvent evt) {
		Cliente cliente;
		
		if(txtId.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira um ID!", "Não achado!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			cliente = clienteDB.buscar(Integer.parseInt(txtId.getText()));
			
			if(cliente != null) {
				txtNome.setText(cliente.getNome());
				txtTelefone.setText(cliente.getTelefone());
			} else
				JOptionPane.showMessageDialog(null, "Pessoa não encontrada!", "Não achado!", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Insira um valor numérico!", "Não achado!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void btnInserirActionPerformed(ActionEvent evt) {
		clear();
		action2();
		operation = "Insert";	
	}
	
	private void btnAtualizarActionPerformed(ActionEvent evt) {
		if(txtId.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira um ID!", "Não achado!", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			action2();
			operation = "Update";
		}
	}
	
	private void btnApagarActionPerformed(ActionEvent evt) {
		if(txtId.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira um ID!", "Não achado!", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			action2();
			txtNome.setEnabled(false);
			txtTelefone.setEnabled(false);
			operation = "Delete";
		}
	}
	
	private void btnCancelarActionPerformed(ActionEvent evt) {
		clear();
		action1();
	}
	
	private void btnConfirmarActionPerformed(ActionEvent evt) {
		switch (operation) {
			case "Insert":
				clienteDB.inserir(new Cliente(txtNome.getText(), txtTelefone.getText()));
				clear();
				action1();
				break;
				
			case "Update":
				clienteDB.atualizar(new Cliente((Integer.parseInt(txtId.getText())), txtNome.getText(), txtTelefone.getText()));
				clear();
				action1();
				break;
				
			case "Delete":
				clienteDB.apagar(new Cliente((Integer.parseInt(txtId.getText())), txtNome.getText(), txtTelefone.getText()));
				clear();
				action1();
				break;
		
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	}

	private void action1() {
		this.txtId.setEnabled(true);
		this.txtNome.setEnabled(false);
		this.txtTelefone.setEnabled(false);
		this.btnProcurar.setEnabled(true);
		this.btnListar.setEnabled(true);
		
		this.btnInserir.setEnabled(true);
		this.btnAtualizar.setEnabled(true);
		this.btnApagar.setEnabled(true);

		this.btnCancelar.setEnabled(false);
		this.btnConfirmar.setEnabled(false);
	}
	
	private void action2() {
		this.txtId.setEnabled(false);
		this.txtNome.setEnabled(true);
		this.txtTelefone.setEnabled(true);
		this.btnProcurar.setEnabled(false);
		this.btnListar.setEnabled(false);
		
		this.btnInserir.setEnabled(false);
		this.btnAtualizar.setEnabled(false);
		this.btnApagar.setEnabled(false);
		
		this.btnCancelar.setEnabled(true);
		this.btnConfirmar.setEnabled(true);
	}
	
	private void clear() {
		this.txtId.setText("");
		this.txtNome.setText("");
		this.txtTelefone.setText("");
	}
}
