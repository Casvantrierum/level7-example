package com.example.level7_example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.level7_example.R
import com.example.level7_example.viewmodel.QuizViewModel
import kotlinx.android.synthetic.main.fragment_create_quiz.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateQuizFragment : Fragment() {

    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btCreateQuizConfirmAnswer.setOnClickListener {
            viewModel.createQuiz(
                etCreateQuizQuestion.text.toString(),
                etCreateQuizAnswer.text.toString()
            )
        }

        observeQuizCreation()
    }

    private fun observeQuizCreation() {
        viewModel.createSuccess.observe(viewLifecycleOwner, {
            Toast.makeText(activity, R.string.successfully_created_quiz, Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        })

        viewModel.errorText.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
}