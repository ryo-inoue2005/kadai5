package form;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * ResultFormクラス. <br>
 * ResultFormクラスは、おみくじ結果を保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultForm extends ActionForm {

	/** 誕生日を表します */
	private String birthday;
	/** 運勢表示を表します */
	private String disp;
	/** 願い事を表します */
	private String negaigoto;
	/** 商いを表します */
	private String akinai;
	/** 学問を表します */
	private String gakumon;

	/**
	 * 誕生日の取得
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 誕生日のセット
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * 運勢表示文字列の取得
	 */
	public String getDisp() {
		return disp;
	}

	/**
	 * 運勢表示文字列のセット
	 */
	public void setDisp(String disp) {
		this.disp = disp;
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

	/**
	 * 誕生日の値をチェックします
	 * 
	 * @param mapping マッピング
	 * @param request リクエスト
	 * @return ActionErrors  アクションエラーオブジェクト
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		// 数値かどうかチェック
		if (!birthday.matches("^[0-9]+$|-[0-9]+$")) {
			errors.add("", new ActionMessage("errors.integer", "誕生日"));
			return errors;
		}

		// 正しい形式かチェック
		String fs = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			fs = df.format(df.parse(birthday));
		} catch (Exception e) {
			errors.add("", new ActionMessage("errors.invalid", "誕生日"));
			return errors;
		}

		// 存在する日付かチェック
		if (!birthday.equals(fs)) {
			errors.add("", new ActionMessage("errors.existsdate", "誕生日"));
			return errors;
		}

		return errors;
	}
}
