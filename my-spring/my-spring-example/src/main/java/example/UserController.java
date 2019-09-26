package example;

import my.framwork.annotation.Autowired;
import my.framwork.annotation.Controller;
import my.framwork.annotation.RequestMapping;
import my.framwork.annotation.RequestMethod;
import my.framwork.bean.View;

import java.util.List;

/**
 * 描述:
 *
 * @author ace-huang
 * @create 2019-09-26 9:37 AM
 */
@Controller
public class UserController {


    private IUserService userService = new UserService();

    /**
     * 用户列表
     * @return
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)

    public View getUserList() {
        List<User> userList = userService.getAllUser();
        return new View("index.jsp").addModel("userList", userList);
    }
}
