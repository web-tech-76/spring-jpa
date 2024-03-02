package jpa.basic1.app.configs;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

@Component
public class AppPersistenceUnitInfo implements PersistenceUnitInfo {

    @Override
    public String getPersistenceUnitName() {
        return "postgresql-jpa-all";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {

        /*HikariConfig configuration = new HikariConfig();
        var props = getProperties();
        configuration.setDataSourceProperties(props);
        */

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://postgresdb.c3248emi8l9h.us-east-1.rds.amazonaws.com/postgres");
        //dataSource.setDriverClassName("org.postgres.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("passw0rd");
        return dataSource;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return null;
    }

    @Override
    public List<URL> getJarFileUrls() {
        return null;
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of("jpa.app.customers.Customer");
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        /*var props = new Properties();
        props.put("javax.persistence.jdbc.driver", "org.postgres.Driver");
        props.put("javax.persistence.jdbc.url", "jdbc:postgresql://postgresdb.c3248emi8l9h.us-east-1.rds.amazonaws.com/postgres");
        props.put("javax.persistence.jdbc.user", "postgres");
        props.put("javax.persistence.jdbc.password", "passw0rd");
        props.put("hibernate.show_sql", "true");
*/
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
