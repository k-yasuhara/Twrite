package dao;

import java.util.List;

import dto.Symptom;

public interface SymptomDao {

	void insert(Symptom symptom, String[] options) throws Exception;
	
	List<String> findById(int id) throws Exception;
	
}
