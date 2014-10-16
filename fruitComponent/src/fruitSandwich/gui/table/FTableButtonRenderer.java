package fruitSandwich.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 按钮式表格渲染器
 * 
 * @author 杜佳恒 2014-9-22
 */
class FTableButtonRenderer extends AbstractCellEditor implements
		TableCellRenderer, TableCellEditor {

	private static final long serialVersionUID = 822551354146132367L;

	private JButton button;
	private FITableControlColumnClickEvent clickEvent;

	private int clickRow;

	public void setClickEvent(FITableControlColumnClickEvent clickEvent) {
		this.clickEvent = clickEvent;
	}

	/**
	 * 按钮式表单元格渲染器，参数text为显示的字样
	 * 
	 * @param text
	 */
	protected FTableButtonRenderer(String text) {
		super();

		button = new JButton(text);
		button.setVerticalAlignment(SwingConstants.CENTER);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setForeground(Color.blue);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setBackground(Color.YELLOW);
		button.setFont(new Font("Dialog", Font.PLAIN, 12));
		button.setSize(new Dimension(55, 15));

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickEvent.click(clickRow);
			}
		});

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return button;
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		this.clickRow = row;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

}
