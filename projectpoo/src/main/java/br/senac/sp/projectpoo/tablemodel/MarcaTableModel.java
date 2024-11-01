package br.senac.sp.projectpoo.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senac.sp.projectpoo.modelo.Marca;

public class MarcaTableModel extends AbstractTableModel {
	private List<Marca> lista;

	public MarcaTableModel(List<Marca> marcas) {
		this.lista = marcas;
	}

	public void setList(List<Marca> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Marca m = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return m.getId();
		case 1:
			return m.getNome();
		default:
			return null;
		}
	}

}
