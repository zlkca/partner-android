package com.artbird.onsite

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.artbird.onsite.ui.MyApp
import com.artbird.onsite.ui.account.AccountViewModel
import com.artbird.onsite.ui.address.AddressViewModel
import com.artbird.onsite.ui.appointment.AppointmentViewModel
import com.artbird.onsite.ui.auth.AuthViewModel
import com.artbird.onsite.ui.building.BuildingViewModel
import com.artbird.onsite.ui.profile.ProfileViewModel
import com.artbird.onsite.ui.quote.QuoteViewModel
import com.artbird.onsite.ui.project.ProjectViewModel
import com.artbird.onsite.ui.role.RoleViewModel
import com.artbird.onsite.ui.theme.SLTheme
import com.artbird.onsite.ui.window.WindowViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.io.File


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val accountViewModel: AccountViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private val addressViewModel: AddressViewModel by viewModels()
    private val roleViewModel: RoleViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
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

//        val cache = SharedPreferencesHelper(this);

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
//                            cache,
                            accountViewModel,
                            authViewModel,
                            addressViewModel,
                            roleViewModel,
                            profileViewModel,
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
//                            cache,
                            accountViewModel,
                            authViewModel,
                            addressViewModel,
                            roleViewModel,
                            profileViewModel,
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

