import com.anop72.randompokemon.RandomPokemonId
import org.junit.Test
import kotlin.test.assertNotNull


class KotlinTest() {

    @Test fun testRandom() {
        assertNotNull(RandomPokemonId().random())
    }
}
