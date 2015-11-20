/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo.pg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import torneiofutebol.modelo.ArbitroDAO;
import torneiofutebol.modelo.DAOFactory;
import torneiofutebol.modelo.JogadorDAO;
import torneiofutebol.modelo.OrganizacaoArbitragemDAO;
import torneiofutebol.modelo.PosicaoJogadorDAO;
import torneiofutebol.modelo.TecnicoDAO;
import torneiofutebol.modelo.TimeFutebolDAO;

/**
 *
 * @author alexromanelli
 */
public class PostgreSqlDAOFactory extends DAOFactory {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String DBURL = "jdbc:postgresql://localhost:5432/campeonato";
    public static final String DBUSER = "postgres"; // corrija, se necessário
    public static final String DBPASSWORD = "romanelli"; // corrija, se necessário
    
    private static Connection connection;
    
    private static Connection createConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            return connection;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,
                    "Conector para PostgreSQL não foi encontrado.", 
                    "Erro de conexão", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível estabelecer conexão com o banco.", 
                    "Erro de conexão", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static Connection getConnection() {
        if (connection == null)
            return createConnection();
        else
            return connection;
    }

    @Override
    public JogadorDAO getJogadorDAO() {
        return new PgJogadorDAO();
    }

    @Override
    public ArbitroDAO getArbitroDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecnicoDAO getTecnicoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeFutebolDAO getTimeFutebolDAO() {
        return new PgTimeFutebolDAO();
    }

    @Override
    public PosicaoJogadorDAO getPosicaoJogadorDAO() {
        return new PgPosicaoJogadorDAO();
    }

    @Override
    public OrganizacaoArbitragemDAO getOrganizacaoArbitragemDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
