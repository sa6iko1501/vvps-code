package main;

import models.Role;
import models.TypeOfPassenger;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class App {

    private List<User> users;

    public App(List<User> users) {
        this.users = users;
    }

    public App(){
        this.users = new ArrayList<>();
    }
    /**
     * @param userRequestingChange The user who is attempting to make a change on another user profile
     * @param userToBeChanged The user which will be changed
     * @param typeToBeAdded The type which will be added to the List of {@link TypeOfPassenger}
     */
    public void addType(User userRequestingChange, User userToBeChanged, TypeOfPassenger typeToBeAdded){
        if(userRequestingChange.getRoles().contains(Role.ADMIN)){
            userToBeChanged.addTypeOfPassenger(typeToBeAdded);
        }
        else{
            throw new UnsupportedOperationException(
                    String.format("User %s does not have the required Role ADMIN to perform changes on other users",
                            userRequestingChange.getUserName())
            );
        }
    }

    /**
     * @param userRequestingChange The user who is attempting to make a change on another user profile
     * @param userToBeChanged The user which will be changed
     * @param typeToBeRemoved The type which will be removed from the List of {@link TypeOfPassenger}
     */
    public void removeType(User userRequestingChange, User userToBeChanged, TypeOfPassenger typeToBeRemoved){
        if(userRequestingChange.getRoles().contains(Role.ADMIN)){
            userToBeChanged.removeTypeOfPassenger(typeToBeRemoved);
        }
        else{
            throw new UnsupportedOperationException(
                    String.format("User %s does not have the required Role ADMIN to perform changes on other users",
                            userRequestingChange.getUserName())
            );
        }
    }

    public List<User> getUsers(User userRequesting) {
        if(userRequesting.getRoles().contains(Role.ADMIN)){
            return users;
        }
        else{
            throw new UnsupportedOperationException(
                    String.format("User %s does not have the required Role ADMIN to view other users",
                            userRequesting.getUserName())
            );
        }
    }

    public void addUser(User userRequesting, String userName,
                        String firstName, String lastName, String email){
        if(userRequesting.getRoles().contains(Role.ADMIN)){
            User user = new User(userName, firstName, lastName, email);
            users.add(user);
        }
        else{
            throw new UnsupportedOperationException(
                    String.format("User %s does not have the required Role ADMIN to add users",
                            userRequesting.getUserName())
            );
        }
    }

    public void deleteUserById(User userRequesting, UUID userToBeDeletedId){
        if(userRequesting.getRoles().contains(Role.ADMIN)){
            User userToBeDeleted = users.stream()
                    .filter(user -> user.getId().equals(userToBeDeletedId))
                    .findFirst().orElse(null);
            if(userToBeDeletedId != null){
                users.remove(userToBeDeleted);
            }
        }
        else{
            throw new UnsupportedOperationException(
                    String.format("User %s does not have the required Role ADMIN to delete users",
                            userRequesting.getUserName())
            );
        }
    }

    public void changeUser(User userRequesting, UUID userToBeChanged, User newUserInfo){
        if(userRequesting.getRoles().contains(Role.ADMIN) || userRequesting.getId().equals(userToBeChanged)){
            users.stream().filter(user -> user.getId().equals(userToBeChanged)).forEach(
                    user -> {
                        user.setUserName(newUserInfo.getUserName());
                        user.setFirstName(newUserInfo.getFirstName());
                        user.setLastName(newUserInfo.getLastName());
                        user.setEmail(newUserInfo.getEmail());
                    }
            );
        }
        else{
            throw new UnsupportedOperationException(
                    String.format("User %s does not have the required Role ADMIN to change users",
                            userRequesting.getUserName())
            );
        }
    }

}
