package com.thaer.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import arrow.core.computations.either
import com.thaer.core.MainApplication
import com.thaer.core.binding_utils.BaseBindingDataInputFragment
import com.thaer.core.viewmodel.MainViewModel
import com.thaer.home.databinding.FragmentHomeBinding
import com.thaer.home.di.DaggerHomeComponent
import com.thaer.home.di.HomeComponent
import kotlinx.coroutines.delay

class HomeFragment : BaseBindingDataInputFragment<FragmentHomeBinding, HomeFragmentInput>() {

//    private val homeHiltViewModel: HomeHiltViewModel by viewModels()
    private var component: HomeComponent? = null
    private val viewModel: HomeViewModel by viewModels { factory }
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        getHomeComponent()?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        arguments?.let {
//            it.let {
//              Log.d(
//                  "ThaerOutput",
//                  (it.getSerializable(HomeFragmentInput().getNavKey()) as HomeFragmentInput).toString()
//              )
//            }
//        }

//        Log.d(
//            "ThaerOutput",
//            getInputData<HomeFragmentInput>().toString()
//        )

        Log.d(
            "ThaerOutput",
            dataInputs.toString()
        )

        binding.btnClick.setOnClickListener {
            mainViewModel.configureSharedData(MainViewModel.SharedData.NavigationSharedMessage("Clicked on the button from home screen"))
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

        launchWhenStarted {
            either<Throwable, Triple<String, String, String>> {
                Triple(first(), second(), third())
            }.mapLeft {
                Log.d("ThaerOutput", "$it")
            }.map {
                Log.d("ThaerOutput", "All operations done, $it")
            }
        }

        childFragmentManager.setFragmentResultListener("requestKey", viewLifecycleOwner) { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            // Do something with the result
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
