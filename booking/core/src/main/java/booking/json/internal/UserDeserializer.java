package booking.json.internal;

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
            JsonNode passwordNode = objectNode.get("password");

            if (usernameNode != null && usernameNode.isTextual() && passwordNode != null && passwordNode.isTextual()) {
                user.setUsername(usernameNode.asText());
                user.setPassword(passwordNode.asText());
            }

            return user;
        }
        return null;
    }
}
