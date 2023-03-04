package com.strangerthings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.strangerthings.databinding.ActivityInformationsBinding


class InformationsActivity : AppCompatActivity() {

    val binding by lazy { ActivityInformationsBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}