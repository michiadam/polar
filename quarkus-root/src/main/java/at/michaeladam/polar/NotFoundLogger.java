package at.michaeladam.polar;

import jakarta.interceptor.Interceptor;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class NotFoundLogger implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext context) {
        System.out.println(context.getUriInfo());
    }


    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (responseContext.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.print("404 ");
            System.out.println(requestContext.getUriInfo().getAbsolutePath());
            System.out.println(requestContext.getUriInfo().getRequestUri());
        }
    }
}