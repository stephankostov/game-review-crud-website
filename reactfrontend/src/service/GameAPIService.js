import axios from 'axios'

const GAME_API_URL = 'http://localhost:8080/api/games'

class GameAPIService {


    createGame(game) {
        return axios.post(GAME_API_URL + '/create', game);
    }

    retrieveGame(id) {
        return axios.get(GAME_API_URL + '/get/' + id);
    }

    retrieveAllGames() {
        return axios.get(GAME_API_URL + '/all');
    }

    updateGame(id, game) {
        return axios.put(GAME_API_URL + '/update/' + id, game);
    }

    deleteGame(id) {
        return axios.delete(GAME_API_URL + '/delete/' + id);
    }
}

export default new GameAPIService();