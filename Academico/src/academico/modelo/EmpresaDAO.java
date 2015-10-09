/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodealunosswing.modelo;

import java.sql.ResultSet;

/**
 *
 * @author alexromanelli
 */
public interface EmpresaDAO { // Data Access Object para Empresa
    public boolean inserirEmpresa(Empresa empresa);
    public boolean excluirEmpresa(Empresa empresa);
    public boolean atualizarEmpresa(Empresa empresa);
    public ResultSet selecionarTodosEmpresas();
    public Empresa encontrarEmpresa(String cnpj);
}
