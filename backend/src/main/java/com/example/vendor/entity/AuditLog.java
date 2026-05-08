@Entity
public class AuditLog {
    @Id @GeneratedValue
    private Long id;

    private String action;
    private String method;
    private String entity;
}