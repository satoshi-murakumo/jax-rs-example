package example.controller.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import com.google.inject.Inject;

import example.controller.response.EmployeeListResponse;
import example.model.DataSourceConfig;
import example.model.dao.EmployeeDao;
import example.model.data.Employee;

@Path("employee")
public class EmployeeService {

    private EmployeeDao employeeDao;

    @Inject
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public EmployeeListResponse get() {
        LocalTransaction tx = DataSourceConfig.getLocalTransaction();
        try {
            tx.begin();
            List<Employee> list = employeeDao.selectAll();
            return EmployeeListResponse.from(list);
        } finally {
            tx.rollback();
        }
    }
}
