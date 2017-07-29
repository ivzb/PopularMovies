package com.udacity.popularMovies;

import com.facebook.stetho.Stetho;

public class DebugMvpApp extends MvpApp {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
            Stetho
                .newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}