/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing.modelo.pg;

import cadastrodealunosswing.modelo.Curso;
import cadastrodealunosswing.modelo.CursoDAO;
import java.sql.ResultSet;

/**
 *
 * @author alexromanelli
 */
public class PgCursoDAO implements CursoDAO {

    @Override
    public boolean inserirCurso(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirCurso(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarCurso(Curso curso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet selecionarTodosCursos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Curso encontrarCurso(int codigoCurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
