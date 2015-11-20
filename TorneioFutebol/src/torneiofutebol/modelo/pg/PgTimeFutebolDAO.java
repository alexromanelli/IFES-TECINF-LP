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
import torneiofutebol.modelo.TimeFutebol;
import torneiofutebol.modelo.TimeFutebolDAO;

/**
 *
 * @author alexandre
 */
public class PgTimeFutebolDAO implements TimeFutebolDAO {

    @Override
    public boolean inserirTimeFutebol(TimeFutebol time) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(""
                    + "insert into time_futebol (nome, nome_abreviado, "
                    + " sede_pais, sede_estado, sede_cidade) "
                    + " values (?, ?, ?, ?, ?)");
            ps.setString(1, time.getNome());
            ps.setString(2, time.getNomeAbreviado());
            ps.setString(3, time.getSedePais());
            ps.setString(4, time.getSedeEstado());
            ps.setString(5, time.getSedeCidade());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de time", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirTimeFutebol(TimeFutebol time) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from "
                    + "time_futebol where id_time = ?");
            ps.setInt(1, time.getIdTime());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao excluir registro de time", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean atualizarTimeFutebol(TimeFutebol time) {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("update time_futebol "
                    + "set nome = ?, nome_abreviado = ?, sede_pais = ?, "
                    + "    sede_estado = ?, sede_cidade = ? "
                    + "where id_time = ?");
            ps.setString(1, time.getNome());
            ps.setString(2, time.getNomeAbreviado());
            ps.setString(3, time.getSedePais());
            ps.setString(4, time.getSedeEstado());
            ps.setString(5, time.getSedeCidade());
            ps.setInt(6, time.getIdTime());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao atualizar registro de time", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public ResultSet selecionarTodosTimesFutebol() {
        try {
            Connection con = PostgreSqlDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("select id_time, "
                    + " nome, nome_abreviado, sede_pais, sede_estado, "
                    + " sede_cidade from time_futebol order by nome",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao selecionar registros de times", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public TimeFutebol encontrarTimeFutebol(int idTimeFutebol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
