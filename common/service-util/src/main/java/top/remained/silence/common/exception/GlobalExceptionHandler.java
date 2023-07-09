package top.remained.silence.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.remained.silence.common.result.Result;

/**
 * @Author: lx
 * @CreateTime: 2023-07-09  22:51
 * @Description: TODO
 */
@ControllerAdvice
//可以将这些通知应用到整个应用程序内的所有控制器上 异常处理，属性配置
// AOP
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(SilenceException.class)
    @ResponseBody
    public Result error(SilenceException e){
        return Result.fail(e);
    }
    @ExceptionHandler(Exception.class) //出现异常 执行此方法
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(e);
    }
}

