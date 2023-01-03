package uz.gita.fastfooddelivery.screen_items

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ImageSelectItem (
    imageUri: MutableState<Uri?>,
    activity: Activity
){
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                imageUri.value = it.data?.data
            }
        )

    Box(
        modifier = Modifier
            .size(100.dp)
            .clickable {
                ImagePicker.with(activity = activity)
                    .crop()
                    .maxResultSize(512, 512, true)
                    .provider(ImageProvider.BOTH)
                    .createIntentFromDialog {
                        launcher.launch(it)
                    }
            }
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xFFCECECE)),
    ) {
        if (imageUri.value != null) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                imageModel = { imageUri.value },
            )
        } else {
            Text(text = "Choose image", modifier = Modifier.align(Alignment.Center))
        }

    }
}