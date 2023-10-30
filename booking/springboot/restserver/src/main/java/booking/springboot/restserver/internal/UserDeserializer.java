package booking.springboot.restserver.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import booking.core.User;

import java.io.IOException;

public final class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        if (jsonNode instanceof ObjectNode objectNode) {
            User user = new User();

            JsonNode usernameNode = objectNode.get("username");
            JsonNode firstNameNode = objectNode.get("firstName");
            JsonNode lastNameNode = objectNode.get("lastName");
            JsonNode passwordNode = objectNode.get("password");

            if (usernameNode != null && usernameNode.isTextual()
                    && firstNameNode != null && firstNameNode.isTextual() && lastNameNode != null
                    && lastNameNode.isTextual() && passwordNode != null && passwordNode.isTextual()) {
                user.setUsername(usernameNode.asText());
                user.setFirstName(firstNameNode.asText());
                user.setLastName(lastNameNode.asText());
                user.setPassword(passwordNode.asText());
            }

            return user;
        }
        return null;
    }
}
