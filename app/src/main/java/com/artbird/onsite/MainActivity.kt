package com.artbird.onsite

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
    private val recordViewModel: ProjectViewModel by viewModels()

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
                    recordViewModel,
                    getExternalDirectory()
                )
            }
//            val pdfDir = getDirectory()
//            PdfScreen(LocalContext.current, "myQuote", pdfDir, QuotePdfPage());
        }

    }




}

