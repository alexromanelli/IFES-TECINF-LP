/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing.modelo.pg;

import cadastrodealunosswing.modelo.EmpresaDAO;
import cadastrodealunosswing.modelo.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alexromanelli
 */
public class PgEmpresaDAO implements EmpresaDAO {

    @Override
    public boolean inserirEmpresa(Empresa empresa) {
        try {
            Connection con = PostgreSQLDAOFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into empresa "
                    + "(cnpj, inscricaoestadual, razaosocial, nomefantasia, "
                    + " simples, uf, municipio) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getInscricaoEstadual());
            ps.setString(3, empresa.getRazaoSocial());
            ps.setString(4, empresa.getNomeFantasia());
            ps.setString(5, Character.toString(empresa.getSimples()));
            ps.setString(6, empresa.getUf());
            ps.setString(7, empresa.getMunicipio());
            int result = ps.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                    "Erro ao inserir registro de empresa", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean excluirEmpresa(Empresa empresa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarEmpresa(Empresa empresa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet selecionarTodosEmpresas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empresa encontrarEmpresa(String cnpj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
