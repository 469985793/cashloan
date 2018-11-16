package com.xindaibao.cashloan.api.user.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import com.xindaibao.cashloan.core.common.exception.BaseRuntimeException;

/**
 * Created by lsk on 2017/2/14.
 */
@Service
public class DBService {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public long insert(final String sql, final Object... args) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < args.length; i++) {
                    Object arg = args[i];
                    ps.setObject(i + 1, arg);
                }
                return ps;
            }
        }, holder);
        return holder.getKey().longValue();
    }

    public int update(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }


    public List<Map<String, Object>> queryList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }


    public Map<String, Object> queryRec(String sql, Object... args) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list.size() > 1) {
            throw new BaseRuntimeException("期望1条，实际返回" + list.size() + "条");
        }
        return list.size() == 0 ? null : list.get(0);
    }
}
