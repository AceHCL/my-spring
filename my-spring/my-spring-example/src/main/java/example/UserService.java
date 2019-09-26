package example;

import my.framwork.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-26 9:37 AM
 */
@Service
public class UserService implements IUserService {
    /**
     * 获取所有用户
     */
    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Tom", 22));
        userList.add(new User(2, "Alic", 12));
        userList.add(new User(3, "Bob", 32));
        return userList;
    }
}
