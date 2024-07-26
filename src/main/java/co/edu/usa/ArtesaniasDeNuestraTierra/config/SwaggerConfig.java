package co.edu.usa.ArtesaniasDeNuestraTierra.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
					title = "Artesanias de Nuestra Tierra",
					description = "Proyecto para los artesanos regionales",
					termsOfService = "www.artesanos.com/terminos-condiciones",
					version = "1.0.0",
					contact = @Contact(
								name = "Desarrolladores",
								url  = "www.artesanos.com/contacto",
								email = "devartesanos@gmail.com"
					),
					license = @License(
							name = "Standard Software Use License For Artesanos",
							url  = "#",
							identifier = "123.534.567.3453.23423"
					)					
		),
		servers = {
				@Server(
						description = "DEV SERVER",
						url = "http://localhost:8080"
				),
				@Server(
						description = "PROD SERVER",
						url = "http://artesanos.com:8080"
				)
		}
)
public class SwaggerConfig
{

}
