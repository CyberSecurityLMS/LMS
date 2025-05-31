package hse.diploma.cybersecplatform.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import hse.diploma.cybersecplatform.R
import hse.diploma.cybersecplatform.di.vm.LocalAuthStateViewModel
import hse.diploma.cybersecplatform.di.vm.LocalViewModelFactory
import hse.diploma.cybersecplatform.navigation.Screen
import hse.diploma.cybersecplatform.ui.components.menu.ProfileMenu
import hse.diploma.cybersecplatform.ui.screens.error.ErrorScreen
import hse.diploma.cybersecplatform.ui.screens.loading.LoadingScreen
import hse.diploma.cybersecplatform.ui.state.ProfileState
import hse.diploma.cybersecplatform.ui.theme.CyberSecPlatformTheme
import hse.diploma.cybersecplatform.ui.theme.Montserrat
import hse.diploma.cybersecplatform.ui.theme.Typography

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val authStateViewModel = LocalAuthStateViewModel.current
    val profileState by profileViewModel.profileState.collectAsState()

    when (profileState) {
        is ProfileState.Loading -> {
            LoadingScreen()
        }
        is ProfileState.Success -> {
            val uiState = (profileState as ProfileState.Success).uiState
            ProfileContent(
                navHostController = navHostController,
                profile = uiState,
                modifier = modifier,
                onLogoutClick = { authStateViewModel.logout() },
            )
        }
        is ProfileState.Error -> {
            val errorType = (profileState as ProfileState.Error).errorType
            ErrorScreen(errorType, onReload = { profileViewModel.loadProfile() })
        }
    }
}

@Composable
private fun ProfileContent(
    navHostController: NavHostController,
    profile: ProfileUiState,
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ElevatedCard(
            modifier =
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            colors =
                CardDefaults.elevatedCardColors(
                    containerColor = colorResource(R.color.dialog_color),
                ),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.welcome_text, profile.userData.fullName),
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colorResource(R.color.button_enabled),
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.email_label, profile.userData.email),
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        color = colorResource(R.color.main_text_color),
                    )
                }
                Spacer(Modifier.width(8.dp))
                Image(
                    painter = painterResource(R.drawable.profile_image),
                    contentDescription = "Illustration",
                    modifier = Modifier.size(width = 136.dp, height = 105.dp),
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_activity),
                contentDescription = "Statistics",
                tint = colorResource(R.color.main_text_color),
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.profile_statistics_section),
                style = Typography.bodyMedium,
            )
        }

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorResource(R.color.profile_content_background)),
        )

        ProfileMenu(
            onTheoryClick = {},
            onSettingsClick = { navHostController.navigate(Screen.Settings.route) },
            onLogoutClick = onLogoutClick,
        )
    }
}

@Composable
private fun ProfileShimmer(modifier: Modifier = Modifier) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ElevatedCard(
            modifier =
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            colors =
                CardDefaults.elevatedCardColors(
                    containerColor = colorResource(R.color.dialog_color),
                ),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "",
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colorResource(R.color.button_enabled),
                            modifier =
                                Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(24.dp)
                                    .placeholder(
                                        true,
                                        highlight =
                                            PlaceholderHighlight.shimmer(),
                                    ),
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "",
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        color = colorResource(R.color.main_text_color),
                        modifier =
                            Modifier
                                .fillMaxWidth(0.5f)
                                .height(18.dp)
                                .placeholder(
                                    true,
                                    highlight =
                                        PlaceholderHighlight.shimmer(),
                                ),
                    )
                    Text(
                        text = "",
                        fontFamily = Montserrat,
                        fontSize = 12.sp,
                        color = colorResource(R.color.main_text_color),
                        modifier =
                            Modifier
                                .fillMaxWidth(0.3f)
                                .height(18.dp)
                                .placeholder(
                                    true,
                                    highlight =
                                        PlaceholderHighlight.shimmer(),
                                ),
                    )
                }
                Spacer(Modifier.width(8.dp))
                Box(
                    Modifier
                        .size(width = 136.dp, height = 105.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .placeholder(
                            true,
                            highlight =
                                PlaceholderHighlight.shimmer(),
                        ),
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_activity),
                contentDescription = "Statistics",
                tint = colorResource(R.color.main_text_color),
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "",
                style = Typography.bodyMedium,
                modifier =
                    Modifier
                        .fillMaxWidth(0.3f)
                        .height(18.dp)
                        .placeholder(
                            true,
                            highlight =
                                PlaceholderHighlight.shimmer(),
                        ),
            )
        }

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(colorResource(R.color.profile_content_background)),
        )

        ProfileMenu(
            onTheoryClick = {},
            onSettingsClick = {},
            onLogoutClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CyberSecPlatformTheme {
        ProfileScreen(
            viewModel(factory = LocalViewModelFactory.current),
            rememberNavController(),
        )
    }
}
