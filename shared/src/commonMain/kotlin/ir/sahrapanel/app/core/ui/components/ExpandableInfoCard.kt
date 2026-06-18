package ir.sahrapanel.app.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ir.sahrapanel.app.core.ui.drawable.AngleDown
import ir.sahrapanel.app.core.ui.drawable.AngleUp
import ir.sahrapanel.app.core.ui.drawable.CircleInfo
import ir.sahrapanel.app.core.ui.drawable.SahraPanelDrawable

@Composable
fun ExpandableInfoCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    icon: Painter? = null,
    maxLinesCollapsed: Int = Int.MAX_VALUE,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {

        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                icon?.let {
                    Icon(
                        painter = it,
                        contentDescription = null
                    )
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        if (expanded)
                            SahraPanelDrawable.AngleUp
                        else
                            SahraPanelDrawable.AngleDown,
                        contentDescription = if (expanded) "Collapse" else "Expand"
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = description,
                        maxLines = if (expanded) Int.MAX_VALUE else maxLinesCollapsed
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = SahraPanelDrawable.CircleInfo,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {

        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                Icon(
                    icon,
                    contentDescription = null
                )


                Text(
                    text = message,
                )

            }
        }
    }
}
