package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.CircleCheck: ImageVector
    get() {
        if (_CircleCheck != null) {
            return _CircleCheck!!
        }
        _CircleCheck = ImageVector.Builder(
            name = "CircleCheck",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(256f, 16f)
                arcToRelative(240f, 240f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0f, 480f)
                arcToRelative(240f, 240f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0f, -480f)
                close()
                moveTo(256f, 512f)
                arcTo(256f, 256f, 0f, isMoreThanHalf = true, isPositiveArc = false, 256f, 0f)
                arcToRelative(256f, 256f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, 512f)
                close()
                moveTo(357.7f, 197.7f)
                curveToRelative(3.1f, -3.1f, 3.1f, -8.2f, 0f, -11.3f)
                reflectiveCurveToRelative(-8.2f, -3.1f, -11.3f, 0f)
                lineTo(224f, 308.7f)
                lineToRelative(-58.3f, -58.3f)
                curveToRelative(-3.1f, -3.1f, -8.2f, -3.1f, -11.3f, 0f)
                reflectiveCurveToRelative(-3.1f, 8.2f, 0f, 11.3f)
                lineToRelative(64f, 64f)
                curveToRelative(3.1f, 3.1f, 8.2f, 3.1f, 11.3f, 0f)
                lineToRelative(128f, -128f)
                close()
            }
        }.build()

        return _CircleCheck!!
    }

@Suppress("ObjectPropertyName")
private var _CircleCheck: ImageVector? = null
