package dao;

import java.util.List;

import dto.RecordDB;

public interface RecordDao {
	//　レコードを全取得->一覧：全ての記録
	List<RecordDB> findAll() throws Exception;

	//個人のレコードのみ取得->一覧：未承認
	List<RecordDB> findAll(Integer loginNum) throws Exception;

	//個人のレコードのみ取得,承認状況->一覧：差し戻し・承認済み
	List<RecordDB> findAll(Integer loginNum, Integer approval) throws Exception;

	//　今日・昨日分のレコードを全取得->toppage相談件数
	List<RecordDB> findAll(int date) throws Exception;

	//　先週今週のレコードを全取得->toppage相談件数グラフ
	List<RecordDB> findAllWeek(int date) throws Exception;

	//　レコードを1件表示->閲覧画面
	RecordDB findById(Integer id) throws Exception;

	//　レコードを登録->新規登録
	Integer insert(RecordDB record) throws Exception;

	//　レコード更新->編集画面
	void update(RecordDB record) throws Exception;

	//　レコード承認
	void permit(Integer id) throws Exception;

	//　レコード差戻し
	void remand(Integer id) throws Exception;
	
	//　レコード削除
	void delete(Integer id) throws Exception;
}
