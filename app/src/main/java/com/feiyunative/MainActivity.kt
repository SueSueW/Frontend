package com.feiyunative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.feiyunative.ui.navigation.AppScaffold
import com.feiyunative.ui.theme.FeiyuNativeTheme
import com.feiyunative.ui.screen.FocusTimelineScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeiyuNativeTheme {
                AppScaffold()
            }
        }
    }
}
