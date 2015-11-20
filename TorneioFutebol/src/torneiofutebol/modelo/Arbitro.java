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
public class Arbitro {
    private int idArbitro;
    private String nome;
    private Date dataNascimento;
    private char sexo;
    private String nacionalidade;
    private OrganizacaoArbitragem organizacaoArbitragem;

    public Arbitro(int idArbitro, String nome, Date dataNascimento, char sexo, String nacionalidade, OrganizacaoArbitragem organizacaoArbitragem) {
        this.idArbitro = idArbitro;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.organizacaoArbitragem = organizacaoArbitragem;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public OrganizacaoArbitragem getOrganizacaoArbitragem() {
        return organizacaoArbitragem;
    }

    public void setOrganizacaoArbitragem(OrganizacaoArbitragem organizacaoArbitragem) {
        this.organizacaoArbitragem = organizacaoArbitragem;
    }
    
    
}
