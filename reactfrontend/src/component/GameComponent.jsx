import React, { Component } from 'react';
import GameAPIService from "../service/GameAPIService";

class GameComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            name: '',
            developerId: '',
            developerName: '',
            languageId: '',
            languageName: '',
            avgRating: ''
        }
        this.editGameClicked = this.editGameClicked.bind(this);
        this.loadGame = this.loadGame.bind(this);
    }

    componentDidMount() {
        this.loadGame();
    }

    loadGame() {
        GameAPIService.retrieveGame(this.state.id)
            .then((response) => {
                let game = response.data;
                console.log(response.data)
                this.setState({
                    name: game.name,
                    developerId: game.developer.developerId,
                    developerName: game.developer.name,
                    languageId: game.language.languageId,
                    languageName: game.language.name,
                    avgRating: game.avgRating
                })
            });
    }

    editGameClicked(id) {
        this.props.history.push(`/games-edit/${id}`);
    }

    viewDeveloperDetails() {
        this.props.history.push(`/developers/${this.state.developerId}`)
    }

    reviewGame() {
        this.props.history.push(`/review/${this.state.id}`)
    }

    render() {

        let { id, name, developerName, languageName, avgRating } = this.state;

        return (
            <div>
                <h3>Game Details</h3>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Developer</th>
                            <th>Language</th>
                            <th>Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{name}</td>
                            <td onClick={() => this.viewDeveloperDetails()}>{developerName}</td>
                            <td>{languageName}</td>
                            <td>{avgRating}</td>
                        </tr>
                    </tbody>
                </table>
                <div><button className="btn btn-success" onClick={() => this.reviewGame()}>Review</button></div>
                <div><button className="btn btn-warning" onClick={() => this.editGameClicked(id)}>Edit</button></div>
            </div>
        )
    }

}

export default GameComponent;