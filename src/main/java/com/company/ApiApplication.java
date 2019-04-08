package com.company;

import com.company.authentication.AppAuthenticator;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                new BasicCredentialAuthFilter.Builder<User>()
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
}