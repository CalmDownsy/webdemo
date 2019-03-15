package com.webdemo.common.contoller;

import com.webdemo.common.doamin.DictDO;
import com.webdemo.common.service.DictService;
import com.webdemo.common.utils.PageUtils;
import com.webdemo.common.utils.Query;
import com.webdemo.common.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/7 10:19
 * @Description:
 */
@Controller
@RequestMapping("/common/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @GetMapping
    // 在UserRealm中对当前登陆用户进行了授权 perms, 根据授权信息判断是否有操作此方法的权限，该方法指定返回页面
    // 与shiro 拦截器中配置 url权限 配合使用
    @RequiresPermissions("common:dict:dict")
    public String dict() {
        return "common/dict/dict";
    }

    @GetMapping("/type")
    @ResponseBody
    public List<DictDO> listType() {
        return dictService.listType();
    }

    @GetMapping("/list")
    @ResponseBody
    @RequiresPermissions("common:dict:dict")
    // url?xx=xx
    public PageUtils list(@RequestParam Map<String ,Object> params) {
        Query query = new Query(params);
        List<DictDO> dictDOS = dictService.list(query);
        int total = dictService.count(query);
        return new PageUtils(dictDOS, total);
    }

    @GetMapping("/add")
    @RequiresPermissions("common:dict:add")
    public String add() {
        return "/common/dict/add";
    }

    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions("common:dict:add")
    public Result save(DictDO dictDO) {
        if (dictService.save(dictDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:dict:batchRemove")
    public Result batchRemove(@RequestParam(value = "ids") Long[] ids) {
        dictService.batchRemove(ids);
        return Result.ok();
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("common:dict:edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        DictDO dictDO = dictService.get(id);
        model.addAttribute("dictDo", dictDO);
        return "/common/dict/edit";
    }

    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("common:dict:edit")
    public Result update() {
        return null;
    }
}