package com.anop72.randompokemon

import java.util.Random

class RandomPokemonId() {

    fun random() : Int {

        val random : Random = Random()

        return random.nextInt(100) + 1
    }

}
