package my.ebl.fire;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import my.ebl.fire.data.model.PersonDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static junit.framework.TestCase.assertEquals;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class SpringBootBootstrapLiveTest {
    private static final String API_ROOT = "http://localhost:8080/persons";

    private PersonDto createRandomPerson() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(randomAlphabetic(10));
        personDto.setLastName(randomAlphabetic(15));
        personDto.setAge(randomNumeric(2));
        personDto.setFavouriteColour(randomAlphabetic(20));
        return personDto;
    }

    private String createPersonAsUri(PersonDto dto) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().basic("admin", "admin")
                .body(dto)
                .post(API_ROOT);
        String print = response.body().print();
        dto.setId(Long.valueOf(print));
        return API_ROOT + "/" + print;
    }

    @Test
    public void whenGetAllPersons_thenOK() {
        Response response = RestAssured.given()
                .auth().basic("admin", "admin")
                .get(API_ROOT);
        System.out.println(response.body().print());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetCreatedPersonById_thenOK() {
        PersonDto dto = createRandomPerson();
        String location = createPersonAsUri(dto);
        Response response = RestAssured.given()
                .auth().basic("admin", "admin")
                .get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(dto.getId().toString(), response.jsonPath().get("id").toString());
        assertEquals(dto.getFirstName(), response.jsonPath().get("firstName"));
        assertEquals(dto.getLastName(), response.jsonPath().get("lastName"));
    }

    @Test
    public void whenGetNotExistPersonById_thenNotFound() {
        Response response = RestAssured.given()
                .auth().basic("admin", "admin")
                .get(API_ROOT + "/" + randomNumeric(4));

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void whenCreateNewPerson_thenCreated() {
        PersonDto dto = createRandomPerson();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().basic("admin", "admin")
                .body(dto)
                .post(API_ROOT);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidPerson_thenError() {
        PersonDto dto = createRandomPerson();
        dto.setAge(null);
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().basic("admin", "admin")
                .body(dto)
                .post(API_ROOT);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }


    @Test
    public void whenUpdateCreatedPerson_thenUpdated() {
        PersonDto dto = createRandomPerson();
        dto.setAge("33");
        String location = createPersonAsUri(dto);
        Response response = RestAssured.given()
                .auth().basic("admin", "admin")
                .get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("33", response.jsonPath().get("age"));

        dto.setAge("22");
        response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().basic("admin", "admin")
                .body(dto)
                .put(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.given()
                .auth().basic("admin", "admin")
                .get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("22", response.jsonPath().get("age"));
    }

    @Test
    public void whenDeleteCreatedPerson_thenOk() {
        PersonDto dto = createRandomPerson();
        String location = createPersonAsUri(dto);
        Response response = RestAssured.given()
                .auth().basic("admin", "admin")
                .delete(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.given()
                .auth().basic("admin", "admin")
                .get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

}
