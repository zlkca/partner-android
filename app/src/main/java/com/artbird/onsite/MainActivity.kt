package com.artbird.onsite

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.artbird.onsite.ui.MyApp
import com.artbird.onsite.ui.account.AccountViewModel
import com.artbird.onsite.ui.address.AddressViewModel
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.auth.AuthViewModel
import com.artbird.onsite.ui.building.BuildingViewModel
import com.artbird.onsite.ui.client.ProfileViewModel
import com.artbird.onsite.ui.quote.QuoteViewModel
import com.artbird.onsite.ui.project.ProjectViewModel
import com.artbird.onsite.ui.role.RoleViewModel
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.window.WindowViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.io.File


//private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
//
//private fun foregroundPermissionApproved(context: Context): Boolean {
//    val writePermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
//        context, Manifest.permission.WRITE_EXTERNAL_STORAGE
//    )
//    val readPermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
//        context, Manifest.permission.READ_EXTERNAL_STORAGE
//    )
//
//    return writePermissionFlag && readPermissionFlag
//}
//
//private fun requestForegroundPermission(context: Context) {
//    val provideRationale = foregroundPermissionApproved(context = context)
//    if (provideRationale) {
//        ActivityCompat.requestPermissions(
//            context as Activity,
//            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
//            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
//        )
//    } else {
//        ActivityCompat.requestPermissions(
//            context as Activity,
//            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
//            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
//        )
//    }
//}


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val accountViewModel: AccountViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private val addressViewModel: AddressViewModel by viewModels()
    private val roleViewModel: RoleViewModel by viewModels()
    private val clientViewModel: ProfileViewModel by viewModels()
    private val appointmentViewModel: AppointmentViewModel by viewModels()
    private val windowViewModel: WindowViewModel by viewModels()
    private val layoutViewModel: BuildingViewModel by viewModels()
    private val quoteViewModel: QuoteViewModel by viewModels()
    private val projectViewModel: ProjectViewModel by viewModels()

    fun getExternalDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {


            SLTheme() {

                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color(160, 125,45)
                    )
                }


                val configuration = LocalConfiguration.current

                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        MyApp(
                            accountViewModel,
                            authViewModel,
                            addressViewModel,
                            roleViewModel,
                            clientViewModel,
                            appointmentViewModel,
                            windowViewModel,
                            layoutViewModel,
                            quoteViewModel,
                            projectViewModel = projectViewModel,
                            getExternalDirectory()
                        )
                    }
                    else -> {
                        MyApp(
                            accountViewModel,
                            authViewModel,
                            addressViewModel,
                            roleViewModel,
                            clientViewModel,
                            appointmentViewModel,
                            windowViewModel,
                            layoutViewModel,
                            quoteViewModel,
                            projectViewModel = projectViewModel,
                            getExternalDirectory()
                        )
                    }
            }
            }
//            val pdfDir = getDirectory()
//            PdfScreen(LocalContext.current, "myQuote", pdfDir, QuotePdfPage());
        }

    }




}

