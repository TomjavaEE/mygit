package com.zmkj.springmvcday3.controller;

import com.sun.deploy.net.HttpResponse;
import com.zmkj.springmvcday3.dao.RoleDao;
import com.zmkj.springmvcday3.entity.Role;
import com.zmkj.springmvcday3.entity.User;
import com.zmkj.springmvcday3.service.RoleService;
import com.zmkj.springmvcday3.service.UserService;
import com.zmkj.springmvcday3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class mvcServlet {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService reloService;


    //登陆后查询所有用户
    @RequestMapping("/userlogin")
    public String login(User user, HttpServletRequest request){
        User login = userService.login(user.getUserCode(), user.getUserPassword());
        if(login==null){
            request.setAttribute("error","用户名或者密码错误");
            return "login";
        }

        return "frame";
    }
    //查询所有用户的信息
    @RequestMapping("selectUser")
    public String user(HttpServletRequest request,@RequestParam(value = "pageNumber",required = false) String pageNumber,
                       @RequestParam(value = "queryname",required = false) String queryname,@RequestParam(value = "queryUserRole",required = false) String queryUserRole){
        int pageNow=1;  //当前页
        String username=null; //用户名
        int userRole=0; //用户角色

        if(pageNumber!=null){
            pageNow= Integer.parseInt(pageNumber);
        }
        if(queryname!=null){
            username=queryname;
        }
        if(queryUserRole!=null){
            userRole=Integer.parseInt(queryUserRole);
        }
        List<User> userList = userService.getUserList(username, userRole, pageNow, 5);
        int count = userService.getUserCount(username, userRole); //总记录数
        int allpage = userService.allPage(count, 5); //总页数
        List<Role> roleList = reloService.getRoleList();
        request.setAttribute("userList",userList);
        request.setAttribute("roleList",roleList);  //传入role集合
        request.setAttribute("pageNow",pageNow);  //传入当前页
        request.setAttribute("allpage",allpage); //传入总页数
        return "userlist";
    }


    //跳转添加用户界面
    @RequestMapping("useradd")
    public String userAddList(){
        return "useradd";
    }

    //添加用户
    @RequestMapping("useraddsave")
    public String useraddsave(User user){
        boolean add = userService.add(user);
       if(add==true){
           return "forward:/user/selectUser";
       }
        return "useradd";
    }
    //删除用户
    @RequestMapping("deluser")
    public String delUser(@RequestParam("uid") int id, HttpServletResponse response){
        System.out.println("id为"+id);
        boolean b = userService.deleteUserById(id);
        System.out.println(b);
        if(b==true){
            try {
                response.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(b==false){
            try {
                response.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "forward:/user/selectUser";
    }

    //根据id传值
    @RequestMapping("selectById")
    public String selectBuId(@RequestParam("userid") String userid, Model model){
        User userById = userService.getUserById(userid);
        model.addAttribute("user",userById);
        return "usermodify";
    }


    //修改用户
    @RequestMapping("updateUser")
    public String updateUser(User user){
        boolean modify = userService.modify(user);
        if(modify==true){
            return "forward:/user/selectUser";
        }
        return "usermodify";
    }

    //显示详细信息
    @RequestMapping("detail")
    public String detail(@RequestParam("userid") String userid, Model model){
        User userById = userService.getUserById(userid);
        model.addAttribute("user",userById);
        return "userview";
    }

}
