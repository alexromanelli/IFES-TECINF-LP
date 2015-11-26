/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneiofutebol.visao;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import torneiofutebol.modelo.Pessoa;

/**
 *
 * @author alexandre
 */
public class ModeloTabelaPessoa extends AbstractTableModel {

    private ArrayList<Pessoa> colecao;
    private static String[] colunas = { "nome", "data nasc.", "sexo", "nacionalidade" };
    
    public ModeloTabelaPessoa() {
        colecao = new ArrayList<>();
    }
    
    public ArrayList<Pessoa> getColecao() {
        return colecao;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    
    @Override
    public int getRowCount() {
        return colecao.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pessoa p = colecao.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getDataNascimento();
            case 2: return p.getSexo();
            case 3: return p.getNacionalidade();
            default: return null;
        }
    }
    
}
