package example.controller.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import example.controller.request.EmployeeRequest;
import example.controller.response.EmployeeListResponse;
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
        List<Employee> list = employeeDao.selectAll();
        return EmployeeListResponse.from(list);
    }

    @POST
    @Path("/")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response post(EmployeeRequest request) {
        return Response.ok().build();
    }
}
