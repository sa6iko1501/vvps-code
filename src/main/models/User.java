package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String userName;
    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;

    private List<TypeOfPassenger> typeOfPassenger;

    public User(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        typeOfPassenger = new java.util.ArrayList<>(List.of(TypeOfPassenger.NORMAL));
        roles = new java.util.ArrayList<>(List.of(Role.USER));
    }
    public void addRole(Role role){
        if(!roles.contains(role)){
            roles.add(role);
        }
    }

    public void removeRole(Role role){
        roles.remove(role);
    }

    public void addTypeOfPassenger(TypeOfPassenger typeOfPassenger){
        if(!this.typeOfPassenger.contains(typeOfPassenger)){
            this.typeOfPassenger.add(typeOfPassenger);
        }
    }

    public void removeTypeOfPassenger(TypeOfPassenger typeOfPassenger){
        this.typeOfPassenger.remove(typeOfPassenger);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<TypeOfPassenger> getTypeOfPassenger() {
        return typeOfPassenger;
    }
}
