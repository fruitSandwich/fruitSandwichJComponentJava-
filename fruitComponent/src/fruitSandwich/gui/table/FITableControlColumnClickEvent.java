package fruitSandwich.gui.table;

/**
 * GUI中点击事件触发事件接口
 * 
 * @author 杜佳恒 2014-9-22
 */
public interface FITableControlColumnClickEvent {
	/**
	 * 点击事件触发事件
	 */
	public String click(int row);

	public String getCloumnName();
}
