package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.AngleUp: ImageVector
    get() {
        if (_AngleUp != null) {
            return _AngleUp!!
        }
        _AngleUp = ImageVector.Builder(
            name = "AngleUp",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 448f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(218.3f, 146.3f)
                curveToRelative(3.1f, -3.1f, 8.2f, -3.1f, 11.3f, 0f)
                lineToRelative(176f, 176f)
                curveToRelative(3.1f, 3.1f, 3.1f, 8.2f, 0f, 11.3f)
                reflectiveCurveToRelative(-8.2f, 3.1f, -11.3f, 0f)
                lineTo(224f, 163.3f)
                lineTo(53.7f, 333.7f)
                curveToRelative(-3.1f, 3.1f, -8.2f, 3.1f, -11.3f, 0f)
                reflectiveCurveToRelative(-3.1f, -8.2f, 0f, -11.3f)
                lineToRelative(176f, -176f)
                close()
            }
        }.build()

        return _AngleUp!!
    }

@Suppress("ObjectPropertyName")
private var _AngleUp: ImageVector? = null
