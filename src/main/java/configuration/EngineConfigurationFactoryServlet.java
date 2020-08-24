/*
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package configuration;

import org.apache.axis.AxisProperties;
import org.apache.axis.ConfigurationException;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.EngineConfigurationFactory;
import org.apache.axis.components.logger.LogFactory;
import org.apache.axis.configuration.EngineConfigurationFactoryDefault;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.server.AxisServer;
import org.apache.axis.utils.ClassUtils;
import org.apache.axis.utils.Messages;
import org.apache.commons.logging.Log;

import javax.servlet.ServletConfig;
import java.io.InputStream;
/**
千万不能删，重写用于实现axis配置
 */
public class EngineConfigurationFactoryServlet
        extends EngineConfigurationFactoryDefault {
    protected static Log log =
            LogFactory.getLog(org.apache.axis.configuration.EngineConfigurationFactoryServlet.class.getName());

    private ServletConfig cfg;
    public static EngineConfigurationFactory newFactory(Object param) {
        return (param instanceof ServletConfig)
                ? new EngineConfigurationFactoryServlet((ServletConfig) param)
                : null;
    }

    protected EngineConfigurationFactoryServlet(ServletConfig conf) {
        super();
        this.cfg = conf;
    }

    public EngineConfiguration getServerEngineConfig() {
        return getServerEngineConfig(cfg);
    }

    private static EngineConfiguration getServerEngineConfig(ServletConfig cfg) {

        // Respect the system property setting for a different config file
        String configFile = cfg.getInitParameter(OPTION_SERVER_CONFIG_FILE);
        if (configFile == null)
            configFile =
                    AxisProperties.getProperty(OPTION_SERVER_CONFIG_FILE);
        if (configFile == null) {
            configFile = SERVER_CONFIG_FILE;
        }

        /*
         * Use the WEB-INF directory
         * (so the config files can't get snooped by a browser)
         */
        String appWebInfPath = "/WEB-INF";
        //由于部署方式变更为jar部署，此处不可以使用改方式获取路径
//        ServletContext ctx = cfg.getServletContext();
//        String realWebInfPath = ctx.getRealPath(appWebInfPath);

        FileProvider config = null;
        String realWebInfPath = org.apache.axis.configuration.EngineConfigurationFactoryServlet.class.getResource(appWebInfPath).getPath();
        InputStream iss = ClassUtils.getResourceAsStream(org.apache.axis.configuration.EngineConfigurationFactoryServlet.class, appWebInfPath+"/" + SERVER_CONFIG_FILE);
        if (iss != null) {
            // FileProvider assumes responsibility for 'is':
            // do NOT call is.close().
            config = new FileProvider(iss);
        }

        if (config == null) {
            log.error(Messages.getMessage("servletEngineWebInfError03", ""));
        }
        if (config == null && realWebInfPath != null) {
            try {
                config = new FileProvider(realWebInfPath, configFile);
            } catch (ConfigurationException e) {
                log.error(Messages.getMessage("servletEngineWebInfError00"), e);
            }
        }
        if (config == null) {
            log.warn(Messages.getMessage("servletEngineWebInfWarn00"));
            try {
                InputStream is =
                        ClassUtils.getResourceAsStream(AxisServer.class,
                                SERVER_CONFIG_FILE);
                config = new FileProvider(is);

            } catch (Exception e) {
                log.error(Messages.getMessage("servletEngineWebInfError02"), e);
            }
        }

        return config;
    }
}
