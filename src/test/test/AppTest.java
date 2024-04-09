package test;

import main.App;
import models.Role;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class AppTest {
    private App app;
    List<User> userTestData;
    User adminUser;
    @Before
    public void setUp(){
        List<User> userTestData = new java.util.ArrayList<>(List.of(new User("admin", "Alexander", "Stoyanov", "someEmail"),
                new User("user1", "Ivan", "Ivanov", "someEmail1"),
                new User("user2", "Petar", "Petrov", "someEmail2")));
        userTestData.get(0).addRole(Role.ADMIN);
        app = new App(userTestData);
        this.userTestData = userTestData;
        adminUser = new User("admin", "SomeName", "SomeName", "someEmail");
        adminUser.addRole(Role.ADMIN);
    }

    @Test
    public void testGetUsers(){
        Assert.assertTrue(compareUserLists(app.getUsers(adminUser), userTestData));
    }

    @Test
    public void testDeleteUser(){
        User userToBeDeleted = userTestData.stream().filter(user -> user.getUserName().equals("user2"))
                .findFirst().orElse(null);
        assert userToBeDeleted != null;
        app.deleteUserById(adminUser, userToBeDeleted.getId());
        userTestData.remove(userToBeDeleted);
        Assert.assertTrue(compareUserLists(app.getUsers(adminUser), userTestData));

        // CleanUp
        userTestData.add(userToBeDeleted);
    }

    @Test
    public void testUpdateUser(){
        User userToBeUpdated = userTestData.stream().filter(user -> user.getUserName().equals("user1"))
                .findFirst().orElse(null);
        assert userToBeUpdated != null;
        userToBeUpdated.setLastName("Some new Lastname");
        userToBeUpdated.setFirstName("Some new Firstname");
        app.changeUser(adminUser, userToBeUpdated.getId(), userToBeUpdated);
        User userAfterUpdate = userTestData.stream().filter(user -> user.getId().equals(userToBeUpdated.getId()))
                .findFirst().orElse(null);
        assert userAfterUpdate != null;
        Assert.assertEquals("Some new Firstname", userAfterUpdate.getFirstName());
        Assert.assertEquals("Some new Lastname", userAfterUpdate.getLastName());
    }

    @Test
    public void testAddUser(){
        app.addUser(adminUser, "someNewUser", "Gosho", "Goshov", "someEmail");
        User addedUser = app.getUsers(adminUser).stream().filter(user -> user.getUserName().equals("someNewUser"))
                .findFirst().orElse(null);
        Assert.assertNotNull(addedUser);
    }


    private boolean compareUserLists(List<User> userList1, List<User> userList2){
        boolean flag = true;
        for(int i=0; i<userList1.size(); i++){
            if(!userList1.get(i).equals(userList2.get(i))){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
