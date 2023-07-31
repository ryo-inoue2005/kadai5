package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import common.ConvertData;
import common.DataBaseAcess;
import omikuji.Omikuji;
import omikuji.OmikujiFactory;

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
	 * @return List<Omikuji> おみくじリスト
	 */
	public List<Omikuji> getLastSixMonthsList(String birthday) throws ClassNotFoundException, SQLException {

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
			sql.append("BETWEEN ? AND CURRENT_DATE ");
			sql.append("AND birthday = ? ");

			// SQLセット
			dba.setSql(sql.toString());

			dba.setData(1, getHalfYearAgo());
			dba.setData(2, ConvertData.toSqlDate(birthday));

			// SQL実行
			resultSet = dba.select();

			List<Omikuji> list = new LinkedList<>();

			// リストに格納
			while (resultSet.next()) {
				Omikuji omikuji = OmikujiFactory.create(resultSet.getString("unsei_name"),
						resultSet.getString("negaigoto"),
						resultSet.getString("akinai"), resultSet.getString("gakumon"));

				omikuji.setCreateDate(resultSet.getString("create_date"));
				list.add(omikuji);
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
	 * 全体の過去半年の運勢結果の割合を取得します。
	 * 
	 * @throws ClassNotFoundException クラス未発見例外
	 * @throws SQLException SQL例外
	 * @return Map<String, Double> 各運勢の割合
	 */
	public Map<String, Double> getLastSixMonthsStats() throws ClassNotFoundException, SQLException {

		ResultSet resultSet = null;

		try {
			// データベース接続
			dba.open();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT u.unsei_name, ROUND(CAST(CAST(COUNT(r.omikuji_code) as float) / ");
			sql.append("(SELECT COUNT(omikuji_code) FROM result) * 100 as numeric),1) as RATIO ");
			sql.append("FROM unseimaster AS u ");
			sql.append("LEFT JOIN omikuji AS o ON u.unsei_code = o.unsei_code ");
			sql.append("LEFT JOIN result AS r ON o.omikuji_code = r.omikuji_code ");
			sql.append("AND r.create_date BETWEEN ? AND CURRENT_DATE ");
			sql.append("GROUP BY u.unsei_name ");
			sql.append("ORDER BY RATIO DESC ");

			// SQLをセット
			dba.setSql(sql.toString());
			dba.setData(1, getHalfYearAgo());

			// SQL実行
			resultSet = dba.select();

			Map<String, Double> unseiMap = new LinkedHashMap<>();

			while (resultSet.next()) {
				unseiMap.put(resultSet.getString("unsei_name"), resultSet.getDouble("RATIO"));
			}
			return unseiMap;

		} finally {
			// データベース切断
			dba.close();

			if (resultSet != null) {
				resultSet.close();
			}
		}
	}

	/**
	 * 半年前の日付を取得します。
	 * 
	 * @return java.sql.Date 半年前の日付
	 */
	public Date getHalfYearAgo() {

		// 今日の日付を取得
		java.util.Date date = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 半年前に設定し、SQL.DATE型に合わせる為に0を代入
		cal.add(Calendar.MONTH, -6);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new java.sql.Date(cal.getTimeInMillis());
	}

}
