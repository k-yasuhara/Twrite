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
	public Integer insert(RecordDB record) throws Exception {
		Integer recordsId = null;

		try (var con = ds.getConnection();) {
			String sql = insertSQL();
			var stmt = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);

			stmt.setTimestamp(1, new Timestamp(record.getStart().getTime()));
			stmt.setTimestamp(2, new Timestamp(record.getEnd().getTime()));
			stmt.setObject(3, record.getPatientPattern(), Types.INTEGER);
			stmt.setString(4, record.getConsContent());
			stmt.setString(5, record.getRespContent());
			stmt.setObject(6, record.getStaffId(), Types.INTEGER);
			stmt.setString(7, record.getRegisterId());
			stmt.executeUpdate();

			//自動採番された管理番号を戻り値に格納
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				recordsId = generatedKeys.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		}
		return recordsId;

	}

	private String insertSQL() {
		String sql = "insert into records"
				+ "(register_id, registered_at, updated_at, start_at, end_at, patient_pattern, consultation, response, editor, staff_id)"
				+ "select admins.id ,now(),now(),?,?,?,?,?,0,? "
				+ "from admins "
				+ "where admins.login_id = ?";

		return sql;
	}

}
