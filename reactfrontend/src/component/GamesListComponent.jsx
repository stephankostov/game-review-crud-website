import React, { Component } from 'react';
import GameAPIService from '../service/GameAPIService'

class GamesListComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            games: [],
            message: null
        }
        this.refreshGames = this.refreshGames.bind(this);
        this.deleteGameClicked = this.deleteGameClicked.bind(this);
        this.viewGameClicked = this.viewGameClicked.bind(this);
    }

    componentDidMount() {
        this.refreshGames();
    }

    refreshGames() {
        GameAPIService.retrieveAllGames()
            .then((response) => {
                    this.setState({ games: response.data });
                });
    }

    deleteGameClicked(gameId) {
        GameAPIService.deleteGame(gameId)
            .then((response) => {
                    this.setState({message: `Delete of game ${gameId} Successful`});
                    this.refreshGames();
                });
    }

    viewGameClicked(id) {
        this.props.history.push(`games/${id}`);
    }

    addGame() {
        this.props.history.push('games-add');
    }

    render() {
        return (
            <div className="container">
                <h3>All Games</h3>

                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}

                <div className="container">
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.games.map(
                                    game =>
                                        <tr key={game.gameId}>
                                            <td>{game.gameId}</td>
                                            <td>{game.name}</td>
                                            <td>{game.avgRating}</td>
                                            <td><button className="btn btn-success" onClick={() => this.viewGameClicked(game.gameId)}>View</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteGameClicked(game.gameId)}>Delete</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <button className="btn btn-success" onClick={() => this.addGame()}>Add Game</button>
                </div>
            </div>
        )
    }
}

export default GamesListComponent;