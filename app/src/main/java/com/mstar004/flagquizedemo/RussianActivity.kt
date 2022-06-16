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
        flagArrayList.add(Flag("россия", R.drawable.russia))
        flagArrayList.add(Flag("австралия", R.drawable.australia))
        flagArrayList.add(Flag("австрия", R.drawable.austria))
        flagArrayList.add(Flag("азербайджан", R.drawable.azarbeijan))
        flagArrayList.add(Flag("албания", R.drawable.albani))
        flagArrayList.add(Flag("алжир", R.drawable.aljir))
        flagArrayList.add(Flag("андорра", R.drawable.andorra))
        flagArrayList.add(Flag("аргентина", R.drawable.argentina))
        flagArrayList.add(Flag("армения", R.drawable.armenia))
        flagArrayList.add(Flag("афганистан", R.drawable.afgani))
        flagArrayList.add(Flag("багамы", R.drawable.bahamas))
        flagArrayList.add(Flag("бангладеш", R.drawable.bangladesh))
        flagArrayList.add(Flag("бахрейн", R.drawable.bayreyn))
        flagArrayList.add(Flag("барбадос", R.drawable.barbados))
        flagArrayList.add(Flag("белоруссия", R.drawable.belarus))
        flagArrayList.add(Flag("бельгия", R.drawable.belgium))
        flagArrayList.add(Flag("белиз", R.drawable.belize))
        flagArrayList.add(Flag("бенин", R.drawable.benin))
        flagArrayList.add(Flag("бутан", R.drawable.bhutan))
        flagArrayList.add(Flag("болтвтя", R.drawable.bolivia))
        flagArrayList.add(Flag("босния", R.drawable.bosnia))
        flagArrayList.add(Flag("ботсвана", R.drawable.botswana))
        flagArrayList.add(Flag("бразилия", R.drawable.brasil))
        flagArrayList.add(Flag("бруней", R.drawable.brunei))
        flagArrayList.add(Flag("болгария", R.drawable.bulgaria))
        flagArrayList.add(Flag("буркина-фасо", R.drawable.burkina))
        flagArrayList.add(Flag("бурунди", R.drawable.burundu))
        flagArrayList.add(Flag("камбоджа", R.drawable.combodja))
        flagArrayList.add(Flag("камерун", R.drawable.cameron))
        flagArrayList.add(Flag("канада", R.drawable.canada))
        flagArrayList.add(Flag("чад", R.drawable.chad))
        flagArrayList.add(Flag("чили", R.drawable.chili))
        flagArrayList.add(Flag("китай", R.drawable.chine))
        flagArrayList.add(Flag("колумбия", R.drawable.colombia))
        flagArrayList.add(Flag("коморы", R.drawable.comoros))
        flagArrayList.add(Flag("конго", R.drawable.congo))
        flagArrayList.add(Flag("коста-рика", R.drawable.costarica))
        flagArrayList.add(Flag("хорватия", R.drawable.croatia))
        flagArrayList.add(Flag("куба", R.drawable.cuba))
        flagArrayList.add(Flag("кипр", R.drawable.cyprus))
        flagArrayList.add(Flag("дания", R.drawable.denmark))
        flagArrayList.add(Flag("джибути", R.drawable.djibouti))
        flagArrayList.add(Flag("доминика", R.drawable.dominica))
        flagArrayList.add(Flag("доминикана", R.drawable.dominican))
        flagArrayList.add(Flag("эквадор", R.drawable.ecuador))
        flagArrayList.add(Flag("египет", R.drawable.egypt))
        flagArrayList.add(Flag("эритрея", R.drawable.eritrea))
        flagArrayList.add(Flag("эстония", R.drawable.estonia))
        flagArrayList.add(Flag("эсватини", R.drawable.eswatini))
        flagArrayList.add(Flag("эфиопия", R.drawable.ethiopia))
        flagArrayList.add(Flag("фиджи", R.drawable.fiji))
        flagArrayList.add(Flag("финляндия", R.drawable.finland))
        flagArrayList.add(Flag("франчия", R.drawable.france))
        flagArrayList.add(Flag("габон", R.drawable.gabon))
        flagArrayList.add(Flag("гамбия", R.drawable.gambia))
        flagArrayList.add(Flag("грузия", R.drawable.georgia))
        flagArrayList.add(Flag("германия", R.drawable.germany))
        flagArrayList.add(Flag("гана", R.drawable.ghana))
        flagArrayList.add(Flag("греция", R.drawable.greece))
        flagArrayList.add(Flag("гренада", R.drawable.grenada))
        flagArrayList.add(Flag("гватемала", R.drawable.guatemala))
        flagArrayList.add(Flag("гвинея", R.drawable.guinea))
        flagArrayList.add(Flag("гайана", R.drawable.guyana))
        flagArrayList.add(Flag("гаити", R.drawable.haiti))
        flagArrayList.add(Flag("гондурас", R.drawable.honduras))
        flagArrayList.add(Flag("венгрия", R.drawable.hungary))
        flagArrayList.add(Flag("исландия", R.drawable.iceland))
        flagArrayList.add(Flag("индия", R.drawable.india))
        flagArrayList.add(Flag("индонезия", R.drawable.indonesia))
        flagArrayList.add(Flag("иран", R.drawable.iran))
        flagArrayList.add(Flag("ирак", R.drawable.iraq))
        flagArrayList.add(Flag("ирландия", R.drawable.ireland))
        flagArrayList.add(Flag("италия", R.drawable.italy))
        flagArrayList.add(Flag("ямайка", R.drawable.jamaica))
        flagArrayList.add(Flag("япония", R.drawable.japan))
        flagArrayList.add(Flag("иордания", R.drawable.jordan))
        flagArrayList.add(Flag("кения", R.drawable.kenya))
        flagArrayList.add(Flag("кирибати", R.drawable.kiribati))
        flagArrayList.add(Flag("кувейт", R.drawable.kuwait))
        flagArrayList.add(Flag("лаос", R.drawable.laos))
        flagArrayList.add(Flag("латвия", R.drawable.latvia))
        flagArrayList.add(Flag("ливан", R.drawable.lebanon))
        flagArrayList.add(Flag("лесото", R.drawable.lesotho))
        flagArrayList.add(Flag("либерия", R.drawable.liberia))
        flagArrayList.add(Flag("ливия", R.drawable.libya))
        flagArrayList.add(Flag("лихтенштейн", R.drawable.liechtenstein))
        flagArrayList.add(Flag("литва", R.drawable.lithuania))
        flagArrayList.add(Flag("люксембург", R.drawable.luxembourg))
        flagArrayList.add(Flag("мадагаскар", R.drawable.madagascar))
        flagArrayList.add(Flag("малави", R.drawable.malawi))
        flagArrayList.add(Flag("малайзия", R.drawable.malaysia))
        flagArrayList.add(Flag("мальдивы", R.drawable.maldives))
        flagArrayList.add(Flag("мали", R.drawable.mali))
        flagArrayList.add(Flag("мальта", R.drawable.malta))
        flagArrayList.add(Flag("мавритания", R.drawable.mauritania))
        flagArrayList.add(Flag("маврики", R.drawable.mauritius))
        flagArrayList.add(Flag("мексико", R.drawable.mexico))
        flagArrayList.add(Flag("микронезия", R.drawable.micronesia))
        flagArrayList.add(Flag("молдавия", R.drawable.moldova))
        flagArrayList.add(Flag("монако", R.drawable.monaco))
        flagArrayList.add(Flag("монголия", R.drawable.mongolia))
        flagArrayList.add(Flag("марокко", R.drawable.morocco))
        flagArrayList.add(Flag("мизамбик", R.drawable.mozambique))


        flagArrayList.add(Flag("узбекистан", R.drawable.uzbekistan))
        flagArrayList.add(Flag("фаластин", R.drawable.falastin))
        flagArrayList.add(Flag("киргизия", R.drawable.kyrgyzstan))
        flagArrayList.add(Flag("казахстан", R.drawable.kazaghstan))

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
            val str = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ"
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