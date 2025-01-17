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

		return new Symptom(id, recordsId, symptomsId);
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
}
