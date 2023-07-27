package dto;

import java.io.Serializable;

/**
 * PastListDtoクラス. <br>
 * PastListDtoは、データベースから取得したおみくじデータを保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class PastListDto implements Serializable {

	/** 作成日を表します */
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
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 作成日のセット
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 運勢の取得
	 */
	public String getUnsei() {
		return unsei;
	}

	/**
	 * 運勢のセット
	 */
	public void setUnsei(String unsei) {
		this.unsei = unsei;
	}

	/**
	 * 願い事の取得
	 */
	public String getNegaigoto() {
		return negaigoto;
	}

	/**
	 * 願い事のセット
	 */
	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	/**
	 * 商いの取得
	 */
	public String getAkinai() {
		return akinai;
	}

	/**
	 * 商いのセット
	 */
	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	/**
	 * 学問の取得
	 */
	public String getGakumon() {
		return gakumon;
	}

	/**
	 * 学問のセット
	 */
	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}
}
