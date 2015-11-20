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
import javax.swing.JOptionPane;
import torneiofutebol.modelo.Jogador;
import torneiofutebol.modelo.JogadorDAO;

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
            ps.setDate(2, (Date)jogador.getDataNascimento());
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
            PreparedStatement ps = con.prepareStatement(
                    "select j.id_jogador, j.nome, j.data_nascimento, j.sexo, j.nacionalidade,"
                    + " p.id_posicao, p.nome, t.id_time, t.nome, t.nome_abreviado, "
                    + " t.sede_pais, t.sede_estado, t.sede_cidade "
                    + " from jogador j "
                    + " join posicao_jogador p on (j.posicao_preferencial = p.id_posicao) "
                    + " join time_futebol t on (j.id_time = t.id_time) "
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
    
}
