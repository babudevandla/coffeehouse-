package com.coffeehouse;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
		info=@Info(
				description="Coffee House",
				version="V12.0.2",
				title = "Coffee House API",
				 contact = @Contact(
				 name = "Prakash",
				 email = "prakash@ig.com",
				 url = "http://www.ig-group.com"
				 ),
				 license = @License(
				 name = "Apache 2.0",
				 url =
				"http://www.apache.org/licenses/LICENSE-2.0"
				 )
				 ),
				 consumes = {"application/json", "application/xml"},
				 produces = {"application/json", "application/xml"},
				 schemes = {SwaggerDefinition.Scheme.HTTP,
				SwaggerDefinition.Scheme.HTTPS},
				 externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://ig-group.com")
		
		)
public interface UserApiDocumentationConfig {

	
}
