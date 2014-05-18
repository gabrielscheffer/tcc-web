/**
 * 
 */
package net.unesc.tcc.gabriel.model;

/**
 * @author Gabriel
 *
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.DataModelListener;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class BemDataModel extends ListDataModel<Bem> implements
		SelectableDataModel<Bem>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1539119016014591189L;

	public BemDataModel() {
	}

	public BemDataModel(List<Bem> data) {
		super(data);
	}

	// @Override
	// public int getRowCount() {
	// // TODO Auto-generated method stub
	// return super.getRowCount();
	// }
	//
	// @Override
	// public int getRowIndex() {
	// // TODO Auto-generated method stub
	// return super.getRowIndex();
	// }
	//
	// @Override
	// public Object getWrappedData() {
	// // TODO Auto-generated method stub
	// return super.getWrappedData();
	// }
	//
	// @Override
	// public boolean isRowAvailable() {
	// // TODO Auto-generated method stub
	// return super.isRowAvailable();
	// }
	//
	// @Override
	// public void setRowIndex(int arg0) {
	// // TODO Auto-generated method stub
	// super.setRowIndex(arg0);
	// }
	//
	// @Override
	// public void setWrappedData(Object data) {
	// // TODO Auto-generated method stub
	// super.setWrappedData(data);
	// }
	//
	// @Override
	// public void addDataModelListener(DataModelListener listener) {
	// // TODO Auto-generated method stub
	// super.addDataModelListener(listener);
	// }
	//
	// @Override
	// public DataModelListener[] getDataModelListeners() {
	// // TODO Auto-generated method stub
	// return super.getDataModelListeners();
	// }
	//
	// @Override
	// public Iterator<Bem> iterator() {
	// // TODO Auto-generated method stub
	// return super.iterator();
	// }
	//
	// @Override
	// public void removeDataModelListener(DataModelListener listener) {
	// // TODO Auto-generated method stub
	// super.removeDataModelListener(listener);
	// }

	@Override
	public Bem getRowData(String rowKey) {
		// In a real app, a more efficient way like a query by rowKey should be
		// implemented to deal with huge data

		List<Bem> bens = (List<Bem>) getWrappedData();

		for (Bem bem : bens) {
			if (bem.getCdBemId().equals(rowKey))
				return bem;
		}

		return null;
	}

	@Override
	public Object getRowKey(Bem bem) {
		return bem.getCdBemId();
	}
}
