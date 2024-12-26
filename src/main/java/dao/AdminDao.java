package dao;

import dto.Admin;

public interface AdminDao {
	//	ログインID、パスワードにマッチするオブジェクトを返すメソッド
	Admin findByLoginIdAndPass(String loginId, String loginPass) throws Exception;
}
