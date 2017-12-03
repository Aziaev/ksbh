package com.rabigol.kswallet.service;

import com.rabigol.kswallet.dao.IUserInfoDao;
import com.rabigol.kswallet.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private IUserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo activeUserInfo = userInfoDao.getActiveUser(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
        UserDetails userDetails = (UserDetails) new User(
                activeUserInfo.getUserName(),
                activeUserInfo.getPassword(),
                Arrays.asList(authority));
        return userDetails;
    }

    @Required
    public void setUserInfoDao(IUserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
}
