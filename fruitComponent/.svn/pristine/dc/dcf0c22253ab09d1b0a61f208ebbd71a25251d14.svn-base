package fruitSandwich.gui.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import fruitSandwich.gui.table.FTableDefaultPagingBar.PageSize;

/**
 * 表格分页触发事件类
 * 
 * @author 杜佳恒 2014-9-22
 */
public class FTablePagingEvent implements ActionListener {

	private FITableRefresh refresh;
	private JButton button_first;
	private JButton button_pre;
	private JButton button_next;
	private JButton button_last;
	private JButton button_go;
	private JComboBox<Object> comboBox_pageNum;
	private JLabel label_pageShow;// {{第1/2页}}
	private JLabel label_allNum; // 共123435条
	JFormattedTextField formattedTextField;

	/**
	 * 必须把所有分页相关的控件传入才能控制
	 * @param refresh
	 * @param button_first
	 * @param button_pre
	 * @param button_next
	 * @param button_last
	 * @param button_go
	 * @param comboBox_pageNum
	 * @param label_pageShow
	 * @param label_allNum
	 * @param formattedTextField
	 */
	public FTablePagingEvent(FITableRefresh refresh, JButton button_first,
			JButton button_pre, JButton button_next, JButton button_last,
			JButton button_go, JComboBox<Object> comboBox_pageNum,
			JLabel label_pageShow, JLabel label_allNum,
			JFormattedTextField formattedTextField) {
		super();
		this.refresh = refresh;
		this.button_first = button_first;
		this.button_pre = button_pre;
		this.button_next = button_next;
		this.button_last = button_last;
		this.button_go = button_go;
		this.comboBox_pageNum = comboBox_pageNum;
		this.label_pageShow = label_pageShow;
		this.label_allNum = label_allNum;
		this.formattedTextField = formattedTextField;
	}

	int currentPage = 1;
	int currentPageFirstNum = 0;
	int allNum = 0;
	int allPage = 0;

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == button_first) {
			currentPage = 1;
			refreshAndUpdatePagingBar();
		}
		if (e.getSource() == button_pre) {
			if (currentPage > 1) {
				currentPage--;
				refreshAndUpdatePagingBar();
			}
		}
		if (e.getSource() == button_next) {
			if (currentPage < allPage) {
				currentPage++;
				refreshAndUpdatePagingBar();
			}
		}
		if (e.getSource() == button_last) {
			currentPage = allPage;
			refreshAndUpdatePagingBar();
		}
		if (e.getSource() == button_go) {
			try {
				currentPage = Integer.parseInt(formattedTextField.getText());
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(button_go, "页码必须为数字");
				return;
			}
			if (currentPage > allPage || currentPage < 0) {
				JOptionPane.showMessageDialog(button_go, "页码超出范围");
				return;
			}
			refreshAndUpdatePagingBar();
		}

		if (e.getSource() == comboBox_pageNum) {
			currentPage = currentPageFirstNum / getComboxSelectNum();
			if (currentPage == 0)
				currentPage = 1;
			refreshAndUpdatePagingBar();
		}
	}

	/**
	 * 获取每页显示多少条
	 * 
	 * @return
	 */
	private int getComboxSelectNum() {
		int selectNum = 0;
		selectNum = PageSize.values()[comboBox_pageNum.getSelectedIndex()]
				.getNum();
		return selectNum;
	}

	public void refreshAndUpdatePagingBar() {
		int pageSize = getComboxSelectNum();
		allNum = refresh.refreshData((currentPage - 1) * pageSize, pageSize);
		label_allNum.setText("共" + allNum + "条");
		allPage = allNum / pageSize;
		if (allNum % pageSize != 0) {
			allPage += 1;
		}
		label_pageShow.setText("第{" + currentPage + "/" + allPage + "}页");
	}
}
