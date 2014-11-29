package fruitSandwich.gui.table;

import javax.swing.table.AbstractTableModel;

import fruitSandwich.util.FArrays;

/**
 * 第一列带复选框最后几列为带触发事件的控制列并可配置的表格模型
 * 
 * @author 杜佳恒 2014-9-26
 */
public class FTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1509504496762458414L;

	private String[] tableColumnName;// 表格列名

	private Object[][] tablePageValue;// 单元格的值
	boolean withCheckbox;// 第一列是否带复选框

	private String[] controlColumnName;

	public FTableModel(String[] columnName, Object[][] pageValue,
			String[] controlColumnName, boolean WITH_CHECKBOX,
			boolean controlCloumnMultiModel) {
		this.withCheckbox = WITH_CHECKBOX;
		this.controlColumnName = controlColumnName;
		this.tableColumnName = columnName;
		this.tablePageValue = pageValue;

		if (WITH_CHECKBOX) {
			String[] copyColumnName = FArrays.moveFillFirstBlank(
					this.tableColumnName, 1);
			this.tableColumnName = copyColumnName;
			this.tableColumnName[0] = "选择";

			Object[][] copyObjects = new Object[this.tablePageValue.length][];
			for (int i = 0; i < tablePageValue.length; i++) {
				copyObjects[i] = FArrays.moveFillFirstBlankObject(
						tablePageValue[i], 1);
				copyObjects[i][0] = false;
			}
			this.tablePageValue = copyObjects;
		}

		if (controlCloumnMultiModel) {
			String[] copyColumnName = FArrays.moveFillLastBlank(
					this.tableColumnName, 1);
			this.tableColumnName = copyColumnName;
			this.tableColumnName[this.tableColumnName.length - 1] = "选择";

			Object[][] copyObjects = new Object[tablePageValue.length][];
			for (int i = 0; i < tablePageValue.length; i++) {
				copyObjects[i] = FArrays.moveFillLastBlankObject(
						tablePageValue[i], 1);
			}
			this.tablePageValue = copyObjects;
		} else if (controlColumnName != null && controlColumnName.length > 0) {
			String[] copyColumnName = FArrays.moveFillLastBlank(
					this.tableColumnName, controlColumnName.length);
			for (int i = 0; i < controlColumnName.length; i++) {
				copyColumnName[copyColumnName.length - controlColumnName.length
						+ i] = controlColumnName[i];
			}
			this.tableColumnName = copyColumnName;

			Object[][] copyObjects = new Object[tablePageValue.length][];
			for (int i = 0; i < tablePageValue.length; i++) {
				copyObjects[i] = FArrays.moveFillLastBlankObject(
						tablePageValue[i], controlColumnName.length);
			}
			this.tablePageValue = copyObjects;
		}

	}

	@Override
	public int getRowCount() {
		return tablePageValue.length;
	}

	@Override
	public int getColumnCount() {
		return tableColumnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tablePageValue[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return this.tableColumnName[column];
	}

	@Override
	public Class<?> getColumnClass(int index) {
		if (tablePageValue.length == 0)
			return super.getClass();
		else {
			Object value = getValueAt(0, index);
			if (value != null)
				return value.getClass();
			else
				return super.getClass();
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (withCheckbox && column == 0) {
			return true;
		}
		if (controlColumnName != null
				&& column > tableColumnName.length - controlColumnName.length
						- 1) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		tablePageValue[arg1][arg2] = arg0;
	}
}
