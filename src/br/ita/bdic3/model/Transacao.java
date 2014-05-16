package br.ita.bdic3.model;

import java.sql.SQLException;

import br.ita.bdic3.dao.TransacaoDAO;

public class Transacao {
	
	private int tra_id, etb_id, cli_id, mid_id, loc_id, ses_id, tra_quantidadedeparcela, mep_id;
	private String tra_tipo, tra_data, tra_hora, tra_descricao_pagamento, tra_validade, tra_url_pagamento, tra_status, tra_confirmed;
	private float tra_total;
	
	public Transacao(){
		
	}
	
	public void transacaoDev(int cli_id, float tra_total, String confirmed){
		this.tra_tipo = "E-COMMERCE";
		this.tra_total = tra_total;
		this.tra_data = "12/06/2014";
		this.tra_hora = "19:09:25";
		this.etb_id = 3286;
		this.cli_id = cli_id;
		this.mid_id = 3740;
		this.loc_id = 250130;
		this.ses_id = 45493;
		this.tra_descricao_pagamento = "";
		this.tra_validade = "";
		this.tra_url_pagamento = "";
		this.tra_status = "";
		this.tra_quantidadedeparcela = 0;
		this.mep_id = 1;
		this.tra_confirmed = confirmed;
	}

	public void save(Transacao transacao){

        new TransacaoDAO().save(transacao);

   }
	
   public int lastId (int cli_id) throws SQLException{
	   
	   return new TransacaoDAO().lastId(cli_id);
   }
	
	public int getTra_id() {
		return tra_id;
	}

	public void setTra_id(int tra_id) {
		this.tra_id = tra_id;
	}

	public int getEtb_id() {
		return etb_id;
	}

	public void setEtb_id(int etb_id) {
		this.etb_id = etb_id;
	}

	public int getMid_id() {
		return mid_id;
	}

	public void setMid_id(int mid_id) {
		this.mid_id = mid_id;
	}

	public int getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}

	public int getSes_id() {
		return ses_id;
	}

	public void setSes_id(int ses_id) {
		this.ses_id = ses_id;
	}

	public int getTra_quantidadedeparcela() {
		return tra_quantidadedeparcela;
	}

	public void setTra_quantidadedeparcela(int tra_quantidadedeparcela) {
		this.tra_quantidadedeparcela = tra_quantidadedeparcela;
	}

	public int getMep_id() {
		return mep_id;
	}

	public void setMep_id(int mep_id) {
		this.mep_id = mep_id;
	}

	public String getTra_tipo() {
		return tra_tipo;
	}

	public void setTra_tipo(String tra_tipo) {
		this.tra_tipo = tra_tipo;
	}

	public String getTra_data() {
		return tra_data;
	}

	public void setTra_data(String tra_data) {
		this.tra_data = tra_data;
	}

	public String getTra_hora() {
		return tra_hora;
	}

	public void setTra_hora(String tra_hora) {
		this.tra_hora = tra_hora;
	}

	public String getTra_descricao_pagamento() {
		return tra_descricao_pagamento;
	}

	public void setTra_descricao_pagamento(String tra_descricao_pagamento) {
		this.tra_descricao_pagamento = tra_descricao_pagamento;
	}

	public String getTra_validade() {
		return tra_validade;
	}

	public void setTra_validade(String tra_validade) {
		this.tra_validade = tra_validade;
	}

	public String getTra_url_pagamento() {
		return tra_url_pagamento;
	}

	public void setTra_url_pagamento(String tra_url_pagamento) {
		this.tra_url_pagamento = tra_url_pagamento;
	}

	public String getTra_status() {
		return tra_status;
	}

	public void setTra_status(String tra_status) {
		this.tra_status = tra_status;
	}

	public String getTra_confirmed() {
		return tra_confirmed;
	}

	public void setTra_confirmed(String tra_confirmed) {
		this.tra_confirmed = tra_confirmed;
	}

	public int getCli_id() {
		return cli_id;
	}
	
	public float getTra_total() {
		return tra_total;
	}

	public void setTra_total(float tra_total) {
		this.tra_total = tra_total;
	}
}
