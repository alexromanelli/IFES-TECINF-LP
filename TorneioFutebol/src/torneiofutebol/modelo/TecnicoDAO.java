/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alexromanelli
 */
public interface TecnicoDAO {
    public boolean inserirTecnico(Tecnico tecnico);
    public boolean excluirTecnico(Tecnico tecnico);
    public boolean atualizarTecnico(Tecnico tecnico);
    public ResultSet selecionarTodosTecnicos();
    public Tecnico encontrarTecnico(int idTecnico);
    public ArrayList<Tecnico> selecionarTecnicosPorCriterios(
            boolean restringirNome, String nome,
            boolean restringirDataNascimento, Date dataInicio, Date dataFim,
            boolean restringirSexo, char sexo, 
            boolean restringirNacionalidade, String nacionalidade);
}
