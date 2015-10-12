/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academico.visao;

import academico.modelo.Curso;
import academico.modelo.Disciplina;
import academico.modelo.DisciplinaDAO;
import academico.modelo.pg.PostgreSQLDAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alexromanelli
 */
public class ModeloTabelaDisciplina extends AbstractTableModel {
    
    private ArrayList<Disciplina> colecaoDisciplinas;
    private String[] colunas = {
        "Cód.disc.", "Nome disciplina", "CH", "Curso"
    };
    
    public ModeloTabelaDisciplina() {
        colecaoDisciplinas = new ArrayList<>();
        carregarDoBD();
    }
    
    private void carregarDoBD() {
        try {
            DisciplinaDAO disciplinaDAO = PostgreSQLDAOFactory.getDisciplinaDAO();
            ResultSet rs = disciplinaDAO.selecionarTodosDisciplinas();
            rs.beforeFirst();
            while (rs.next()) {
                // obter os dados dos campos da disciplina (também dados do curso)
                int codDisciplina = rs.getInt(1);
                String nomeDisciplina = rs.getString(2);
                int cargaHorariaDisciplina = rs.getInt(3);
                String ementa = rs.getString(4);
                int codCurso = rs.getInt(5);
                String nomeCurso = rs.getString(6);
                int cargaHorariaCurso = rs.getInt(7);
                String nomeCoordenador = rs.getString(8);
                // criar um objeto de disciplina
                Curso c = new Curso(codCurso, nomeCurso, cargaHorariaCurso,
                        nomeCoordenador);
                Disciplina d = new Disciplina(codDisciplina, nomeDisciplina, 
                        cargaHorariaDisciplina, ementa, c);
                // inserir o objeto na coleção de disciplinas
                colecaoDisciplinas.add(d);
            }
        } catch (SQLException e) {
            
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return colecaoDisciplinas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Disciplina d = colecaoDisciplinas.get(rowIndex);
        switch (columnIndex) {
            case 0: return d.getCodDisciplina();
            case 1: return d.getNome();
            case 2: return d.getCargaHoraria();
            case 3: return d.getCurso().getNome();
            default: return null;
        }
    }
    
}
