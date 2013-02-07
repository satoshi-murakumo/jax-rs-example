package example.model.dao;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import example.model.DataSourceConfig;
import example.model.data.Employee;

/**
 */
@Dao(config = DataSourceConfig.class)
public interface EmployeeDao {


    @Select
    List<Employee> selectAll();

    /**
     * @param employeeId
     * @return the Employee entity
     */
    @Select
    Employee selectById(BigDecimal employeeId);

    /**
     * @param employeeId
     * @param versionNo
     * @return the Employee entity
     */
    @Select(ensureResult = true)
    Employee selectByIdAndVersion(BigDecimal employeeId, BigDecimal versionNo);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(Employee entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(Employee entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Employee entity);
}