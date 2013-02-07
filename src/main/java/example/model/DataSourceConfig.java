package example.model;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.SimpleDataSource;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.doma.jdbc.tx.KeepAliveLocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionalDataSource;

public class DataSourceConfig extends DomaAbstractConfig {

    private static final DataSource originalDataSource = createDataSource();

    private static final LocalTransactionalDataSource localTxDataSource = createLocalTxDataSource();

    private static final Dialect dialect = new OracleDialect();

    @Override
    public DataSource getDataSource() {
        return localTxDataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    protected static DataSource createDataSource() {
        try {
            OracleConnectionPoolDataSource oraSource = new OracleConnectionPoolDataSource();

            Properties prop = new Properties();
            prop.setProperty("MinLimit", "5");
            prop.setProperty("MaxLimit", "20");
            prop.setProperty("ConnectionCachingEnabled", "true");

            oraSource.setConnectionProperties(prop);
            oraSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            oraSource.setUser("user04");
            oraSource.setPassword("password");

            return oraSource;
        } catch (SQLException e) {
            e.printStackTrace();

            SimpleDataSource dataSource = new SimpleDataSource();
            dataSource.setUrl("jdbc:oracle:thin:@tnsmaga");
            dataSource.setUser("user01");
            dataSource.setPassword("password");
            return dataSource;
        }
    }

    protected static LocalTransactionalDataSource createLocalTxDataSource() {
        return new LocalTransactionalDataSource(originalDataSource);
    }

    public static LocalTransaction getLocalTransaction() {
        return localTxDataSource.getLocalTransaction(defaultJdbcLogger);
    }

    public static KeepAliveLocalTransaction getKeepAliveLocalTransaction() {
        return localTxDataSource
                .getKeepAliveLocalTransaction(defaultJdbcLogger);
    }

    public static DataSource getOriginalDataSource() {
        return originalDataSource;
    }


}
