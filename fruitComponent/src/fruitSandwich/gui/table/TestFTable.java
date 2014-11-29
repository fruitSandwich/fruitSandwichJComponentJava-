package fruitSandwich.gui.table;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class TestFTable {

	private JFrame frame;
	JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFTable window = new TestFTable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestFTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		FTableDataGenerate dataGenerate = new FTableDataGenerate() {

			@Override
			public Object[][] generateTableData(int firstResult, int needNum) {
				int allNum = generateAllNum();
				String[] names = generateColumnName();
				Object object[][];
				if (allNum > firstResult + needNum)
					object = new Object[needNum][names.length];
				else
					object = new Object[allNum - firstResult][names.length];
				for (int i = 0; i < object.length; i++) {
					for (int j = 0; j < names.length; j++) {
						object[i][j] = new Random().nextInt(100);// (firstResult
					}
				}
				return object;
			}

			@Override
			public String[] generateColumnName() {
				String[] columnName = new String[4];
				for (int i = 0; i < columnName.length; i++) {
					columnName[i] = i + "列";
				}
				return columnName;
			}

			@Override
			public int generateAllNum() {
				return 50;
			}
		};

		final FTable simpleTable = new FTable(FTable.SIMPLE_UNEDITEABLE_MODEL,
				true);

		List<FITableControlColumnClickEvent> clickEvents = new ArrayList<FITableControlColumnClickEvent>();
		clickEvents.add(new FITableControlColumnClickEvent() {

			@Override
			public void click(int row) {
				JOptionPane.showMessageDialog(frame, "详情" + row + "行");
			}

			@Override
			public String getCloumnName() {
				return "详情";
			}
		});
		clickEvents.add(new FITableControlColumnClickEvent() {

			@Override
			public void click(int row) {
				JOptionPane.showMessageDialog(button, "删除" + row + "行");
			}

			@Override
			public String getCloumnName() {
				return "删除";
			}
		});

		simpleTable.initializeDataSource(dataGenerate);

		simpleTable.initializeDataSourceAndEventAndControlColumnNames(
				dataGenerate, clickEvents, new String[] { "详情", "删除" });

		scrollPane.setViewportView(simpleTable);

		FTableDefaultPagingBar tablePagingBar = new FTableDefaultPagingBar(
				simpleTable);
		frame.getContentPane().add(tablePagingBar, BorderLayout.SOUTH);

		JButton button = new JButton("fresh");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				simpleTable.refreshData(0, 10);
			}
		});
		frame.getContentPane().add(button, BorderLayout.NORTH);

	}
}
