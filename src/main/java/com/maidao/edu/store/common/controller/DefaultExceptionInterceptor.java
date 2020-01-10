package com.maidao.edu.store.common.controller;

import com.maidao.edu.store.common.exception.RuntimeServiceException;
import com.maidao.edu.store.common.exception.ServiceException;
import com.maidao.edu.store.common.resources.LocaleBundles;
import com.maidao.edu.store.common.util.L;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionInterceptor {

    private static final RuntimeServiceException DEFAULT_EXCEPTION = new RuntimeServiceException();

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest request, HandlerMethod handlerMethod, Throwable ex) {
//        L.error(ex);
        if (!(ex instanceof ServiceException)) {
            ApiLog.log(request, null);
        }
        ServiceException se;
        if (ex instanceof ServiceException) {
            se = (ServiceException) ex;
        } else {
            L.error(ex);
            se = DEFAULT_EXCEPTION;
        }
        int errorCode = se.getErrorCode();

        String errorMsg = "";

        if (errorCode == 600) {
            errorMsg = se.getErrorParams()[0].toString();
        } else {
            errorMsg = LocaleBundles.get(LocaleBundles.getDefaultLocale(), "err." + errorCode);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("errcode", errorCode);
        result.put("errmsg", errorMsg);
        result.put("errdata", se.getErrorData());
        return new ModelAndView(new JsonView(result));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Throwable ex) {
        return new ModelAndView(new NotFoundView());
    }
}