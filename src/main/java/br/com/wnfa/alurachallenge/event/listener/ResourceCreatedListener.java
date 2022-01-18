package br.com.wnfa.alurachallenge.event.listener;

import br.com.wnfa.alurachallenge.event.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent>{

	@Override
    public void onApplicationEvent(ResourceCreatedEvent resourceCreatedEvent) {
        HttpServletResponse response = resourceCreatedEvent.getResponse();
        Long id = resourceCreatedEvent.getId();

        adicionarHeaderLocation(response, id);
    }

    private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }
}
