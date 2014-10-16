package fruitSandwich.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FTable extends JTable implements FITableRefresh {

	private static final long serialVersionUID = -7244671344616243095L;
	private FTableDataGenerate dataGenerate;
	private List<FITableControlColumnClickEvent> clickEventList;

	// private String lastColumnValue = "详情";

	public static final int SIMPLE_UNEDITEABLE_MODEL = 0;// 前后列都不提供功能，只显示数据用的表格
	public static final int FIRSTCOLUMN_CHECKBOX_MODEL = 1;// 第一列提供选择复选框的表格

	private int tableShowModel = 0;// 是否带复选框,默认不带
	FTableModel ftableModel;// 真实的表格模型

	/**
	 * 
	 * @param dataGenerate
	 *            数据源
	 * @param controlColumnClickEvent
	 *            控制列事件以及列名
	 * @param CHECKBOX
	 *            第一列是否带复选框
	 */
	public FTable(int CHECKBOX) {
		this.setRowHeight(30);
		this.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer dtr = new DefaultTableCellRenderer() {

			private static final long serialVersionUID = -3927034491176734319L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean ishasFocus,
					int row, int column) {
				if (row % 2 == 0) {
					setBackground(new Color(227, 227, 227));
				} else {
					setBackground(Color.WHITE);
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, ishasFocus, row, column);
			}
		};
		dtr.setHorizontalAlignment(JLabel.CENTER);
		this.setDefaultRenderer(String.class, dtr);
		this.setDefaultRenderer(int.class, dtr);
		this.setDefaultRenderer(Integer.class, dtr);

		this.tableShowModel = CHECKBOX;

	}

	/**
	 * 初始化表格数据源以及表格控制列
	 * 
	 * @param dataGenerate
	 *            表格数据源
	 * @param controlColumnClickEvent
	 *            表格控制列事件
	 */
	public void initializeDataSourceAndEvent(FTableDataGenerate dataGenerate,
			List<FITableControlColumnClickEvent> controlColumnClickEvent) {
		this.dataGenerate = dataGenerate;
		this.clickEventList = controlColumnClickEvent;
		refreshData(0, 10);
	}

	@Override
	public int refreshData(int firstResult, int needNum) {
		if (dataGenerate != null) {
			final Object[][] data = dataGenerate.generateTableData(firstResult,
					needNum);
			String[] columnName = dataGenerate.generateColumnName();
			int allNum = dataGenerate.generateAllNum();

			switch (tableShowModel) {
			case 0:
				ftableModel = new FTableModel(columnName, data, null, false);
				break;
			case 1:
				ftableModel = new FTableModel(columnName, data, null, true);
				break;
			default:
				ftableModel = new FTableModel(columnName, data, null, false);
				break;
			}
			this.setModel(ftableModel);

			if (tableShowModel == FIRSTCOLUMN_CHECKBOX_MODEL) {
				renderFirstColumn();
			}
			renderControlColumn();
			return allNum;
		}
		return 0;
	}

	/**
	 * 渲染最后一列为链接按钮样式
	 */
	private void renderControlColumn() {
		for (int i = 0; i < clickEventList.size(); i++) {
			FITableControlColumnClickEvent tableControlColumnClickEvent = clickEventList
					.get(i);
			FTableButtonCellRenderer buttonCellRenderer = new FTableButtonCellRenderer(
					tableControlColumnClickEvent.getCloumnName());
			buttonCellRenderer.setClickEvent(tableControlColumnClickEvent);
			this.getColumnModel()
					.getColumn(
							ftableModel.getColumnCount()
									- clickEventList.size() + i)
					.setCellRenderer(buttonCellRenderer);
		}
	}

	/**
	 * 为复选框做第一列渲染，设置单元格宽度
	 */
	private void renderFirstColumn() {
		this.getColumnModel().getColumn(0).setMaxWidth(30);
		this.getColumnModel().getColumn(0).setMinWidth(20);
	}

}
