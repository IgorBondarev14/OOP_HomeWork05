package personal.model;

public interface Mapperable {
    public String map(User user);
    public User map(String line);
}
