package br.ita.bdic3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.ita.bdic3.connection.ConnectionMysql;
import br.ita.bdic3.model.Transacao;

public class TransacaoDAO {

	private ConnectionMysql conexao;

	public TransacaoDAO() {
		conexao = ConnectionMysql.getInstance();
		conexao.conectar();
	}

	public boolean save(Transacao t) {

		String sql = "INSERT INTO transacao "
				+ "(tra_tipo, tra_total, tra_data, tra_hora, etb_id, "
				+ "cli_id, mid_id, loc_id, ses_id, tra_descricao_pagamento, "
				+ "tra_validade, tra_url_pagamento, tra_status, tra_quantidadeparcela, "
				+ "mep_id, tra_confirmed)" + "VALUES ('"
				+ t.getTra_tipo()
				+ "', "
				+ t.getTra_total()
				+ ", '"
				+ t.getTra_data()
				+ "', '"
				+ t.getTra_hora()
				+ "', "
				+ t.getEtb_id()
				+ ", "
				+ t.getCli_id()
				+ ", "
				+ t.getMid_id()
				+ ", "
				+ t.getLoc_id()
				+ ", "
				+ t.getSes_id()
				+ ", '"
				+ t.getTra_descricao_pagamento()
				+ "', '"
				+ t.getTra_validade()
				+ "', '"
				+ t.getTra_url_pagamento()
				+ "', '"
				+ t.getTra_status()
				+ "', "
				+ t.getTra_quantidadedeparcela()
				+ ", " + t.getMep_id() + ", '" + t.getTra_confirmed() + "');";
		return this.conexao.executarComandosSQL(sql);

	}

	public int lastId(int cli_id) throws SQLException {

		int id = 0;
		ResultSet rs = conexao
				.pegarResultadoSQL("SELECT transacao.tra_id as last FROM transacao WHERE transacao.cli_id = "
						+ cli_id + " GROUP BY last DESC LIMIT 1;");
		while (rs.next()) {
			id = rs.getInt("last");
			System.out.println("LAST ID: " + id);
		}
		return id;

	}
}
