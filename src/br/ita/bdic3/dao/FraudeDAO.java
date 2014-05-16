package br.ita.bdic3.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import br.ita.bdic3.connection.ConnectionMysql;
import br.ita.bdic3.model.Fraude;

public class FraudeDAO {

	private ConnectionMysql conexao;

	public FraudeDAO() {
		conexao = ConnectionMysql.getInstance();
		conexao.conectar();
	}

	public boolean save(Fraude f) {

		String sql = "INSERT INTO fraude (fra_nome, fra_tipo, fra_forma_deteccao, fra_data_deteccao, tra_id)"
				+ "VALUES ('"
				+ f.getFra_nome()
				+ "', '"
				+ f.getFra_tipo()
				+ "', '"
				+ f.getFra_forma_deteccao()
				+ "', '"
				+ f.getFra_data_detecao()
				+ "', "
				+ f.getTra_id() + ");";
		return this.conexao.executarComandosSQL(sql);

	}

}