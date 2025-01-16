package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<String> findById(int id) throws Exception {
		List<String> symptoms = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = "select symptoms_id from symptoms where records_id = ?";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				symptoms.add(mapToSymptoms(rs).getSymptomsId().toString());
			}
		} catch (Exception e) {
			throw e;
		}
		return symptoms;
	}

	private Symptom mapToSymptoms(ResultSet rs) throws SQLException {

		Integer symptomsId = (Integer) rs.getObject("symptoms_id");

		return new Symptom(null, null, symptomsId);
	}

}
