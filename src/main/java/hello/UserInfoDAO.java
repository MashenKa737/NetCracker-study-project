package hello;

import java.util.List;

public interface UserInfoDAO {

    public UserInfo findUserInfo(String userName);

    // [USER,ADMIN,..]
    public List<String> getUserRoles(String userName);

}
