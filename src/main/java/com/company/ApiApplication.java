package com.company;

import com.company.authentication.AppAuthenticator;
import com.company.model.Account;
import com.company.model.Consumer;
import com.company.resource.ConsumerResource;
import com.company.resource.LoginResource;
import com.company.resource.OrderResource;
import com.company.resource.ProductResource;
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

import java.util.TimeZone;


public class ApiApplication extends Application<ApiConfiguration> {
    private GuiceBundle<ApiConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        guiceBundle = createGuiceBundle(new ApiGuiceModule());

        this.addBundlesToBootstrap(bootstrap);
    }

    // Code smell: long method
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

        jerseyEnv.register(new AuthValueFactoryProvider.Binder<>(Consumer.class));

        jerseyEnv.register(ConsumerResource.class);
        jerseyEnv.register(ProductResource.class);
        jerseyEnv.register(LoginResource.class);
        jerseyEnv.register(OrderResource.class);
    }

    // Refactored code smell:
//    @Override
//    public void run(ApiConfiguration configuration, Environment environment) {
//        AppAuthenticator authenticator = guiceBundle.getInjector().getInstance(AppAuthenticator.class);
//        JerseyEnvironment jerseyEnvironment = environment.jersey();
//
//        this.configureEnvironmentTimeSettings(environment);
//        this.registerAuthenticationClassesToJerseyEnvironment(jerseyEnvironment, authenticator);
//        this.registerResourceClassesToJerseyEnvironment(jerseyEnvironment);
//    }
//
//    private void configureEnvironmentTimeSettings(Environment environment) {
//        // These settings are necessary to comply with ISO 8601
//        environment.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        environment.getObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT+1"));
//    }
//
//    private void registerAuthenticationClassesToJerseyEnvironment(JerseyEnvironment jerseyEnvironment, AppAuthenticator authenticator) {
//        jerseyEnvironment.register(new AuthDynamicFeature(
//                new BasicCredentialAuthFilter.Builder<Account>()
//                        .setAuthenticator(authenticator)
//                        .setRealm("IPRWC")
//                        .buildAuthFilter())
//        );
//
//        jerseyEnvironment.register(new AuthValueFactoryProvider.Binder<>(Consumer.class));
//    }
//
//    private void registerResourceClassesToJerseyEnvironment(JerseyEnvironment jerseyEnvironment) {
//        jerseyEnvironment.register(new AuthValueFactoryProvider.Binder<>(Consumer.class));
//        jerseyEnvironment.register(ConsumerResource.class);
//        jerseyEnvironment.register(ProductResource.class);
//        jerseyEnvironment.register(LoginResource.class);
//        jerseyEnvironment.register(OrderResource.class);
//    }

    private GuiceBundle<ApiConfiguration> createGuiceBundle(Module module) {
        return GuiceBundle
                .defaultBuilder(ApiConfiguration.class)
                .modules(module)
                .build();
    }

    private void addBundlesToBootstrap(Bootstrap<ApiConfiguration> bootstrap) {
        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public String getName() {
        return "IPRWC s1110698";
    }
}