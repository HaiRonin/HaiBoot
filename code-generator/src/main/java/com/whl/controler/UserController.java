package com.whl.controler;

import com.whl.entity.User;
import com.whl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author whl
 * @since 2020-05-28
 */
@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;

    @PostMapping("insert")
    @ResponseBody
    private boolean insert(@RequestBody User user) {
        user.setUserId(11L);
        return iUserService.insert(user);
    }

    @PostMapping("update")
    @ResponseBody
    private boolean update(@RequestBody User user) {
        return iUserService.updateById(user);
    }

    @PostMapping("delete")
    @ResponseBody
    private boolean delete(@RequestBody User user) {
        return iUserService.deleteById(user.getUserId());
    }

}
