package ir.sahrapanel.app.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowCard(
    modifier: Modifier = Modifier,
    title: String? = null, // Optional title
    maxItemsInEachRow: Int ,
    cardPadding: Dp = 0.dp,
    contentPadding: Dp = 16.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
    rowCrossAxisAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.padding(cardPadding),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Only shows the title if it's actually provided
            if (!title.isNullOrEmpty()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            FlowRow(
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = horizontalArrangement,
                verticalArrangement = verticalArrangement,
                itemVerticalAlignment = rowCrossAxisAlignment,
                maxItemsInEachRow = maxItemsInEachRow
            ) {
                content()
            }
        }
    }
}