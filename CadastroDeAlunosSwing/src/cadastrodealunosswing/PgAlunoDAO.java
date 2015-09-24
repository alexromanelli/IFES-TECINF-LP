/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alexromanelli
 */
public class PgAlunoDAO implements AlunoDAO {

    @Override
    public boolean inserirAluno(Aluno aluno) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into aluno "
                    + "(matricula, nome, datanascimento, sexo, email) "
                    + "values (?, ?, ?, ?, ?)");
            ps.setInt(1, aluno.getMatricula());
            ps.setString(2, aluno.getNome());
            ps.setString(3, aluno.getDataNascimento());
            ps.setString(4, Character.toString(aluno.getSexo()));
            ps.setString(5, aluno.getEmail());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de aluno", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirAluno(Aluno aluno) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from aluno "
                    + "where matricula = ?");
            ps.setInt(1, aluno.getMatricula());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao excluir registro de aluno", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean atualizarAluno(Aluno aluno) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("update aluno "
                    + "set nome = ?, "
                    + "datanascimento = ?, "
                    + "sexo = ?, "
                    + "email = ? "
                    + "where matricula = ?");
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getDataNascimento());
            ps.setString(3, Character.toString(aluno.getSexo()));
            ps.setString(4, aluno.getEmail());
            ps.setInt(5, aluno.getMatricula());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao atualizar o registro de aluno", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public ResultSet selecionarTodosAlunos() {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("select matricula, "
                    + "nome, datanascimento, sexo, email "
                    + "from aluno "
                    + "order by nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao obter registros de alunos", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Aluno encontrarAluno(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
