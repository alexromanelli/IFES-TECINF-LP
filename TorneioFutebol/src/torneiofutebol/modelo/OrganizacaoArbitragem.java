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
public class OrganizacaoArbitragem {
    private int idOrganizacaoArbitragem;
    private String nome;

    public OrganizacaoArbitragem(int idOrganizacaoArbitragem, String nome) {
        this.idOrganizacaoArbitragem = idOrganizacaoArbitragem;
        this.nome = nome;
    }

    public int getIdOrganizacaoArbitragem() {
        return idOrganizacaoArbitragem;
    }

    public void setIdOrganizacaoArbitragem(int idOrganizacaoArbitragem) {
        this.idOrganizacaoArbitragem = idOrganizacaoArbitragem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
