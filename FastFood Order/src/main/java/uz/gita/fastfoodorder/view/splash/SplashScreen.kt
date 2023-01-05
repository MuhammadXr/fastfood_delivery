package uz.gita.fastfoodorder.view.splash

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.fastfoodorder.view.splash.viewmodel.SplashViewModel
import uz.gita.fastfoodorder.viewmodel.SplashViewModelImpl

class SplashScreen: AndroidScreen() {
    @Preview
    @Composable
    override fun Content() {

        val viewModel: SplashViewModel = getViewModel<SplashViewModelImpl>()
        SplashScreenContent()
    }
}

@Composable
fun SplashScreenContent(

)
{

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = uz.gita.fastfoodorder.R.drawable.foodicon),
                contentDescription = "Splash Image",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "Event App",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(12.dp)
            )

        }
        Box(modifier = Modifier.fillMaxSize()) {
            val pm: PackageManager =
                LocalView.current.context.applicationContext.packageManager
            val pkgName: String = LocalView.current.context.applicationContext.packageName
            var pkgInfo: PackageInfo? = null
            try {
                pkgInfo = pm.getPackageInfo(pkgName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            val ver = pkgInfo?.versionName

            Text(
                text = "Version $ver",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.BottomCenter)
            )
        }

}