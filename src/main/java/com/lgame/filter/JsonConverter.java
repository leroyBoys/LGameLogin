package com.lgame.filter;

import com.lgame.util.json.JsonUtil;
import com.logger.log.SystemLogger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

/**
 * Created by leroy:656515489@qq.com
 * 2017/6/21.
 */
public class JsonConverter extends MappingJackson2HttpMessageConverter {
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        SystemLogger.info(this.getClass(),"======send msg="+ JsonUtil.getJsonFromBean(object));
        super.writeInternal(object, outputMessage);
    }
}
