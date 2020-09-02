package exerciciosSGBD.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exerciciosSGBD.model.Cliente;

public class ClienteDB {
	
	public void inserir(Cliente cliente) {
		Connection con = ConexaoDB.getConnection();
		String sql = "INSERT INTO cliente (nome, telefone) VALUES (?, ?)";
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			
			stmt.execute();
			
			JOptionPane.showMessageDialog(null, "Cliente cadastrado!");
			
			stmt.close();
			ConexaoDB.closeConnection(con);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}
	
	public void atualizar(Cliente cliente) {
		Connection con = ConexaoDB.getConnection();
		String sql = "UPDATE cliente SET nome = ?, telefone = ? WHERE id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setInt(3, cliente.getId());
			
			stmt.execute();
			
			JOptionPane.showMessageDialog(null, "Cliente alterada!", "Cliente Alterado!", JOptionPane.INFORMATION_MESSAGE);

			stmt.close();
			ConexaoDB.closeConnection(con);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}
	
	public void apagar(Cliente cliente) {
		Connection con = ConexaoDB.getConnection();
		String sql = "DELETE FROM cliente WHERE id = ?";
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, cliente.getId());
			
			stmt.execute();
			
			JOptionPane.showMessageDialog(null, "Cliente apagado!", "Cliente Apagado!", JOptionPane.INFORMATION_MESSAGE);

			stmt.close();
			ConexaoDB.closeConnection(con);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
		
	}
	
	public Cliente buscar(int id) {
		Connection con = ConexaoDB.getConnection();
		String sql = "SELECT * FROM cliente WHERE id = ?";
		PreparedStatement stmt;
		Cliente cliente = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"));
			}
			
			rs.close();
			stmt.close();
			ConexaoDB.closeConnection(con);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
		
		ConexaoDB.closeConnection(con);
		
		return cliente;
	}
	
	public List<Cliente> listar() {
		Connection con = ConexaoDB.getConnection();
		String sql = "SELECT * FROM cliente";
		List<Cliente> clientes = new ArrayList<>();
	
		try {
			Statement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"));
				clientes.add(cliente);
			}
			
			rs.close();
			stmt.close();
			ConexaoDB.closeConnection(con);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
		
		return clientes;
		
		
		
	}
}
