package bbm.config;

import org.jooq.ConnectionProvider;
import org.jooq.ExecuteListenerProvider;
import org.jooq.TransactionProvider;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jooq.JooqProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bbm
 * @date 2020/5/14
 */
@Configuration
public class JooqConfig {

    @Bean
    public Settings getSettings() {
        Settings settings = SettingsTools.defaultSettings();
        settings.withRenderSchema(true);
        settings.setRenderNameStyle(RenderNameStyle.AS_IS);
        settings.withExecuteLogging(true);
        return settings;
    }

    @Bean
    public DefaultConfiguration jooqConfiguration(@Autowired Settings settings,
        @Autowired JooqProperties properties,
        @Autowired ConnectionProvider connection,
        @Autowired TransactionProvider transactionProvider,
        ExecuteListenerProvider[] executeListenerProviders) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        if (properties.getSqlDialect() != null) {
            configuration.set(properties.getSqlDialect());
        }
        configuration.set(connection);
        configuration.set(transactionProvider);
        configuration.set(settings);
        configuration.set(executeListenerProviders);
        return configuration;
    }
}