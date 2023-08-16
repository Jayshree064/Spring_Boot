const HomeComponent = () => {
    return(
        <div className="container mt-5">
            <h1 className="text-center">Welcome to Home Page</h1>
            <div className="d-flex justify-content-center">
                <a href="/login" className="btn btn-primary px-5 py-2 mt-3">Login</a>
            </div>
        </div>
    )
}

export default HomeComponent;