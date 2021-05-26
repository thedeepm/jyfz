package com.xjy.edu.util;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPacketUtil
{
    public static final String RESULT_KEY_SUCCESS = "success";
    public static final String RESULT_KEY_FAIL = "fail";
    public static final String RESULT_KEY_ERROR = "error";
    public static final String APP_RETURN_SUCCESS_CODE = "1";
    public static final String APP_RETURN_FAIL_CODE = "0";

    public static ResponseData jsonResult(Object data, String msg)
    {
        return new ResponseData(msg, "1", data);
    }

    public static ResponseData jsonResult(long total, List rows) {
        return new ResponseData("success", APP_RETURN_SUCCESS_CODE, new PageData(total, rows));
    }

    public static ResponseData errorJsonResult(String msg) {
        return new ResponseData(msg, APP_RETURN_FAIL_CODE);
    }

    public static ResponseData jsonResult(Object data) {
        return jsonResult(data, null);
    }

    public static ResponseData jsonResult(String msg) {
        return jsonResult(null, msg);
    }

    public static ResponseData jsonSuccessResult() {
        return jsonResult(null, "success");
    }

    public static ResponseData jsonFailResult() {
        return errorJsonResult( "fail");
    }

    public static ResponseData dataGridEmptyResult() {
        return new ResponseData(new PageData(0L, Lists.newArrayList()));
    }

    public static ResponseData dataGridResult(long total, List rows)
    {
        if (rows == null) {
            return dataGridEmptyResult();
        }
        return new ResponseData(new PageData(total, rows));
    }

    public static ResponseData changeDBResult(int dbResult)
    {
        if (dbResult > 0) {
            return new ResponseData(APP_RETURN_SUCCESS_CODE);
        }
        return new ResponseData(APP_RETURN_FAIL_CODE);
    }

    public static ResponseData changeDBResult(boolean dbResult)
    {
        Map<String, Object> result = new HashMap();
        if (dbResult) {
            return new ResponseData("操作成功", APP_RETURN_SUCCESS_CODE);
        }
        return new ResponseData("操作失败", "1");
    }
}

