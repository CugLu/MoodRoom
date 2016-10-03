package mr.user.service;

import java.util.List;

import mr.user.dao.UserDao;
import mr.user.domain.User;

public class UserService {
	private UserDao userDao=new UserDao();

	public void regist(User form) throws UserException {
		User user=userDao.findByUsername(form.getUname());
		if(user!=null)
			throw new UserException("用户名已被注册");
		
		user=userDao.findByEmail(form.getUemail());
		if(user!=null)
			throw new UserException("email已被注册");
		
		userDao.add(form);
	}

	public void active(String code) throws UserException {
		User user=userDao.findByCode(code);
		if(user==null)
			throw new UserException("激活码无效！");
		else if(user.isUstatus()==true)
			throw new UserException("您已经激活过了！");
		userDao.updateState(user.getUid(), true);
	}

	public User login(User form) throws UserException {
		User user=userDao.findByUsername(form.getUname());
		if(user==null){
			throw new UserException("用户不存在！");
		}
		else if(!user.getUpassword().equals(form.getUpassword())){
			throw new UserException("密码不对啊大兄弟！");
		}
		else if(user.isUstatus()!=true){
			throw new UserException("别逗，你还没激活！");
		}
		else
			return user;
	}

	public void addPostNumber(String uname) {
		userDao.addPostNumber(uname);
	}

	public User findUser(String uname) {
		return userDao.findUser(uname);
	}

	public List<User> findActiveUser() {
		return userDao.findActiveUser();
	}

	public List<User> findUnactiveUser() {
		return userDao.findUnactiveUser();
	}

	public void delete(String uid) {
		userDao.delete(uid);
	}
	
}
