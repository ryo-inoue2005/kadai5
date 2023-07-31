package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * FormActionクラス. <br>
 * FormActionクラスは、誕生日に入力画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class FormAction extends Action {

	/**
	 * セッションを初期化し、誕生日入力画面に遷移します。
	 * 
	 * @throws ClassNotFoundException クラス未発見例外
	 * @throws SQLException SQL例外
	 * @param mapping マッピング
	 * @param form フォーム
	 * @param request リクエスト
	 * @param response レスポンス
	 * @return フォワード先
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// セッション初期化
		HttpSession session = request.getSession();
		session.invalidate();

		return mapping.findForward("form");

	}

}
