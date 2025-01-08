package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import dto.RecordDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecordDaoImpl implements RecordDao {
	private DataSource ds;

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
	public List<RecordDB> findAll() throws Exception {
		List<RecordDB> recordList = new ArrayList<RecordDB>();

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

	@Override
	public void insert(RecordDB record) throws Exception {
		List<RecordDB> recordList = new ArrayList<RecordDB>();

		try (var con = ds.getConnection();) {
			String sql = "select * from records";
			var stmt = con.prepareStatement(sql);
		
			stmt.executeUpdate();
		
		} catch (Exception e) {
			throw e;
		}

	}

}
