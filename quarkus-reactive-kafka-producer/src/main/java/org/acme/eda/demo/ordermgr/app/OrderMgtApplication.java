package org.acme.eda.demo.ordermgr.app;

import jakarta.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    tags = {
            @Tag(name="eda", description="Event Driven Architecture Examples"),
    },
    info = @Info(
        title="Order Management service API",
        version = "1.0.0",
        contact = @Contact(
            name = "Jerome Boyer",
            url = "http://jeromeboyer.net"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class OrderMgtApplication extends Application{
    
}
