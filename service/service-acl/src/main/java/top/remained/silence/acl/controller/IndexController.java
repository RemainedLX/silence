package top.remained.silence.acl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.common.result.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lx
 * @CreateTime: 2023-07-11  00:15
 * @Description: TODO
 */
@Api(tags = "登录接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/acl/index")
public class IndexController {
    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(String username, String password) {
        Map<String,String> map  = new HashMap<String,String>();
        map.put("token","token");
        return Result.ok(map);
    }
    @ApiOperation("登录信息")
    @GetMapping("/info")
    public Result info(String username, String password){
        Map<String,String> map  = new HashMap<String,String>();
        map.put("name","name");
        map.put("avatar","avatar");
        return Result.ok(map);
    }
    @ApiOperation("退出")
    @PostMapping("/logout")
    public Result logout(String username, String password){
        return Result.ok("logout");
    }
}

