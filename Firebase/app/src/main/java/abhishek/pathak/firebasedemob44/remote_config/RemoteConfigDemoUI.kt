package abhishek.pathak.firebasedemob44.remote_config

import abhishek.pathak.firebasedemob44.remote_config.Constant.FEATURE_ADVERTISEMENT
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

@Composable
fun FeatureToggleSwitch(featureName: String, defaultValue: Boolean) {

    val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    val featureEnabled = remember { mutableStateOf(defaultValue) }

    LaunchedEffect(Unit) {
        firebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    featureEnabled.value =
                        firebaseRemoteConfig.getBoolean(featureName)
                    Log.i(
                        "feature_flag_ad",
                        "$firebaseRemoteConfig.getBoolean(featureName)"
                    )
                }
            }
    }

    Switch(checked = featureEnabled.value,
        onCheckedChange = { newValue ->
            firebaseRemoteConfig.setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(1)
                    .build()
            )

            firebaseRemoteConfig.setDefaultsAsync(mapOf(featureName to newValue))
            firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        featureEnabled.value = firebaseRemoteConfig.getBoolean(featureName)
                    }
                }
        })
}

@Composable
fun FeatureScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Feature Ads")
        FeatureToggleSwitch(featureName = FEATURE_ADVERTISEMENT, defaultValue = false)
    }
}