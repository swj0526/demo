package core.model.controller;

import core.model.domain.TbAdmin;
import core.model.service.AdminService;
import net.atomarrow.bean.ServiceResult;
import net.atomarrow.render.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 操作admin表
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    /**
     * 注销返回登录页面
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";

    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 登录检查
     *
     * @param map
     * @param admin
     * @return
     */
    @RequestMapping("/loginCheck")
    @ResponseBody
    public ServiceResult loginCheck(Map<String, Object> map, TbAdmin admin) {
        ServiceResult adminDB = adminService.getAdminService(admin);
        if (adminDB.isSuccess() == true) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            String userName = admin.getUserName();
            session.setAttribute("name", userName);
            return adminDB;
        } else {
            return adminDB;
        }
    }


    /**
     * 注册检查
     * @param admin
     * @return
     */
    @RequestMapping("/registerCheck")
    @ResponseBody
    public ServiceResult registerCheck(TbAdmin admin) {
        ServiceResult result = adminService.addAdminService(admin);
     /* ServiceResult result = adminService.getAdmin( new TbStudent());
        ResultBean admin = adminService.addAdmin(adminBean);*/
        return result;
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/home")
    public String home() { //主页
        return "home";
    }
}
