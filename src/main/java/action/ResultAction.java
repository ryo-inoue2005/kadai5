package action;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.GetOmikujiDao;
import dao.RegisterOmikujiDao;
import form.OmikujiForm;

/**
 * ResultActionクラス. <br>
 * ResultActionクラスは、結果表示画面を制御します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ResultAction extends Action {

	/**
	 * 結果表示画面に遷移させます
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
			HttpServletResponse response) throws ClassNotFoundException, SQLException, ParseException {

		// フォームから誕生日を取得
		OmikujiForm omikujiForm = (OmikujiForm) form;
		String birthday = omikujiForm.getBirthday();

		// セッションに誕生日をセット
		HttpSession session = request.getSession();
		session.setAttribute("birthday", birthday);

		// 誕生日と今日の日付を元にresultテーブルに存在するか検索をする
		GetOmikujiDao getDao = new GetOmikujiDao();
		int omikujiCode = getDao.getResult(birthday);

		// resultテーブルに無かった場合、おみくじを引き、resultテーブルに登録
		if (omikujiCode == 0) {
			Random random = new Random();

			// データベースからおみくじの数を取得し、ランダムにおみくじコードを発行する
			omikujiCode = random.nextInt(getDao.getMaxOmikujiCode()) + 1;

			// resultテーブルに登録する
			RegisterOmikujiDao registerDao = new RegisterOmikujiDao();
			int result = registerDao.registerResult(omikujiCode, birthday);

			// 登録件数が1件以外だったら強制終了させる
			if (result != 1) {
				throw new SQLException();
			}
		}

		// おみくじコードでおみくじを引き、リクエストスコープに格納
		request.setAttribute("omikuji", getDao.drawOmikuji(omikujiCode));

		// おみくじコードをセッションに追加
		session.setAttribute("omikujiCode", omikujiCode);

		return mapping.findForward("result");

	}

}
