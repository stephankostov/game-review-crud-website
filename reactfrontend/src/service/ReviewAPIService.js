import axios from 'axios'

const REVIEW_API_URL = 'http://localhost:8080/api/reviews'

class ReviewAPIService {

    retrieveReview(id) {
        return axios.get(REVIEW_API_URL + '/get/' + id);
    }

    retrieveAllReviews() {
        return axios.get(REVIEW_API_URL + '/all');
    }

    deleteReview(id) {
        return axios.delete(REVIEW_API_URL + '/delete/' + id);
    }

    createReview(game) {
        return axios.post(REVIEW_API_URL + '/create', game)
    }

    updateReview(id, game) {
        return axios.put(REVIEW_API_URL + '/games/' + id, game)
    }
}

export default new ReviewAPIService();