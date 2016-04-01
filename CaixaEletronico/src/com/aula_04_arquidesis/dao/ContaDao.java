package com.aula_04_arquidesis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aula_04_arquidesis.connection.Conexao;
import com.aula_04_arquidesis.model.Conta;

public class ContaDao {

	public ContaDao() {
		
	}

	public double consultarSaldo(Conta conta) {

		String sqlSelect = "SELECT saldo FROM conta WHERE conta.conta = ? ";
		try (Connection conn = new Conexao().conectar(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, conta.getConta());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					conta.setSaldo(rs.getDouble("saldo"));
				}
			}catch(Exception e){
				return 0;
			}
		} catch (SQLException e) {
			return 0;
		}
		return conta.getSaldo();
	}

	public int atualizarSaldo(Conta conta) {
		String sqlUpdate = "UPDATE conta SET saldo = ? WHERE conta = ?";
		int ra = 0;
		try (Connection conn = new Conexao().conectar(); 
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setDouble(1, conta.getSaldo());
			stm.setInt(2, conta.getConta());
			ra = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ra;
	}
}
