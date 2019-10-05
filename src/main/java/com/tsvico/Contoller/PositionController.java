package com.tsvico.Contoller;

import com.alibaba.fastjson.JSONObject;
import com.tsvico.Service.PositionService;
import com.tsvico.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/23 16:45
 * 功能
 */
@Controller()
@RequestMapping("admin/page/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public String position(Model model){
        model.addAttribute("positions",positionService.getAll());
        return "admin/page/position";
    }

    @PostMapping(value = "/Delete",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("position_id") String ids){
        JSONObject jsonObject = new JSONObject();
        int id;
        if (ids == null){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","请传入数据");
            return jsonObject.toJSONString();
        }
        try{
            id = Integer.parseInt(ids);
            int res = positionService.deletePosition(id);
            if (res>0){
                jsonObject.put("returnCode",200);
                jsonObject.put("message","删除成功");
            }else {
                jsonObject.put("returnCode",0);
                jsonObject.put("message","内容不存在");
            }
        }catch (Exception e){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","非法字符"+e.getMessage());
            return jsonObject.toJSONString();
        }
        return jsonObject.toJSONString();
    }
    @PostMapping(value = "/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(Position position){
        System.out.println(position);
        JSONObject jsonObject = new JSONObject();
        int result = positionService.updatePosition(position);
        if (result!=0){
            jsonObject.put("returnCode",200);
            jsonObject.put("message","更新成功");
        }else {
            jsonObject.put("returnCode",0);
            jsonObject.put("message","更新失败");
        }
        return jsonObject.toJSONString();
    }
}
