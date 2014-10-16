package fruitSandwich.gui.table;

/**
 * 表格数据生成器
 * 
 * @author 杜佳恒 2014-9-22
 */
public interface FTableDataGenerate {

	/**
	 * 生成表格数据
	 * 
	 * @param firstResult
	 *            第一条记录下标
	 * @param needNum
	 *            需要记录条数
	 * @return
	 */
	public Object[][] generateTableData(int firstResult, int needNum);

	public int generateAllNum();

	/**
	 * 生成表头数据
	 * 
	 * @return
	 */
	public String[] generateColumnName();
}
