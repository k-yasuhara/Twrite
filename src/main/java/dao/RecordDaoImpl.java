package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = findAllSQL() + " group by r.id;";
			var stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToRecordViewList(rs));
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
				+ " r.approval_status,"
				+ " group_concat(symptoms_pattern.symptoms_name separator \",\") as \"symptoms\" "
				+ " from records as r "
				+ " join staff as s on r.staff_id = s.id "
				+ "	join patient as p on r.patient_pattern = p.id "
				+ " join symptoms on r.id = symptoms.records_id "
				+ " join symptoms_pattern on symptoms.symptoms_id = symptoms_pattern.id ";

		return sql;
	}

	@Override
	public List<RecordDB> findAll(Integer loginNum) throws Exception {
		List<RecordDB> recordList = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = findAllSQL() + " where r.register_id = ? group by r.id;";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, loginNum);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToRecordViewList(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return recordList;
	}

	@Override
	public List<RecordDB> findAll(Integer loginNum, Integer approval) throws Exception {
		List<RecordDB> recordList = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = findAllSQL() + " where r.register_id = ? and r.approval_status = ? group by r.id;";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, loginNum);
			stmt.setInt(2, approval);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToRecordViewList(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return recordList;
	}

	private RecordDB mapToRecordViewList(ResultSet rs) throws Exception {

		Integer id = (Integer) rs.getObject("id");
		Integer registerId = (Integer) rs.getObject("register_id");
		Date start = rs.getTimestamp("start_at");
		Date end = rs.getTimestamp("end_at");
		String consContent = rs.getString("consultation");
		String respContent = rs.getString("response");
		String symptoms = rs.getString("symptoms");

		String sName = rs.getString("name");
		Staff staff = new Staff(null, sName);

		String pAttribute = rs.getString("attribute");
		Patient patient = new Patient(id, pAttribute);

		RecordDB record = new RecordDB(id, registerId, null, null, start, end, null, consContent, respContent, null,
				null, null, symptoms, staff, patient);

		return record;
	}

	@Override
	public Integer insert(RecordDB record) throws Exception {
		Integer recordsId = null;

		try (var con = ds.getConnection();) {
			String sql = insertSQL();
			var stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

			stmt.setObject(1, record.getRegisterId(), Types.INTEGER);
			stmt.setTimestamp(2, new Timestamp(record.getStart().getTime()));
			stmt.setTimestamp(3, new Timestamp(record.getEnd().getTime()));
			stmt.setObject(4, record.getPatientPattern(), Types.INTEGER);
			stmt.setString(5, record.getConsContent());
			stmt.setString(6, record.getRespContent());
			stmt.setObject(7, record.getStaffId(), Types.INTEGER);
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
				+ "values "
				+ "(? ,now(), now() ,? ,? ,? ,? ,? ,0, ?);";

		return sql;
	}

	@Override
	public RecordDB findById(Integer id) throws Exception {
		RecordDB record = null;

		try (var con = ds.getConnection();) {
			String sql = "select * from records where id = ?";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				record = mapToRecordView(rs);
			}

		} catch (Exception e) {
			throw e;
		}
		return record;
	}

	private RecordDB mapToRecordView(ResultSet rs) throws SQLException {

		Integer id = (Integer) rs.getObject("id");
		Integer registerId = (Integer) rs.getObject("register_id");
		Date start = rs.getTimestamp("start_at");
		Date end = rs.getTimestamp("end_at");
		Integer patientP = (Integer) rs.getObject("patient_pattern");
		String consContent = rs.getString("consultation");
		String respContent = rs.getString("response");
		Integer staffId = (Integer) rs.getObject("staff_id");

		RecordDB record = new RecordDB(id, registerId, null, null, start, end, patientP, consContent, respContent, 0,
				staffId, null, null, null, null);

		return record;
	}

	@Override
	public void update(RecordDB record) throws Exception {

		try (var con = ds.getConnection();) {
			String sql = updateSQL();
			var stmt = con.prepareStatement(sql);

			stmt.setTimestamp(1, new Timestamp(record.getStart().getTime()));
			stmt.setTimestamp(2, new Timestamp(record.getEnd().getTime()));
			stmt.setObject(3, record.getPatientPattern(), Types.INTEGER);
			stmt.setString(4, record.getConsContent());
			stmt.setString(5, record.getRespContent());
			stmt.setObject(6, record.getStaffId(), Types.INTEGER);
			stmt.setObject(7, record.getId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	private String updateSQL() {
		String sql = "update records set "
				+ "updated_at = now(), "
				+ "start_at=?, "
				+ "end_at=?, "
				+ "patient_pattern=?, "
				+ "consultation=?, "
				+ "response=?, "
				+ "staff_id=? "
				+ "where id=?;";
		return sql;
	}

	@Override
	public void permit(Integer id) throws Exception {
		try (var con = ds.getConnection();) {
			String sql = "update records set "
					+ "approval_status = 1 "
					+ "where id = ?;";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void remand(Integer id) throws Exception {
		try (var con = ds.getConnection();) {
			String sql = "update records set "
					+ "approval_status = 2 "
					+ "where id = ?;";
			var stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<RecordDB> findAll(int date) throws Exception {
		List<RecordDB> recordList = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = findAllSQL() + countRecord(date);
			var stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToRecordViewList(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return recordList;
	}

	private String countRecord(int date) {
		String sql = new String();
		if (date == 0) {
			sql = "where date(start_at) = curdate() group by r.id";
		} else if (date == -1) {
			sql = "where date(start_at) = CURDATE() - INTERVAL 1 DAY group by r.id";
		}
		return sql;
	}

	@Override
	public List<RecordDB> findAllWeek(int date) throws Exception {
		List<RecordDB> recordList = new ArrayList<>();

		try (var con = ds.getConnection();) {
			String sql = findAllSQL() + countRecordWeek(date);
			var stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				recordList.add(mapToRecordViewList(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return recordList;
	}

	private String countRecordWeek(int date) {
		String sql = new String();

		sql = "where date(start_at) = "
				+ " (SELECT CURDATE() - INTERVAL (WEEKDAY(CURDATE()) +"
				+ date
				+ ") DAY) group by r.id";
		return sql;
	}

}
