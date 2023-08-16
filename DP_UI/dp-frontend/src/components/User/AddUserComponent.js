import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import UserService from "../../service/UserService";
import UserForm from "../UI/UserForm";
import { Fragment } from "react";
import Navbar from "../UI/Navbar";

const AddUserComponent = (props) => {
    const navigation = useNavigate();

    const goBackHandler = () => {
        navigation('/users')
    }

    const addUserHandler = (enteredUserData) => {
        const userData = {
            ...enteredUserData
        }
        console.log(userData)

        UserService.addNewUser(userData)
            .then((response)=>{
                console.log("success ",response);
                navigation("/users")
                toast.success(response.message)
            })
            .catch((error)=>{
                console.log(error)
                error.response.data.message.map((err)=>{toast.error(err)})
            })
    }

    return(
        <Fragment>
            <Navbar/>
            <div className="container mt-5">
                <h1 className="text-center">Add User</h1>
                <div className="container mt-3 border rounded-3 py-4 px-5">
                    <UserForm onAdd={addUserHandler} onCancel={goBackHandler}/>
                </div>
            </div>
        </Fragment>
    )
}

export default AddUserComponent;