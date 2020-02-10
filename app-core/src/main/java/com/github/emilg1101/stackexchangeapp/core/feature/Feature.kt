package com.github.emilg1101.stackexchangeapp.core.feature

interface Feature<Dependencies> {

    fun inject(dependencies: Dependencies)
}
