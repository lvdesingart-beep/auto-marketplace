@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // BCrypt хеш
    private int maxAds = 5;

    // геттери і сеттери
}
