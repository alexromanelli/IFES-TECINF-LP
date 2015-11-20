/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo;

import java.util.Date;

/**
 *
 * @author alexromanelli
 */
public class Jogador extends Pessoa {
    private PosicaoJogador posicaoPreferencial;
    private TimeFutebol time;

    public Jogador(int idJogador, String nome, Date dataNascimento, 
            char sexo, String nacionalidade, PosicaoJogador posicaoPreferencial, 
            TimeFutebol time) {
        super(idJogador, nome, dataNascimento, sexo, nacionalidade);
        this.posicaoPreferencial = posicaoPreferencial;
        this.time = time;
    }

    public PosicaoJogador getPosicaoPreferencial() {
        return posicaoPreferencial;
    }

    public void setPosicaoPreferencial(PosicaoJogador posicaoPreferencial) {
        this.posicaoPreferencial = posicaoPreferencial;
    }

    public TimeFutebol getTime() {
        return time;
    }

    public void setTime(TimeFutebol time) {
        this.time = time;
    }
    
    
}
