package utility.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class CrawlerLogger {
    public static void initiateLogger() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        AppenderComponentBuilder console = builder.newAppender("stdout", "Console")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);

        console.add(builder.newLayout("PatternLayout").
                addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        builder.add(console);

        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d [%t] %-5level: %msg%n");

        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?"))
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));

        AppenderComponentBuilder rollingFile = builder.newAppender("rolling", "RollingFile")
                .addAttribute("fileName", "logs/logging.log")
                .addAttribute("filePattern", "rolling-%d{MM-dd-yy}.log.gz")
                .addComponent(triggeringPolicy)
                .addComponent(layoutBuilder);


        builder.add(rollingFile);

        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.ERROR);
        rootLogger.add(builder.newAppenderRef("stdout"));

        builder.add(rootLogger);

        LoggerComponentBuilder logger = builder.newLogger("com", Level.INFO);
        logger.add(builder.newAppenderRef("rolling")).addAttribute("additivity", false);

        builder.add(logger);

        Configurator.initialize(builder.build());
    }
}
