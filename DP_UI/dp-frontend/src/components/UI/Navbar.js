import { useNavigate } from "react-router-dom";

const Navbar = () => {
    const navigation = useNavigate();
    const logoutHandler = () => {
        localStorage.removeItem('email')
        navigation("/login")
    }

    return(
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid ms-3">
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse ms-3" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a className="nav-link" aria-current="page" href="/users">Users</a>
                        </li>
                        <li className="nav-item">
                            <a type="button" className="nav-link" href="/blockUser">Block User</a>
                        </li>
                    </ul>
                </div>
                <div className="d-flex justify-content-end me-5">
                    <button className="btn btn-dark me-5" onClick={logoutHandler}>Logout</button>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;