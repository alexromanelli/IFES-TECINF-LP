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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirDisciplina(Disciplina disciplina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarDisciplina(Disciplina disciplina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
