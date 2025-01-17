package dao;

import java.util.List;

import dto.Symptom;

public interface SymptomDao {
	
	//レコード作成
	void insert(Integer recordsId, String[] options) throws Exception;
	
	//レコード取得
	List<Symptom> findById(Integer id) throws Exception;
	
	//レコード全削除
	void delete(Integer symptom)throws Exception;

}
