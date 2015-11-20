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
public class Tecnico extends Pessoa {
    // Pessoa é superclasse de Tecnico
    // Tecnico é subclasse de Pessoa

    private TimeFutebol time;

    public Tecnico(int idTecnico, String nome, Date dataNascimento, 
            char sexo, String nacionalidade, TimeFutebol time) {
        super(idTecnico, nome, dataNascimento, sexo, nacionalidade);
        this.time = time;
    }

    public TimeFutebol getTime() {
        return time;
    }

    public void setTime(TimeFutebol time) {
        this.time = time;
    }
    
    
}
