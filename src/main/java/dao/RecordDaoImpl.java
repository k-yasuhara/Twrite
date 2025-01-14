package dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import dto.Patient;
import dto.RecordDB;
import dto.Staff;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecordDaoImpl implements RecordDao {
	private DataSource ds;

	@Override
	public List<RecordDB> findAll() throws Exception {
		List<RecordDB> recordList = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = findAllSQL();
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

	private String findAllSQL() {
		String sql = "select "
				+ "	r.id,"
				+ "	r.register_id,"
				+ "	r.start_at,"
				+ " r.end_at,"
				+ " s.name,"
				+ " p.attribute,"
				+ " r.consultation,"
				+ " r.response,"
				+ " group_concat(symptoms_pattern.symptoms_name separator \",\") as \"symptoms\" "
				+ " from records as r "
				+ " join staff as s on r.staff_id = s.id "
				+ "	join patient as p on r.patient_pattern = p.id "
				+ " join symptoms on r.id = symptoms.records_id "
				+ " join symptoms_pattern on symptoms.symptoms_id = symptoms_pattern.id "
				+ " group by r.id;";
		return sql;
	}

	private RecordDB mapToRecord(ResultSet rs) throws Exception {

		Integer id = (Integer) rs.getObject("r.id");
		String registerId = rs.getString("r.register_id");
		Date start = rs.getTimestamp("r.start_at");
		Date end = rs.getTimestamp("r.end_at");
		String consContent = rs.getString("r.consultation");
		String respContent = rs.getString("r.response");
		String symptoms = rs.getString("symptoms");
		
		String sName = rs.getString("s.name");
		Staff staff = new Staff(null, sName);
				
		String pAttribute = rs.getString("p.attribute");
		Patient patient = new Patient(id, pAttribute);
		
		RecordDB record = new RecordDB(id, registerId, null, end, start, null, null, consContent, respContent, null, null, symptoms, staff, patient);
		
		return record;
	}

	@Override
	public Integer insert(RecordDB record) throws Exception {
		Integer recordsId = null;

		try (var con = ds.getConnection();) {
			String sql = insertSQL();
			var stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

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
