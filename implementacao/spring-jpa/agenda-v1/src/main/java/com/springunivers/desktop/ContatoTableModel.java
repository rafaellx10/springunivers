package com.springunivers.desktop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.springunivers.model.Contato;

public class ContatoTableModel extends AbstractTableModel {
	private static final int COL_ID = 0;
    private static final int COL_NOME = 1;
    private static final int COL_DDD = 3;
    private static final int COL_TELEFONE = 2;
    
    private List<Contato> linhas;
    private String[] colunas = new String[]{"Id", "Nome", "DDD", "Telefone"};
    public ContatoTableModel(List<Contato> contatos) {
        this.linhas = new ArrayList<>(contatos);
    }
 
    public int getRowCount() {
        return linhas.size();
    }
 
    public int getColumnCount() {
        return colunas.length;
    }
 
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
 
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Integer.class;
        }
        return String.class;
    }
 
    public boolean isCellEditable(int rowIndex, int
    columnIndex) {
        return false;
    }
 
    public Object getValueAt(int row, int column) {
 
        Contato m = linhas.get(row);
 
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_NOME) {
            return m.getNome();
        } else if (column == COL_TELEFONE) {
            return m.getNumero();
        } else if (column == COL_DDD) {
            return m.getDdd();
        }
        return "";
    }
 
    public void setValueAt(Object aValue, int row, 
    int column) {
        Contato u = linhas.get(row);
        if (column == COL_ID) {
            u.setId((Integer) aValue);
        } else if (column == COL_NOME) {
            u.setNome(aValue.toString());
        } else if (column == COL_DDD) {
            u.setDdd(Integer.valueOf(aValue.toString()));
        } else if (column == COL_TELEFONE) {
            u.setNumero(Long.valueOf(aValue.toString()));
        }
    }
 
    public Contato getContato(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
 
    public void addContato(Contato contato) {
        linhas.add(contato);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, 
        ultimoIndice);
 
    }
 
    public void updateContato(int indiceLinha, Contato marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, 
        indiceLinha);
 
    }
 
    public void removeContato(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, 
        indiceLinha);
 
    }

}
