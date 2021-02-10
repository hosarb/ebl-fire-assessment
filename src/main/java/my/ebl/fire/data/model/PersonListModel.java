package my.ebl.fire.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public interface PersonListModel extends Serializable {
    Long getId();

    @JsonProperty("first_name")
    String getFirstName();

    @JsonProperty("last_name")
    String getLastName();

    @JsonProperty("age")
    String getAge();

    @JsonProperty("favourite_colour")
    String getFavouriteColour();
}
