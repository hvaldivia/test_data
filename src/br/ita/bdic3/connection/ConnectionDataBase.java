package br.ita.bdic3.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDataBase {

    //Attributes//
    protected String senha;
    protected String usuario;
    protected String host;
    protected String base;
    protected String className;
    protected String porta;
    protected Connection con;

    protected ConnectionDataBase() {
    }

    public void conectar() {
        String conexao = "";
        if (con != null) {
            return;
        }
        try {
            Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("erro de conexão");           
        }
        try {
            conexao = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
            conexao = String.format(conexao, host, porta, base, usuario, senha);
            con = DriverManager.getConnection(conexao, usuario, senha);
        } catch (Exception e) {
            System.out.println("erro de conexão");
        }
    }

    public void desconectar() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Commands SQL//
    public boolean executarComandosSQL(String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet pegarResultadoSQL(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}



