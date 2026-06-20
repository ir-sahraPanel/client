package ir.sahrapanel.app.core.ui.drawable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SahraPanelDrawable.Gear: ImageVector
    get() {
        if (_Gear != null) {
            return _Gear!!
        }
        _Gear = ImageVector.Builder(
            name = "Gear",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(211.6f, 28.5f)
                curveToRelative(1.6f, -7.3f, 8.1f, -12.5f, 15.6f, -12.5f)
                lineToRelative(57.6f, 0f)
                curveToRelative(7.5f, 0f, 14f, 5.2f, 15.6f, 12.5f)
                lineToRelative(12.9f, 57.1f)
                curveToRelative(0.6f, 2.6f, 2.5f, 4.8f, 5f, 5.7f)
                curveToRelative(17.9f, 6.8f, 34.5f, 16.4f, 49.1f, 28.4f)
                curveToRelative(2.1f, 1.7f, 4.9f, 2.2f, 7.4f, 1.4f)
                lineToRelative(56f, -17.4f)
                curveToRelative(7.1f, -2.2f, 14.9f, 0.8f, 18.6f, 7.3f)
                lineTo(478.3f, 161f)
                curveToRelative(3.7f, 6.5f, 2.5f, 14.7f, -3f, 19.8f)
                lineToRelative(-43.1f, 39.8f)
                curveToRelative(-2f, 1.8f, -2.9f, 4.5f, -2.5f, 7.2f)
                curveToRelative(1.5f, 9.2f, 2.3f, 18.7f, 2.3f, 28.4f)
                reflectiveCurveToRelative(-0.8f, 19.1f, -2.3f, 28.4f)
                curveToRelative(-0.4f, 2.6f, 0.5f, 5.3f, 2.5f, 7.2f)
                lineToRelative(43.1f, 39.8f)
                curveToRelative(5.5f, 5.1f, 6.7f, 13.3f, 3f, 19.8f)
                lineTo(449.4f, 401f)
                curveToRelative(-3.7f, 6.5f, -11.5f, 9.5f, -18.6f, 7.3f)
                lineToRelative(-56f, -17.4f)
                curveToRelative(-2.6f, -0.8f, -5.4f, -0.3f, -7.4f, 1.4f)
                curveToRelative(-14.6f, 11.9f, -31.2f, 21.6f, -49.1f, 28.4f)
                curveToRelative(-2.5f, 1f, -4.4f, 3.1f, -5f, 5.7f)
                lineToRelative(-12.9f, 57.1f)
                curveToRelative(-1.6f, 7.3f, -8.1f, 12.5f, -15.6f, 12.5f)
                lineToRelative(-57.6f, 0f)
                curveToRelative(-7.5f, 0f, -14f, -5.2f, -15.6f, -12.5f)
                lineToRelative(-12.9f, -57.1f)
                curveToRelative(-0.6f, -2.6f, -2.5f, -4.8f, -5f, -5.7f)
                curveToRelative(-17.9f, -6.8f, -34.5f, -16.4f, -49.1f, -28.4f)
                curveToRelative(-2.1f, -1.7f, -4.9f, -2.2f, -7.4f, -1.4f)
                lineToRelative(-56f, 17.4f)
                curveToRelative(-7.1f, 2.2f, -14.9f, -0.8f, -18.6f, -7.3f)
                lineTo(33.7f, 351f)
                curveToRelative(-3.7f, -6.5f, -2.5f, -14.7f, 3f, -19.8f)
                lineToRelative(-5.4f, -5.9f)
                lineToRelative(5.4f, 5.9f)
                lineToRelative(43.1f, -39.8f)
                curveToRelative(2f, -1.8f, 2.9f, -4.5f, 2.5f, -7.2f)
                curveTo(80.8f, 275.1f, 80f, 265.7f, 80f, 256f)
                reflectiveCurveToRelative(0.8f, -19.1f, 2.3f, -28.4f)
                curveToRelative(0.4f, -2.6f, -0.5f, -5.3f, -2.5f, -7.2f)
                lineTo(36.7f, 180.7f)
                curveToRelative(-5.5f, -5.1f, -6.7f, -13.3f, -3f, -19.8f)
                lineTo(62.6f, 111f)
                curveToRelative(3.7f, -6.5f, 11.5f, -9.5f, 18.6f, -7.3f)
                lineToRelative(56f, 17.4f)
                curveToRelative(2.6f, 0.8f, 5.4f, 0.3f, 7.4f, -1.4f)
                curveToRelative(14.6f, -12f, 31.2f, -21.6f, 49.1f, -28.4f)
                curveToRelative(2.5f, -1f, 4.4f, -3.1f, 5f, -5.7f)
                lineToRelative(12.9f, -57.1f)
                close()
                moveTo(227.2f, 0f)
                curveToRelative(-15f, 0f, -27.9f, 10.4f, -31.2f, 25f)
                lineTo(184f, 78f)
                curveToRelative(-16.7f, 6.8f, -32.2f, 15.8f, -46.1f, 26.7f)
                lineTo(85.9f, 88.5f)
                curveTo(71.6f, 84f, 56.2f, 90.1f, 48.7f, 103f)
                lineTo(19.9f, 153f)
                curveToRelative(-7.5f, 13f, -5f, 29.4f, 6f, 39.5f)
                lineToRelative(39.9f, 36.9f)
                curveTo(64.6f, 238.1f, 64f, 247f, 64f, 256f)
                reflectiveCurveToRelative(0.6f, 17.9f, 1.8f, 26.6f)
                lineTo(25.9f, 319.5f)
                curveToRelative(-11f, 10.1f, -13.5f, 26.6f, -6f, 39.5f)
                lineTo(48.7f, 409f)
                curveToRelative(7.5f, 13f, 22.9f, 19f, 37.2f, 14.6f)
                lineToRelative(51.9f, -16.2f)
                curveToRelative(14f, 10.9f, 29.5f, 19.9f, 46.1f, 26.7f)
                lineToRelative(12f, 53f)
                curveToRelative(3.3f, 14.6f, 16.3f, 25f, 31.2f, 25f)
                lineToRelative(57.6f, 0f)
                curveToRelative(15f, 0f, 27.9f, -10.4f, 31.2f, -25f)
                lineToRelative(12f, -53f)
                curveToRelative(16.7f, -6.7f, 32.2f, -15.8f, 46.1f, -26.7f)
                lineToRelative(51.9f, 16.2f)
                curveToRelative(14.3f, 4.4f, 29.7f, -1.6f, 37.2f, -14.6f)
                lineTo(492.1f, 359f)
                curveToRelative(7.5f, -13f, 5f, -29.4f, -6f, -39.5f)
                lineToRelative(-39.9f, -36.9f)
                curveToRelative(1.2f, -8.7f, 1.8f, -17.6f, 1.8f, -26.6f)
                reflectiveCurveToRelative(-0.6f, -17.9f, -1.8f, -26.6f)
                lineToRelative(39.9f, -36.9f)
                curveToRelative(11f, -10.1f, 13.5f, -26.6f, 6f, -39.5f)
                lineTo(463.3f, 103f)
                curveToRelative(-7.5f, -13f, -22.9f, -19f, -37.2f, -14.6f)
                lineToRelative(-51.9f, 16.2f)
                curveTo(360.2f, 93.7f, 344.7f, 84.7f, 328f, 78f)
                lineTo(316f, 25f)
                curveToRelative(-3.3f, -14.6f, -16.3f, -25f, -31.2f, -25f)
                lineTo(227.2f, 0f)
                close()
                moveTo(192f, 256f)
                arcToRelative(64f, 64f, 0f, isMoreThanHalf = true, isPositiveArc = true, 128f, 0f)
                arcToRelative(64f, 64f, 0f, isMoreThanHalf = true, isPositiveArc = true, -128f, 0f)
                close()
                moveTo(336f, 256f)
                arcToRelative(80f, 80f, 0f, isMoreThanHalf = true, isPositiveArc = false, -160f, 0f)
                arcToRelative(80f, 80f, 0f, isMoreThanHalf = true, isPositiveArc = false, 160f, 0f)
                close()
            }
        }.build()

        return _Gear!!
    }

@Suppress("ObjectPropertyName")
private var _Gear: ImageVector? = null
