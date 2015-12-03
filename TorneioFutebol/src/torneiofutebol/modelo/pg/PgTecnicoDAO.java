/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo.pg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import torneiofutebol.modelo.Jogador;
import torneiofutebol.modelo.PosicaoJogador;
import torneiofutebol.modelo.Tecnico;
import torneiofutebol.modelo.TecnicoDAO;
import torneiofutebol.modelo.TimeFutebol;

/**
 *
 * @author alexandre
 */
public class PgTecnicoDAO implements TecnicoDAO {

    @Override
    public boolean inserirTecnico(Tecnico tecnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirTecnico(Tecnico tecnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarTecnico(Tecnico tecnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet selecionarTodosTecnicos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tecnico encontrarTecnico(int idTecnico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Tecnico> selecionarTecnicosPorCriterios(
            boolean restringirNome, String nome, 
            boolean restringirDataNascimento, Date dataInicio, Date dataFim, 
            boolean restringirSexo, char sexo, 
            boolean restringirNacionalidade, String nacionalidade) {
        Connection con = PostgreSqlDAOFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(
                    "select tec.id_tecnico, tec.nome, tec.data_nascimento, tec.sexo, tec.nacionalidade,"
                    + " t.id_time, t.nome, t.nome_abreviado, "
                    + " t.sede_pais, t.sede_estado, t.sede_cidade "
                    + " from tecnico tec "
                    + " join time_futebol t on (tec.id_time = t.id_time) "
                    + "where (upper(tec.nome) like ?) " 
                    + "  and (tec.data_nascimento between ? and ?) "
                    + "  and (tec.sexo = ? or tec.sexo = ?)"
                    + "  and (upper(tec.nacionalidade) like ?)"
                    + "order by tec.nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            if (restringirNome)
                ps.setString(1, "%" + nome.toUpperCase() + "%");
            else
                ps.setString(1, "%");
            
            if (restringirDataNascimento) {
                ps.setDate(2, new java.sql.Date(dataInicio.getTime()));
                ps.setDate(3, new java.sql.Date(dataFim.getTime()));
            } else {
                ps.setDate(2, new java.sql.Date(new GregorianCalendar(1900, 0, 1).getTime().getTime()));
                ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            }
            
            if (restringirSexo) {
                ps.setString(4, Character.toString(sexo));
                ps.setString(5, Character.toString(sexo));
            } else {
                ps.setString(4, "M");
                ps.setString(5, "F");
            }
            
            if (restringirNacionalidade)
                ps.setString(6, "%" + nacionalidade.toUpperCase() + "%");
            else
                ps.setString(6, "%");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Tecnico> colecao = new ArrayList<>();
            rs.beforeFirst();
            while (rs.next()) {
                int idTecnico = rs.getInt(1);
                String nomeTecnico = rs.getString(2);
                java.util.Date dataNascimento = rs.getDate(3);
                sexo = rs.getString(4).charAt(0);
                nacionalidade = rs.getString(5);
                int idTime = rs.getInt(6);
                String nomeTime = rs.getString(7);
                String nomeAbreviado = rs.getString(8);
                String sedePais = rs.getString(9);
                String sedeEstado = rs.getString(10);
                String sedeCidade = rs.getString(11);
                
                TimeFutebol t = new TimeFutebol(idTime, nomeTime, nomeAbreviado, 
                        sedePais, sedeEstado, sedeCidade);
                Tecnico tec = new Tecnico(idTecnico, nomeTecnico, dataNascimento, 
                        sexo, nacionalidade, t);
                colecao.add(tec);
            }
            
            return colecao;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter registros de técnicos.\n" + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
