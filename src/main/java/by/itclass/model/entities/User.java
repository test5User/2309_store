package by.itclass.model.entities;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class User {
    private int id;
    @NonNull private String name;
    @NonNull private String email;
    @NonNull private String login;
    @Setter
    @NonNull private char[] password;
}
