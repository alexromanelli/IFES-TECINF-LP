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
import javax.swing.JOptionPane;
import torneiofutebol.modelo.PosicaoJogador;
import torneiofutebol.modelo.PosicaoJogadorDAO;

/**
 *
 * @author alexandre
 */
public class PgPosicaoJogadorDAO implements PosicaoJogadorDAO {

    @Override
    public boolean inserirPosicaoJogador(PosicaoJogador posicaoJogador) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "insert into posicao_jogador (nome) values (?)");
            ps.setString(1, posicaoJogador.getNome());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de posição de jogador.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean atualizarPosicaoJogador(PosicaoJogador posicaoJogador) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "update jogador set nome = ? where id_posicao = ?");
            ps.setString(1, posicaoJogador.getNome());
            ps.setInt(2, posicaoJogador.getIdPosicao());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao atualizar registro de posição de jogador.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirPosicaoJogador(PosicaoJogador posicaoJogador) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "delete from posicao_jogador where id_posicao = ?");
            ps.setInt(1, posicaoJogador.getIdPosicao());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao excluir registro de posição de jogador.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public ResultSet selecionarTodasPosicoesJogador() {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select id_posicao, nome from posicao_jogador "
                            + "order by nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao selecionar registros de posições de jogador.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public PosicaoJogador encontrarPosicaoJogador(int id) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select nome from posicao_jogador "
                            + "where id_posicao = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString(1);
                PosicaoJogador posicao = new PosicaoJogador(id, nome);
                return posicao;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao selecionar registro de posição de jogador.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
