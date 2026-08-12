package com.example.englishwordsapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.englishwordsapp.databinding.ActivityLearnWordBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalArgumentException("Binding for Activity must be not null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.layoutAnswer3.setOnClickListener {
            markAnswerCorrect(
                binding.layoutAnswer3,
                binding.tvVariantNumber3,
                binding.tvVariantValue3
            )
            showResultMessage(true)
        }
        binding.layoutAnswer1.setOnClickListener {
            markAnswerWrong(
                binding.layoutAnswer1,
                binding.tvVariantNumber1,
                binding.tvVariantValue1
            )
            showResultMessage(false)
        }

        binding.btnContinue.setOnClickListener {
            markAnswerNeutral(
                binding.layoutAnswer1,
                binding.tvVariantNumber1,
                binding.tvVariantValue1
            )
            markAnswerNeutral(
                binding.layoutAnswer3,
                binding.tvVariantNumber3,
                binding.tvVariantValue3
            )
        }


    }

    private fun markAnswerNeutral(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_conteiners
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            )
        )

        tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_rounded_variants,
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textVariantsColor
                )
            )
        }
    }




private fun markAnswerWrong(
    layoutAnswer: LinearLayout,
    tvVariantNumber: TextView,
    tvVariantValue: TextView,
) {
    layoutAnswer.background = ContextCompat.getDrawable(
        this@MainActivity,
        R.drawable.shape_rounded_conteiners_wrong
    )
    tvVariantNumber.background = ContextCompat.getDrawable(
        this@MainActivity,
        R.drawable.shape_rounded_variants_wrong
    )
    tvVariantNumber.setTextColor(
        ContextCompat.getColor(
            this@MainActivity,
            R.color.white
        )
    )
    tvVariantValue.setTextColor(
        ContextCompat.getColor(
            this@MainActivity,
            R.color.wrongAnswerColor
        )
    )
    binding.btnSkip.isVisible = false
    binding.layoutResult.setBackgroundColor(
        ContextCompat.getColor(
            this@MainActivity,
            R.color.wrongAnswerColor
        )
    )

    binding.btnContinue.setTextColor(
        ContextCompat.getColor(
            this@MainActivity,
            R.color.wrongAnswerColor
        )
    )
    binding.ivResultIcon.setImageDrawable(
        ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.ic_wrong
        )
    )
    binding.tvResultMessage.text = resources.getString(R.string.title_wrong)
}

private fun markAnswerCorrect(
    layoutAnswer: LinearLayout,
    tvVariantNumber: TextView,
    tvVariantValue: TextView,
) {
    layoutAnswer.background = ContextCompat.getDrawable(
        this@MainActivity,
        R.drawable.shape_rounded_conteiners_correct
    )
    tvVariantNumber.background = ContextCompat.getDrawable(
        this@MainActivity,
        R.drawable.shape_rounded_variants_correct
    )
    tvVariantNumber.setTextColor(
        ContextCompat.getColor(
            this@MainActivity,
            R.color.white
        )
    )
    tvVariantValue.setTextColor(
        ContextCompat.getColor(
            this@MainActivity,
            R.color.correctAnswerColor
        )
    )
}
    private fun showResultMessage(isCurrect: Boolean){
        val color: Int
        val messageText: String
        val resultIconResourse: Int
        if(isCurrect){
            color = ContextCompat.getColor(this,R.color.correctAnswerColor)
            resultIconResourse = R.drawable.ic_correct
            messageText = "Currect!"
        }else {
            color = ContextCompat.getColor(this,R.color.wrongAnswerColor)
            resultIconResourse = R.drawable.ic_wrong
            messageText = "Wrong!"

        }
        with(binding){
            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResultMessage.text = messageText
            ivResultIcon.setImageResource(resultIconResourse)
        }
    }
}