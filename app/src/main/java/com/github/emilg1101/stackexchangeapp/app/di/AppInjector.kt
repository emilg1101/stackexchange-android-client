package com.github.emilg1101.stackexchangeapp.app.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.emilg1101.stackexcahnge.questiondetails.di.QuestionDetailsFeature
import com.github.emilg1101.stackexcahnge.questiondetails.di.questionDetailsFeature
import com.github.emilg1101.stackexcahnge.questiondetails.ui.question.QuestionDetailsNavigation
import com.github.emilg1101.stackexchangeapp.app.di.module.AuthorizationModule
import com.github.emilg1101.stackexchangeapp.app.di.module.NotificationsModule
import com.github.emilg1101.stackexchangeapp.app.di.module.ProfileDetailsModule
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainComponent
import com.github.emilg1101.stackexchangeapp.authorization.di.AuthorizationComponent
import com.github.emilg1101.stackexchangeapp.data.di.DataComponent
import com.github.emilg1101.stackexchangeapp.domain.repository.AnswersRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.QuestionsRepository
import com.github.emilg1101.stackexchangeapp.domain.repository.TagsRepository
import com.github.emilg1101.stackexchangeapp.notifications.di.NotificationsComponent
import com.github.emilg1101.stackexchangeapp.profiledetails.di.ProfileDetailsComponent
import com.github.emilg1101.stackexchangeapp.questions.di.QuestionsFeature
import com.github.emilg1101.stackexchangeapp.questions.di.questionsFeature
import com.github.emilg1101.stackexchangeapp.questions.ui.QuestionsNavigation

object AppInjector {

    private lateinit var appComponent: AppComponent
    private lateinit var dataComponent: DataComponent
    lateinit var mainComponent: MainComponent

    fun init(application: Application) {
        appComponent = AppComponent.create(application)
        dataComponent = DataComponent.create(appComponent.context)
        mainComponent = MainComponent.create()
        questionsFeature.inject(object : QuestionsFeature.Dependencies {
            override val questionsRepository: QuestionsRepository = dataComponent.repositoryComponent.questionsRepository
            override val tagsRepository: TagsRepository = dataComponent.repositoryComponent.tagsRepository
            override val questionsNavigation: QuestionsNavigation = mainComponent.navigator
        })
        questionDetailsFeature.inject(object : QuestionDetailsFeature.Dependencies {
            override val questionsRepository: QuestionsRepository = dataComponent.repositoryComponent.questionsRepository
            override val answersRepository: AnswersRepository = dataComponent.repositoryComponent.answersRepository
            override val navigation: QuestionDetailsNavigation = mainComponent.navigator
            override val commentsRepository: CommentsRepository = dataComponent.repositoryComponent.commentsRepository
        })
        ProfileDetailsComponent.instance = ProfileDetailsModule()
        AuthorizationComponent.instance = AuthorizationModule(mainComponent)
        NotificationsComponent.instance = NotificationsModule()

        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                // (activity as? AppCompatActivity)?.supportFragmentManager?.fragmentFactory = AppFragmentFactory()
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }
}

inline fun <reified T : Any> inject(noinline factoryProducer: ((AppInjector) -> T)): Lazy<T> {
    return object : Lazy<T> {
        override val value = factoryProducer(AppInjector)

        override fun isInitialized() = true
    }
}

class AppFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (loadFragmentClass(classLoader, className)) {
            // QuestionsSearchFragment::class.java -> QuestionsSearchComponentProvider.provideQuestionSearchFragment()
            else -> return super.instantiate(classLoader, className)
        }
    }
}
