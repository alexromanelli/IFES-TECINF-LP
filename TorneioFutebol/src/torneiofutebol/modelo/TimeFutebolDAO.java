/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo;

import java.sql.ResultSet;

/**
 *
 * @author alexromanelli
 */
public interface TimeFutebolDAO {
    public boolean inserirTimeFutebol(TimeFutebol time);
    public boolean excluirTimeFutebol(TimeFutebol time);
    public boolean atualizarTimeFutebol(TimeFutebol time);
    public ResultSet selecionarTodosTimesFutebol();
    public TimeFutebol encontrarTimeFutebol(int idTimeFutebol);
}
