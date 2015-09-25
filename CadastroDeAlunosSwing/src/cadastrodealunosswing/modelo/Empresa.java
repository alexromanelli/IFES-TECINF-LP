/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing.modelo;

/**
 *
 * @author alexromanelli
 */
public class Empresa {
    private String cnpj;
    private String inscricaoEstadual;
    private String razaoSocial;
    private String nomeFantasia;
    private char simples;
    private String uf;
    private String municipio;

    public Empresa(String cnpj, String inscricaoEstadual, String razaoSocial, String nomeFantasia, char simples, String uf, String municipio) {
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.simples = simples;
        this.uf = uf;
        this.municipio = municipio;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public char getSimples() {
        return simples;
    }

    public void setSimples(char simples) {
        this.simples = simples;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
}
