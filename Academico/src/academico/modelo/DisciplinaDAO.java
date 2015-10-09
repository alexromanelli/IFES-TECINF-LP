/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing.modelo;

import java.sql.ResultSet;

/**
 *
 * @author alexromanelli
 */
public interface DisciplinaDAO {
    public boolean inserirDisciplina(Disciplina disciplina);
    public boolean excluirDisciplina(Disciplina disciplina);
    public boolean atualizarDisciplina(Disciplina disciplina);
    public ResultSet selecionarTodosDisciplinas();
    public Disciplina encontrarDisciplina(int codDisciplina);
}

