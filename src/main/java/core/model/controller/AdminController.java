package core.model.controller;

import core.model.bean.AdminBean;
import core.model.bean.ResultBean;
import core.model.domain.TbStudent;
import core.model.service.AdminService;
import net.atomarrow.bean.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     *
     * @return
     */
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    /**
     * 注销返回登录页面
     *
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
     *
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
     * @param adminBean
     * @return
     */
    @RequestMapping("/loginCheck")
    @ResponseBody
    public ResultBean loginCheck(Map<String, Object> map, AdminBean adminBean) {
       /* ResultBean admin = adminService.getAdmin(adminBean);
        if (admin.getSuccess() == true) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            AdminBean adminBeanSession = (AdminBean) admin.getData();
            session.setAttribute("name", adminBeanSession.getUserName());
        }
        return admin;*/
       return null;
    }


    /**
     * 注册检查
     *
     * @param adminBean
     * @return
     */
    @RequestMapping("/registerCheck")
    @ResponseBody
    public ResultBean registerCheck(AdminBean adminBean) {
     /* ServiceResult result = adminService.getAdmin( new TbStudent());
        ResultBean admin = adminService.addAdmin(adminBean);*/
        return null;
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/home")
    public String home() { //主页
        return "home";
    }
}
