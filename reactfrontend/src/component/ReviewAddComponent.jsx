import React, { Component } from 'react';
import GameAPIService from "../service/GameAPIService";
import ReviewAPIService from "../service/ReviewAPIService";
import UserAPIService from "../service/UserAPIService";

class ReviewAddComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            gameId: this.props.match.params.id,
            gameName: '',
            rating: '',
            comment: '',
            ratingOptions: [1,2,3,4,5]
        }
        this.loadGame = this.loadGame.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.loadGame();
    }

    loadGame() {
        GameAPIService.retrieveGame(this.state.gameId)
            .then((response) => {
                let game = response.data;
                this.setState({
                    gameName: game.name
                })
            });
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }

    async handleSubmit(event) {
        event.preventDefault()
        let user = await UserAPIService.retrieveUser(1)
            .then(response => response.data);
        let game = await GameAPIService.retrieveGame(this.state.gameId)
            .then(response => response.data);
        let review = {reviewId: 999999, game: game, user: user, rating: this.state.rating, review: this.state.comment}
        ReviewAPIService.createReview(review)
            .then(response => {
                this.setState({message: 'Review Added Successfully'})
                this.props.history.push(`/games/${this.state.gameId}`)
                console.log(response)
            });
    }


    render() {
        return(
            <div>
                <h2>Review Game</h2>
                <form>

                    <div className="form-group">
                        <label>Rating:</label>
                        <select onChange={this.onChange} name="rating" >
                            {this.state.ratingOptions.map(
                                ratingOption =>
                                    <option key={ratingOption}>{ratingOption}</option>
                            )}
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Comment:</label>
                        <input type="text" placeholder="comment" name="comment"
                               className="form-control" value={this.state.comment} onChange={this.onChange}/>
                    </div>

                    <button className="btn btn-success" onClick={this.handleSubmit}>Save</button>
                </form>
            </div>
        )
    }
}

export default ReviewAddComponent;