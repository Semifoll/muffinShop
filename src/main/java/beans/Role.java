package beans;

public class Role {
    private int codRole;
    private String name;
    public Role(int codRole, String name){
        this.codRole = codRole;
        this.name = name;
    }

    public int getCodRole() {
        return codRole;
    }

    public void setCodRole(int codRole) {
        this.codRole = codRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
