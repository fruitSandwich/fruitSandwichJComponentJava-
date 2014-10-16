package fruitSandwich.gui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FTable extends JTable implements FITableRefresh {

	private static final long serialVersionUID = -7244671344616243095L;
	private FTableDataGenerate dataGenerate;
	private FITableLastColumnClickEvent clickEvent;
	private String lastColumnValue = "详情";

	public static final int SIMPLE_UNEDITEABLE_MODEL = 0;// 不可编辑只展示的表格
	public static final int FIRSTCOLUMN_CHECKBOX_MODEL = 1;// 第一列提供选择复选框的表格
	public static final int LASTCOLUMN_ASSOCIATE_MODEL = 2;// 最后一列提供详情的表格
	public static final int BOTH_CHECKBOX_ASSOCIATE_MODEL = 3;// 第一列带复选框最后一列带详情链接

	private int TABLE_MODEL;// 表格模型代码
	FTableModel ftableModel;// 真实的表格模型

	/**
	 * 构造默认表格，行颜色相间、高度设置等
	 */
	public FTable(int tableModel) {
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

		this.TABLE_MODEL = tableModel;
	}

	/**
	 * 分页默认表格初始化
	 * 
	 * 指定数据源生成器和点击事件以及最后一列显示内容
	 * 
	 * @param dataGenerate
	 * @param clickEvent
	 * @param lastColumnValue
	 */
	public void initializeDataSourceAndEvent(FTableDataGenerate dataGenerate,
			FITableLastColumnClickEvent clickEvent, String lastColumnValue) {
		this.lastColumnValue = lastColumnValue;
		initializeDataSourceAndEvent(dataGenerate, clickEvent);
	}

	/**
	 * 分页默认表格初始化
	 * 
	 * 指定数据源生成器和点击事件，最后一列名字默认为"详情"
	 * 
	 * @param dataGenerate
	 * @param clickEvent
	 */
	public void initializeDataSourceAndEvent(FTableDataGenerate dataGenerate,
			FITableLastColumnClickEvent clickEvent) {
		this.dataGenerate = dataGenerate;
		this.clickEvent = clickEvent;
		refreshData(0, 10);
	}

	@Override
	public int refreshData(int firstResult, int needNum) {
		if (dataGenerate != null) {
			final Object[][] data = dataGenerate.generateTableData(firstResult,
					needNum);
			String[] columnName = dataGenerate.generateColumnName();
			int allNum = dataGenerate.generateAllNum();

			switch (TABLE_MODEL) {
			case FIRSTCOLUMN_CHECKBOX_MODEL:
				ftableModel = new FTableModel(columnName, data, true, false);
				break;
			case LASTCOLUMN_ASSOCIATE_MODEL:
				ftableModel = new FTableModel(columnName, data, false, true);
				break;
			case BOTH_CHECKBOX_ASSOCIATE_MODEL:
				ftableModel = new FTableModel(columnName, data, true, true);
				break;
			case SIMPLE_UNEDITEABLE_MODEL:
				ftableModel = new FTableModel(columnName, data, false, false);
				break;
			default:
				ftableModel = new FTableModel(columnName, data, false, false);
				break;
			}
			this.setModel(ftableModel);

			if (TABLE_MODEL == LASTCOLUMN_ASSOCIATE_MODEL
					|| TABLE_MODEL == BOTH_CHECKBOX_ASSOCIATE_MODEL) {
				renderLastColumn();
			}
			if (TABLE_MODEL == FIRSTCOLUMN_CHECKBOX_MODEL
					|| TABLE_MODEL == BOTH_CHECKBOX_ASSOCIATE_MODEL) {
				renderFirstColumn();
			}

			return allNum;
		}
		return 0;
	}

	/**
	 * 渲染最后一列为链接按钮样式
	 */
	private void renderLastColumn() {
		FTableButtonCellRenderer buttonCellRenderer = new FTableButtonCellRenderer(
				lastColumnValue);
		buttonCellRenderer.setClickEvent(clickEvent);
		this.getColumnModel().getColumn(ftableModel.getColumnCount() - 1)
				.setCellRenderer(buttonCellRenderer);
	}

	/**
	 * 为复选框做第一列渲染，设置单元格宽度
	 */
	private void renderFirstColumn() {
		this.getColumnModel().getColumn(0).setMaxWidth(30);
		this.getColumnModel().getColumn(0).setMinWidth(20);
	}

}
