package com.udacity.popularMovies.di.component;

import com.udacity.popularMovies.di.PerService;
import com.udacity.popularMovies.di.module.ServiceModule;
import com.udacity.popularMovies.service.SyncService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
