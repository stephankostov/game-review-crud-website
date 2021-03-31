import axios from 'axios'

const USER_API_URL = 'http://localhost:8080/api/users'

class UserAPIService {

    retrieveUser(id) {
        return axios.get(USER_API_URL + '/get/' + id);
    }

    retrieveAllUsers() {
        return axios.get(USER_API_URL + '/all');
    }

    deleteUser(id) {
        return axios.delete(USER_API_URL + '/delete/' + id);
    }

    createUser(game) {
        return axios.post(USER_API_URL + '/create', game)
    }

    updateUser(id, game) {
        return axios.put(USER_API_URL + '/games/' + id, game)
    }
}

export default new UserAPIService();