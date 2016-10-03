package mr.post.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import mr.post.domain.PageBean;
import mr.post.domain.Post;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class PostDao {
	private QueryRunner qr = new TxQueryRunner();

	public void add(Post post) {
		try {
			String sql = "insert into tb_post values(?,?,?,?,?,?,?,?)";
			
			/*
			 * 将util的Date转换成sql的Timestamp
			 */
			Timestamp timestamp = new Timestamp(post.getPtime().getTime());
			Object[] params = {post.getPid(),post.getUname(),post.getPtitle(),
					post.getPimage(),post.getPcontent(),post.getPzan(),
					post.getPcomment(),timestamp};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*public List<Post> findAll() {
		try {
			String sql="select * from tb_post";
			return qr.query(sql, new BeanListHandler<Post>(Post.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	
	public PageBean<Post> findAll(int pc, int ps) {
		try {
			/*
			 * 1. 得到PageBean对象pb
			 * 2. 设置pb的pc和ps
			 * 3. 得到tr，设置给pb
			 * 4. 得到beanList，设置给pb
			 * 5. 返回pb
			 */
			PageBean<Post> pb = new PageBean<Post>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * 得到tr
			 */
			String sql = "select count(*) from tb_post";
			Number num = (Number)qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * 得到beanList
			 */
			sql = "select * from tb_post limit ?,?";
			List<Post> beanList = qr.query(sql, 
					new BeanListHandler<Post>(Post.class), 
					(pc-1)*ps, ps);
			pb.setBeanList(beanList);
			return pb;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Post> findUserPost(String uname) {
		try {
			String sql="select * from tb_post where uname=?";
			return qr.query(sql, new BeanListHandler<Post>(Post.class),uname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Post findPost(String pid) {
		try {
			String sql="select * from tb_post where pid=?";
			return qr.query(sql, new BeanHandler<Post>(Post.class), pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addPcomment(String pid) {
		try {
			String sql="update tb_post set pcomment=pcomment+1 where pid=?";
			qr.update(sql, pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void changeZan(String pid, String text) {
		try {
			String sql;
			if(text.equals("赞一个"))
				sql="update tb_post set pzan=pzan+1 where pid=?";
			else
				sql="update tb_post set pzan=pzan-1 where pid=?";
			qr.update(sql, pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String findZan(String pid) {
		try {
			String sql="select pzan from tb_post where pid=?";
			Number number=(Number) qr.query(sql, new ScalarHandler(), pid);
			return number.toString();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Post> findHotPost() {
		try {
			String sql="select * from tb_post where pcomment>=?";
			return qr.query(sql, new BeanListHandler<Post>(Post.class),3);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Post> findColdPost() {
		try {
			String sql="select * from tb_post where pcomment<?";
			return qr.query(sql, new BeanListHandler<Post>(Post.class),3);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String pid) {
		try {
			String sql="delete from tb_post where pid=?";
			qr.update(sql, pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
