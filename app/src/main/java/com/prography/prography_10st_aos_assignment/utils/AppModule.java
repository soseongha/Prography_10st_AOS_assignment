package com.prography.prography_10st_aos_assignment.utils;

import android.content.Context;

import com.prography.prography_10st_aos_assignment.data.repositoryImpl.LocalRepositoryImpl;
import com.prography.prography_10st_aos_assignment.data.repositoryImpl.UnsplashRepositoryImpl;
import com.prography.prography_10st_aos_assignment.domain.repository.UnsplashRepository;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetBookmarksUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotoUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetPhotosUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.GetRandomPhotoUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.IsBookmarkedUsecase;
import com.prography.prography_10st_aos_assignment.domain.usecase.ToggleBookmarkUsecase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static Context provideContext(@ApplicationContext Context context) {
        return context;
    }

    @Provides
    @Singleton
    public static LocalRepositoryImpl provideLocalRepository(Context context) {
        return new LocalRepositoryImpl(context);
    }

    @Provides
    @Singleton
    public static UnsplashRepositoryImpl provideUnsplashRepository() {
        return new UnsplashRepositoryImpl();
    }

    @Provides
    public static GetBookmarksUsecase provideGetBookmarksUsecase(LocalRepositoryImpl localRepository) {
        return new GetBookmarksUsecase(localRepository);
    }

    @Provides
    public static IsBookmarkedUsecase provideIsBookmarkedUsecase(LocalRepositoryImpl localRepository) {
        return new IsBookmarkedUsecase(localRepository);
    }

    @Provides
    public static ToggleBookmarkUsecase provideToggleBookmarkUsecase(LocalRepositoryImpl localRepository) {
        return new ToggleBookmarkUsecase(localRepository);
    }

    @Provides
    public static GetPhotosUsecase provideGetPhotosUsecase(UnsplashRepositoryImpl unsplashRepository) {
        return new GetPhotosUsecase(unsplashRepository);
    }

    @Provides
    public static GetPhotoUsecase provideGetPhotoUsecase(UnsplashRepositoryImpl unsplashRepository) {
        return new GetPhotoUsecase(unsplashRepository);
    }

    @Provides
    public static GetRandomPhotoUsecase provideGetRandomPhotoUsecase(UnsplashRepositoryImpl unsplashRepository) {
        return new GetRandomPhotoUsecase(unsplashRepository);
    }

}