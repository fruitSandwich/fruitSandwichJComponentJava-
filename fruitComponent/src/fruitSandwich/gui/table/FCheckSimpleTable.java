package fruitSandwich.gui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FCheckSimpleTable extends JTable implements FITableRefresh{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3739056702767674602L;
	
	
	FTableDataGenerate dataGenerate;
	FITableControlColumnClickEvent clickEvent;
	String lastColumnValue = "详情";

	/**
	 * 构造默认表格，行颜色相间、高度设置等
	 */
	public FCheckSimpleTable() {
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
			FITableControlColumnClickEvent clickEvent, String lastColumnValue) {
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
			FITableControlColumnClickEvent clickEvent) {
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
			FTableUnEditableModel unEditableModel = new FTableUnEditableModel(
					columnName, data);

			this.setModel(unEditableModel);

			return allNum;
		}
		return 0;
	}

}
