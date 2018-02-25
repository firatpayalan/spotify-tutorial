package com.firat.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * loads application property file at boot-time(app.properties).
 * according to maven -Dparameter,
 * service-*.properties file will be loaded.
 * Example - 1;
 * maven clean install -Pservice-a
 * loads myapp.properties into app.properties
 * Example - 2;
 * maven clean install -Pservice-b
 * loads service-b.properties into app.properties
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PropertyReader {
//    @Log
//    Logger logger;

    private Properties properties;

    @PostConstruct
    private void init() throws IOException {
        InputStream stream = null;
        try {
            properties = new Properties();
            stream = getClass().getClassLoader().getResourceAsStream(AppConstants.NAME_PROPERTYFILE);
            properties.load(stream);
        }
        catch (Exception e)
        {
//TODO            logger.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (null != null)
                stream.close();
        }

    }

    public Properties getProperties() {
        return properties;
    }
}