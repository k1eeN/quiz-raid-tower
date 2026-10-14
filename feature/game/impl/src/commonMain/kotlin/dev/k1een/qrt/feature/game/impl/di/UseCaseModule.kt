package dev.k1een.qrt.feature.game.impl.di

import dev.k1een.qrt.feature.game.api.usecase.GetCategoriesUseCase
import dev.k1een.qrt.feature.game.api.usecase.GetQuestionsUseCase
import dev.k1een.qrt.feature.game.impl.domain.usecase.GetCategoriesUseCaseImpl
import dev.k1een.qrt.feature.game.impl.domain.usecase.GetQuestionsUseCaseImpl
import org.koin.dsl.module

val gameUseCaseModule = module {
    factory<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
    factory<GetQuestionsUseCase> { GetQuestionsUseCaseImpl(get()) }
}
