package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.OmikujiBean;
import common.ConvertData;
import common.DataBaseAcess;

/**
 * GetPastOmikujiDaoクラス. <br>
 * GetPastOmikujiDaoは、データベースからテーブル情報を取得します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class GetPastOmikujiDao {

	/** データベースアクセスオブジェクト */
	private final DataBaseAcess dba = new DataBaseAcess();

	/**
	 * resultテーブルに保存されている過去半年のおみくじデータを取得します
	 * 
	 * @throws ClassNotFoundException クラス未発見例外
	 * @throws SQLException SQL例外
	 * @param birthday 誕生日
	 * @return List<OmikujiBean> おみくじBeanリスト
	 */
	public List<OmikujiBean> getLastSixMonthsList(String birthday) throws ClassNotFoundException, SQLException {

		ResultSet resultSet = null;

		try {
			// データベース接続
			dba.open();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT r.create_date, u.unsei_name, o.negaigoto, o.akinai, o.gakumon ");
			sql.append("FROM unseimaster AS u ");
			sql.append("INNER JOIN omikuji AS o ON u.unsei_code = o.unsei_code ");
			sql.append("INNER JOIN result AS r ON o.omikuji_code = r.omikuji_code ");
			sql.append("WHERE r.create_date ");
			sql.append("BETWEEN current_timestamp + '-6 month' AND current_timestamp ");
			sql.append("AND birthday = ? ");

			// SQLセット
			dba.setSql(sql.toString());

			// index1番に誕生日をセット
			dba.setData(1, ConvertData.toSqlDate(birthday));

			// SQL実行
			resultSet = dba.select();
			List<OmikujiBean> list = new ArrayList<>();

			// Beanに格納
			while (resultSet.next()) {
				OmikujiBean bean = new OmikujiBean();
				bean.setCreateDate(resultSet.getDate("create_date").toString());
				bean.setUnsei(resultSet.getString("unsei_name"));
				bean.setNegaigoto(resultSet.getString("negaigoto"));
				bean.setAkinai(resultSet.getString("akinai"));
				bean.setGakumon(resultSet.getString("gakumon"));
				list.add(bean);
			}

			return list;

		} finally {
			// データベース切断
			dba.close();

			if (resultSet != null) {
				resultSet.close();
			}
		}

	}

	/**
	 * resultテーブルに保存されている全体の過去半年と本日の占い結果の割合を取得します。
	 * 
	 * @throws ClassNotFoundException クラス未発見例外
	 * @throws SQLException SQL例外
	 * @param birthday 誕生日
	 * @return List<OmikujiBean> おみくじBeanリスト
	 */
	public double getLastSixMonthsStats(int omikujiCode) throws ClassNotFoundException, SQLException {

		ResultSet resultSet = null;

		try {
			// データベース接続
			dba.open();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ROUND(CAST(CAST(COUNT(omikuji_code) as float) / ");
			sql.append("(SELECT COUNT(omikuji_code)  FROM result) * 100 as numeric),1) as RATIO ");
			sql.append("FROM result ");
			sql.append("WHERE create_date ");
			sql.append("BETWEEN current_timestamp + '-6 month' AND current_timestamp ");
			sql.append("AND omikuji_code = ? ");

			// SQLをセット
			dba.setSql(sql.toString());

			// index1番におみくじコードをセット
			dba.setData(1, omikujiCode);

			// SQL実行
			resultSet = dba.select();

			// 割合を返す
			if (resultSet.next()) {
				return resultSet.getDouble("RATIO");
			}

			return 0;

		} finally {
			// データベース切断
			dba.close();

			if (resultSet != null) {
				resultSet.close();
			}
		}
	}

}
