package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.CircleInfo: ImageVector
    get() {
        if (_CircleInfo != null) {
            return _CircleInfo!!
        }
        _CircleInfo = ImageVector.Builder(
            name = "CircleInfo",
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
                moveTo(208f, 352f)
                curveToRelative(-4.4f, 0f, -8f, 3.6f, -8f, 8f)
                reflectiveCurveToRelative(3.6f, 8f, 8f, 8f)
                lineToRelative(96f, 0f)
                curveToRelative(4.4f, 0f, 8f, -3.6f, 8f, -8f)
                reflectiveCurveToRelative(-3.6f, -8f, -8f, -8f)
                lineToRelative(-40f, 0f)
                lineToRelative(0f, -136f)
                curveToRelative(0f, -4.4f, -3.6f, -8f, -8f, -8f)
                lineToRelative(-32f, 0f)
                curveToRelative(-4.4f, 0f, -8f, 3.6f, -8f, 8f)
                reflectiveCurveToRelative(3.6f, 8f, 8f, 8f)
                lineToRelative(24f, 0f)
                lineToRelative(0f, 128f)
                lineToRelative(-40f, 0f)
                close()
                moveTo(256f, 176f)
                arcToRelative(16f, 16f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -32f)
                arcToRelative(16f, 16f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, 32f)
                close()
            }
        }.build()

        return _CircleInfo!!
    }

@Suppress("ObjectPropertyName")
private var _CircleInfo: ImageVector? = null
