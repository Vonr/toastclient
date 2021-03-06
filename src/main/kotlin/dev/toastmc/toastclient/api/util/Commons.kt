package dev.toastmc.toastclient.api.util

import dev.toastmc.toastclient.api.util.font.FontAccessor
import dev.toastmc.toastclient.impl.module.client.Font
import dev.toastmc.toastclient.mixin.client.IChatHud
import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText
import net.minecraft.text.MutableText
import net.minecraft.text.Text

val mc: MinecraftClient = MinecraftClient.getInstance()

fun lit(string: String): MutableText {
    return LiteralText(string)
}

fun message(text: Text) {
    if (mc.player != null) (mc.inGameHud.chatHud as IChatHud).callAddMessage(text, 5932)
}

fun message(str: String) {
    message(lit(str))
}

fun capitalize(str: String): String {
    return if (str.isEmpty()) { str }
    else str.substring(0, 1).toUpperCase() + str.substring(1)
}

fun getStringWidth(string: String): Int {
    return if (Font.isEnabled()) FontAccessor.fontRenderer.getWidth(string)
    else mc.textRenderer.getWidth(string)
}