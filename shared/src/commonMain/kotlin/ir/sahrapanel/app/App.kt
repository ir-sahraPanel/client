@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package ir.sahrapanel.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.sahrapanel.app.core.navigation.AppRoutes
import ir.sahrapanel.app.core.ui.theme.SahraPanelTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
@Preview
fun SahraPanel() {
    SahraPanelTheme {
            AppRoutes()
    }

}


@Composable
fun SupportingPane() {
    val navigator = rememberSupportingPaneScaffoldNavigator()
    val scope = rememberCoroutineScope()
    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("صفحه اصلی برنامه") }, navigationIcon = {
                            // دکمه بازگشت در موبایل وقتی پنل پشتیبان باز است کاربرد دارد
                            if (navigator.canNavigateBack()) {
                                TextButton(onClick = {
                                    scope.launch {
                                        navigator.navigateBack()
                                    }

                                }) {
                                    Text("بازگشت")
                                }
                            }
                        }, actions = {
                            // دکمه تنظیمات برای باز کردن پنل پشتیبان
                            TextButton(onClick = {
                                scope.launch {
                                    navigator.navigateTo(SupportingPaneScaffoldRole.Supporting)
                                }
                            }) {
                                Text("تنظیمات")
                            }
                        })
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(paddingValues)
                            .background(Color(0xFFF0F4F8)), contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("محتوای اصلی (مثلاً چت یا دیتابیس)", fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                scope.launch {
                                    navigator.navigateTo(SupportingPaneScaffoldRole.Supporting)
                                }
                            }) {
                                Text("باز کردن پنل کمکی")
                            }
                        }
                    }
                }
            }
        },
        supportingPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                        .background(Color(0xFFE1BEE7), shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("🛠️ پنل پشتیبان / تنظیمات", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text("در دسکتاپ و تبلت افقی، این بخش رو در کنار صفحه می‌بینی.")
                        Text("در موبایل، این بخش به صورت تمام‌صفحه باز میشه.")

                        Spacer(modifier = Modifier.height(32.dp))

                        // دکمه بستن پنل (بیشتر کاربرد در موبایل)
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                            onClick = {
                                scope.launch {
                                    navigator.navigateBack()
                                }
                            }) {
                            Text("بستن این پنل")
                        }
                    }
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreePaneAdaptiveScreen() {
    // نویگیتور برای مدیریت هوشمند سه پنل
    val navigator = rememberListDetailPaneScaffoldNavigator<String>()
    val scope = rememberCoroutineScope()
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective, value = navigator.scaffoldValue,

        // پنل اول: منو یا لیست اصلی
        listPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color(0xFFECEFF1)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("۱. لیست اصلی", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(16.dp))

                        TextButton(onClick = {
                            scope.launch {
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, "Content")
                            }

                        }) {
                            Text("باز کردن محتوا")
                        }
                    }
                }
            }
        },

        // پنل دوم: محتوای اصلی
        detailPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("۲. جزئیات اصلی") }, navigationIcon = {
                            if (navigator.canNavigateBack()) {

                                // دکمه بازگشت به صورت متنی
                                TextButton(onClick = {
                                    scope.launch {
                                        navigator.navigateBack()
                                    }
                                }) {
                                    Text("بازگشت")
                                }
                            }
                        }, actions = {
                            // دکمه تنظیمات یا پروفایل به صورت متنی در TopBar
                            TextButton(onClick = {
                                scope.launch {
                                    navigator.navigateTo(
                                        ListDetailPaneScaffoldRole.Extra, "Info"
                                    )
                                }

                            }) {
                                Text("اطلاعات بیشتر")
                            }
                        })
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(paddingValues)
                            .background(Color.White), contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("محتوای انتخاب شده در اینجا نمایش داده می‌شود", fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(20.dp))

                            TextButton(
                                colors = ButtonDefaults.textButtonColors(contentColor = Color.Blue),
                                onClick = {
                                    scope.launch {
                                        navigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Extra, "Details"
                                        )
                                    }

                                }) {
                                Text("نمایش پنل سوم (Extra)")
                            }
                        }
                    }
                }
            }
        },

        // پنل سوم: اطلاعات تکمیلی (Extra)
        extraPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                        .background(Color(0xFFFFE0B2), shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("۳. پنل اطلاعات تکمیلی", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(24.dp))

                        Text("این پنل در مانیتورهای بزرگ در سمت راست قرار می‌گیرد.")

                        Spacer(modifier = Modifier.height(16.dp))

                        // دکمه بستن به صورت متنی
                        TextButton(
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.Red),
                            onClick = {
                                scope.launch {
                                    navigator.navigateBack()
                                }

                            }) {
                            Text("بستن این بخش")
                        }
                    }
                }
            }
        })
}