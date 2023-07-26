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

/**
 * StatsActionクラス. <br>
 * StatsActionクラスは、過去半年の統計表示画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class StatsAction extends Action {

	/**
	 * 過去半年の統計表示画面に遷移させます
	 * 
	 * @throws ClassNotFoundException クラス未発見例外
	 * @throws SQLException SQL例外
	 * @param mapping マッピング
	 * @param form フォーム
	 * @param request リクエスト
	 * @param response レスポンス
	 * @return フォワード先
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException {

		// セッションからおみくじコードを取得
		HttpSession session = request.getSession();
		int omikujiCode = (Integer) session.getAttribute("omikujiCode");

		// おみくじコードを元に、過去半年と本日の占い結果の割合を取得し、リクエストスコープにセット
		GetPastOmikujiDao dao = new GetPastOmikujiDao();
		request.setAttribute("stats", dao.getLastSixMonthsStats(omikujiCode));

		return mapping.findForward("stats");
	}

}
