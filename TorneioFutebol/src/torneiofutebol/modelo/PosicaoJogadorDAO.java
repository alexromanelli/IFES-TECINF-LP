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
public interface PosicaoJogadorDAO {
    public boolean inserirPosicaoJogador(PosicaoJogador posicaoJogador);
    public boolean atualizarPosicaoJogador(PosicaoJogador posicaoJogador);
    public boolean excluirPosicaoJogador(PosicaoJogador posicaoJogador);
    public ResultSet selecionarTodasPosicoesJogador();
    public PosicaoJogador encontrarPosicaoJogador(int id);
}
