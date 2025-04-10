package hse.diploma.cybersecplatform.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import hse.diploma.cybersecplatform.model.VulnerabilityType
import hse.diploma.cybersecplatform.ui.components.SearchBar
import hse.diploma.cybersecplatform.ui.components.cards.VulnerabilityCard
import hse.diploma.cybersecplatform.ui.navigation.Screen
import hse.diploma.cybersecplatform.ui.theme.CyberSecPlatformTheme
import hse.diploma.cybersecplatform.utils.mock.mockVulnerabilityItems

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        SearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            modifier = Modifier.background(Color.White),
        )
        // TODO: replace with real data
        VulnerabilitiesContent(mockVulnerabilityItems, navController)
    }
}

@Composable
fun VulnerabilitiesContent(
    items: List<Pair<VulnerabilityType, Int>>,
    navController: NavHostController,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(4.dp))
        }

        items(items) { item ->
            VulnerabilityCard(
                type = item.first,
                tasksCount = item.second,
                onClick = { navController.navigate(Screen.TaskScreen.createRoute(item.first)) },
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CyberSecPlatformTheme {
        HomeScreen(navController = rememberNavController())
    }
}
