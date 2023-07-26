package bean;

/**
 * OmikujiBeanクラス. <br>
 * OmikujiBeanは、おみくじデータを保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiBean {

	/** 作成日を表ます */
	private String createDate;
	/** 運勢を表します */
	private String unsei;
	/** 願い事を表します */
	private String negaigoto;
	/** 商いを表します */
	private String akinai;
	/** 学問を表します */
	private String gakumon;

	/**
	 * 作成日の取得
	 * 
	 * @return createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 作成日のセット
	 * 
	 * @param createDate
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 運勢の取得
	 * 
	 * @return unsei
	 */
	public String getUnsei() {
		return unsei;
	}

	/**
	 * 運勢のセット
	 * 
	 * @param unsei
	 */
	public void setUnsei(String unsei) {
		this.unsei = unsei;
	}

	/**
	 * 願い事の取得
	 * 
	 * @return negaigoto
	 */
	public String getNegaigoto() {
		return negaigoto;
	}

	/**
	 * 願い事のセット
	 * 
	 * @param negaigoto
	 */
	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	/**
	 * 商いの取得
	 * 
	 * @return akinai
	 */
	public String getAkinai() {
		return akinai;
	}

	/**
	 * 商いのセット
	 * 
	 * @param akinai
	 */
	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	/**
	 * 学問の取得
	 * 
	 * @return gakumon
	 */
	public String getGakumon() {
		return gakumon;
	}

	/**
	 * 学問のセット
	 * 
	 * @param gakumon
	 */
	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}
}
