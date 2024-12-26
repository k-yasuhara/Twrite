package dao;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import dto.Admin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdminDaoImpl implements AdminDao {
	private DataSource ds;

	@Override
	public Admin findByLoginIdAndPass(String loginId, String loginPass) throws Exception {
		Admin admin = null;

		try (var con = ds.getConnection();) {
			String sql = "select * from admins where login_id=?";
			var stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(loginPass, rs.getString("login_pass"))) {
					admin = mapToAdmin(rs);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return admin;
	}

	private Admin mapToAdmin(ResultSet rs) throws Exception {

		Integer id = (Integer) rs.getObject("id");
		String loginId = rs.getString("login_id");
		String loginPass = rs.getString("login_pass");
		String name = rs.getString("name");

		return new Admin(id, loginId, loginPass, name);
	}

}
