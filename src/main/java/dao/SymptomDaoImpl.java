package dao;

import java.sql.Types;

import javax.sql.DataSource;

import dto.Symptom;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SymptomDaoImpl implements SymptomDao {

	private DataSource ds;

	@Override
	public void insert(Symptom symptom, String[] options) throws Exception {

		//checboxにチェックがない場合、処理を終える
		if (options == null)
			return;
		
		try (var con = ds.getConnection();) {
			String sql = insertSQL();
			var stmt = con.prepareStatement(sql);

			for (String option : options) {
				stmt.setObject(1, symptom.getRecodsId(), Types.INTEGER);
				stmt.setObject(2, Integer.parseInt(option));
				stmt.addBatch();
			}
			stmt.executeBatch();
		} catch (Exception e) {
			throw e;
		}

	}

	private String insertSQL() {
		String sql = "insert into symptoms (records_id, symptoms_id)"
				+ "values (?,?)";
		return sql;
	}

}
