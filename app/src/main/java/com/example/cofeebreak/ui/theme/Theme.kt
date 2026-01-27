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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext

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
    val oppositeColor: Color = Color.Black,
    val mainColor: Color = Color(0xFF14AC46),
    val bottomTextAuth: Color = Color(0xFFAAAAAA),
    val tfIconsColor: Color = Color(0xFF147F37),
    val tfColor: Color = Color(0xFFC1C7D0),
    val placeholderColor: Color = Color(0xFFA1A1A1),
    val alternativeBlack: Color = Color(0xFF324A59),
    val backIconColor: Color = Color.Black,
    val eyeIconColor: Color = Color.Black,
    val forgotPasswordColor: Color = Color(0xFF147F37),
    val authArrowIconColor: Color = Color.White,
    val signUpTextColor: Color = Color.Black,
    val outlinedTfColor: Color = Color(0xFFB7BBC9),
    val resendInTextColor: Color = Color(0xFF324A59),
    val enteredOutlinedTextFieldColor: Color = Color(0xFFB7BBC9).copy(alpha = 0.5f),
    val menuTopText: Color = Color(0xFFA1A1A1),
    val menuNameColor: Color = Color(0xFF4F7993),
    val menuIconsColor: Color = Color(0xFF001833),
    val menuBoxColor: Color = Color(0xFF272D31),
    val activeBottomBarIcon: Color = Color(0xFF324A59),
    val defaultBottomBarIcon: Color = Color(0xFFD8D8D8),
    val navigationBarBackground: Color = Color.White,
    val backProfileIcon: Color = Color.Black,
    val profileBackgroundIcon: Color = Color(0xFFF7F8FB),
    val titleTextProfile: Color = Color(0x00183338).copy(alpha = 0.22f),
    val profileMainText: Color = Color(0xFF324A59),
    val rewardHistoryColor: Color = Color(0x324A5938).copy(alpha = 0.22f),
    val orderOptionsBoxColor: Color = Color(0xFFD8D8D8).copy(alpha = 0.4f),
    val switchColor: Color = Color(0xFF14AC46),
    val dateBoxColor: Color = Color(0x7676801F).copy(alpha = 0.88f),
    val totalPriceColor: Color = Color(0xFF001833),
    val nextButton: Color = Color(0xFF324A59),
    val activeSlider: Color = Color(0xFF007AFF),
    val inactiveSlider: Color = Color(0x78788033).copy(alpha = 0.2f),
    val baristaShadow: Color = Color(0x5A6CEA12).copy(alpha = 0.7f),
    val baristaBoxBackground: Color = Color.White,
    val designerSelectTypeColor: Color = Color.White,
    val milkSyrupTitle: Color = Color(0x3C3C4399).copy(alpha = 0.6f),
    val selectDesigner: Color = Color(0xFF001833),
    val cancelButton: Color = Color.White,
    val orderBoxBackground: Color = Color(0xFFF7F8FB),
    val optionsColor: Color = Color(0xFF757575),
    val countColor: Color = Color.Black.copy(alpha = 0.57f)
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
            oppositeColor = Color.White,
            mainColor = Color(0xFF14AC46),
            bottomTextAuth = Color(0xFFAAAAAA),
            tfIconsColor = Color(0xFF4F7993),
            tfColor = Color(0xFFC1C7D0),
            placeholderColor = Color(0xFFA1A1A1),
            alternativeBlack = Color(0xFFA1A1A1),
            backIconColor = Color(0xFF4F7993),
            eyeIconColor = Color(0xFFA8A8A8),
            forgotPasswordColor = Color(0xFF324A59),
            authArrowIconColor = Color.Black,
            signUpTextColor = Color(0xFF4F7993),
            outlinedTfColor = Color(0xFF585A62),
            resendInTextColor = Color(0xFFAAAAAA).copy(alpha = 0.5f),
            enteredOutlinedTextFieldColor = Color(0xFF585A62).copy(alpha = 0.5f),
            menuTopText = Color(0xFFD8D8D8),
            menuNameColor = Color(0xFFD9D9D9),
            menuIconsColor = Color(0xFF4F7993),
            menuBoxColor = Color(0xFF334855),
            activeBottomBarIcon = Color(0xFF4F7993),
            defaultBottomBarIcon = Color(0XFFD8D8D8),
            navigationBarBackground = Color(0xFF272D31),
            backProfileIcon = Color(0xFF4F7993),
            profileBackgroundIcon = Color(0xFF444A4D),
            titleTextProfile = Color(0xFF4F7993),
            profileMainText = Color(0xFFAAAAAA),
            rewardHistoryColor = Color(0xFFA1A1A1),
            orderOptionsBoxColor = Color(0xFFD8D8D8),
            switchColor = Color(0xFF34C759),
            dateBoxColor = Color(0x7676801F).copy(alpha = 0.12f),
            totalPriceColor = Color(0xFF61ADDD),
            nextButton = Color(0xFF334855),
            activeSlider = Color(0xFF4F7993),
            inactiveSlider = Color(0x78788033).copy(alpha = 0.2f),
            baristaShadow = Color.Transparent,
            baristaBoxBackground = Color(0xFF334855),
            designerSelectTypeColor = Color(color = 0xFF324A59),
            milkSyrupTitle = Color(0xFFCDECFF),
            selectDesigner = Color(0xFFB5B5B5),
            cancelButton = Color(0xFF334855),
            orderBoxBackground = Color(0xFF334855),
            optionsColor = Color(0xFFA1A1A1),
            countColor = Color(0xFFD9D9D9)
        )
    } else {
        ThemeColors(
            mainBackgroundColor = Color.White,
            oppositeColor = Color.Black,
            mainColor = Color(0xFF14AC46),
            bottomTextAuth = Color(0xFFAAAAAA),
            tfIconsColor = Color(0xFF147F37),
            tfColor = Color(0xFFC1C7D0),
            placeholderColor = Color(0xFFA1A1A1),
            alternativeBlack = Color(0xFF324A59),
            backIconColor = Color.Black,
            eyeIconColor = Color.Black,
            forgotPasswordColor = Color(0xFF147F37),
            authArrowIconColor = Color.White,
            signUpTextColor = Color.Black,
            outlinedTfColor = Color(0xFFB7BBC9),
            resendInTextColor = Color(0xFF324A59),
            enteredOutlinedTextFieldColor = Color(0xFFB7BBC9).copy(alpha = 0.5f),
            menuTopText = Color(0xFFA1A1A1),
            menuNameColor = Color(0xFF4F7993),
            menuIconsColor = Color(0xFF001833),
            menuBoxColor = Color(0xFF272D31),
            activeBottomBarIcon = Color(0xFF324A59),
            defaultBottomBarIcon = Color(0xFFD8D8D8),
            navigationBarBackground = Color.White,
            backProfileIcon = Color.Black,
            profileBackgroundIcon = Color(0xFFF7F8FB),
            titleTextProfile = Color(0x00183338).copy(alpha = 0.22f),
            profileMainText = Color(0xFF324A59),
            rewardHistoryColor = Color(0x324A5938).copy(alpha = 0.22f),
            orderOptionsBoxColor = Color(0xFFD8D8D8).copy(alpha = 0.4f),
            switchColor = Color(0xFF14AC46),
            dateBoxColor = Color(0x7676801F).copy(alpha = 0.12f),
            totalPriceColor = Color(0xFF001833),
            nextButton = Color(0xFF324A59),
            activeSlider = Color(0xFF007AFF),
            inactiveSlider = Color(0x78788033).copy(alpha = 0.2f),
            baristaShadow = Color(0x5A6CEA12).copy(alpha = 0.7f),
            baristaBoxBackground = Color.White,
            designerSelectTypeColor = Color.White,
            milkSyrupTitle = Color(0x3C3C4399).copy(alpha = 0.6f),
            selectDesigner = Color(0xFF001833),
            cancelButton = Color.White,
            orderBoxBackground = Color(0xFFF7F8FB),
            optionsColor = Color(0xFF757575),
            countColor = Color.Black.copy(alpha = 0.57f)
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