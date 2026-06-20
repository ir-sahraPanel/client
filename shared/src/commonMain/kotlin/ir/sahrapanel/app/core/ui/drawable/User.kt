package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.User: ImageVector
    get() {
        if (_User != null) {
            return _User!!
        }
        _User = ImageVector.Builder(
            name = "User",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 448f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(336f, 128f)
                arcToRelative(112f, 112f, 0f, isMoreThanHalf = true, isPositiveArc = false, -224f, 0f)
                arcToRelative(112f, 112f, 0f, isMoreThanHalf = true, isPositiveArc = false, 224f, 0f)
                close()
                moveTo(96f, 128f)
                arcToRelative(128f, 128f, 0f, isMoreThanHalf = true, isPositiveArc = true, 256f, 0f)
                arcTo(128f, 128f, 0f, isMoreThanHalf = true, isPositiveArc = true, 96f, 128f)
                close()
                moveTo(16f, 482.3f)
                curveToRelative(0f, 7.6f, 6.1f, 13.7f, 13.7f, 13.7f)
                lineToRelative(388.6f, 0f)
                curveToRelative(7.6f, 0f, 13.7f, -6.1f, 13.7f, -13.7f)
                curveTo(432f, 392.7f, 359.3f, 320f, 269.7f, 320f)
                lineToRelative(-91.4f, 0f)
                curveTo(88.7f, 320f, 16f, 392.7f, 16f, 482.3f)
                close()
                moveTo(0f, 482.3f)
                curveTo(0f, 383.8f, 79.8f, 304f, 178.3f, 304f)
                lineToRelative(91.4f, 0f)
                curveTo(368.2f, 304f, 448f, 383.8f, 448f, 482.3f)
                curveToRelative(0f, 16.4f, -13.3f, 29.7f, -29.7f, 29.7f)
                lineTo(29.7f, 512f)
                curveTo(13.3f, 512f, 0f, 498.7f, 0f, 482.3f)
                close()
            }
        }.build()

        return _User!!
    }

@Suppress("ObjectPropertyName")
private var _User: ImageVector? = null
