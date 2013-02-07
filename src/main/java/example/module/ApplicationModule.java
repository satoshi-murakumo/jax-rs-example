package example.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import example.controller.service.HelloWorldService;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(String.class)
        .annotatedWith(Names.named("opning message"))
        .toInstance("injected message");

        bind(HelloWorldService.class);
    }

}
