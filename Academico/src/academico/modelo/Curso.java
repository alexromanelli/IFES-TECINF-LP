/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.modelo;

/**
 *
 * @author alexromanelli
 */
public class Curso {
    
    private int codCurso;
    private String nome;
    private int cargaHoraria;
    private String coordenador;

    public Curso(int codCurso, String nome, int cargaHoraria, String coordenador) {
        this.codCurso = codCurso;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.coordenador = coordenador;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    
}
