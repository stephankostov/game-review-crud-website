import axios from 'axios'

const DEVELOPER_API_URL = 'http://localhost:8080/api/developers'

class DeveloperAPIService {

    retrieveDeveloper(id) {
        return axios.get(DEVELOPER_API_URL + '/get/' + id);
    }

    retrieveAllDevelopers() {
        return axios.get(DEVELOPER_API_URL + '/all');
    }

    deleteDeveloper(id) {
        return axios.delete(DEVELOPER_API_URL + '/delete/' + id);
    }

    createDeveloper(game) {
        return axios.post(DEVELOPER_API_URL + '/create', game)
    }

    updateDeveloper(id, game) {
        return axios.put(DEVELOPER_API_URL + '/games/' + id, game)
    }
}

export default new DeveloperAPIService();