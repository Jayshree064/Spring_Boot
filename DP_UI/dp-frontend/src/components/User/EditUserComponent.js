import { useNavigate, useParams } from "react-router-dom";
import UserForm from "../UI/UserForm";
import UserService from "../../service/UserService";
import { toast } from "react-toastify";
import Navbar from "../UI/Navbar";
import { Fragment } from "react";

const EditUserComponent = () => {
    const navigation = useNavigate();
    const {id} = useParams();

    const goBackHandler = () => {
        navigation('/users')
    }

    const editUserHandler = (enteredEditedData) => {
        const editedData = {
            ...enteredEditedData
        }

        UserService.editUser(editedData,id)
            .then((response) => {
                console.log("success ",response)
                navigation("/users")
                toast.success(response.message)
            })
            .catch((error) => {
                console.log(error)
                error.response.data.message.map((err) => {toast.error(err)})
            })
    }

    return(
        <Fragment>
            <Navbar/>
            <div className="container mt-5">
                <h1 className="text-center">Edit User</h1>
                <div className="container mt-3 border rounded-3 py-4 px-5">
                    <UserForm onEdit={editUserHandler} onCancel={goBackHandler}/>
                </div>
            </div>
        </Fragment>
    )
}

export default EditUserComponent;