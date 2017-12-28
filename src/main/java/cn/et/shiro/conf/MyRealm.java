package cn.et.shiro.conf;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import cn.et.shiro.dao.UserMapper;
import cn.et.shiro.entity.UserInfo;

@Component
public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	UserMapper userMapper;

	/**
	 * 认证 将登陆输入的用户名和密码和数据库中的用户名和密码对比 是否相等 返回值 null表示认证失败 非null认证通过
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		
		UsernamePasswordToken uToken=(UsernamePasswordToken) arg0;
		//获取用户名
		String userName=uToken.getPrincipal().toString();
		//根据用户名来获取该用户的所有信息
		UserInfo user = userMapper.queryUser(userName);
		 
		if(user !=null && user.getPassword().equals(new String(uToken.getPassword()))){
			System.out.println("认证成功");
			
			//该类是AuthenticationInfo的一个子实现类
			SimpleAccount simpleAccount=new SimpleAccount(uToken.getPrincipal(),uToken.getCredentials(),"MyRealm");
			
			return simpleAccount;
		}
		return null;
	}

	/**
	 * 获取当前用户的授权数据 将当前用户在数据库的角色和权限 加载到AuthorizationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("获取授权信息");
		
		//获取用户名
		String userName = arg0.getPrimaryPrincipal().toString();
		//根据用户名获取该用户的角色信息
		Set<String> roles = userMapper.querRoleByName(userName);
		//根据用户名获取该用户的权限信息
		Set<String> perms = userMapper.queryPermsByName(userName);
		//该类是AuthenticationInfo的一个子实现类
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(perms);
		
		return authorizationInfo;
	}
}
