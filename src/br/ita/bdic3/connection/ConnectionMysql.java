package br.ita.bdic3.connection;

public class ConnectionMysql extends ConnectionDataBase {

	   
    private static ConnectionMysql singleton;
    

    private ConnectionMysql() {
        super();
        this.host = "127.0.0.1"; 
        this.usuario = "root";
        this.base = "bdic3";
        this.porta = "3306";
        this.senha = "root";
        className = "com.mysql.jdbc.Driver";
    }

    public static ConnectionMysql getInstance() {
        if(singleton == null){
            singleton = new ConnectionMysql();
        }
        singleton.conectar();
        return singleton;
    }
}