/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.visao;

import academico.modelo.Curso;
import academico.modelo.CursoDAO;
import academico.modelo.pg.PostgreSQLDAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alexromanelli
 */
public class ModeloTabelaCurso extends AbstractTableModel {

    private ArrayList<Curso> colecaoCurso;
    private static final String[] colunas = {
        "Código", "Nome", "Carga horária", "Coordenador"
    };
    
    public ModeloTabelaCurso() {
        colecaoCurso = new ArrayList<>();
        carregarDoBD();
    }
    
    private void carregarDoBD() {
        try {
            CursoDAO cursoDAO = PostgreSQLDAOFactory.getCursoDAO();
            ResultSet rs = cursoDAO.selecionarTodosCursos();
            rs.beforeFirst();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                String nome = rs.getString(2);
                int cargaHoraria = rs.getInt(3);
                String coordenador = rs.getString(4);
                Curso curso = new Curso(codigo, nome, cargaHoraria, coordenador);
                colecaoCurso.add(curso);
            }
        } catch (SQLException e) {
            
        }
    }
    
    @Override
    public int getRowCount() {
        return colecaoCurso.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = colecaoCurso.get(rowIndex);
        switch (columnIndex) {
            case 0: return curso.getCodCurso();
            case 1: return curso.getNome();
            case 2: return curso.getCargaHoraria();
            case 3: return curso.getCoordenador();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}
