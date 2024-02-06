package com.example.writepractice.view

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.writepractice.R
import com.example.writepractice.databinding.ActivityMainBinding
import com.example.writepractice.viewmodel.LettersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val lettersViewModel: LettersViewModel by viewModels()

    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread.sleep(1000)
        screenSplash.setKeepOnScreenCondition { false }
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        lettersViewModel.lettersModel.observe(this, Observer { currentLetter ->
            binding.tvLetterPainter.text = currentLetter.Letters
        })

    }

    private fun setListeners() {
        binding.btnBlack.setOnClickListener {
            /*Toast.makeText(this, resources.getString(R.string.btnBlackClicked), Toast.LENGTH_SHORT)
                .show()*/
            paintBrush.color = resources.getColor(R.color.black)
            currentColor(paintBrush.color)
        }
        binding.btnBlue.setOnClickListener {
            /*Toast.makeText(this, resources.getString(R.string.btnBlueClicked), Toast.LENGTH_SHORT)
                .show()*/
            paintBrush.color = resources.getColor(R.color.blue)
            currentColor(paintBrush.color)
        }
        binding.btnRed.setOnClickListener {
           /* Toast.makeText(this, resources.getString(R.string.btnRedClicked), Toast.LENGTH_SHORT)
                .show()*/
            paintBrush.color = resources.getColor(R.color.red)
            currentColor(paintBrush.color)
        }
        binding.btnClear.setOnClickListener {
            /*Toast.makeText(this, resources.getString(R.string.btnClearClicked), Toast.LENGTH_SHORT)
                .show()*/
            binding.paintView.pathList.clear()
            binding.paintView.colorList.clear()
            path.reset()
        }
        binding.btnNext.setOnClickListener {
            /*Toast.makeText(this, resources.getString(R.string.btnNextClicked), Toast.LENGTH_SHORT)
                .show()*/
            lettersViewModel.randomLetters()
            binding.paintView.pathList.clear()
            binding.paintView.colorList.clear()
            path.reset()
        }
        binding.btnSelectSize.setOnClickListener {
           /* Toast.makeText(this, resources.getString(R.string.btnBrushSizeClicked), Toast.LENGTH_SHORT)
                .show()*/
            if (!btnSizePressed) {
                binding.rsSelectSize.visibility = View.VISIBLE
                binding.rsSelectSize.animate().alpha(1f).translationY(0f).setDuration(200).start()
                btnSizePressed = true
            } else {
                binding.rsSelectSize.visibility = View.GONE
                btnSizePressed = false
            }
        }
        binding.rsSelectSize.addOnChangeListener { _, value, _ ->
            paintBrush.strokeWidth = value
        }
    }

    private var btnSizePressed = false

    private fun currentColor(color: Int) {
        binding.paintView.currentBrush = color
        path = Path()
    }
}