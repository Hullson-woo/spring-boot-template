package org.example.controller.system.health;

import org.example.common.result.Result;
import org.example.common.result.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>健康状态检查</p>
 *
 * @author Hullson
 * @date 2025-06-14
 */

@RestController
@RequestMapping("/system/health")
public class HealthController {

    @GetMapping("check")
    public Result check() {
        return ResultUtil.success("connection successful");
    }
}
