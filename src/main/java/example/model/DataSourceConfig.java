package example.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
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
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/oracle");
            return ds;
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
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
