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
public class TimeFutebol {
    private int idTime;
    private String nome;
    private String nomeAbreviado;
    private String sedePais;
    private String sedeEstado;
    private String sedeCidade;

    public TimeFutebol(int idTime, String nome, String nomeAbreviado, String sedePais, String sedeEstado, String sedeCidade) {
        this.idTime = idTime;
        this.nome = nome;
        this.nomeAbreviado = nomeAbreviado;
        this.sedePais = sedePais;
        this.sedeEstado = sedeEstado;
        this.sedeCidade = sedeCidade;
    }

    public int getIdTime() {
        return idTime;
    }

    public void setIdTime(int idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public void setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
    }

    public String getSedePais() {
        return sedePais;
    }

    public void setSedePais(String sedePais) {
        this.sedePais = sedePais;
    }

    public String getSedeEstado() {
        return sedeEstado;
    }

    public void setSedeEstado(String sedeEstado) {
        this.sedeEstado = sedeEstado;
    }

    public String getSedeCidade() {
        return sedeCidade;
    }

    public void setSedeCidade(String sedeCidade) {
        this.sedeCidade = sedeCidade;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    
}
