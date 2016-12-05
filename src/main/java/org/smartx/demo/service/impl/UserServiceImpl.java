package org.smartx.demo.service.impl;

import org.smartx.demo.dao.UserDao;
import org.smartx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * <b>Creation Time:</b> 16/12/4
 *
 * @author kext
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String findNameById(Integer id) {
        return userDao.findNameById(id);
    }
}
