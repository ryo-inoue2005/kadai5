package form;

import org.apache.struts.validator.ValidatorForm;

import omikuji.Omikuji;

/**
 * ResultFormクラス. <br>
 * ResultFormクラスは、おみくじ結果を保管します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultForm extends ValidatorForm {

	/** 誕生日を表します */
	private String birthday;
	/** おみくじを表します */
	private Omikuji omikuji;

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
	 * おみくじの取得
	 */
	public Omikuji getOmikuji() {
		return omikuji;
	}

	/**
	 * おみくじのセット
	 */
	public void setOmikuji(Omikuji omikuji) {
		this.omikuji = omikuji;
	}

	/**
	 * 誕生日の値をチェックします
	 * 
	 * @param mapping マッピング
	 * @param request リクエスト
	 * @return ActionErrors  アクションエラーオブジェクト
	 */
//	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//
//		ActionErrors errors = new ActionErrors();
//		
//		if(birthday.equals("")) {
//			errors.add("", new ActionMessage("errors.integer", "誕生日わん"));
//			return errors;
//		}
//
//		// 数値かどうかチェック
//		if (!birthday.matches("^[0-9]+$|-[0-9]+$")) {
//			errors.add("", new ActionMessage("errors.integer", "誕生日"));
//			return errors;
//		}
//
//		// 正しい形式かチェック
//		String fs = null;
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//		try {
//			fs = df.format(df.parse(birthday));
//		} catch (Exception e) {
//			errors.add("", new ActionMessage("errors.invalid", "誕生日"));
//			return errors;
//		}
//
//		// 存在する日付かチェック
//		if (!birthday.equals(fs)) {
//			errors.add("", new ActionMessage("errors.existsdate", "誕生日"));
//			return errors;
//		}
//
//		return errors;
//	}
}
