package com.company;

import com.company.authentication.AppAuthenticator;
import com.company.model.Account;
import com.company.model.User;
import com.company.resource.LoginResource;
import com.company.resource.ProductResource;
import com.company.resource.UserResource;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Module;
import com.hubspot.dropwizard.guicier.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
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

    private GuiceBundle<ApiConfiguration> createGuiceBundle(Module module) {
        return GuiceBundle
                .defaultBuilder(ApiConfiguration.class)
                .modules(module)
                .build();
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) {
        environment.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        environment.getObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT+1"));

        AppAuthenticator authenticator = guiceBundle.getInjector().getInstance(AppAuthenticator.class);

        JerseyEnvironment jerseyEnv = environment.jersey();
        jerseyEnv.register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<Account>()
                        .setAuthenticator(authenticator)
                        .setRealm("IPRWC")
                        .buildAuthFilter())
        );

//        jerseyEnv.register(RolesAllowedDynamicFeature.class);
        jerseyEnv.register(new AuthValueFactoryProvider.Binder<>(User.class));

        jerseyEnv.register(UserResource.class);
        jerseyEnv.register(ProductResource.class);
        jerseyEnv.register(LoginResource.class);
    }

    private void setupCORS(Environment environment) {

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }
}