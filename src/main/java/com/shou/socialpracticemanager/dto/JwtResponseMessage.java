package com.shou.socialpracticemanager.dto;


import com.shou.socialpracticemanager.security.handler.ResultEnum;
import java.util.HashMap;
import java.util.Map;

public class JwtResponseMessage  {

    public  static Map<String, Object> LoginSuccess(ResultEnum respCode, String jwtToken) {
        Map<String, Object> map = new HashMap<>();
        map.put("jwtToken",jwtToken);
        map.put("code", respCode.getCode());
        map.put("message", respCode.getMessage());
        return map;
    }

    public  static Map<String, Object> result(ResultEnum respCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", respCode.getCode());
        map.put("message", respCode.getMessage());
        return map;
    }

    public  static Map<String, Object> deny(ResultEnum respCode,String cause) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", respCode.getCode());
        map.put("message", respCode.getMessage());
        map.put("cause", cause);
        return map;
    }

}
