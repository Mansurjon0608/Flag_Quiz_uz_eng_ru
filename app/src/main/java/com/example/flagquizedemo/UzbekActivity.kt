package com.example.flagquizedemo

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
import com.example.flagquizedemo.databinding.ActivityUzbekBinding
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class UzbekActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityUzbekBinding
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

        binding = ActivityUzbekBinding.inflate(layoutInflater)
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
                dialog?.dismiss()
            }

            dialog?.findViewById<CardView>(R.id.btn_english)?.setOnClickListener {
                startActivity(Intent(this, UzbekActivity::class.java))
                Toast.makeText(this, "English is selected", Toast.LENGTH_SHORT).show()
            }

            dialog?.findViewById<CardView>(R.id.btn_russian)?.setOnClickListener {
                startActivity(Intent(this, RussianActivity::class.java))
                Toast.makeText(this, "Выбрано русскый язык", Toast.LENGTH_SHORT).show()
            }
        }

        buttonArrayList = ArrayList()
        handler = Handler()

        linerMatn = findViewById(R.id.lin_1_matn)
        linerBtn1 = findViewById(R.id.lin_2_btn_1)
        linerBtn2 = findViewById(R.id.lin_3_btn_2)
        image = findViewById(R.id.image_1)

        binding.btnShowAnswer.setOnClickListener {
            score--
            nextButton()
            if (binding.tvAnswer.visibility == View.VISIBLE) {
                binding.tvAnswer.visibility = View.GONE
            } else binding.tvAnswer.visibility = View.VISIBLE
            binding.btnShowAnswer.isEnabled = false
            binding.tvScore.text = getString(R.string.text_score_uz) + ": $score"
        }
    }

    override fun onResume() {
        super.onResume()
        createObject()
        btnJoylaCount()

        binding.seekBar.max = flagArrayList.size

        binding.seekBar.progress = count

        binding.btnNext.setOnClickListener {
            getMusic()
            if (count == flagArrayList.size - 1) {
                count = 0
            } else {
                count++
            }
            Toast.makeText(this,
                "${count + 1} - ${getString(R.string.text_level_uz)}",
                Toast.LENGTH_SHORT).show()
            btnJoylaCount()
            score--
            createObject()
            nextButton()

        }
    }

    @SuppressLint("SetTextI18n")
    private fun createObject() {

        binding.tvScore.text = "${R.string.text_score_uz} : $score"
        binding.tvStep.text = "- ${count + 1} -"

        flagArrayList = ArrayList()
        flagArrayList.add(Flag("russia", R.drawable.russia))
        flagArrayList.add(Flag("afghanistan", R.drawable.afgani))
        flagArrayList.add(Flag("albania", R.drawable.albani))
        flagArrayList.add(Flag("algeria", R.drawable.aljir))
        flagArrayList.add(Flag("andorra", R.drawable.andorra))
        flagArrayList.add(Flag("argentina", R.drawable.argentina))
        flagArrayList.add(Flag("armenia", R.drawable.armenia))
        flagArrayList.add(Flag("australia", R.drawable.australia))
        flagArrayList.add(Flag("austria", R.drawable.austria))
        flagArrayList.add(Flag("azerbaijan", R.drawable.azarbeijan))
        flagArrayList.add(Flag("bahamas", R.drawable.bahamas))
        flagArrayList.add(Flag("bahrain", R.drawable.bayreyn))
        flagArrayList.add(Flag("bangladesh", R.drawable.bangladesh))
        flagArrayList.add(Flag("barbados", R.drawable.barbados))
        flagArrayList.add(Flag("belarus", R.drawable.belarus))
        flagArrayList.add(Flag("belgium", R.drawable.belgium))
        flagArrayList.add(Flag("belize", R.drawable.belize))
        flagArrayList.add(Flag("benin", R.drawable.benin))
        flagArrayList.add(Flag("bhutan", R.drawable.bhutan))
        flagArrayList.add(Flag("bolivia", R.drawable.bolivia))
        flagArrayList.add(Flag("bosnia", R.drawable.bosnia))
        flagArrayList.add(Flag("botswana", R.drawable.botswana))
        flagArrayList.add(Flag("brazil", R.drawable.brasil))
        flagArrayList.add(Flag("brunei", R.drawable.brunei))
        flagArrayList.add(Flag("bulgaria", R.drawable.bulgaria))
        flagArrayList.add(Flag("burkina-faso", R.drawable.burkina))
        flagArrayList.add(Flag("burundi", R.drawable.burundu))
        flagArrayList.add(Flag("cambodia", R.drawable.combodja))
        flagArrayList.add(Flag("cameroon", R.drawable.cameron))
        flagArrayList.add(Flag("canada", R.drawable.canada))
        flagArrayList.add(Flag("chad", R.drawable.chad))
        flagArrayList.add(Flag("chili", R.drawable.chili))
        flagArrayList.add(Flag("chine", R.drawable.chine))
        flagArrayList.add(Flag("colombia", R.drawable.colombia))
        flagArrayList.add(Flag("cambodia", R.drawable.combodja))
        flagArrayList.add(Flag("comoros", R.drawable.comoros))
        flagArrayList.add(Flag("congo", R.drawable.congo))
        flagArrayList.add(Flag("costa-rica", R.drawable.costarica))
        flagArrayList.add(Flag("croatia", R.drawable.croatia))
        flagArrayList.add(Flag("cuba", R.drawable.cuba))
        flagArrayList.add(Flag("cyprus", R.drawable.cyprus))
        flagArrayList.add(Flag("denmark", R.drawable.denmark))
        flagArrayList.add(Flag("djibouti", R.drawable.djibouti))
        flagArrayList.add(Flag("dominica", R.drawable.dominica))
        flagArrayList.add(Flag("dominican", R.drawable.dominican))
        flagArrayList.add(Flag("ecuador", R.drawable.ecuador))
        flagArrayList.add(Flag("egypt", R.drawable.egypt))
        flagArrayList.add(Flag("eritrea", R.drawable.eritrea))
        flagArrayList.add(Flag("estonia", R.drawable.estonia))
        flagArrayList.add(Flag("eswatini", R.drawable.eswatini))
        flagArrayList.add(Flag("ethiopia", R.drawable.ethiopia))
        flagArrayList.add(Flag("fiji", R.drawable.fiji))
        flagArrayList.add(Flag("finland", R.drawable.finland))
        flagArrayList.add(Flag("france", R.drawable.france))
        flagArrayList.add(Flag("gabon", R.drawable.gabon))
        flagArrayList.add(Flag("gambia", R.drawable.gambia))
        flagArrayList.add(Flag("georgia", R.drawable.georgia))
        flagArrayList.add(Flag("germany", R.drawable.germany))
        flagArrayList.add(Flag("ghana", R.drawable.ghana))
        flagArrayList.add(Flag("greece", R.drawable.greece))
        flagArrayList.add(Flag("grenada", R.drawable.grenada))
        flagArrayList.add(Flag("guatemala", R.drawable.guatemala))
        flagArrayList.add(Flag("guinea", R.drawable.guinea))
        flagArrayList.add(Flag("guyana", R.drawable.guyana))
        flagArrayList.add(Flag("haiti", R.drawable.haiti))
        flagArrayList.add(Flag("holy-sea", R.drawable.hollysea))
        flagArrayList.add(Flag("honduras", R.drawable.honduras))
        flagArrayList.add(Flag("hungary", R.drawable.hungary))
        flagArrayList.add(Flag("iceland", R.drawable.iceland))
        flagArrayList.add(Flag("india", R.drawable.india))
        flagArrayList.add(Flag("indonesia", R.drawable.indonesia))
        flagArrayList.add(Flag("iran", R.drawable.iran))
        flagArrayList.add(Flag("iraq", R.drawable.iraq))
        flagArrayList.add(Flag("ireland", R.drawable.ireland))
        flagArrayList.add(Flag("italy", R.drawable.italy))
        flagArrayList.add(Flag("jamaica", R.drawable.jamaica))
        flagArrayList.add(Flag("japan", R.drawable.japan))
        flagArrayList.add(Flag("jordan", R.drawable.jordan))
        flagArrayList.add(Flag("kenya", R.drawable.kenya))
        flagArrayList.add(Flag("kiribati", R.drawable.kiribati))
        flagArrayList.add(Flag("kuwait", R.drawable.kuwait))
        flagArrayList.add(Flag("laos", R.drawable.laos))
        flagArrayList.add(Flag("latvia", R.drawable.latvia))
        flagArrayList.add(Flag("lebanon", R.drawable.lebanon))
        flagArrayList.add(Flag("lesotho", R.drawable.lesotho))
        flagArrayList.add(Flag("liberia", R.drawable.liberia))
        flagArrayList.add(Flag("libya", R.drawable.libya))
        flagArrayList.add(Flag("liechtenstein", R.drawable.liechtenstein))
        flagArrayList.add(Flag("lithuania", R.drawable.lithuania))
        flagArrayList.add(Flag("luxembourg", R.drawable.luxembourg))
        flagArrayList.add(Flag("madagascar", R.drawable.madagascar))
        flagArrayList.add(Flag("malawi", R.drawable.malawi))
        flagArrayList.add(Flag("malaysia", R.drawable.malaysia))
        flagArrayList.add(Flag("maldives", R.drawable.maldives))
        flagArrayList.add(Flag("mali", R.drawable.mali))
        flagArrayList.add(Flag("malta", R.drawable.malta))
        flagArrayList.add(Flag("mauritania", R.drawable.mauritania))
        flagArrayList.add(Flag("mauritius", R.drawable.mauritius))
        flagArrayList.add(Flag("mexico", R.drawable.mexico))
        flagArrayList.add(Flag("micronesia", R.drawable.micronesia))
        flagArrayList.add(Flag("moldova", R.drawable.moldova))
        flagArrayList.add(Flag("monaco", R.drawable.monaco))
        flagArrayList.add(Flag("mongolia", R.drawable.mongolia))
        flagArrayList.add(Flag("montenegro", R.drawable.montenegro))
        flagArrayList.add(Flag("morocco", R.drawable.morocco))
        flagArrayList.add(Flag("mozambique", R.drawable.mozambique))

        flagArrayList.add(Flag("north-korea", R.drawable.northkorea))
        flagArrayList.add(Flag("salvador", R.drawable.salvador))

        flagArrayList.add(Flag("palestine", R.drawable.falastin))
        flagArrayList.add(Flag("uzbekistan", R.drawable.uzbekistan))
        flagArrayList.add(Flag("kyrgyzstan", R.drawable.kyrgyzstan))
        flagArrayList.add(Flag("kazakhstan", R.drawable.kazaghstan))

    }

    @SuppressLint("SetTextI18n")
    fun btnJoylaCount() {
        image.setImageResource(flagArrayList[count].image!!)
        binding.tvAnswer.visibility = View.GONE
        linerMatn.removeAllViews()
        linerBtn1.removeAllViews()
        linerBtn2.removeAllViews()
        countryName = ""
        btnJoyla(flagArrayList[count].name)
        quantitiesLetter()
        binding.tvStep.text = "- ${count + 1} -"
        binding.tvAnswer.text = flagArrayList[count].name?.toUpperCase(Locale.ROOT)
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
            val str = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
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
            countryName += button1.text.toString().toUpperCase(Locale.ROOT)
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
        if (countryName == flagArrayList[count].name?.toUpperCase(Locale.ROOT)) {
            Toast.makeText(this, getString(R.string.text_successful_uz), Toast.LENGTH_SHORT).show()
            Toast.makeText(this, getString(R.string.text_got_score_uz), Toast.LENGTH_SHORT).show()
            score++
            binding.winnerAnim.visibility = View.VISIBLE
            binding.winnerAnim.playAnimation()

            handler.postDelayed({
                binding.winnerAnim.visibility = View.GONE
                btnJoylaCount()
                binding.tvScore.text = getString(R.string.text_score_uz) + " : $score"
                nextButton()
            }, 3000)

            levelWinMusic()

            if (count == flagArrayList.size - 1) {
                count = 0
            } else {
                count++
            }
            binding.seekBar.progress = count
        } else {
            if (countryName.length == flagArrayList[count].name?.length) {
                Toast.makeText(this, getString(R.string.text_error_uz), Toast.LENGTH_SHORT).show()
                Toast.makeText(this, getString(R.string.text_lost_score_uz), Toast.LENGTH_SHORT)
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

        binding.tvScore.text = getString(R.string.text_score_uz) + ": $score"
    }

    private fun getMusic() {
        getPointMusic = MediaPlayer.create(this, R.raw.click)
        getPointMusic.start()
    }

    private fun nextButton() {
        if (score == 0) {
            binding.btnNext.isEnabled = false
            binding.btnNext.setImageResource(R.drawable.ic_baseline_navigate_next_disable)
        } else {
            binding.btnNext.isEnabled = true
            binding.btnNext.setImageResource(R.drawable.ic_baseline_navigate_next_24)
        }

        if (score == 0) {
            binding.btnShowAnswer.isEnabled = false
            binding.btnShowAnswer.setImageResource(R.drawable.ic_baseline_info_disable)
        } else {
            binding.btnShowAnswer.isEnabled = true
            binding.btnShowAnswer.setImageResource(R.drawable.ic_baseline_info_24)
        }

    }

    private fun quantitiesLetter() {
        binding.tvQuantities.text = flagArrayList[count].name?.substring(0,
            flagArrayList[count].name?.length!!)?.length.toString()

    }
}