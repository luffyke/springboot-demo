package org.smartx.demo.dao.impl;

import org.smartx.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * </p>
 *
 * <b>Creation Time:</b> 16/12/4
 *
 * @author kext
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String findNameById(Integer id) {
        return jdbcTemplate.queryForObject("select name from user where id = ?", new Object[]{id}, String.class);
    }
}
