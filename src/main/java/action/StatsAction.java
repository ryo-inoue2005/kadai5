package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.GetPastOmikujiDao;
import form.StatsForm;

/**
 * StatsActionクラス. <br>
 * StatsActionクラスは、過去半年の統計表示画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class StatsAction extends Action {

	/**
	 * 過去半年の割合を取得し、過去半年の割合統計表示画面に遷移します。
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

		StatsForm statsForm = (StatsForm) form;

		// おみくじコードを元に、過去半年の割合を取得し、フォームにセット
		GetPastOmikujiDao dao = new GetPastOmikujiDao();
		statsForm.setStatsMap(dao.getLastSixMonthsStats());

		return mapping.findForward("stats");
	}

}
