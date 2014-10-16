package fruitSandwich.gui.table;

import javax.swing.table.AbstractTableModel;

/**
 * 不可编辑表模型
 * 
 * @author 杜佳恒 2014-9-22
 */
class FTableUnEditableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4543351257210420812L;

	private String[] columnName;

	private Object[][] pageValue;

	protected FTableUnEditableModel(String[] columnName, Object[][] pageValue) {
		this.columnName = columnName;
		this.pageValue = pageValue;
	}

	@Override
	public int getRowCount() {
		return pageValue.length;
	}

	@Override
	public int getColumnCount() {
		return pageValue[0].length;
	}

	@Override
	public String getColumnName(int i) {
		return columnName[i];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return pageValue[rowIndex][columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int index) {
		Object value = getValueAt(0, index);
		if (value != null)
			return value.getClass();
		else
			return super.getClass();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		pageValue[arg1][arg2] = arg0;
	}
}
