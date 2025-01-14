package dao;

import dto.Symptom;

public interface SymptomDao {

	void insert(Symptom symptom, String[] options) throws Exception;
	
}
