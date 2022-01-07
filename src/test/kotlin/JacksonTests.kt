
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import kotlin.test.assertEquals

class JacksonTests {

    data class ExtendableBean(val name: String) {

        @get:JsonAnyGetter
        val properties: MutableMap<String, String> = mutableMapOf()
    }

    @Test
    fun `when serializing using JSON Any Getter then correct`() {
        val bean = ExtendableBean("Hello Bean").also {
            it.properties["key"] = "value"
        }
        val result = ObjectMapper().writeValueAsString(bean)
        assertEquals("""{"name":"Hello Bean","key":"value"}""", result)
    }

}