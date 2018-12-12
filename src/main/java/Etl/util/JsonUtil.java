package Etl.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description 用来解析json串的工具类
 * @Author dtygfn
 * @Date: 2018/12/12 14:14
 */
public class JsonUtil {

    public static Map<Integer,Double> getJsonmap(Map<Integer,Double> jsonmap,String json){
       if (StringUtils.isNotEmpty(json)){
           JSONObject jsonObject = JSON.parseObject(json);
           Set<String> strings = jsonObject.keySet();
           Iterator<String> iterator = strings.iterator();
           while(iterator.hasNext()){
               String id= iterator.next();
               System.out.println(id);
               JSONObject value = (JSONObject) jsonObject.get(id);
               Double score = Double.parseDouble(String.valueOf(value.get("score")));
               System.out.println(score);
               jsonmap.put(Integer.parseInt(id),score);
           }

       }
        return jsonmap;
    }
}
