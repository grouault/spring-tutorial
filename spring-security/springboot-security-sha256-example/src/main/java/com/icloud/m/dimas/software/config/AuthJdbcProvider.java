package com.icloud.m.dimas.software.config;

import com.icloud.m.dimas.software.entity.User;
import com.icloud.m.dimas.software.utils.CommonsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AuthJdbcProvider implements AuthenticationProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private CommonsUtils utils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select username, encript_password, hash_id, enabled, blocked, expired from user_detail where username = ?", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new User(
                            rs.getString("username"),
                            rs.getString("encript_password"),
                            rs.getString("hash_id"),
                            rs.getBoolean("enabled"),
                            rs.getBoolean("blocked"),
                            rs.getBoolean("expired")
                    );
                }
            }, username);

            boolean isValid = utils.isPasswordValid(user.getPassword(), password, user.getSalt());
            if (!isValid)
                throw new BadCredentialsException("Username atau password salah!");

            if (!user.isEnable())
                throw new LockedException("Username is locked!");

            if (user.isBlocked())
                throw new LockedException("Username is blocked!");

            if (user.isExpired())
                throw new AccountExpiredException("Username is expired!");

        } catch (EmptyResultDataAccessException erdae) {
            throw new UsernameNotFoundException("Username atau password salah!");
        }

        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
