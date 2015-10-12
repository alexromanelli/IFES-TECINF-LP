/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.modelo.pg;

import academico.modelo.AlunoDAO;
import academico.modelo.CursoDAO;
import academico.modelo.DisciplinaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alexromanelli
 */
public class PostgreSQLDAOFactory {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String DBURL = "jdbc:postgresql://localhost:5432/tecinfoacademico";
    public static final String DBUSER = "postgres"; // corrija, se necessário
    public static final String DBPASSWORD = "romanelli"; // corrija, se necessário
    
    private static Connection connection;
    
    public static Connection createConnection() {
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
    
    public static AlunoDAO getAlunoDAO() {
        return new PgAlunoDAO();
    }
    
    public static CursoDAO getCursoDAO() {
        return new PgCursoDAO();
    }

    public static DisciplinaDAO getDisciplinaDAO() {
        return new PgDisciplinaDAO(); // DAO = (D)ata (A)ccess (O)bject
    }
    
}
