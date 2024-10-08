package org.acme.eda.demo.ordermgr.infra.api;

import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.acme.eda.demo.ordermgr.domain.OrderService;
import io.quarkus.runtime.StartupEvent;

@Path("/api/v1/version")
@ApplicationScoped
public class VersionResource {
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    @Inject
    @ConfigProperty(name="app.version")
    public String version;

    @GET
    public String getVersion(){
        return "{ \"version\": \"" + version + "\"}";
    }

    void onStart(@Observes StartupEvent ev){
		logger.info(getVersion());
	}
}
