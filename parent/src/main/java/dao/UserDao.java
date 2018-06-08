package dao;


import entity.User;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by 周杰伦 on 2018/6/7.
 */


public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {

        String sql = "insert into user (username, password) values (?, ?)";

        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());

    }

}
