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
	public void insert(Integer recordsId, String[] options) throws Exception {

		//checboxにチェックがない場合、処理を終える
		if (options == null)
			return;

		try (var con = ds.getConnection();) {
			String sql = insertSQL();
			var stmt = con.prepareStatement(sql);

			for (String o : options) {
				stmt.setObject(1, recordsId, Types.INTEGER);
				stmt.setObject(2, Integer.parseInt(o));
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
	public List<Symptom> findById(Integer id) throws Exception {
		List<Symptom> symptoms = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = "select * from symptoms where records_id = ?";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				symptoms.add(mapToSymptoms(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return symptoms;
	}

	private Symptom mapToSymptoms(ResultSet rs) throws SQLException {

		Integer id = (Integer) rs.getObject("id");
		Integer recordsId = (Integer) rs.getObject("records_id");
		Integer symptomsId = (Integer) rs.getObject("symptoms_id");

		return new Symptom(id, recordsId, symptomsId, null, null);
	}

	@Override
	public void delete(Integer id) throws Exception {
		try (var con = ds.getConnection();) {
			String sql = "delete from symptoms where records_id = ?";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Symptom> countSymptom(int date) throws Exception {
		List<Symptom> symptoms = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = countSymptomSQL(date);
			var stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				symptoms.add(mapToCountSymptoms(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return symptoms;
	}

	private Symptom mapToCountSymptoms(ResultSet rs) throws SQLException {
		String symptomName = rs.getString("symptoms_name");
		int countSymptom = rs.getInt("count");
		return new Symptom(null, null, null, symptomName, countSymptom);
	}

	private String countSymptomSQL(int date) {
		String sql = "select symptoms_name, count(symptoms_id) as count "
				+ "FROM symptoms "
				+ "join symptoms_pattern on symptoms_id = symptoms_pattern.id "
				+ "join records on records_id = records.id "
				+ coutSmptomTodaySQL(date)
				+ "group by symptoms_id "
				+ "order by count(*) desc "
				+ "limit 3 ;";
		return sql;
	}

	private String coutSmptomTodaySQL(int date) {
		String sql = new String();
		if (date == 0) {
			sql = "where date(start_at) = curdate() ";
		} else if (date == -1) {
			sql = "where date(start_at) = CURDATE() - INTERVAL 1 DAY ";
		}
		return sql;
	}
}
