package mr.user.dao;

import java.sql.SQLException;
import java.util.List;

import mr.user.domain.User;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	public User findByUsername(String username) {
		try {
			String sql="select * from tb_user where uname=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User findByEmail(String uemail) {
		try{
			String sql="select * from tb_user where uemail=?";
			return qr.query(sql, new BeanHandler<User>(User.class), uemail);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void add(User form) {
		try {
			String sql="insert into tb_user values(?,?,?,?,?,?,?)";
			Object[] params={form.getUid(),form.getUname(),form.getUpassword(),
					form.getUemail(),form.getUcode(),form.isUstatus(),form.getPostnumber()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public User findByCode(String code) {
		try{
			String sql="select * from tb_user where ucode=?";
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void updateState(String uid, boolean b) {
		try {
			String sql="update tb_user set ustatus=? where uid=?";
			qr.update(sql, b, uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addPostNumber(String uname) {
		try {
			String sql="update tb_user set postnumber=postnumber+1 where uname=?";
			qr.update(sql, uname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User findUser(String uname) {
		try{
			String sql="select * from tb_user where uname=?";
			return qr.query(sql, new BeanHandler<User>(User.class), uname);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<User> findActiveUser() {
		try{
			String sql="select * from tb_user where postnumber>=?";
			return qr.query(sql, new BeanListHandler<User>(User.class),3);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<User> findUnactiveUser() {
		try{
			String sql="select * from tb_user where postnumber<?";
			return qr.query(sql, new BeanListHandler<User>(User.class),3);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void delete(String uid) {
		try {
			String sql="delete from tb_user where uid=?";
			qr.update(sql, uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
