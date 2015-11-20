/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo;

/**
 *
 * @author alexromanelli
 */
public class PosicaoJogador {
    private int idPosicao;
    private String nome;

    public PosicaoJogador(int idPosicao, String nome) {
        this.idPosicao = idPosicao;
        this.nome = nome;
    }

    public int getIdPosicao() {
        return idPosicao;
    }

    public void setIdPosicao(int idPosicao) {
        this.idPosicao = idPosicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    
}
