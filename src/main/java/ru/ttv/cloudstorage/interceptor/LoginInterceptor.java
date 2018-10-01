package ru.ttv.cloudstorage.interceptor;

import ru.ttv.cloudstorage.api.annotation.Loggable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Timofey Teplykh
 */
@Loggable
@Interceptor
public class LoginInterceptor {

    @AroundInvoke
    public Object intercept(final InvocationContext context) throws Exception{
        System.out.println(context.getTarget().getClass().getSimpleName()+":"+context.getMethod().getName());
        return context.proceed();
    }
}
