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
public interface JogadorDAO {
    public boolean inserirJogador(Jogador jogador);
    public boolean excluirJogador(Jogador jogador);
    public boolean atualizarJogador(Jogador jogador);
    public ResultSet selecionarTodosJogadores();
    public Jogador encontrarJogador(int idJogador);
}
