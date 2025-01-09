package dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import dto.RecordDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecordDaoImpl implements RecordDao {
	private DataSource ds;

	@Override
	public List<RecordDB> findAll() throws Exception {
		List<RecordDB> recordList = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = "select * from records";
			var stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToRecord(rs));

			}
		} catch (Exception e) {
			throw e;
		}
		return recordList;
	}

	private RecordDB mapToRecord(ResultSet rs) throws Exception {

		Integer id = (Integer) rs.getObject("id");
		String registerId = rs.getString("register_id");
		Date registered = rs.getTimestamp("registered_at");
		Date updated = rs.getTimestamp("updated_at");
		Date start = rs.getTimestamp("start_at");
		Date end = rs.getTimestamp("end_at");
		Integer patientPattern = (Integer) rs.getObject("patient_pattern");
		String consContent = rs.getString("consultation");
		String respContent = rs.getString("response");
		Integer editor = (Integer) rs.getObject("editor");
		Integer staffId = (Integer) rs.getObject("staff_id");

		return new RecordDB(id, registerId, registered,
				updated, start, end, patientPattern,
				consContent, respContent, editor, staffId);
	}

	@Override
	public void insert(RecordDB record) throws Exception {

		try (var con = ds.getConnection();) {
			String sql = insertSQL();
			var stmt = con.prepareStatement(sql);

			stmt.setString(1, record.getRegisterId());
			stmt.setTimestamp(2, new Timestamp(record.getStart().getTime()));
			stmt.setTimestamp(3, new Timestamp(record.getEnd().getTime()));
			stmt.setObject(4, record.getPatientPattern(), Types.INTEGER);
			stmt.setString(5, record.getConsContent());
			stmt.setString(6, record.getRespContent());
			stmt.setObject(7, record.getStaffId(), Types.INTEGER);

			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private String insertSQL() {
		String sql = "insert into records "
				+ "(register_id, registered_at, updated_at, start_at, end_at, patient_pattern, consultation, response, editor, staff_id) "
				+ "values "
				+ "(?, now(), now(), ?,?,?,?,?,0,?)";
		return sql;
	}

}
