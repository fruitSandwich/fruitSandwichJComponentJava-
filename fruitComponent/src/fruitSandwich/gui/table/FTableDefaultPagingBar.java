package fruitSandwich.gui.table;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;

/**
 * 表格分页条
 * 
 * @author 杜佳恒 2014-9-22
 */
public class FTableDefaultPagingBar extends JPanel {
	private static final long serialVersionUID = 2880074751287929452L;

	private JButton button_first;
	private JButton button_pre;
	private JButton button_next;
	private JButton button_last;
	private JButton button_go;
	private JComboBox<Object> comboBox_pageNum;
	private JLabel label_pageShow;// {{第1/2页}}
	private JLabel label_allNum; // 共123435条
	JFormattedTextField formattedTextField;

	private JLabel label_pageNum;
	private JLabel label_go;

	FTablePagingEvent pagingEvent;

	/**
	 * 初始化时传入表格刷新事件一般为实现了刷新接口的表格类，以及初始化刷新逻辑类并绑定界面中的各类事件
	 * 
	 * @param refresh
	 */
	public FTableDefaultPagingBar(FITableRefresh refresh) {
		initializeComponent();
		setLayout(new GridLayout(0, 11, 0, 0));
		add(button_first);
		add(button_pre);
		add(label_pageShow);
		add(button_next);
		add(button_last);
		add(label_pageNum);
		add(comboBox_pageNum);
		add(label_allNum);
		add(label_go);
		add(formattedTextField);
		add(button_go);

		// 刷新逻辑
		pagingEvent = new FTablePagingEvent(refresh, button_first, button_pre,
				button_next, button_last, button_go, comboBox_pageNum,
				label_pageShow, label_allNum, formattedTextField);

		button_first.addActionListener(pagingEvent);
		button_pre.addActionListener(pagingEvent);
		button_next.addActionListener(pagingEvent);
		button_last.addActionListener(pagingEvent);
		comboBox_pageNum.addActionListener(pagingEvent);
		button_go.addActionListener(pagingEvent);

		pagingEvent.refreshAndUpdatePagingBar();
	}

	/**
	 * 每页显示多少条
	 * 
	 * @author 杜佳恒 2014-9-22
	 */
	public enum PageSize {
		ten(10), twenty(20), thirty(30), fifity(50), one_hundred(100), two_hundred(
				200), five_hundred(500);

		private int num;

		PageSize(int num) {
			this.num = num;
		}

		public int getNum() {
			return num;
		}

		@Override
		public String toString() {
			return num + "条";
		}
	}

	/**
	 * 初始化界面中的组件
	 */
	private void initializeComponent() {
		button_first = new JButton("<<");
		button_first.setMargin(new java.awt.Insets(0, 0, 0, 0));

		button_pre = new JButton("<");
		button_pre.setMargin(new java.awt.Insets(0, 0, 0, 0));

		label_pageShow = new JLabel("\u7B2C{1/1}\u9875");
		label_pageShow.setHorizontalAlignment(SwingConstants.CENTER);
		label_pageShow.setForeground(Color.BLUE);
		label_pageShow.setFont(new Font("宋体", Font.PLAIN, 12));

		button_next = new JButton(">");
		button_next.setMargin(new java.awt.Insets(0, 0, 0, 0));

		button_last = new JButton(">>");
		button_last.setMargin(new java.awt.Insets(0, 0, 0, 0));

		label_pageNum = new JLabel("\u6BCF\u9875:");
		label_pageNum.setHorizontalAlignment(SwingConstants.CENTER);
		label_pageNum.setForeground(Color.BLUE);
		label_pageNum.setFont(new Font("宋体", Font.PLAIN, 12));

		comboBox_pageNum = new JComboBox<Object>();
		comboBox_pageNum.setModel(new DefaultComboBoxModel<Object>(PageSize
				.values()));
		comboBox_pageNum.setSelectedIndex(0);
		comboBox_pageNum.setForeground(new Color(0, 51, 204));
		comboBox_pageNum.setFont(new Font("宋体", Font.PLAIN, 12));
		comboBox_pageNum.setBackground(new Color(255, 255, 204));

		label_allNum = new JLabel("\u51710\u6761");
		label_allNum.setHorizontalAlignment(SwingConstants.CENTER);
		label_allNum.setForeground(Color.BLUE);
		label_allNum.setFont(new Font("宋体", Font.PLAIN, 12));

		label_go = new JLabel("\u8F6C\u5230\uFF1A");
		label_go.setHorizontalAlignment(SwingConstants.CENTER);
		label_go.setForeground(Color.BLUE);

		formattedTextField = new JFormattedTextField(NumberFormat.getInstance());
		formattedTextField.setFocusLostBehavior(JFormattedTextField.COMMIT);
		formattedTextField.setColumns(10);

		button_go = new JButton("go");
		button_go.setMargin(new java.awt.Insets(0, 0, 0, 0));
		button_go.setForeground(Color.BLUE);

	}
}
