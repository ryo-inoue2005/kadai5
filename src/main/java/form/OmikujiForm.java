package form;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * OmikujiFormクラス. <br>
 * OmikujiFormクラスは、誕生日の値を格納します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class OmikujiForm extends ActionForm {

	/** 誕生日を表します */
	private String birthday;

	/**
	 * 誕生日の取得
	 * 
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 誕生日のセット
	 * 
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
