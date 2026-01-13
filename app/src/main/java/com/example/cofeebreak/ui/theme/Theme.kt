package com.example.cofeebreak.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.graphics.toColorInt
import com.example.cofeebreak.R
import kotlin.Int

private val DarkColorScheme = darkColorScheme(
    primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40, secondary = PurpleGrey40, tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

//data class Theme1(val color: Color = Color.Unspecified)
//
//private val LocalTheme1Provider = staticCompositionLocalOf<Theme1> { Theme1() }
//
//@Composable
//fun Theme1t(
//    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//
//    val thene = if (isSystemInDarkTheme) {
//        Theme1()
//    } else {
//        Theme1()
//    }
//
//    CompositionLocalProvider(
//        LocalTheme1Provider provides thene ,
//        content = content
//    )
//}
//
//// MainActiity // Text(color = Theme11.colorSchene.
//
//object Theme11 {
//    val colorScheme: Theme1
//        @Composable
//        get() = LocalTheme1Provider.current
//}

data class ThemeColors(
    val mainBackgroundColor: Color = Color.White,
    val mainColor: Color = Color(0x14AC46),
    val bottomTextAuth: Color = Color(0xAAAAAA),
    val tfIconsColor: Color = Color(0x147F37),
    val tfColor: Color = Color(0xC1C7D0),
    val placeholderColor: Color = Color(0xA1A1A1),
    val alternativeBlack: Color = Color(0x324A59)
)

private val LocalTheme = staticCompositionLocalOf<ThemeColors> {
    ThemeColors()
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val themeColors = if (isDarkTheme) {
        ThemeColors(
            mainBackgroundColor = Color.Black,
            mainColor = Color(0x14AC46),
            bottomTextAuth = Color(0xAAAAAA),
            tfIconsColor = Color(0x4F7993),
            tfColor = Color(0xC1C7D0),
            placeholderColor = Color(0xA1A1A1),
            alternativeBlack = Color(0x324A59)
        )
    } else {
        ThemeColors(
            mainBackgroundColor = Color.White,
            mainColor = Color(0x14AC46),
            bottomTextAuth = Color(0xAAAAAA),
            tfIconsColor = Color(0x147F37),
            tfColor = Color(0xC1C7D0),
            placeholderColor = Color(0xA1A1A1),
            alternativeBlack = Color(0x324A59)
        )
    }

    CompositionLocalProvider(
        LocalTheme provides themeColors
    ) {
        content()
    }
}

object Theme {
    val colors: ThemeColors
        @Composable get() = LocalTheme.current
}

//<color name="purple_200">#FFBB86FC</color>
//<color name="purple_500">#FF6200EE</color>
//<color name="purple_700">#FF3700B3</color>
//<color name="teal_200">#FF03DAC5</color>
//<color name="teal_700">#FF018786</color>
//<color name="black">#FF000000</color>
//<color name="white">#FFFFFFFF</color>
//<color name="MainColor">#14AC46</color>
//<color name="Gray">#AAAAAA</color>
//<color name="Red">#147F37</color>
//<color name="TfColor">#C1C7D0</color>
//<color name="PlaceholderColor">#A1A1A1</color>
//<color name="AlternativeBlack">#324A59</color>

@Composable
fun CoffeeBreakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}