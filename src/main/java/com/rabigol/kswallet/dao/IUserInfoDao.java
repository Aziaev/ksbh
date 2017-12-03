package com.rabigol.kswallet.dao;

import com.rabigol.kswallet.entity.UserInfo;

public interface IUserInfoDao {
    UserInfo getActiveUser(String userName);
}
