package dev.toastmc.toastclient.api.config

import dev.toastmc.toastclient.IToastClient
import dev.toastmc.toastclient.api.managers.FriendManager
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object ConfigUtil : IToastClient {

    val mainDirectory = "${mc.runDirectory.canonicalPath}/toastclient/"

    const val moduleDirectory = "modules/"
    const val hudDirectory = "modules/hud/"

    fun init() {
        initLocations()
        loadEverything()
    }

    fun loadEverything(){
        ConfigLoader.loadModules()
        ConfigLoader.loadComponents()
        FriendManager.loadFriends()
    }

    fun saveEverything() {
        ConfigSaver.saveModules()
        ConfigSaver.saveComponents()
        FriendManager.saveFriends()
    }

    fun registerFile(location: String) {
        val path = Paths.get("$mainDirectory$location.json")
        if (Files.exists(path)) {
            Files.delete(path)
            Files.createFile(path)
        } else {
            Files.createFile(path)
        }
    }

    fun initLocations() {
        try {
            if(!Files.exists(Paths.get(mainDirectory)))
                Files.createDirectories(Paths.get(mainDirectory))
            if(!Files.exists(Paths.get(mainDirectory + moduleDirectory)))
                Files.createDirectories(Paths.get(mainDirectory + moduleDirectory))
            if(!Files.exists(Paths.get(mainDirectory + hudDirectory)))
                Files.createDirectories(Paths.get(mainDirectory + hudDirectory))
        } catch (ignore: IOException) {}
    }

}