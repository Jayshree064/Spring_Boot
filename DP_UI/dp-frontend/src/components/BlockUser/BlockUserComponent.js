import { Fragment, useEffect, useRef, useState } from "react";
import Navbar from "../UI/Navbar";
import * as Icon from 'react-bootstrap-icons';
import BlockUserService from "../../service/BlockUserService";
import {Modal, Button} from 'react-bootstrap';  
import { toast } from "react-toastify";
import noDataImg from '../../assets/no_data.png';

const BlockUserComponent = () => {
    const [showDeleteModal, setshowDeleteModal] = useState(false);  
    const [showAddModal, setShowaAddModal] = useState(false);
    const[blockUserId,setBlockUserId] = useState();
    const[blockUsers,setBlockUsers] = useState([]);
    const [emailError,setEmailError] = useState();
    const email = {email: localStorage.getItem('email')}
    const blockUserMailRef = useRef();

    const getBlockedUsers = () => {
        BlockUserService.getBlockUser(email)
            .then((response) => {
                console.log(response)
                setBlockUsers(response)
            })
            .catch((response)=>{
                setBlockUsers([])
                toast.error(response.response.data.message)
            })
    }

    useEffect(() => {
        getBlockedUsers()
    },[])

    const deleteModalClose = () => setshowDeleteModal(false);  
    const deleteModalShow = (id) => {
        setshowDeleteModal(true); 
        setBlockUserId(id)
    }

    const addModalClose = () => setShowaAddModal(false);  
    const addModalShow = () => setShowaAddModal(true); 
    
    const userList = blockUsers.map(blockUser => {
            return(
                <tr key={blockUser.blockUserId}>
                    <td>{blockUser.blockedUserId.firstName}</td>
                    <td>{blockUser.blockedUserId.lastName}</td>
                    <td>{blockUser.blockedUserId.email}</td>
                        <td>{blockUser.blockedUserId.phoneNumber}</td>
                        <td>
                        <button type="submit" className="btn btn-outline-danger border-0 py-1 px-2" onClick={() => deleteModalShow(blockUser.blockUserId)}><Icon.TrashFill size={18}/></button>
                    </td>
                </tr>
            )
        }
    )

    const deleteHandler = (userId) => {
        console.log(userId)
        BlockUserService.removeBlockUser(userId)
            .then((response) => {
                console.log(response)
                getBlockedUsers()
                toast.success("User successfully removed")
            })
            .catch((error)=>{
                toast.error("User can't remove")
            })
            setshowDeleteModal(false)
    }

    function isValidEmail(email) {
        return /\S+@\S+\.\S+/.test(email);
      }

    const addBlockUserHandler = (event) =>{
        event.preventDefault()
        const data = {
            blockerUserEmail: localStorage.getItem('email'),
            blockedUserEmail: blockUserMailRef.current.value
        }
        if(blockUserMailRef.current.value.trim().length === 0){
            // toast.error("Please enter email")
            setEmailError({message:"Please enter email"})
            return
        }
        if(blockUserMailRef.current.value.trim().length > 0){
            setEmailError(false)
        }
        if(!isValidEmail(blockUserMailRef.current.value)){
            // toast.error("Please enter valid email")
            setEmailError({message:"Please enter valid email address"})
            return
        }
        if(isValidEmail(blockUserMailRef.current.value)){
            setEmailError(false)
        }
        BlockUserService.addBlockUser(data)
            .then((response) => {
                console.log(response)
                getBlockedUsers()
                toast.success("User successfully added to block user list")
                setShowaAddModal(false)
            })
            .catch((error) => {
                toast.error(error.response.data.message)
            })
    }

    return(
        <Fragment>
            <Navbar/>
            <div className="container mt-5">
                <h1 className="text-center">Block User List</h1>
                <div className="d-flex justify-content-end me-5 pe-2 mt-3">
                    <button type="submit" className="btn px-3 border-0" onClick={addModalShow}><Icon.PlusCircleFill className='text-primary fs-4' style={{fontSize: 40}}/></button>
                </div>
                {blockUsers.length === 0 && 
                    <div className="container mt-5 d-flex justify-content-center">
                        <img src={noDataImg} alt="" className="w-50 h-50"/>
                    </div>
                }
                {blockUsers.length > 0 &&
                    <table className="table table-hover mt-5 text-center">
                        <thead>
                            <tr>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone number</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {userList}
                        </tbody>
                    </table>
                }
            </div>
            <Modal centered show={showDeleteModal} onHide={deleteModalClose}>  
                <Modal.Header closeButton>  
                    <Modal.Title>Remove Blocked User</Modal.Title>  
                </Modal.Header>  
                
                <Modal.Body>  
                    <h5>Do you want to remove user form block user list</h5>  
                </Modal.Body>  
                
                <Modal.Footer>  
                    <Button variant="outline-secondary" onClick={deleteModalClose}>Cancel</Button>  
                    <Button variant="outline-danger" onClick={() => deleteHandler(blockUserId)} >Remove</Button>  
                </Modal.Footer>  
            </Modal>  
            <Modal centered show={showAddModal} onHide={addModalClose}>  
                <Modal.Header closeButton>  
                    <Modal.Title>Block User</Modal.Title>  
                </Modal.Header>  
                <Modal.Body>  
                    <form>
                        <label htmlFor="email" className="form-label">Enter email</label>
                        <input type="email" className="form-control" id="email" ref={blockUserMailRef}/>
                        {emailError && <p className="text-danger">{emailError.message}</p>}
                    </form>
                </Modal.Body>  
                <Modal.Footer>  
                    <Button variant="outline-secondary" onClick={addModalClose}>Cancel</Button>  
                    <Button variant="outline-primary px-3" onClick={addBlockUserHandler}>Add</Button>  
                </Modal.Footer>  
            </Modal> 
        </Fragment>
    )
}

export default BlockUserComponent;