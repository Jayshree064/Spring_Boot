import React, { useRef, useState } from "react";
import LoginService from "../service/LoginService";
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const LoginComponent = () => {
    const nav = useNavigate();
    const usernameInputRef = useRef();
    const passwordInputRef = useRef();
    const [emailError,setEmailError] = useState();
    const [passwordError,setPasswordError] = useState();

    const loginHandler = (event) => {
        event.preventDefault();
        const enteredUsername = usernameInputRef.current.value;
        const enteredPassword = passwordInputRef.current.value;
        if(enteredUsername.trim().length === 0){
            setEmailError({message:"Please enter email."})
            return
        }
        if(enteredUsername.trim().length > 0){
            setEmailError(false)
        }
        if(enteredPassword.trim().length === 0){
            setPasswordError({message:"Please enter password."})
        }
        if(enteredPassword.trim().length > 0){
            setPasswordError(false)
        }
        let loginData = {
            email : enteredUsername,
            password : enteredPassword
        };
        console.log("Login Data : "+JSON.stringify(loginData))
        LoginService(loginData)
            .then((response) => {
                console.log("success ",response)
                localStorage.setItem('email',loginData.email)
                usernameInputRef.current.value = ''
                passwordInputRef.current.value = ''
                nav("/users");
                toast.success("User login success");
            })
            .catch((error)=>{
                console.log("failes ",error.response.data.message)
                toast.error(error.response.data.message)
            })
    }

    return(
        <div className="container mt-5">
            <h2 className="text-center">Login</h2>
            <div className="container mt-3 border rounded-5 p-5 w-50">
                <form>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Email address</label>
                        <input type="email" className="form-control" id="email" ref={usernameInputRef}/>
                        {emailError && <p className="text-danger">{emailError.message}</p>}
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password</label>
                        <input type="password" className="form-control" id="password" ref={passwordInputRef}/>
                        {passwordError && <p className="text-danger">{passwordError.message}</p>}
                    </div>
                    <div className="d-flex justify-content-center">
                        <button type="submit" className="btn btn-outline-dark px-4 py-2" onClick={loginHandler}>Login</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default LoginComponent;