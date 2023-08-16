import { myAxios } from "./helper";

class BlockUserService{
    getBlockUser(userEmail){
        return myAxios
            .post("/block-users",userEmail)
            .then((response) => response.data)
    }

    removeBlockUser(userId){
        return myAxios
            .delete("/block-users/"+userId)
            .then((response) => response.data)
    }

    addBlockUser(userData){
        return myAxios
            .post("/block-users/add",userData)
            .then((response) => response.data)
    }
}

export default new BlockUserService()