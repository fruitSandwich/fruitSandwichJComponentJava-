package fruitSandwich.gui.table;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import fruitSandwich.gui.table.FITableLastColumnClickEvent;
import fruitSandwich.gui.table.FTable;
import fruitSandwich.gui.table.FTableDataGenerate;
import fruitSandwich.gui.table.FTableDefaultPagingBar;
import javax.swing.JScrollPane;

public class TestFTable {

	private JFrame frame;

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
						object[i][j] = (firstResult + i) + "#" + j;
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

		final FTable simpleTable = new FTable(
				FTable.BOTH_CHECKBOX_ASSOCIATE_MODEL);
		simpleTable.initializeDataSourceAndEvent(dataGenerate,
				new FITableLastColumnClickEvent() {

					@Override
					public void click(int row) {
						String value = simpleTable.getModel()
								.getValueAt(row, 1).toString();
						System.out.println(value);
					}
				});

		scrollPane.setViewportView(simpleTable);

		FTableDefaultPagingBar tablePagingBar = new FTableDefaultPagingBar(
				simpleTable);
		frame.getContentPane().add(tablePagingBar, BorderLayout.SOUTH);

	}

}
