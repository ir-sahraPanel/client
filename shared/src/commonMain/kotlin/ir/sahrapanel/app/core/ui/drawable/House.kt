package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.House: ImageVector
    get() {
        if (_House != null) {
            return _House!!
        }
        _House = ImageVector.Builder(
            name = "House",
            defaultWidth = 576.dp,
            defaultHeight = 512.dp,
            viewportWidth = 576f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(293.3f, 2f)
                curveToRelative(-3f, -2.7f, -7.6f, -2.7f, -10.6f, 0f)
                lineTo(2.7f, 250f)
                curveToRelative(-3.3f, 2.9f, -3.6f, 8f, -0.7f, 11.3f)
                reflectiveCurveToRelative(8f, 3.6f, 11.3f, 0.7f)
                lineTo(64f, 217.1f)
                lineTo(64f, 448f)
                curveToRelative(0f, 35.3f, 28.7f, 64f, 64f, 64f)
                lineToRelative(320f, 0f)
                curveToRelative(35.3f, 0f, 64f, -28.7f, 64f, -64f)
                lineToRelative(0f, -230.9f)
                lineTo(562.7f, 262f)
                curveToRelative(3.3f, 2.9f, 8.4f, 2.6f, 11.3f, -0.7f)
                reflectiveCurveToRelative(2.6f, -8.4f, -0.7f, -11.3f)
                lineTo(293.3f, 2f)
                close()
                moveTo(80f, 448f)
                lineToRelative(0f, -245.1f)
                lineTo(288f, 18.7f)
                lineTo(496f, 202.9f)
                lineTo(496f, 448f)
                curveToRelative(0f, 26.5f, -21.5f, 48f, -48f, 48f)
                lineToRelative(-80f, 0f)
                lineToRelative(0f, -168f)
                curveToRelative(0f, -13.3f, -10.7f, -24f, -24f, -24f)
                lineToRelative(-112f, 0f)
                curveToRelative(-13.3f, 0f, -24f, 10.7f, -24f, 24f)
                lineToRelative(0f, 168f)
                lineToRelative(-80f, 0f)
                curveToRelative(-26.5f, 0f, -48f, -21.5f, -48f, -48f)
                close()
                moveTo(224f, 496f)
                lineToRelative(0f, -168f)
                curveToRelative(0f, -4.4f, 3.6f, -8f, 8f, -8f)
                lineToRelative(112f, 0f)
                curveToRelative(4.4f, 0f, 8f, 3.6f, 8f, 8f)
                lineToRelative(0f, 168f)
                lineToRelative(-128f, 0f)
                close()
            }
        }.build()

        return _House!!
    }

@Suppress("ObjectPropertyName")
private var _House: ImageVector? = null
