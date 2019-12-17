package org.lvgo.octopus.bean;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 这是一个基础bean, 看, 它有一个统一的日志打印管里.. 是理!! 管理管理
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 14:12
 */
public abstract class OctopusBeans implements Serializable {

    private static final long serialVersionUID = 7409285595685160701L;
    protected static Logger log = LoggerFactory.getLogger(OctopusBeans.class);


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
