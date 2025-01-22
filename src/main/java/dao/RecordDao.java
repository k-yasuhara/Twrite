package dao;

import java.util.List;

import dto.RecordDB;

public interface RecordDao {
	//　レコードを全取得
	List<RecordDB> findAll() throws Exception;

	//個人のレコードのみ取得
	List<RecordDB> findAll(Integer loginNum) throws Exception;

	//個人のレコードのみ取得,承認状況
	List<RecordDB> findAll(Integer loginNum, Integer approval) throws Exception;

	//　今日・昨日分のレコードを全取得
	List<RecordDB> findAll(int date) throws Exception;

	//　先週今週のレコードを全取得
	List<RecordDB> findAllWeek(int date) throws Exception;

	//　レコードを1件表示
	RecordDB findById(Integer id) throws Exception;

	//　レコードを登録
	Integer insert(RecordDB record) throws Exception;

	//　レコード更新
	void update(RecordDB record) throws Exception;

	//　レコード承認
	void permit(Integer id) throws Exception;

	//　レコード差戻し
	void remand(Integer id) throws Exception;
}
