import React, { Fragment } from 'react';
import { ToastContainer } from 'react-toastify';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import LoginComponent from './components/LoginComponent';
import 'bootstrap/dist/css/bootstrap.css';
import 'react-toastify/dist/ReactToastify.css';
import HomeComponent from './components/HomeComponent';
import UserComponent from './components/User/UserComponent';
import AddUserComponent from './components/User/AddUserComponent';
import EditUserComponent from './components/User/EditUserComponent';
import BlockUserComponent from './components/BlockUser/BlockUserComponent';

function App() {
  return (
    <Fragment>
      <ToastContainer/>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<HomeComponent/>}/>
          <Route path='/login' element={<LoginComponent/>}/>
          <Route path='/users' element={<UserComponent/>}/>
          <Route path='/addUser' element={<AddUserComponent/>}/>
          <Route path='/editUser/:id' element={<EditUserComponent/>}/>
          <Route path='/blockUser' element={<BlockUserComponent/>}/>
        </Routes>
      </BrowserRouter>
    </Fragment>
  );
}

export default App;
