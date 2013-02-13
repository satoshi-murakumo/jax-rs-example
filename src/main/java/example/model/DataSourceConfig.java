package example.model;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.OracleDialect;
import org.seasar.extension.dbcp.impl.ConnectionPoolImpl;
import org.seasar.extension.dbcp.impl.DataSourceImpl;
import org.seasar.extension.dbcp.impl.XADataSourceImpl;
import org.seasar.extension.jta.TransactionManagerImpl;
import org.seasar.extension.jta.UserTransactionImpl;

public class DataSourceConfig extends DomaAbstractConfig {

    protected static OracleDialect dialect;

    protected static UserTransaction ut;
    protected static TransactionManager tm;
    protected static XADataSourceImpl xads;
    protected static ConnectionPoolImpl pool;
    protected static DataSourceImpl ds;

    static {
        dialect = new OracleDialect();

        tm = new TransactionManagerImpl();
        ut = new UserTransactionImpl(tm);

        xads = new XADataSourceImpl();
        xads.setDriverClassName("oracle.jdbc.OracleDriver");
        xads.setURL("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        xads.setUser("user04");
        xads.setPassword("password");

        pool = new ConnectionPoolImpl();
        pool.setTransactionManager(tm);
        pool.setXADataSource(xads);
        pool.setMaxPoolSize(30);
        pool.setAllowLocalTx(true);

        ds = new DataSourceImpl(pool);
    }


    @Override
    public DataSource getDataSource() {
        return ds;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    public static UserTransaction getTransaction() {
        return ut;
    }
}
