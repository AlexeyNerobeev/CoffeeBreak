package com.example.cofeebreak.feature_app.presentation.Cafe

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PointF
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cofeebreak.Navigation
import com.example.cofeebreak.R
import com.example.cofeebreak.common.roboto
import com.example.cofeebreak.ui.theme.Theme
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun CafeScreen(navController: NavController, vm: CafeVM = hiltViewModel()) {
    val state = vm.state.value
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val mapObjects = remember { mapView.map.mapObjects.addCollection() }
    var permissionGranted by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = state.isComplete) {
        if(state.isComplete){
            navController.navigate(Navigation.StartupScreen)
        }
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)) {

            DisposableEffect(Unit) {
                MapKitFactory.getInstance().onStart()
                mapView.onStart()

                onDispose {
                    mapView.onStop()
                    MapKitFactory.getInstance().onStop()
                }
            }

            if(!permissionGranted){
                RequestLocationPermission {
                    permissionGranted = true
                }
            }

            LaunchedEffect(permissionGranted) {
                if (permissionGranted) {
                    getCurrentLocation(context) { point ->
                        vm.onEvent(CafeEvent.OnUserLocationReceived(point))
                    }
                }
            }

            LaunchedEffect(state.coffeePoints) {
                mapObjects.clear()

                state.coffeePoints.forEach { point ->
                    val placemark = mapObjects.addPlacemark(point)

                    placemark.setIcon(
                        ImageProvider.fromResource(context, R.drawable.coffee_shop_location)
                    )
                }
            }

            LaunchedEffect(state.userLocation) {
                state.userLocation?.let {
                    val placemark = mapObjects.addPlacemark(it)

                    placemark.setIcon(
                        ImageProvider.fromResource(context, R.drawable.user_location)
                    )

                    mapView.map.move(
                        CameraPosition(state.userLocation, 16f, 0f, 0f)
                    )
                }
            }

            AndroidView(
                factory = { mapView },
                modifier = Modifier.fillMaxSize()
            )
        }
        IconButton(onClick = {

        },
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 26.dp)
                .padding(top = 21.dp)) {
            Icon(painter = painterResource(R.drawable.back_icon),
                contentDescription = null,
                tint = Color.Black)
        }
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        state.userLocation?.let {
                            mapView.map.move(
                                CameraPosition(state.userLocation, 16f, 0f, 0f)
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 35.dp)
                        .padding(end = 30.dp)
                        .align(Alignment.End)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.location_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(Theme.colors.mainColor),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 27.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.choose_a_coffee_break_coffee_shop),
                            color = Color.White,
                            fontFamily = roboto,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 27.dp)
                                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                                .background(Theme.colors.mainBackgroundColor)
                        ) {
                            if(state.load){
                                CircularProgressIndicator(
                                    color = Theme.colors.oppositeColor,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            } else {
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 21.dp)
                                        .padding(horizontal = 30.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Theme.colors.mainColor)
                                            .clickable {
                                                vm.onEvent(CafeEvent.SaveCoffeeShopAddress(state.coffeeShopList[0].address))
                                            }) {
                                        Row(
                                            modifier = Modifier
                                                .padding(13.dp)
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.coffee_shop_icon),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Text(
                                                text = state.coffeeShopList[0].address,
                                                color = Color.White,
                                                fontFamily = roboto,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(600),
                                                modifier = Modifier
                                                    .padding(start = 11.dp)
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                contentAlignment = Alignment.CenterEnd
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.more_icon),
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                            }
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 7.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Theme.colors.mainColor)
                                            .clickable {
                                                vm.onEvent(CafeEvent.SaveCoffeeShopAddress(state.coffeeShopList[1].address))
                                            }) {
                                        Row(
                                            modifier = Modifier
                                                .padding(13.dp)
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.coffee_shop_icon),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Text(
                                                text = state.coffeeShopList[1].address,
                                                color = Color.White,
                                                fontFamily = roboto,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(600),
                                                modifier = Modifier
                                                    .padding(start = 11.dp)
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                contentAlignment = Alignment.CenterEnd
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.more_icon),
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                            }
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 7.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Theme.colors.mainColor)
                                            .clickable {
                                                vm.onEvent(CafeEvent.SaveCoffeeShopAddress(state.coffeeShopList[2].address))
                                            }) {
                                        Row(
                                            modifier = Modifier
                                                .padding(13.dp)
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.coffee_shop_icon),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Text(
                                                text = state.coffeeShopList[2].address,
                                                color = Color.White,
                                                fontFamily = roboto,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(600),
                                                modifier = Modifier
                                                    .padding(start = 11.dp)
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                contentAlignment = Alignment.CenterEnd
                                            ) {
                                                Icon(
                                                    painter = painterResource(R.drawable.more_icon),
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RequestLocationPermission(
    onGranted: () -> Unit
) {
    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                onGranted()
            }
        }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
}

@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    onLocation: (Point) -> Unit
) {
    val client = LocationServices.getFusedLocationProviderClient(context)

    client.getCurrentLocation(
        com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
        null
    ).addOnSuccessListener { location ->
        if (location != null) {
            onLocation(Point(location.latitude, location.longitude))
        }
    }
}
