package io.github.jhipster.application.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Proyecto.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Proyecto.class.getName() + ".tareas", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Cliente.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Cliente.class.getName() + ".contactos", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Contacto.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Contacto.class.getName() + ".tareas", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tarea.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tarea.class.getName() + ".creas", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tarea.class.getName() + ".asignados", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tarea.class.getName() + ".validas", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tarea.class.getName() + ".maestras", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Tarea.class.getName() + ".esperas", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Departamento.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Departamento.class.getName() + ".empleados", jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Perfil.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Empleado.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Empleado.class.getName() + ".perfils", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
