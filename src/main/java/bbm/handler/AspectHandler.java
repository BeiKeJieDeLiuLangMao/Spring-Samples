package bbm.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author bbm
 * @date 2020/5/15
 */
@Aspect
@Slf4j
@Component
public class AspectHandler {
    @Pointcut("execution(public * bbm.controller.TestRestController.*(..))")
    public void method() {
    }

    @Before("method()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("Begin execute: {}", joinPoint.getSignature());
    }

    @After("method()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("After execute: {}", joinPoint.getSignature());
    }
}
