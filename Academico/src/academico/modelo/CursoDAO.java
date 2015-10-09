/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.modelo;

import java.sql.ResultSet;

/**
 *
 * @author alexromanelli
 */
public interface CursoDAO {
    public boolean inserirCurso(Curso curso);
    public boolean excluirCurso(Curso curso);
    public boolean atualizarCurso(Curso curso);
    public ResultSet selecionarTodosCursos();
    public Curso encontrarCurso(int codigoCurso);
}
