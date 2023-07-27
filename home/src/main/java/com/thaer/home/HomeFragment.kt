package com.thaer.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import arrow.core.Either
import arrow.core.Tuple20
import arrow.core.computations.either
import arrow.core.flatMap
import arrow.fx.coroutines.parZip
import com.thaer.core.MainApplication
import com.thaer.core.binding_utils.BaseBindingFragment
import com.thaer.core.factory.ViewModelFactory
import com.thaer.home.databinding.FragmentHomeBinding
import com.thaer.home.di.DaggerHomeComponent
import com.thaer.home.di.HomeComponent
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {

//    private val homeHiltViewModel: HomeHiltViewModel by viewModels()
    private var component: HomeComponent? = null
    private val viewModel: HomeViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        getHomeComponent()?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClick.setOnClickListener {
            HomeNavigationHandler.getInterface()?.onButtonClicked()
//            mainViewModel.showBottomNav()
        }

//        viewModel.getMarvelHeroes()

//        launchWhenStarted {
//            val heroes = homeHiltViewModel.getMarvelHeroes()
//            Log.d("ThaerOutput", "From hilt: $heroes")
//        }

        launchWhenStarted {
//            viewModel.getMarvelHeroes()
            viewModel.getMarvelHeroesAsEither()
            viewModel.heroesListLiveData.observe(viewLifecycleOwner) {
                Log.d("ThaerOutput", "From hilt: $it")
            }
        }

//        launchWhenStarted {
//            either<Throwable, Triple<String, String, String>> {
//                Triple(first(), second(), third())
//            }.mapLeft {
//                Log.d("ThaerOutput", "$it")
//            }.map {
//                Log.d("ThaerOutput", "All operations done, $it")
//            }
//        }

        launchWhenStarted {
            parZip({ first() }, { second() }) { first, second ->
                Log.d("ThaerOutput", "First $first and second $second")
            }
        }

    }

    private fun getHomeComponent(): HomeComponent? {
        if (component == null) {
            val coreComponent = (requireActivity().applicationContext as MainApplication).coreComponent
            component = DaggerHomeComponent.factory().create(coreComponent)
        }
        return component
    }

}

private suspend fun first(): String {
    delay(1000)
    Log.d("ThaerOutput", "First operation done")
    return "First"
}

private suspend fun second(): String {
    delay(2000)
    Log.d("ThaerOutput", "Second operation done")
    return "Second"
}

private suspend fun third(): String {
    delay(3000)
    Log.d("ThaerOutput", "Third operation done")
    return "Third"
}
