/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.modelo.pg;

import academico.modelo.Disciplina;
import academico.modelo.DisciplinaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alexromanelli
 */
public class PgDisciplinaDAO implements DisciplinaDAO {

    @Override
    public boolean inserirDisciplina(Disciplina disciplina) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into disciplina "
                    + "(nome, cargahoraria, ementa, codcurso) "
                    + "values (?, ?, ?, ?)");
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCargaHoraria());
            ps.setString(3, disciplina.getEmenta());
            ps.setInt(4, disciplina.getCurso().getCodCurso());
            int r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de disciplina",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirDisciplina(Disciplina disciplina) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from disciplina "
                    + "where coddisciplina = ?");
            ps.setInt(1, disciplina.getCodDisciplina());
            int r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao excluir registro de disciplina",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean atualizarDisciplina(Disciplina disciplina) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                      "update disciplina "
                    + "set nome = ?, "
                    + "cargahoraria = ?, "
                    + "ementa = ?, "
                    + "codcurso = ? "
                    + "where coddisciplina = ?");
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCargaHoraria());
            ps.setString(3, disciplina.getEmenta());
            ps.setInt(4, disciplina.getCurso().getCodCurso());
            ps.setInt(5, disciplina.getCodDisciplina());
            int r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao atualizar registro de disciplina",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public ResultSet selecionarTodosDisciplinas() {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(""
                    + "select d.coddisciplina, d.nome, d.cargahoraria,"
                    + "       d.ementa, c.codcurso, c.nome, c.cargahoraria,"
                    + "       c.coordenador "
                    + "from disciplina d "
                    + " join curso c on (d.codcurso = c.codcurso) "
                    + " order by d.nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao selecionar disciplinas.",
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Disciplina encontrarDisciplina(int codDisciplina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
