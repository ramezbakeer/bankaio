package com.bankaio.Bankaio;

/*
import static org.assertj.core.api.Assertions.assertThat;
@JsonTest
public class CustomerJsonTest {
    @Autowired
    JacksonTester<Customer> json;
        @Test
        void customerSerializationTest() throws IOException{

            Customer customer = new Customer(1L,"Ramez","ramez@gmail","r@123","01151562868");
            assertThat(json.write(customer)).isStrictlyEqualToJson("customer.json");
            assertThat(json.write(customer)).hasJsonPathNumberValue("@.id");
            assertThat(json.write(customer)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
            assertThat(json.write(customer)).hasJsonPathStringValue("@.name");
            assertThat(json.write(customer)).extractingJsonPathStringValue("@.name").isEqualTo("Ramez");
            assertThat(json.write(customer)).hasJsonPathStringValue("@.email");
            assertThat(json.write(customer)).extractingJsonPathStringValue("@.email").isEqualTo("ramez@gmail");
            assertThat(json.write(customer)).hasJsonPathStringValue("@.password");
            assertThat(json.write(customer)).extractingJsonPathStringValue("@.password").isEqualTo("r@123");
            assertThat(json.write(customer)).hasJsonPathStringValue("@.phone");
            assertThat(json.write(customer)).extractingJsonPathStringValue("@.phone").isEqualTo("01151562868");

        }
    @Test
    void customerDeserializationTest() throws IOException {

        String expected = """
                {
                  "id": 1000,
                  "name": "Ramez",
                  "email": "ramez@gmail",
                  "password": "r@123",
                  "phone": "01151562868"
                }
           """;
        assertThat(json.parse(expected))
                .isEqualTo(new Customer(1000L, "Ramez","ramez@gmail","r@123","01151562868"));
        assertThat(json.parseObject(expected).id()).isEqualTo(1000);
        assertThat(json.parseObject(expected).name()).isEqualTo("Ramez");
        assertThat(json.parseObject(expected).email()).isEqualTo("ramez@gmail");
        assertThat(json.parseObject(expected).password()).isEqualTo("r@123");
        assertThat(json.parseObject(expected).phone()).isEqualTo("01151562868");
    }

}
    */