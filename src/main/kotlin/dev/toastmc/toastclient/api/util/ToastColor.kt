package dev.toastmc.toastclient.api.util

import com.mojang.blaze3d.platform.GlStateManager
import java.awt.Color

class ToastColor : Color {
    constructor(rgb: Int) : super(rgb) {}
    constructor(rgba: Int, alpha: Boolean) : super(rgba, alpha) {}
    constructor(r: Int, g: Int, b: Int) : super(r, g, b) {}
    constructor(r: Int, g: Int, b: Int, a: Int) : super(r, g, b, a) {}
    constructor(color: Color) : super(color.red, color.green, color.blue, color.alpha) {}
    constructor(color: ToastColor, a: Int) : super(color.red, color.green, color.blue, a) {}

    companion object {
        private const val serialVersionUID = 1L
        fun rainbow(): ToastColor {
            val hue = System.currentTimeMillis() % (320 * 32) / (320f * 32)
            return ToastColor(fromHSB(hue, 1f, 1f, 255))
        }

        fun fromHSB(hue: Float, saturation: Float, brightness: Float, alpha: Int): ToastColor {
            val hsbColor = getHSBColor(hue, saturation, brightness)
            return ToastColor(hsbColor.red, hsbColor.green, hsbColor.blue, alpha)
        }
    }

    val hue: Float
        get() = RGBtoHSB(red, green, blue, null)[0]
    val saturation: Float
        get() = RGBtoHSB(red, green, blue, null)[1]
    val brightness: Float
        get() = RGBtoHSB(red, green, blue, null)[2]

    val aBGRPackedInt: Int
        get() {
            var i = -1
            try {
                i = ("0x" + Integer.toHexString(rgb)).toInt()
            } catch (ignored: NumberFormatException) {
            }
            return i
        }

    fun glColor() {
        GlStateManager.color4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)
    }
}