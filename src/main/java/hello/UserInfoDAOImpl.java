package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

    @Autowired
    public UserInfoDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Override
    public UserInfo findUserInfo(String userName) {
        String sql = String.format("SELECT login, password FROM login, password WHERE login.id = password.id AND login.login = '%s'", userName);

        UserInfoMapper mapper = new UserInfoMapper();
        try {
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public List<String> getUserRoles(String userName) {
        String sql = String.format("SELECT user_role from login WHERE login = '%s'", userName);

        List<String> roles = this.getJdbcTemplate().queryForList(sql, String.class);

        return roles;
    }

}