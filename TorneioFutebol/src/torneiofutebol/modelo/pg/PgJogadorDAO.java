/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.modelo.pg;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import torneiofutebol.modelo.Jogador;
import torneiofutebol.modelo.JogadorDAO;
import torneiofutebol.modelo.PosicaoJogador;
import torneiofutebol.modelo.TimeFutebol;

/**
 *
 * @author alexromanelli
 */
public class PgJogadorDAO implements JogadorDAO {

    @Override
    public boolean inserirJogador(Jogador jogador) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into jogador "
                    + "(nome, data_nascimento, sexo, nacionalidade, "
                    + "posicao_preferencial, id_time) values "
                    + "(?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, jogador.getNome());
            ps.setDate(2, new Date(jogador.getDataNascimento().getTime())); // <-------- atenção para a conversão de java.util.Date para java.sql.Date
            ps.setString(3, Character.toString(jogador.getSexo()));
            ps.setString(4, jogador.getNacionalidade());
            ps.setInt(5, jogador.getPosicaoPreferencial().getIdPosicao());
            ps.setInt(6, jogador.getTime().getIdTime());

            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de jogador", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirJogador(Jogador jogador) {
        Connection con = PostgreSqlDAOFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("delete from jogador "
                    + "where id_jogador = ?");
            ps.setInt(1, jogador.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro de jogador.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean atualizarJogador(Jogador jogador) {
        Connection con = PostgreSqlDAOFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(
                    "update jogador "
                    + "set nome = ?, "
                    + "data_nascimento = ?, "
                    + "sexo = ?, "
                    + "nacionalidade = ?, "
                    + "posicao_preferencial = ?, "
                    + "id_time = ? "
                    + "where id_jogador = ?");
            ps.setString(1, jogador.getNome());
            ps.setDate(2, new Date(jogador.getDataNascimento().getTime()));
            ps.setString(3, Character.toString(jogador.getSexo()));
            ps.setString(4, jogador.getNacionalidade());
            ps.setInt(5, jogador.getPosicaoPreferencial().getIdPosicao());
            ps.setInt(6, jogador.getTime().getIdTime());
            ps.setInt(7, jogador.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar registro de jogador.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public ResultSet selecionarTodosJogadores() {
        Connection con = PostgreSqlDAOFactory.getConnection();
        try {
//            PreparedStatement ps = con.prepareStatement(
//                    "select j.id_jogador, j.nome, j.data_nascimento, j.sexo, j.nacionalidade,"
//                    + " p.id_posicao, p.nome, t.id_time, t.nome, t.nome_abreviado, "
//                    + " t.sede_pais, t.sede_estado, t.sede_cidade "
//                    + " from jogador j "
//                    + " join posicao_jogador p on (j.posicao_preferencial = p.id_posicao) "
//                    + " join time_futebol t on (j.id_time = t.id_time) "
//                    + "order by t.nome, j.nome",
//                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
            PreparedStatement ps = con.prepareStatement(
                    "select id_jogador, j.nome, data_nascimento, sexo, nacionalidade,"
                    + " posicao_preferencial, t.id_time, t.nome "
                    + " from jogador j"
                    + "      join time_futebol t on (j.id_time = t.id_time) "
                    + "order by t.nome, j.nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter registros de jogadores.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public Jogador encontrarJogador(int idJogador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Jogador> selecionarJogadoresPorCriterios(boolean restringirNome, String nome, boolean restringirDataNascimento, java.util.Date dataInicio, java.util.Date dataFim, boolean restringirSexo, char sexo, boolean restringirNacionalidade, String nacionalidade) {
        Connection con = PostgreSqlDAOFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(
                    "select j.id_jogador, j.nome, j.data_nascimento, j.sexo, j.nacionalidade,"
                    + " p.id_posicao, p.nome, t.id_time, t.nome, t.nome_abreviado, "
                    + " t.sede_pais, t.sede_estado, t.sede_cidade "
                    + " from jogador j "
                    + " join posicao_jogador p on (j.posicao_preferencial = p.id_posicao) "
                    + " join time_futebol t on (j.id_time = t.id_time) "
                    + "where (upper(j.nome) like ?) "
                    + "  and (j.data_nascimento between ? and ?) "
                    + "  and (j.sexo = ? or sexo = ?)"
                    + "  and (j.nacionalidade like ?)"
                    + "order by j.nome",
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
            
            ArrayList<Jogador> colecao = new ArrayList<>();
            rs.beforeFirst();
            while (rs.next()) {
                int idJogador = rs.getInt(1);
                String nomeJogador = rs.getString(2);
                java.util.Date dataNascimento = rs.getDate(3);
                sexo = rs.getString(4).charAt(0);
                nacionalidade = rs.getString(5);
                int idPosicao = rs.getInt(6);
                String nomePosicao = rs.getString(7);
                int idTime = rs.getInt(8);
                String nomeTime = rs.getString(9);
                String nomeAbreviado = rs.getString(10);
                String sedePais = rs.getString(11);
                String sedeEstado = rs.getString(12);
                String sedeCidade = rs.getString(13);
                
                PosicaoJogador p = new PosicaoJogador(idPosicao, nomePosicao);
                TimeFutebol t = new TimeFutebol(idTime, nomeTime, nomeAbreviado, 
                        sedePais, sedeEstado, sedeCidade);
                Jogador j = new Jogador(idJogador, nomeJogador, dataNascimento, 
                        sexo, nacionalidade, p, t);
                colecao.add(j);
            }
            
            return colecao;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter registros de jogadores.\n" + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
