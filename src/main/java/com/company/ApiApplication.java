package com.company;

import com.company.authentication.AppAuthenticator;
import com.company.model.User;
import com.company.service.AuthenticationService;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Module;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlet.FilterHolder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.TimeZone;


public class ApiApplication extends Application<ApiConfiguration> {
    private final Logger logger = LoggerFactory.getLogger(ApiApplication.class);

    private GuiceBundle<ApiConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception { new ApiApplication().run(args); }

    @Override
    public String getName() { return "IPRWC s1110698"; }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        guiceBundle = createGuiceBundle(new ApiGuiceModule());

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) {
        environment.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        environment.getObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT+1"));
        

//        setupAuthentication(environment);
//        configureClientFilter(environment);
    }

    private GuiceBundle<ApiConfiguration> createGuiceBundle(Module module) {
        return GuiceBundle
                .defaultBuilder(ApiConfiguration.class)
                .modules(module)
                .build();
    }

    private void setupAuthentication(Environment environment) {
        AuthenticationService authenticationService = guiceBundle.getInjector().getInstance(AuthenticationService.class);
        ApiUnauthorizedHandler unauthorizedHandler = guiceBundle.getInjector().getInstance(ApiUnauthorizedHandler.class);
        AppAuthenticator authenticator = guiceBundle.getInjector().getInstance(AppAuthenticator.class);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(authenticator)
                        .setRealm("IPRWC")
                        .buildAuthFilter())
        );

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    private void configureClientFilter(Environment environment) {
        environment.getApplicationContext().addFilter(
                new FilterHolder(new ClientFilter()),
                "/*",
                EnumSet.allOf(DispatcherType.class)
        );
    }
}