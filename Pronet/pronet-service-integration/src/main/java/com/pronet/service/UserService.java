package com.pronet.service;

import org.pronet.common.vo.UserVo;

/**
 * Created by pr250155 on 5/19/17.
 */
public interface UserService {
    public UserVo getUser(int userId);
    UserVo addUser(UserVo userVo);
}
