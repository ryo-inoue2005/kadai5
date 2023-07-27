package form;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * ResultFormクラス. <br>
 * ResultFormクラスは、運勢の割合を格納するマップを保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class StatsForm extends ActionForm {

	/** 運勢の割合を格納するマップ */
	private Map<String, Double> statsMap = new LinkedHashMap<>();

	/**
	 * マップの取得
	 */
	public Map<String, Double> getStatsMap() {
		return statsMap;
	}

	/**
	 * マップのセット
	 */
	public void setStatsMap(Map<String, Double> statsMap) {
		this.statsMap = statsMap;
	}
}
