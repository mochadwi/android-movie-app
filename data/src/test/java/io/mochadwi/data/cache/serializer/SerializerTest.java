package io.mochadwi.data.cache.serializer;

import io.mochadwi.data.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SerializerTest {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.mochadwi.io/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private Serializer serializer;

  @Before
  public void setUp() {
    serializer = new Serializer();
  }

  @Test
  public void testSerializeHappyCase() {
    final UserEntity userEntityOne = serializer.deserialize(JSON_RESPONSE, UserEntity.class);
    final String jsonString = serializer.serialize(userEntityOne, UserEntity.class);
    final UserEntity userEntityTwo = serializer.deserialize(jsonString, UserEntity.class);

    assertThat(userEntityOne.getUserId(), is(userEntityTwo.getUserId()));
    assertThat(userEntityOne.getFullname(), is(equalTo(userEntityTwo.getFullname())));
    assertThat(userEntityOne.getFollowers(), is(userEntityTwo.getFollowers()));
  }

  @Test
  public void testDesearializeHappyCase() {
    final UserEntity userEntity = serializer.deserialize(JSON_RESPONSE, UserEntity.class);

    assertThat(userEntity.getUserId(), is(1));
    assertThat(userEntity.getFullname(), is("Simon Hill"));
    assertThat(userEntity.getFollowers(), is(7484));
  }
}
