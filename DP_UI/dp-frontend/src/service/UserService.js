import { myAxios } from "./helper"

class UserService{
    getAllUsers(){
        return myAxios
            .get('/users')
            .then((response) => response.data)
    }

    addNewUser(userData){
        return myAxios
            .post("/users",userData)
            .then((response) => response.data)
    }

    getUserById(userId){
        return myAxios
            .get("/users/"+userId)
            .then((response) => response.data)
    }

    editUser(editedUserData,userId){
        return myAxios
            .put("/users/"+userId,editedUserData)
            .then((response) => response.data)
    }
}

export default new UserService();