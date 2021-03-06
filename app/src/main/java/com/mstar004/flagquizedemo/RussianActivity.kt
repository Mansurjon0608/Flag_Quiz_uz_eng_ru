package com.mstar004.flagquizedemo

import Models.Flag
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.children
import com.mstar004.flagquizedemo.databinding.ActivityRussianBinding
import java.util.*
import kotlin.collections.ArrayList

class RussianActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityRussianBinding
    lateinit var flagArrayList: ArrayList<Flag>
    var count = 0
    var score = 0
    var countryName = ""
    lateinit var buttonArrayList: ArrayList<Button>
    private lateinit var vibrator: Vibrator
    private lateinit var gameLevelWin: MediaPlayer
    lateinit var linerMatn: LinearLayout
    lateinit var linerBtn1: LinearLayout
    lateinit var linerBtn2: LinearLayout
    lateinit var image: ImageView
    private lateinit var handler: Handler
    private lateinit var getPointMusic: MediaPlayer
    private lateinit var failPointmusic: MediaPlayer
    private var dialog: Dialog? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor = Color.TRANSPARENT
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

        binding = ActivityRussianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnLanguage.setOnClickListener {

            dialog = Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.setContentView(R.layout.language_dialog)
            dialog?.setCanceledOnTouchOutside(true)
            dialog?.setCancelable(true)
            dialog?.show()

            dialog?.findViewById<CardView>(R.id.btn_uzbekLanguage)?.setOnClickListener {
                startActivity(Intent(this, UzbekActivity::class.java))
                Toast.makeText(this, "O'zbek tili tanlandi", Toast.LENGTH_SHORT).show()
            }

            dialog?.findViewById<CardView>(R.id.btn_english)?.setOnClickListener {
                startActivity(Intent(this, UzbekActivity::class.java))
                Toast.makeText(this, "English is selected", Toast.LENGTH_SHORT).show()
            }

            dialog?.findViewById<CardView>(R.id.btn_russian)?.setOnClickListener {
                dialog?.dismiss()
            }
        }


        buttonArrayList = ArrayList()
        handler = Handler()

        linerMatn = findViewById(R.id.lin_1_matn)
        linerBtn1 = findViewById(R.id.lin_2_btn_1)
        linerBtn2 = findViewById(R.id.lin_3_btn_2)
        image = findViewById(R.id.image_1)

        binding.btnShowAnswerR.setOnClickListener {
            score--
            nextButton()
            if (binding.tvAnswerR.visibility == View.VISIBLE) {
                binding.tvAnswerR.visibility = View.GONE
            } else binding.tvAnswerR.visibility = View.VISIBLE
        }
        binding.btnShowAnswerR.isEnabled = false
        binding.tvScore.text = getString(R.string.text_score_ru) + "$score"
    }

    override fun onResume() {
        super.onResume()
        createObject()
        btnJoylaCount()

        binding.btnNextR.setOnClickListener {
            getMusic()
            if (count == flagArrayList.size - 1) {
                count = 0
            } else {
                count++
            }
            Toast.makeText(this,
                "${count + 1} - ${getString(R.string.text_level_ru)}",
                Toast.LENGTH_SHORT).show()
            btnJoylaCount()
            score--
            createObject()
            nextButton()

        }
    }

    @SuppressLint("SetTextI18n")
    private fun createObject() {

        binding.tvScore.text = getString(R.string.text_score_ru) + ": $score"
        binding.tvStep.text = "- ${count + 1} -"

        flagArrayList = ArrayList()
        flagArrayList.add(Flag("????????????", R.drawable.russia))
        flagArrayList.add(Flag("??????????????????", R.drawable.australia))
        flagArrayList.add(Flag("??????????????", R.drawable.austria))
        flagArrayList.add(Flag("??????????????????????", R.drawable.azarbeijan))
        flagArrayList.add(Flag("??????????????", R.drawable.albani))
        flagArrayList.add(Flag("??????????", R.drawable.aljir))
        flagArrayList.add(Flag("??????????????", R.drawable.andorra))
        flagArrayList.add(Flag("??????????????????", R.drawable.argentina))
        flagArrayList.add(Flag("??????????????", R.drawable.armenia))
        flagArrayList.add(Flag("????????????????????", R.drawable.afgani))
        flagArrayList.add(Flag("????????????", R.drawable.bahamas))
        flagArrayList.add(Flag("??????????????????", R.drawable.bangladesh))
        flagArrayList.add(Flag("??????????????", R.drawable.bayreyn))
        flagArrayList.add(Flag("????????????????", R.drawable.barbados))
        flagArrayList.add(Flag("????????????????????", R.drawable.belarus))
        flagArrayList.add(Flag("??????????????", R.drawable.belgium))
        flagArrayList.add(Flag("??????????", R.drawable.belize))
        flagArrayList.add(Flag("??????????", R.drawable.benin))
        flagArrayList.add(Flag("??????????", R.drawable.bhutan))
        flagArrayList.add(Flag("??????????????", R.drawable.bolivia))
        flagArrayList.add(Flag("????????????", R.drawable.bosnia))
        flagArrayList.add(Flag("????????????????", R.drawable.botswana))
        flagArrayList.add(Flag("????????????????", R.drawable.brasil))
        flagArrayList.add(Flag("????????????", R.drawable.brunei))
        flagArrayList.add(Flag("????????????????", R.drawable.bulgaria))
        flagArrayList.add(Flag("??????????????-????????", R.drawable.burkina))
        flagArrayList.add(Flag("??????????????", R.drawable.burundu))
        flagArrayList.add(Flag("????????????????", R.drawable.combodja))
        flagArrayList.add(Flag("??????????????", R.drawable.cameron))
        flagArrayList.add(Flag("????????????", R.drawable.canada))
        flagArrayList.add(Flag("??????", R.drawable.chad))
        flagArrayList.add(Flag("????????", R.drawable.chili))
        flagArrayList.add(Flag("??????????", R.drawable.chine))
        flagArrayList.add(Flag("????????????????", R.drawable.colombia))
        flagArrayList.add(Flag("????????????", R.drawable.comoros))
        flagArrayList.add(Flag("??????????", R.drawable.congo))
        flagArrayList.add(Flag("??????????-????????", R.drawable.costarica))
        flagArrayList.add(Flag("????????????????", R.drawable.croatia))
        flagArrayList.add(Flag("????????", R.drawable.cuba))
        flagArrayList.add(Flag("????????", R.drawable.cyprus))
        flagArrayList.add(Flag("??????????", R.drawable.denmark))
        flagArrayList.add(Flag("??????????????", R.drawable.djibouti))
        flagArrayList.add(Flag("????????????????", R.drawable.dominica))
        flagArrayList.add(Flag("????????????????????", R.drawable.dominican))
        flagArrayList.add(Flag("??????????????", R.drawable.ecuador))
        flagArrayList.add(Flag("????????????", R.drawable.egypt))
        flagArrayList.add(Flag("??????????????", R.drawable.eritrea))
        flagArrayList.add(Flag("??????????????", R.drawable.estonia))
        flagArrayList.add(Flag("????????????????", R.drawable.eswatini))
        flagArrayList.add(Flag("??????????????", R.drawable.ethiopia))
        flagArrayList.add(Flag("??????????", R.drawable.fiji))
        flagArrayList.add(Flag("??????????????????", R.drawable.finland))
        flagArrayList.add(Flag("??????????????", R.drawable.france))
        flagArrayList.add(Flag("??????????", R.drawable.gabon))
        flagArrayList.add(Flag("????????????", R.drawable.gambia))
        flagArrayList.add(Flag("????????????", R.drawable.georgia))
        flagArrayList.add(Flag("????????????????", R.drawable.germany))
        flagArrayList.add(Flag("????????", R.drawable.ghana))
        flagArrayList.add(Flag("????????????", R.drawable.greece))
        flagArrayList.add(Flag("??????????????", R.drawable.grenada))
        flagArrayList.add(Flag("??????????????????", R.drawable.guatemala))
        flagArrayList.add(Flag("????????????", R.drawable.guinea))
        flagArrayList.add(Flag("????????????", R.drawable.guyana))
        flagArrayList.add(Flag("??????????", R.drawable.haiti))
        flagArrayList.add(Flag("????????????????", R.drawable.honduras))
        flagArrayList.add(Flag("??????????????", R.drawable.hungary))
        flagArrayList.add(Flag("????????????????", R.drawable.iceland))
        flagArrayList.add(Flag("??????????", R.drawable.india))
        flagArrayList.add(Flag("??????????????????", R.drawable.indonesia))
        flagArrayList.add(Flag("????????", R.drawable.iran))
        flagArrayList.add(Flag("????????", R.drawable.iraq))
        flagArrayList.add(Flag("????????????????", R.drawable.ireland))
        flagArrayList.add(Flag("????????????", R.drawable.italy))
        flagArrayList.add(Flag("????????????", R.drawable.jamaica))
        flagArrayList.add(Flag("????????????", R.drawable.japan))
        flagArrayList.add(Flag("????????????????", R.drawable.jordan))
        flagArrayList.add(Flag("??????????", R.drawable.kenya))
        flagArrayList.add(Flag("????????????????", R.drawable.kiribati))
        flagArrayList.add(Flag("????????????", R.drawable.kuwait))
        flagArrayList.add(Flag("????????", R.drawable.laos))
        flagArrayList.add(Flag("????????????", R.drawable.latvia))
        flagArrayList.add(Flag("??????????", R.drawable.lebanon))
        flagArrayList.add(Flag("????????????", R.drawable.lesotho))
        flagArrayList.add(Flag("??????????????", R.drawable.liberia))
        flagArrayList.add(Flag("??????????", R.drawable.libya))
        flagArrayList.add(Flag("??????????????????????", R.drawable.liechtenstein))
        flagArrayList.add(Flag("??????????", R.drawable.lithuania))
        flagArrayList.add(Flag("????????????????????", R.drawable.luxembourg))
        flagArrayList.add(Flag("????????????????????", R.drawable.madagascar))
        flagArrayList.add(Flag("????????????", R.drawable.malawi))
        flagArrayList.add(Flag("????????????????", R.drawable.malaysia))
        flagArrayList.add(Flag("????????????????", R.drawable.maldives))
        flagArrayList.add(Flag("????????", R.drawable.mali))
        flagArrayList.add(Flag("????????????", R.drawable.malta))
        flagArrayList.add(Flag("????????????????????", R.drawable.mauritania))
        flagArrayList.add(Flag("??????????????", R.drawable.mauritius))
        flagArrayList.add(Flag("??????????????", R.drawable.mexico))
        flagArrayList.add(Flag("????????????????????", R.drawable.micronesia))
        flagArrayList.add(Flag("????????????????", R.drawable.moldova))
        flagArrayList.add(Flag("????????????", R.drawable.monaco))
        flagArrayList.add(Flag("????????????????", R.drawable.mongolia))
        flagArrayList.add(Flag("??????????????", R.drawable.morocco))
        flagArrayList.add(Flag("????????????????", R.drawable.mozambique))


        flagArrayList.add(Flag("????????????????????", R.drawable.uzbekistan))
        flagArrayList.add(Flag("????????????????", R.drawable.falastin))
        flagArrayList.add(Flag("????????????????", R.drawable.kyrgyzstan))
        flagArrayList.add(Flag("??????????????????", R.drawable.kazaghstan))

    }

    @SuppressLint("SetTextI18n")
    fun btnJoylaCount() {
        image.setImageResource(flagArrayList[count].image!!)
        binding.tvAnswerR.visibility = View.GONE
        linerMatn.removeAllViews()
        linerBtn1.removeAllViews()
        linerBtn2.removeAllViews()
        countryName = ""
        btnJoyla(flagArrayList[count].name)
        quantitiesLetter()
        binding.tvStep.text = "- ${count + 1} -"
        binding.tvAnswerR.text = flagArrayList[count].name?.uppercase(Locale.ROOT)
        nextButton()

    }

    private fun btnJoyla(countryName: String?) {
        val btnArray: ArrayList<Button> = randomBtn(countryName)
        for (i in 0..5) {
            linerBtn1.addView(btnArray[i])
        }
        for (i in 6..11) {
            linerBtn2.addView(btnArray[i])
        }
    }

    private fun randomBtn(countryName: String?): ArrayList<Button> {
        val array = ArrayList<Button>()
        val arrayText = ArrayList<String>()

        for (c in countryName!!) {
            arrayText.add(c.toString())
        }

        if (arrayText.size != 12) {
            val str = "????????????????????????????????????????????????????????????????"
            for (i in arrayText.size until 12) {
                val random = Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }
        arrayText.shuffle()

        for (i in 0 until arrayText.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f
            )
            button.text = arrayText[i]
            button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if (buttonArrayList.contains(button1)) {
            linerMatn.removeView(button1)
            var hasC = false
            linerBtn1.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    countryName = countryName.substring(0, countryName.length - 1)
                    hasC = true
                }
            }
            linerBtn2.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasC) {
                        countryName = countryName.substring(0, countryName.length - 1)
                    }
                }
            }

        } else {
            button1.visibility = View.INVISIBLE
            countryName += button1.text.toString().uppercase(Locale.ROOT)
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArrayList.add(button2)
            linerMatn.addView(button2)
            rightAnswer()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun rightAnswer() {
        if (countryName == flagArrayList[count].name?.uppercase(Locale.ROOT)) {
            Toast.makeText(this, getString(R.string.text_successful_ru), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, getString(R.string.text_got_score_ru), Toast.LENGTH_SHORT).show()
            score++
            binding.winnerAnim.visibility = View.VISIBLE
            binding.winnerAnim.playAnimation()

            handler.postDelayed({
                binding.winnerAnim.visibility = View.GONE
                btnJoylaCount()
                binding.tvScore.text = getString(R.string.text_score_ru) + ": $score"
                nextButton()
            }, 3000)

            levelWinMusic()

            if (count == flagArrayList.size - 1) {
                count = 0
            } else {
                count++
            }

        } else {
            if (countryName.length == flagArrayList[count].name?.length) {
                Toast.makeText(this, getString(R.string.text_error_ru), Toast.LENGTH_SHORT).show()
                Toast.makeText(this, getString(R.string.text_lost_score_ru), Toast.LENGTH_SHORT)
                    .show()
                vibrate()
                failMusic()
                linerMatn.removeAllViews()
                linerBtn2.removeAllViews()
                linerBtn1.removeAllViews()
                btnJoyla(flagArrayList[count].name)
                countryName = ""
                nextButton()

            }
        }
    }

    private fun vibrate() {
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            vibrator.vibrate(500)
        }
    }

    private fun levelWinMusic() {
        gameLevelWin = MediaPlayer.create(this, R.raw.level_win)
        gameLevelWin.start()
    }

    @SuppressLint("SetTextI18n")
    private fun failMusic() {
        failPointmusic = MediaPlayer.create(this, R.raw.fail_point)
        failPointmusic.start()
        if (score > 0) {
            score--
        } else score = 0

        binding.tvScore.text = getString(R.string.text_score) + ": $score"
    }

    private fun getMusic() {
        getPointMusic = MediaPlayer.create(this, R.raw.click)
        getPointMusic.start()
    }

    private fun nextButton() {
        if (score == 0) {
            binding.btnNextR.isEnabled = false
            binding.btnNextR.setImageResource(R.drawable.ic_baseline_navigate_next_disable)
        } else {
            binding.btnNextR.isEnabled = true
            binding.btnNextR.setImageResource(R.drawable.ic_baseline_navigate_next_24)
        }

        if (score == 0) {
            binding.btnShowAnswerR.isEnabled = false
            binding.btnShowAnswerR.setImageResource(R.drawable.ic_baseline_info_disable)
        } else {
            binding.btnShowAnswerR.isEnabled = true
            binding.btnShowAnswerR.setImageResource(R.drawable.ic_baseline_info_24)
        }

    }

    private fun quantitiesLetter() {
        binding.tvQuantities.text = flagArrayList[count].name?.substring(0,
            flagArrayList[count].name?.length!!)?.length.toString()

    }
}