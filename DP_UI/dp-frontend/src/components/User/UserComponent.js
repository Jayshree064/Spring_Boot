import { Fragment, useEffect, useState } from 'react';
import * as Icon from 'react-bootstrap-icons';
import UserService from '../../service/UserService';
import Navbar from '../UI/Navbar';
import { toast } from 'react-toastify';


const UserComponent = () => {
    const [users,setUsers] = useState([]);
    
    useEffect(()=>{
        UserService.getAllUsers()
            .then((response)=>{
                console.log(response)
                setUsers(response)
            }).catch((error) => {
                toast.error(error.response.data.message)
            })
    },[])

    const loggedInUser = localStorage.getItem('email')

    const newUserList = users.filter(
        function(user){
            return user.email !== loggedInUser
        }
    )

    const userList = newUserList.map(user => {
        return(
            <tr key={user.userId}>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.email}</td>
                <td>{user.phoneNumber}</td>
                <td>
                    <a href={`/editUser/${user.userId}`} className="btn border-0 btn-outline-success me-2 py-1 px-2"><Icon.PencilSquare size={18} /></a>
                    {/* <a href="#" className="btn btn-outline-danger border-0 py-1 px-2"><Icon.TrashFill size={18}/></a> */}
                </td>
            </tr>
        )
    })

    return(
        <Fragment>
            <Navbar/>
            <div className="container mt-5">
                <h1 className="text-center">Users</h1>
                <div className="d-flex justify-content-end me-5 pe-2 mt-3">
                    <a href="/addUser" className="btn px-3 border-0 rounded-3"><Icon.PlusCircleFill className='text-primary fs-4' style={{fontSize: 40}}/></a>
                </div>
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
            </div>
        </Fragment>
    )
}

export default UserComponent;