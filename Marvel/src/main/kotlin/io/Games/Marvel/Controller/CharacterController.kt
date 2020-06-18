package io.Games.Marvel.Controller

import io.Games.Marvel.Service.CharacterService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import kotlin.streams.toList

@RestController
@RequestMapping("/Marvel")
class CharacterController {


    var characterService = CharacterService()

    @RequestMapping("/home")
    fun LoadCharacters(): String? {
        characterService.LoadAvengerCharacter()
        characterService.loadAntiHeros()
        characterService.loadMutants()
        return "Welcome To Marvel Games"
    }

    @RequestMapping("/{name}")
    fun getPower(@PathVariable name: String): String {
        return characterService.getPowerLevel(name)
    }

    @RequestMapping("/AllCharacters")
    fun getAllCharacters(): Map<String, Int> {
        return characterService.finalCharacters
    }

    @RequestMapping("/LoadAll")
    fun getCharList() {
        characterService.getMarvelCharacter()
        characterService.getAntCharacters()
        characterService.getMutantCharacters()
    }
}
