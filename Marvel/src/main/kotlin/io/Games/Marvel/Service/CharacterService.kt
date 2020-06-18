package io.Games.Marvel.Service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.Games.Marvel.GameCharacters
import org.springframework.scheduling.annotation.Async
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

class CharacterService {

    // To read the response from json objects
    var restTemplate = RestTemplate()

    //Final map of characters
    var finalCharacters = mutableMapOf<String, Int>()


    @Async
    fun getMarvelCharacter(): CompletableFuture<List<GameCharacters?>?>? {
        val url = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b"
        val response = restTemplate.getForObject(url, Array<GameCharacters>::class.java)!!
        addCharacter(response)
        return CompletableFuture.completedFuture(response.toList())
    }

    @Async
    fun getAntCharacters(): CompletableFuture<List<GameCharacters?>?>? {
        val url = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b"
        val response = restTemplate.getForObject(url, Array<GameCharacters>::class.java)!!
        addCharacter(response)
        return CompletableFuture.completedFuture(response.toList())
    }

    @Async
    fun getMutantCharacters(): CompletableFuture<List<GameCharacters?>?>? {
        val url = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b"
        val response = restTemplate.getForObject(url, Array<GameCharacters>::class.java)!!
        addCharacter(response)
        return CompletableFuture.completedFuture(response.toList())
    }


    // Method to create array of Avenger characters
    fun LoadAvengerCharacter() {
        val avengerCharacters = Array<GameCharacters>(6) { GameCharacters("", 0, 0) }
        avengerCharacters[0] = GameCharacters("Iron man", 60, 0)
        avengerCharacters[1] = GameCharacters("Captain America", 68, 0)
        avengerCharacters[2] = GameCharacters("Spider man", 58, 0)
        avengerCharacters[3] = GameCharacters("B;ack Panther", 68, 0)
        avengerCharacters[4] = GameCharacters("Vision", 50, 0)
        avengerCharacters[5] = GameCharacters("Hawk eye", 30, 0)
        addCharacter(avengerCharacters)
    }

    // Method to create array of AntiHeros
    fun loadAntiHeros() {
        val antiHeros = Array<GameCharacters>(6) { GameCharacters("", 0, 0) }
        antiHeros[0] = GameCharacters("Mandrin", 70, 0)
        antiHeros[1] = GameCharacters("Thanos", 80, 0)
        antiHeros[2] = GameCharacters("Galactus", 95, 0)
        antiHeros[3] = GameCharacters("Hela", 75, 0)
        antiHeros[4] = GameCharacters("Ego", 50, 0)
        antiHeros[5] = GameCharacters("Dr. Doom", 78, 0)
        addCharacter(antiHeros)
    }

    // Method to create array of Mutants
    fun loadMutants() {
        val Mutans = Array<GameCharacters>(6) { GameCharacters("", 0, 0) }
        Mutans[0] = GameCharacters("Apocalypse", 80, 0)
        Mutans[1] = GameCharacters("Professor", 75, 0)
        Mutans[2] = GameCharacters("Dark", 90, 0)
        Mutans[3] = GameCharacters("Magneto", 78, 0)
        Mutans[4] = GameCharacters("Wolverine", 64, 0)
        Mutans[5] = GameCharacters("Mystique", 66, 0)
        addCharacter(Mutans)
    }

    /*
    @args : Array of GameCharacters
    @return : nothing
    * */
    private fun addCharacter(Characters: Array<GameCharacters>) {
        for (character in Characters) {
            if (finalCharacters.size < 16) {
                finalCharacters.put(character.name, character.power)
            } else {
                var minPower = finalCharacters.minBy { it.value }
                var characterWithMinPower = minPower?.key
                finalCharacters.remove(characterWithMinPower)
                finalCharacters.put(character.name, character.power)
            }
        }
    }

    /*
    @args : name of game character
    @return : Power level of character
    * */
    fun getPowerLevel(name: String): String {
        var power: String
        if (finalCharacters.contains(name)) {
            power = finalCharacters[name].toString()
        } else {
            power = "Character not found"
        }
        return power
    }
}