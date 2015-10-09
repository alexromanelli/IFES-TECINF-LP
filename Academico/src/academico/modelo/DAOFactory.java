/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.modelo;

/**
 *
 * @author alexromanelli
 */
public interface DAOFactory {
    public static final int POSTGRESQL = 1;
    public static final int MYSQL = 2;
    public static final int WEB = 3;
    
    public AlunoDAO getAlunoDAO();
    public DisciplinaDAO getDisciplinaDAO();
    public CursoDAO getCursoDAO();
    
}
