package com.hwq.bi.aop;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hwq.bi.annotation.CheckPoint;
import com.hwq.bi.annotation.ReduceRewardPoint;
import com.hwq.bi.common.ErrorCode;
import com.hwq.bi.exception.ThrowUtils;
import com.hwq.bi.model.entity.User;
import com.hwq.bi.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:HWQ
 * @DateTime:2023/9/23 15:17
 * @Description: 调用服务扣用积分AOP
 **/
@Aspect
@Component
public class GenChartInterceptor {
    @Resource
    private UserService userService;

    @AfterReturning(pointcut = "@annotation(com.hwq.bi.annotation.ReduceRewardPoint)")
    public void reduceUserRewardPoints(JoinPoint joinPoint) {
        // 获取注解中的值
        MethodSignature signature =
                (MethodSignature)joinPoint.getSignature();
        ReduceRewardPoint annotation = signature.getMethod().getAnnotation(ReduceRewardPoint.class);
        int reduceNum = annotation.reducePoint();
        // 获取用户信息
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        // 扣减用户积分
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .eq("id", loginUser.getId())
                .setSql("totalRewardPoints = totalRewardPoints - " + reduceNum);
        boolean update = userService.update(userUpdateWrapper);
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
    }

    @Before("@annotation(checkPoint)")
    public void doInterceptor(CheckPoint checkPoint) throws Throwable {
        int needPoint = checkPoint.needPoint();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        // 必须有该权限才通过
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        // 校验用户积分
        ThrowUtils.throwIf(loginUser.getTotalRewardPoints() < needPoint, ErrorCode.OPERATION_ERROR, "积分不足");
    }

}
