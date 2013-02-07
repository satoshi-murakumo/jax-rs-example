package example.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import example.controller.service.EmployeeService;
import example.controller.service.HelloWorldService;
import example.model.dao.EmployeeDao;
import example.model.dao.EmployeeDaoImpl;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(String.class)
        .annotatedWith(Names.named("opning message"))
        .toInstance("injected message");

        bind(HelloWorldService.class);
        bind(EmployeeService.class);

        bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
    }

}
