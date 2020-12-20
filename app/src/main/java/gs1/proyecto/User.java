package gs1.proyecto;

public class User {
    private int id;
    private String usuario, nombre, email, pass;

    //Constructors


    public User(int id, String usuario, String nombre, String email, String pass) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
