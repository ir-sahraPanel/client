package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.AngleDown: ImageVector
    get() {
        if (_AngleDown != null) {
            return _AngleDown!!
        }
        _AngleDown = ImageVector.Builder(
            name = "AngleDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 448f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(218.3f, 365.7f)
                curveToRelative(3.1f, 3.1f, 8.2f, 3.1f, 11.3f, 0f)
                lineToRelative(176f, -176f)
                curveToRelative(3.1f, -3.1f, 3.1f, -8.2f, 0f, -11.3f)
                reflectiveCurveToRelative(-8.2f, -3.1f, -11.3f, 0f)
                lineTo(224f, 348.7f)
                lineTo(53.7f, 178.3f)
                curveToRelative(-3.1f, -3.1f, -8.2f, -3.1f, -11.3f, 0f)
                reflectiveCurveToRelative(-3.1f, 8.2f, 0f, 11.3f)
                lineToRelative(176f, 176f)
                close()
            }
        }.build()

        return _AngleDown!!
    }

@Suppress("ObjectPropertyName")
private var _AngleDown: ImageVector? = null
