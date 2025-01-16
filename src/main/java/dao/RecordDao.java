package dao;

import java.util.List;

import dto.RecordDB;

public interface RecordDao {
	// レコードを全取得
	List<RecordDB> findAll() throws Exception;

	//レコードを1件表示
	RecordDB findById(int id) throws Exception;

	// レコードを登録
	Integer insert(RecordDB record) throws Exception;
	
	void update(RecordDB record) throws Exception;
	
}
