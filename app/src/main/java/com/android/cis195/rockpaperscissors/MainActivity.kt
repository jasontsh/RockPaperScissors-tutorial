package com.android.cis195.rockpaperscissors

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var player_1_choice: Int = 0
    private var player_2_choice: Int = 0

    private var player1_clicked: TextView? = null
    private var player2_clicked: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player_1_choice = 0
        player_2_choice = 0
        val player_1_rock = findViewById(R.id.player_1_rock) as Button
        val player_1_paper = findViewById(R.id.player_1_paper) as Button
        val player_1_scissors = findViewById(R.id.player_1_scissors) as Button
        player1_clicked = findViewById(R.id.player_1_clicked) as TextView

        player_1_rock.setOnClickListener {
            player_1_choice = 1
            player1_clicked!!.setText(R.string.clicked)
        }
        player_1_paper.setOnClickListener {
            player_1_choice = 2
            player1_clicked!!.setText(R.string.clicked)
        }
        player_1_scissors.setOnClickListener {
            player_1_choice = 3
            player1_clicked!!.setText(R.string.clicked)
        }

        val player_2_rock = findViewById(R.id.player_2_rock) as Button
        val player_2_paper = findViewById(R.id.player_2_paper) as Button
        val player_2_scissors = findViewById(R.id.player_2_scissors) as Button
        player2_clicked = findViewById(R.id.player_2_clicked) as TextView

        player_2_rock.setOnClickListener {
            player_2_choice = 1
            player2_clicked!!.setText(R.string.clicked)
        }
        player_2_paper.setOnClickListener {
            player_2_choice = 2
            player2_clicked!!.setText(R.string.clicked)
        }
        player_2_scissors.setOnClickListener {
            player_2_choice = 3
            player2_clicked!!.setText(R.string.clicked)
        }

        val go = findViewById(R.id.evaluate) as Button
        go.setOnClickListener(View.OnClickListener { view ->
            if (player_1_choice == 0 || player_2_choice == 0) {
                return@OnClickListener
            }
            if (player_1_choice == player_2_choice) {
                Toast.makeText(view.context, R.string.tie, Toast.LENGTH_SHORT).show()
            } else if (player_2_choice == 1 && player_1_choice == 3) {
                Toast.makeText(view.context, R.string.player_2_win, Toast.LENGTH_SHORT).show()
            } else if (player_1_choice == 1 && player_2_choice == 3) {
                Toast.makeText(view.context, R.string.player_1_win, Toast.LENGTH_SHORT).show()
            } else if (player_2_choice > player_1_choice) {
                Toast.makeText(view.context, R.string.player_2_win, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, R.string.player_1_win, Toast.LENGTH_SHORT).show()
            }
        })
        go.setOnLongClickListener { view ->
            val intent = Intent(view.context, CheatActivity::class.java)
            intent.putExtra(getString(R.string.player_1), player_1_choice)
            intent.putExtra(getString(R.string.player_2), player_2_choice)
            startActivityForResult(intent, CHEAT_REQUEST_CODE)
            true
        }
    }

    fun reset(v: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.reset_dialog_title)
                .setMessage(R.string.reset_dialog_text)
                .setPositiveButton(R.string.reset_confirm) { dialogInterface, i ->
                    player_1_choice = 0
                    player_2_choice = 0
                    player1_clicked!!.setText(R.string.not_clicked)
                    player2_clicked!!.setText(R.string.not_clicked)
                }
                .setNegativeButton(R.string.reset_cancel, null)
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CHEAT_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            player_2_choice = data.getIntExtra(getString(R.string.player_2), 0)
            if (player_2_choice != 0) {
                player2_clicked!!.setText(R.string.clicked)
            }
        }
    }

    companion object {

        val CHEAT_REQUEST_CODE = 1
    }
}
