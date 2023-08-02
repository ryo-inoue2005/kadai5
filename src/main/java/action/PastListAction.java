package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.GetPastOmikujiDao;
import form.PastListForm;

/**
 * PastListActionクラス. <br>
 * PastListActionクラスは、過去半年の結果リスト画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class PastListAction extends Action {

	/**
	 * 過去半年の結果を取得し、過去半年のリスト統計画面に遷移します。
	 * 
	 * @throws ClassNotFoundException クラス未発見例外
	 * @throws SQLException SQL例外
	 * @param mapping マッピング
	 * @param form フォーム
	 * @param request リクエスト
	 * @param response レスポンス
	 * @return フォワード先
	 * @throws UnauthorizedAccessException 不正アクセス例外
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		PastListForm pastListForm = (PastListForm) form;

		// セッションから誕生日を取得
		HttpSession session = request.getSession();
		String birthday = (String) session.getAttribute("birthday");

		// 入力された誕生日の過去半年の結果を取得し、フォームにセット
		GetPastOmikujiDao dao = new GetPastOmikujiDao();
		pastListForm.setListOmikuji(dao.getLastSixMonthsList(birthday));

		return mapping.findForward("success");
	}
}
