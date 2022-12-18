package uz.gita.fastfooddelivery.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface MyToast{
    fun makeText(text: String)
}

class MyToastImpl(private val context: Context): MyToast{
    override fun makeText(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}