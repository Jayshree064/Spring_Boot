import { myAxios } from './helper';

const LoginService = (loginData) => {
    return myAxios
        .post('/login',loginData)
        .then((response) => response.data)
}

export default LoginService;