package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import common.ConvertData;
import common.DataBaseAcess;
import dto.PastListDto;

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
	 * @return List<PastListDto> おみくじDTOリスト
	 */
	public List<PastListDto> getLastSixMonthsList(String birthday) throws ClassNotFoundException, SQLException {

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
			List<PastListDto> list = new ArrayList<>();

			// dtoに格納
			while (resultSet.next()) {
				PastListDto dto = new PastListDto();
				dto.setCreateDate(resultSet.getDate("create_date").toString());
				dto.setUnsei(resultSet.getString("unsei_name"));
				dto.setNegaigoto(resultSet.getString("negaigoto"));
				dto.setAkinai(resultSet.getString("akinai"));
				dto.setGakumon(resultSet.getString("gakumon"));
				list.add(dto);
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
			sql.append("SELECT ROUND(CAST(CAST(COUNT(o.omikuji_code) as float) / ");
			sql.append("(SELECT COUNT(omikuji_code) FROM result) * 100 as numeric),1) as RATIO ");
			sql.append("FROM unseimaster AS u ");
			sql.append("INNER JOIN omikuji AS o ON u.unsei_code = o.unsei_code ");
			sql.append("INNER JOIN result AS r ON o.omikuji_code = r.omikuji_code ");
			sql.append("WHERE r.create_date ");
			sql.append("BETWEEN ? AND CURRENT_DATE ");
			sql.append("AND u.unsei_name = ? ");

			// SQLをセット
			dba.setSql(sql.toString());
			dba.setData(1, getHalfYearAgo());

			String[] unseiArray = { "大吉", "吉", "中吉", "小吉", "末吉", "凶" };
			Map<String, Double> unseiMap = new LinkedHashMap<>();

			// 運勢ごとに割合を格納
			for (String unsei : unseiArray) {

				dba.setData(2, unsei);
				resultSet = dba.select();

				if (resultSet.next()) {
					unseiMap.put(unsei, resultSet.getDouble("RATIO"));
				}
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
