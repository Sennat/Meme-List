package com.project.memelist.view

import androidx.fragment.app.Fragment
import com.project.memelist.di.DI

// Ever class in kotlin by default is considered final
// "final" keyword it can not be inherited must used "open" keyword to make the class to be inheritable
open class ViewModelFragment : Fragment() {

    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }
}