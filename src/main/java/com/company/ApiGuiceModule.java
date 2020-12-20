package com.company;

import com.company.authentication.AppAuthenticator;
import com.company.persistence.AccountDAO;
import com.company.persistence.OrderDAO;
import com.company.persistence.ProductDAO;
import com.company.persistence.UserDAO;
import com.company.service.AuthenticationService;
import com.company.service.OrderService;
import com.company.service.ProductService;
import com.company.service.UserService;
import com.google.inject.Binder;
import com.google.inject.Provides;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiGuiceModule extends DropwizardAwareModule<ApiConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGuiceModule.class);
    private Jdbi jdbi;

    @Override
    public void configure(Binder binder) {
        LOGGER.info("Log binder registered.");
        final ApiConfiguration config = getConfiguration();
        final Environment environment = getEnvironment();
        final JdbiFactory factory = new JdbiFactory();
        jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
    }

    @Provides
    AppAuthenticator provideAppAuthenticator() { return new AppAuthenticator(provideAuthenticatorService()); }

    @Provides
    AuthenticationService provideAuthenticatorService() { return new AuthenticationService(provideAccountDAO()); }

    @Provides
    AccountDAO provideAccountDAO() { return jdbi.onDemand(AccountDAO.class); }

    @Provides
    UserDAO provideUserDAO() {
        return jdbi.onDemand(UserDAO.class);
    }

    @Provides
    UserService provideUserService() {
        return new UserService(provideUserDAO());
    }

    @Provides
    ProductDAO provideProductDAO() {
        return jdbi.onDemand((ProductDAO.class));
    }

    @Provides
    ProductService provideProductService() {
        return new ProductService(provideProductDAO());
    }

    @Provides
    OrderDAO provideOrderDAO() {
        return jdbi.onDemand(OrderDAO.class);
    }

    @Provides
    OrderService provideOrderService() {
        return new OrderService(provideOrderDAO());
    }
}
