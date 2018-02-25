package com.firat.test.questiontwo;

import com.firat.questiontwo.ConfigurationMap;
import com.firat.questiontwo.MyMapper;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyMapperTest {


    @Test
    public void mapperTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Source source = new Source();
        source.setGsm("111");
        source.setName("forat");
        source.setSurname("hederrrr");

        ConfigurationMap<Source,Target> map = new ConfigurationMap<Source, Target>(Target.class,source) {
            @Override
            public void configure(Source source) {
                target.setTelephoneNumber(source.getGsm());
            }
        };
        MyMapper ops = new MyMapper();

        Target target = ops.read(source,Target.class,map);
        assertEquals("111",target.getTelephoneNumber());
        System.out.println(source.toString());
        System.out.println(target.toString());
    }


}

