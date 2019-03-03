package com.webdemo.common.contoller;

import com.webdemo.common.utils.ShiroUtils;
import com.webdemo.system.domain.UserDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUserName() {
        return getUser().getUsername();
    }
}
