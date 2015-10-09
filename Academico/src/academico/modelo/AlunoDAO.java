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
public interface AlunoDAO {
    public boolean inserirAluno(Aluno aluno);
    public boolean excluirAluno(Aluno aluno);
    public boolean atualizarAluno(Aluno aluno);
    public ResultSet selecionarTodosAlunos();
    public Aluno encontrarAluno(int matricula);
}
