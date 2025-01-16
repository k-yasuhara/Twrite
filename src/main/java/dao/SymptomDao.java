package dao;

import java.util.List;

import dto.Symptom;

public interface SymptomDao {

	void insert(Integer recordsId, String[] options) throws Exception;

	List<String> findByIdView(int id) throws Exception;

	void update(Integer recordsId, String[] options) throws Exception;
	
	void delete(List<Symptom> symptom)throws Exception;
	
	List<Symptom> findByIdDelete(int id) throws Exception;
	

}
