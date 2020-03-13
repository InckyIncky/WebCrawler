package utility.propertyManager;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/config.properties"})
public interface MyConfigs extends Config {

//    @DefaultValue("https://www.mann-ivanov-ferber.ru/books/allbooks/?booktype=audiobook")
    @Key("targetURL")
    String targetURL();
    @Key("StorageFile")
    String storageFile();
}
