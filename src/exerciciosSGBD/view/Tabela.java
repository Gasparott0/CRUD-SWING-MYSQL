package exerciciosSGBD.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import exerciciosSGBD.db.ClienteDB;
import exerciciosSGBD.model.Cliente;

public class Tabela {

	private JFrame frame;
	private JTable table;

	private ClienteDB clienteDB;
	
	String[] colunas = {"Id","Nome","Telefone"};

	public Tabela() {
		initialize();
	}

	private void initialize() {
		
		clienteDB = new ClienteDB();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		TableModel dataModel = new DefaultTableModel(null, new String[]{"ID", "NOME", "TELEFONE"});
		table.setModel(dataModel);
		table.setBounds(10, 11, 414, 239);
		frame.getContentPane().add(table);
		
		populaTabela();

		frame.setVisible(true);
	}

	private void populaTabela() {
		List<Cliente> clientes = clienteDB.listar();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);

		clientes.forEach(c -> {
			model.addRow(new Object[] {
					c.getId(), c.getNome(), c.getTelefone() });
		});

	}
}
