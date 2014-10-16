package fruitSandwich.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import java.awt.BorderLayout;

/**
 * 按钮式表格渲染器
 * 
 * @author 杜佳恒 2014-9-22
 */
class FTableButtonCellRenderer extends JPanel implements TableCellRenderer {

	private static final long serialVersionUID = 822551354146132367L;

	private JButton button;
	private FITableLastColumnClickEvent clickEvent;

	public void setClickEvent(FITableLastColumnClickEvent clickEvent) {
		this.clickEvent = clickEvent;
	}

	/**
	 * 按钮式表单元格渲染器，参数text为显示的字样
	 * 
	 * @param text
	 */
	protected FTableButtonCellRenderer(String text) {
		super();
		setLayout(new BorderLayout(0, 0));

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
		add(button);

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (isSelected && hasFocus) {
			clickEvent.click(row);

			// System.out.println(new Random().nextInt());
			// System.out.println("value:" + value);
			// System.out.println("isSelected:" + isSelected);
			// System.out.println("hasFocus:" + hasFocus);
			// System.out.println("row:" + row);
			// System.out.println("column:" + column);
		}

		return this;
	}

}
