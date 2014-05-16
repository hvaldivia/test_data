package br.ita.bdic3;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ita.bdic3.dao.TransacaoDAO;
import br.ita.bdic3.model.Fraude;
import br.ita.bdic3.model.Transacao;
import br.ita.bdic3.util.Boxsplot;
import br.ita.bdic3.util.HiveJdbcClient;

public class Run {
	
	static Transacao tm = new Transacao();
	
	public static void main(String[] args) throws SQLException {
		Run run = new Run();
		run.olap02us05();

	}
	
	public void olap02us05() throws SQLException{
		// Captura Entrada - Sem tratamentos
		int cli_id = 40000; 
		String valor = JOptionPane.showInputDialog("Qual é o valor da compra?");

		float valor_max = 0;
		float upper_limit = 0;

		// Conexão HIVE
		HiveJdbcClient hive = new HiveJdbcClient();

		try {
			System.out.println("Status da Conexão HIVE :"
					+ hive.connectionHive());
			valor_max = hive.consultaHive(cli_id);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Verificação da Transação
		Boxsplot boxsplot = new Boxsplot();

		try {
			upper_limit = boxsplot.boxSplot(valor_max);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Interação com o Usuário
		//
		// Caso o valor da transação atual seja
		// maior que o upper_limit detectado na estatistica
		// dispara-se uma mensagem ao cliente solitando confirmação
		// de compra
		if (Float.parseFloat(valor) > upper_limit) {
			switch (JOptionPane.showConfirmDialog(null, 
					   "Transação Incomum Registrada \r\n Valor "+valor + "\r\n Você autoriza essa transação ?" ,null, JOptionPane.YES_NO_OPTION)) {
			case 0:
				tm.transacaoDev(cli_id, Float.parseFloat(valor), "N");
				tm.save(tm);
				System.out.println("Transação Aprovada pelo Cliente");
				break;
			case 1:
				tm.transacaoDev(cli_id, Float.parseFloat(valor), "S");
				tm.save(tm);
				int tra_id = tm.lastId(cli_id);
				Fraude f = new Fraude();
				f.fraudeDev(tra_id);
				f.save(f);
				System.out.println("Salvar Fraude");
				break;
			}
		// Senão para na comparação acima
		// a transação é concretizada	
		} else {
		
			tm.transacaoDev(cli_id, Float.parseFloat(valor),"");
			tm.save(tm);
			
			JOptionPane.showMessageDialog(null, "Transação Realizada com Sucesso");
		}
	}

}
