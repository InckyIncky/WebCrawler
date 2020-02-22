package utility.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.io.IOException;

public class CrawlerLogger {
    public static void test() throws IOException {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        AppenderComponentBuilder console = builder.newAppender("stdout", "Console")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);

        console.add(builder.newLayout("PatternLayout").
                addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        builder.add(console);

        AppenderComponentBuilder file = builder.newAppender("log", "File");
        file.addAttribute("filename", "logs/logging.log");

        builder.add(file);
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d [%t] %-5level: %msg%n");

        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?"))
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M"));

        AppenderComponentBuilder rollingFile = builder.newAppender("rolling", "RollingFile")
                .addAttribute("fileName", "rolling.log")
                .addAttribute("filePattern", "rolling-%d{MM-dd-yy}.log.gz")
                .addComponent(triggeringPolicy)
                .addComponent(layoutBuilder);


        builder.add(rollingFile);

        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.ERROR);
        rootLogger.add(builder.newAppenderRef("stdout"));

        builder.add(rootLogger);

        LoggerComponentBuilder logger = builder.newLogger("com", Level.INFO);
        logger.add(builder.newAppenderRef("log")).addAttribute("additivity", true);
        builder.add(logger);

        builder.writeXmlConfiguration(System.out);

        Configurator.initialize(builder.build());
    }
}
