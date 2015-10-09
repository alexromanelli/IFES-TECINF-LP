/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.modelo.pg;

import academico.modelo.Curso;
import academico.modelo.CursoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alexromanelli
 */
public class PgCursoDAO implements CursoDAO {

    @Override
    public boolean inserirCurso(Curso curso) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into curso "
                    + "(nome, cargahoraria, coordenador) "
                    + "values (?, ?, ?)");
            ps.setString(1, curso.getNome());
            ps.setInt(2, curso.getCargaHoraria());
            ps.setString(3, curso.getCoordenador());
            int r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de curso",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirCurso(Curso curso) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from curso "
                    + "where codcurso = ?");
            ps.setInt(1, curso.getCodCurso());
            int r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao excluir registro de curso",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean atualizarCurso(Curso curso) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                      "update curso "
                    + "set nome = ?, "
                    + "cargahoraria = ?, "
                    + "coordenador = ? "
                    + "where codcurso = ?");
            ps.setString(1, curso.getNome());
            ps.setInt(2, curso.getCargaHoraria());
            ps.setString(3, curso.getCoordenador());
            ps.setInt(4, curso.getCodCurso());
            int r = ps.executeUpdate();
            return r == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao atualizar registro de curso",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public ResultSet selecionarTodosCursos() {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("select codcurso, "
                    + "nome, cargahoraria, coordenador "
                    + "from curso "
                    + "order by nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao selecionar registros de cursos",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Curso encontrarCurso(int codigoCurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
