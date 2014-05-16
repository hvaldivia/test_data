package br.ita.bdic3.model;

import br.ita.bdic3.dao.FraudeDAO;

public class Fraude {

	private int fra_id, tra_id;
	private String fra_nome, fra_tipo, fra_forma_deteccao, fra_data_detecao, inc_descricao;
	
	
	public Fraude (){
		
	}
	
	public void fraudeDev(int tra_id){
		this.fra_nome = "teste";
		this.fra_tipo = "AUTO-FRAUDE";
		this.fra_forma_deteccao = "FILTRO_PRE_TRANSACAO";
		this.fra_forma_deteccao = "BOXSPLOT-HIVE";
	    this.fra_data_detecao = "12/06/2014";
	    this.tra_id = tra_id;
	}
	
	
	public void save(Fraude fraude){

        new FraudeDAO().save(fraude);

   }


	public int getTra_id() {
		return tra_id;
	}


	public void setTra_id(int tra_id) {
		this.tra_id = tra_id;
	}


	public String getFra_nome() {
		return fra_nome;
	}


	public void setFra_nome(String fra_nome) {
		this.fra_nome = fra_nome;
	}


	public String getFra_tipo() {
		return fra_tipo;
	}


	public void setFra_tipo(String fra_tipo) {
		this.fra_tipo = fra_tipo;
	}


	public String getFra_forma_deteccao() {
		return fra_forma_deteccao;
	}


	public void setFra_forma_deteccao(String fra_forma_deteccao) {
		this.fra_forma_deteccao = fra_forma_deteccao;
	}


	public String getFra_data_detecao() {
		return fra_data_detecao;
	}


	public void setFra_data_detecao(String fra_data_detecao) {
		this.fra_data_detecao = fra_data_detecao;
	}


	public String getInc_descricao() {
		return inc_descricao;
	}


	public void setInc_descricao(String inc_descricao) {
		this.inc_descricao = inc_descricao;
	}


	public int getFra_id() {
		return fra_id;
	}
	
	
	

	
	
	
	
	
}
