import axios from 'axios'

const LANGUAGE_API_URL = 'http://localhost:8080/api/languages'

class LanguageAPIService {

    retrieveLanguage(id) {
        return axios.get(LANGUAGE_API_URL + '/get/' + id);
    }

    retrieveAllLanguages() {
        return axios.get(LANGUAGE_API_URL + '/all');
    }

    deleteLanguage(id) {
        return axios.delete(LANGUAGE_API_URL + '/delete/' + id);
    }

    createLanguage(game) {
        return axios.post(LANGUAGE_API_URL + '/create', game)
    }

    updateLanguage(id, game) {
        return axios.put(LANGUAGE_API_URL + '/games/' + id, game)
    }
}

export default new LanguageAPIService();