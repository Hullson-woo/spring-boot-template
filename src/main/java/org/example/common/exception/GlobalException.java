package org.example.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.common.result.Result;
import org.example.common.result.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * <p>全局异常</p>
 *
 * @author Hullson
 * @date 2025-05-31
 * @since 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalException {
    private static Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 拒绝访问异常全局处理器
     * @param e         AccessDeniedException
     * @param request   Http请求
     * @param response  Http响应
     * @return          Result
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        log.error("## Access Denied Exception. request URI: {} ##", requestURI);
        e.printStackTrace();
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        return ResultUtil.error(HttpServletResponse.SC_UNAUTHORIZED, "暂无访问权限，请联系管理员授权");
    }

    /**
     * Http请求方式不支持异常全局处理器
     * @param e         HttpRequestMethodNotSupportedException
     * @param request   Http请求
     * @param response  Http响应
     * @return          Result
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                               HttpServletRequest request,
                                                               HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        log.error("## Request Method Not Support. request URI: {} ##", requestURI);
        e.printStackTrace();
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json; charset=UTF-8");
        return ResultUtil.error(HttpServletResponse.SC_BAD_REQUEST,"请求方式不支持\n" + e.getMessage());
    }

    /**
     * 未知运行时异常全局处理器
     * @param e         RuntimeException
     * @param request   Http请求
     * @param response  Http响应
     * @return          Result
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result handleRuntimeException(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        log.error("## RuntimeException. request URI: {} ##", requestURI);
        e.printStackTrace();
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json; charset=UTF-8");
        String errorMsg = e.getMessage();
        if (StringUtils.isEmpty(errorMsg)) {
            errorMsg = e.getClass().getSimpleName();
        }
        return ResultUtil.error(String.format("RuntimeException: %s", errorMsg));
    }

    /**
     * 服务端异常全局处理器
     * @param e         Exception
     * @param request   Http请求
     * @param response  Http响应
     * @return          Result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        log.error("## Internal Server Error. request URI: {} ##", requestURI);
        log.error("## Internal Server Error ##", e);
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json; charset=UTF-8");
        String errorMsg = e.getMessage();
        if (StringUtils.isEmpty(errorMsg)) {
            errorMsg = e.getClass().getSimpleName();
        }
        return ResultUtil.error(String.format("Internal Server Error: %s", errorMsg));
    }

    /**
     * 自定义异常全局处理器
     * @param e         ServiceException
     * @param request   Http请求
     * @return          Result
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("## Service Exception. request URI: {} ##", requestURI);
        e.printStackTrace();
        return ResultUtil.returnCode(e.getCode(),e.getMessage());
    }
}
