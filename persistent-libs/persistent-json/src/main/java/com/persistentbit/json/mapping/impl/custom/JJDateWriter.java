package com.persistentbit.json.mapping.impl.custom;


import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.mapping.impl.JJObjectWriter;
import com.persistentbit.json.nodes.JJNode;
import com.persistentbit.json.nodes.JJNodeNull;
import com.persistentbit.json.nodes.JJNodeString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Peter Muys
 * @since 22/10/2015
 */
public class JJDateWriter implements JJObjectWriter
{
    @Override
    public JJNode write(Object value, JJWriter master)
    {
        if(value == null){
            return JJNodeNull.Null;
        }

        String result;
        if(value instanceof Timestamp){
            result = ((Timestamp)value).toInstant().toString();
        } else if(value instanceof java.sql.Date){
           result = ((java.sql.Date)value).toInstant().toString();
        }else if(value instanceof Date){
            result = ((Date)value).toInstant().toString();
        } else if(value instanceof LocalDateTime){
            LocalDateTime ldt = (LocalDateTime)value;
            result = ZonedDateTime.of(ldt, ZoneId.systemDefault()).toInstant().toString();
        } else if(value instanceof ZonedDateTime){
            result = value.toString();
        } else if(value instanceof LocalDate){
            result = ((LocalDate)value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }else{
            throw new RuntimeException("Don't know how to convert to json:" + value.getClass());
        }
        return new JJNodeString(result);
    }
}
