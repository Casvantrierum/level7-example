package com.example.level7_example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.level7_example.R
import com.example.level7_example.viewmodel.QuizViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getQuiz()

        btCreateQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_CreateQuizFragment)
        }

        viewModel.quiz.observe(viewLifecycleOwner, Observer {
            //make button visible and clickable
            if (!it.answer.isBlank() || !it.answer.isBlank()) {
                btStartQuiz.alpha = 1.0f
                btStartQuiz.isClickable = true

                btStartQuiz.setOnClickListener {
                    findNavController().navigate(R.id.action_HomeFragment_to_QuizFragment)
                }
            }
        })
    }
}
