package com.android.cis195.rockpaperscissors

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class CheatActivity : AppCompatActivity() {

    private var player_1_choice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        val intent = intent
        player_1_choice = intent.getIntExtra(getString(R.string.player_1), 0)
        val player_2_choice = intent.getIntExtra(getString(R.string.player_2), 0)
        (findViewById(R.id.player_1_choice) as TextView).text = getResultCheat(true, player_1_choice)
        (findViewById(R.id.player_2_choice) as TextView).text = getResultCheat(false, player_2_choice)
    }

    private fun getResultCheat(firstPlayer: Boolean, value: Int): String {
        val builder = StringBuilder()
        builder.append("Player ")
        if (firstPlayer) {
            builder.append("1: ")
        } else {
            builder.append("2: ")
        }
        when (value) {
            0 -> builder.append("not selected")
            1 -> builder.append("Rock")
            2 -> builder.append("Paper")
            3 -> builder.append("Scissors")
            else -> builder.append("no choice")
        }
        return builder.toString()
    }

    private fun winForYou(opponent: Int): Int {
        when (opponent) {
            1 -> return 2
            2 -> return 3
            3 -> return 1
            else -> return 0
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_cheat, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cheat_for_me) {
            val intent = Intent()
            intent.putExtra(getString(R.string.player_2), winForYou(player_1_choice))
            setResult(Activity.RESULT_OK, intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }
}
