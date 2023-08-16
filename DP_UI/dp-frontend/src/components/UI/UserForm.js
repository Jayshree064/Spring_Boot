import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import { toast } from 'react-toastify';
import UserService from "../../service/UserService";

const UserForm = (props) => {
    const [existingUser,setExistingUser] = useState([]);
    const {id} = useParams();
    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const emailRef = useRef();
    const phoneNumberRef = useRef();
    const dobRef = useRef();
    const passwordRef = useRef();
    const repasswordRef = useRef();
    const addressRef = useRef();

    const addUserHandler = (event) => {
        event.preventDefault();
        const userData = {
            firstName : firstNameRef.current.value,
            lastName : lastNameRef.current.value,
            email: emailRef.current.value,
            password: passwordRef.current.value,
            address: addressRef.current.value,
            phoneNumber: phoneNumberRef.current.value,
            birthDate: dobRef.current.value,
        }
        let fname = firstNameRef.current.value;
        let password = passwordRef.current.value;
        let repassword = repasswordRef.current.value;
        let email = emailRef.current.value;
        if(fname.trim().length === 0){
            toast.error("Please enter first name")
            return
        }
        if(email.trim().length === 0){
            toast.error("Please enter email")
            return
        }
        if(password.trim().length === 0){
            toast.error("Please enter password")
            return
        }
        if(password.trim() !== repassword.trim()){
            toast.error("Password and repassword must be same");
            return;
        }
        console.log(userData)
        props.onAdd(userData)
    }

    const editUserHandler = (event) => {
        event.preventDefault();
        const editedUserData = {
            firstName : firstNameRef.current.value,
            lastName : lastNameRef.current.value,
            email: emailRef.current.value,
            password: passwordRef.current.value,
            address: addressRef.current.value,
            phoneNumber: phoneNumberRef.current.value,
            birthDate: dobRef.current.value,
        }
        let fname = firstNameRef.current.value;
        let password = passwordRef.current.value;
        let repassword = repasswordRef.current.value;
        let email = emailRef.current.value;
        if(fname.trim().length === 0){
            toast.error("Please enter first name")
            return
        }
        if(email.trim().length === 0){
            toast.error("Please enter email")
            return
        }
        if(password.trim().length === 0){
            toast.error("Please enter password")
            return
        }
        if(password.trim() !== repassword.trim()){
            toast.error("Password and repassword must be same");
            return;
        }
        console.log(editedUserData)
        props.onEdit(editedUserData)
    }

    useEffect(() => {
        if(id > 0){
            UserService.getUserById(id)
            .then((response) => {
                console.log(response)
                setExistingUser(response)
            }).catch((error)=>{
                console.log(error)
            })
        }
    },[])

    return(
        <form>
            <div className="row">
                <div className="col">
                    <label htmlFor="fname" className="form-label">First Name</label>
                    <input type="text" className="form-control" id="fname" ref={firstNameRef} defaultValue={existingUser.firstName}/>
                </div>
                <div className="col">
                    <label htmlFor="lname" className="form-label">Last Name</label>
                    <input type="text" className="form-control" id="lname" ref={lastNameRef} defaultValue={existingUser.lastName}/>
                </div>
            </div>
            <div className="row mt-3">
                <div className="col">
                    <label htmlFor="phoneNumber" className="form-label">Phone number</label>
                    <input type="number" className="form-control" id="phoneNumber" ref={phoneNumberRef} defaultValue={existingUser.phoneNumber}/>
                </div>
                <div className="col">
                    <label htmlFor="birthDate" className="form-label">Date of Birth</label>
                    <input type="date" className="form-control" id="birthDate" ref={dobRef} defaultValue={existingUser.dateOfBirth}/>
                </div>
            </div>
            <div className="row mt-3">
                <div className="col">
                    <label htmlFor="email" className="form-label">Email Address</label>
                    <input type="email" className="form-control" id="email" ref={emailRef} defaultValue={existingUser.email}/>
                </div>
            </div>
            <div className="row mt-3">
                <div className="col">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="password" className="form-control" id="password" ref={passwordRef} defaultValue={existingUser.password}/>
                </div>
                <div className="col">
                    <label htmlFor="repassword" className="form-label">Re-enter Password</label>
                    <input type="password" className="form-control" id="repassword" ref={repasswordRef} defaultValue={existingUser.password}/>
                </div>
            </div>
            <div className="row mt-3">
                <div className="col">
                    <label htmlFor="address" className="form-label">Address</label>
                    <textarea className="form-control" id="address" rows="3" ref={addressRef} defaultValue={existingUser.address}></textarea>
                </div>
            </div>
            <div className="d-flex justify-content-center mt-3"> 
                {!id && <button type="submit" className="btn btn-outline-primary me-3 px-4" onClick={addUserHandler}>Add</button>}
                {id && <button type="submit" className="btn btn-outline-primary me-3 px-4" onClick={editUserHandler}>Edit</button>}
                <button type="cancel" className="btn btn-outline-warning px-3" onClick={props.onCancel}>Cancel</button>
            </div>
        </form>
    )
}

export default UserForm